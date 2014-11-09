package com.tmxlr.libs.facebook.actions;

import com.google.gson.Gson;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetCommentListener;
import com.tmxlr.libs.facebook.entities.Comment;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbResponse;

public class GetCommentAction extends GetAction {

	private final String commentId;

	public GetCommentAction(String commentId, FbSessionManager sessionManager, GetCommentListener listener) {
		super(sessionManager, listener);
		this.commentId = commentId;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		final Comment comment = gson.fromJson(resData, Comment.class);

		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(comment);
			}
		});
	}

	@Override
	public GetCommentListener getActionListener() {
		return (GetCommentListener) super.getActionListener();
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		String path = String.format("/%s", commentId);
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.GET, null);
		builder.setPath(path);
		return builder;
	}

	@Override
	public Action reCreateAction() {
		GetCommentAction action = new GetCommentAction(commentId, getSessionManager(), getActionListener());
		return action;
	}

}
