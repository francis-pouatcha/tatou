package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/unitofmesureses")
@Controller
@RooWebScaffold(path = "unitofmesureses", formBackingObject = UnitOfMesures.class)
@RooWebFinder
public class UnitOfMesuresController {

    @RequestMapping(value = "/config", produces = "text/html")
    public String configUnitOfMesure(@RequestParam(value = "id", required = false) Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        populateView(uiModel, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, httpServletRequest.getAttribute(MessageType.SUCCESS_MESSAGE));
        return "unitofmesureses/config";
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editUnitOfMesure(@PathVariable("id") Long id, Model uiModel) {
        populateView(uiModel, UnitOfMesures.findUnitOfMesures(id), null);
        return "unitofmesureses/config";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.POST)
    public String configUnitOfMesure(@Valid UnitOfMesures unitOfMesures, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        unitOfMesures.validate(bindingResult, uiModel);
        if (bindingResult.hasErrors()) {
            populateView(uiModel, unitOfMesures, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "unitofmesureses/config";
        }
        if (unitOfMesures.getIsRefUnit()) {
            List<UnitOfMesures> udms = UnitOfMesures.findUnitOfMesuressByGroupEqualsAndReference(unitOfMesures.getUnitGroup(), Boolean.TRUE).getResultList();
            for (UnitOfMesures unitOfMesures2 : udms) {
                unitOfMesures2.setIsRefUnit(Boolean.FALSE);
                unitOfMesures2.merge();
            }
        }
        if (unitOfMesures.getId() != null) {
            unitOfMesures.merge();
        } else {
            unitOfMesures.persist();
        }
        uiModel.asMap().clear();
        httpServletRequest.setAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Emplacement enregistrer avec success !"));
        uiModel.asMap().clear();
        return "forward:/unitofmesureses/config?id=" + encodeUrlPathSegment(unitOfMesures.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/findByNameAndGroup", produces = "text/html", method = RequestMethod.POST)
    public String findUnitOfMesure(UnitOfMesures unitOfMesures, Model uiModel) {
        List<UnitOfMesures> unitOfMesureses = UnitOfMesures.findUnitOfMesuresesByNameLikeAndUnitGroup(unitOfMesures.getName(), unitOfMesures.getUnitGroup()).getResultList();
        populateView(uiModel, null, unitOfMesureses);
        return "unitofmesureses/config";
    }

    public void populateView(Model uiModel, UnitOfMesures unitOfMesures, List<cm.adorsys.gpao.model.UnitOfMesures> unitOfMesureses) {
        uiModel.addAttribute("unitofmesureses", unitOfMesureses == null ? UnitOfMesures.findAllUnitOfMesureses() : unitOfMesureses);
        uiModel.addAttribute("unitOfMesures", unitOfMesures == null ? new UnitOfMesures() : unitOfMesures);
        List<UdmGroup> findAllUdmGroups = UdmGroup.findAllUdmGroups();
        uiModel.addAttribute("udmgroups", findAllUdmGroups);
    }
}
