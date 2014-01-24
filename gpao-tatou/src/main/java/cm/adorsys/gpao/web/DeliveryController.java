package cm.adorsys.gpao.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.uimodels.DeliveryFinder;
import cm.adorsys.gpao.services.impl.TatouDeliveryService;
import cm.adorsys.gpao.utils.GpaoPdfProducer;
import cm.adorsys.gpao.utils.GpaoRepportPath;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/deliverys")
@Controller
@RooWebScaffold(path = "deliverys", formBackingObject = Delivery.class)
public class DeliveryController {

	@Autowired
	TatouDeliveryService deliveryService;

	@Autowired
	GpaoPdfProducer pdfProducer;

	@RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
	public String addOrEditdeliverysForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
		Delivery deliverys = id == null ? new Delivery() : Delivery.findDelivery(id);
		populateEditForm(uiModel, deliverys);
		return "deliverys/deliverysView";
	}

	@RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
	public String addOrEditdeliverys(@Valid Delivery delivery, BindingResult bindingResult,RedirectAttributes redirectAttributes, Model uiModel, HttpServletRequest httpServletRequest) {
		delivery.setCompany(Company.findCompany(Long.valueOf(1)));
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, delivery);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
			return "deliverys/deliverysView";
		}
		Delivery merge = delivery.merge();
		uiModel.asMap().clear() ;
		redirectAttributes.addFlashAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !") ;
		return "redirect:/deliverys/addOrEditForm?id=" + encodeUrlPathSegment(merge.getId().toString(), httpServletRequest)+"&form";
	}

	@RequestMapping(value = "/close/{id}", method = RequestMethod.GET, produces = "text/html")
	public String closeDelivery(@PathVariable("id") Long id, Model uiModel ,RedirectAttributes redirectAttributes , HttpServletRequest httpServletRequest ) {
		Delivery findDelivery = Delivery.findDelivery(id);
		Delivery closeDelivery = deliveryService.closeDelivery(findDelivery);
		Delivery produiceDelivery = deliveryService.produiceDeliveryForUnReceiveArticle(closeDelivery);
		if(produiceDelivery.hasDeliveryItem()){
			produiceDelivery.merge();
		}else {
			produiceDelivery.remove();
		}
		uiModel.asMap().clear() ;
		redirectAttributes.addFlashAttribute(MessageType.SUCCESS_MESSAGE, "Livraison Cloturee avec success !") ;
		return "redirect:/deliverys/addOrEditForm?id=" + encodeUrlPathSegment(closeDelivery.getId().toString(), httpServletRequest)+"&form";

	}
	@RequestMapping(value = "/setReceptQte/{ItemId}/{qteRecue}", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String setReceptQte(@PathVariable("ItemId") Long ItemId, @PathVariable("qteRecue") BigDecimal qteRecue ) {
		DeliveryItems deliveryItems = DeliveryItems.findDeliveryItems(ItemId);
		if(deliveryItems.getDelivery().isOpen()){
		deliveryItems.setQteReceive(qteRecue);
		deliveryItems.calculateUnreceiveQte();
		deliveryItems.calculateQteInStock();
		DeliveryItems merge = deliveryItems.merge();
		}
		return  deliveryItems.getDelivery().getVersion().toString();

	}

	@RequestMapping(value = "/acceptAll/{id}", method = RequestMethod.GET, produces = "text/html")
	public String acceptAll(@PathVariable("id") Long id, Model uiModel,RedirectAttributes redirectAttributes , HttpServletRequest httpServletRequest) {
		Delivery findDelivery = Delivery.findDelivery(id);
		Delivery closeDelivery = deliveryService.accepAllDeliveryItems(findDelivery);
		uiModel.asMap().clear() ;
		redirectAttributes.addFlashAttribute(MessageType.SUCCESS_MESSAGE, "Livraison validee avec success !") ;
		return "redirect:/deliverys/addOrEditForm?id=" + encodeUrlPathSegment(closeDelivery.getId().toString(), httpServletRequest)+"&form";
	}

	@RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getNextDelivery(@PathVariable("id") Long id, Model uiModel) {
		List<Delivery> nextDelivery = Delivery.findDeliverysByIdUpperThan(id).setMaxResults(1).getResultList();
		if (nextDelivery.isEmpty()) {
			populateEditForm(uiModel, Delivery.findDelivery(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "deliverys/deliverysView";
		}
		Delivery next = nextDelivery.iterator().next();
		populateEditForm(uiModel, next);
		return "deliverys/deliverysView";
	}

	@RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getPreviousDelivery(@PathVariable("id") Long id, Model uiModel) {
		List<Delivery> nextDelivery = Delivery.findDeliverysByIdLowerThan(id).setMaxResults(1).getResultList();
		if (nextDelivery.isEmpty()) {
			populateEditForm(uiModel, Delivery.findDelivery(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de commande  trouve !");
			return "deliverys/deliverysView";
		}
		Delivery next = nextDelivery.iterator().next();
		populateEditForm(uiModel, next);
		return "deliverys/deliverysView";
	}

	@RequestMapping(value = "/deliveryNote/{reference}.pdf", method = RequestMethod.GET, produces = { "application/pdf" })
	public void etatProduitPerisable(@PathVariable("reference") String reference, HttpServletRequest request, HttpServletResponse response) {
		Map parameters = new HashMap();
		Delivery next = Delivery.findDeliverysByReferenceEquals(reference).getResultList().iterator().next();
		parameters.put("commandeid", next.getId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.DELIVERY_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	@RequestMapping(value="/find", params = {"form" }, method = RequestMethod.GET)
	public String findDeliveryForm(Model uiModel) {
		populateFindForm(uiModel, new DeliveryFinder());
		return "deliverys/findDeliveryView";
	}

	@RequestMapping(value="/find", method = RequestMethod.POST)
	public String findDelivery( DeliveryFinder deliveryFinder, Model uiModel) {
		uiModel.addAttribute("deliverys", deliveryFinder.find());
		populateFindForm(uiModel, deliveryFinder);
		return "deliverys/list";
	}
	@RequestMapping(value = "/printFind.pdf", method = RequestMethod.POST, produces = { "application/pdf" })
	public void prindfindDelivery(DeliveryFinder deliveryFinder, HttpServletResponse response) {
		Map parameters = new HashMap();
		parameters.put("tenderId", deliveryFinder.getDeliveryId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.TENDER_LIST_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	@RequestMapping(value = "/listByDocRef/{docRef}", method = RequestMethod.GET, produces = "text/html")
	public String listByDocRef(@PathVariable("docRef") String docRef, Model uiModel) {
		uiModel.addAttribute("deliverys", Delivery.findDeliverysByDocRef(docRef).getResultList());
		addDateTimeFormatPatterns(uiModel);
		return "deliverys/list";
	}
	void populateFindForm(Model uiModel, DeliveryFinder  deliveryFinder) {
		uiModel.addAttribute("deliveryFinder", deliveryFinder);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("deliveryorigins", Arrays.asList(DeliveryOrigin.values()));
		uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
	}
	void populateEditForm(Model uiModel, Delivery delivery) {
		uiModel.addAttribute("delivery", delivery);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("companys", Company.findAllCompanys());
		uiModel.addAttribute("deliveryitemses", new ArrayList<DeliveryItems>());
		uiModel.addAttribute("deliveryorigins", Arrays.asList(DeliveryOrigin.values()));
		uiModel.addAttribute("devises", Devise.findAllDevises());
		uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
	}
}
