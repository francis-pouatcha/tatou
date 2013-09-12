package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.DeliveryItems;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/deliveryitemses")
@Controller
@RooWebScaffold(path = "deliveryitemses", formBackingObject = DeliveryItems.class)
public class DeliveryItemsController {
}
