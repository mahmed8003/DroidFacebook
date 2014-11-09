package com.tmxlr.libs.facebook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tmxlr.libs.facebook.actions.Action;
import com.tmxlr.libs.facebook.actions.DislikeAction;
import com.tmxlr.libs.facebook.actions.GetAppAccessTokenAction;
import com.tmxlr.libs.facebook.actions.GetCommentAction;
import com.tmxlr.libs.facebook.actions.GetCommentsAction;
import com.tmxlr.libs.facebook.actions.GetPostsAction;
import com.tmxlr.libs.facebook.actions.GetProfileAction;
import com.tmxlr.libs.facebook.actions.HasUserLikedAction;
import com.tmxlr.libs.facebook.actions.LikeAction;
import com.tmxlr.libs.facebook.actions.PostCommentAction;
import com.tmxlr.libs.facebook.actions.listeners.GetAccessTokenListener;
import com.tmxlr.libs.facebook.actions.listeners.GetCommentListener;
import com.tmxlr.libs.facebook.actions.listeners.GetCommentsListener;
import com.tmxlr.libs.facebook.actions.listeners.GetPostsListener;
import com.tmxlr.libs.facebook.actions.listeners.GetProfileListener;
import com.tmxlr.libs.facebook.actions.listeners.OnDislikeListener;
import com.tmxlr.libs.facebook.actions.listeners.OnHasUserLikedListener;
import com.tmxlr.libs.facebook.actions.listeners.OnLikeListener;
import com.tmxlr.libs.facebook.actions.listeners.OnLoginListener;
import com.tmxlr.libs.facebook.actions.listeners.OnLogoutListener;
import com.tmxlr.libs.facebook.actions.listeners.PostCommentListener;
import com.tmxlr.libs.facebook.entities.Entity;
import com.tmxlr.libs.facebook.entities.FbError;
import com.tmxlr.libs.facebook.network.FbRequest;
import com.tmxlr.libs.facebook.network.FbRequestParams;

public class FbClient {
	public static final String TAG = FbClient.class.getSimpleName();
	public static final String ACCESS_TOKEN_PARAM = "access_token";
	public static final String BUILD = "3.16.0";

	private static FbClient instance = null;
	private static FbConfiguration fbConfiguration = null;

	private final Gson gson;
	private final ExecutorService threadPool;

	private Activity activity;
	private FbSessionManager sessionManager;

	private FbClient() {
		threadPool = Executors.newCachedThreadPool();
		gson = createGson();
	}

	public static final FbClient getInstance(final Activity activity, final FbSessionManager sessionManager) {
		if (instance == null) {
			instance = new FbClient();
		}

		instance.activity = activity;
		instance.sessionManager = sessionManager;

		return instance;
	}

	public static final FbClient getInstance() {
		if (instance == null) {
			throw new RuntimeException("First call \"FbClient getInstance(final Activity activity)\" method in create method before calling simple getInstance method");
		}
		return instance;
	}

	public static final void setConfiguration(FbConfiguration configuration) {
		fbConfiguration = configuration;
	}

	public static final FbConfiguration getConfiguration() {
		return fbConfiguration;
	}

	public Activity getActivity() {
		return activity;
	}

	public FbSessionManager getSessionManager() {
		return sessionManager;
	}

	/*
	 * public void setActivity(Activity activity) { this.activity = activity; }
	 * 
	 * public void setSessionManager(FbSessionManager sessionManager) {
	 * this.sessionManager = sessionManager; }
	 */

	private Gson createGson() {
		return new GsonBuilder().setDateFormat(FbRequest.ISO_8601_FORMAT_STRING).create();
	}

	public void performAction(Action action) {
		threadPool.submit(action);
	}

	/**
	 * Login to Facebook
	 * 
	 * @param onLoginListener
	 */
	public void login(OnLoginListener onLoginListener) {
		sessionManager.login(onLoginListener);
	}

	/**
	 * Logout from Facebook
	 */
	public void logout(OnLogoutListener onLogoutListener) {
		sessionManager.logout(onLogoutListener);
	}

	/**
	 * Are we logged in to facebook
	 * 
	 * @return <code>True</code> if we have active and open session to facebook
	 */
	public boolean isLogin() {
		return sessionManager.isLogin(true);
	}

	public void getAppAccessToken(final GetAccessTokenListener listener) {
		GetAppAccessTokenAction action = new GetAppAccessTokenAction(getSessionManager(), listener);
		performAction(action);
	}

	public void getPosts(final String nodeId, final GetPostsListener listener) {
		this.getPosts(nodeId, null, listener);
	}

	public void getPosts(final String nodeId, final FbRequestParams params, final GetPostsListener listener) {
		GetPostsAction action = new GetPostsAction(nodeId, params, getSessionManager(), listener);
		performAction(action);
	}

	public void getComments(final String objId, final GetCommentsListener listener) {
		this.getComments(objId, null, listener);
	}

	public void getComments(final String objId, final FbRequestParams params, final GetCommentsListener listener) {
		GetCommentsAction action = new GetCommentsAction(objId, params, getSessionManager(), listener);
		performAction(action);
	}

	public void getComment(final String commentId, final GetCommentListener listener) {
		GetCommentAction action = new GetCommentAction(commentId, getSessionManager(), listener);
		performAction(action);
	}
	
	public void postComment(final String postId, final FbRequestParams params, final PostCommentListener listener) {
		PostCommentAction action = new PostCommentAction(postId, params, getSessionManager(), listener);
		performAction(action);
	}

	public void getProfile(final String userId, final FbRequestParams params, final GetProfileListener listener) {
		GetProfileAction action = new GetProfileAction(userId, params, getSessionManager(), listener);
		performAction(action);
	}

	public void getProfile(final String userId, final GetProfileListener listener) {
		this.getProfile(userId, null, listener);
	}
	
	public void like(final String objId, final OnLikeListener listener) {
		LikeAction action = new LikeAction(objId, getSessionManager(), listener);
		performAction(action);
	}
	
	public void dislike(final String objId, final OnDislikeListener listener) {
		DislikeAction action = new DislikeAction(objId, getSessionManager(), listener);
		performAction(action);
	}
	
	public void hasUserLiked(final String userId, final Entity entity, final OnHasUserLikedListener listener ) {
		HasUserLikedAction action = new HasUserLikedAction(userId, entity, getSessionManager(), listener);
		performAction(action);
	};

	public Gson getGson() {
		return gson;
	}

	public void fetchAppTokenAndOpenSession() {
		getAppAccessToken(new GetAccessTokenListener() {

			@Override
			public void onWait() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(FbError error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAccessToken(String token) {
				sessionManager.setClientBasedAccessToken(token);
			}
		});
	}

}
