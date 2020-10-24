package com.bc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonDatabase {
	
	public static ArrayList<String> getEmails(int personId) {
		
		ArrayList<String> email = new ArrayList<>();
		
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
		
		String query = "select e.email from PersonEmail e " + "where e.personId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String em = rs.getString("email");
				email.add(em);
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
		return email;
	}
	
	public static ArrayList<Person> loadAllPersons() {
		
		HashMap<String, Person> personMap = new HashMap<>();
		
		ArrayList<Person> personList = new ArrayList<>();
		
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
		
		String query = "select p.personId, p.personCode, p.lastName, p.firstName, a.street, a.city, a.state, a.zip, a.country from Person p " + 
		               "join Address a on a.addressId = p.addressId;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("personId");
				String code = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				Address a = new Address(street, city, state, zip, country);
				Person p = new Person(code, lastName, firstName, a, getEmails(id));
				personList.add(p);
				personMap.put(code, p);
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
		return personList;	
	}
	
	public static Person getPerson(int personId) {
		
		Person person = null;
		
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		java.sql.Connection connection = null;
		String url = "jdbc:mysql://cse.unl.edu/jyii";
		String username = "jyii";
		String password = "";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select p.personId, p.personCode, p.lastName, p.firstName, a.street, a.city, a.state, a.zip, a.country from Person p " +
				       "join Address a on a.addressId = p.addressId where p.personId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("personId");
				String code = rs.getString("personCode");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				Address a = new Address(street, city, state, zip, country);
				person = new Person(code, lastName, firstName, a, getEmails(id));
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
		return person;
	}

}
