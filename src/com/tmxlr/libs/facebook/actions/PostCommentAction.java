package com.tmxlr.libs.facebook.actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.Permission;
import com.tmxlr.libs.facebook.actions.listeners.PostCommentListener;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class PostCommentAction extends PostAction {

	private final String postId;
	private final FbRequestParams params;

	public PostCommentAction(String postId, FbRequestParams params, FbSessionManager sessionManager, PostCommentListener listener) {
		super(sessionManager, listener);
		this.postId = postId;
		this.params = params;
	}
	
	@Override
	public Permission[] getRequiredPermissions() {
		Permission[] permissions = new Permission[]{Permission.PUBLISH_ACTION};
		return permissions;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		String path = String.format("%s/comments", postId);
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.POST, params);
		builder.setPath(path);
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		JsonObject jsonObject = gson.fromJson(resData, JsonObject.class);
		final String id = jsonObject.get("id").getAsString();
		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(id);
			}
		});
	}

	@Override
	public PostCommentListener getActionListener() {
		return (PostCommentListener) super.getActionListener();
	}

	@Override
	public Action reCreateAction() {
		PostCommentAction action = new PostCommentAction(postId, params, getSessionManager(), getActionListener());
		return action;
	}

}
