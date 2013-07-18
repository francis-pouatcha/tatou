package cm.adorsys.gpao;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import com.google.gwt.safehtml.shared.UriUtils;
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
	
	 

}
