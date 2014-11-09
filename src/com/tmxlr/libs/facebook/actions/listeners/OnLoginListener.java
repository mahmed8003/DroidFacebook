package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.Permission;

public interface OnLoginListener extends ActionListener {

	void onLogin();

	void onNotAcceptingPermissions(Permission.Type type);
}
