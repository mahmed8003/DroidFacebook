package com.tmxlr.libs.facebook.actions;

import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.actions.listeners.PostActionListener;

public abstract class PostAction extends Action {

	public static final String TAG = PostAction.class.getSimpleName();

	public PostAction(FbSessionManager sessionManager, PostActionListener listener) {
		super(sessionManager, listener);
	}
	
	@Override
	public PostActionListener getActionListener() {
		return (PostActionListener) super.getActionListener();
	}

}
