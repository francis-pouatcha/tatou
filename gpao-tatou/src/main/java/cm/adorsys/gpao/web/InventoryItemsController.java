package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.InventoryItems;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/inventoryitemses")
@Controller
@RooWebScaffold(path = "inventoryitemses", formBackingObject = InventoryItems.class)
public class InventoryItemsController {
}
