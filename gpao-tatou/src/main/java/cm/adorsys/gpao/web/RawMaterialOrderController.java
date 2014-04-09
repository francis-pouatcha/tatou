package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.RawMaterialOrder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rawmaterialorders")
@Controller
@RooWebScaffold(path = "rawmaterialorders", formBackingObject = RawMaterialOrder.class)
public class RawMaterialOrderController {
}
