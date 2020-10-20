/*
 * Author: Jackelyn Yii, Chase Barnts
 * A child class of Product. The constructor has a super function 
 * which calls in the constructor from the Product class. 
 */
package com.bc; 

public class Concession extends Product {
	
	public Double unitCost;
	public Double quantity;
	public Repair associatedRepair;

	public Concession(String productCode, String productType, String productLabel, Double unitCost) {
		super(productCode, productType, productLabel);
		this.unitCost = unitCost;
	}

	public Concession(Concession old) {
		super(old.getProductCode(), old.getProductType(), old.getProductLabel()); 
		this.unitCost = old.getUnitCost();	
		
	}
	
	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Repair getAssociatedRepair() {
		return associatedRepair;
	}

	public void setAssociatedRepair(Repair associatedRepair) {
		this.associatedRepair = associatedRepair;
	}
	
	public double getsubTotal() {
		return this.unitCost * this.quantity;
	}

	@Override
	public double getDiscount() {
		double disc = 0.0;
		if(!(this.associatedRepair == null)) {
			disc = -(10.0/100.0) * this.getsubTotal();
		}
		return disc;
	}

	
}