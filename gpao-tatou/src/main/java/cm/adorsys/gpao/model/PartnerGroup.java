package cm.adorsys.gpao.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PartnerGroup {

	@NotNull
	private String name;

	private String description;

	public PartnerGroup() {
		// TODO Auto-generated constructor stub
	}
	public PartnerGroup( String name ) {
		this.name = name ;
	}

	public static void init(){
		if(PartnerGroup.countPartnerGroups()<= 0){
			PartnerGroup partnerGroup = new PartnerGroup("Groupe par defaut");
			partnerGroup.setDescription("le groupe de partenaire par default ");
			partnerGroup.persist() ;
			
		}
	}
}
