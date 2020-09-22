/*
 * A subclass of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc;

public class Repair extends Product{
	
	public Double partsCost;
	public Double hourlyLaborCost;
	
	public Repair(String productCode, String productType, String productLabel, Double partsCost,
			Double hourlyLaborCost) {
		super(productCode, productType, productLabel);
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
	}

	public Double getPartsCost() {
		return partsCost;
	}

	public void setPartsCost(Double partsCost) {
		this.partsCost = partsCost;
	}

	public Double getHourlyLaborCost() {
		return hourlyLaborCost;
	}

	public void setHourlyLaborCost(Double hourlyLaborCost) {
		this.hourlyLaborCost = hourlyLaborCost;
	}
	
	
}