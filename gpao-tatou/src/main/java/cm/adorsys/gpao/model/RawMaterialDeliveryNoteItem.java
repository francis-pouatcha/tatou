package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote", "findRawMaterialDeliveryNoteItemsByRawMaterial" })
public class RawMaterialDeliveryNoteItem extends GpaoBaseEntity {

    /**
     * The raw material
     */
    @ManyToOne
    private Product rawMaterial;

    /**
     * ordered quantity
     */
    private BigDecimal orderedQty;

    /**
     * delivered quantity
     */
    private BigDecimal deliveredQty;

    /**
     * Undelivered quantity
     */
    private BigDecimal undeliveredQty;

    /**
     * The raw material delivery note
     */
    @ManyToOne
    private RawMaterialDeliveryNote rawMaterialDelveryNote;
}
