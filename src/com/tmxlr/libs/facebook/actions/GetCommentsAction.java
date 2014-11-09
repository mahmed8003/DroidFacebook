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
import com.tmxlr.libs.facebook.actions.listeners.GetCommentsListener;
import com.tmxlr.libs.facebook.entities.Comment;
import com.tmxlr.libs.facebook.entities.Paging;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class GetCommentsAction extends GetAction {

	private final String objId;
	private final FbRequestParams params;

	private GetCommentsAction(String url, FbSessionManager sessionManager, GetCommentsListener listener) {
		super(sessionManager, listener);
		this.objId = url;
		this.params = null;
	}

	public GetCommentsAction(String objId, FbRequestParams params, FbSessionManager sessionManager, GetCommentsListener listener) {
		super(sessionManager, listener);
		this.objId = objId;
		this.params = params;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		FbRequestBuilder builder = null;
		if (objId.contains("https://")) {
			builder = new FbRequestBuilder(objId, HttpMethod.GET, null);
		} else {
			String path = String.format("/%s/comments", objId);
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
		List<Comment> tComments = null;
		Paging tPaging = null;
		if (rootObj.has("data")) {
			JsonArray postsArray = rootObj.getAsJsonArray("data");
			Type listType = new TypeToken<ArrayList<Comment>>() {
			}.getType();
			tComments = gson.fromJson(postsArray, listType);
		}

		if (rootObj.has("paging")) {
			JsonObject pagingObj = rootObj.getAsJsonObject("paging");
			tPaging = gson.fromJson(pagingObj, Paging.class);
		}

		if (tPaging != null) {
			if (tPaging.hasNext()) {
				GetAction action = new GetCommentsAction(tPaging.getNext(), getSessionManager(), getActionListener());
				tPaging.setNextPageAction(action);
			}

			if (tPaging.hasPrevious()) {
				GetAction action = new GetCommentsAction(tPaging.getPrevious(), getSessionManager(), getActionListener());
				tPaging.setPrevPageAction(action);
			}
		} else {
			tPaging = new Paging();
		}

		final List<Comment> comments = tComments;
		final Paging paging = tPaging;

		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(comments, paging);
			}
		});
	}

	@Override
	public GetCommentsListener getActionListener() {
		return (GetCommentsListener) super.getActionListener();
	}

	@Override
	public Action reCreateAction() {
		GetCommentsAction action = new GetCommentsAction(objId, params, getSessionManager(), getActionListener());
		return action;
	}

}
