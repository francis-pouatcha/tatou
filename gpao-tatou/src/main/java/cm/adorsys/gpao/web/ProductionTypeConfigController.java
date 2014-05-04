package cm.adorsys.gpao.web;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productiontypeconfigs")
@Controller
@RooWebScaffold(path = "productiontypeconfigs", formBackingObject = ProductionTypeConfig.class)
public class ProductionTypeConfigController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditProductionTypeConfigForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        ProductionTypeConfig productionTypeConfig = null;
        if (id == null) {
            productionTypeConfig = new ProductionTypeConfig();
        } else {
            productionTypeConfig = ProductionTypeConfig.findProductionTypeConfig(id);
        }
        populateEditForm(uiModel, productionTypeConfig);
        return "productiontypeconfigs/productiontypeconfigView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditProductionTypeConfig(@Valid ProductionTypeConfig productionTypeConfig, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productionTypeConfig);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "productiontypeconfigs/productiontypeconfigView";
        }
        if (productionTypeConfig.getId() == null) {
            productionTypeConfig.persist();
        }
        productionTypeConfig = doAConsistantMerge(productionTypeConfig);
        populateEditForm(uiModel, productionTypeConfig);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "productiontypeconfigs/productiontypeconfigView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionTypeConfig> productionTypeConfigs = ProductionTypeConfig.findManufacturingVouchersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (productionTypeConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionTypeConfig.findProductionTypeConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productiontypeconfigs/productiontypeconfigView";
        }
        populateEditForm(uiModel, productionTypeConfigs.iterator().next());
        return "productiontypeconfigs/productiontypeconfigView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionTypeConfig> productionTypeConfigs = ProductionTypeConfig.findManufacturingVouchersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (productionTypeConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionTypeConfig.findProductionTypeConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productiontypeconfigs/productiontypeconfigView";
        }
        populateEditForm(uiModel, productionTypeConfigs.iterator().next());
        return "productiontypeconfigs/productiontypeconfigView";
    }

    private ProductionTypeConfig doAConsistantMerge(ProductionTypeConfig productionTypeConfig) {
        try {
            productionTypeConfig.merge();
        } catch (Exception e) {
            productionTypeConfig.setVersion(CustomerOrder.findCustomerOrder(productionTypeConfig.getId()).getVersion());
            productionTypeConfig = productionTypeConfig.merge();
        }
        return productionTypeConfig;
    }
}
