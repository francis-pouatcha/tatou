package cm.adorsys.gpao.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    @NotNull
    private String name;

    private String taxePayerNumber;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String webSite;

    private String logoPath;

    @NotNull
    @ManyToOne
    private Devise devise;
}
