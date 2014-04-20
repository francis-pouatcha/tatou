package cm.adorsys.gpao.web;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cm.adorsys.gpao.model.Caracteristic;

@RequestMapping("/caracteristics")
@Controller
@RooWebScaffold(path = "caracteristics", formBackingObject = Caracteristic.class)
public class CaracteristicController {
}
