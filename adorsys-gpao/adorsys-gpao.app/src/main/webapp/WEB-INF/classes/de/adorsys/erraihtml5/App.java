package de.adorsys.erraihtml5;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

@EntryPoint
public class App {
	
	@Inject
	FrameView bodyView;
	
	@PostConstruct
	public void initApp(){
		RestClient.setApplicationRoot(UriUtils.fromString("http://localhost:8080/errai-example.server/rest").asString());
		RestClient.setJacksonMarshallingActive(true);
		RootPanel.get().add(bodyView);
	}
	
	@Produces @Singleton
	public Validator create() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}

}
