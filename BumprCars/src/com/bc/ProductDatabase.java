package com.bc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDatabase {
	
public static Repair getRepair(int productId) {
		
		Repair r = null;
		
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
		
		String query = "select p.productCode, p.type, p.label, f.partsCost, f.hourlyLaborCost, f.hoursWorked " + 
                       "from Product p join Repair f on f.productId = p.productId where p.productId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double partsCost = rs.getDouble("partsCost");
				double hourlyLaborCost = rs.getDouble("hourlyLaborCost");
				double hoursWorked = rs.getDouble("hoursWorked");
				r = new Repair(code, type, label, partsCost, hourlyLaborCost);
				r.setHoursWorked(hoursWorked);
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
		return r;
	}
	
	public static Rentals getRental(int productId) {
		
		Rentals r = null;
		
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
		
		String query = "select p.productCode, p.type, p.label, r.dailyCost, r.deposit, r.cleaningFee, r.daysRented " +
                             "from Product p join Rental r on r.productId = p.productId where p.productId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double dailyCost = rs.getDouble("dailyCost");
				double deposit = rs.getDouble("deposit");
				double cleaningFee = rs.getDouble("cleaningFee");
				double daysRented = rs.getDouble("daysRented");
				r = new Rentals(code, type, label, dailyCost, deposit, cleaningFee);
				r.setDaysRented(daysRented);
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
		return r;
	}
	
	public static Concession getConcession(int productId) {
		
		Concession c = null;
		
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
		
		String query = "select p.productCode, p.type, p.label, c.unitCost, c.quantity, c.associatedRepair " +
                                 "from Product p join Concession c on c.productId = p.productId where p.productId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double unitCost = rs.getDouble("unitCost");
				double quantity = rs.getDouble("quantity");
				int associatedRepair = rs.getInt("associatedRepair");
				c = new Concession(code, type, label, unitCost);
				c.setQuantity(quantity);
				c.setAssociatedRepair(getRepair(associatedRepair));
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
	
	public static Towing getTowing(int productId) {
		
		Towing t = null;
		
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
		
		String query = "select p.productCode, p.type, p.label, t.costPerMile, t.milesTowed " + 
                             "from Product p join Towing t on t.productId = p.productId where p.productId = ?;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double costPerMile = rs.getDouble("costPerMile");
				double milesTowed = rs.getDouble("milesTowed");
				t = new Towing(code, type, label, costPerMile);
				t.setMilesTowed(milesTowed);
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
		return t;
	}
	
	public static ArrayList<Product> loadAllProducts() {
		
		HashMap<String, Product> productMap = new HashMap<>();
		
		ArrayList<Product> productList = new ArrayList<>();
		
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
		
		String repairQuery = "select p.productCode, p.type, p.label, f.partsCost, f.hourlyLaborCost, f.hoursWorked " + 
		                     "from Product p join Repair f on f.productId = p.productId;";
		String rentalQuery = "select p.productCode, p.type, p.label, r.dailyCost, r.deposit, r.cleaningFee, r.daysRented " +
		                     "from Product p join Rental r on r.productId = p.productId;";
		String concessionQuery = "select p.productCode, p.type, p.label, c.unitCost, c.quantity, c.associatedRepair " +
		                     "from Product p join Concession c on c.productId = p.productId;";
		String towingQuery = "select p.productCode, p.type, p.label, t.costPerMile, t.milesTowed " + 
		                     "from Product p join Towing t on t.productId = p.productId;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(repairQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double partsCost = rs.getDouble("partsCost");
				double hourlyLaborCost = rs.getDouble("hourlyLaborCost");
				double hoursWorked = rs.getDouble("hoursWorked");
				p = new Repair(code, type, label, partsCost, hourlyLaborCost);
				Repair f = new Repair((Repair)p);
				f.setHoursWorked(hoursWorked);
				productList.add(p);
				productMap.put(code, p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			ps = connection.prepareStatement(rentalQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double dailyCost = rs.getDouble("dailyCost");
				double deposit = rs.getDouble("deposit");
				double cleaningFee = rs.getDouble("cleaningFee");
				double daysRented = rs.getDouble("daysRented");
				p = new Rentals(code, type, label, dailyCost, deposit, cleaningFee);
				Rentals r = new Rentals((Rentals)p);
				r.setDaysRented(daysRented);
				productList.add(p);
				productMap.put(code, p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			ps = connection.prepareStatement(concessionQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double unitCost = rs.getDouble("unitCost");
				double quantity = rs.getDouble("quantity");
				int associatedRepair = rs.getInt("associatedRepair");
				p = new Concession(code, type, label, unitCost);
				Concession c = new Concession((Concession)p);
				c.setQuantity(quantity);
				c.setAssociatedRepair(getRepair(associatedRepair));
				productList.add(p);
				productMap.put(code, p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			ps = connection.prepareStatement(towingQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				String code = rs.getString("productCode");
				String type = rs.getString("type");
				String label = rs.getString("label");
				double costPerMile = rs.getDouble("costPerMile");
				double milesTowed = rs.getDouble("milesTowed");
				p = new Towing(code, type, label, costPerMile);
				Towing t = new Towing((Towing)p);
				t.setMilesTowed(milesTowed);
				productList.add(p);
				productMap.put(code, p);
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
		return productList;
	}
	
	
}
