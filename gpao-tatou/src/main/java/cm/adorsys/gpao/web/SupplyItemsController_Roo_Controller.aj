// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.web.SupplyItemsController;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect SupplyItemsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SupplyItemsController.create(@Valid SupplyItems supplyItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, supplyItems);
            return "supplyitemses/create";
        }
        uiModel.asMap().clear();
        supplyItems.persist();
        return "redirect:/supplyitemses/" + encodeUrlPathSegment(supplyItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SupplyItemsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SupplyItems());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (Product.countProducts() == 0) {
            dependencies.add(new String[] { "product", "products" });
        }
        if (Supply.countSupplys() == 0) {
            dependencies.add(new String[] { "supply", "supplys" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "supplyitemses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SupplyItemsController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("supplyitems", SupplyItems.findSupplyItems(id));
        uiModel.addAttribute("itemId", id);
        return "supplyitemses/show";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SupplyItemsController.update(@Valid SupplyItems supplyItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, supplyItems);
            return "supplyitemses/update";
        }
        uiModel.asMap().clear();
        supplyItems.merge();
        return "redirect:/supplyitemses/" + encodeUrlPathSegment(supplyItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SupplyItemsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, SupplyItems.findSupplyItems(id));
        return "supplyitemses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SupplyItemsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SupplyItems supplyItems = SupplyItems.findSupplyItems(id);
        supplyItems.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/supplyitemses";
    }
    
    void SupplyItemsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("supplyItems_expirationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void SupplyItemsController.populateEditForm(Model uiModel, SupplyItems supplyItems) {
        uiModel.addAttribute("supplyItems", supplyItems);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("products", Product.findAllProducts());
        uiModel.addAttribute("supplys", Supply.findAllSupplys());
        uiModel.addAttribute("unitofmesureses", UnitOfMesures.findAllUnitOfMesureses());
    }
    
    String SupplyItemsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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