package cm.adorsys.gpao.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.utils.MessageType;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/companys")
@Controller
@RooWebScaffold(path = "companys", formBackingObject = Company.class)
public class CompanyController {
	

	@RequestMapping(value = "/config",method = RequestMethod.GET, produces = "text/html")
	public String configCompanyForm(Model uiModel) {
		 List<Company> companys = Company.findAllCompanys();
		 Company company = null ;
		 if(!companys.isEmpty()) company = companys.iterator().next();
		populateEditForm(uiModel, company);
		return "companys/companyView";
	}

	@RequestMapping(value = "/saveOrEdit",method = RequestMethod.PUT, produces = "text/html")
	public String saveOrEditPartners(@Valid Company company, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, company);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE,"une erreur est Survenue durant l'enregistrement !");
			return "companys/companyView";
		}
		uiModel.asMap().clear();
		Company merge =	company.merge();
		populateEditForm(uiModel, merge);
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistree avec success !");
		return "companys/companyView";
	}
	
	
	void  populateEditForm(Model uiModel, Company company) {
        uiModel.addAttribute("company", company==null ? new Company():company);
       // uiModel.addAttribute("contactses", Contacts.findAllContactses());
        uiModel.addAttribute("devises", Devise.findAllDevises());
    }
}
