package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.entities.FObject;
import com.tmxlr.libs.facebook.entities.Post;

public interface GetObjectListener extends GetActionListener {

	public void onSuccess(Post post, FObject object);
}
