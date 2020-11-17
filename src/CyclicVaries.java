package coursework;

import java.util.Random;


public class CyclicVaries extends Appliance{
	
	private float consumptionPerHour;
	
	
	Random randomNum;
	private int minUnitsConsumed, maxUnitsConsumed;
	
	//Determining the amount of power used in a given increment of time by calculating a random number within the range given in Table 2 
	//Random number generator to determine number of units consumed per hour
	public CyclicVaries(String name, float consumptionPerHour, int activeTime, int minUnitsConsumed, int maxUnitsConsumed) {
		super(name);
		
		randomNum = new Random();
		
		this.minUnitsConsumed = minUnitsConsumed;
		this.maxUnitsConsumed = maxUnitsConsumed;
		
		if(consumptionPerHour >= minUnitsConsumed && consumptionPerHour <= maxUnitsConsumed) {
			this.consumptionPerHour = consumptionPerHour;
		}
		
		
	}

	//Determine whether the appliance is on based on the time of day
	//Incriment the appliance to the meter it is attached to, by a random value between maxUnitsConsumed and minUnitsConsumed
	
	@Override
	public void timePasses(int timeStep) {
		
		if(maxUnitsConsumed < 0 && minUnitsConsumed < 0) {
			this.consumptionPerHour = -(randomNum.nextInt((Math.abs(maxUnitsConsumed)+1)-Math.abs(minUnitsConsumed)) + Math.abs(minUnitsConsumed));
		}else {
			this.consumptionPerHour = randomNum.nextInt((maxUnitsConsumed+1)-minUnitsConsumed) + minUnitsConsumed;
		}
		
		
		super.tellMeterToConsumeUnits(this.consumptionPerHour);
		
		
	}
}
