package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.model.uimodels.PurchaseOrderFinder;
import cm.adorsys.gpao.model.uimodels.TenderFinder;
import cm.adorsys.gpao.services.Impl.TatouPurchaseService;
import cm.adorsys.gpao.utils.GpaoPdfProducer;
import cm.adorsys.gpao.utils.GpaoRepportPath;
import cm.adorsys.gpao.utils.MessageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@RequestMapping("/purchaseorders")
@Controller
@RooWebScaffold(path = "purchaseorders", formBackingObject = PurchaseOrder.class)
public class PurchaseOrderController {

    Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    TatouPurchaseService purchaseService;

    @Autowired
    GpaoPdfProducer pdfProducer;

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditPurchaseOrdersForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        PurchaseOrder purchaseOrder = id == null ? new PurchaseOrder() : PurchaseOrder.findPurchaseOrder(id);
        populateEditForm(uiModel, purchaseOrder);
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String addOrEditPurchaseOrders(@Valid PurchaseOrder purchaseOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        purchaseOrder.setCompany(Company.findCompany(Long.valueOf(1)));
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "purchaseorders/purchaseordersView";
        }
        if (purchaseOrder.getId() == null) {
            purchaseOrder.persist();
        }
        purchaseOrder.setOrderItems(OrderItems.findOrderItemssByPurchaseOrder(purchaseOrder).getResultList());
        purchaseService.addOderItemsFromTenders(purchaseOrder);
        purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
        try {
            purchaseOrder = purchaseOrder.merge();
        } catch (Exception e) {
            purchaseOrder.setVersion(PurchaseOrder.findPurchaseOrder(purchaseOrder.getId()).getVersion());
            purchaseOrder = purchaseOrder.merge();
        }
        populateEditForm(uiModel, purchaseOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextPurchaseOrder(@PathVariable("id") Long id, Model uiModel) {
        List<PurchaseOrder> nextPurchaseOrder = PurchaseOrder.findPurchaseOrdersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextPurchaseOrder.isEmpty()) {
            populateEditForm(uiModel, PurchaseOrder.findPurchaseOrder(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
            return "purchaseorders/purchaseordersView";
        }
        PurchaseOrder next = nextPurchaseOrder.iterator().next();
        populateEditForm(uiModel, next);
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousPurchaseOrder(@PathVariable("id") Long id, Model uiModel) {
        List<PurchaseOrder> nextPurchaseOrder = PurchaseOrder.findPurchaseOrdersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextPurchaseOrder.isEmpty()) {
            populateEditForm(uiModel, PurchaseOrder.findPurchaseOrder(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
            return "purchaseorders/purchaseordersView";
        }
        PurchaseOrder next = nextPurchaseOrder.iterator().next();
        populateEditForm(uiModel, next);
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/findProductByNameLike/{name}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findProductByNameLike(@PathVariable("name") String name) {
        List<Product> productList = purchaseService.findProductByNameLike(name, 100);
        return Product.toJsonArray(productList);
    }

    @RequestMapping(value = "/findProductFromPurcharseOrder/{ordderId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findProductFromPurcharseOrder(@PathVariable("ordderId") Long ordderId) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(ordderId);
        List<Product> productList = purchaseService.findProductFormPurchaseOrder(purchaseOrder);
        return Product.toJsonArray(productList);
    }

    @RequestMapping(value = "/getSelectedProduct/{productId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSelectedProduct(@PathVariable("productId") Long productId) {
        OrderItemUimodel selectedProduct = purchaseService.findSelectedProduct(productId);
        return selectedProduct.toJson();
    }

    @RequestMapping(value = "/getUdmListFromUdmGroup/{groupId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUdmListFromUdmGroup(@PathVariable("groupId") Long groupId) {
        List<UnitOfMesures> resultList = UnitOfMesures.findUnitOfMesuressByGroupEquals(UdmGroup.findUdmGroup(groupId)).getResultList();
        return UnitOfMesures.toJsonArray(resultList);
    }

    @RequestMapping(value = "/{purchaseId}/addOrderItem", method = RequestMethod.GET)
    @ResponseBody
    public String addOrderItem(@PathVariable("purchaseId") Long purchaseId, OrderItemUimodel itemUimodel, Model uiModel) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
        purchaseService.addOrderItems(purchaseOrder, itemUimodel);
        purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
        PurchaseOrder merge = purchaseOrder.merge();
        List<OrderItems> orderItems = merge.getOrderItems();
        return OrderItems.toJsonArray(orderItems);
    }

    @RequestMapping(value = "/{purchaseId}/removeOrderItem", method = RequestMethod.GET)
    public String removeOrderItems(@PathVariable("purchaseId") Long purchaseId, @RequestParam("itemid") Long[] orderItemIds, Model uiModel) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
        purchaseService.deleteOrderItems(purchaseOrder, Arrays.asList(orderItemIds));
        purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
        PurchaseOrder merge = purchaseOrder.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "ligne supreimee avec success !");
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/{purchaseId}/caculated", method = RequestMethod.GET)
    public String calculatedTaxAndAmount(@PathVariable("purchaseId") Long purchaseId, Model uiModel) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
        purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
        PurchaseOrder merge = purchaseOrder.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Calcul des taxes effectues avec success !");
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/{purchaseId}/validatedOrder", method = RequestMethod.GET)
    public String validatedOrder(@PathVariable("purchaseId") Long purchaseId, Model uiModel) {
        PurchaseOrder purchaseOrder = PurchaseOrder.findPurchaseOrder(purchaseId);
        if (purchaseOrder.getOrderItems().isEmpty()) {
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "validation Impossible la commande est vide !");
        } else {
            purchaseService.calculatePurchaseTaxAndAmount(purchaseOrder);
            purchaseService.validatedPurchase(purchaseOrder);
            if (purchaseOrder.hasTender()) {
                purchaseService.closeTender(purchaseOrder.getTender());
            }
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "validation effectuee avec success !");
        }
        PurchaseOrder merge = purchaseOrder.merge();
        populateEditForm(uiModel, merge);
        return "purchaseorders/purchaseordersView";
    }

    @RequestMapping(value = "/orderNote/{reference}.pdf", method = RequestMethod.GET, produces = { "application/pdf" })
    public void tenderNote(@PathVariable("reference") String reference, HttpServletRequest request, HttpServletResponse response) {
        Map parameters = new HashMap();
        PurchaseOrder next = PurchaseOrder.findPurchaseOrderByReferenceEquals(reference).getSingleResult();
        parameters.put("orderid", next.getId());
        try {
            pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.PURCHASE_ORDER_JRXML_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
    @RequestMapping(value="/find", params = {"form" }, method = RequestMethod.GET)
   	public String findTenderForm(Model uiModel) {
   		populateFindForm(uiModel, new PurchaseOrderFinder());
   		return "purchaseorders/findPurchaseOrderView";
   	}

   	@RequestMapping(value="/find", method = RequestMethod.POST)
   	public String findTender(@Valid PurchaseOrderFinder purchaseOrderFinder, Model uiModel) {
   		uiModel.addAttribute("purchaseorders", purchaseOrderFinder.find());
   		return "purchaseorders/list";
   	}
   	
   	@RequestMapping(value = "/printFind.pdf", method = RequestMethod.POST, produces = { "application/pdf" })
	public void prindfindPurchaseOrders(PurchaseOrderFinder purchaseOrderFinder, HttpServletResponse response) {
		Map parameters = new HashMap();
		parameters.put("entityId", purchaseOrderFinder.getPurchaseId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.PURCHASE_LIST_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

   	void populateFindForm(Model uiModel, PurchaseOrderFinder purchaseOrderFinder) {
        uiModel.addAttribute("purchaseOrderFinder", purchaseOrderFinder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        List<Partner> resultList = Partner.findAllActiveProviders().getResultList();
        resultList.add(0,new Partner());
        uiModel.addAttribute("partners", resultList);
    }
   	
    void populateEditForm(Model uiModel, PurchaseOrder purchaseOrder) {
        uiModel.addAttribute("purchaseOrder", purchaseOrder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("partners", Partner.findAllActiveProviders().getResultList());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("companys", Company.findAllCompanys());
        List<Tenders> tenderses = new ArrayList<Tenders>();
        if (purchaseOrder.isClosed()) {
            tenderses.add(purchaseOrder.getTender());
        } else {
            tenderses = Tenders.findTenderByStatus(DocumentStates.OUVERT).getResultList();
            tenderses.add(0, new Tenders());
        }
        uiModel.addAttribute("tenderses", tenderses);
    }
}
