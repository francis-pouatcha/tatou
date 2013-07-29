// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.web.PartnerController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PartnerController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PartnerController.create(@Valid Partner partner, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partner);
            return "partners/create";
        }
        uiModel.asMap().clear();
        partner.persist();
        return "redirect:/partners/" + encodeUrlPathSegment(partner.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PartnerController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Partner());
        return "partners/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PartnerController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("partner", Partner.findPartner(id));
        uiModel.addAttribute("itemId", id);
        return "partners/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PartnerController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partners", Partner.findPartnerEntries(firstResult, sizeNo));
            float nrOfPages = (float) Partner.countPartners() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partners", Partner.findAllPartners());
        }
        return "partners/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PartnerController.update(@Valid Partner partner, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partner);
            return "partners/update";
        }
        uiModel.asMap().clear();
        partner.merge();
        return "redirect:/partners/" + encodeUrlPathSegment(partner.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PartnerController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Partner.findPartner(id));
        return "partners/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PartnerController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Partner partner = Partner.findPartner(id);
        partner.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/partners";
    }
    
    String PartnerController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
