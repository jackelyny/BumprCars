package com.bc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDatabase {
	
	public static ArrayList<Invoice> loadAllInvoices() {
		
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		
		Invoice i = null;
		
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
		
		String query = "select i.invoiceCode, i.ownerAccount, i.customerAccount, p.productId " + 
		               "from Invoice i join InvoiceProduct p on p.invoiceId = i.invoiceId;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("invoiceCode");
				int ownerAccount = rs.getInt("ownerAccount");
				int customerAccount = rs.getInt("customerAccount");
				int productId = rs.getInt("productId");
				i = new Invoice(code, PersonDatabase.getPerson(ownerAccount), CustomerDatabase.getCustomer(customerAccount), ProductDatabase.loadAllProducts(productId));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return invoiceList;
	}

}
