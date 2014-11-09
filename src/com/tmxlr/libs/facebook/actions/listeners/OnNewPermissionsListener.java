package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.Permission.Type;

public interface OnNewPermissionsListener {
    void onSuccess(String accessToken);
    void onNotAcceptingPermissions(Type type);
}