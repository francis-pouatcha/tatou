package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rawmaterialorderitems")
@Controller
@RooWebScaffold(path = "rawmaterialorderitems", formBackingObject = RawMaterialOrderItem.class)
public class RawMaterialOrderItemController {
}
