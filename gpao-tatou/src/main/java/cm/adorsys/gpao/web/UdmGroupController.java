package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.UdmGroup;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/udmgroups")
@Controller
@RooWebScaffold(path = "udmgroups", formBackingObject = UdmGroup.class)
public class UdmGroupController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid UdmGroup udmGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, udmGroup);
            return "udmgroups/create";
        }
        uiModel.asMap().clear();
        udmGroup.persist();
        return "redirect:/unitofmesureses/config?id=" + encodeUrlPathSegment(udmGroup.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid UdmGroup udmGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, udmGroup);
            return "udmgroups/update";
        }
        uiModel.asMap().clear();
        udmGroup.merge();
        return "redirect:/unitofmesureses/config?id=" + encodeUrlPathSegment(udmGroup.getId().toString(), httpServletRequest);
    }
}
