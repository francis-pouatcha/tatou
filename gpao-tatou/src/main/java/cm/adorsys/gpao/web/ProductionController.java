package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.Production;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionTask;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import cm.adorsys.gpao.model.excepions.InvalidEntityValueException;
import cm.adorsys.gpao.services.IProductionService;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productions")
@Controller
@RooWebScaffold(path = "productions", formBackingObject = Production.class)
public class ProductionController {

	@Autowired
	IProductionService productionService;
	
    @RequestMapping(value="/list",produces = "text/html")
    public String customListController(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("productions", Production.findProductionEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Production.countProductions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("productions", Production.findAllProductions(sortFieldName, sortOrder));
        }
        uiModel.addAttribute("openProductions", productionService.findOpenProductions());
        addDateTimeFormatPatterns(uiModel);
        return "productions/list";
    }
    

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditProductionForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) throws InvalidEntityValueException {
        Production production = null;
        if (id == null) {
            production = new Production();
        } else {
        	production = Production.findProduction(id);
        }
        populateEditForm(uiModel, production);
        return "productions/productionsView";
    }

    
    void populateEditForm(Model uiModel, Production production) {
        uiModel.addAttribute("production", production);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("manufacturingvouchers", ManufacturingVoucher.findAllManufacturingVouchers());
        uiModel.addAttribute("productiontypeconfigs", ProductionTypeConfig.findAllProductionTypeConfigs());
        if(production != null && production.getId() != null) {
        	List<ProductionStep> productionSteps = productionService.findProductionStepsByProduction(production);
            List<ProductionTask> productionTasks = productionService.findProductionTasksByProduction(production);
            try {
				uiModel.addAttribute("rawMaterialOrderItems", productionService.findRawMaterialOrderItems(production));
			} catch (InvalidEntityValueException e) {
				uiModel.addAttribute(MessageType.ERROR_MESSAGE, e.getMessage());
			}
            uiModel.addAttribute("manufacturingVoucherItems", productionService.findManufacturingVoucherItems(production));
            uiModel.addAttribute("productionSteps", productionSteps);
            uiModel.addAttribute("productionTasks", productionTasks);
            uiModel.addAttribute("activStep", productionService.findActiveProductionStep(production));
            uiModel.addAttribute("activTask", productionService.findActiveProductionTask(production));
        }
    }
}
