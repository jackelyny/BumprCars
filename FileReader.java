package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	public ArrayList<Person> readPerson() {
		
		ArrayList<Person> personList = new ArrayList<>();
		ArrayList<String> emailList = new ArrayList<String>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		
		while(s.hasNextLine()) {
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
		}
		s.close();
		return personList;
	}
	
	public ArrayList<Customer> readCustomer() {
		
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
			Customer c = new Customer(customerCode, customerType, customerName, primaryContact, addr);
			customerList.add(c);
		}
		s.close();
		return customerList;
	}
	
	public ArrayList<Product> readProduct() {
		
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
		}
		s.close();
		return productList;
	}
}