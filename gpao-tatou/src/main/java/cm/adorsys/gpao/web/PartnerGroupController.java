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
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/partnergroups")
@Controller
@RooWebScaffold(path = "partnergroups", formBackingObject = PartnerGroup.class)
public class PartnerGroupController {

    @RequestMapping(value = "/config", produces = "text/html")
    public String configTaxe(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        PartnerGroup partnerGroup = null;
        if (id != null) partnerGroup = PartnerGroup.findPartnerGroup(id);
        populateView(uiModel, partnerGroup, null);
        return "partnergroups/config";
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editPartnerGroup(@PathVariable("id") Long id, Model uiModel) {
        populateView(uiModel, PartnerGroup.findPartnerGroup(id), null);
        return "partnergroups/config";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.PUT)
    public String configPartnerGroup(@Valid PartnerGroup PartnerGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateView(uiModel, PartnerGroup, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "partnergroups/config";
        }
        uiModel.asMap().clear();
        PartnerGroup merge = PartnerGroup.merge();
        populateView(uiModel, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("groupe de partenair enregistrer avec success !"));
        return "partnergroups/config";
    }

    public void populateView(Model uiModel, PartnerGroup partnerGroup, List<PartnerGroup> partnerGroups) {
        uiModel.addAttribute("partnergroups", partnerGroups == null ? PartnerGroup.findAllPartnerGroups() : partnerGroups);
        uiModel.addAttribute("partnerGroup", partnerGroup == null ? new PartnerGroup() : partnerGroup);
    }
}
