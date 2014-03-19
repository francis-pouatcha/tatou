package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.Caracteristic;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/caracteristics")
@Controller
@RooWebScaffold(path = "caracteristics", formBackingObject = Caracteristic.class)
public class CaracteristicController {
}
