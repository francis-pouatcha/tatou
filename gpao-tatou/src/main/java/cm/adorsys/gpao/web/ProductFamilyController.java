package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.ProductFamily;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/productfamilys")
@Controller
@RooWebScaffold(path = "productfamilys", formBackingObject = ProductFamily.class)
public class ProductFamilyController {

	@RequestMapping(value = "/{module}",params = "form", produces = "text/html")
	public String createForm(@PathVariable("module") String module ,Model uiModel) {
		uiModel.addAttribute("module", module);
		populateEditForm(uiModel, new ProductFamily());
		return "productfamilys/"+module+"/create";
	}
	@RequestMapping(value = "/{module}/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("module") String module ,@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("module", module);
        populateEditForm(uiModel, ProductFamily.findProductFamily(id));
        return "productfamilys/"+module+"/update";
    }

	@RequestMapping(value = "/{module}",method = RequestMethod.POST, produces = "text/html")
	public String create(@PathVariable("module") String module ,@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.addAttribute("module", module);
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, productFamily);
			return "productfamilys/"+module+"/create";
		}
		uiModel.asMap().clear();
		productFamily.persist();
		return "redirect:/productsubfamilys/config/"+module+"?id=" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{module}",method = RequestMethod.PUT, produces = "text/html")
	public String update(@PathVariable("module") String module ,@Valid ProductFamily productFamily, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.addAttribute("module", module);
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, productFamily);
			 return "productfamilys/"+module+"/update";
		}
		uiModel.asMap().clear();
		productFamily.merge();
		return "redirect:/productsubfamilys/config/"+module+"?id=" + encodeUrlPathSegment(productFamily.getId().toString(), httpServletRequest);
	}
}
