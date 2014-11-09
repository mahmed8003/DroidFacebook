package com.tmxlr.libs.facebook.actions;

import java.util.concurrent.Callable;

import android.os.Handler;
import android.os.Looper;

import com.facebook.Session;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tmxlr.libs.facebook.Errors;
import com.tmxlr.libs.facebook.Errors.ErrorMsg;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.Logger;
import com.tmxlr.libs.facebook.Permission;
import com.tmxlr.libs.facebook.actions.listeners.ActionListener;
import com.tmxlr.libs.facebook.actions.listeners.OnReopenSessionListener;
import com.tmxlr.libs.facebook.entities.FbError;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequest.FbRequestBuilder;
import com.tmxlr.libs.facebook.network.FbResponse;

public abstract class Action implements Callable<Action> {

	public static final String TAG = Action.class.getSimpleName();
	
	private final Handler handler;
	private final FbSessionManager sessionManager;
	private final ActionListener actionListener;
	private FbRequest fbRequest;

	public Action(final FbSessionManager sessionManager, final ActionListener listener) {
		this.sessionManager = sessionManager;
		this.actionListener = listener;
		handler = new Handler(Looper.getMainLooper());
	}

	public abstract FbRequestBuilder createRequestBuilder();

	public abstract void handleResult(FbResponse response);

	public abstract Action reCreateAction();

	public void onPostProcessAction(Action action) {
	}

	public void onPreProcessAction(Action action) {
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public Permission[] getRequiredPermissions() {
		return null;
	}

	private boolean checkPermissions() {
		/*
		boolean result = true;
		Permission[] p = getRequiredPermissions();
		if (p != null) {
			result = sessionManager.hasPermissions(p);
		}
		return result;
		*/
		return true;
	}

	private void requestPermissions() {

		if (sessionManager.isLogin(true)) {
			if (sessionManager.canMakeAdditionalRequest()) {
				getActionListener().onWait();
				
				sessionManager.getSessionStatusCallback().setOnReopenSessionListener(new OnReopenSessionListener() {
					@Override
					public void onSuccess() {
						Action action = reCreateAction();
						FbClient.getInstance().performAction(action);
					}

					@Override
					public void onNotAcceptingPermissions(Permission.Type type) {
						String reason = Errors.getError(ErrorMsg.CANCEL_PERMISSIONS_PUBLISH, String.valueOf(getRequiredPermissions()));
						Logger.logError(Action.class, reason, null);
						FbError error = Errors.getPermissionError(String.valueOf(getRequiredPermissions()));
						sendErrorMessageOnUiThread(error);
					}
				});
				sessionManager.extendPublishPermissions(getRequiredPermissions());

			} else {
				return;
			}
		} else {
			String reason = Errors.getError(ErrorMsg.LOGIN);
			Logger.logError(Action.class, reason, null);
			FbError error = Errors.getLoginError(reason);
			getActionListener().onError(error);
		}

	
	}

	private void performAction() {

		try {
			FbRequestBuilder builder = createRequestBuilder();
			sendWaitMessageOnUiThread();
			fbRequest = builder.build(sessionManager);
			FbResponse response = new FbResponse(fbRequest);
			if (response.ok()) {
				handleResult(response);
			} else {
				String res = response.getBody();
				Gson gson = FbClient.getInstance().getGson();
				JsonObject resObj = gson.fromJson(res, JsonObject.class);
				if (res.contains("error")) {
					JsonObject errObj = resObj.getAsJsonObject("error");
					FbError error = gson.fromJson(errObj, FbError.class);
					Logger.logError(Action.class, res);
					sendErrorMessageOnUiThread(error);
				}
			}
		} catch (Exception e) {
			Logger.logError(Action.class, e.getMessage(), e);
			sendErrorMessageOnUiThread(Errors.getExceptionError(e));
		}

	}

	@Override
	public Action call() throws Exception {
		onPreProcessAction(this);

		if (checkPermissions()) {
			performAction();
		} else {
			requestPermissionsOnUiThread();
		}

		onPostProcessAction(this);

		return this;
	}

	public FbSessionManager getSessionManager() {
		return sessionManager;
	}

	public Session getSession() {
		return sessionManager.getActiveSession();
	}

	public void sendErrorMessageOnUiThread(final FbError error) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				actionListener.onError(error);
			}
		});
	}

	public void sendWaitMessageOnUiThread() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				actionListener.onWait();
			}
		});
	}

	public void sendCancleMessageOnUiThread() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				actionListener.onCancel();
			}
		});
	}

	public void requestPermissionsOnUiThread() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				requestPermissions();
			}
		});
	}

	public void doInUiThread(final OnUiThreadJob job) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				job.doInUiThread();
			}
		});
	}

	public interface OnUiThreadJob {
		public void doInUiThread();
	}
}
