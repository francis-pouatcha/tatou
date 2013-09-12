package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Gender;
import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoDocumentDirectories;
import cm.adorsys.gpao.utils.GpaoFileUtils;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/gpaousers")
@Controller
@RooWebScaffold(path = "gpaousers", formBackingObject = GpaoUser.class)
public class GpaoUserController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditGpaoUsersForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        GpaoUser gpaoUser = id == null ? new GpaoUser() : GpaoUser.findGpaoUser(id);
        populateEditForm(uiModel, gpaoUser);
        return "gpaousers/gpaouserView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String addOrEditGpaoUsers(@Valid GpaoUser gpaoUser, BindingResult bindingResult, Model uiModel, HttpSession session) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gpaoUser);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "gpaousers/gpaouserView";
        }
        MultipartFile uploadegLogo = gpaoUser.getUserImage();
        if (uploadegLogo != null) {
            if (!uploadegLogo.isEmpty()) {
                String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.USERS_IMG_PATH, uploadegLogo, "img_" + gpaoUser.getUserName());
                if (saveFileName != null) gpaoUser.setUserImagePath(saveFileName);
            }
        }
        gpaoUser.makeFullName();
        GpaoUser merge = gpaoUser.merge();
        uiModel.asMap().clear();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "gpaousers/gpaouserView";
    }

    @RequestMapping(value = "/changePersonalInfoForm", method = RequestMethod.GET, produces = "text/html")
    public String changePersonalInfoForm(Model uiModel) {
        GpaoUser gpaoUser = SecurityUtil.getGpaoUser();
        populateEditForm(uiModel, gpaoUser);
        return "gpaousers/infos";
    }

    @RequestMapping(value = "/changePersonalInfo", method = RequestMethod.POST, produces = "text/html")
    public String changePersonalInfo(@Valid GpaoUser gpaoUser, BindingResult bindingResult, Model uiModel, HttpSession session) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, gpaoUser);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "gpaousers/infos";
        }
        MultipartFile uploadegLogo = gpaoUser.getUserImage();
        if (uploadegLogo != null) {
            if (!uploadegLogo.isEmpty()) {
                String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.USERS_IMG_PATH, uploadegLogo, "img_" + gpaoUser.getUserName());
                if (saveFileName != null) gpaoUser.setUserImagePath(saveFileName);
            }
        }
        gpaoUser.makeFullName();
        gpaoUser.setGpaoUserGroups(GpaoUser.findGpaoUser(gpaoUser.getId()).getGpaoUserGroups());
        GpaoUser merge = gpaoUser.merge();
        populateEditForm(uiModel, merge);
        if (session != null) session.setAttribute("currentUser", gpaoUser);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "gpaousers/infos";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextGpaoUser(@PathVariable("id") Long id, Model uiModel) {
        List<GpaoUser> nextGpaoUser = GpaoUser.findGpaoUsersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextGpaoUser.isEmpty()) {
            populateEditForm(uiModel, GpaoUser.findGpaoUser(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun GpaoUser trouve !");
            return "gpaousers/gpaouserView";
        }
        GpaoUser next = nextGpaoUser.iterator().next();
        populateEditForm(uiModel, next);
        return "gpaousers/gpaouserView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousGpaoUser(@PathVariable("id") Long id, Model uiModel) {
        List<GpaoUser> nextGpaoUser = GpaoUser.findGpaoUsersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextGpaoUser.isEmpty()) {
            populateEditForm(uiModel, GpaoUser.findGpaoUser(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun GpaoUser trouve !");
            return "gpaousers/gpaouserView";
        }
        GpaoUser next = nextGpaoUser.iterator().next();
        populateEditForm(uiModel, next);
        return "gpaousers/gpaouserView";
    }

    @RequestMapping(value = "/resetpassword/{id}", method = RequestMethod.GET)
    public String resetPassword(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        GpaoUser gpaoUser = GpaoUser.findGpaoUser(id);
        String newPassword = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
        gpaoUser.changePassword(newPassword);
        GpaoUser merge = (GpaoUser) gpaoUser.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, " Le Nouveau Password est : <b> " + newPassword + "</b>");
        return "gpaousers/gpaouserView";
    }

    void populateEditForm(Model uiModel, GpaoUser gpaoUser) {
        uiModel.addAttribute("gpaoUser", gpaoUser);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("genders", Arrays.asList(Gender.values()));
        uiModel.addAttribute("gpaousergroups", GpaoUserGroup.findAllGpaoUserGroups());
    }
}
