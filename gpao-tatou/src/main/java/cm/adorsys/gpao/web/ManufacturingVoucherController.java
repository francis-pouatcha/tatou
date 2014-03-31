package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manufacturingvouchers")
@Controller
@RooWebScaffold(path = "manufacturingvouchers", formBackingObject = ManufacturingVoucher.class)
public class ManufacturingVoucherController {
}
