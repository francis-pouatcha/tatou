package cm.adorsys.gpao.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.RoleNames;
import cm.adorsys.gpao.security.SecurityUtil;

@Controller
@RequestMapping(value={"/","/index"})
public class HomeController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String gotoHomePage(HttpSession session,  Model uiModel){
		GpaoUser gpaoUser = SecurityUtil.getGpaoUser();
		if(session != null) session.setAttribute("currentUser", gpaoUser);
		if(gpaoUser.hasRole(RoleNames.ROLE__MODULE_ADMINISTRATION))  return "adminHome";
		if(gpaoUser.hasRole(RoleNames.ROLE_MODULE_ACHAT))  return "purchaseHome";
		if(gpaoUser.hasRole(RoleNames.ROLE_MODULE_STOCK))  return "stockHome";
		if(gpaoUser.hasRole(RoleNames.ROLE_MODULE_PRODUCTION))  return "productionHome";
		return "login" ;
		
	}

}
