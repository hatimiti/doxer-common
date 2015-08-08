package com.github.hatimiti.doxer.common.message;

import java.util.Objects;


public class OwnedMessages extends AppMessages<OwnedMessages> {

	protected Owner owner;

//	static final Collector<OwnedMessages, ?, OwnedMessages> merge
//		= Collector.of(OwnedMessages::new, (l, t) -> t.addAll(l), (l, t) -> { t.addAll(l); return t; }, Characteristics.IDENTITY_FINISH);
//
//	private OwnedMessages() {
//	}

	public OwnedMessages(Owner owner) {
		Objects.requireNonNull(owner);
		this.owner = owner;
	}

	public OwnedMessages(Owner owner, AppMessage message) {
		super(message);
		Objects.requireNonNull(owner);
		this.owner = owner;
	}

	public OwnedMessages(
			Owner owner,
			AppMessageLevel level, String key, Object... params) {
		super(level, key, params);
		Objects.requireNonNull(owner);
		this.owner = owner;
	}

	public OwnedMessages(
			Owner owner,
			AppMessageLevel level, boolean isResource, String keyOrMessage, Object... params) {
		super(level, isResource, keyOrMessage, params);
		Objects.requireNonNull(owner);
		this.owner = owner;
	}

	public Owner getOwner() {
		return this.owner;
	}

	@Override
	protected OwnedMessages createInstance() {
		return new OwnedMessages(this.owner);
	}

}
