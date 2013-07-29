package cm.adorsys.gpao.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.UdmGroup;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/partnergroups")
@Controller
@RooWebScaffold(path = "partnergroups", formBackingObject = PartnerGroup.class)
public class PartnerGroupController {
	
	 @RequestMapping(method = RequestMethod.POST, produces = "text/html")
	    public String create(@Valid PartnerGroup partnerGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, partnerGroup);
	            return "partnergroups/create";
	        }
	        uiModel.asMap().clear();
	        partnerGroup.persist();
	        return "redirect:/partners/addOrEditForm";
	    }

	    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	    public String update(@Valid PartnerGroup partnerGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, partnerGroup);
	            return "partnergroups/update";
	        }
	        uiModel.asMap().clear();
	        partnerGroup.merge();
	        return "redirect:/partners/addOrEditForm";
	    }
}
