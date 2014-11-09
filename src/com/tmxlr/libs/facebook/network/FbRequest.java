package com.tmxlr.libs.facebook.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import com.tmxlr.libs.facebook.FbClient;
import com.tmxlr.libs.facebook.FbSessionManager;
import com.tmxlr.libs.facebook.network.FbRequestParams.FileWrapper;
import com.tmxlr.libs.facebook.network.FbRequestParams.StreamWrapper;

public class FbRequest extends HttpRequest {

	public static final String TAG = FbRequest.class.getSimpleName();

	public enum HttpMethod {
		GET, POST, DELETE, HEAD, OPTIONS, PUT, TRACE
	}

	public static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final SimpleDateFormat iso8601DateFormat = new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.getDefault());

	public static final String BASE_URL = "https://graph.facebook.com/v2.1/";
	private static final String USER_AGENT_HEADER = "User-Agent";
	private static final String USER_AGENT_BASE = "FBAndroidSDK";
	private static final String FORMAT_PARAM = "format";
	private static final String FORMAT_JSON = "json";
	private static final String SDK_PARAM = "sdk";
	private static final String SDK_ANDROID = "android";
	private static final String BATCH_APP_ID_PARAM = "batch_app_id";
	private static final String ACCESS_TOKEN_PARAM = "access_token";

	/*
	 * public FbRequest(CharSequence url, String method) throws
	 * HttpRequestException { super(url, method); }
	 */
	private FbRequest(URL url, String method) throws HttpRequestException {
		super(url, method);
	}

	public static class FbRequestBuilder {

		private final String baseUrl;
		private String path;
		private final HttpMethod httpMethod;
		private final FbRequestParams params;

		public FbRequestBuilder(String baseUrl, HttpMethod httpMethod, FbRequestParams params) {
			this.baseUrl = baseUrl;
			this.httpMethod = httpMethod;
			if (params != null) {
				this.params = params;
			} else {
				this.params = new FbRequestParams();
			}
		}

		public void setPath(String path) {
			this.path = path;
		}

		public FbRequestBuilder put(String key, String value) {
			params.put(key, value);
			return this;
		}

		public FbRequestBuilder put(String key, int value) {
			params.put(key, value);
			return this;
		}

		public FbRequestBuilder put(String key, float value) {
			params.put(key, value);
			return this;
		}

		public FbRequestBuilder put(String key, long value) {
			params.put(key, value);
			return this;
		}

		public FbRequestBuilder put(String key, Date date) {
			params.put(key, date);
			return this;
		}

		public FbRequestBuilder put(final String name, final File file) throws FileNotFoundException {
			return put(name, name, file);
		}

		public FbRequestBuilder put(final String name, final String filename, final File file) throws FileNotFoundException {
			return put(name, filename, null, file);
		}

		public FbRequestBuilder put(final String name, final String filename, final String contentType, final File file) throws FileNotFoundException {
			params.put(name, filename, contentType, file);
			return this;
		}

		public FbRequestBuilder put(final String name, final InputStream stream) {
			return put(name, name, null, stream);
		}

		public FbRequestBuilder put(final String name, final String filename, final String contentType, final InputStream stream) {
			params.put(name, filename, contentType, stream);
			return this;
		}

		public FbRequest build(FbSessionManager mgr) throws MalformedURLException {
			

			String accessToken = mgr.getAccessToken();
			if (accessToken != null) {
				put(ACCESS_TOKEN_PARAM, accessToken);
			} else {
				accessToken = FbClient.getConfiguration().getAppId() + "|" + FbClient.getConfiguration().getAppSecret();
				put(ACCESS_TOKEN_PARAM, accessToken);
			}

			put(BATCH_APP_ID_PARAM, mgr.getConfiguration().getAppId());
			put(SDK_PARAM, SDK_ANDROID);
			put(FORMAT_PARAM, FORMAT_JSON);
			
			
			URL url = getUrl();
			FbRequest request = new FbRequest(url, httpMethod.name());
			request.header(USER_AGENT_HEADER, String.format("%s.%s", USER_AGENT_BASE, FbClient.BUILD));

			if (httpMethod == HttpMethod.POST) {
				for (ConcurrentHashMap.Entry<String, String> entry : params.getUrlParams().entrySet()) {
					request.part(entry.getKey(), entry.getValue());
				}

				for (ConcurrentHashMap.Entry<String, FileWrapper> entry : params.getFileParams().entrySet()) {
					FileWrapper fw = entry.getValue();
					request.part(fw.name, fw.filename, fw.contentType, fw.file);
				}

				for (ConcurrentHashMap.Entry<String, StreamWrapper> entry : params.getStreamParams().entrySet()) {
					StreamWrapper sw = entry.getValue();
					request.part(sw.name, sw.filename, sw.contentType, sw.stream);
				}
			}

			if (params.getUploadProgress() != null) {
				request.progress(params.getUploadProgress());
			}

			return request;
		}

		private URL getUrl() throws MalformedURLException {
			URL url = new URL(baseUrl);
			if (path != null) {
				url = new URL(url, path);
			}

			if (httpMethod == HttpMethod.GET || httpMethod == HttpMethod.DELETE) {
				String urlStr = url.toString();
				for (ConcurrentHashMap.Entry<String, String> entry : params.getUrlParams().entrySet()) {
					urlStr = addParameter(urlStr, entry.getKey(), entry.getValue());
				}
				url = new URL(urlStr);
			}

			return url;
		}

		private String addParameter(String URL, String name, String value) {
			int qpos = URL.indexOf('?');
			int hpos = URL.indexOf('#');
			char sep = qpos == -1 ? '?' : '&';
			String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
			return hpos == -1 ? URL + seg : URL.substring(0, hpos) + seg + URL.substring(hpos);
		}

		private String encodeUrl(String url) {
			try {
				return URLEncoder.encode(url, "UTF-8");
			} catch (UnsupportedEncodingException uee) {
				throw new IllegalArgumentException(uee);
			}
		}
	}
}
