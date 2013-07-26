package cm.adorsys.gpao.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.utils.MessageType;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/unitofmesureses")
@Controller
@RooWebScaffold(path = "unitofmesureses", formBackingObject = UnitOfMesures.class)
public class UnitOfMesuresController {

	@RequestMapping(value = "/config", produces = "text/html")
	public String configUnitOfMesure(@RequestParam(value="id",required=false) Long id, Model uiModel) {
		UdmGroup udmGroup = null ;
		if(id!=null) udmGroup = UdmGroup.findUdmGroup(id);
		populateView(uiModel, null,null,udmGroup);
		return "unitofmesureses/config";
	}

	@RequestMapping(value = "/save/{id}", produces = "text/html")
	public String editUnitOfMesure(@PathVariable("id")Long id , Model uiModel) {
		populateView(uiModel, UnitOfMesures.findUnitOfMesures(id),null,new UdmGroup());
		return "unitofmesureses/config";
	}

	@RequestMapping(value = "/save", produces = "text/html" ,method = RequestMethod.POST)
	public String configUnitOfMesure(@Valid UnitOfMesures unitOfMesures, BindingResult bindingResult , Model uiModel,HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateView(uiModel, unitOfMesures,null,new UdmGroup());
			uiModel.addAttribute(MessageType.ERROR_MESSAGE,Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
			return "unitofmesureses/config";
		}
		uiModel.asMap().clear();
		 UnitOfMesures merge =unitOfMesures.merge();
        return "redirect:/unitofmesureses/config?id=" + encodeUrlPathSegment(merge.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(value = "/findByNameAndGroup", produces = "text/html" ,method = RequestMethod.POST)
	public String findUnitOfMesure(UnitOfMesures unitOfMesures , Model uiModel) {
		List<UnitOfMesures> unitOfMesureses = UnitOfMesures.findUnitOfMesuresesByNameLikeAndUnitGroup(unitOfMesures.getName(), unitOfMesures.getUnitGroup()).getResultList();
		populateView(uiModel, null, unitOfMesureses , new UdmGroup());
		return "unitofmesureses/config";
	}

	public void populateView(Model uiModel,UnitOfMesures unitOfMesures ,List<UnitOfMesures> unitOfMesureses,UdmGroup udmGroup){
		uiModel.addAttribute("unitofmesureses",unitOfMesureses==null? UnitOfMesures.findAllUnitOfMesureses():unitOfMesureses) ;
		uiModel.addAttribute("unitOfMesures", unitOfMesures==null? new UnitOfMesures():unitOfMesures) ;
		List<UdmGroup> findAllUdmGroups = UdmGroup.findAllUdmGroups();
		if(udmGroup!=null) findAllUdmGroups.add(0, udmGroup);
		uiModel.addAttribute("udmgroups", findAllUdmGroups ) ;
	}


}

