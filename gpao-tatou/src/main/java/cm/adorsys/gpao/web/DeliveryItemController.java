package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.DeliveryItem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/deliveryitems")
@Controller
@RooWebScaffold(path = "deliveryitems", formBackingObject = DeliveryItem.class)
public class DeliveryItemController {
}
