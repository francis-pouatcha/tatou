package de.adorsys.erraihtml5.person;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.api.base.TransportIOException;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.slf4j.LoggerFactory;

import com.google.gwt.http.client.Response;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;

import de.adorsys.errai.example.api.Address;
import de.adorsys.errai.example.api.Person;
import de.adorsys.errai.example.api.PersonRestResource;
import de.adorsys.erraihtml5.PersonOperation;
import de.adorsys.erraihtml5.PersonOperationType;
import de.adorsys.erraihtml5.support.Val;

@Templated
public class SavePersonView extends Composite {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SavePersonView.class);
	
	@Inject
	@DataField
	@Bound
	TextBox firstName;
	
	@Inject
	@DataField
	@Bound
	TextBox sureName;
	
	@Inject
	Val validations;
	
	@Inject
	@AutoBound
	DataBinder<Person> dataBinder;
	@Inject
	javax.enterprise.event.Event<PersonOperation> personSaved;
	
	@Inject
	Caller<PersonRestResource> personRestCaller ;
	@PostConstruct
	private void init() {
		//dataBinder.setModel(new Person());
	}
	
	@EventHandler("save")
	@SinkNative(Event.ONCLICK)
	public void doNext(Event e) {
		if (!validations.validate(dataBinder.getModel())) {
			return;
		}
		System.out.println(firstName.getValue());
		Person model = dataBinder.getModel();
		model.setAddress(new Address());
		System.out.println(model);
		
		
		RemoteCallback<Person> remoteCallback = new RemoteCallback<Person>() {

			@Override
			public void callback(Person person) {
				Window.alert("Account From REST Call :  \n \n"+person);
				LOG.info("Account From REST Server  Call: \n \n"+person);
			}
		};
		RemoteCallback<List<Person>> personListRemoteCallBack = new RemoteCallback<List<Person>>() {

			
			@Override
			public void callback(List<Person> response) {
				Window.alert("Person List From REST Server  Call    :    \n \n "+response.toString());
				LOG.info("Person List From REST Server  Call :     "+response.toString());
			}
		};
		ResponseCallback responseCallback = new ResponseCallback() {
			
			@Override
			public void callback(Response response) {
				Window.alert("Response "+response.getText());
				LOG.info("Response "+response.getText());
			}
		};

		ErrorCallback errorCallback = new ErrorCallback() {
			@Override
			public boolean error(Message message, Throwable throwable) {
				if(throwable instanceof TransportIOException){
					System.out.println("Could Reach the server : ["+message+"]  \n and ["+throwable+"]" );
				}else {
					System.out.println("Marschalling errors ["+message+"]  \n and ["+throwable+"]" );
				}
				return true;
			}
		};
		personRestCaller.call(remoteCallback, errorCallback).create(model);
		personRestCaller.call(personListRemoteCallBack,errorCallback).list();
		PersonOperation personOperation = new PersonOperation(model, PersonOperationType.CREATE_SUCCESS);
		
		personSaved.fire(personOperation);
	}
	
}
