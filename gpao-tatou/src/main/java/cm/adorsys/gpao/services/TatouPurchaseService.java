package cm.adorsys.gpao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;

@Service
public class TatouPurchaseService implements IPurchaseServices {

	@Autowired
	TatouDeliveryService deliveryService;

	@Override
	public List<Product> findProductByNameLike(String productName) {
		List<Product> productList = new ArrayList<Product>() ;
		productList = Product.findProductsByNameLike(productName).getResultList();
		return productList ;
	}

	@Override
	public List<Product> findProductByNameLike(String productName, int size) {
		List<Product> productList = new ArrayList<Product>() ;
		productList = Product.findProductsByNameLike(productName).setMaxResults(size).getResultList();
		return productList ;
	}

	@Override
	public OrderItemUimodel findSelectedProduct(Long productId) {
		Product findProduct = Product.findProduct(productId);
		if(findProduct!=null){
			OrderItemUimodel orderItemUimodel = new OrderItemUimodel(findProduct);
			return orderItemUimodel;
		}
		return new OrderItemUimodel();
	}



	@Override
	public int deleteOrderItems(PurchaseOrder purchaseOrder, Long orderItemId) {
		OrderItems orderItems = OrderItems.findOrderItems(orderItemId) ;
		if(orderItemId != null ){
			if(purchaseOrder.getOrderItems().contains(orderItems))purchaseOrder.getOrderItems().remove(orderItems) ;
			orderItems.remove();
			return 1 ;
		}
		return 0;
	}

	@Override
	public int deleteOrderItems(PurchaseOrder purchaseOrder, List<Long> orderItemIds) {
		int deleteOrderNumber = 0 ;
		for (Long orderItemId : orderItemIds) {
			deleteOrderNumber =+ deleteOrderItems(purchaseOrder, orderItemId);
		}
		return deleteOrderNumber ;
	}

	@Override
	public void calculatePurchaseTaxAndAmount(PurchaseOrder purchaseOrder) {
		Set<OrderItems> orderItems = purchaseOrder.getOrderItems();
		purchaseOrder.initAmount();
		for (OrderItems orderItems2 : orderItems) {
			orderItems2.calculateTaxAndAmout();
			purchaseOrder.increaseAmountFromOrderItem(orderItems2);
		}

	}
	@Transactional
	@Override
	public void validatedPurchase(PurchaseOrder purchaseOrder) {
		if(DocumentStates.DRAFT.equals(purchaseOrder.getOrderState())){
			Delivery delivery = deliveryService.getDeliveryFromOrder(purchaseOrder);
			delivery.persist();
			Set<DeliveryItems> deliveryItems = deliveryService.getDeliveryItems(purchaseOrder,delivery);
			delivery.setDeliveryItems(deliveryItems);
			deliveryService.calCulateDeliveryAmout(delivery);
			purchaseOrder.setOrderState(DocumentStates.VALIDATED);
			delivery.merge();
		}

	}

	@Override
	public void cancelPurchase(PurchaseOrder purchaseOrder) {
		purchaseOrder.setOrderState(DocumentStates.CANCELED);

	}


	@Override
	public void addOrderItems(PurchaseOrder purchaseOrder, OrderItemUimodel itemUimodel) {
		Product product = Product.findProduct(itemUimodel.getProductId());
		OrderItems hasProduct = purchaseOrder.hasProduct(product);
		if(hasProduct!=null) {
			hasProduct.reset(itemUimodel);
			hasProduct.merge();
		}else {
			OrderItems orderItems = new OrderItems(purchaseOrder, itemUimodel);
			orderItems.persist();
			purchaseOrder.getOrderItems().add(orderItems);
		}


	}

}
