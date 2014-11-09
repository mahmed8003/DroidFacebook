package com.tmxlr.libs.facebook.actions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetPostsListener;
import com.tmxlr.libs.facebook.entities.Paging;
import com.tmxlr.libs.facebook.entities.Post;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class GetPostsAction extends GetAction {

	private final String nodeId;
	private final FbRequestParams params;

	public GetPostsAction(String nodeId, FbRequestParams params, FbSessionManager sessionManager, GetPostsListener listener) {
		super(sessionManager, listener);
		this.nodeId = nodeId;
		this.params = params;
	}

	private GetPostsAction(String url, FbSessionManager sessionManager, GetPostsListener listener) {
		super(sessionManager, listener);
		nodeId = url;
		params = null;
	}
	
	@Override
	public FbRequestBuilder createRequestBuilder() {
		FbRequestBuilder builder = null;
		if (nodeId.contains("https://")) {
			 builder = new FbRequestBuilder(nodeId, HttpMethod.GET, params);
		} else {
			String path = String.format("/%s/posts", nodeId);
			builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.GET, params);
			builder.setPath(path);
		}
		
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		JsonObject rootObj = gson.fromJson(resData, JsonObject.class);

		List<Post> tPosts = null;
		Paging tPaging = null;

		if (rootObj.has("data")) {
			JsonArray postsArray = rootObj.getAsJsonArray("data");
			Type listType = new TypeToken<ArrayList<Post>>() {
			}.getType();
			tPosts = gson.fromJson(postsArray, listType);
		}

		if (rootObj.has("paging")) {
			JsonObject pagingObj = rootObj.getAsJsonObject("paging");
			tPaging = gson.fromJson(pagingObj, Paging.class);
		}

		if (tPaging != null) {
			if (tPaging.hasNext()) {
				GetPostsAction action = new GetPostsAction(tPaging.getNext(), getSessionManager(), getActionListener());
				tPaging.setNextPageAction(action);
			}

			if (tPaging.hasPrevious()) {
				GetPostsAction action = new GetPostsAction(tPaging.getPrevious(), getSessionManager(), getActionListener());
				tPaging.setPrevPageAction(action);
			}
		}else{
			tPaging = new Paging();
		}
		
		final List<Post> posts = tPosts;
		final Paging paging = tPaging;

		doInUiThread(new OnUiThreadJob() {

			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(posts, paging);
			}
		});

	}

	@Override
	public GetPostsListener getActionListener() {
		return (GetPostsListener) super.getActionListener();
	}

	@Override
	public Action reCreateAction() {
		GetPostsAction action = new GetPostsAction(nodeId, params, getSessionManager(), getActionListener());
		return action;
	}

}
