package cm.adorsys.gpao.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IManufacturingVoucherService;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;

@Service
public class TatouRawMeterialOrderService implements IRawMaterialOrderService{

	private static final Logger LOGGER = LoggerFactory.getLogger(TatouRawMeterialOrderService.class);
	@Autowired
	IProductService productService;
	
	@Autowired
	IManufacturingVoucherService manufacturingVoucherService;
	@Override
	public RawMaterialOrder generateRawMaterialOrderFromManufacturingVoucher(ManufacturingVoucher manufacturingVoucher) {
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
	public RawMaterialOrderItem createRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,ManufacturingVoucherItem manufacturingVoucherItem,List<ProductIntrant> productIntrants){
		Assert.notNull(rawMaterialOrder, "The raw material order should not be null here");
		Assert.notNull(manufacturingVoucherItem, "The raw material order item should not be null here");
		Assert.notEmpty(productIntrants, "The product intrant should not be null here");
		RawMaterialOrderItem rawMaterialOrderItem = new RawMaterialOrderItem();
		for (ProductIntrant productIntrant : productIntrants) {
			rawMaterialOrderItem.setProduct(productIntrant.getRawMaterial());
			rawMaterialOrderItem.setQuantity(manufacturingVoucherItem.getQuantity().multiply(productIntrant.getQuantity()));
			rawMaterialOrderItem.setUdm(productIntrant.getUdm());
		}
		rawMaterialOrderItem.setRawMaterialOrder(rawMaterialOrder);
		return rawMaterialOrderItem;
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
}
