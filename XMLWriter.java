/*
 * A class that takes in ArrayList of Person, Customer and Product as
 * a .dat file, writes and output them in XML. 
 */
package com.bc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;


public class XMLWriter {
	
	static XStream xstream = new XStream();
	
	public static void printPersonToXML(ArrayList<Person> person) throws IOException {
		
		String xmlPerson = xstream.toXML(person);
		
		File f = new File("data/PersonsXMLOutput.xml");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(xmlPerson);
		w.close();
	}
	
	public static void printCustomerToXML(ArrayList<Customer> customer) throws IOException {
		
		String xmlCustomer = xstream.toXML(customer);
		
		File f = new File("data/CustomersXMLOutput.xml");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(xmlCustomer);
		w.close();
	}
	
	public static void printProductToXML(ArrayList<Product> product) throws IOException {
		
		String xmlProduct = xstream.toXML(product);
		
		File f = new File("data/ProductsXMLOutput.xml");
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		w.write(xmlProduct);
		w.close();
	}
}