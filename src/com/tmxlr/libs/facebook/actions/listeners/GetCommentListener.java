package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.entities.Comment;

public interface GetCommentListener extends GetActionListener {

	public void onSuccess(Comment comment);
}
