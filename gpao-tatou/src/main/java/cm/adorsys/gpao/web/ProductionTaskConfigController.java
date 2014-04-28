package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionTaskConfig;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productiontaskconfigs")
@Controller
@RooWebScaffold(path = "productiontaskconfigs", formBackingObject = ProductionTaskConfig.class)
public class ProductionTaskConfigController {
}
