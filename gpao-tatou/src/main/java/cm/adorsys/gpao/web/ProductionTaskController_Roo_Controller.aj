// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionTask;
import cm.adorsys.gpao.model.ProductionTaskConfig;
import cm.adorsys.gpao.web.ProductionTaskController;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ProductionTaskController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProductionTaskController.create(@Valid ProductionTask productionTask, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productionTask);
            return "productiontasks/create";
        }
        uiModel.asMap().clear();
        productionTask.persist();
        return "redirect:/productiontasks/" + encodeUrlPathSegment(productionTask.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProductionTaskController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ProductionTask());
        return "productiontasks/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProductionTaskController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("productiontask", ProductionTask.findProductionTask(id));
        uiModel.addAttribute("itemId", id);
        return "productiontasks/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProductionTaskController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("productiontasks", ProductionTask.findProductionTaskEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) ProductionTask.countProductionTasks() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("productiontasks", ProductionTask.findAllProductionTasks(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "productiontasks/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProductionTaskController.update(@Valid ProductionTask productionTask, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productionTask);
            return "productiontasks/update";
        }
        uiModel.asMap().clear();
        productionTask.merge();
        return "redirect:/productiontasks/" + encodeUrlPathSegment(productionTask.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProductionTaskController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ProductionTask.findProductionTask(id));
        return "productiontasks/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProductionTaskController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ProductionTask productionTask = ProductionTask.findProductionTask(id);
        productionTask.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/productiontasks";
    }
    
    void ProductionTaskController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("productionTask_startdate_date_format", "dd-MM-yyyy hh:mm");
        uiModel.addAttribute("productionTask_enddate_date_format", "dd-MM-yyyy hh:mm");
    }
    
    void ProductionTaskController.populateEditForm(Model uiModel, ProductionTask productionTask) {
        uiModel.addAttribute("productionTask", productionTask);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("productionsteps", ProductionStep.findAllProductionSteps());
        uiModel.addAttribute("productiontaskconfigs", ProductionTaskConfig.findAllProductionTaskConfigs());
    }
    
    String ProductionTaskController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}