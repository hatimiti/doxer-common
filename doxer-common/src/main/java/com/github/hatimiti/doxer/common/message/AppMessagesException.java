package com.github.hatimiti.doxer.common.message;

public class AppMessagesException extends RuntimeException {

	private final AppMessages<?> appMessages;

	public AppMessagesException(Owner owner, AppMessage message) {
		appMessages = new OwnedMessages(owner, message);
	}

	public AppMessagesException(AppMessage message) {
		appMessages = new GlobalMessages(message);
	}

	public AppMessages<?> getAppMessages() {
		return appMessages;
	}

}
