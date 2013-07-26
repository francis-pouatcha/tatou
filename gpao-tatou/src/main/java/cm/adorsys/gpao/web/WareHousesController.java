package cm.adorsys.gpao.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.WareHouses;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/warehouseses")
@Controller
@RooWebScaffold(path = "warehouseses", formBackingObject = WareHouses.class)
public class WareHousesController {
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid WareHouses wareHouses, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, wareHouses);
            return "warehouseses/create";
        }
        uiModel.asMap().clear();
        wareHouses.persist();
        return "redirect:/locations/config?id=" + encodeUrlPathSegment(wareHouses.getId().toString(), httpServletRequest);
    }
	
	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid WareHouses wareHouses, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, wareHouses);
            return "warehouseses/update";
        }
        uiModel.asMap().clear();
        wareHouses.merge();
        return "redirect:/locations/config?id=" + encodeUrlPathSegment(wareHouses.getId().toString(), httpServletRequest);
    }
}
