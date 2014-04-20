package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/rawmaterialorders")
@Controller
@RooWebScaffold(path = "rawmaterialorders", formBackingObject = RawMaterialOrder.class)
public class RawMaterialOrderController extends AbstractOrderController {

	@Autowired
	IRawMaterialOrderService rawMaterialOrderService;
	@Autowired
	IProductService productService;
    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditManufacturingVoucherForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        RawMaterialOrder rawMaterialOrder = id == null ? new RawMaterialOrder() : RawMaterialOrder.findRawMaterialOrder(id);
        populateEditForm(uiModel, rawMaterialOrder);
        return "rawmaterialorders/rawmaterialordersView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditRawMaterialOrder(@Valid RawMaterialOrder rawMaterialOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rawMaterialOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "rawmaterialdeliverynotes/rawmaterialdeliverynotesView";
        }
        if (rawMaterialOrder.getId() == null) {
        	rawMaterialOrder.setOrderState(DocumentStates.BROUILLON);
        	rawMaterialOrder.setOrigin(DeliveryOrigin.CREATED);
        	rawMaterialOrder.persist();
        }
        rawMaterialOrder = doAConsistantMerge(rawMaterialOrder);
        populateEditForm(uiModel, rawMaterialOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "rawmaterialorders/rawmaterialordersView";
    }


    @RequestMapping(value = "/findProductByNameLike/{name}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findProductByNameLike(@PathVariable("name") String name) {
        List<Product> productList = productService.findProductsByNameLike(name, 100);
        return Product.toJsonArray(productList);
    }

    @RequestMapping(value = "/getSelectedProduct/{productId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSelectedProduct(@PathVariable("productId") Long productId) {
        return Product.findProduct(productId).toJson();
    }

    
    @RequestMapping(value = "/{rawMaterialOrderId}/addRawMaterialOrderItem", method = RequestMethod.GET, params = { "productId" })
    @ResponseBody
    public String addOrderItem(@PathVariable("rawMaterialOrderId") Long rawMaterialOrderId, RawMaterialOrderItem rawMaterialOrderItem, @RequestParam("productId") Long productId, Model uiModel) {
        RawMaterialOrder rawMaterialOrder = RawMaterialOrder.findRawMaterialOrder(rawMaterialOrderId);
        rawMaterialOrderItem.setRawMaterialOrder(rawMaterialOrder);
        rawMaterialOrderItem.setProduct(Product.findProduct(productId));
        rawMaterialOrderService.addRawMaterialOrderItem(rawMaterialOrder, rawMaterialOrderItem);
        return RawMaterialOrderItem.toJsonArray(RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList());
    }

    @RequestMapping(value = "/{rawMaterialOrderId}/removeItem", method = RequestMethod.GET)
    public String removeOrderItems(@PathVariable("rawMaterialOrderId") Long rawMaterialOrderId, @RequestParam("itemid") Long[] orderItemIds, Model uiModel) {
    	RawMaterialOrder rawMaterialOrder = RawMaterialOrder.findRawMaterialOrder(rawMaterialOrderId);
        boolean removeItems = rawMaterialOrderService.removeItems(Arrays.asList(orderItemIds));
        if (!removeItems) {
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Nothing deleted!");
            populateEditForm(uiModel, rawMaterialOrder);
        }
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "lignes supprimee avec success !");
        populateEditForm(uiModel, rawMaterialOrder);
        return "rawmaterialorders/rawmaterialordersView";
    }
    private RawMaterialOrder doAConsistantMerge(
			RawMaterialOrder rawMaterialOrder) {
    	try {
    		rawMaterialOrder.merge();
        } catch (Exception e) {
        	rawMaterialOrder.setVersion(CustomerOrder.findCustomerOrder(rawMaterialOrder.getId()).getVersion());
        	rawMaterialOrder = rawMaterialOrder.merge();
        }
        return rawMaterialOrder;
	}

	void populateEditForm(Model uiModel, RawMaterialOrder rawMaterialOrder) {
        if (rawMaterialOrder != null && rawMaterialOrder.getId() != null) {
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
