package cm.adorsys.gpao.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.services.TatouPurchaseService;
import cm.adorsys.gpao.utils.MessageType;

@Transactional
@RequestMapping("/purchaseorders")
@Controller
@RooWebScaffold(path = "purchaseorders", formBackingObject = PurchaseOrder.class)
public class PurchaseOrderController {

	@Autowired
	TatouPurchaseService purchaseService;

	@RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
	public String addOrEditPurchaseOrdersForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
		PurchaseOrder purchaseOrder = id == null ? new PurchaseOrder() : PurchaseOrder.findPurchaseOrder(id);
		populateEditForm(uiModel, purchaseOrder);
		return "products/purchaseordersView";
	}

	@RequestMapping(value = "/addOrEdit", method = RequestMethod.PUT, produces = "text/html")
	public String addOrEditPurchaseOrders(@Valid PurchaseOrder purchaseOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, purchaseOrder);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
			return "products/purchaseordersView";
		}
		PurchaseOrder merge = purchaseOrder.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
		return "products/purchaseordersView";
	}

	@RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getNextPurchaseOrder(@PathVariable("id") Long id, Model uiModel) {
		List<PurchaseOrder> nextPurchaseOrder = PurchaseOrder.findPurchaseOrdersByIdUpperThan(id).setMaxResults(1).getResultList();
		if (nextPurchaseOrder.isEmpty()) {
			populateEditForm(uiModel, PurchaseOrder.findPurchaseOrder(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "products/purchaseordersView";
		}
		PurchaseOrder next = nextPurchaseOrder.iterator().next();
		populateEditForm(uiModel, next);
		return "products/purchaseordersView";
	}

	@RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getPreviousPurchaseOrder(@PathVariable("id") Long id, Model uiModel) {
		List<PurchaseOrder> nextPurchaseOrder = PurchaseOrder.findPurchaseOrdersByIdLowerThan(id).setMaxResults(1).getResultList();
		if (nextPurchaseOrder.isEmpty()) {
			populateEditForm(uiModel, PurchaseOrder.findPurchaseOrder(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "products/purchaseordersView";
		}
		PurchaseOrder next = nextPurchaseOrder.iterator().next();
		populateEditForm(uiModel, next);
		return "products/purchaseordersView";
	}

	@RequestMapping(value = "/findProductByNameLike/{name}" , method = RequestMethod.GET,produces="application/json; charset=utf-8")
	@ResponseBody
	public String findProductByNameLike(@PathVariable("name") String name) {
		List<Product> productList = purchaseService.findProductByNameLike(name,100);
		return Product.toJsonArray(productList) ;
	}

	@RequestMapping(value = "/getSelectedProduct/{productId}" , method = RequestMethod.GET,produces="application/json; charset=utf-8")
	@ResponseBody
	public String getSelectedProduct(@PathVariable("productId") Long productId) {
		OrderItemUimodel selectedProduct = purchaseService.findSelectedProduct(productId);
		return selectedProduct.toJson() ;
	}


	@RequestMapping(value = "/{purchaseId}/addOrderItem" , method = RequestMethod.GET)
	public String addOrderItem(@PathVariable("purchaseId") Long purchaseId ,OrderItemUimodel itemUimodel,Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
		purchaseService.addOrderItems(purchaseOrder, itemUimodel);
		purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
		PurchaseOrder merge = purchaseOrder.merge();
		populateEditForm(uiModel, merge);
		return "products/purchaseordersView";
	}
	@RequestMapping(value = "/{purchaseId}/removeOrderItem" , method = RequestMethod.GET)
	public String removeOrderItems(@PathVariable("purchaseId") Long purchaseId ,List<Long> orderItemIds,Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
		purchaseService.deleteOrderItems(purchaseOrder, orderItemIds);
		purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
		PurchaseOrder merge = purchaseOrder.merge();
		populateEditForm(uiModel, merge);
		return "products/purchaseordersView";
	}
	
	@RequestMapping(value = "/{purchaseId}/caculated" , method = RequestMethod.GET)
	public String calculatedTaxAndAmount(@PathVariable("purchaseId") Long purchaseId ,Model uiModel) {
		PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
		purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
		PurchaseOrder merge = purchaseOrder.merge();
		populateEditForm(uiModel, merge);
		return "products/purchaseordersView";
	}

	void populateEditForm(Model uiModel, PurchaseOrder purchaseOrder) {
		uiModel.addAttribute("purchaseOrder", purchaseOrder);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
		uiModel.addAttribute("partners", Partner.findAllPartners());
		uiModel.addAttribute("devises", Devise.findAllDevises());
	}
}
