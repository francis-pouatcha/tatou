package cm.adorsys.gpao.web;
import java.util.Arrays;
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

import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productsubfamilys")
@Controller
@RooWebScaffold(path = "productsubfamilys", formBackingObject = ProductSubFamily.class)
public class ProductSubFamilyController {

    @RequestMapping(value = "/config/{module}", produces = "text/html")
    public String configProductSubFamily(@PathVariable("module") String module, @RequestParam(value = "id", required = false) Long id, Model uiModel) {
        uiModel.addAttribute("module", module);
        ProductFamily family = null;
        if (id != null) family = ProductFamily.findProductFamily(id);
        populateView(uiModel, null, null, family);
        return "productsubfamilys/" + module + "/config";
    }

    @RequestMapping(value = "/save/{module}/{id}", produces = "text/html")
    public String editProductSubFamily(@PathVariable("module") String module, @PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("module", module);
        populateView(uiModel, ProductSubFamily.findProductSubFamily(id), null, null);
        return "productsubfamilys/" + module + "/config";
    }

    @RequestMapping(value = "/save/{module}", produces = "text/html", method = RequestMethod.POST)
    public String configProductSubFamily(@PathVariable("module") String module, @Valid ProductSubFamily productSubFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.addAttribute("module", module);
        if (bindingResult.hasErrors()) {
            populateView(uiModel, productSubFamily, null, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "productsubfamilys/" + module + "/config";
        }
        uiModel.asMap().clear();
        ProductSubFamily merge = productSubFamily.merge();
        populateView(uiModel, null, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Sous Famille  enregistrer avec success !"));
        return "productsubfamilys/" + module + "/config";
    }

    @RequestMapping(value = "/findByNameAndFamily/{module}", produces = "text/html", method = RequestMethod.POST)
    public String findProductSubFamily(@PathVariable("module") String module, ProductSubFamily productSubFamily, Model uiModel) {
        uiModel.addAttribute("module", module);
        List<ProductSubFamily> productSubFamilies = ProductSubFamily.findProductSubFamilyByNameLikeProductFamily(productSubFamily.getName(), productSubFamily.getProductFamily()).getResultList();
        populateView(uiModel, null, productSubFamilies, null);
        return "productsubfamilys/" + module + "/config";
    }

    public void populateView(Model uiModel, ProductSubFamily productSubFamily, List<ProductSubFamily> productSubFamilies, ProductFamily productFamily) {
        uiModel.addAttribute("productsubfamilys", productSubFamilies == null ? ProductSubFamily.findAllProductSubFamilys() : productSubFamilies);
        uiModel.addAttribute("productsubfamily", productSubFamily == null ? new ProductSubFamily() : productSubFamily);
        List<ProductFamily> allFamily = ProductFamily.findAllProductFamilys();
        if (productFamily != null) allFamily.add(0, productFamily);
        uiModel.addAttribute("productfamilys", allFamily);
    }
}
