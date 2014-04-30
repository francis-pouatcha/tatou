/**
 * 
 */
package cm.adorsys.gpao.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import cm.adorsys.gpao.utils.MessageType;

/**
 * @author bwa
 *
 */

@RequestMapping("/caracteristics/{productId}")
@Controller
public class CustomCaracteristicController extends CaracteristicController{

    @RequestMapping(value = "/manage", produces = "text/html")
    public String mangeCaracteristic(@PathVariable("productId")Long productId ,@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder ,Model uiModel) {
    	Caracteristic caracteristic = null;
        if (id != null) caracteristic = Caracteristic.findCaracteristic(id);
        paginatedListQuery(uiModel, caracteristic,productId, null, null,page, size, sortFieldName, sortOrder);
        return "caracteristics/manage";
    }


    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.POST)
    public String saveCaracteristic(@PathVariable("productId")Long productId ,@Valid Caracteristic caracteristic, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            paginatedListQuery(uiModel, caracteristic,productId, null, null, 1, 10, null, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "caracteristics/manage";
        }
        uiModel.asMap().clear();
        if(caracteristic.getId() == null) {
        	caracteristic.persist();
        }else {
			caracteristic.merge().flush();
		}
        return "redirect:/caracteristics/"+productId+"/save/"+encodeUrlPathSegment(caracteristic.getId().toString(), httpServletRequest);
        /*paginatedListQuery(uiModel, caracteristic,productId, null, null, 1, 10, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Caracteristique du produit enregistree avec success ! Veillez ajouter les specificites."));
        return "caracteristics/manage";*/
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editCaracteristic(@PathVariable("productId")Long productId ,@PathVariable("id") Long id, Model uiModel) {
        paginatedListQuery(uiModel, Caracteristic.findCaracteristic(id), productId,null, null, 1, 10, null, null);
        return "caracteristics/manage";
    }
    @RequestMapping(value="/findSpecificityByNameLike/{specificityName}", method = RequestMethod.GET ,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findSpecifityByNameLike(@PathVariable("productId")Long productId ,@PathVariable("specificityName")String specificityName,HttpServletRequest servletRequest) {
    	if(StringUtils.isEmpty(specificityName)) {
    		specificityName ="*";
    	}
    	List<Specificity> specificities = Specificity.findSpecificitysByDesignationLike(specificityName).getResultList();
    	return Specificity.toJsonArray(specificities);
    }

    @RequestMapping(value="/getSelectedSpecificity/{specificityId}",method=RequestMethod.GET,produces="application/json; charset=utf-8")
    @ResponseBody
    public String getSelectedSpecificityItem(@PathVariable("productId")Long productId ,@PathVariable("specificityId")Long specificityId,HttpServletRequest httpServletRequest) {
		return Specificity.findSpecificity(specificityId).toJson();
	}
    @RequestMapping(value="/{caracteristicId}/addSelectedSpecificity",method=RequestMethod.GET)
    @ResponseBody
    public String addSeclectedSpecificity(@PathVariable("productId")Long productId ,@PathVariable("caracteristicId")Long caracteristicId,@Valid Specificity specificity, Model uiModel) {
    	Caracteristic caracteristic = Caracteristic.findCaracteristic(caracteristicId);
    	Assert.notNull(specificity.getId(), "Invalid specificity");
    	specificity = Specificity.findSpecificity(specificity.getId());
    	List<SpecificityToCaracteristicMap> existingRelationCaracteristicMaps = SpecificityToCaracteristicMap.findSpecificityToCaracteristicMapsBySpecificityAndCaracteristicEquals(specificity, caracteristic).getResultList();
    	if(!existingRelationCaracteristicMaps.isEmpty()) {
    		return "ALREADY_PRESENT";
    	}
    	SpecificityToCaracteristicMap specificityToCaracteristicMap = new SpecificityToCaracteristicMap(caracteristic, specificity);
		specificityToCaracteristicMap.persist();
		return Specificity.toJsonArray(SpecificityToCaracteristicMap.findSpecificitysByCaracteristicsEquals(caracteristic).getResultList());
	}

    @RequestMapping(value = "/{caracteristic}/specificitys", produces = "text/html")
    public String mangeSpecificity(@PathVariable("productId")Long productId ,@PathVariable("caracteristic")Long caracteristicId ,@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder ,Model uiModel) {
    	Caracteristic caracteristic = Caracteristic.findCaracteristic(caracteristicId);
        paginatedSpecificityListQuery(uiModel, null, null, page, size, sortFieldName, sortOrder);
        paginatedListQuery(uiModel, caracteristic,productId, null, null, 1, 10, sortFieldName, sortOrder);
        return "caracteristics/manage";
    }

    @RequestMapping(value = "/{caracteristic}/specificitys/{specificityId}", produces = "text/html",method=RequestMethod.DELETE)
    public String removeSpecificity(@PathVariable("productId")Long productId ,@PathVariable("caracteristic")Long caracteristicId ,@PathVariable("specificityId") Long specificityId,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder ,Model uiModel) {
    	Caracteristic caracteristic = Caracteristic.findCaracteristic(caracteristicId);
    	Specificity specificity = Specificity.findSpecificity(specificityId);
    	List<SpecificityToCaracteristicMap> maps = SpecificityToCaracteristicMap.findSpecificityToCaracteristicMapsBySpecificityAndCaracteristicEquals(specificity, caracteristic).getResultList();
    	if(!maps.isEmpty()) {
    		maps.iterator().next().remove();
    		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "La specificite a ete retiree avec success !");
    	}
        paginatedSpecificityListQuery(uiModel, null, null, page, size, sortFieldName, sortOrder);
        paginatedListQuery(uiModel, caracteristic,productId, null, null, 1, 10, sortFieldName, sortOrder);
        return "caracteristics/manage";
    }
    @RequestMapping(value = "/{caracteristic}/specificitys/{specificityId}", produces = "text/html")
    public String showSpecificity(@PathVariable("productId")Long productId ,@PathVariable("caracteristic")Long caracteristicId ,@PathVariable("specificityId") Long specificityId, Model uiModel, HttpServletRequest httpServletRequest) {
        return "redirect:/specificitys/save/"+encodeUrlPathSegment(specificityId.toString(), httpServletRequest);
    }
	private void paginatedListQuery(Model uiModel, Caracteristic caracteristic,Long productId, List<Caracteristic> caracteristics , Specificity specificity, Integer page, Integer size, String sortFieldName,String sortOrder) {
		if(productId == null) {
        	throw new IllegalArgumentException("Invalid product id");
        }
		Product product =Product.findProduct(productId);
		uiModel.addAttribute("product", product);
//		Collections.<Product> 
		List<Product> products = Arrays.<Product> asList(product);
		uiModel.addAttribute("products", products);
		products = null;
        uiModel.addAttribute("specificity", specificity == null ? new Specificity() : specificity);
        uiModel.addAttribute("caracteristic", caracteristic == null ? new Caracteristic() : caracteristic);
        if(caracteristic !=null && caracteristic.getId() != null) {
        	List<Specificity> specificitys = SpecificityToCaracteristicMap.findSpecificitysByCaracteristicsEquals(caracteristic).getResultList();
        	uiModel.addAttribute("specificitys", specificitys);
        }
        if(caracteristics != null) {
    		uiModel.addAttribute("caracteristics", caracteristics);
    		return ;// stop the execution here, if caracteristics are supplied. (@see find controller)
        }
		if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("caracteristics", Caracteristic.findCaracteristicEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Specificity.countSpecificitys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("caracteristics", Specificity.findAllSpecificitys(sortFieldName, sortOrder));
        }
	}

	private void paginatedSpecificityListQuery(Model uiModel, Specificity specificity, List<Specificity> specificities,Integer page, Integer size, String sortFieldName,String sortOrder) {
		if(specificity == null ) {
			uiModel.addAttribute("specificity", new Specificity());
		}else {
			uiModel.addAttribute("specificity", specificity);
			List<Caracteristic> caracteristics = SpecificityToCaracteristicMap.findCaracteristicsBySpecificityEquals(specificity).getResultList();
			uiModel.addAttribute("caracteristics", caracteristics);
		}
        if(specificities != null) {
    		uiModel.addAttribute("specificitys", specificities);
    		return ;// stop the execution if specificites are not null
        }
		if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("specificitys", Specificity.findSpecificityEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Specificity.countSpecificitys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("specificitys", Specificity.findAllSpecificitys(sortFieldName, sortOrder));
        }
	}

}
