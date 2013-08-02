package cm.adorsys.gpao.model;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProductSubFamily {

	@NotNull
	private String name;

	private String description;

	@Value("true")
	private Boolean isActive;

	@NotNull
	@ManyToOne
	private ProductFamily productFamily;

	public static void init(){
		if(ProductSubFamily.countProductSubFamilys() <= 0){
			ProductFamily.init();
			ProductSubFamily productSubFamily = new ProductSubFamily();
			productSubFamily.setName("CHAUSSURES DE SECURITEE") ;
			productSubFamily.setProductFamily(ProductFamily.findAllProductFamilys().iterator().next());
			productSubFamily.persist();
		}
	} 

	//finders

	public static TypedQuery<ProductSubFamily> findProductSubFamilyByNameLikeProductFamily(String name, ProductFamily  productFamily) {
		if (name == null || name.length() == 0) name = "*";
		name = name.replace('*', '%');
		name = name + "%";
		if (productFamily == null) throw new IllegalArgumentException("The productFamily argument is required");
		EntityManager em = ProductSubFamily.entityManager();
		TypedQuery<ProductSubFamily> q = em.createQuery("SELECT o FROM ProductSubFamily AS o WHERE LOWER(o.name) LIKE LOWER(:name)  AND o.productFamily = :productFamily ORDER BY o.name ", ProductSubFamily.class);
		q.setParameter("name", name);
		q.setParameter("productFamily", productFamily);
		return q;
	}
}
