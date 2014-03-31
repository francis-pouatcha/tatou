package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manufacturingvoucheritems")
@Controller
@RooWebScaffold(path = "manufacturingvoucheritems", formBackingObject = ManufacturingVoucherItem.class)
public class ManufacturingVoucherItemController {
}
