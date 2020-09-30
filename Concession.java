/*
 * A child class of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc; 

public class Concession extends Product {
	
	public Double unitCost;

	public Concession(String productCode, String productType, String productLabel, Double unitCost) {
		super(productCode, productType, productLabel);
		this.unitCost = unitCost;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	
	
}