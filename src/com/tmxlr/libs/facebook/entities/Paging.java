package com.tmxlr.libs.facebook.entities;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.actions.GetAction;

public class Paging {
	
	public static final String TAG = Paging.class.getSimpleName();

	@SerializedName("cursors")
	private Cursor cursor;

	@SerializedName("previous")
	private String previous;

	@SerializedName("next")
	private String next;
	
	private transient GetAction nextPageAction;
	private transient GetAction previousPageAction;
	
	public Paging() {
		cursor = null;
		previous = null;
		next = null;
		nextPageAction = null;
		previousPageAction = null;
	}

	
	public Cursor getCursor() {
		return cursor;
	}

	public String getPrevious() {
		return previous;
	}

	public String getNext() {
		return next;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public boolean hasPrevious() {
		return previous != null;
	}
	
	public void setNextPageAction(GetAction action) {
		this.nextPageAction = action;
	}
	
	public void setPrevPageAction(GetAction action) {
		this.previousPageAction = action;
	}
	
	public void nextPage() {
		if (nextPageAction != null) {
			FbClient.getInstance().performAction(nextPageAction);
		}else{
			Log.d(TAG, "No more next pages");
		}
	}
	
	public void previousPage() {
		if (previousPageAction != null) {
			FbClient.getInstance().performAction(previousPageAction);
		}else{
			Log.d(TAG, "No more previous pages");
		}
	}

}
