package com.tmxlr.libs.facebook.actions.listeners;

import com.tmxlr.libs.facebook.entities.Entity;

public interface OnHasUserLikedListener extends GetActionListener {
	
	public void onComplete(Entity entity, boolean liked, int totalCount);

}
