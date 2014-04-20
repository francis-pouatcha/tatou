package cm.adorsys.gpao.web;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItem;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Partner;

@RequestMapping("/deliverys")
@Controller
@RooWebScaffold(path = "deliverys", formBackingObject = Delivery.class)
public class DeliveryController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditDeliveryForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        Delivery delivery;
        if (id == null) {
            delivery = new Delivery();
        } else {
            delivery = Delivery.findDelivery(id);
        }
        populateEditForm(uiModel, delivery);
        return "deliverys/deliverysView";
    }

    void populateEditForm(Model uiModel, Delivery delivery) {
        uiModel.addAttribute("delivery", delivery);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("deliveryorigins", Arrays.asList(DeliveryOrigin.values()));
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("partners", Partner.findAllPartners());
        if (delivery != null && delivery.getId() != null) {
            uiModel.addAttribute("deliveryitems", DeliveryItem.findDeliveryItemsByDelivery(delivery).getResultList());
        }
    }
}
