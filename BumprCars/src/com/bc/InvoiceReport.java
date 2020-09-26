package com.bc;

import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceReport {
	
	public static void main(String args[]) {
		
		FileReader r = new FileReader();
		
		HashMap<String, Person> person = r.readPerson();
		HashMap<String, Customer> customer = r.readCustomer();
		HashMap<String, Product> product = r.readProduct();
		
		ArrayList<Invoices> invoice = r.readInvoices(person, customer, product);
		
		ReportWriter.summaryReport(invoice);
		
	}
	
}