package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rawmaterialdeliverynoteitems")
@Controller
@RooWebScaffold(path = "rawmaterialdeliverynoteitems", formBackingObject = RawMaterialDeliveryNoteItem.class)
public class RawMaterialDeliveryNoteItemController {
}
