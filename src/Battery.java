package coursework;

public class Battery {
	private float storedUnits;
	private float capacityLimit;
	private double drawnUnits;
	
	//Setting the capacity of the battery & any production that can be stored is lost
	
	public Battery() {
		this.capacityLimit = 20;
		this.storedUnits = 0;
	}
	
	public double getDrawnUnits() {
		return this.drawnUnits;
	}
	
	public double getUnits(double requiredUnits) {
		if(this.hasCharge()) {
			if(requiredUnits >= this.storedUnits) {
				// Requested unit count is equal or more than stored in the battery
				return this.drain();
			}else {
				// Requested unit count is less than stored in the battery
				this.drain(requiredUnits);
				return requiredUnits;
			}
		}else {
			return 0;
		}
	}
	
	
	//Boolean method that tells you whether battery is full
	public boolean isFull() {
		return this.storedUnits == capacityLimit;
	}
	
	//Boolean method that tells you when battery still has some charge remaining
	public boolean hasCharge() {
		return storedUnits > 0;
	}
	
	//Method that returns the amount of stored units 
	public double getStoredUnitsCount() {
		return this.storedUnits;
	}
	
	//
	public void addUnits(float units) {
		if(this.storedUnits + units <= this.capacityLimit) {
			this.storedUnits += units;
		}else {
			this.doFullCharge();
		}
	}
	
	//method that fully charges battery
	private void doFullCharge() {
		storedUnits = this.capacityLimit;
	}
	
	// This will drain whole battery
	private double drain() {
		double currentStoredUnits = this.storedUnits;
		this.drawnUnits += this.storedUnits;
		this.storedUnits = 0;
		return currentStoredUnits;
	}
	
	// This will drain specified amount of units
	private void drain(double units) {
		this.drawnUnits += units;
		this.storedUnits -= units;
	}
}