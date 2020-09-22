/*
 * This class handles information for products. It is also a 
 * parent class to four sub classes. (Concession, Rentals, Repair,
 * and Towing)
 */
package com.bc;

public class Product {
	
	public String productCode;
	public String productType;
	public String productLabel;
	
	public Product(String productCode, String productType, String productLabel) {
		super();
		this.productCode = productCode;
		this.productType = productType;
		this.productLabel = productLabel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductLabel() {
		return productLabel;
	}

	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}
	
}