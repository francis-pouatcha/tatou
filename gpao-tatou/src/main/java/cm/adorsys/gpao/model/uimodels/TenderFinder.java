package cm.adorsys.gpao.model.uimodels;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Tenders;

@RooJavaBean
@RooJson
public class TenderFinder implements GpaoEntityFinder<Tenders> {

    private String reference;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date beginDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private DocumentStates status = DocumentStates.TOUS;

    @Override
    public List<Tenders> find(int page, int size) {
        return Tenders.findTenderByStatusAndDateBetween(status, beginDate, endDate, reference).setFirstResult(page).setMaxResults(size).getResultList();
    }

    @Override
    public List<Tenders> find() {
        return Tenders.findTenderByStatusAndDateBetween(status, beginDate, endDate, reference).getResultList();
    }

    public List<Long> getTenderId() {
        ArrayList<Long> tenderId = new ArrayList<Long>();
        List<Tenders> find = find();
        for (Tenders tenders : find) {
            tenderId.add(tenders.getId());
        }
        return tenderId;
    }
}
