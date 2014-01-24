// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.web.PartnerGroupController;
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

privileged aspect PartnerGroupController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PartnerGroupController.create(@Valid PartnerGroup partnerGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partnerGroup);
            return "partnergroups/create";
        }
        uiModel.asMap().clear();
        partnerGroup.persist();
        return "redirect:/partnergroups/" + encodeUrlPathSegment(partnerGroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PartnerGroupController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PartnerGroup());
        return "partnergroups/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PartnerGroupController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("partnergroup", PartnerGroup.findPartnerGroup(id));
        uiModel.addAttribute("itemId", id);
        return "partnergroups/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PartnerGroupController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partnergroups", PartnerGroup.findPartnerGroupEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) PartnerGroup.countPartnerGroups() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partnergroups", PartnerGroup.findAllPartnerGroups(sortFieldName, sortOrder));
        }
        return "partnergroups/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PartnerGroupController.update(@Valid PartnerGroup partnerGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partnerGroup);
            return "partnergroups/update";
        }
        uiModel.asMap().clear();
        partnerGroup.merge();
        return "redirect:/partnergroups/" + encodeUrlPathSegment(partnerGroup.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PartnerGroupController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, PartnerGroup.findPartnerGroup(id));
        return "partnergroups/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PartnerGroupController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PartnerGroup partnerGroup = PartnerGroup.findPartnerGroup(id);
        partnerGroup.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/partnergroups";
    }
    
    void PartnerGroupController.populateEditForm(Model uiModel, PartnerGroup partnerGroup) {
        uiModel.addAttribute("partnerGroup", partnerGroup);
    }
    
    String PartnerGroupController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
