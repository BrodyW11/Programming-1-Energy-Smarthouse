package coursework;

//Inherits from Appliance
//Properties for amount of units consumed and for how long it is active in a 24 hour period
public class CyclicFixed extends Appliance{
	private float consumptionPerHour;
	private float activeTime;
	
	
	//Constructor that initialises the properties & checks number of hours active is 1-24 
	public CyclicFixed(String name, float consumptionPerHour, int activeTime) {
		super(name);
		
		this.consumptionPerHour = consumptionPerHour;
		this.activeTime = activeTime;
		
		}
	
	//Overriding timePasses from Appliance
	//Calculates number of units the appliance has used in this time period
	@Override
	public void timePasses(int timeStep) {
		if (timeStep%24 < activeTime)
			super.tellMeterToConsumeUnits(this.consumptionPerHour);
	}
}
