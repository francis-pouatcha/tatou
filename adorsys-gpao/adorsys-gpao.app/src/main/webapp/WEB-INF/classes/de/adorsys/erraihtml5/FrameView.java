package de.adorsys.erraihtml5;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

import de.adorsys.errai.example.api.Person;
import de.adorsys.erraihtml5.person.SavePersonView;

@Templated
public class FrameView extends Composite {
	
	@DataField
	SimplePanel body = new SimplePanel();
	
	@Inject
	SavePersonView savePerson;
	
	@Inject
	PersonList personList;
	
	@PostConstruct
	private void displayBody() {
		body.setWidget(savePerson);
	}
	
	void onPersonSaved(@Observes PersonOperation po) {
		System.out.println("Person Saved  	"+po.toString());
		if(PersonOperationType.CREATE_SUCCESS.equals(po.getPersonOperationType())){
			List<Person> persons = new ArrayList<Person>();
			persons.add(po.getSavedPerson());
			//personList.initPersonTable(persons);
			body.setWidget(personList);
		}else {
			body.setWidget(savePerson);
		}
	}

}
