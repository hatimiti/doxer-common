package com.github.hatimiti.doxer.common.message;


public class GlobalMessages extends AppMessages<GlobalMessages> {

	public GlobalMessages() {
	}

	public GlobalMessages(AppMessage message) {
		super(message);
	}

	public GlobalMessages(
			AppMessageLevel level, String key, Object... params) {
		super(level, key, params);
	}

	public GlobalMessages(
			AppMessageLevel level, boolean isResource, String keyOrMessage, Object... params) {
		super(level, isResource, keyOrMessage, params);
	}

	@Override
	protected GlobalMessages createInstance() {
		return new GlobalMessages();
	}

}
