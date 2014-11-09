package com.tmxlr.libs.facebook.actions;

import com.tmxlr.libs.facebook.Errors;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetAccessTokenListener;
import com.tmxlr.libs.facebook.entities.FbError;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;



public class GetAppAccessTokenAction extends GetAction {


	public GetAppAccessTokenAction(FbSessionManager sessionManager, GetAccessTokenListener listener) {
		super(sessionManager, listener);
	}
	
	@Override
	public FbRequestBuilder createRequestBuilder() {
		String path = "oauth/access_token";
		FbRequestParams params = new FbRequestParams();
		params.put("client_id", getSessionManager().getConfiguration().getAppId());
		params.put("client_secret", getSessionManager().getConfiguration().getAppSecret());
		params.put("grant_type", "client_credentials");
		
		FbRequestBuilder builder = new FbRequestBuilder(FbRequest.BASE_URL, HttpMethod.GET, params);
		builder.setPath(path);
		return builder;
	}

	@Override
	public void handleResult(FbResponse response) {
		final String res = response.getBody();
		doInUiThread(new OnUiThreadJob() {
			@Override
			public void doInUiThread() {
				if (res != null) {
					final String[] parts = res.split("=");
					if (parts.length == 2) {
						getActionListener().onAccessToken(parts[1]);
					} else {
						FbError error = Errors.getNoResponseError("GetAppAccessTokenRequest");
						sendErrorMessageOnUiThread(error);
					}
				} else {
					FbError error = Errors.getNoResponseError("GetAppAccessTokenRequest");
					sendErrorMessageOnUiThread(error);
				}
			}
		});

	}

	@Override
	public GetAccessTokenListener getActionListener() {
		return (GetAccessTokenListener) super.getActionListener();
	}

	@Override
	public Action reCreateAction() {
		GetAppAccessTokenAction action = new GetAppAccessTokenAction(getSessionManager(), getActionListener());
		return action;
	}
	

}
