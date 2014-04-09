// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.web.RawMaterialOrderController;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

privileged aspect RawMaterialOrderController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String RawMaterialOrderController.create(@Valid RawMaterialOrder rawMaterialOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rawMaterialOrder);
            return "rawmaterialorders/create";
        }
        uiModel.asMap().clear();
        rawMaterialOrder.persist();
        return "redirect:/rawmaterialorders/" + encodeUrlPathSegment(rawMaterialOrder.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String RawMaterialOrderController.createForm(Model uiModel) {
        populateEditForm(uiModel, new RawMaterialOrder());
        return "rawmaterialorders/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String RawMaterialOrderController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("rawmaterialorder", RawMaterialOrder.findRawMaterialOrder(id));
        uiModel.addAttribute("itemId", id);
        return "rawmaterialorders/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String RawMaterialOrderController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("rawmaterialorders", RawMaterialOrder.findRawMaterialOrderEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) RawMaterialOrder.countRawMaterialOrders() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("rawmaterialorders", RawMaterialOrder.findAllRawMaterialOrders(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "rawmaterialorders/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String RawMaterialOrderController.update(@Valid RawMaterialOrder rawMaterialOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rawMaterialOrder);
            return "rawmaterialorders/update";
        }
        uiModel.asMap().clear();
        rawMaterialOrder.merge();
        return "redirect:/rawmaterialorders/" + encodeUrlPathSegment(rawMaterialOrder.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String RawMaterialOrderController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, RawMaterialOrder.findRawMaterialOrder(id));
        return "rawmaterialorders/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String RawMaterialOrderController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        RawMaterialOrder rawMaterialOrder = RawMaterialOrder.findRawMaterialOrder(id);
        rawMaterialOrder.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/rawmaterialorders";
    }
    
    void RawMaterialOrderController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("rawMaterialOrder_orderdate_date_format", "dd-MM-yyyy HH:mm");
    }
    
    void RawMaterialOrderController.populateEditForm(Model uiModel, RawMaterialOrder rawMaterialOrder) {
        uiModel.addAttribute("rawMaterialOrder", rawMaterialOrder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
    }
    
    String RawMaterialOrderController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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