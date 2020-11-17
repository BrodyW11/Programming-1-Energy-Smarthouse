package coursework;

//Defining the type of utility, the cost of one unit of this utility
//Representation of balance of units

public class Meter {
	protected String utilityName;
	protected double unitCost;
	protected float meterReading;
	
	//Constructor that initialises the 3 values
	public Meter(String utilityName, double unitCost, float meterReading) {
		this.utilityName = utilityName;
		this.unitCost = unitCost;
		this.meterReading = meterReading;
	}
	
	//Method that takes number of units as a parameter & updates meter reading
	public void consumeUnits(float numberOfUnits) {
		this.meterReading += numberOfUnits;
	}
	
	//Method that reports the amount of units of utility used and the subsequent cost
	///Print the utility used & the cost associated with that meter reading
	//Return meter reading to 0 & return calculated cost
	public double report() {
		double theCost = this.meterReading*this.unitCost;
		
		System.out.println("Utility Name : "+this.utilityName);
		System.out.println("Meter Reading : "+this.meterReading);
		System.out.println("Cost : "+theCost);
		
		this.meterReading = 0;
		return theCost;
	}
}


