package coursework;

import java.util.ArrayList;

public class House {
	Meter electricMeter;
	Meter waterMeter;
	ArrayList<Appliance> appliances;
	
	public House() {
		electricMeter = new Meter("Electric",0.013,0);
		waterMeter = new Meter("Water",0.002,0);
		appliances = new ArrayList<>();
	}
	
	public House(Meter electricMeter, Meter waterMeter) {
		this.electricMeter = electricMeter;
		this.waterMeter = waterMeter;
		appliances = new ArrayList<>();
	}
	
	public void addElectricAppliance(Appliance appliance) {
		appliance.setMeter(this.electricMeter);
		appliances.add(appliance);
	} 
	
	public void addWaterAppliance(Appliance appliance) {
		appliance.setMeter(this.waterMeter);
		appliances.add(appliance);
	} 
	
	public void removeAppliance(Appliance appliance) {
		appliances.remove(appliance);
	}
	
	public int numAppliances() {
		return this.appliances.size();
	}
	
	public double activateAppliances(int timeStep) {
		for(Appliance appliance : appliances) {
			appliance.timePasses(timeStep);
		}
		
		return this.electricMeter.report()+this.waterMeter.report();
	}
	
	public double activate(int numberOfHours) {
		double totalCost = 0;
		
		for(int i=0;i<numberOfHours;i++) {
			System.out.println("\n\n*******Hour " + (i + 1) + "*******");
			try { 
				Thread.sleep(500); 
				totalCost += activateAppliances(i); 
			} 
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		
		return totalCost;
	}
}
