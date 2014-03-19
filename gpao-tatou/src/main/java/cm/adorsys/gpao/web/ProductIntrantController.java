package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductIntrant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productintrants")
@Controller
@RooWebScaffold(path = "productintrants", formBackingObject = ProductIntrant.class)
public class ProductIntrantController {
}
