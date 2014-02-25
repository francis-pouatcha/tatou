/**
 * 
 */
package cm.adorsys.gpao.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;

/**
 * @author bwa
 *
 */
public interface ICustomerOrderService {
	/**
	 * This method evaluate the taxed amoutHT, compute the taxes amount, and compute the taxed amount
	 * The user is responsible to merge the results.
	 * @param customerOrder
	 * @param customerOrderItems
	 */
	public CustomerOrder computeAndSetAmounts(CustomerOrder customerOrder, Collection<CustomerOrderItem> customerOrderItems);
	
	/**
	 * Compute taxes values, of a list of customerOrderItems
	 * @param taxes
	 * @param customerOrderItems
	 * @return
	 */
	public BigDecimal computeTaxes(Set<Taxe> taxes,Collection<CustomerOrderItem> customerOrderItems);
	/**
	 * @param taxe
	 * @param customerOrderItems
	 * @return
	 */
	public BigDecimal computeTaxes(Taxe taxe,Collection<CustomerOrderItem> customerOrderItems);
	/**
	 * compute the amountHt of a given list of {@link CustomerOrderItem customerOrderItems}
	 * @param customerOrderItems
	 * @return the amounHt amount : {@link BigDecimal}
	 */
	public BigDecimal computeAmountHt(Collection<CustomerOrderItem> customerOrderItems) ;
	/**
	 * compute taxes amount, from an amountHt
	 * @param taxes the customer order taxes
	 * @param amountHt the amountHt of a customer order. Issued from the amountHt of all the customer order items.
	 * @return the taxes Amount.
	 */
	public BigDecimal computeTaxesAmountOnAmountHt(Set<Taxe> taxes,	BigDecimal amountHt);
	
	public List<CustomerOrderItem> findCustomerOrderItems(CustomerOrder customerOrder);

	/**
	 * find product with name like 'name' and return a max number.
	 * @param name
	 * @param max : max result to return. return 100 result if result is negative.
	 * @return
	 */
	public List<Product> findProductByNameLike(String name, int max);

	OrderItemUimodel findSelectedProduct(Long productId);

	public void addCustomerOrderItems(CustomerOrder customerOrder,OrderItemUimodel itemUimodel);
	
	/**
	 * <p>validate a {@link CustomerOrder customer order} </p>
	 * @param customerOrder : the customer order to validate
	 * @return
	 */
	public CustomerOrder validateCustomerOrder(CustomerOrder customerOrder);

	/**
	 * @param customerOrder
	 * @param customerOrderItemsIds
	 */
	public void deleteOrderItems(CustomerOrder customerOrder, List<Long> customerOrderItemsIds);

	/**
	 * Cancel customer order
	 * @param customerOrder
	 * @return
	 */
	public CustomerOrder cancelCustomerOrder(CustomerOrder customerOrder);
	
	public boolean isBusinessOperationAllowed(CustomerOrder customerOrder,BusinessOperation businessOperation);
}
