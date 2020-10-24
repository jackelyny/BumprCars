/*
 * Author: Jackelyn Yii
 * A subclass of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc;

public class Rentals extends Product {
	
	public Double dailyCost;
	public Double deposit;
	public Double cleaningFee;
	public Double daysRented;

	public Rentals(String productCode, String productType, String productLabel, Double dailyCost, Double deposit,
			Double cleaningFee) {
		super(productCode, productType, productLabel);
		this.dailyCost = dailyCost;
		this.deposit = deposit;
		this.cleaningFee = cleaningFee;
	}
	
	public Rentals(Rentals old) {
		super(old.getProductCode(), old.getProductType(), old.getProductLabel());
		this.dailyCost = old.getDailyCost();
		this.deposit = old.getDeposit();
		this.cleaningFee = old.getCleaningFee();
	}

	public Double getDailyCost() {
		return dailyCost;
	}

	public void setDailyCost(Double dailyCost) {
		this.dailyCost = dailyCost;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getCleaningFee() {
		return cleaningFee;
	}

	public void setCleaningFee(Double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}

	public Double getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(Double daysRented) {
		this.daysRented = daysRented;
	}
	
	public double getsubTotal() {
		return (this.dailyCost * this.daysRented) - this.deposit + this.cleaningFee;
	}
	
	public double getDiscount() {
		return 0;
	}
}