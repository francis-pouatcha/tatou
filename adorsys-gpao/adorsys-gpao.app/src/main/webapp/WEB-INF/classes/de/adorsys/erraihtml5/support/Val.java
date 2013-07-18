package de.adorsys.erraihtml5.support;

import java.util.Collection;
import java.util.Set;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.errai.databinding.client.BindableProxy;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;


@Singleton
public class Val {
	
	@Inject
	Validator validator;
	
	@Inject
	ErrorRenderer errorRenderer;
	
	public boolean validate(Object object, Class<?>... groups) {
		BindableProxy<?> proxy = (BindableProxy<?>) object;
		Set<ConstraintViolation<Object>> validate = validator.validate(proxy.unwrap(), groups);
		mapViolations(validate, proxy);
		return validate.size() == 0;
	}

	private void mapViolations(Collection<ConstraintViolation<Object>> cv, BindableProxy<?> proxy) {
		for (String prop : proxy.getState().getBoundProperties()) {
			Widget widget = proxy.getState().getWidget(prop);
			errorRenderer.removeError(widget);
		}
		for (ConstraintViolation<?> constraintViolation : cv) {
			System.out.println(constraintViolation.getPropertyPath());
			Widget widget = proxy.getState().getWidget(constraintViolation.getPropertyPath().toString());
			errorRenderer.showErrror(constraintViolation, widget);
		}
	}
	
}
