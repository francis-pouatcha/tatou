package cm.adorsys.gpao.ui.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;

import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShowing;

import com.google.inject.Inject;

import cm.adorsys.gpao.controllers.LoginController;
import cm.adorsys.gpao.ui.layouts.PublicTemplate;
import cm.adorsys.gpao.ui.pages.contents.Login;
import cm.adorsys.gpao.ui.widgets.Dashboard;

 
@Page(startingPage=true)
public class LoginPage  extends PublicTemplate{

    @Inject @New Login  login ;
    @PostConstruct
	void init(){
		content.add(login);
	}
    
   
    
}
