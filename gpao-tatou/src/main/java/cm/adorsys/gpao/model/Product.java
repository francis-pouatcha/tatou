package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.web.multipart.MultipartFile;

import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import cm.adorsys.gpao.utils.UdmUtils;

import flexjson.JSONSerializer;

/**
 * @author clovisgakam
 *
 */
@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS",finders = { "findProductsByNameLike" })
public class Product extends GpaoBaseEntity{

	@Enumerated
	private ProductType productType;

	private String reference;

	@NotNull
	@Column(unique=true)
	private String name;

	@ManyToOne
	private ProductSubFamily famille;

	@ManyToOne
	private WareHouses warehouse;

	@Value("true")
	private Boolean canBebuy;

	@Value("true")
	private Boolean canBeSale;

	@Value("0")
	@Min(0L)
	@NotNull
	private BigDecimal virtualStock;

	@Value("0")
	@Min(0L)
	private BigDecimal minStock;

	@ManyToOne
	private UnitOfMesures defaultUdm;

	@NotNull
	@ManyToOne
	private Devise defaultCurrency;

	@NotNull
	@Value("0")
	private BigDecimal purchasePrice;

	@NotNull
	@Value("0")
	private BigDecimal salePrice;

	private String epaisseur;

	private String color;

	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Taxe> saleTaxes = new HashSet<Taxe>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Taxe> purchaseTaxes = new HashSet<Taxe>();

	@Value("true")
	private Boolean actived;

	private transient MultipartFile productImage;

	private String productImagePath;

	private String codeBare;

	public String toString(){
		return name+" : "+reference;
	}

	public void increaseVirtualQuantity(BigDecimal qte){
		virtualStock = virtualStock.add(qte);
	}
	public boolean isInitialEntry(){
		return getId() == null && getVirtualStock().intValue()!=0;
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public static String toJsonArray(Collection<Product> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	@PostPersist
	public void postPersist(){
		reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.ARTICLE_SEQUENCE_PREFIX);
		name = name.toUpperCase();
	}
	@PreUpdate
	public void preUpdate(){
		name = name.toUpperCase();
	}

	public static TypedQuery<cm.adorsys.gpao.model.Product> findProductsByIdUpperThan(Long id) {
		EntityManager em = Product.entityManager();
		TypedQuery<Product> q = em.createQuery("SELECT o FROM Product AS o WHERE  o.id > :id ORDER BY o.id ", Product.class);
		q.setParameter("id", id);
		return q;
	}

	public static TypedQuery<cm.adorsys.gpao.model.Product> findProductsByIdLowerThan(Long id) {
		EntityManager em = Product.entityManager();
		TypedQuery<Product> q = em.createQuery("SELECT o FROM Product AS o WHERE  o.id < :id ORDER BY o.id DESC ", Product.class);
		q.setParameter("id", id);
		return q;
	}
	public void calculateStockQuantity(){
		virtualStock = BigDecimal.ZERO;
		List<DeliveryItems> resultList = DeliveryItems.findDeliveryItemssByProductAndStockQteNotEqual(this, BigDecimal.ZERO).getResultList();
		if(!resultList.isEmpty()){
		for (DeliveryItems deliveryItems : resultList) {
			virtualStock = virtualStock.add(UdmUtils.convert(deliveryItems.getUdm(), getDefaultUdm(), deliveryItems.getQteReceive()));
		}
		}
	}

	public static TypedQuery<cm.adorsys.gpao.model.Product> findProductsByNameLikeAndWareHouseAndProductSubFamilly(String name ,
			WareHouses wareHouses ,ProductSubFamily famille) {
		EntityManager em = Product.entityManager();
		StringBuilder query = new StringBuilder("SELECT o FROM Product AS o WHERE  o.id Is NOT NULL ");
		if(StringUtils.isNotBlank(name)){
			name = name.replace('*', '%');
			name = name+ "%";
			query.append(" AND  LOWER(o.name) LIKE LOWER(:name)");
		}
		if(wareHouses != null){
			query.append(" AND o.warehouse = :warehouse");
		}
		if(famille != null){
			query.append(" AND o.famille = :famille");
		}
		TypedQuery<Product> q = em.createQuery(query.append(" ORDER BY o.name ").toString(), Product.class);

		if(StringUtils.isNotBlank(name))q.setParameter("name", name);
		if(famille != null)q.setParameter("famille", famille);
		if(wareHouses != null)q.setParameter("warehouse", wareHouses);
		return q;
	}
	public static TypedQuery<Product> findProductsByNameLike(String name) {
		if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
		name = name.replace('*', '%');
		if (name.charAt(0) != '%') {
			name = "%" + name;
		}
		if (name.charAt(name.length() - 1) != '%') {
			name = name + "%";
		}
		EntityManager em = Product.entityManager();
		TypedQuery<Product> q = em.createQuery("SELECT o FROM Product AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Product.class);
		q.setParameter("name", name);
		return q;
	}
}
