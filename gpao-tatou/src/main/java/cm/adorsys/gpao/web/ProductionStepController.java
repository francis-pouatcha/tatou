package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionStep;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productionsteps")
@Controller
@RooWebScaffold(path = "productionsteps", formBackingObject = ProductionStep.class)
public class ProductionStepController {
}
