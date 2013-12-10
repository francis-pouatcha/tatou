package cm.adorsys.gpao.model.uimodels;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;

import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.PartnerType;

@RooJavaBean
public class PartnerFinder implements GpaoEntityFinder<Partner> {
	
    private String name;

    private Boolean isCustomer = Boolean.TRUE;

    private Boolean isProvider  = Boolean.TRUE;
    
    private PartnerType partnerType = PartnerType.ENTREPRISE;

    private PartnerGroup partnerGroup;

	@Override
	public List<Partner> find(int page, int size) {
		return Partner.findPartnerByNameLikeAndGroupAndType(name, partnerGroup, partnerType, isCustomer, isProvider).
				setFirstResult(page).setMaxResults(size).getResultList();
	}

	@Override
	public List<Partner> find() {
	   return Partner.findPartnerByNameLikeAndGroupAndType(name, partnerGroup, partnerType, isCustomer, isProvider).getResultList();
	}




}
