package com.tmxlr.libs.facebook.actions;

import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.DeleteActionListener;

public abstract class DeleteAction extends Action {

	public DeleteAction(FbSessionManager sessionManager, DeleteActionListener listener) {
		super(sessionManager, listener);
	}

	@Override
	public DeleteActionListener getActionListener() {
		return (DeleteActionListener) super.getActionListener();
	}
}
