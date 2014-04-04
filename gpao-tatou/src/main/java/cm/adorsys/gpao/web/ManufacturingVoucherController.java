package cm.adorsys.gpao.web;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.uimodel.ManufacturingItemUiModel;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IProductionService;
import cm.adorsys.gpao.services.impl.function.FindManufacturingVoucherItems;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/manufacturingvouchers")
@Controller
@RooWebScaffold(path = "manufacturingvouchers", formBackingObject = ManufacturingVoucher.class)
public class ManufacturingVoucherController {
	
	@Autowired
	IProductionService  productionService;

	@Autowired
	IProductService productService;
	
    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditCustomerOrdersForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        ManufacturingVoucher manufacturingVoucher =   id == null ? new ManufacturingVoucher() : ManufacturingVoucher.findManufacturingVoucher(id); 
        populateEditForm(uiModel, manufacturingVoucher);
        return "manufacturingvouchers/manufacturingvoucherView";
    }


    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditCustomerOrders(@Valid ManufacturingVoucher manufacturingVoucher, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, manufacturingVoucher);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        if (manufacturingVoucher.getId() == null) {
            manufacturingVoucher.setDocumentState(DocumentStates.BROUILLON);
            manufacturingVoucher.persist();
        }
        manufacturingVoucher = doAConsistantMerge(manufacturingVoucher);
        populateEditForm(uiModel, manufacturingVoucher);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "manufacturingvouchers/manufacturingvoucherView";
    }
    
    @RequestMapping(value = "/findProductByNameLike/{name}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findProductByNameLike(@PathVariable("name") String name) {
    	List<Product> productList = productService.findProductsByNameLike(name, 100);
        return Product.toJsonArray(productList);
    }

    @RequestMapping(value = "/getSelectedProduct/{productId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSelectedProduct(@PathVariable("productId") Long productId) {
    	return Product.findProduct(productId).toJson();
    }

    @RequestMapping(value = "/{manufacturingVoucherId}/addManufacturingItem", method = RequestMethod.GET, params = { "productId" })
    @ResponseBody
    public String addOrderItem(@PathVariable("manufacturingVoucherId") Long manufacturingVoucherId, ManufacturingVoucherItem manufacturingVoucherItem, @RequestParam("productId") Long productId, Model uiModel) {
        ManufacturingVoucher manufacturingVoucher = ManufacturingVoucher.findManufacturingVoucher(manufacturingVoucherId);
        /*if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.VALIDATE)) {
            throw new RuntimeException("This operation is not allowed");
        }*/
        manufacturingVoucherItem.setProduct(Product.findProduct(productId));
        productionService.addManufacturingVoucherItem(manufacturingVoucher, manufacturingVoucherItem);
        return  ManufacturingVoucherItem.toJsonArray(ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList());
    }

    private ManufacturingVoucher doAConsistantMerge(ManufacturingVoucher manufacturingVoucher) {
        try {
        	manufacturingVoucher.merge();
        } catch (Exception e) {
        	manufacturingVoucher.setVersion(CustomerOrder.findCustomerOrder(manufacturingVoucher.getId()).getVersion());
        	manufacturingVoucher = manufacturingVoucher.merge();
        }
        return manufacturingVoucher;
    }
    void populateEditForm(Model uiModel, ManufacturingVoucher manufacturingVoucher) {
        uiModel.addAttribute("manufacturingVoucher", manufacturingVoucher);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("partners", Partner.findAllActiveProviders().getResultList());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
        FindManufacturingVoucherItems processManufacturingVoucher = (FindManufacturingVoucherItems)productionService.processManufacturingVoucher(manufacturingVoucher, new FindManufacturingVoucherItems());
        List<ManufacturingVoucherItem> manufacturingVoucherItems = processManufacturingVoucher.getManufacturingVoucherItems();
        List<ManufacturingItemUiModel> manufacturingItemUiModels = new ArrayList<ManufacturingItemUiModel>();
        for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
        	ManufacturingItemUiModel manufacturingItemUiModel = new ManufacturingItemUiModel();
        	manufacturingItemUiModel.setManufacturingVoucherItem(manufacturingVoucherItem);
        	List<Caracteristic> resultList = Caracteristic.findCaracteristicsByProduct(manufacturingVoucherItem.getProduct()).getResultList();
        	Caracteristic caracteristic = null;
        	if(!resultList.isEmpty()) {
        		caracteristic= resultList.iterator().next();
        	}
			manufacturingItemUiModel.setCaracteristic(caracteristic);
        	if(caracteristic != null) {
        		Collection<Specificity> specificities = SpecificityToCaracteristicMap.findSpecificitysByCaracteristicsEquals(caracteristic).getResultList();
    			manufacturingItemUiModel.setSpecifycities(new HashSet<Specificity>(specificities));
        	}
        	manufacturingItemUiModels.add(manufacturingItemUiModel);
		}
		uiModel.addAttribute("manufacturingItemUiModels", manufacturingItemUiModels);
    }
}