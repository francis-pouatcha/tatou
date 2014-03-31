package cm.adorsys.gpao.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.uimodels.PasswordReset;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/passwordresets")
@Controller
public class PasswordResetController {
	@RequestMapping(method = RequestMethod.POST)
	public String update(@Valid PasswordReset passwordReset, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if(!passwordReset.passwordsEqual()){
			ObjectError error = new ObjectError("newPassword","Both password entered are not identical");
			bindingResult.addError(error);
		}
		GpaoUser user = SecurityUtil.getGpaoUser();

		if(!user.checkExistingPasword(passwordReset.getCurrentPassword())){
			ObjectError error = new ObjectError("currentPassword","Current password not matching our record.");
			bindingResult.addError(error);
		}
		if (bindingResult.hasErrors()) {
			passwordReset.setUserName(user.getUserName());// doesn't come back
			uiModel.addAttribute("passwordReset", passwordReset);
			return "passwordresets/passwordresetView";
		}
		user.changePassword(passwordReset.getNewPassword());
		user.merge();
		uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Mot de passe Modifier Avec success  !");
		populateEditForm(uiModel);
		return "passwordresets/passwordresetView";
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String updateForm(Model uiModel) {
		populateEditForm(uiModel) ;
		return "passwordresets/passwordresetView";
	}

	void populateEditForm(Model uiModel) {
		String userName = SecurityUtil.getUserName();
		PasswordReset passwordReset = new PasswordReset();
		passwordReset.setUserName(userName);
		uiModel.addAttribute("passwordReset", passwordReset);
	}
}
