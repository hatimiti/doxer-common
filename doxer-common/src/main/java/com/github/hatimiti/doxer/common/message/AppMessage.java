package com.github.hatimiti.doxer.common.message;

import static java.util.Objects.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class AppMessage implements Serializable, Comparator<AppMessage> {

	/**
	 * <p>The message key or literal message. If isResource is true, this field means literal message.</p>
	 */
	protected String keyOrMessage = null;

	/**
	 * <p>The replacement values for this mesasge.</p>
	 */
	protected Object[] params = null;

	/**
	 * <p>Indicates level of this message.</p>
	 */
	protected AppMessageLevel level;
	
	/**
	 * <p>Indicates order of this message.</p>
	 */
	protected int order;
	
	/**
	 * <p>Indicates whether the key is taken to be as a  bundle key [true] or literal value [false].</p>
	 */
	protected boolean isResource = true;

	public AppMessage(AppMessageLevel level, String key, Object... params) {
		this(level, true, key, params);
	}

	public AppMessage(AppMessageLevel level, boolean isResource, String keyOrMessage, Object... params) {
		requireNonNull(level);
		requireNonNull(keyOrMessage);
		this.level = level;
		this.isResource = isResource;
		this.keyOrMessage = keyOrMessage;
		this.params = params;
	}
	
	public AppMessageLevel getLevel() {
		return this.level;
	}

	public String getKeyOrMessage() {
		return this.keyOrMessage;
	}
	
	public Object[] getParams() {
		return Arrays.copyOf(this.params, this.params.length);
	}
	
	public boolean isResource() {
		return this.isResource;
	}
	
	public AppMessage order(int order) {
		this.order = order;
		return this;
	}

	/**
	 * <p>Returns a String in the format: key[value1, value2, etc].</p>
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder buff = new StringBuilder();
		buff.append(this.keyOrMessage);
		buff.append("[");
		if (this.params != null) {
			for (int i = 0; i < this.params.length; i++) {
				buff.append(this.params[i]);
				// don't append comma to last entry
				if (i < this.params.length - 1) {
					buff.append(", ");
				}
			}
		}
		buff.append("]");
		return buff.toString();
	}

	@Override
	public int compare(AppMessage o1, AppMessage o2) {
		// TODO
		return 0;
	}

}
