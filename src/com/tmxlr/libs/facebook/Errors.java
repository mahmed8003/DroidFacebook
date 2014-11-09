package com.tmxlr.libs.facebook;

import java.util.Locale;

import com.tmxlr.libs.facebook.entities.FbError;

public class Errors {
	
	public static final FbError getNoResponseError(String request) {
		FbError error = new FbError("NoResponse", "Something went wrong, Unable to process " + request);
		return error;
	}

	public static final FbError getExceptionError(Exception exception) {
		FbError error = new FbError("ExceptionError", exception.getMessage(), 8700);
		return error;
	}

	public static final FbError getPermissionError(String permission) {
		FbError error = new FbError("PermissionError", "Permission: " + permission + " wasn't accepted by user", 8701);
		return error;
	}
	
	public static final FbError getLoginError(String message) {
		FbError error = new FbError("LoginError", "Publish permission: " + message + " wasn't set by FbConfiguration", 8702);
		return error;
	}

	public static enum ErrorMsg {
		LOGIN("You are not logged in"), CANCEL_WEB_LOGIN("User canceled the login web dialog"), PERMISSIONS_PUBLISH("Publish permission: '%s' wasn't set by SimpleFacebookConfiguration"), CANCEL_PERMISSIONS_PUBLISH(
				"Publish permissions: '%s' weren't accepted by user");

		private String mMsg;

		private ErrorMsg(String msg) {
			mMsg = msg;
		}

		public String message() {
			return mMsg;
		}
	}

	/**
	 * Get final error message
	 * 
	 * @param errorMsg
	 * @param params
	 * @return
	 */
	public static String getError(ErrorMsg errorMsg, Object... params) {
		if (params == null) {
			return errorMsg.message();
		} else {
			return String.format(Locale.US, errorMsg.message(), params);
		}
	}

	public static String getError(ErrorMsg errorMsg) {
		return errorMsg.message();
	}
}
