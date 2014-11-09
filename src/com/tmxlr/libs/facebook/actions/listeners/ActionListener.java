package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.entities.FbError;

public interface ActionListener {

	public void onError(FbError error);

	public void onWait();
	
	public void onCancel();

}
