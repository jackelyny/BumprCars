/*
 * Author: Jackelyn Yii, Chase Barnts
 * The file reader class reads in data files and assign each data to its objects
 */
package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {
	
	public ArrayList<Person> readPerson() {
		
		HashMap<String, Person> personMap = new HashMap<>();
		
		ArrayList<Person> personList = new ArrayList<>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		
		while(s.hasNextLine()) {
			ArrayList<String> emailList = new ArrayList<String>();
			String tokens[] = s.nextLine().split(";", -1);
			String code = tokens[0];
			String name[] = tokens[1].split(",");
			String lastName = name[0];
			String firstName = name[1];
			String address[] = tokens[2].split(",");
			Address addr = new Address(address[0], address[1], address[2], address[3], address[4]);	
			if(tokens.length >= 4) {
				String email[] = tokens[3].split(",");
				for(int i=0; i<email.length; i++) {
				emailList.add(email[i]);
				}
			}
			Person p = new Person(code, lastName, firstName, addr, emailList);
			personList.add(p);
			personMap.put(code, p);
		}
		s.close();
		return personList;
	}
	
	public ArrayList<Customer> readCustomer(ArrayList<Person> personList) {
		
		HashMap<String, Customer> customerMap = new HashMap<>();
		
		ArrayList<Customer> customerList = new ArrayList<>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Customers.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		
		while(s.hasNextLine()) {
			String tokens[] = s.nextLine().split(";", -1);
			String customerCode = tokens[0];
			String customerType = tokens[1];
			String customerName = tokens[2];
			String primaryContact = tokens[3];
			String address[] = tokens[4].split(",");
			Address addr = new Address(address[0], address[1], address[2], address[3], address[4]);
			Person code = null;
			for(Person x : personList) {
				if(x.getPersonCode().equals(primaryContact)) {
					code = x;
				}
			}
			Customer c = new Customer(customerCode, customerType, customerName, code, addr);
			customerList.add(c);
			customerMap.put(customerCode, c);
		}
		s.close();
		return customerList;
	}
	
	public ArrayList<Product> readProduct() {
		
		HashMap<String, Product> productMap = new HashMap<>();
		
		ArrayList<Product> productList = new ArrayList<>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Products.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		
		Product p = null;
		double unitCost = 0, costPerMile = 0, partsCost = 0, 
				hourlyCost = 0, dailyCost = 0, deposit = 0, cleaningFee = 0;
		while(s.hasNextLine()) {
			String tokens[] = s.nextLine().split(";", -1);
			String productCode = tokens[0];
			String productType = tokens[1];
			String productLabel = tokens[2];
			if(tokens.length == 4 && tokens[1].equals("C")) {
				 unitCost = Double.parseDouble(tokens[3]);
			} else if(tokens.length == 4 && tokens[1].equals("T")) {
				 costPerMile = Double.parseDouble(tokens[3]);
			} else if(tokens.length == 5) {
				 partsCost = Double.parseDouble(tokens[3]);
				 hourlyCost = Double.parseDouble(tokens[4]);
			} else if(tokens.length == 6) {
				 dailyCost = Double.parseDouble(tokens[3]);
				 deposit = Double.parseDouble(tokens[4]);
				 cleaningFee = Double.parseDouble(tokens[5]);
			}
			if(tokens[1].equals("C")) {
				 p = new Concession(productCode, productType, productLabel, unitCost);
			} else if(tokens[1].equals("T")) {
				 p = new Towing(productCode, productType, productLabel, costPerMile);
			} else if(tokens[1].equals("F")) {
				p = new Repair(productCode, productType, productLabel, partsCost, hourlyCost);
			} else if(tokens[1].equals("R")) {
				p = new Rentals(productCode, productType, productLabel, dailyCost, deposit, cleaningFee);
			}
			productList.add(p);
			productMap.put(productCode, p);
		}
		s.close();
		return productList;
	}
	
	//helped by Chloe Lenhert
	public ArrayList<Invoice> readInvoices(ArrayList<Person> personList, ArrayList<Customer> customerList, ArrayList<Product> productList) {
		
		ArrayList<Invoice> invoice = new ArrayList<>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Invoices.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		
		while(s.hasNextLine()) {
			ArrayList<Product> invoiceProduct = new ArrayList<>();
			String tokens[] = s.nextLine().split(";", -1);
			String invoiceCode = tokens[0];
			String ownerCode = tokens[1];
			String customerCode = tokens[2];
			String product[] = tokens[3].split(",");
			for(String x : product) {
				String split[] = x.split(":");
				for(Product p : productList) {
					if(p.getProductCode().equals(split[0])) {
						if(p.getProductType().equals("R")) {
							Rentals r = new Rentals((Rentals)p);
							r.setDaysRented(Double.parseDouble(split[1]));
							invoiceProduct.add(r);
						} else if(p.getProductType().equals("F")) {
							Repair f = new Repair((Repair)p);
							f.setHoursWorked(Double.parseDouble(split[1]));
							invoiceProduct.add(f);
						} else if(p.getProductType().equals("T")) {
							Towing t = new Towing((Towing)p);
							t.setMilesTowed(Double.parseDouble(split[1]));
							invoiceProduct.add(t);
						} else if(p.getProductType().equals("C")) {
							Concession c = new Concession((Concession)p);
							c.setQuantity(Double.parseDouble(split[1]));
							if(split.length == 3) {
								Repair associatedRepair = null;
								for(Product pr : invoiceProduct) {
									if(pr.getProductCode().equals(split[2])) {
										associatedRepair = (Repair) pr;
										c.setAssociatedRepair(associatedRepair);
										System.out.println(c.getAssociatedRepair());
									}
								}
							}
							invoiceProduct.add(c);
						}
					}
				}
			}
			Person owner = null;
			Customer customer = null;
			for(Person x : personList) {
				if(x.getPersonCode().equals(ownerCode)) {
					owner = x;
				}
			}
			for(Customer c : customerList) {
				if(c.getCustomerCode().equals(customerCode)) {
					customer = c;
				}
			}
			Invoice i = new Invoice(invoiceCode, owner, customer, invoiceProduct);
			invoice.add(i);
		}
		s.close();
		return invoice;
	}
}