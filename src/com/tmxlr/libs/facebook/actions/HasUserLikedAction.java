package com.tmxlr.libs.facebook.actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.OnHasUserLikedListener;
import com.tmxlr.libs.facebook.entities.Entity;
import com.tmxlr.libs.facebook.entities.FbError;
import com.tmxlr.libs.facebook.entities.Paging;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbRequest.HttpMethod;
import com.tmxlr.libs.facebook.network.FbRequestParams;
import com.tmxlr.libs.facebook.network.FbResponse;

public class HasUserLikedAction extends GetAction {

	private final String userId;
	private final Entity entity;
	private final String url;
	

	public HasUserLikedAction(String userId, Entity entity, FbSessionManager sessionManager, OnHasUserLikedListener listener) {
		super(sessionManager, listener);
		this.userId = userId;
		this.entity = entity;
		this.url = entity.getId();
	}
	
	private HasUserLikedAction(String userId, Entity entity, String url, FbSessionManager sessionManager, OnHasUserLikedListener listener) {
		super(sessionManager, listener);
		this.userId = userId;
		this.entity = entity;
		this.url = url;
	}

	@Override
	public FbRequestBuilder createRequestBuilder() {
		FbRequestBuilder builder = null;
		if (url.contains("https://")) {
			builder = new FbRequestBuilder(url, HttpMethod.GET, null);
		} else {
			FbRequestParams params = new FbRequestParams();
			params.put("limit", 1000).put("summary", 1);
			String path = String.format("%s/likes", url);
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

		int tTotalCount = 0;
		if (rootObj.has("summary")) {
			JsonObject summary = rootObj.getAsJsonObject("summary");
			tTotalCount = summary.get("total_count").getAsInt();
		}

		Paging tPaging = null;
		if (rootObj.has("paging")) {
			JsonObject pagingObj = rootObj.getAsJsonObject("paging");
			tPaging = gson.fromJson(pagingObj, Paging.class);
		}

		final int totalCount = tTotalCount;
		final Paging paging = tPaging;

		if (rootObj.has("data")) {
			final String data = rootObj.get("data").toString();

			doInUiThread(new OnUiThreadJob() {
				@Override
				public void doInUiThread() {
					if (data.contains(userId)) {
						getActionListener().onComplete(entity, true, totalCount);
					} else {
						if (paging != null) {
							if (paging.hasNext()) {
								final GetAction action = new HasUserLikedAction(userId, entity, paging.getNext(), getSessionManager(), getActionListener());
								FbClient.getInstance().performAction(action);
							} else {
								getActionListener().onComplete(entity, false, totalCount);
							}
						}else{
							FbError error = new FbError("InvalidData", "Something went wrong");
							getActionListener().onError(error);
						}
					}
				}
			});
		}
	}

	@Override
	public Action reCreateAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnHasUserLikedListener getActionListener() {
		return (OnHasUserLikedListener) super.getActionListener();
	}

}
