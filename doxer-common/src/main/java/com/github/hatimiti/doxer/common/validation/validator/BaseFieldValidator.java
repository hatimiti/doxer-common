package com.github.hatimiti.doxer.common.validation.validator;

import static com.github.hatimiti.doxer.common.message.AppMessageLevel.*;
import static java.util.Objects.*;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.message.OwnedMessages;
import com.github.hatimiti.doxer.common.message.Owner;
import com.github.hatimiti.doxer.common.validation.Vval;

public abstract class BaseFieldValidator implements Validator {

	protected AppMessagesContainer container;
	protected String templateMessageKey;

	protected BaseFieldValidator(AppMessagesContainer container) {
		requireNonNull(container);
		this.container = container;
		this.templateMessageKey = getDefaultMessageKey();
	}

	protected BaseFieldValidator key(String templateMessageKey) {
		requireNonNull(templateMessageKey);
		this.templateMessageKey = templateMessageKey;
		return this;
	}

	@Override
	public boolean check(
			final Vval value,
			final Owner owner,
			final Object... params) {

		boolean result = checkSpecifically(value);
		if (!result) {
			addMessage(owner, params);
		}
		return result;
	}

	protected void addMessage(final Owner owner, final Object... params) {
		addMessage(false, owner, params);
	}

	protected void addMessage(
			final boolean isGlobal,
			final Owner owner,
			final Object... params) {

		container.add(new OwnedMessages(owner, ERROR, this.templateMessageKey, params));
	}

	protected abstract boolean checkSpecifically(Vval value);
	protected abstract String getDefaultMessageKey();
}
