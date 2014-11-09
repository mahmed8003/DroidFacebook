package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.Permission;

public interface OnReopenSessionListener {
	public void onSuccess();

	public void onNotAcceptingPermissions(Permission.Type type);
}
