package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.entities.Profile;

public interface GetProfileListener extends GetActionListener {

	public void onSuccess(Profile profile);
}
