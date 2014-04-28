package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productiontypeconfigs")
@Controller
@RooWebScaffold(path = "productiontypeconfigs", formBackingObject = ProductionTypeConfig.class)
public class ProductionTypeConfigController {
}
