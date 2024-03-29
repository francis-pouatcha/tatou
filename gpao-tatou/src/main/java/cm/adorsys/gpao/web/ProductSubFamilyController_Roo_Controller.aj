// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.web.ProductSubFamilyController;
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

privileged aspect ProductSubFamilyController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProductSubFamilyController.create(@Valid ProductSubFamily productSubFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productSubFamily);
            return "productsubfamilys/create";
        }
        uiModel.asMap().clear();
        productSubFamily.persist();
        return "redirect:/productsubfamilys/" + encodeUrlPathSegment(productSubFamily.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProductSubFamilyController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ProductSubFamily());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (ProductFamily.countProductFamilys() == 0) {
            dependencies.add(new String[] { "productfamily", "productfamilys" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "productsubfamilys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProductSubFamilyController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("productsubfamily", ProductSubFamily.findProductSubFamily(id));
        uiModel.addAttribute("itemId", id);
        return "productsubfamilys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProductSubFamilyController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("productsubfamilys", ProductSubFamily.findProductSubFamilyEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) ProductSubFamily.countProductSubFamilys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("productsubfamilys", ProductSubFamily.findAllProductSubFamilys(sortFieldName, sortOrder));
        }
        return "productsubfamilys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProductSubFamilyController.update(@Valid ProductSubFamily productSubFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productSubFamily);
            return "productsubfamilys/update";
        }
        uiModel.asMap().clear();
        productSubFamily.merge();
        return "redirect:/productsubfamilys/" + encodeUrlPathSegment(productSubFamily.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProductSubFamilyController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ProductSubFamily.findProductSubFamily(id));
        return "productsubfamilys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProductSubFamilyController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ProductSubFamily productSubFamily = ProductSubFamily.findProductSubFamily(id);
        productSubFamily.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/productsubfamilys";
    }
    
    void ProductSubFamilyController.populateEditForm(Model uiModel, ProductSubFamily productSubFamily) {
        uiModel.addAttribute("productSubFamily", productSubFamily);
        uiModel.addAttribute("productfamilys", ProductFamily.findAllProductFamilys());
    }
    
    String ProductSubFamilyController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
