package cm.adorsys.gpao.model.uimodels;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.PartnerType;

@RooJavaBean
@RooJson
public class PartnerFinder implements GpaoEntityFinder<Partner> {

    private String name;

    private Boolean isCustomer = Boolean.TRUE;

    private Boolean isProvider = Boolean.TRUE;

    private PartnerType partnerType = PartnerType.ENTREPRISE;

    private PartnerGroup partnerGroup;

    @Override
    public List<Partner> find(int page, int size) {
        return Partner.findPartnerByNameLikeAndGroupAndType(name, partnerGroup, partnerType, isCustomer, isProvider).setFirstResult(page).setMaxResults(size).getResultList();
    }

    @Override
    public List<Partner> find() {
        return Partner.findPartnerByNameLikeAndGroupAndType(name, partnerGroup, partnerType, isCustomer, isProvider).getResultList();
    }
}
