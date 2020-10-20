/*
 * Author: Jackelyn Yii, Chase Barnts
 * This is the main class that calls in array list of 
 * person customer and product from the read class. then
 * they are passed in the invoice array and reports
 * are produced.
 */
package com.bc;

import java.util.ArrayList;


public class InvoiceReport {
	
	public static void main(String args[]) {
		
		FileReader r = new FileReader();
		
		ArrayList<Person> person = r.readPerson();
		ArrayList<Customer> customer = r.readCustomer(person);
		ArrayList<Product> product = r.readProduct();
		
		ArrayList<Invoice> invoice = r.readInvoices(person, customer, product);
	
		ReportWriter.summaryReport(invoice);
		Invoice.detailReport(invoice, person);

	}
}