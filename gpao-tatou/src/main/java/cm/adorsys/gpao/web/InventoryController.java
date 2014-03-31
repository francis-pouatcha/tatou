package cm.adorsys.gpao.web;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.utils.GpaoPdfProducer;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/inventorys")
@Controller
@RooWebScaffold(path = "inventorys", formBackingObject = Inventory.class)
public class InventoryController {

    @Autowired
    GpaoPdfProducer pdfProducer;

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditInventoryForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Inventory inventory = id == null ? new Inventory() : Inventory.findInventory(id);
        populateEditForm(uiModel, inventory);
        return "inventorys/inventoryView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String addOrEditInventory(@Valid Inventory inventory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        inventory.setCompany(Company.findCompany(Long.valueOf(1)));
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, inventory);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "inventorys/inventoryView";
        }
        Inventory merge = inventory.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "inventorys/inventoryView";
    }

    void populateEditForm(Model uiModel, Inventory inventory) {
        uiModel.addAttribute("inventory", inventory);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("inventoryitemses", new ArrayList<InventoryItems>());
    }
}
