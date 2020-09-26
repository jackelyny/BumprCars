package com.bc;

import java.util.ArrayList;

public class Invoices {
	
	private String invoiceCode;
	private Person ownerCode;
	private Customer customerCode;
	private ArrayList<Product> productList;
	
	public Invoices(String invoiceCode, Person ownerCode, Customer customerCode, ArrayList<Product> productList) {
		this.invoiceCode = invoiceCode;
		this.ownerCode = ownerCode;
		this.customerCode = customerCode;
		this.productList = productList;
	}
	
	public String getInvoiceCode() {
		return invoiceCode;
	}
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public Person getOwnerCode() {
		return ownerCode;
	}
	
	public void setOwnerCode(Person ownerCode) {
		this.ownerCode = ownerCode;
	}
	
	public Customer getCustomerCode() {
		return customerCode;
	}
	
	public void setCustomerCode(Customer customerCode) {
		this.customerCode = customerCode;
	}
	
	public ArrayList<Product> getProductList() {
		return productList;
	}
	
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
}