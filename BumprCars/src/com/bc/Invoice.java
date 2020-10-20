/*
 * Author: Jackelyn Yii, Chase Barnts
 * The invoice class handles all invoice data and has an array list of 
 * products that allows easy access to functions and onjects in the product 
 * class
 */
package com.bc;

import java.util.ArrayList;

public class Invoice {
	
	private String invoiceCode;
	private Person ownerAccount;
	private Customer customerAccount;
	private ArrayList<Product> invoiceProduct;
	
	public Invoice(String invoiceCode, Person ownerAccount, Customer customerAccount, ArrayList<Product> invoiceProduct) {
		this.invoiceCode = invoiceCode;
		this.ownerAccount = ownerAccount;
		this.customerAccount = customerAccount;
		this.invoiceProduct = invoiceProduct;
	}
	
	public String getInvoiceCode() {
		return invoiceCode;
	}
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public Person getOwnerAccount() {
		return ownerAccount;
	}
	
	public void setOwnerAccount(Person ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	
	public Customer getCustomerAccount() {
		return customerAccount;
	}
	
	public void setCustomerCode(Customer customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public ArrayList<Product> getProductList() {
		return invoiceProduct;
	}
	
	public void setProductList(ArrayList<Product> invoiceProduct) {
		this.invoiceProduct = invoiceProduct;
	}
	
	public boolean getFreeTowing() { 
		boolean containsRental = false;
		boolean containsRepair = false;
		
		for(Product x : invoiceProduct) {
			if(x instanceof Rentals) {
				containsRental = true;
			}
			if(x instanceof Repair) {
				containsRepair = true;
			}
			if(containsRental && containsRepair) {
				return true;
			}
		}
		return containsRental && containsRepair;
	}
	
	public double getSubTotal() {
		double total = 0.0;
		for(Product x : invoiceProduct) {
			if(x.getProductType().equals("T") && this.getFreeTowing()) {
				Towing temp = (Towing) x;
				temp.setDiscount(true); 
				total += temp.getsubTotal();
			} else {
				total += x.getsubTotal();
			}
			
		}
		return total;
	}
	
	public double getDiscounts() {
		double disc = 0.0;
		for(Product x : invoiceProduct) {
			disc += x.getDiscount();
		}
		if(this.getCustomerAccount().getCustomerType().equals("P") && this.getCustomerAccount().getPrimaryContactCode().getEmail().size() > 1) {
			disc += -(this.getSubTotal() + this.getTax()) * 0.05;
		}
		return disc;
	}
	
	public double getTax() {
		double tax = 0.0;
		for(Product x : invoiceProduct) {
			tax += x.getDiscount();
		}
		return (this.getSubTotal() + tax) * this.getCustomerAccount().getTaxes();
	}
	
	public double getFee() {
		return this.getCustomerAccount().getFee();
	}
	
	public void identifyAccount() {
		if(this.getCustomerAccount().getCustomerType().equals("B")) {
			System.out.println(" (Business Account)");
		} else if(this.getCustomerAccount().getCustomerType().equals("P")) {
			System.out.println(" (Personal Account)");
		}
	}

	public static void detailReport(ArrayList<Invoice> invoice, ArrayList<Person> person) {
	
		System.out.println("Invoice Details:");
		System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
		for(Invoice x : invoice) {
			System.out.printf("Invoice " + x.getInvoiceCode() +"\n");
			System.out.println("------------------------------------------");
			System.out.println("Owner:");
			System.out.println("	" + x.getOwnerAccount().getLastName() + ", " +  x.getOwnerAccount().getFirstName());
			System.out.println("	" + x.getOwnerAccount().getAddress().getStreet());
			System.out.println(" 	" + x.getOwnerAccount().getAddress().getCity() + ", " + x.getOwnerAccount().getAddress().getState() + " " +  x.getOwnerAccount().getAddress().getCountry() + " " + x.getOwnerAccount().getAddress().getZip());
			System.out.println("Customer:");
			if(x.getCustomerAccount().getCustomerType().equals("B")) {
				System.out.println("	" + x.getCustomerAccount().getCustomerName() + " (Business Acount)");
			} else if(x.getCustomerAccount().getCustomerType().equals("P")) {
				System.out.println("	" + x.getCustomerAccount().getCustomerName() + " (Personal Account)");
			}
			System.out.println("	" + x.getCustomerAccount().getAddress().getStreet());
			System.out.println(" 	" + x.getCustomerAccount().getAddress().getCity() + ", " + x.getCustomerAccount().getAddress().getState() + " " + x.getCustomerAccount().getAddress().getCountry() + " " + x.getCustomerAccount().getAddress().getZip());
			System.out.println("Products:");
			System.out.println("  Code 	       Company 			   	     Subtotal 		 Discount 	     Taxes               Total");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			double subTotal = 0.0, discountTotal = 0.0, taxTotal = 0.0, Total = 0.0;
			for(Product p : x.getProductList()) {
				double tax = x.getCustomerAccount().getTaxes() * (p.getsubTotal() + p.getDiscount());
				double total = p.getsubTotal() + p.getDiscount() + tax;
				System.out.printf("  %-12s %-39s $ %-17.2f $ %-17.2f $ %-17.2f $ %-7.2f\n", p.getProductCode(), p.getProductLabel(), p.getsubTotal(), p.getDiscount(), tax, total);
				subTotal += p.getsubTotal();
				discountTotal += p.getDiscount();
				taxTotal += tax;
				Total += total;
			}	
			System.out.println("======================================================================================================================================");
			System.out.printf("ITEM TOTALS : 				 	       $ %-17.2f $ %-17.2f $ %-17.2f $ %.2f\n", subTotal, discountTotal, taxTotal, Total);
			double fee = 0.0;
			if(x.getCustomerAccount().getCustomerType().equals("P")) {
				fee = x.getDiscounts();
				System.out.printf("LOYAL CUSTOMER DISCOUNT:										  	   $ %.2f\n", fee);
			} else if(x.getCustomerAccount().getCustomerType().equals("B")) {
				fee = x.getFee();
				System.out.printf("BUSINESS ACCOUNT FEE: 												   $ %.2f\n", fee);
			} 
			double grandTotal = Total + fee;
			System.out.printf("GRAND TOTAL:     												   $ %.2f\n", grandTotal);
			System.out.println("\n\n				 THANK YOU FOR DOING BUSINESS WITH US!\n\n");
			}
		}
	}


