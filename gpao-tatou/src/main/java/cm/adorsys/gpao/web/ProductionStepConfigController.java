package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionStepConfig;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productionstepconfigs")
@Controller
@RooWebScaffold(path = "productionstepconfigs", formBackingObject = ProductionStepConfig.class)
public class ProductionStepConfigController {
}
