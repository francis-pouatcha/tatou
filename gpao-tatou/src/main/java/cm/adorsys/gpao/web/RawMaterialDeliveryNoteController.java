package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialDeliveryNoteService;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/rawmaterialdeliverynotes")
@Controller
@RooWebScaffold(path = "rawmaterialdeliverynotes", formBackingObject = RawMaterialDeliveryNote.class)
public class RawMaterialDeliveryNoteController extends AbstractOrderController{

	@Autowired
	SecurityUtil securityUtil;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IRawMaterialDeliveryNoteService  rawMaterialDeliveryNoteService;
    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditRawMaterialDeliveryNoteForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
    	RawMaterialDeliveryNote rawMaterialDeliveryNote = null;
        if (id == null) {
            rawMaterialDeliveryNote = new RawMaterialDeliveryNote();
            rawMaterialDeliveryNote.setOrderDate(new Date());
            rawMaterialDeliveryNote.setCreatedBy(SecurityUtil.getUserName());
        } else {
            rawMaterialDeliveryNote = RawMaterialDeliveryNote.findRawMaterialDeliveryNote(id);
        }
        populateEditForm(uiModel, rawMaterialDeliveryNote);
        return "rawmaterialdeliverynotes/rawmaterialdeliverynotesView";
    }
    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditRawMaterialDeliveryNote(@Valid RawMaterialDeliveryNote rawMaterialDeliveryNote, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, rawMaterialDeliveryNote);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "rawmaterialdeliverynotes/rawmaterialdeliverynotesView";
        }
        if (rawMaterialDeliveryNote.getId() == null) {
        	rawMaterialDeliveryNote.setOrderState(DocumentStates.BROUILLON);
        	rawMaterialDeliveryNote.setOrigin(DeliveryOrigin.CREATED);
        	rawMaterialDeliveryNote.persist();
        }
        rawMaterialDeliveryNote = doAConsistantMerge(rawMaterialDeliveryNote);
        populateEditForm(uiModel, rawMaterialDeliveryNote);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "rawmaterialdeliverynotes/rawmaterialdeliverynotesView";
    }

    private RawMaterialDeliveryNote doAConsistantMerge(RawMaterialDeliveryNote rawMaterialDeliveryNote) {
        try {
        	rawMaterialDeliveryNote.merge();
        } catch (Exception e) {
        	rawMaterialDeliveryNote.setVersion(CustomerOrder.findCustomerOrder(rawMaterialDeliveryNote.getId()).getVersion());
        	rawMaterialDeliveryNote = rawMaterialDeliveryNote.merge();
        }
        return rawMaterialDeliveryNote;
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

    
    @RequestMapping(value = "/{rawMaterialDeliveryNoteId}/addRawMaterialDeliveryItem", method = RequestMethod.GET, params = { "productId" })
    @ResponseBody
    public String addOrderItem(@PathVariable("rawMaterialDeliveryNoteId") Long rawMaterialDeliveryNoteId, RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem, @RequestParam("productId") Long productId, Model uiModel) {
        RawMaterialDeliveryNote rawMaterialDeliveryNote = RawMaterialDeliveryNote.findRawMaterialDeliveryNote(rawMaterialDeliveryNoteId);
        rawMaterialDeliveryNoteItem.setRawMaterial(Product.findProduct(productId));
        rawMaterialDeliveryNoteService.addRawMaterialDeliveryNoteItem(rawMaterialDeliveryNote, rawMaterialDeliveryNoteItem);
        return RawMaterialDeliveryNoteItem.toJsonArray(RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(rawMaterialDeliveryNote).getResultList());
    }

    
    @RequestMapping(value = "/{rawMaterialDeliveryNoteId}/removeItem", method = RequestMethod.GET)
    public String removeOrderItems(@PathVariable("rawMaterialDeliveryNoteId") Long rawMaterialDeliveryNoteId, @RequestParam("itemid") Long[] orderItemIds, Model uiModel) {
    	RawMaterialDeliveryNote rawMaterialDeliveryNote = RawMaterialDeliveryNote.findRawMaterialDeliveryNote(rawMaterialDeliveryNoteId);
        boolean removeItems = rawMaterialDeliveryNoteService.removeItems(Arrays.asList(orderItemIds));
        if (!removeItems) {
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Nothing deleted!");
            populateEditForm(uiModel, rawMaterialDeliveryNote);
        }
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "lignes supprimee avec success !");
        populateEditForm(uiModel, rawMaterialDeliveryNote);
        return "rawmaterialdeliverynotes/rawmaterialdeliverynotesView";
    }

    void populateEditForm(Model uiModel, RawMaterialDeliveryNote rawMaterialDeliveryNote) {
        uiModel.addAttribute("rawMaterialDeliveryNote", rawMaterialDeliveryNote);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        if(rawMaterialDeliveryNote.getId() != null) {
        	uiModel.addAttribute("rawMaterialDeliveryNoteItems", RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(rawMaterialDeliveryNote).getResultList());
        }
    }
}