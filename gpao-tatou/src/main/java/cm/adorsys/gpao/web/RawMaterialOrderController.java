package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.Taxe;

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
    	if(rawMaterialOrder != null) {
    		List<RawMaterialOrderItem> rawMaterialOrderItems = RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
    		uiModel.addAttribute("orderItems", rawMaterialOrderItems);
    	}
        uiModel.addAttribute("rawMaterialOrder", rawMaterialOrder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
    }
    
}