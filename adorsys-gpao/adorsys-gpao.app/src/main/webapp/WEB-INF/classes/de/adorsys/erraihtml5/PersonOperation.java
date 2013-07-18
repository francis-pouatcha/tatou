package de.adorsys.erraihtml5;

import java.util.List;

import de.adorsys.errai.example.api.Person;

public class PersonOperation {

	private Person savedPerson;
	private PersonOperationType personOperationType;
	private List<Person> optionalPersonList ;
	
	public PersonOperation() {
		super();
	}
	public PersonOperation(Person savedPerson,
			PersonOperationType personOperationType) {
		super();
		this.savedPerson = savedPerson;
		this.personOperationType = personOperationType;
	}
	/**
	 * @return the savedPerson
	 */
	public Person getSavedPerson() {
		return savedPerson;
	}
	/**
	 * @param savedPerson the savedPerson to set
	 */
	public void setSavedPerson(Person savedPerson) {
		this.savedPerson = savedPerson;
	}
	/**
	 * @return the personOperationType
	 */
	public PersonOperationType getPersonOperationType() {
		return personOperationType;
	}
	/**
	 * @param personOperationType the personOperationType to set
	 */
	public void setPersonOperationType(PersonOperationType personOperationType) {
		this.personOperationType = personOperationType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonOperation [savedPerson=" + savedPerson
				+ ", personOperationType=" + personOperationType + "]";
	}
	public List<Person> getOptionalPersonList() {
		return optionalPersonList;
	}
	public void setOptionalPersonList(List<Person> optionalPersonList) {
		this.optionalPersonList = optionalPersonList;
	}
	
}
