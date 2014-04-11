package cm.adorsys.gpao.web;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.uimodel.ManufacturingItemUiModel;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IManufacturingVoucherService;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;
import cm.adorsys.gpao.services.impl.function.FindManufacturingVoucherItems;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/manufacturingvouchers")
@Controller
@RooWebScaffold(path = "manufacturingvouchers", formBackingObject = ManufacturingVoucher.class)
public class ManufacturingVoucherController extends AbstractOrderController {

    @Autowired
    IManufacturingVoucherService manufacturingVoucherService;

    @Autowired
    IProductService productService;

    @Autowired
    IRawMaterialOrderService rawMaterialOrderService;
    
    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditManufacturingVoucherForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        ManufacturingVoucher manufacturingVoucher = null;
        if(id == null ) {
        	manufacturingVoucher = new ManufacturingVoucher();
        	manufacturingVoucher.setCreateDate(new Date());
        	manufacturingVoucher.setCreatedBy(SecurityUtil.getUserName());
        }else {
        	manufacturingVoucher =  ManufacturingVoucher.findManufacturingVoucher(id);
        }
        populateEditForm(uiModel, manufacturingVoucher);
        return "manufacturingvouchers/manufacturingvoucherView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditManufacturingVoucherOrders(@Valid ManufacturingVoucher manufacturingVoucher, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, manufacturingVoucher);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        if (manufacturingVoucher.getId() == null) {
            manufacturingVoucher.setDocumentState(DocumentStates.BROUILLON);
            manufacturingVoucher.setOrigin(DeliveryOrigin.CREATED);
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
        manufacturingVoucherItem.setProduct(Product.findProduct(productId));
        manufacturingVoucherService.addManufacturingVoucherItem(manufacturingVoucher, manufacturingVoucherItem);
        return ManufacturingVoucherItem.toJsonArray(ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList());
    }

    @RequestMapping(value = "/{manufacturingVoucherId}/removeItem", method = RequestMethod.GET)
    public String removeOrderItems(@PathVariable("manufacturingVoucherId") Long customerOrderId, @RequestParam("itemid") Long[] orderItemIds, Model uiModel) {
        ManufacturingVoucher manufacturingVoucher = ManufacturingVoucher.findManufacturingVoucher(customerOrderId);
        boolean deleteManufacturingOrderItems = manufacturingVoucherService.deleteManufacturingOrderItems(Arrays.asList(orderItemIds));
        if (!deleteManufacturingOrderItems) {
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Nothing deleted!");
            populateEditForm(uiModel, manufacturingVoucher);
        }
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "lignes suprimee avec success !");
        populateEditForm(uiModel, manufacturingVoucher);
        return "manufacturingvouchers/manufacturingvoucherView";
    }

    @RequestMapping(value = "/{manufacturingVoucherId}/validatedVoucher", method = RequestMethod.GET)
    @Transactional(rollbackFor=Throwable.class)
    public String validateManufacturingVoucher(@PathVariable("manufacturingVoucherId") Long manufacturingVoucherId, Model uiModel) {
        ManufacturingVoucher manufacturingVoucher = ManufacturingVoucher.findManufacturingVoucher(manufacturingVoucherId);
        List<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
        if (manufacturingVoucherItems.isEmpty()) {
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Ce bon de fabrication interne est vide, Impossible de supprimer");
            populateEditForm(uiModel, manufacturingVoucher);
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        boolean validateManufacturingVoucher = manufacturingVoucherService.validateManufacturingVoucher(manufacturingVoucher);
        if(!validateManufacturingVoucher) {
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "La validation du bon de commande n'a pas ete effectuee. Contactez l'administrateur.");
            populateEditForm(uiModel, manufacturingVoucher);
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        RawMaterialOrder rawMaterialOrder= rawMaterialOrderService.generateRawMaterialOrderFromManufacturingVoucher(manufacturingVoucher);
        uiModel.addAttribute("rawMaterialOrder", rawMaterialOrder);
        populateEditForm(uiModel, manufacturingVoucher);
        return "manufacturingvouchers/manufacturingvoucherView";
    }
    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ManufacturingVoucher> manufacturingVouchers = ManufacturingVoucher.findManufacturingVouchersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (manufacturingVouchers.isEmpty()) {
        	populateEditForm(uiModel, ManufacturingVoucher.findManufacturingVoucher(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun bon de fabrication interne trouve !");
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        populateEditForm(uiModel, manufacturingVouchers.iterator().next());
        return "manufacturingvouchers/manufacturingvoucherView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getManufacturingVoucher(@PathVariable("id") Long id, Model uiModel) {
        List<ManufacturingVoucher> manufacturingVouchers = ManufacturingVoucher.findManufacturingVouchersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (manufacturingVouchers.isEmpty()) {
        	populateEditForm(uiModel, ManufacturingVoucher.findManufacturingVoucher(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune commande trouve !");
            return "manufacturingvouchers/manufacturingvoucherView";
        }
        populateEditForm(uiModel, manufacturingVouchers.iterator().next());
        return "manufacturingvouchers/manufacturingvoucherView";
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
    	Assert.notNull(manufacturingVoucher, "The manufacturing voucher should not be not null here");
        uiModel.addAttribute("manufacturingVoucher", manufacturingVoucher);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("customerorders", CustomerOrder.findCustomerOrdersByOrderState(DocumentStates.VALIDER).getResultList());
        uiModel.addAttribute("partners", Partner.findAllActiveProviders().getResultList());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
        if(manufacturingVoucher.getId() != null) {

            FindManufacturingVoucherItems processManufacturingVoucher = (FindManufacturingVoucherItems) manufacturingVoucherService.processManufacturingVoucher(manufacturingVoucher, new FindManufacturingVoucherItems());
            List<ManufacturingVoucherItem> manufacturingVoucherItems = processManufacturingVoucher.getManufacturingVoucherItems();
            List<ManufacturingItemUiModel> manufacturingItemUiModels = new ArrayList<ManufacturingItemUiModel>();
            for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
                ManufacturingItemUiModel manufacturingItemUiModel = new ManufacturingItemUiModel();
                manufacturingItemUiModel.setManufacturingVoucherItem(manufacturingVoucherItem);
                List<Caracteristic> resultList = Caracteristic.findCaracteristicsByProduct(manufacturingVoucherItem.getProduct()).getResultList();
                Caracteristic caracteristic = null;
                if (!resultList.isEmpty()) {
                    caracteristic = resultList.iterator().next();
                }
                manufacturingItemUiModel.setCaracteristic(caracteristic);
                if (caracteristic != null) {
                    Collection<Specificity> specificities = SpecificityToCaracteristicMap.findSpecificitysByCaracteristicsEquals(caracteristic).getResultList();
                    manufacturingItemUiModel.setSpecifycities(new HashSet<Specificity>(specificities));
                }
                manufacturingItemUiModels.add(manufacturingItemUiModel);
            }
            uiModel.addAttribute("manufacturingItemUiModels", manufacturingItemUiModels);
        }else {
        	uiModel.addAttribute("deliveryorigins", Arrays.asList(DeliveryOrigin.CREATED));
        }
    }
}
