package coursework;

//Second meter class that extends the meter class

public class BatteryMeter extends Meter{

	private Battery battery;
	
	//The BatteryMeter is connected to an instance of a Battery class which has the ability to store a utility
	
	public BatteryMeter(String utilityName, double unitCost, float meterReading) {
		super(utilityName, unitCost, meterReading);
		this.battery = new Battery();
	}
	
	@Override
	public void consumeUnits(float numberOfUnits) {
		if(numberOfUnits < 0) {
			if(!this.battery.isFull()) {
				this.battery.addUnits(-numberOfUnits);
			}
		}else if(numberOfUnits > 0) {
			if(this.battery.hasCharge()) {
				// Battery has some charge.
				double amountGotFromBattery = this.battery.getUnits(numberOfUnits);
				if(amountGotFromBattery != numberOfUnits) {
					double excessNeeds = numberOfUnits - amountGotFromBattery;
					this.meterReading += excessNeeds;
				}
			}else {
				this.meterReading += numberOfUnits;
			}
		}
	}

	//Overriding the report method in BatteryMeter so that it is capable of recording and reporting the amount of power drawn from the battery
	//Record the total amount drawn from the mains.
	
	@Override
	public double report() {
		double theCost = this.meterReading*this.unitCost;
		
		System.out.println("Utility Name : "+this.utilityName);
		System.out.println("Meter Reading : "+((this.meterReading)<0?0:this.meterReading));
		System.out.println("Cost :"+((this.meterReading)<0?0:theCost));
		System.out.println("Units in the battery : "+this.battery.getStoredUnitsCount());
		System.out.println("Total drawn units from battery : "+this.battery.getDrawnUnits());
		
		this.meterReading = 0;
		return theCost;
	}
}
