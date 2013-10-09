package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.utils.GpaoDocumentDirectories;
import cm.adorsys.gpao.utils.GpaoFileUtils;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Currency;
import java.util.List;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/companys")
@Controller
@RooWebScaffold(path = "companys", formBackingObject = Company.class)
public class CompanyController {

    @RequestMapping(value = "/config", method = RequestMethod.GET, produces = "text/html")
    public String configCompanyForm(Model uiModel) {
        List<Company> companys = Company.findAllCompanys();
        Company company = null;
        if (!companys.isEmpty()) company = companys.iterator().next();
        populateEditForm(uiModel, company);
        return "companys/companyView";
    }

    @RequestMapping(value = "/saveOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String saveOrEditPartners(@Valid Company company, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, company);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement !");
            return "companys/companyView";
        }
        MultipartFile uploadegLogo = company.getUploadedLogo();
        if (uploadegLogo != null) {
            if (!uploadegLogo.isEmpty()) {
                String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.COMPANY_LOGO_PATH, uploadegLogo, "logo");
                if (saveFileName != null) company.setLogoPath(saveFileName);
            }
        }
        Company merge = company.merge();
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistree avec success !");
        return "companys/companyView";
    }

    void populateEditForm(Model uiModel, Company company) {
        uiModel.addAttribute("company", company == null ? new Company() : company);
        uiModel.addAttribute("devises", Devise.findAllDevises());
    }
}
