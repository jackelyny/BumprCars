/*
 * Author: Jackelyn Yii
 * This class has all the information about the customers. 
 * Similarly to the Person class, the address is handled 
 * by the Address class. 
 */
package com.bc;

public class Customer {
	
	public String customerCode;
	private String customerType;
	private String customerName;
	private Person primaryContactCode;
	private Address address;
	
	public Customer(String customerCode, String customerType, String customerName, Person primaryContactCode,
			Address address) {
		super();
		this.customerCode = customerCode;
		this.customerType = customerType;
		this.customerName = customerName;
		this.primaryContactCode = primaryContactCode;
		this.address = address;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Person getPrimaryContactCode() {
		return primaryContactCode;
	}

	public void setPrimaryContactCode(Person primaryContactCode) {
		this.primaryContactCode = primaryContactCode;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public double getTaxes() {
		double tax = 0.0;
		if(getCustomerType().equals("B")) {
			tax = ((4.25 / 100));
		} else if(getCustomerType().equals("P")) {
			tax = ((8.0 / 100));
		}
		return tax;
	}
	
	public double getFee() {
		double fee = 0.0;
		if(getCustomerType().equals("B")) {
			fee = 75.50;
		}
		return fee;
	}
}