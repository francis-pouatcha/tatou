package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.web.ProductController;

public interface IPurchaseServices {
	public List<Product> findProductByNameLike(String productName);
	public List<Product> findProductByNameLike(String productName,int size);
	public OrderItemUimodel findSelectedProduct(Long productId);
	public  int deleteOrderItems(PurchaseOrder purchaseOrder , Long orderItemId) ;
	public  int deleteOrderItems(PurchaseOrder purchaseOrder , List<Long> orderItemId) ;
	public void calculatePurchaseTaxAndAmount(PurchaseOrder purchaseOrder);
	public void validatedPurchase(PurchaseOrder purchaseOrder);
	public void cancelPurchase(PurchaseOrder purchaseOrder);
	public void addOrderItems(PurchaseOrder purchaseOrder , OrderItemUimodel itemUimodel );

}
