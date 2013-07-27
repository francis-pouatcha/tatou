package cm.adorsys.gpao.utils;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface BussinessValidation {
	
	public void validate(BindingResult bindingResult ,Model uiModel);

}
