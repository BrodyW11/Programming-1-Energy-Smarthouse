package coursework;

//Defines 
public abstract class Appliance {
	private String name;
	private Meter meter;
	
	//Constructor that initialises the Appliance with a name
	public Appliance(String name) {

		this.name = name;
	}
	
	//Setter method to set the meter
	public void setMeter(Meter meter) {

		this.meter = meter;
	}
	
	//Method that calls consumeUnits on the meter associated with this appliance
	protected void tellMeterToConsumeUnits(float numberOfUnits) {

		meter.consumeUnits(numberOfUnits);
	}
	
	//Abstract method that uses inheritance to model different types of Appliance
	//CyclicFixed, CyclicVaries, RandomFixed & RandomVaries all inherit from this method
	public abstract void timePasses(int timeStep);

}

