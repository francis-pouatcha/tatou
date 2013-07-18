package cm.adorsys.gpao.ui.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.ui.nav.client.local.Page;

import cm.adorsys.gpao.ui.layouts.Template;
import cm.adorsys.gpao.ui.widgets.AdministrationSideBar;
import cm.adorsys.gpao.ui.widgets.Dashboard;
import cm.adorsys.gpao.ui.widgets.DashbordSideBar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

@ApplicationScoped
@Page
public class DashBordPage extends Template{
    
    @Inject Dashboard  dashboard ;
    @Inject DashbordSideBar leftMenu ;
    
	@PostConstruct
	void init(){
		content.add(dashboard);
		sidebar.add(leftMenu);
		header.activateSelectedLink(header.getDashbordLink());
		addHelpInformations(new HTMLPanel(PAGE_INFORMATIONS));
	}
private static final String PAGE_INFORMATIONS ="<div class='alert alert-info'>Dashbord page informations goes here !</div>";

        
}
