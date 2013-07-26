package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductSubFamily;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productsubfamilys")
@Controller
@RooWebScaffold(path = "productsubfamilys", formBackingObject = ProductSubFamily.class)
public class ProductSubFamilyController {
}
