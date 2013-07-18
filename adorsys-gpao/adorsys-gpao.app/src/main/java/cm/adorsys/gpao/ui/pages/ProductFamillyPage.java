package cm.adorsys.gpao.ui.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.New;

import org.jboss.errai.ui.nav.client.local.Page;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

import cm.adorsys.gpao.ui.layouts.Template;
import cm.adorsys.gpao.ui.pages.contents.Login;
import cm.adorsys.gpao.ui.widgets.AdministrationSideBar;
import cm.adorsys.gpao.ui.widgets.Dashboard;
@ApplicationScoped
@Page
public class ProductFamillyPage extends Template {
    private static final String PAGE_INFORMATIONS ="<div class='alert alert-info'>Product Fammily page informations goes here !</div>";

    @Inject @New Dashboard  dashboard ;
    @Inject AdministrationSideBar liftmenu ;
    @PostConstruct
	void init(){
		content.add(dashboard);
		sidebar.add(liftmenu);
		mainToolBar.getToolbar().setHTML("<h3><i class='icon-home'></i> Administration</h3>");
		header.activateSelectedLink(header.getAdminLink());
		addHelpInformations(new HTMLPanel(PAGE_INFORMATIONS));
	}
    
}
