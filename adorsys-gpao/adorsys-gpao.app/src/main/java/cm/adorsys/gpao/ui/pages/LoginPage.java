package cm.adorsys.gpao.ui.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;

import org.jboss.errai.ui.nav.client.local.Page;

import cm.adorsys.gpao.ui.layouts.PublicTemplate;
import cm.adorsys.gpao.ui.pages.contents.Login;

import com.google.inject.Inject;

 
@Page(startingPage=true)
public class LoginPage  extends PublicTemplate{

    @Inject @New Login  login ;
    @PostConstruct
	void init(){
		content.add(login);
	}
    
   
    
}
