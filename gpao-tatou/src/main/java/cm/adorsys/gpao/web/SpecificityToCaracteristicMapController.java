package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/specificitytocaracteristicmaps")
@Controller
@RooWebScaffold(path = "specificitytocaracteristicmaps", formBackingObject = SpecificityToCaracteristicMap.class)
public class SpecificityToCaracteristicMapController {
}
