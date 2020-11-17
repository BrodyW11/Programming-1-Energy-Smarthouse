package coursework;

import java.util.Random;

public class RandomVaries extends Appliance{
	
	private float consumptionPerHour;
	private int probabilityHigh;
	private int probabilityLevel;
	private float totalConsumption;
	private int minUnitsConsumed, maxUnitsConsumed;
	
	public RandomVaries(String name, float consumptionPerHour, int minUnitsConsumed, int maxUnitsConsumed, int probabilityHigh, int probabilityLevel) {
		super(name);
		
		if(consumptionPerHour >= -25 && consumptionPerHour <= -1) {
			this.consumptionPerHour = consumptionPerHour;
		}
		
		this.probabilityHigh = probabilityHigh;	
		this.probabilityLevel = probabilityLevel;
		
		this.minUnitsConsumed = minUnitsConsumed;
		this.maxUnitsConsumed = maxUnitsConsumed;
	}

	@Override
	public void timePasses(int timeStep) {
		
		this.totalConsumption = 0;
		
		Random rand = new Random();
		
		double probability = Double.parseDouble(Integer.toString(this.probabilityLevel))/Double.parseDouble(Integer.toString(this.probabilityHigh));
		double randomValue = rand.nextDouble();
		if(randomValue <= probability) {
			if(maxUnitsConsumed < 0 && minUnitsConsumed < 0) {
				this.consumptionPerHour = -(rand.nextInt((Math.abs(maxUnitsConsumed)+1)-Math.abs(minUnitsConsumed)) + Math.abs(minUnitsConsumed));
			}else {
				this.consumptionPerHour = rand.nextInt((maxUnitsConsumed+1)-minUnitsConsumed) + minUnitsConsumed;
			}
		}
		
		super.tellMeterToConsumeUnits(this.totalConsumption);
		
		
	}
}

