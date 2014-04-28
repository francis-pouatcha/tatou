package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.Production;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productions")
@Controller
@RooWebScaffold(path = "productions", formBackingObject = Production.class)
public class ProductionController {
}
