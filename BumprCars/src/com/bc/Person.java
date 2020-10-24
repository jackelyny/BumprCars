/**
 * Author: Jackelyn Yii
 * This class handles all the information that has to do with the 
 * a person. The address is handled by the Address class. The emails are
 * put in an arraylist because some lines of information of a person 
 * can contain several numbers of emails. 
 */
package com.bc;

import java.util.ArrayList;

public class Person {
	
	public Integer personId;
	public String personCode;
	private String lastName;
	private String firstName;
	private Address address;
	private ArrayList<String> email;
	
	public Person(String personCode, String lastName, String firstName, Address address, ArrayList<String> email) {
		super();
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}
	
	
}