package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.OrderItems;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/orderitemses")
@Controller
@RooWebScaffold(path = "orderitemses", formBackingObject = OrderItems.class)
public class OrderItemsController {
}
