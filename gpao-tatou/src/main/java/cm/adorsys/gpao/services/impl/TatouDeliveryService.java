package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.excepions.UnmatchUnitOfMesureGroupException;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IDeliveryService;
import cm.adorsys.gpao.utils.UdmUtils;

@Service
public class TatouDeliveryService implements IDeliveryService {

	@Override
	public Delivery getDeliveryFromOrder(PurchaseOrder order) {
		Delivery delivery = new Delivery(order);
		return delivery;
	}

	@Override
	public Set<DeliveryItems> getDeliveryItems(PurchaseOrder order,Delivery delivery) {
		List<OrderItems> orderItems = order.getOrderItems();
		Set<DeliveryItems> deliveryItems = new HashSet<DeliveryItems>();
		for (OrderItems items : orderItems) {
			DeliveryItems deliveryItems2 = new DeliveryItems(items,delivery);
			deliveryItems2.persist();
			deliveryItems.add(deliveryItems2);
		}
		return deliveryItems;
	}

	@Override
	public void calCulateDeliveryAmout(Delivery delivery) {
		Set<DeliveryItems> deliveryItems = delivery.getDeliveryItems();
		delivery.initAmount();
		for (DeliveryItems deliveryItems2 : deliveryItems) {
			delivery.increaseAmountFromDeliveryItem(deliveryItems2);
		}
	}
	@Override
	public Delivery getDeliveryFromInventory(Inventory inventory) {
		Delivery delivery = new Delivery(Company.getOwnComapny());
		delivery.setDocRef(inventory.getReference());
		delivery.setCurrency(inventory.getCurrency()) ;
		delivery.setOrigin(DeliveryOrigin.INVENTAIRE) ;
		return delivery;
	}

	@Override
	public Set<DeliveryItems> getDeliveryItems(Inventory inventory,
			Delivery delivery) {
		Set<DeliveryItems> deliveryItems = new HashSet<DeliveryItems>();
		Set<InventoryItems> inventoryItems = inventory.getInventoryItems();
		for (InventoryItems inventoryItems2 : inventoryItems) {
			DeliveryItems inventoryItem = getDeliveryItemFromInventoryItem(inventoryItems2, delivery);
			inventoryItem.persist();
			deliveryItems.add(inventoryItem);
		}
		return deliveryItems;
	}

	private DeliveryItems getDeliveryItemFromInventoryItem(InventoryItems inventoryItems , Delivery delivery){
		DeliveryItems deliveryItems = new DeliveryItems();
		deliveryItems.setProduct(inventoryItems.getProduct());
		deliveryItems.setOrderQte(inventoryItems.getRealStock());
		deliveryItems.setQteReceive(inventoryItems.getRealStock());
		deliveryItems.setQteInStock(inventoryItems.getRealStock());
		deliveryItems.setDelivery(delivery);
		deliveryItems.setAmountHt(inventoryItems.getProductPrice().multiply(BigDecimal.valueOf(inventoryItems.getRealStock().longValue())));
		deliveryItems.setTaxAmount(BigDecimal.ZERO);
		deliveryItems.setTaxedAmount(deliveryItems.getAmountHt());
		deliveryItems.setUdm(inventoryItems.getUdm());
		return deliveryItems ;
	}

	@Override
	public Delivery closeDelivery(Delivery delivery) throws UnmatchUnitOfMesureGroupException {
		if(DocumentStates.OUVERT.equals(delivery.getStatus())){
			Set<DeliveryItems> deliveryItems = delivery.getDeliveryItems();
			for (DeliveryItems deliveryItems2 : deliveryItems) {
				Product product = deliveryItems2.getProduct();
				BigDecimal convert = UdmUtils.convert(deliveryItems2.getUdm(), product.getDefaultUdm(), deliveryItems2.getQteReceive());
				product.increaseVirtualQuantity(convert);
				product.merge();
			}
			delivery.setStatus(DocumentStates.FERMER)	;
			delivery.setReceivedDate(new Date());
			delivery.setReceiveBy(SecurityUtil.getUserName());
			delivery = delivery.merge();
		}
		return delivery ;
	}
	@Override
	public Delivery accepAllDeliveryItems(Delivery delivery)  {
		if(DocumentStates.OUVERT.equals(delivery.getStatus())){
			Set<DeliveryItems> deliveryItems = delivery.getDeliveryItems();
			for (DeliveryItems deliveryItems2 : deliveryItems) {
				deliveryItems2.acceptAllOrderQte();
				deliveryItems2.merge();
			}
			delivery = delivery.merge();
		}
		return delivery ;
	}
	public  DeliveryItems getDeliveryItemFromUnreceiveQte(DeliveryItems deliveryItems){
		DeliveryItems deliveryItems2 =null;
		if(deliveryItems.hasUnreceiveQte()){
			deliveryItems2 = new DeliveryItems();
			deliveryItems2.setProduct(deliveryItems.getProduct());
			deliveryItems2.setOrderQte(deliveryItems.getQteUnreceive());
			deliveryItems2.setExpirationDate(deliveryItems.getExpirationDate());
			deliveryItems2.setAmountHt(deliveryItems.getAmountHt());
			deliveryItems2.setTaxAmount(deliveryItems.getTaxAmount());
			deliveryItems2.setTaxedAmount(deliveryItems.getTaxedAmount());
			deliveryItems2.setUdm(deliveryItems.getUdm());
		}
		return deliveryItems2 ;
	}

	public Delivery produiceDeliveryForUnReceiveArticle(Delivery delivery){
		PurchaseOrder order = PurchaseOrder.findPurchaseOrderByReferenceEquals(delivery.getDocRef()).getSingleResult();
		Delivery delivery2 = new Delivery(order);
		delivery2.persist();
		for (DeliveryItems deliveryItems : delivery.getDeliveryItems()) {
			DeliveryItems itemFromUnreceiveQte = getDeliveryItemFromUnreceiveQte(deliveryItems);
			if(itemFromUnreceiveQte!=null){
				itemFromUnreceiveQte.setDelivery(delivery2);
				itemFromUnreceiveQte.persist();
				delivery2.increaseAmountFromDeliveryItem(itemFromUnreceiveQte);
			}
		} 
		return delivery2.merge();

	}

}
