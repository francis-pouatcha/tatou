package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionTask;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productiontasks")
@Controller
@RooWebScaffold(path = "productiontasks", formBackingObject = ProductionTask.class)
public class ProductionTaskController {
}
