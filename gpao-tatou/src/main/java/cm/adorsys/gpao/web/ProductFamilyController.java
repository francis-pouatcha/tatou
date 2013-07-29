package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductFamily;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/productfamilys")
@Controller
@RooWebScaffold(path = "productfamilys", formBackingObject = ProductFamily.class)
public class ProductFamilyController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productFamily);
            return "productfamilys/create";
        }
        uiModel.asMap().clear();
        productFamily.persist();
        return "redirect:/productsubfamilys/config?id=" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productFamily);
            return "productfamilys/update";
        }
        uiModel.asMap().clear();
        productFamily.merge();
        return "redirect:/productsubfamilys/config?id=" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
    }
}
