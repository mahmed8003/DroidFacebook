package com.tmxlr.libs.facebook.actions.listeners;

import java.util.List;

import com.tmxlr.libs.facebook.entities.Paging;
import com.tmxlr.libs.facebook.entities.Post;

public interface GetPostsListener extends GetActionListener {
	
	public void onSuccess(List<Post> posts, Paging paging);

}
