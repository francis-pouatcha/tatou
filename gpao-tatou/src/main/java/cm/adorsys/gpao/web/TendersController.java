package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Tenders;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tenderses")
@Controller
@RooWebScaffold(path = "tenderses", formBackingObject = Tenders.class)
public class TendersController {
}
