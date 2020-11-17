package coursework;

import java.util.Random;

//Defines instance variables 
public class RandomFixed extends Appliance{
	
	private float consumptionPerHour;
	private int probabilityHigh;
	private int probabilityLevel;
	private float totalConsumption;
	
	//Constructor that initialises the instance variables
	//Works in the probabilities that a given appliance will be turned on in a given incriment of time
	
	public RandomFixed(String name, float consumptionPerHour, int probabilityHigh, int probabilityLevel) {
		super(name);
		
		this.consumptionPerHour = consumptionPerHour;
		
		this.probabilityHigh = probabilityHigh;
		this.probabilityLevel = probabilityLevel;
		
	}

	//Determines whether the appliance is on according to outcome of the probability coefficient
	//Tells meter to increment the meter by the value specified on construction
	
	@Override
	public void timePasses(int timeStep) {
		this.totalConsumption = 0;
		
		Random rand = new Random();
		
		double probability = Double.parseDouble(Integer.toString(this.probabilityLevel))/Double.parseDouble(Integer.toString(this.probabilityHigh));
		double randomValue = rand.nextDouble();
		
		if(randomValue <= probability) {
			this.totalConsumption += this.consumptionPerHour;
		}
		
		super.tellMeterToConsumeUnits(this.totalConsumption);
	}
}
