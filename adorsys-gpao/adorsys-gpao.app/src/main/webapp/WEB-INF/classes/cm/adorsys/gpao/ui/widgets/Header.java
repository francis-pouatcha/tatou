package cm.adorsys.gpao.ui.widgets;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Singleton;

import org.mvel2.sh.command.basic.Help;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped @Singleton
public class Header extends Composite {

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}

	public Header() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField Anchor buyLink ;
	@UiField Anchor productLink ;
	@UiField Anchor saleLink ;
	@UiField Anchor stockLink ;
	@UiField Anchor adminLink ;
	@UiField Anchor dashbordLink ;
	@UiField Anchor help ;

	
	
	public Anchor getDashbordLink() {
	    return dashbordLink;
	}

	public void setDashbordLink(Anchor dashbordLink) {
	    this.dashbordLink = dashbordLink;
	}

	public void activateSelectedLink(Anchor link){
	    deactivateSelectedLink();
	    link.getParent().addStyleName("active");
	}
	
	public void deactivateSelectedLink(){
	    List<Anchor> links = Arrays.asList(buyLink,productLink,saleLink,stockLink,adminLink);
	    for (Anchor anchor : links) {
		anchor.getParent().removeStyleName("active");
	    }
	}

	public Anchor getBuyLink() {
	    return buyLink;
	}

	public void setBuyLink(Anchor buyLink) {
	    this.buyLink = buyLink;
	}

	public Anchor getProductLink() {
	    return productLink;
	}

	public void setProductLink(Anchor productLink) {
	    this.productLink = productLink;
	}

	public Anchor getSaleLink() {
	    return saleLink;
	}

	public void setSaleLink(Anchor saleLink) {
	    this.saleLink = saleLink;
	}

	public Anchor getStockLink() {
	    return stockLink;
	}

	public void setStockLink(Anchor stockLink) {
	    this.stockLink = stockLink;
	}

	public Anchor getAdminLink() {
	    return adminLink;
	}

	public void setAdminLink(Anchor adminLink) {
	    this.adminLink = adminLink;
	}

	public Anchor getHelp() {
	    return help;
	}

	public void setHelp(Anchor help) {
	    this.help = help;
	}
	
	

}

