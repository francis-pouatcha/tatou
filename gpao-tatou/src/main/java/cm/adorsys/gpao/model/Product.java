package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Product {

    @Enumerated
    private ProductType productType;

    @NotNull
    private String reference;

    @NotNull
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
    private BigInteger virtualStock;

    @Value("0")
    @Min(0L)
    private BigInteger minStock;

    @ManyToOne
    private UnitOfMesures defaultUdm;

    @NotNull
    @ManyToOne
    private Devise defaultCurrency;

    @Value("0")
    private BigDecimal purchasePrice;

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

    @Transient
    private MultipartFile productImage;

    private String productImagePath;

    private String codeBare;

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
}
