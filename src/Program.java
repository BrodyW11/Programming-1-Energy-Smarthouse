package coursework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Program {

	public static void main(String[] args) {
		
		ArrayList<ConfigRead> configReads = getConfigs("myhome.txt");
		//ArrayList<ConfigRead> configReads = getConfigs(args[0]); // Get argument when you execute using javac
				
		House house = new House(new Meter("Electricity",0.013,0),new Meter("Water", 0.002,0));

		for(ConfigRead configRead : configReads) {
			Appliance appliance = null;
			if(configRead.subclass.equals("CyclicFixed")) {
				appliance = new CyclicFixed(configRead.name, configRead.fixedUnitsConsumed, configRead.cycleLength);
				
			}else if(configRead.subclass.equals("CyclicVaries")) {
				appliance = new CyclicVaries(configRead.name, 6.0f , configRead.cycleLength, configRead.minUnitsConsumed, configRead.maxUnitsConsumed);
			}else if(configRead.subclass.equals("RandomFixed")) {
				appliance = new RandomFixed(configRead.name, configRead.fixedUnitsConsumed, configRead.probabilityHigh, configRead.probabilityLevel);
			}else if(configRead.subclass.equals("RandomVaries")) {
				appliance = new RandomVaries(configRead.name, 6.0f, configRead.minUnitsConsumed, configRead.maxUnitsConsumed, configRead.probabilityHigh, configRead.probabilityLevel);
			}
			
			if(configRead.meter.equals("electric")) {
				
				house.addElectricAppliance(appliance);
			}else if(configRead.meter.equals("water")) {
				
				house.addWaterAppliance(appliance);
			}
			
		}
		
		double totalCost = 0;
		if(args.length == 2) {
			totalCost = house.activate(Integer.parseInt(args[1]));
		}else {
			totalCost = house.activate(24*7);
		}
		 
		System.out.println("\n Total Cost : "+totalCost);
	}
	
	private static ArrayList<ConfigRead> getConfigs(String fileName) {
		ArrayList<ConfigRead> configReads = new ArrayList<>();
		ConfigRead configRead = new ConfigRead();
			
		
		File file = new File(fileName);
		
		try{
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			
			for(String line; (line = bufferReader.readLine()) != null; ) {
				if(!line.trim().equals("")) {
					String[] parts = line.split(":");
			        String key = parts[0];
				    String value = "";
				    if(parts.length == 2) {
				    	value = parts[1];
				    }
				    
				    value = value.trim();
				    key = key.trim();
				   
			    
			       switch(key) {
			       		case "name":
			       			configRead.name = value.replaceAll("\\s","");
			       			break;
			       		case "subclass":
			       			configRead.subclass = value.replaceAll("\\s","");
			       			break;
			       		case "meter":
			       			configRead.meter = value.replaceAll("\\s","");
			       			break;
			       		case "Min units consumed":
			       			if(!value.isEmpty()) {
			       				configRead.minUnitsConsumed = Integer.parseInt((value.trim()));
			       			}
			       			break;
			       		case "Max units consumed":
			       			if(!value.isEmpty()) {
			       				configRead.maxUnitsConsumed = Integer.parseInt(value.trim());
			       			}
			       			break;
			       		case "Fixed units consumed":
			       			if(!value.isEmpty()) {
			       				configRead.fixedUnitsConsumed = Float.valueOf(value.trim());
			       			}
			       			break;
			       		case "Probability switched on":
			       			if(!value.isEmpty()) {
			       				String[] fields = value.replaceAll("\\s","").split("in");
			       				configRead.probabilityHigh = Integer.parseInt(fields[1]);
			       				configRead.probabilityLevel = Integer.parseInt(fields[0]);
			       			}
			       			break;
			       		case "Cycle length":
			       			if(!value.isEmpty()) {
			       				String val = value.split("\\/")[0];
			       				configRead.cycleLength = Integer.parseInt(val.trim());
			       			}
			       			configReads.add(configRead);
			       			configRead = new ConfigRead();
			       			break;
			       		default:
			       }
				}
		    }
		}catch(Exception e) {
			System.out.println("There is an error " + e.getMessage());
		}
		
		return configReads;
	}

}
