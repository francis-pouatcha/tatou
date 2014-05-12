// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import cm.adorsys.gpao.web.ProductController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect ProductController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByNameLike", "form" }, method = RequestMethod.GET)
    public String ProductController.findProductsByNameLikeForm(Model uiModel) {
        return "products/findProductsByNameLike";
    }
    
    @RequestMapping(params = "find=ByNameLike", method = RequestMethod.GET)
    public String ProductController.findProductsByNameLike(@RequestParam("name") String name, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("products", Product.findProductsByNameLike(name, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Product.countFindProductsByNameLike(name) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("products", Product.findProductsByNameLike(name, sortFieldName, sortOrder).getResultList());
        }
        return "products/list";
    }
    
    @RequestMapping(params = { "find=ByProductionTypeConfig", "form" }, method = RequestMethod.GET)
    public String ProductController.findProductsByProductionTypeConfigForm(Model uiModel) {
        uiModel.addAttribute("productiontypeconfigs", ProductionTypeConfig.findAllProductionTypeConfigs());
        return "products/findProductsByProductionTypeConfig";
    }
    
    @RequestMapping(params = "find=ByProductionTypeConfig", method = RequestMethod.GET)
    public String ProductController.findProductsByProductionTypeConfig(@RequestParam("productionTypeConfig") ProductionTypeConfig productionTypeConfig, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("products", Product.findProductsByProductionTypeConfig(productionTypeConfig, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Product.countFindProductsByProductionTypeConfig(productionTypeConfig) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("products", Product.findProductsByProductionTypeConfig(productionTypeConfig, sortFieldName, sortOrder).getResultList());
        }
        return "products/list";
    }
    
}
