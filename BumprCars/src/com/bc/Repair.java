/*
 * Author: Jackelyn Yii, Chase Barnts
 * A subclass of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc;

public class Repair extends Product{
	
	public Double partsCost;
	public Double hourlyLaborCost;
	public Double hoursWorked;
	
	public Repair(String productCode, String productType, String productLabel, Double partsCost,
			Double hourlyLaborCost) {
		super(productCode, productType, productLabel);
		this.partsCost = partsCost;
		this.hourlyLaborCost = hourlyLaborCost;
	}
	
	public Repair(Repair old) {
		super(old.getProductCode(), old.getProductType(), old.getProductLabel());
		this.partsCost = old.getPartsCost();
		this.hourlyLaborCost = old.getHourlyLaborCost();
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
	
	public Double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	public double getsubTotal() {
		return (this.hourlyLaborCost * this.hoursWorked) + this.partsCost;
	}
	
	public double getDiscount() {
		return 0;
	}
	
}