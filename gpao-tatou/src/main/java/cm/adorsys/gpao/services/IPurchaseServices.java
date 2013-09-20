package cm.adorsys.gpao.services;

import java.util.List;
import java.util.Set;

import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;

public interface IPurchaseServices {
	public List<Product> findProductByNameLike(String productName);
	public List<Product> findProductByNameLike(String productName,int size);
	public OrderItemUimodel findSelectedProduct(Long productId);
	public  int deleteTenderItem(Tenders tenders , Long tenderItemId) ;
	public int deleteOrderItems(PurchaseOrder purchaseOrder, Long orderItemId) ;
	public  int deleteOrderItems(PurchaseOrder purchaseOrder , List<Long> orderItemId) ;
	public  int deleteTenderItems(Tenders tenders , List<Long> tenderItemIds) ;
	public void calculatePurchaseTaxAndAmount(PurchaseOrder purchaseOrder);
	public void validatedPurchase(PurchaseOrder purchaseOrder);
	public void cancelPurchase(PurchaseOrder purchaseOrder);
	public void addOrderItems(PurchaseOrder purchaseOrder , OrderItemUimodel itemUimodel );
	public void addTenderItems(Tenders tenders , OrderItemUimodel itemUimodel );
	public void cancelTender(Tenders tenders);
	public void closeTender(Tenders tenders);
	public void restoreTender(Tenders tenders);
	public void addOderItemsFromTenders(PurchaseOrder order);
	public List<Product> findProductFormPurchaseOrder(PurchaseOrder purchaseOrder);

}
