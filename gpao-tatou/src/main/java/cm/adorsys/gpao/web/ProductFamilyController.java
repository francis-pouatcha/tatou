package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductFamily;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productfamilys")
@Controller
@RooWebScaffold(path = "productfamilys", formBackingObject = ProductFamily.class)
public class ProductFamilyController {
}
