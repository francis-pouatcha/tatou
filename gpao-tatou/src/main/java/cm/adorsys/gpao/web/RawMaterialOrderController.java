package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/rawmaterialorders")
@Controller
@RooWebScaffold(path = "rawmaterialorders", formBackingObject = RawMaterialOrder.class)
public class RawMaterialOrderController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditManufacturingVoucherForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        RawMaterialOrder rawMaterialOrder = id == null ? new RawMaterialOrder() : RawMaterialOrder.findRawMaterialOrder(id);
        populateEditForm(uiModel, rawMaterialOrder);
        return "rawmaterialorders/rawmaterialordersView";
    }

    void populateEditForm(Model uiModel, RawMaterialOrder rawMaterialOrder) {
    	if(rawMaterialOrder != null && rawMaterialOrder.getId() != null) {
    		List<RawMaterialOrderItem> rawMaterialOrderItems = RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
    		uiModel.addAttribute("orderItems", rawMaterialOrderItems);
    	}
        uiModel.addAttribute("rawMaterialOrder", rawMaterialOrder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
    }


    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<RawMaterialOrder> rawMaterialOrders = RawMaterialOrder.findRawMaterialOrdersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (rawMaterialOrders.isEmpty()) {
        	populateEditForm(uiModel, RawMaterialOrder.findRawMaterialOrder(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande interne de matiere premiere trouve !");
            return "rawmaterialorders/rawmaterialordersView";
        }
        populateEditForm(uiModel, rawMaterialOrders.iterator().next());
        return "rawmaterialorders/rawmaterialordersView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
    	 List<RawMaterialOrder> rawMaterialOrders = RawMaterialOrder.findRawMaterialOrdersByIdLowerThan(id).setMaxResults(1).getResultList();
         if (rawMaterialOrders.isEmpty()) {
         	populateEditForm(uiModel, RawMaterialOrder.findRawMaterialOrder(id));
             uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande interne de matiere premiere trouve !");
             return "rawmaterialorders/rawmaterialordersView";
         }
         populateEditForm(uiModel, rawMaterialOrders.iterator().next());
         return "rawmaterialorders/rawmaterialordersView";
    }
}