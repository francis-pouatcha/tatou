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
import cm.adorsys.gpao.model.ProductionTaskConfig;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productiontaskconfigs")
@Controller
@RooWebScaffold(path = "productiontaskconfigs", formBackingObject = ProductionTaskConfig.class)
public class ProductionTaskConfigController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditProductionTaskConfigConfigForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        ProductionTaskConfig productionTaskConfig = null;
        if (id == null) {
            productionTaskConfig = new ProductionTaskConfig();
        } else {
            productionTaskConfig = ProductionTaskConfig.findProductionTaskConfig(id);
        }
        populateEditForm(uiModel, productionTaskConfig);
        return "productiontaskconfigs/productiontaskconfigsView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditProductionTaskConfig(@Valid ProductionTaskConfig productionTaskConfig, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productionTaskConfig);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "productiontaskconfigs/productiontaskconfigsView";
        }
        if (productionTaskConfig.getId() == null) {
            productionTaskConfig.persist();
        }
        productionTaskConfig = doAConsistantMerge(productionTaskConfig);
        populateEditForm(uiModel, productionTaskConfig);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "productiontaskconfigs/productiontaskconfigsView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextProductionTask(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionTaskConfig> productionTaskConfigs = ProductionTaskConfig.findProductionTaskConfigByIdUpperThan(id).setMaxResults(1).getResultList();
        if (productionTaskConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionTaskConfig.findProductionTaskConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productiontaskconfigs/productiontaskconfigsView";
        }
        populateEditForm(uiModel, productionTaskConfigs.iterator().next());
        return "productiontaskconfigs/productiontaskconfigsView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousProductionTask(@PathVariable("id") Long id, Model uiModel) {
        List<ProductionTaskConfig> productionTaskConfigs = ProductionTaskConfig.findProductionTaskConfigByIdLowerThan(id).setMaxResults(1).getResultList();
        if (productionTaskConfigs.isEmpty()) {
            populateEditForm(uiModel, ProductionTaskConfig.findProductionTaskConfig(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune configuration trouve !");
            return "productiontaskconfigs/productiontaskconfigsView";
        }
        populateEditForm(uiModel, productionTaskConfigs.iterator().next());
        return "productiontaskconfigs/productiontaskconfigsView";
    }

    private ProductionTaskConfig doAConsistantMerge(ProductionTaskConfig productionTaskConfig) {
        try {
            productionTaskConfig.merge();
        } catch (Exception e) {
            productionTaskConfig.setVersion(CustomerOrder.findCustomerOrder(productionTaskConfig.getId()).getVersion());
            productionTaskConfig = productionTaskConfig.merge();
        }
        return productionTaskConfig;
    }
}
