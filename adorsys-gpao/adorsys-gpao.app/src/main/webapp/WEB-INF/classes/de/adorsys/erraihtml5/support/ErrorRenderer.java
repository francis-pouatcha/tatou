package de.adorsys.erraihtml5.support;

import javax.validation.ConstraintViolation;

import com.google.gwt.user.client.ui.Widget;

public interface ErrorRenderer {
	
	public void showErrror(ConstraintViolation<?> constraintViolation, Widget widget);

	public void removeError(Widget widget);

}
