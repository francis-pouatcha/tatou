package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findDeliveryItemsesByReferenceEquals", "findDeliveryItemsesByDelivery", "findDeliveryItemsesBySupply", "findSupplyItemsesBySupply" })
public class SupplyItems extends GpaoBaseEntity {

    private String reference;

    @NotNull
    @ManyToOne
    private Product product;

    private BigDecimal orderQte = BigDecimal.ZERO;

    private BigDecimal qteReceive = BigDecimal.ZERO;

    private BigDecimal qteUnreceive = BigDecimal.ZERO;

    private BigDecimal qteInStock = BigDecimal.ZERO;

    @NotNull
    @ManyToOne
    private Supply supply;

    private BigDecimal amountHt = BigDecimal.ZERO;

    /*  private BigDecimal taxAmount = BigDecimal.ZERO;
     private BigDecimal taxedAmount = BigDecimal.ZERO;*/
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date expirationDate;

    @ManyToOne
    private UnitOfMesures udm;

    public SupplyItems() {
    }

    public SupplyItems(OrderItems items, Supply supply) {
        this.product = items.getProduct();
        this.orderQte = items.getQuantity();
        this.expirationDate = new Date();
        this.amountHt = items.getSubTotal();
        /*this.taxAmount = items.getTaxeAmount();
         this.taxedAmount = items.getTaxedSubTotal();*/
        this.supply = supply;
        this.udm = items.getUdm();
    }

    public void calculateQteInStock() {
        qteInStock = qteReceive;
    }

    public void acceptAllOrderQte() {
        qteReceive = orderQte;
    }

    public boolean hasUnreceiveQte() {
        return qteUnreceive.intValue() > 0;
    }

    public void calculateUnreceiveQte() {
        qteUnreceive = orderQte.subtract(qteReceive);
    }

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequenceWhitoutDate(getId(), supply.getDocRef());
    }

    public static TypedQuery<cm.adorsys.gpao.model.SupplyItems> findDeliveryItemssByProductAndStockQteNotEqual(Product product, BigDecimal qteReceive) {
        EntityManager em = SupplyItems.entityManager();
        TypedQuery<SupplyItems> q = em.createQuery("SELECT o FROM SupplyItems AS o WHERE  o.product = :product AND o.qteReceive != :qteReceive ORDER BY o.id DESC ", SupplyItems.class);
        q.setParameter("product", product);
        q.setParameter("qteReceive", qteReceive);
        return q;
    }
}
