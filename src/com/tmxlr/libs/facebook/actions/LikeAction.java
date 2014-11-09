package com.tmxlr.libs.facebook.actions;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.OnLikeListener;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbResponse;

public class LikeAction extends PostAction {

	private final String objId;

	public LikeAction(String objId, FbSessionManager sessionManager, OnLikeListener listener) {
		super(sessionManager, listener);
		this.objId = objId;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		String path = String.format("/%s/likes", objId);
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.POST, null);
		builder.setPath(path);
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		Log.d(TAG, resData);
		final JsonObject jsonObject = gson.fromJson(resData, JsonObject.class);

		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				boolean result = false;
				if (jsonObject.has("success")) {
					result = jsonObject.get("success").getAsBoolean();
				} else {
					result = jsonObject.get("FACEBOOK_NON_JSON_RESULT").getAsBoolean();
				}
				getActionListener().onComplete(result);
			}
		});

	}

	@Override
	public Action reCreateAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnLikeListener getActionListener() {
		return (OnLikeListener) super.getActionListener();
	}
}
