/*
 * A class that takes in ArrayList of Person, Customer and Product and
 * as a .dat file, converts it to JSON and outputs them. 
 */
package com.bc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONWriter {
	
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static void printPersonToJson(ArrayList<Person> person) throws IOException {
		
		String gsonPerson = gson.toJson(person);
		
		File f = new File("data/PersonsJSONOutput.json");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(gsonPerson);
		w.close();
	}
	
	public static void printCustomerToJson(ArrayList<Customer> customer) throws IOException {
		
		String gsonCustomer = gson.toJson(customer);
		
		File f = new File("data/CustomersJSONOutput.json");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(gsonCustomer);
		w.close();
	}
	
	public static void printProductToJson(ArrayList<Product> product) throws IOException {
		
		String gsonProduct = gson.toJson(product);
		
		File f = new File("data/ProductsJSONOutput.json");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(gsonProduct);
		w.close();
	}
	
}