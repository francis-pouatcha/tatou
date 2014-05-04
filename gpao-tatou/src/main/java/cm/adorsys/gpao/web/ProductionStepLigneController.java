package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionStepLigne;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productionsteplignes")
@Controller
@RooWebScaffold(path = "productionsteplignes", formBackingObject = ProductionStepLigne.class)
public class ProductionStepLigneController {
}
