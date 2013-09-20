package cm.adorsys.gpao.services;

import java.util.Set;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.PurchaseOrder;

public interface IDeliveryService {
	public Delivery getDeliveryFromOrder(PurchaseOrder order) ;
	public Delivery getDeliveryFromInventory(Inventory inventory) ;
	public Set<DeliveryItems> getDeliveryItems(PurchaseOrder order,Delivery delivery) ;
	public Set<DeliveryItems> getDeliveryItems(Inventory inventory ,Delivery delivery) ;
	public Delivery closeDelivery(Delivery delivery);
	public void calCulateDeliveryAmout(Delivery delivery) ;
	public Delivery accepAllDeliveryItems(Delivery delivery) ;

	
}
