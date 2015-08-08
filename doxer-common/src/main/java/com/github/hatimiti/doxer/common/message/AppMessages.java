package com.github.hatimiti.doxer.common.message;

import static com.github.hatimiti.doxer.common.message.AppMessageLevel.*;
import static java.util.stream.Collectors.*;

import com.github.hatimiti.doxer.common.domain.type.ArrayListType;

public abstract class AppMessages<M extends AppMessages<?>> extends ArrayListType<AppMessage> {

	protected AppMessages() {
	}

	protected AppMessages(AppMessage message) {
		add(message);
	}

	protected AppMessages(AppMessageLevel level, String key, Object... params) {
		add(createMessage(level, key, params));
	}

	protected AppMessages(AppMessageLevel level, boolean isResource, String keyOrMessage, Object... params) {
		add(createMessage(level, isResource, keyOrMessage, params));
	}

	public boolean addInfo(String key, Object... params) {
		return add(createMessage(INFO, key, params));
	}
	public boolean addInfo(String key, boolean isResource, Object... params) {
		return add(createMessage(INFO, isResource, key, params));
	}
	public M filterByInfoLevel() {
		return filterByLevel(INFO);
	}

	public boolean addWarn(String key, Object... params) {
		return add(createMessage(WARN, key, params));
	}
	public boolean addWarn(String key, boolean isResource, Object... params) {
		return add(createMessage(WARN, isResource, key, params));
	}
	public M filterByWarnLevel() {
		return filterByLevel(WARN);
	}

	public boolean addError(String key, Object... params) {
		return add(createMessage(ERROR, key, params));
	}
	public boolean addError(String key, boolean isResource, Object... params) {
		return add(createMessage(ERROR, isResource, key, params));
	}
	public M filterByErrorLevel() {
		return filterByLevel(ERROR);
	}

	private AppMessage createMessage(
			AppMessageLevel level, String key, Object... params) {
		return new AppMessage(level, key, params);
	}

	private AppMessage createMessage(
			AppMessageLevel level, boolean isResource, String keyOrMessage, Object... params) {
		return new AppMessage(level, isResource, keyOrMessage, params);
	}

	private M filterByLevel(AppMessageLevel level) {
		return this.stream().filter(m -> level == m.getLevel()).collect(toCollection(this::createInstance));
	}

	protected abstract M createInstance();

}
