package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionProductLigne;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productionproductlignes")
@Controller
@RooWebScaffold(path = "productionproductlignes", formBackingObject = ProductionProductLigne.class)
public class ProductionProductLigneController {
}
