package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.excepions.InsufficientRawMaterialException;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IManufacturingVoucherService;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialDeliveryNoteService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;

@Service
public class TatouRawMaterialOrderService implements IRawMaterialOrderService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TatouRawMaterialOrderService.class);
	@Autowired
	IProductService productService;
	
	@Autowired
	IManufacturingVoucherService manufacturingVoucherService;
	@Autowired
	IRawMaterialDeliveryNoteService rawMaterialDeliveryNoteService;
	@Override
	public RawMaterialOrder generateRawMaterialOrderFromManufacturingVoucher(ManufacturingVoucher manufacturingVoucher) throws InsufficientRawMaterialException {
		Assert.notNull(manufacturingVoucher, "The manufacturing voucher should not be null here");
		List<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
		Assert.notNull(manufacturingVoucherItems, "No manufacturing voucher items found");
		if (manufacturingVoucherItems.isEmpty()) {
			LOGGER.warn("Returning null raw material");
			return null;
		}
		RawMaterialOrder rawMaterialOrder = createRawMaterialOrder(manufacturingVoucher);
		rawMaterialOrder.persist();
		for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
			List<ProductIntrant> intrants = manufacturingVoucherService.getIntrant(manufacturingVoucherItem);
			if(intrants == null || intrants.isEmpty()) {
				continue;
			}
			RawMaterialOrderItem rawMaterialOrderItem = createRawMaterialOrderItem(rawMaterialOrder, manufacturingVoucherItem, intrants);
			rawMaterialOrderItem.persist();
		}
		return rawMaterialOrder;
	}
	public RawMaterialOrder createRawMaterialOrder(ManufacturingVoucher manufacturingVoucher) {
		RawMaterialOrder rawMaterialOrder = new RawMaterialOrder();
		rawMaterialOrder.setCreatedBy(SecurityUtil.getUserName());
		rawMaterialOrder.setDelivered(false);
		rawMaterialOrder.setOrderDate(new Date());
		rawMaterialOrder.setOrderState(DocumentStates.BROUILLON);
		Assert.isTrue(StringUtils.isNotBlank(manufacturingVoucher.getReference()), "The manufacturing voucher should not be blank");
		rawMaterialOrder.setDocRef(manufacturingVoucher.getReference());
		return rawMaterialOrder;
	}
	public RawMaterialOrderItem createRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,ManufacturingVoucherItem manufacturingVoucherItem,List<ProductIntrant> productIntrants) throws InsufficientRawMaterialException{
		Assert.notNull(rawMaterialOrder, "The raw material order should not be null here");
		Assert.notNull(manufacturingVoucherItem, "The raw material order item should not be null here");
		Assert.notEmpty(productIntrants, "The product intrant should not be null here");
		RawMaterialOrderItem rawMaterialOrderItem = new RawMaterialOrderItem();
		for (ProductIntrant productIntrant : productIntrants) {
			rawMaterialOrderItem.setProduct(productIntrant.getRawMaterial());
			BigDecimal rawMaterialOrderItemQuantity = manufacturingVoucherItem.getQuantity().multiply(productIntrant.getQuantity());
			Product rawMaterial = productIntrant.getRawMaterial();
			if(!checkSufficientRawMaterial(rawMaterialOrderItemQuantity, rawMaterial)) {
				createPurchaseOrderItem(manufacturingVoucherItem,rawMaterialOrderItemQuantity, rawMaterial);
//				throw new InsufficientRawMaterialException(rawMaterialOrderItemQuantity, rawMaterial.getVirtualStock(), rawMaterial,"Impossible de traiter ce bon. Cause : Manque de matiere premiere en stock.");
				rawMaterialOrderItemQuantity = rawMaterial.getVirtualStock();
			}
//			updateProductVirtualStock(rawMaterial,rawMaterialOrderItemQuantity);
			rawMaterialOrderItem.setQuantity(rawMaterialOrderItemQuantity);
			rawMaterialOrderItem.setUdm(productIntrant.getUdm());
		}
		rawMaterialOrderItem.setRawMaterialOrder(rawMaterialOrder);
		return rawMaterialOrderItem;
	}
	private void createPurchaseOrderItem(ManufacturingVoucherItem manufacturingVoucherItem,BigDecimal rawMaterialOrderItemQuantity, Product rawMaterial) {
		BigDecimal quantityToPurchase = rawMaterialOrderItemQuantity.subtract(rawMaterial.getVirtualStock());
		List<PurchaseOrder> purchaseOrders = PurchaseOrder.findPurchaseOrdersByDocRefEquals(manufacturingVoucherItem.getManufacturingVoucher().getReference()).getResultList();
		if(purchaseOrders.isEmpty()) {
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			Set<Taxe> taxes = new HashSet<Taxe>();
			taxes.add(Taxe.findAllTaxes().iterator().next());
			purchaseOrder.setSaleTaxes(taxes);
			purchaseOrder.setCurrency(Devise.findAllDevises().iterator().next());
			purchaseOrder.setCompany(Company.findAllCompanys().iterator().next());
			purchaseOrder.setReference(manufacturingVoucherItem.getManufacturingVoucher().getReference());
			purchaseOrder.setDocRef(manufacturingVoucherItem.getManufacturingVoucher().getReference());
			purchaseOrder.setSupplier(Partner.findAllPartners().iterator().next());
			purchaseOrder.persist();
		}
		purchaseOrders = PurchaseOrder.findPurchaseOrdersByDocRefEquals(manufacturingVoucherItem.getManufacturingVoucher().getReference()).getResultList();
		PurchaseOrder purchaseOrder = purchaseOrders.iterator().next();
		OrderItems orderItems = new OrderItems();
		orderItems.setProduct(rawMaterial);
		orderItems.setPurchaseOrder(purchaseOrder);
		orderItems.setQuantity(quantityToPurchase);
		orderItems.setUdm(rawMaterial.getDefaultUdm());
		orderItems.setReference(purchaseOrders.iterator().next().getReference());
		orderItems.setSubTotal(quantityToPurchase.multiply(rawMaterial.getPurchasePrice()));
		orderItems.persist();
	}
	/**
	 * check if there are enought raw material.
	 * @param rawMaterialOrderItemQuantity
	 * @param product
	 * @return
	 */
	private boolean checkSufficientRawMaterial(BigDecimal rawMaterialOrderItemQuantity,Product product) {
		return product.getVirtualStock().compareTo(rawMaterialOrderItemQuantity) >= 0;
	}

	public boolean areThereEnoughRawMaterial(CustomerOrderItem customerOrderItem) {
		List<ProductIntrant> productIntrants = ProductIntrant.findProductIntrantsByProduct(customerOrderItem.getProduct()).getResultList();
		for (ProductIntrant productIntrant : productIntrants) {
			int compareTo = customerOrderItem.getQuantity().multiply(productIntrant.getQuantity()).compareTo(productIntrant.getRawMaterial().getVirtualStock());
			if(compareTo == 1) {
				
			}else if (compareTo == 0) {
				
			} else if(compareTo == -1){

			}
		}
		return true;
	}
	public Map<Product, BigDecimal> computeUnavailableRawMaterial(ManufacturingVoucherItem manufacturingVoucherItem){
		Product productToProduce = manufacturingVoucherItem.getProduct();
		List<ProductIntrant> intrants = ProductIntrant.findProductIntrantsByProduct(productToProduce).getResultList();
		Map<Product,BigDecimal> unAvalaibleProductsWithQuantity = new HashMap<Product, BigDecimal>();
		//compute for every product the quantity
		for (ProductIntrant productIntrant : intrants) {
			Product currentRawMaterial = productIntrant.getRawMaterial();
			BigDecimal quantityNeeded = productIntrant.getQuantity().multiply(productIntrant.getQuantity());
			BigDecimal virtualStock = currentRawMaterial.getVirtualStock();
			if(virtualStock.compareTo(quantityNeeded) < 0) {
				BigDecimal quantityToOrder = quantityNeeded.subtract(virtualStock);
				unAvalaibleProductsWithQuantity.put(currentRawMaterial, quantityToOrder);
			}
		}
		return unAvalaibleProductsWithQuantity;
	}
	@Override
	public boolean addRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,
			RawMaterialOrderItem rawMaterialOrderItem) {
		Assert.isTrue(rawMaterialOrder == null || rawMaterialOrder.getId() != null, "Invalid ram material");
		Assert.notNull(rawMaterialOrderItem, "The raw material should not be null here");
		List<RawMaterialOrderItem> existingItems = RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
		boolean ligneExist= false;
		for (RawMaterialOrderItem rawMaterialOrderItem2 : existingItems) {
			if(rawMaterialOrderItem2.getProduct().getId().equals(rawMaterialOrderItem.getProduct().getId())) {
				ligneExist = true;
				rawMaterialOrderItem2.setUdm(rawMaterialOrderItem.getUdm());
				rawMaterialOrderItem2.setQuantity(rawMaterialOrderItem.getQuantity());
				rawMaterialOrderItem2.merge();
			}
		}
		if(!ligneExist) {
			rawMaterialOrderItem.setRawMaterialOrder(rawMaterialOrder);
			rawMaterialOrderItem.persist();
		}
		return true;
	}
	@Override
	public boolean removeItems(List<Long> rawMaterialOrderItems) {
		Assert.notNull(rawMaterialOrderItems, "The raw material order items idsshould not be null");
		for (Long rawMaterialOrderItem : rawMaterialOrderItems) {
			if(rawMaterialOrderItem != null) {
				RawMaterialOrderItem.findRawMaterialOrderItem(rawMaterialOrderItem).remove();
			}
		}
		return true;
	}
	/**
	 * This method validate the raw meterial, and generate the raw material delivery note.
	 */
	@Override
	public boolean validateRawMaterialOrderAndRawMaterialGenerateDeliveryNote(RawMaterialOrder rawMaterialOrder) {
		if(rawMaterialOrder == null || rawMaterialOrder.getId() == null) {
			return false;
		}
		if(RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList().isEmpty()) {
			return false;
		}
		rawMaterialOrder.setOrderState(DocumentStates.VALIDER);
		rawMaterialOrder.setValidatedBy(SecurityUtil.getUserName());
		rawMaterialOrder.merge();
		rawMaterialDeliveryNoteService.generateRawMaterialDeliveryNoteFromRawMaterialOrder(rawMaterialOrder);
		return true;
	}
	public boolean changeState(RawMaterialOrder rawMaterialOrder,DocumentStates documentStates) {
		rawMaterialOrder.setOrderState(documentStates);
		rawMaterialOrder.merge();
		return true;
	}
}
