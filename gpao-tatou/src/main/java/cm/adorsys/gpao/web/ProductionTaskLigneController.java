package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ProductionTaskLigne;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productiontasklignes")
@Controller
@RooWebScaffold(path = "productiontasklignes", formBackingObject = ProductionTaskLigne.class)
public class ProductionTaskLigneController {
}
