/*
 * Author: Jackelyn Yii, Chase Barnts
 * This class produces a summary report for invoices. 
 */
package com.bc;

import java.util.ArrayList;

public class ReportWriter {
	
	public static void summaryReport(ArrayList<Invoice> invoice) {
		
		double subTotal = 0.0, discTotal = 0.0, feeTotal = 0.0, taxTotal = 0.0, finalTotal = 0.0;
		System.out.println("Executive Summary Report:\n");
		System.out.println("Code 	   Owner                       Customer Account    	    	        Subtotal            Discounts           Fees                Taxes               Total");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(Invoice x : invoice) {
			String code = x.getInvoiceCode();
			String lastName = x.getOwnerAccount().getLastName();
			String firstName = x.getOwnerAccount().getFirstName();
			String account = x.getCustomerAccount().getCustomerName();
			double subtotal = x.getSubTotal();
			double discounts = x.getDiscounts();
			double fees = x.getFee();
			double taxes = x.getTax();
			double total = subtotal + discounts + fees + taxes;
			subTotal += subtotal;
			discTotal += discounts;
			feeTotal += fees;
			taxTotal += taxes;
			finalTotal += total;
			System.out.printf("%-10s %-10s,%-16s %-40s $ %-17.2f $ %-17.2f $ %-17.2f $ %-17.2f $ %-17.2f \n", code, lastName, firstName, account, subtotal, discounts, fees, taxes, total);
		}
		System.out.println("=================================================================================================================================================================================");
		System.out.printf("TOTALS                                                                          $ %-17.2f $ %-17.2f $ %-17.2f $ %-17.2f $ %-17.2f\n", subTotal, discTotal, feeTotal, taxTotal, finalTotal);
	}
}