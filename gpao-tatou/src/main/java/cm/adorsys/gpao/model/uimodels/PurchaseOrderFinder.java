package cm.adorsys.gpao.model.uimodels;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PurchaseOrder;

@RooJavaBean
@RooJson
public class PurchaseOrderFinder implements GpaoEntityFinder<PurchaseOrder> {

    private String reference;

    private String tenderRef;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date beginDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    private Partner supplier;

    private DocumentStates orderState = DocumentStates.TOUS;

    @Override
    public List<PurchaseOrder> find(int page, int size) {
        return PurchaseOrder.findPurchaseOrderByStatusAndTenderRefAndDateBetween(supplier, orderState, tenderRef, beginDate, endDate, reference).setFirstResult(page).setMaxResults(page).getResultList();
    }

    @Override
    public List<PurchaseOrder> find() {
        return PurchaseOrder.findPurchaseOrderByStatusAndTenderRefAndDateBetween(supplier, orderState, tenderRef, beginDate, endDate, reference).getResultList();
    }

    public List<Long> getPurchaseId() {
        ArrayList<Long> tenderId = new ArrayList<Long>();
        List<PurchaseOrder> find = find();
        for (PurchaseOrder purchaseOrder : find) {
            tenderId.add(purchaseOrder.getId());
        }
        return tenderId;
    }
}
