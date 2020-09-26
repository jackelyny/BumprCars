package com.bc;

import java.util.ArrayList;

public class ReportWriter {
	
	public static void summaryReport(ArrayList<Invoices> invoice) {
		
		System.out.println("Executive Summary Report");
		System.out.println("Code Owner  Customer Account Subtotal Discounts Fees Taxes Total");
		System.out.println("-------------------------------------------------------------------------------------------");
		for(Invoices x : invoice) {
			String code = x.getInvoiceCode();
			String lastName = x.getOwnerCode().getLastName();
			String firstName = x.getOwnerCode().getFirstName();
			String account = x.getCustomerCode().getCustomerName();
			System.out.printf("%s %s, %s %s $\n", code, lastName, firstName, account);
		}
	}	
}