package com.bc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDatabase {
	
	public static ArrayList<Customer> loadAllCustomers() {
		
		HashMap<String, Customer> customerMap = new HashMap<>();
		
		ArrayList<Customer> customerList = new ArrayList<>();
		
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
		
		String query = "select c.customerCode, c.type, c.name, c.contact, a.street, a.city, a.state, a.zip, a.country " + 
		               "from Customer c join Address a on c.addressId = a.addressId join Person p on c.contact = p.personId;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("customerCode");
				String type = rs.getString("type");
				String name = rs.getString("name");
				int contact = rs.getInt("contact");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				Address a = new Address(street, city, state, zip, country);
				Customer c = new Customer(code, type, name, PersonDatabase.getPerson(contact), a);
				customerList.add(c);
				customerMap.put(code, c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return customerList;
	}
	
	public static Customer getCustomer(int customerId) {
		
		Customer c = null;
		
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		java.sql.Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select c.customerId, c.customerCode, c.type, c.name, c.contact, a.street, a.city, a.state, a.zip, a.country " + 
	               "from Customer c join Address a on c.addressId = a.addressId join Person p on c.contact = p.personId where c.customerId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("customerCode");
				String type = rs.getString("type");
				String name = rs.getString("name");
				int contact = rs.getInt("contact");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				Address a = new Address(street, city, state, zip, country);
				c = new Customer(code, type, name, PersonDatabase.getPerson(contact), a);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return c;
	}

}
