package cm.adorsys.gpao.web;
import java.util.Date;
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

import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.ProductionWorkshop;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/productionworkshops")
@Controller
@RooWebScaffold(path = "productionworkshops", formBackingObject = ProductionWorkshop.class)
public class ProductionWorkshopController {
	 	@RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
	    public String addOrEditProductionWorshopsForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
	 		ProductionWorkshop productionWorkshop = null ;
	 		if(id == null ) {
	 			productionWorkshop =  new ProductionWorkshop();
	 			productionWorkshop.setDateCreated(new Date());
	 		}else {
	 			productionWorkshop = ProductionWorkshop.findProductionWorkshop(id);
	 		}
	        populateEditForm(uiModel, productionWorkshop);
	        return "productionworkshops/view";
	    }

	    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
	    public String addOrEditProducts(@Valid ProductionWorkshop productionWorkshop, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, productionWorkshop);
	            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
	            return "productionworkshops/view";
	        }
	        if(productionWorkshop.getId() == null) {
	        	productionWorkshop.persist();
	        }else {
	        	productionWorkshop.merge();
	        }
	        populateEditForm(uiModel, productionWorkshop);
	        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
	        return "productionworkshops/view";
	    }

	    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
	    public String getNextProduct(@PathVariable("id") Long id, Model uiModel) {
	        List<ProductionWorkshop> nextProducts = ProductionWorkshop.findProductionWorkshopsByIdUpperThan(id).setMaxResults(1).getResultList();
	        if (nextProducts.isEmpty()) {
	            populateEditForm(uiModel, ProductionWorkshop.findProductionWorkshop(id));
	            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Atelier de production trouve !");
	            return "productionworkshops/view";
	        }
	        ProductionWorkshop next = nextProducts.iterator().next();
	        populateEditForm(uiModel, next);
	        return "productionworkshops/view";
	    }

	    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
	    public String getPreviousProduct(@PathVariable("id") Long id, Model uiModel) {
	        List<ProductionWorkshop> previousProducts = ProductionWorkshop.findProductionWorkshopsByIdUpperThan(id).setMaxResults(1).getResultList();
	        if (previousProducts.isEmpty()) {
	            populateEditForm(uiModel, ProductionWorkshop.findProductionWorkshop(id));
	            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Atelier de production trouve !");
	            return "productionworkshops/view";
	        }
	        ProductionWorkshop next = previousProducts.iterator().next();
	        populateEditForm(uiModel, next);
	        return "productionworkshops/view";
	    }


	    void populateEditForm(Model uiModel, ProductionWorkshop productionWorkshop) {
	        uiModel.addAttribute("productionWorkshop", productionWorkshop);
	        addDateTimeFormatPatterns(uiModel);
	        uiModel.addAttribute("gpaousers", GpaoUser.findAllGpaoUsers());
	    }
}