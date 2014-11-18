package com.tmxlr.libs.facebook.actions;

import com.google.gson.Gson;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetObjectListener;
import com.tmxlr.libs.facebook.entities.FObject;
import com.tmxlr.libs.facebook.entities.Post;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class GetObjectAction extends GetAction {

	final Post post;
	final FbRequestParams params;
	
	public GetObjectAction(Post post, FbRequestParams params, FbSessionManager sessionManager, GetObjectListener listener) {
		super(sessionManager, listener);
		this.post = post;
		this.params = params;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		String path = String.format("/%s", post.getObjectId());
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.GET, params);
		builder.setPath(path);
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		final FObject object = gson.fromJson(resData, FObject.class);

		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(post, object);
			}
		});		
	}

	@Override
	public Action reCreateAction() {
		return null;
	}
	
	@Override
	public GetObjectListener getActionListener() {
		return (GetObjectListener) super.getActionListener();
	}

}
