package cm.adorsys.gpao.model;

import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.CurrencyUtils;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord
public class Inventory {

    private String reference;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date closed;

    private String closedBy;

    @Enumerated
    private DocumentStates status = DocumentStates.OPENED;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory")
    private Set<InventoryItems> inventoryItems = new HashSet<InventoryItems>();

    @Value("0")
    private BigDecimal gapAmount;

    @ManyToOne
    private Devise currency;

    @ManyToOne
    private Company company;

    public Inventory() {
    }

    public String toString() {
        return reference;
    }

    public void calculateGapAmount() {
        gapAmount = BigDecimal.ZERO;
        for (InventoryItems items : inventoryItems) {
            gapAmount = gapAmount.add(items.getGapAmount());
        }
    }
    

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.INVENTORY_SEQUENCE_PREFIX);
        createdBy = SecurityUtil.getUserName();
    }
}
