/*
 * Author: Jackelyn Yii, Chase Barnts
 * A subclass of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc;

public class Towing extends Product{
	
	public Double costPerMile;
	public Double milesTowed;
	private boolean freeTowing;

	public Towing(String productCode, String productType, String productLabel, Double costPerMile) {
		super(productCode, productType, productLabel);
		this.costPerMile = costPerMile;
		this.freeTowing = false;
	}
	
	public Towing(Towing old) {
		super(old.getProductCode(), old.getProductType(), old.getProductLabel());
		this.costPerMile = old.getCostPerMile();
	}

	public Double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(Double costPerMile) {
		this.costPerMile = costPerMile;
	}
	
	public Double getMilesTowed() {
		return milesTowed;
	}

	public void setMilesTowed(Double milesTowed) {
		this.milesTowed = milesTowed;
	}
	
	public double getsubTotal() {
		return this.costPerMile * this.milesTowed;
	}
	
	public void setDiscount(boolean freeTowing) {
		this.freeTowing = freeTowing;
	}
	
	@Override
	public double getDiscount() {
		if(freeTowing == true) {
			return -(this.getsubTotal());
		} else {
			return 0.0;
		}
	}


}