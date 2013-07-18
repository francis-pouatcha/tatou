package cm.adorsys.gpao.support;

import javax.validation.Validator;
import javax.validation.groups.Default;

import cm.adorsys.gpao.api.model.Users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public final class Erraihtml5ValidatorFactory extends
		AbstractGwtValidatorFactory {

	@GwtValidation(value = { Users.class }, groups = { Default.class })
	public interface GwtValidator extends Validator {
	}

	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(GwtValidator.class);
	}
}