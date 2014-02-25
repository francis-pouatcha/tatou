package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.TenderItems;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tenderitemses")
@Controller
@RooWebScaffold(path = "tenderitemses", formBackingObject = TenderItems.class)
public class TenderItemsController {
}
