package cm.adorsys.gpao.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.TenderItems;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IPurchaseServices;

@Service
public class TatouPurchaseService implements IPurchaseServices {

	@Autowired
	TatouSupplyService deliveryService;

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
	public List<Product> findProductFormPurchaseOrder(PurchaseOrder purchaseOrder) {
		List<Product> productList = new ArrayList<Product>() ;
		List<OrderItems> orderItems = purchaseOrder.getOrderItems();
		for (OrderItems orderItems2 : orderItems) {
			productList.add(orderItems2.getProduct());
		}
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
		List<OrderItems> orderItems = purchaseOrder.getOrderItems();
		purchaseOrder.initAmount();
		for (OrderItems orderItems2 : orderItems) {
//			orderItems2.calculateTaxAndAmout();
			purchaseOrder.increaseAmountFromOrderItem(orderItems2);
		}
		purchaseOrder.applyTaxesRateAndComputeTotalAmout();
	}
	@Override
	public void validatedPurchase(PurchaseOrder purchaseOrder) {
		if(DocumentStates.BROUILLON.equals(purchaseOrder.getOrderState())){
			Supply supply = deliveryService.getDeliveryFromOrder(purchaseOrder);
			supply.persist();
			Set<SupplyItems> supplyItems = deliveryService.getDeliveryItems(purchaseOrder,supply);
			supply.setSupplyItems(supplyItems);
			deliveryService.calCulateDeliveryAmout(supply);
			purchaseOrder.setOrderState(DocumentStates.VALIDER);
			supply.merge();
			purchaseOrder.setValidateDate(new Date());
			purchaseOrder.setValidatedBy(SecurityUtil.getUserName());
			purchaseOrder.setIsValided(Boolean.TRUE);
		}

	}

	@Override
	public void cancelPurchase(PurchaseOrder purchaseOrder) {
		purchaseOrder.setOrderState(DocumentStates.ANNULER);

	}


	@Override
	public void addOrderItems(PurchaseOrder purchaseOrder, OrderItemUimodel itemUimodel) {
		Product product = Product.findProduct(itemUimodel.getProduct().getId());
		OrderItems hasProduct = purchaseOrder.hasProduct(product);
		if(hasProduct!=null) {
			hasProduct.reset(itemUimodel);
			hasProduct.merge();
		}else {
			OrderItems orderItems = new OrderItems(purchaseOrder, itemUimodel);
			//orderItems.persist();
			purchaseOrder.getOrderItems().add(orderItems);
		}


	}

	@Override
	public int deleteTenderItem(Tenders tenders, Long tenderItemId) {
		TenderItems tenderItems = TenderItems.findTenderItems(tenderItemId) ;
		if(tenderItemId != null ){
			if(tenders.getTenderItems().contains(tenderItems))tenders.getTenderItems().remove(tenderItems) ;
			tenderItems.remove();
			return 1 ;
		}
		return 0;
	}

	@Override
	public int deleteTenderItems(Tenders tenders, List<Long> tenderItemIds) {
		int deleteOrderNumber = 0 ;
		for (Long tenderItemId : tenderItemIds) {
			deleteOrderNumber =+ deleteTenderItem(tenders, tenderItemId);
		}
		return deleteOrderNumber ;
	}

	@Override
	public void addTenderItems(Tenders tenders, OrderItemUimodel itemUimodel) {
		Product product = Product.findProduct(itemUimodel.getProduct().getId());
		TenderItems hasProduct =  tenders.hasProduct(product);
		if(hasProduct!=null) {
			hasProduct.reset(itemUimodel);
			hasProduct.merge();
		}else {
			TenderItems tenderItems = new TenderItems(tenders, itemUimodel);
			//orderItems.persist();
			tenders.getTenderItems().add(tenderItems);
		}


	}

	@Override
	public void cancelTender(Tenders tenders) {
		if(DocumentStates.OUVERT.equals(tenders.getStatus())){
			List<PurchaseOrder> resultList = PurchaseOrder.findPurchaseOrdersByTenderAndStatus(tenders, DocumentStates.VALIDER).getResultList();
			for (PurchaseOrder purchaseOrder : resultList) {
				purchaseOrder.remove();
			}
			tenders.setStatus(DocumentStates.ANNULER);
		}
	}

	@Override
	public void addOderItemsFromTenders(PurchaseOrder order) {
		if(order.hasTender() && order.getOrderItems().isEmpty()){
			Set<TenderItems> tenderItems = order.getTender().getTenderItems();
			for (TenderItems tenderItems2 : tenderItems) {
				OrderItems orderItems = new OrderItems(order, tenderItems2);
				if(order.hasProduct(orderItems.getProduct())==null){
				orderItems.persist();
				order.getOrderItems().add(orderItems);
				}
			}
		}
	}

	@Override
	public void closeTender(Tenders tenders) {
		if(DocumentStates.OUVERT.equals(tenders.getStatus())){
			List<PurchaseOrder> purchaseOrders = PurchaseOrder.findPurchaseOrdersByTender(tenders).getResultList();
			if(!purchaseOrders.isEmpty()){
				PurchaseOrder orderToValidate = purchaseOrders.get(0);
				for (PurchaseOrder purchaseOrder : purchaseOrders) {
					if(orderToValidate.getAmountHt().compareTo(purchaseOrder.getAmountHt()) == -1){
						orderToValidate = purchaseOrder;
					}else if(orderToValidate.getAmountHt().compareTo(purchaseOrder.getAmountHt()) == 1){
						/*cancelPurchase(purchaseOrder);
						purchaseOrder.merge();*/
						purchaseOrder.remove();
					}else {
					}

				}
				validatedPurchase(orderToValidate);
				orderToValidate.merge();
			}
			tenders.setStatus(DocumentStates.FERMER);
			tenders.setClosed(new Date());
			tenders.setClosedBy(SecurityUtil.getUserName());
		}

	}
	@Override
	public void closeTenderFromPurchaseOrder(Tenders tenders,PurchaseOrder orderToValidate) {
		if(DocumentStates.OUVERT.equals(tenders.getStatus())){
			List<PurchaseOrder> purchaseOrders = PurchaseOrder.findPurchaseOrdersByTender(tenders).getResultList();
			if(!purchaseOrders.isEmpty()){
				for (PurchaseOrder purchaseOrder : purchaseOrders) {
					if(!purchaseOrder.getReference().equals(orderToValidate.getReference())){
						/*cancelPurchase(purchaseOrder);
						purchaseOrder.merge().flush();*/
						purchaseOrder.remove();
					}
				}
				validatedPurchase(orderToValidate);
				orderToValidate.merge().flush();
			}
			tenders.setStatus(DocumentStates.FERMER);
			tenders.setClosed(new Date());
			tenders.setClosedBy(SecurityUtil.getUserName());
		}

	}

	@Override
	public void restoreTender(Tenders tenders) {
		if(DocumentStates.ANNULER.equals(tenders.getStatus())){
			tenders.setStatus(DocumentStates.OUVERT);
		}

	}

}
