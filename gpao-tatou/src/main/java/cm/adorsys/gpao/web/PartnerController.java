package cm.adorsys.gpao.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.PartnerType;
import cm.adorsys.gpao.utils.MessageType;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/partners")
@Controller
@RooWebScaffold(path = "partners", formBackingObject = Partner.class)
public class PartnerController {
	
	@RequestMapping(value = "/addOrEditForm",method = RequestMethod.GET, produces = "text/html")
    public String addOrEditPartnersForm(@RequestParam(value = "id", required = false) Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        Partner partner = id==null?new Partner():Partner.findPartner(id);
        populateEditForm(uiModel, partner);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, httpServletRequest.getAttribute(MessageType.SUCCESS_MESSAGE));
        return "partners/partnerView";
    }
	
	@RequestMapping(value = "/addOrEdit",method = RequestMethod.PUT, produces = "text/html")
    public String addOrEditPartners(@Valid Partner partner, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partner);
            return "partners/update";
        }
        uiModel.asMap().clear();
        partner.merge();
        return "redirect:/partners/addOrEditForm";
    }
	
	
	
	
	void populateEditForm(Model uiModel, Partner partner) {
        uiModel.addAttribute("partner", partner);
       // uiModel.addAttribute("contactses", Contacts.findAllContactses());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("partnergroups", PartnerGroup.findAllPartnerGroups());
        uiModel.addAttribute("partnertypes", Arrays.asList(PartnerType.values()));
    }
}
