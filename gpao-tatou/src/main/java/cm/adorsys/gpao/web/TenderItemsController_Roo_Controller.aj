// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.TenderItems;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.web.TenderItemsController;
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

privileged aspect TenderItemsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TenderItemsController.create(@Valid TenderItems tenderItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenderItems);
            return "tenderitemses/create";
        }
        uiModel.asMap().clear();
        tenderItems.persist();
        return "redirect:/tenderitemses/" + encodeUrlPathSegment(tenderItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TenderItemsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new TenderItems());
        return "tenderitemses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TenderItemsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("tenderitems", TenderItems.findTenderItems(id));
        uiModel.addAttribute("itemId", id);
        return "tenderitemses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TenderItemsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tenderitemses", TenderItems.findTenderItemsEntries(firstResult, sizeNo));
            float nrOfPages = (float) TenderItems.countTenderItemses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tenderitemses", TenderItems.findAllTenderItemses());
        }
        return "tenderitemses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TenderItemsController.update(@Valid TenderItems tenderItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenderItems);
            return "tenderitemses/update";
        }
        uiModel.asMap().clear();
        tenderItems.merge();
        return "redirect:/tenderitemses/" + encodeUrlPathSegment(tenderItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TenderItemsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, TenderItems.findTenderItems(id));
        return "tenderitemses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TenderItemsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TenderItems tenderItems = TenderItems.findTenderItems(id);
        tenderItems.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tenderitemses";
    }
    
    void TenderItemsController.populateEditForm(Model uiModel, TenderItems tenderItems) {
        uiModel.addAttribute("tenderItems", tenderItems);
        uiModel.addAttribute("products", Product.findAllProducts());
        uiModel.addAttribute("unitofmesureses", UnitOfMesures.findAllUnitOfMesureses());
    }
    
    String TenderItemsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
