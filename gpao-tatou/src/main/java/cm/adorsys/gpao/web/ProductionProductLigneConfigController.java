package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionProductLigneConfig;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productionproductligneconfigs")
@Controller
@RooWebScaffold(path = "productionproductligneconfigs", formBackingObject = ProductionProductLigneConfig.class)
public class ProductionProductLigneConfigController {
}
