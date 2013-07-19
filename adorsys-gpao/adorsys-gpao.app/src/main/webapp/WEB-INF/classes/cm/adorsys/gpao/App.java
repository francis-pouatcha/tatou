package cm.adorsys.gpao;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

@EntryPoint
public class App {
    @Inject
	private Navigation navigation;

	@PostConstruct
	public void initApp(){
		RestClient.setApplicationRoot(UriUtils.fromString("http://localhost:8080/adorsys-gpao.server/rest").asString());
		RestClient.setJacksonMarshallingActive(true);
		RootPanel.get().add(navigation.getContentPanel());
	}
	
	@Produces @Singleton
	public Validator create() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Produces @New
	public ErrorCallback defaultErrorCallback() {
		 ErrorCallback errorCallback = new ErrorCallback() {
	        
	        @Override
	        public boolean error(Message message, Throwable throwable) {
	    	 Modal modal = new Modal(true);
	    	 modal.setCloseVisible(true);
	    	 modal.add(new HTMLPanel("<div class='alert alert-error'>"+throwable.getMessage() +"</div>"));
	    	 modal.show();
	    	return false;
	        }
	    };
		return errorCallback ;
	}
	
	 

}
