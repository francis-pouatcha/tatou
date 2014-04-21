package cm.adorsys.gpao.model;
import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findRawMaterialDeliveryNotesByDocRefEquals" })
public class RawMaterialDeliveryNote extends GpaoBaseEntity {

    /**
     */
    private String reference;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date orderDate;

    /**
     */
    private String createdBy;

    /**
     */
    @Value("false")
    private Boolean validated;

    /**
     */
    @Enumerated
    private DocumentStates orderState;

    /**
     */
    private String docRef;

    /**
     */
    @Enumerated
    private DeliveryOrigin origin;

    @PostPersist
    void postPersist() {
        this.reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.RAWMATERIAL_DELIVERY_NOTE_SEQUENCE_PREFIX);
    }
}
