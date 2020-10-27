package com.bc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceDatabase {
	
	public static ArrayList<Invoice> loadAllInvoices() {
		
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		
		Invoice i = null;
		Product p = null;
		
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		java.sql.Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select i.invoiceId, i.invoiceCode, i.ownerAccount, i.customerAccount from Invoice i;";
		
		java.sql.PreparedStatement ps = null;
		java.sql.PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<Product> invoiceProduct = new ArrayList<>();
				int invoiceId = rs.getInt("invoiceId");
				String code = rs.getString("invoiceCode");
				int ownerAccount = rs.getInt("ownerAccount");
				int customerAccount = rs.getInt("customerAccount");
				String query2 = "select p.productId, p.value, p.associatedRepair from InvoiceProduct p where p.invoiceId = ?;";
				ps2 = connection.prepareStatement(query2);
				ps2.setInt(1, invoiceId);
				rs2 = ps2.executeQuery();		
				while(rs2.next()) {		
					int productId = rs.getInt("productId");
					double value = rs.getDouble("value");
					int associatedRepair = rs.getInt("associatedRepair");
					if(ProductDatabase.getProduct(productId).productType.equals("R")) {
						Rentals r = new Rentals((Rentals)p);
						r.setDaysRented(value);
					} else if(ProductDatabase.getProduct(productId).productType.equals("F")) {
						Repair f = new Repair((Repair)p);
						f.setHoursWorked(value);
					} else if(ProductDatabase.getProduct(productId).productType.equals("T")) {
						Towing t = new Towing((Towing)p);
						t.setMilesTowed(value);
					} else if(ProductDatabase.getProduct(productId).productType.equals("C")) {
						Concession c = new Concession((Concession)p);
						c.setQuantity(value);
						Repair associated = null;
						c.setAssociatedRepair(associatedRepair);
					}
					invoiceProduct.add(ProductDatabase.getProduct(productId));		
				}			
				i = new Invoice(code, PersonDatabase.getPerson(ownerAccount), CustomerDatabase.getCustomer(customerAccount), invoiceProduct);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return invoiceList;
	}

}
