package cm.adorsys.gpao.model;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
@RooJson
public class PartnerGroup extends GpaoBaseEntity {

    @NotNull
    private String name;

    private String description;

    public PartnerGroup() {
    }

    public PartnerGroup(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static void init() {
        if (PartnerGroup.countPartnerGroups() <= 0) {
            PartnerGroup partnerGroup = new PartnerGroup("Groupe par defaut");
            partnerGroup.setDescription("le groupe de partenaire par default ");
            partnerGroup.persist();
        }
    }
}
