/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.TaxeType;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.services.BusinessOperation;
import cm.adorsys.gpao.services.ICustomerOrderService;
import cm.adorsys.gpao.utils.TaxeUtils;

/**
 * @author bwa
 *
 */
@Service
public class TatouCustomerOrderService implements ICustomerOrderService {

	@Override
	public CustomerOrder computeAndSetAmounts(CustomerOrder customerOrder,
			Collection<CustomerOrderItem> customerOrderItems) {
		Assert.notNull(customerOrder, "The customer order must not be null here");
		Assert.notNull(customerOrderItems, "The customer order item list must not be null here");
		Set<Taxe> taxes = customerOrder.getTaxes();
		BigDecimal taxesAmount = BigDecimal.ZERO;
		BigDecimal amountHt = computeAmountHt(customerOrderItems);
		if(taxes != null && !taxes.isEmpty()) {
			taxesAmount = computeTaxesAmountOnAmountHt(taxes, amountHt);
		}
		BigDecimal taxedAmount = amountHt.add(taxesAmount);
		customerOrder.setTaxeAmount(taxesAmount);
		customerOrder.setAmountHt(amountHt);
		customerOrder.setTotalAmount(taxedAmount);
		return customerOrder;
	}

	@Override
	public BigDecimal computeTaxesAmountOnAmountHt(Set<Taxe> taxes,	BigDecimal amountHt) {
		BigDecimal taxesAmount = BigDecimal.ZERO;
		for (Taxe taxe : taxes) {
			TaxeType taxeType = taxe.getTaxeType();
			if (TaxeType.PAR_POURCENTAGE.equals(taxeType)) {
				taxesAmount = taxesAmount.add(TaxeUtils.computeTaxeByPercentage(amountHt, taxe.getTaxeValue()));
			}else if (TaxeType.PAR_VALEUR.equals(taxeType)) {
				taxesAmount = taxesAmount.add(TaxeUtils.computeTaxeByValue(amountHt, taxe.getTaxeValue()));
			}
		}
		return taxesAmount;
	}

	@Override
	 public BigDecimal computeAmountHt(Collection<CustomerOrderItem> customerOrderItems) {
		Assert.notNull(customerOrderItems,"The list of customer order items should not be null");
		if(customerOrderItems.isEmpty()) {
			return BigDecimal.ZERO;
		}
		Iterator<CustomerOrderItem> orderItemIterator = customerOrderItems.iterator();
		BigDecimal amountHt = BigDecimal.ZERO;
		while (orderItemIterator.hasNext()) {
			CustomerOrderItem customerOrderItem = (CustomerOrderItem) orderItemIterator.next();
			amountHt = amountHt.add(customerOrderItem.getAmountHt());
		}
		return amountHt;
	}

	public BigDecimal computeTaxes(Set<Taxe> taxes,	Collection<CustomerOrderItem> customerOrderItems) {
		Assert.notEmpty(taxes, "The taxes amount should not be null here");
		if(taxes.isEmpty() || customerOrderItems == null || customerOrderItems.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal taxeValue = BigDecimal.ZERO;
		for (Taxe taxe : taxes) {
		  taxeValue = taxeValue.add(computeTaxes(taxe,customerOrderItems));
		}
		return taxeValue;
	}

	public BigDecimal computeTaxes(Taxe taxe,Collection<CustomerOrderItem> customerOrderItems) {
		Assert.notNull(taxe, "Taxes Value should not be null here");
		if(customerOrderItems == null || customerOrderItems.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal taxeValue = BigDecimal.ZERO;
		TaxeType taxeType = taxe.getTaxeType();
		if(TaxeType.PAR_POURCENTAGE.equals(taxeType)) {
			for (CustomerOrderItem customerOrderItem : customerOrderItems) {
				taxeValue = taxeValue.add(TaxeUtils.computeTaxeByPercentage(customerOrderItem.getAmountHt(), taxe.getTaxeValue()));
			}
		}else if(TaxeType.PAR_VALEUR.equals(taxeType)) {
			for (CustomerOrderItem customerOrderItem : customerOrderItems) {

				taxeValue = taxeValue.add(TaxeUtils.computeTaxeByValue(customerOrderItem.getAmountHt(), taxe.getTaxeValue()));
			}
		}else {
			throw new IllegalArgumentException("Invalid taxe type");
		}
		return taxeValue;
	}
	@Override
	public List<CustomerOrderItem> findCustomerOrderItems(
			CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer order should not be null on find customer order items");
		if(customerOrder.getId() == null) {
			return Collections.<CustomerOrderItem> emptyList();
		}
		return CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList();
	}

	@Override
	public List<Product> findProductByNameLike(String name, int max) {
		Assert.notNull(name, "the product name should not be null on find product by name like");
		if(max <0) {
			max = 100;
		}
		return Product.findProductsByNameLike(name).setMaxResults(max).getResultList();
	}

	@Override
	public OrderItemUimodel findSelectedProduct(Long productId) {
		Assert.notNull(productId, "Product Id should not be null on find selected order item.");
		Product findProduct = Product.findProduct(productId);
		if(findProduct!=null){
			OrderItemUimodel orderItemUimodel = new OrderItemUimodel(findProduct);
			return orderItemUimodel;
		}
		return new OrderItemUimodel();
	}
	@Override
	public void addCustomerOrderItems(CustomerOrder customerOrder,OrderItemUimodel itemUimodel) {
		Assert.notNull(customerOrder, "Customer Order should on add order item");
		List<CustomerOrderItem> existingSimiliarProducts= CustomerOrderItem.findCustomerOrderItemsByCustomerOrderAndProduct(customerOrder, itemUimodel.getProduct()).getResultList();
		if(!existingSimiliarProducts.isEmpty()) {
			CustomerOrderItem customerOrderItem = existingSimiliarProducts.iterator().next();
			customerOrderItem.reset(itemUimodel);
			customerOrderItem.merge();
		}else {
			CustomerOrderItem customerOrderItem = new CustomerOrderItem(customerOrder, itemUimodel);
			customerOrderItem.persist();
		}
	}

	@Override
	public CustomerOrder validateCustomerOrder(CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer Order should on validate customer order");
		customerOrder.setValidated(Boolean.TRUE);
		customerOrder.setOrderState(DocumentStates.VALIDER);
		return customerOrder;
	}

	@Override
	public void deleteOrderItems(CustomerOrder customerOrder,
			List<Long> customerOrderItemsIds) {
		Assert.notNull(customerOrder, "Customer Order should on delete order item");
		if(customerOrderItemsIds == null || customerOrderItemsIds.isEmpty()) {
			return ;
		}
		for (Long customerOrderItemId : customerOrderItemsIds) {
			CustomerOrderItem customerOrderItem = CustomerOrderItem.findCustomerOrderItem(customerOrderItemId);
			if(customerOrderItem != null) {
				customerOrderItem.remove();
			}
		}
	}

	@Override
	public CustomerOrder cancelCustomerOrder(CustomerOrder customerOrder) {
		customerOrder.setOrderState(DocumentStates.ANNULER);
		return customerOrder;
	}

	@Override
	public boolean isBusinessOperationAllowed(CustomerOrder customerOrder,BusinessOperation businessOperation) {
		if(DocumentStates.BROUILLON.equals(customerOrder.getOrderState()) || DocumentStates.OUVERT.equals(customerOrder.getOrderState()) ) {
			return true;
		}else if(DocumentStates.VALIDER.equals(customerOrder.getOrderState()) && BusinessOperation.CANCEL.equals(businessOperation)) {
			return true;
		}else if(DocumentStates.VALIDER.equals(customerOrder.getOrderState()) && BusinessOperation.LIST_DELIVERY.equals(businessOperation)) {
			return true;
		}else if(DocumentStates.VALIDER.equals(customerOrder.getOrderState()) && BusinessOperation.PRINT_PDF.equals(businessOperation)) {
			return true;
		}else if(DocumentStates.ANNULER.equals(customerOrder.getOrderState()) && BusinessOperation.PRINT_PDF.equals(businessOperation)) {
			return true;
		}else if(DocumentStates.ANNULER.equals(customerOrder.getOrderState())) {
			return false;
		}else if(DocumentStates.FERMER.equals(customerOrder.getOrderState())) {
			return false ;
		}
		return false;
	}

	@Override
	public CustomerOrder closeCustomerOrder(CustomerOrder customerOrder) {
		customerOrder.setOrderState(DocumentStates.FERMER);
		return customerOrder;
	}
}