package com.tmxlr.libs.facebook.actions;

import android.util.Log;

import com.google.gson.Gson;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetProfileListener;
import com.tmxlr.libs.facebook.entities.Profile;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class GetProfileAction extends GetAction {

	private final String userId;
	private final FbRequestParams params;
	
	public GetProfileAction(String userId, FbRequestParams params, FbSessionManager sessionManager, GetProfileListener listener) {
		super(sessionManager, listener);
		this.userId = userId;
		this.params = params;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.GET, params);
		builder.setPath(userId);
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		FbClient client = FbClient.getInstance();
		Gson gson = client.getGson();

		String resData = response.getBody();
		Log.d(TAG, resData);
		final Profile profile = gson.fromJson(resData, Profile.class);

		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				getActionListener().onSuccess(profile);
			}
		});
		
	}

	@Override
	public Action reCreateAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GetProfileListener getActionListener() {
		return (GetProfileListener) super.getActionListener();
	}

}
