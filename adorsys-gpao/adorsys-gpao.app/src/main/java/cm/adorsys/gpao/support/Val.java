package cm.adorsys.gpao.support;

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
	public Set<ConstraintViolation<Object>> getValidationContraint(Object object, Class<?>... groups) {
		BindableProxy<?> proxy = (BindableProxy<?>) object;
		Set<ConstraintViolation<Object>> validate = validator.validate(proxy.unwrap(), groups);
		return validate ;
	}

	private void mapViolations(Collection<ConstraintViolation<Object>> cv, BindableProxy<?> proxy) {
		for (String prop : proxy.getProxyAgent().getBoundProperties()) {
			Widget widget = proxy.getProxyAgent().getWidget(prop);
			errorRenderer.removeError(widget);
		}
		for (ConstraintViolation<?> constraintViolation : cv) {
			Widget widget = proxy.getProxyAgent().getWidget(constraintViolation.getPropertyPath().toString());
			errorRenderer.showErrror(constraintViolation, widget);
		}
	}
	
}
