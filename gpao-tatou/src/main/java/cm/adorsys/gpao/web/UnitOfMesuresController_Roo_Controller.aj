// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.web.UnitOfMesuresController;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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

privileged aspect UnitOfMesuresController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UnitOfMesuresController.create(@Valid UnitOfMesures unitOfMesures, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, unitOfMesures);
            return "unitofmesureses/create";
        }
        uiModel.asMap().clear();
        unitOfMesures.persist();
        return "redirect:/unitofmesureses/" + encodeUrlPathSegment(unitOfMesures.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UnitOfMesuresController.createForm(Model uiModel) {
        populateEditForm(uiModel, new UnitOfMesures());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (UdmGroup.countUdmGroups() == 0) {
            dependencies.add(new String[] { "udmgroup", "udmgroups" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "unitofmesureses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UnitOfMesuresController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("unitofmesures", UnitOfMesures.findUnitOfMesures(id));
        uiModel.addAttribute("itemId", id);
        return "unitofmesureses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UnitOfMesuresController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("unitofmesureses", UnitOfMesures.findUnitOfMesuresEntries(firstResult, sizeNo));
            float nrOfPages = (float) UnitOfMesures.countUnitOfMesureses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("unitofmesureses", UnitOfMesures.findAllUnitOfMesureses());
        }
        return "unitofmesureses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UnitOfMesuresController.update(@Valid UnitOfMesures unitOfMesures, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, unitOfMesures);
            return "unitofmesureses/update";
        }
        uiModel.asMap().clear();
        unitOfMesures.merge();
        return "redirect:/unitofmesureses/" + encodeUrlPathSegment(unitOfMesures.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UnitOfMesuresController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, UnitOfMesures.findUnitOfMesures(id));
        return "unitofmesureses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UnitOfMesuresController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        UnitOfMesures unitOfMesures = UnitOfMesures.findUnitOfMesures(id);
        unitOfMesures.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/unitofmesureses";
    }
    
    void UnitOfMesuresController.populateEditForm(Model uiModel, UnitOfMesures unitOfMesures) {
        uiModel.addAttribute("unitOfMesures", unitOfMesures);
        uiModel.addAttribute("udmgroups", UdmGroup.findAllUdmGroups());
    }
    
    String UnitOfMesuresController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
