package cm.adorsys.gpao.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cm.adorsys.gpao.model.Location;
import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.utils.MessageType;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/productsubfamilys")
@Controller
@RooWebScaffold(path = "productsubfamilys", formBackingObject = ProductSubFamily.class)
public class ProductSubFamilyController {
	

	@RequestMapping(value = "/config", produces = "text/html")
	public String configProductSubFamily(@RequestParam(value="id",required=false) Long id, Model uiModel) {
		ProductFamily family = null ;
		if(id!=null) family = ProductFamily.findProductFamily(id);
		populateView(uiModel, null,null,family);
		return "productsubfamilys/config";
	}

	@RequestMapping(value = "/save/{id}", produces = "text/html")
	public String editProductSubFamily(@PathVariable("id")Long id , Model uiModel) {
		populateView(uiModel, ProductSubFamily.findProductSubFamily(id),null,null);
		return "productsubfamilys/config";
	}

	@RequestMapping(value = "/save", produces = "text/html" ,method = RequestMethod.POST)
	public String configProductSubFamily(@Valid ProductSubFamily productSubFamily, BindingResult bindingResult , Model uiModel,HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateView(uiModel, productSubFamily,null,null);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE,Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
			return "productsubfamilys/config";
		}
		uiModel.asMap().clear();
		ProductSubFamily merge =productSubFamily.merge();
		 populateView(uiModel, null,null ,null);
	     uiModel.addAttribute(MessageType.SUCCESS_MESSAGE,Arrays.asList("Sous Famille  enregistrer avec success !"));
        return "productsubfamilys/config";
	}
	
	@RequestMapping(value = "/findByNameAndFamily", produces = "text/html" ,method = RequestMethod.POST)
	public String findProductSubFamily(ProductSubFamily productSubFamily , Model uiModel) {
		List<ProductSubFamily> productSubFamilies = ProductSubFamily.findProductSubFamilyByNameLikeProductFamily(productSubFamily.getName(), productSubFamily.getProductFamily()).getResultList();
		populateView(uiModel, null, productSubFamilies , null);
		return "productsubfamilys/config";
	}

	public void populateView(Model uiModel,ProductSubFamily productSubFamily ,List<ProductSubFamily> productSubFamilies,ProductFamily productFamily){
		uiModel.addAttribute("productsubfamilys",productSubFamilies==null? ProductSubFamily.findAllProductSubFamilys():productSubFamilies) ;
		uiModel.addAttribute("productsubfamily", productSubFamily==null? new ProductSubFamily():productSubFamily) ;
		List<ProductFamily> allFamily = ProductFamily.findAllProductFamilys();
		if(productFamily!=null) allFamily.add(0, productFamily);
		uiModel.addAttribute("productfamilys", allFamily ) ;
	}
}
