package cm.adorsys.gpao.model.uimodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Tenders;
@RooJavaBean
public class DeliveryFinder implements GpaoEntityFinder<Delivery>{

	private String reference;

	private DocumentStates status ;

	private DeliveryOrigin origin;

	private String docRef;

	private String receiveBy;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date beginReceivedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endReceivedDate;

	@Override
	public List<Delivery> find(int page, int size) {
		return Delivery.findDeliveryByStatusAndOriginAndReceiveDateBetween(status, reference, beginReceivedDate, endReceivedDate, docRef, receiveBy, origin).
				setFirstResult(page).setMaxResults(size).getResultList();
	}

	@Override
	public List<Delivery> find() {
		return Delivery.findDeliveryByStatusAndOriginAndReceiveDateBetween(status, reference, beginReceivedDate, endReceivedDate, docRef, receiveBy, origin).
				getResultList();
	}
	
	public List<Long> getDeliveryId() {
			ArrayList<Long> deliveryId = new ArrayList<Long>();
			List<Delivery> find = find();
			for (Delivery delivery : find) {
				deliveryId.add(delivery.getId());
			}
			return deliveryId;
	}

}
