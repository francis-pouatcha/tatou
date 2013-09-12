package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.RoleNames;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.utils.GpaoDocumentDirectories;
import cm.adorsys.gpao.utils.GpaoFileUtils;
import cm.adorsys.gpao.utils.MessageType;
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
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/gpaousergroups")
@Controller
@RooWebScaffold(path = "gpaousergroups", formBackingObject = GpaoUserGroup.class)
public class GpaoUserGroupController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditGpaoUserGroupsForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        GpaoUserGroup gpaoUserGroup = id == null ? new GpaoUserGroup() : GpaoUserGroup.findGpaoUserGroup(id);
        populateEditForm(uiModel, gpaoUserGroup);
        return "gpaousergroups/gpaousergroupView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.PUT, produces = "text/html")
    public String addOrEditGpaoUserGroups(@Valid GpaoUserGroup gpaoUserGroup, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gpaoUserGroup);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "gpaousergroups/gpaousergroupView";
        }
        GpaoUserGroup merge = gpaoUserGroup.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "gpaousergroups/gpaousergroupView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextGpaoUserGroup(@PathVariable("id") Long id, Model uiModel) {
        List<GpaoUserGroup> nextGpaoUserGroup = GpaoUserGroup.findGpaoUserGroupsByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextGpaoUserGroup.isEmpty()) {
            populateEditForm(uiModel, GpaoUserGroup.findGpaoUserGroup(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun GpaoUserGroup trouve !");
            return "gpaousergroups/gpaousergroupView";
        }
        GpaoUserGroup next = nextGpaoUserGroup.iterator().next();
        populateEditForm(uiModel, next);
        return "gpaousergroups/gpaousergroupView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousGpaoUserGroup(@PathVariable("id") Long id, Model uiModel) {
        List<GpaoUserGroup> nextGpaoUserGroup = GpaoUserGroup.findGpaoUserGroupsByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextGpaoUserGroup.isEmpty()) {
            populateEditForm(uiModel, GpaoUserGroup.findGpaoUserGroup(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun GpaoUserGroup trouve !");
            return "gpaousergroups/gpaousergroupView";
        }
        GpaoUserGroup next = nextGpaoUserGroup.iterator().next();
        populateEditForm(uiModel, next);
        return "gpaousergroups/gpaousergroupView";
    }

    void populateEditForm(Model uiModel, GpaoUserGroup gpaoUserGroup) {
        uiModel.addAttribute("gpaoUserGroup", gpaoUserGroup);
        uiModel.addAttribute("rolenameses", Arrays.asList(RoleNames.values()));
    }
}
