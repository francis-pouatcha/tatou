// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.web.InventoryItemsController;
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

privileged aspect InventoryItemsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String InventoryItemsController.create(@Valid InventoryItems inventoryItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, inventoryItems);
            return "inventoryitemses/create";
        }
        uiModel.asMap().clear();
        inventoryItems.persist();
        return "redirect:/inventoryitemses/" + encodeUrlPathSegment(inventoryItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String InventoryItemsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new InventoryItems());
        List<String[]> dependencies = new ArrayList<String[]>();
        if (UnitOfMesures.countUnitOfMesureses() == 0) {
            dependencies.add(new String[] { "unitofmesures", "unitofmesureses" });
        }
        uiModel.addAttribute("dependencies", dependencies);
        return "inventoryitemses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String InventoryItemsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("inventoryitems", InventoryItems.findInventoryItems(id));
        uiModel.addAttribute("itemId", id);
        return "inventoryitemses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String InventoryItemsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("inventoryitemses", InventoryItems.findInventoryItemsEntries(firstResult, sizeNo));
            float nrOfPages = (float) InventoryItems.countInventoryItemses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("inventoryitemses", InventoryItems.findAllInventoryItemses());
        }
        return "inventoryitemses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String InventoryItemsController.update(@Valid InventoryItems inventoryItems, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, inventoryItems);
            return "inventoryitemses/update";
        }
        uiModel.asMap().clear();
        inventoryItems.merge();
        return "redirect:/inventoryitemses/" + encodeUrlPathSegment(inventoryItems.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String InventoryItemsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, InventoryItems.findInventoryItems(id));
        return "inventoryitemses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String InventoryItemsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        InventoryItems inventoryItems = InventoryItems.findInventoryItems(id);
        inventoryItems.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/inventoryitemses";
    }
    
    void InventoryItemsController.populateEditForm(Model uiModel, InventoryItems inventoryItems) {
        uiModel.addAttribute("inventoryItems", inventoryItems);
        uiModel.addAttribute("inventorys", Inventory.findAllInventorys());
        uiModel.addAttribute("products", Product.findAllProducts());
        uiModel.addAttribute("unitofmesureses", UnitOfMesures.findAllUnitOfMesureses());
    }
    
    String InventoryItemsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
