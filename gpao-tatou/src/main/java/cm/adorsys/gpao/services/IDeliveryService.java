package cm.adorsys.gpao.services;

import java.util.Set;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.PurchaseOrder;

public interface IDeliveryService {
	public Delivery getDeliveryFromOrder(PurchaseOrder order) ;
	public Set<DeliveryItems> getDeliveryItems(PurchaseOrder order,Delivery delivery) ;
	public void calCulateDeliveryAmout(Delivery delivery) ;

}
