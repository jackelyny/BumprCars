package com.bc.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bc.CustomerDatabase;
import com.bc.DatabaseInfo;
import com.bc.PersonDatabase;

 /* DO NOT change or remove the import statements beneath this.
 * They are required for the webgrader to run this phase of the project.
 * These lines may be giving you an error; this is fine. 
 * These are webgrader code imports, you do not need to have these packages.
 */
//import com.bc.model.Concession;
//import com.bc.model.Invoice;
//import com.bc.model.Customer;
//import com.bc.model.Towing;
//import com.bc.model.Person;
//import com.bc.model.Product;
//import com.bc.model.Rental;
//import com.bc.model.Repair;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 * Adapted from Dr. Hasan's original version of this file
 * @author Chloe
 *
 */
public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		
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
		
		String query = "delete from InvoiceProduct;";
		String query2 = "delete from Invoice;";
		String query3 = "delete from Customer;";
		String query4 = "delete from PersonEmail;";
		String query5 = "delete from Person;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query2);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query3);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query4);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query5);
			rs = ps.executeQuery();
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
		
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		
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
		
		String addressQuery = "insert into Address (street, city, state, zip, country) values (?, ?, ?, ?, ?);";
		String personQuery = "insert into Person (personCode, lastName, firstName, addressId) values (?, ?, ?, ?);";
		PreparedStatement ps = null;
		int addressId = 0;
		
		try {
			ps = connection.prepareStatement(addressQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			addressId = keys.getInt(1);
			ps = connection.prepareStatement(personQuery);
			ps.setString(1, personCode);
			ps.setString(2, lastName);
			ps.setString(3, firstName);
			ps.setInt(4, addressId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		
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
		
		String emailQuery = "insert into PersonEmail (email, personId) values (?, ?);";
		String personQuery = "select p.personId from Person p where p.personCode = ?;";
		PreparedStatement ps = null;
		int personId = 0;
		
		try {
			ps = connection.prepareStatement(personQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, personCode);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			personId = keys.getInt(1);
			ps = connection.prepareStatement(emailQuery);
			ps.setString(1, email);
			ps.setInt(2, personId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCusomters() {
		
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
		
		String query = "delete from InvoiceProduct;";
		String query2 = "delete from Invoice;";
		String query3 = "delete from Customer;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query2);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query3);
			rs = ps.executeQuery();
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
	}
	
	/**
	 * 5. Method to add a customer record to the database with the provided data
	 * 
	 * @param customerCode
	 * @param customerType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode, String name, String street, String city, String state, String zip, String country) {
		
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
		
		String addressQuery = "insert into Address (street, city, state, zip, country) values (?, ?, ?, ?, ?);";
		String customerQuery = "insert into Customer (customerCode, type, name, contact, addressId) values (?, ?, ?, ?, ?);";
		java.sql.PreparedStatement ps = null;
		int addressId = 0;
		
		try {
			ps = connection.prepareStatement(addressQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			addressId = keys.getInt(1);
			int personId = PersonDatabase.getPersonId(primaryContactPersonCode);
			ps = connection.prepareStatement(customerQuery);
			ps.setString(1, customerCode);
			ps.setString(2, customerType);
			ps.setString(3, name);
			ps.setInt(4, personId);
			ps.setInt(5, addressId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
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
		
		String removeInvoiceProduct = "delete from InvoiceProduct;";
		String removeTowing = "delete from Towing;";
		String removeConcession = "delete from Concession;";
		String removeRepair = "delete from Repair;";
		String removeRental = "delete from Rental;";
		String removeProduct = "delete from Product;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(removeInvoiceProduct);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(removeTowing);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(removeConcession);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(removeRepair);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(removeRental);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(removeProduct);
			rs = ps.executeQuery();
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
	}

	/**
	 * 7. Adds a concession record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param unitCost
	 */
	public static void addConcession(String productCode, String productLabel, double unitCost) {

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

		String productQuery = "insert into Product (productCode, type, label) values (?, ?, ?);";
		String concessionQuery = "insert into Concession (productId, unitCost) (?, ?);";
		PreparedStatement ps = null;
		int productId = 0;
		
		try {
			ps = connection.prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setString(2, "C");
			ps.setString(3, productLabel);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			productId = keys.getInt(1);
			ps = connection.prepareStatement(concessionQuery);
			ps.setInt(1, productId);
			ps.setDouble(2, unitCost);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 8. Adds a repair record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param proudctLabel
	 * @param partsCost
	 * @param laborRate
	 */
	public static void addRepair(String productCode, String productLabel, double partsCost, double laborRate) {
		
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
		
		String productQuery = "insert into Product (productCode, type, label) values (?, ?, ?);";
		String repairQuery = "insert into Repair (productId, partsCost, hourlyLaborCost) values (?, ?, ?);";
		PreparedStatement ps = null;
		int productId = 0;
		
		try {
			ps = connection.prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setString(2, "F");
			ps.setString(3, productLabel);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			productId = keys.getInt(1);
			ps = connection.prepareStatement(repairQuery);
			ps.setInt(1, productId);
			ps.setDouble(2, partsCost);
			ps.setDouble(3, laborRate);
			ps.executeUpdate();
			} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 9. Adds a towing record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param costPerMile
	 */
	public static void addTowing(String productCode, String productLabel, double costPerMile) {

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
		
		String productQuery = "insert into Product (productCode, type, label) values (?, ?, ?);";
		String towingQuery = "insert into Towing (productId, costPerMile) values (?, ?);";
		PreparedStatement ps = null;
		int productId = 0;
		
		try {
			ps = connection.prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setString(2, "T");
			ps.setString(3, productLabel);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			productId = keys.getInt(1);
			ps = connection.prepareStatement(towingQuery);
			ps.setInt(1, productId);
			ps.setDouble(2, costPerMile);
			ps.executeUpdate();
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 10. Adds a rental record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param dailyCost
	 * @param deposit
	 * @param cleaningFee
	 */
	public static void addRental(String productCode, String productLabel, double dailyCost, double deposit, double cleaningFee) {

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
		
		String productQuery = "insert into Product (productCode, type, label) values (?, ?, ?);";
		String rentalQuery = "insert into Rental (productId, dailyCost, deposit, cleaingFee) values (?, ?, ?, ?);";
		PreparedStatement ps = null;
		int productId = 0;
		
		try {
			ps = connection.prepareStatement(productQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setString(2, "R");
			ps.setString(3, productLabel);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			productId = keys.getInt(1);
			ps = connection.prepareStatement(rentalQuery);
			ps.setInt(1, productId);
			ps.setDouble(2, dailyCost);
			ps.setDouble(3, deposit);
			ps.setDouble(4, cleaningFee);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			}
			try {
				if(ps != null && !ps.isClosed()) {
					ps.close();
				}
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		
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
		
		String query = "delete from InvoiceProduct;";
		String query2 = "delete from Invoice;";
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			ps = connection.prepareStatement(query2);
			rs = ps.executeQuery();
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
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 * 
	 * @param invoiceCode
	 * @param ownerCode
	 * @param customertCode
	 */
	public static void addInvoice(String invoiceCode, String ownerCode, String customerCode) {
		
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
		
		String query = "insert into Invoice (invoiceCode, ownerAccount, customerAccount) values (?, ?, ?);";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(query);
			int personId = PersonDatabase.getPersonId(ownerCode);
			int customerId = CustomerDatabase.getCustomerId(customerCode);
			ps.setString(1, invoiceCode);
			ps.setInt(2, personId);
			ps.setInt(3, customerId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 13. Adds a particular Towing (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of miles towed
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param milesTowed
	 */
	public static void addTowingToInvoice(String invoiceCode, String productCode, double milesTowed) {
		
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
		
		String 
	}

	/**
	 * 14. Adds a particular Repair (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of hours worked
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param hoursWorked
	 */
	public static void addRepairToInvoice(String invoiceCode, String productCode, double hoursWorked) {
		/* TODO*/
	}

     /**
      * 15. Adds a particular Concession (corresponding to <code>productCode</code> to an 
      * invoice corresponding to the provided <code>invoiceCode</code> with the given
      * number of quantity.
      * NOTE: repairCode may be null
      * 
      * @param invoiceCode
      * @param productCode
      * @param quantity
      * @param repairCode
      */
    public static void addConcessionToInvoice(String invoiceCode, String productCode, int quantity, String repairCode) {
    	/* TODO*/
    }
	
    /**
     * 16. Adds a particular Rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of days rented. 
     * 
     * @param invoiceCode
     * @param productCode
     * @param daysRented
     */
    public static void addRentalToInvoice(String invoiceCode, String productCode, double daysRented) {
    	/* TODO*/
    }

   
}
