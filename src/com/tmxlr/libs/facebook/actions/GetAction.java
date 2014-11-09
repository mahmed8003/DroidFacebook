package com.tmxlr.libs.facebook.actions;

import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.GetActionListener;

public abstract class GetAction extends Action {

	public GetAction(FbSessionManager sessionManager, GetActionListener listener) {
		super(sessionManager, listener);
	}

	@Override
	public GetActionListener getActionListener() {
		return (GetActionListener) super.getActionListener();
	}

}
