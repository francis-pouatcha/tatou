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
import cm.adorsys.gpao.model.ProductionStepConfig;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productionstepconfigs")
@Controller
@RooWebScaffold(path = "productionstepconfigs", formBackingObject = ProductionStepConfig.class)
public class ProductionStepConfigController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditProductionStepConfigConfigForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        ProductionStepConfig productionStepConfig = null;
        if (id == null) {
            productionStepConfig = new ProductionStepConfig();
        } else {
            productionStepConfig = ProductionStepConfig.findProductionStepConfig(id);
        }
        populateEditForm(uiModel, productionStepConfig);
        return "productionstepconfigs/productionstepconfigView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditProductionStepConfig(@Valid ProductionStepConfig productionStepConfig, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productionStepConfig);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "productionstepconfigs/productionstepconfigView";
        }
        if (productionStepConfig.getId() == null) {
            productionStepConfig.persist();
        }
        productionStepConfig = doAConsistantMerge(productionStepConfig);
        populateEditForm(uiModel, productionStepConfig);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "productionstepconfigs/productionstepconfigView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionStepConfig> productionStepConfigs = ProductionStepConfig.findProductionStepConfigByIdUpperThan(id).setMaxResults(1).getResultList();
        if (productionStepConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionStepConfig.findProductionStepConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productionstepconfigs/productionstepconfigView";
        }
        populateEditForm(uiModel, productionStepConfigs.iterator().next());
        return "productionstepconfigs/productionstepconfigView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionStepConfig> productionStepConfigs = ProductionStepConfig.findProductionStepConfigByIdLowerThan(id).setMaxResults(1).getResultList();
        if (productionStepConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionStepConfig.findProductionStepConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productionstepconfigs/productionstepconfigView";
        }
        populateEditForm(uiModel, productionStepConfigs.iterator().next());
        return "productionstepconfigs/productionstepconfigView";
    }

    private ProductionStepConfig doAConsistantMerge(ProductionStepConfig productionStepConfig) {
        try {
            productionStepConfig.merge();
        } catch (Exception e) {
            productionStepConfig.setVersion(CustomerOrder.findCustomerOrder(productionStepConfig.getId()).getVersion());
            productionStepConfig = productionStepConfig.merge();
        }
        return productionStepConfig;
    }
}
