package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.utils.CurrencyUtils;
import flexjson.JSONSerializer;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class OrderItems extends GpaoBaseEntity{

	@NotNull
	private String reference;

	@NotNull
	@ManyToOne
	private Product product;

	@ManyToOne
	private UnitOfMesures udm;

	private BigDecimal quantity = BigDecimal.ONE;;

	private BigDecimal subTotal;
	
	private BigDecimal taxedSubTotal;

	private BigDecimal taxeAmount;

	@NotNull
	@ManyToOne
	private PurchaseOrder purchaseOrder;

	public OrderItems() {
		// TODO Auto-generated constructor stub
	}
	
	  public String toJson() {
	        return new JSONSerializer().exclude("*.class").serialize(this);
	    }
	    
	    public static String toJsonArray(Collection<OrderItems> collection) {
	        return new JSONSerializer().exclude("*.class").serialize(collection);
	    }
	public OrderItems(PurchaseOrder order,OrderItemUimodel itemUimodel) {
		product = Product.findProduct(itemUimodel.getProduct().getId());
		udm = itemUimodel.getUdm();
		quantity = itemUimodel.getQuantity();
		purchaseOrder = order ;
		reference = ""+order.getOrderItems().size()+1;
		subTotal = itemUimodel.getUnitPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
		subTotal = CurrencyUtils.convertAmount(product.getDefaultCurrency(), order.getCurrency(), subTotal);
	}
	public OrderItems(PurchaseOrder order,TenderItems tenderItems) {
		product = tenderItems.getProducts();
		udm = tenderItems.getUdm();
		quantity = tenderItems.getQuantity();
		purchaseOrder = order ;
		reference = ""+order.getOrderItems().size()+1;
		subTotal = product.getPurchasePrice().multiply(BigDecimal.valueOf(quantity.longValue()));
		subTotal = CurrencyUtils.convertAmount(product.getDefaultCurrency(), order.getCurrency(), subTotal);
	}
	
	public void reset(OrderItemUimodel itemUimodel) {
		if(purchaseOrder.getTender()!=null){
			subTotal = itemUimodel.getUnitPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
			//subTotal = CurrencyUtils.convertAmount(product.getDefaultCurrency(), getPurchaseOrder().getCurrency(), subTotal);
		}else {
			product = Product.findProduct(itemUimodel.getProduct().getId());
			udm = itemUimodel.getUdm();
			quantity = itemUimodel.getQuantity();
			subTotal = itemUimodel.getUnitPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
			//subTotal = CurrencyUtils.convertAmount(product.getDefaultCurrency(), getPurchaseOrder().getCurrency(), subTotal);
		}
	}

	public void calculateTaxAndAmout(){
		taxeAmount = BigDecimal.ZERO ;
		taxedSubTotal = BigDecimal.ZERO ;
		Set<Taxe> saleTaxes = product.getPurchaseTaxes();
		for (Taxe taxe : saleTaxes) {
             taxeAmount = taxeAmount.add(taxe.getTaxeFromAmount(subTotal));
		}
		 taxedSubTotal = subTotal.add(taxeAmount);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		OrderItems other = (OrderItems) obj;
		if (getId() == null) {
			if (other.getId() != null) return false;
		} else if (!getId().equals(other.getId())) return false;
		return true;
	}
	 public static TypedQuery<cm.adorsys.gpao.model.OrderItems> findOrderItemssByPurchaseOrder(PurchaseOrder purchaseOrder) {
	        EntityManager em = OrderItems.entityManager();
	        TypedQuery<OrderItems> q = em.createQuery("SELECT o FROM OrderItems AS o WHERE  o.purchaseOrder = :purchaseOrder ", OrderItems.class);
	        q.setParameter("purchaseOrder", purchaseOrder);
	        return q;
	    }

}
