package com.tmxlr.libs.facebook.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.tmxlr.libs.facebook.network.HttpRequest.UploadProgress;

public class FbRequestParams {

	private final ConcurrentHashMap<String, String> urlParams;
	private final ConcurrentHashMap<String, StreamWrapper> streamParams;
	private final ConcurrentHashMap<String, FileWrapper> fileParams;
	private UploadProgress uploadProgress;

	public FbRequestParams() {
		urlParams = new ConcurrentHashMap<String, String>();
		streamParams = new ConcurrentHashMap<String, StreamWrapper>();
		fileParams = new ConcurrentHashMap<String, FileWrapper>();
	}

	public FbRequestParams put(String key, String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
		return this;
	}

	public FbRequestParams put(String key, int value) {
		if (key != null) {
			urlParams.put(key, String.valueOf(value));
		}
		return this;
	}

	public FbRequestParams put(String key, float value) {
		if (key != null) {
			urlParams.put(key, String.valueOf(value));
		}
		return this;
	}

	public FbRequestParams put(String key, long value) {
		if (key != null) {
			urlParams.put(key, String.valueOf(value));
		}
		return this;
	}

	public FbRequestParams put(String key, Date date) {
		if (key != null) {
			urlParams.put(key, FbRequest.iso8601DateFormat.format(date));
		}
		return this;
	}

	public FbRequestParams put(final String name, final File file) throws FileNotFoundException {
		return put(name, name, file);
	}

	public FbRequestParams put(final String name, final String filename, final File file) throws FileNotFoundException {
		return put(name, filename, null, file);
	}

	public FbRequestParams put(final String name, final String filename, final String contentType, final File file) throws FileNotFoundException {
		if (file == null || !file.exists()) {
			throw new FileNotFoundException();
		}
		if (name != null) {
			fileParams.put(name, new FileWrapper(name, filename, contentType, file));
		}
		return this;
	}

	public FbRequestParams put(final String name, final InputStream stream) {
		return put(name, name, null, stream);
	}

	public FbRequestParams put(final String name, final String filename, final String contentType, final InputStream stream) {
		if (name != null && stream != null) {
			streamParams.put(name, new StreamWrapper(name, filename, contentType, stream));
		}
		return this;
	}
	
	public void setUploadProgress(UploadProgress uploadProgress) {
		this.uploadProgress = uploadProgress;
	}
	
	public ConcurrentHashMap<String, String> getUrlParams() {
		return urlParams;
	}

	public ConcurrentHashMap<String, StreamWrapper> getStreamParams() {
		return streamParams;
	}

	public ConcurrentHashMap<String, FileWrapper> getFileParams() {
		return fileParams;
	}

	public UploadProgress getUploadProgress() {
		return uploadProgress;
	}



	public class FileWrapper {

		public final String name;
		public final String filename;
		public final String contentType;
		public final File file;

		public FileWrapper(final String name, final String filename, final String contentType, final File file) {
			this.name = name;
			this.filename = filename;
			this.contentType = contentType;
			this.file = file;

		}
	}

	public class StreamWrapper {

		public final String name;
		public final String filename;
		public final String contentType;
		public final InputStream stream;

		public StreamWrapper(final String name, final String filename, final String contentType, final InputStream stream) {
			this.name = name;
			this.filename = filename;
			this.contentType = contentType;
			this.stream = stream;
		}
	}

}
