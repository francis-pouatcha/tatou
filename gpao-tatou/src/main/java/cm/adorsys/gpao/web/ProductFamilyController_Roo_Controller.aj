// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.web.ProductFamilyController;
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

privileged aspect ProductFamilyController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProductFamilyController.create(@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productFamily);
            return "productfamilys/create";
        }
        uiModel.asMap().clear();
        productFamily.persist();
        return "redirect:/productfamilys/" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProductFamilyController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ProductFamily());
        return "productfamilys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProductFamilyController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("productfamily", ProductFamily.findProductFamily(id));
        uiModel.addAttribute("itemId", id);
        return "productfamilys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProductFamilyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("productfamilys", ProductFamily.findProductFamilyEntries(firstResult, sizeNo));
            float nrOfPages = (float) ProductFamily.countProductFamilys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("productfamilys", ProductFamily.findAllProductFamilys());
        }
        return "productfamilys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProductFamilyController.update(@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productFamily);
            return "productfamilys/update";
        }
        uiModel.asMap().clear();
        productFamily.merge();
        return "redirect:/productfamilys/" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProductFamilyController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ProductFamily.findProductFamily(id));
        return "productfamilys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProductFamilyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ProductFamily productFamily = ProductFamily.findProductFamily(id);
        productFamily.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/productfamilys";
    }
    
    void ProductFamilyController.populateEditForm(Model uiModel, ProductFamily productFamily) {
        uiModel.addAttribute("productFamily", productFamily);
        uiModel.addAttribute("productsubfamilys", ProductSubFamily.findAllProductSubFamilys());
    }
    
    String ProductFamilyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
