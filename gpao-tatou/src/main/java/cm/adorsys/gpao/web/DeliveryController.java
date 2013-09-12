package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.services.TatouPurchaseService;
import cm.adorsys.gpao.utils.MessageType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

@RequestMapping("/deliverys")
@Controller
@RooWebScaffold(path = "deliverys", formBackingObject = Delivery.class)
public class DeliveryController {

    @Autowired
    TatouPurchaseService purchaseService;

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditdeliverysForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Delivery deliverys = id == null ? new Delivery() : Delivery.findDelivery(id);
        populateEditForm(uiModel, deliverys);
        return "deliverys/deliverysView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.PUT, produces = "text/html")
    public String addOrEditdeliverys(@Valid Delivery delivery, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        delivery.setCompany(Company.findCompany(Long.valueOf(1)));
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, delivery);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "deliverys/deliverysView";
        }
        Delivery merge = delivery.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "deliverys/deliverysView";
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
