package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.GpaoBaseEntity;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/deliveryitemses")
@Controller
@RooWebScaffold(path = "deliveryitemses", formBackingObject = SupplyItems.class)
@RooWebFinder
public class DeliveryItemsController {

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("deliveryitemses", GpaoBaseEntity.findEntries(firstResult, sizeNo, SupplyItems.class));
            float nrOfPages = (float) SupplyItems.countSupplyItemses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("deliveryitemses", GpaoBaseEntity.findAll(SupplyItems.class));
        }
        addDateTimeFormatPatterns(uiModel);
        return "deliveryitemses/list";
    }
}
