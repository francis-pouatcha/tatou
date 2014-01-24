package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.TenderItems;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.model.uimodels.ProductFinder;
import cm.adorsys.gpao.model.uimodels.TenderFinder;
import cm.adorsys.gpao.services.impl.TatouPurchaseService;
import cm.adorsys.gpao.utils.GpaoPdfProducer;
import cm.adorsys.gpao.utils.GpaoRepportPath;
import cm.adorsys.gpao.utils.MessageType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

@RequestMapping("/tenderses")
@Controller
@RooWebScaffold(path = "tenderses", formBackingObject = Tenders.class)
public class TendersController {

	@Autowired
	TatouPurchaseService purchaseService;

	@Autowired
	GpaoPdfProducer pdfProducer;

	@RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
	public String addOrEditTenderForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
		Tenders tenders = id == null ? new Tenders() : Tenders.findTenders(id);
		populateEditForm(uiModel, tenders);
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
	public String addOrEditTendersOrders(@Valid Tenders tenders, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, tenders);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
			return "tenderses/tenderView";
		}
		if (tenders.getId() == null) {
			tenders.persist();
		}
		tenders.setTenderItems(new HashSet<TenderItems>(TenderItems.findTenderItemssByTenders(tenders).getResultList()));
		try {
			tenders = tenders.merge();
		} catch (Exception e) {
			tenders.setVersion(Tenders.findTenders(tenders.getId()).getVersion());
			tenders = tenders.merge();
		}
		populateEditForm(uiModel, tenders);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getNextTenders(@PathVariable("id") Long id, Model uiModel) {
		List<Tenders> nextTenders = Tenders.findTenderssByIdUpperThan(id).setMaxResults(1).getResultList();
		if (nextTenders.isEmpty()) {
			populateEditForm(uiModel, Tenders.findTenders(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "tenderses/tenderView";
		}
		Tenders next = nextTenders.iterator().next();
		populateEditForm(uiModel, next);
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getPreviousTenders(@PathVariable("id") Long id, Model uiModel) {
		List<Tenders> nextTenders = Tenders.findTenderssByIdLowerThan(id).setMaxResults(1).getResultList();
		if (nextTenders.isEmpty()) {
			populateEditForm(uiModel, Tenders.findTenders(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "tenderses/tenderView";
		}
		Tenders next = nextTenders.iterator().next();
		populateEditForm(uiModel, next);
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/findProductByNameLike/{name}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findProductByNameLike(@PathVariable("name") String name) {
		List<Product> productList = purchaseService.findProductByNameLike(name, 100);
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

	@RequestMapping(value = "/{tenderId}/addTenderItem", method = RequestMethod.GET)
	@ResponseBody
	public String addTenderItem(@PathVariable("tenderId") Long tenderId, OrderItemUimodel itemUimodel, Model uiModel) {
		Tenders tenders = Tenders.findTenders(tenderId);
		purchaseService.addTenderItems(tenders, itemUimodel);
		Tenders merge = tenders.merge();
		Set<TenderItems> tenderItems = merge.getTenderItems();
		return TenderItems.toJsonArray(tenderItems);
	}

	@RequestMapping(value = "/{tenderId}/removeTenderItem", method = RequestMethod.GET)
	public String removeTenderItem(@PathVariable("tenderId") Long tenderId, @RequestParam("itemid") Long[] tenderItemIds, Model uiModel) {
		Tenders tenders = Tenders.findTenders(tenderId);
		purchaseService.deleteTenderItems(tenders, Arrays.asList(tenderItemIds));
		Tenders merge = tenders.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "ligne suprimee avec success !");
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/{tenderId}/close", method = RequestMethod.GET)
	public String closedTender(@PathVariable("tenderId") Long purchaseId, Model uiModel) {
		Tenders tenders = Tenders.findTenders(purchaseId);
		purchaseService.closeTender(tenders);
		Tenders merge = tenders.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "CLOTURE avec success !");
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/{tenderId}/canceled", method = RequestMethod.GET)
	public String canceledTender(@PathVariable("tenderId") Long purchaseId, Model uiModel) {
		Tenders tenders = Tenders.findTenders(purchaseId);
		purchaseService.cancelTender(tenders);
		Tenders merge = tenders.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "ANULLE  avec success !");
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/{tenderId}/restore", method = RequestMethod.GET)
	public String restoreTender(@PathVariable("tenderId") Long purchaseId, Model uiModel) {
		Tenders tenders = Tenders.findTenders(purchaseId);
		purchaseService.restoreTender(tenders);
		Tenders merge = tenders.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Restore avec success !");
		return "tenderses/tenderView";
	}

	@RequestMapping(value = "/tenderNote/{reference}.pdf", method = RequestMethod.GET, produces = { "application/pdf" })
	public void tenderNote(@PathVariable("reference") String reference, HttpServletRequest request, HttpServletResponse response) {
		Map parameters = new HashMap();
		Tenders next = Tenders.findTendersByReferenceEquals(reference).getResultList().iterator().next();
		parameters.put("tenderid", next.getId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.TENDER_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	@RequestMapping(value="/find", params = {"form" }, method = RequestMethod.GET)
	public String findTenderForm(Model uiModel) {
		populateFindForm(uiModel, new TenderFinder());
		return "tenderses/findTenderView";
	}

	@RequestMapping(value="/find", method = RequestMethod.POST)
	public String findTender(@Valid TenderFinder tenderFinder, Model uiModel) {
		uiModel.addAttribute("tenderses", tenderFinder.find());
		populateFindForm(uiModel, tenderFinder);
		return "tenderses/list";
	}
	@RequestMapping(value = "/printFind.pdf", method = RequestMethod.POST, produces = { "application/pdf" })
	public void prindfindTender(TenderFinder tenderFinder, HttpServletResponse response) {
		Map parameters = new HashMap();
		parameters.put("tenderId", tenderFinder.getTenderId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.TENDER_LIST_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	void populateFindForm(Model uiModel, TenderFinder  tenderFinder) {
		uiModel.addAttribute("tenderFinder", tenderFinder);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
	}

	void populateEditForm(Model uiModel, Tenders tenders) {
		uiModel.addAttribute("tenders", tenders);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("companys", Company.findAllCompanys());
		uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
	}
}
