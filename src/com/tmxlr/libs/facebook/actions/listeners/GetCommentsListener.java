package com.tmxlr.libs.facebook.actions.listeners;

import java.util.List;

import com.tmxlr.libs.facebook.entities.Comment;
import com.tmxlr.libs.facebook.entities.Paging;

public interface GetCommentsListener extends GetActionListener {

	public void onSuccess(final List<Comment> comments, final Paging paging);

}
