package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.WareHouses;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/warehouseses")
@Controller
@RooWebScaffold(path = "warehouseses", formBackingObject = WareHouses.class)
public class WareHousesController {
}
