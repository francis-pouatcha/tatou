package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.PartnerType;
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

@RequestMapping("/partners")
@Controller
@RooWebScaffold(path = "partners", formBackingObject = Partner.class)
public class PartnerController {

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditPartnersForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Partner partner = id == null ? new Partner() : Partner.findPartner(id);
        populateEditForm(uiModel, partner);
        return "partners/partnerView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String addOrEditPartners(@Valid Partner partner, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partner);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement !");
            return "partners/update";
        }
        MultipartFile uploadegLogo = partner.getPartnerLogo();
        if (uploadegLogo != null) {
            if (!uploadegLogo.isEmpty()) {
                String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.COMPANY_LOGO_PATH, uploadegLogo, "logo_" + partner.getName());
                if (saveFileName != null) partner.setLogoPath(saveFileName);
            }
        }
        Partner merge = partner.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "partners/partnerView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextPartners(@PathVariable("id") Long id, Model uiModel) {
        List<Partner> nextPartners = Partner.findPartnersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextPartners.isEmpty()) {
            populateEditForm(uiModel, Partner.findPartner(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Partenaire trouve !");
            return "partners/partnerView";
        }
        Partner next = nextPartners.iterator().next();
        populateEditForm(uiModel, next);
        return "partners/partnerView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousPartners(@PathVariable("id") Long id, Model uiModel) {
        List<Partner> nextPartners = Partner.findPartnersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextPartners.isEmpty()) {
            populateEditForm(uiModel, Partner.findPartner(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Partenaire trouve !");
            return "partners/partnerView";
        }
        Partner next = nextPartners.iterator().next();
        populateEditForm(uiModel, next);
        return "partners/partnerView";
    }

    void populateEditForm(Model uiModel, Partner partner) {
        uiModel.addAttribute("partner", partner);
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("partnergroups", PartnerGroup.findAllPartnerGroups());
        uiModel.addAttribute("partnertypes", Arrays.asList(PartnerType.values()));
    }
}
