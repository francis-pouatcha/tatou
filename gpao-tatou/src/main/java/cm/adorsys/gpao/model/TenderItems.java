package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import flexjson.JSONSerializer;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TenderItems {

	@ManyToOne
	private Product products;

	@Min(0L)
	private BigDecimal quantity;

	@ManyToOne
	private UnitOfMesures udm;

	@NotNull
	@ManyToOne
	private Tenders tender;

	public void reset(OrderItemUimodel itemUimodel) {
		products = Product.findProduct(itemUimodel.getProductId());
		udm = itemUimodel.getUdm();
		quantity = itemUimodel.getQuantity();
	}

	public TenderItems() {
		// TODO Auto-generated constructor stub
	}

	public TenderItems(Tenders tender,OrderItemUimodel itemUimodel) {
		products = Product.findProduct(itemUimodel.getProductId());
		udm = itemUimodel.getUdm();
		quantity = itemUimodel.getQuantity();
		this.tender = tender ;
	}
	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public static String toJsonArray(Collection<TenderItems> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
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
		TenderItems other = (TenderItems) obj;
		if (getId() == null) {
			if (other.getId() != null) return false;
		} else if (!getId().equals(other.getId())) return false;
		return true;
	}
	

	 public static TypedQuery<cm.adorsys.gpao.model.TenderItems> findTenderItemssByTenders(Tenders tender) {
	        EntityManager em = TenderItems.entityManager();
	        TypedQuery<TenderItems> q = em.createQuery("SELECT o FROM TenderItems AS o WHERE  o.tender = :tender ", TenderItems.class);
	        q.setParameter("tender", tender);
	        return q;
	    }


}
