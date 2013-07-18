package de.adorsys.erraihtml5.support;

import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import de.adorsys.errai.example.api.Person;

public final class Erraihtml5ValidatorFactory extends
		AbstractGwtValidatorFactory {

	@GwtValidation(value = { Person.class }, groups = { Default.class })
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}