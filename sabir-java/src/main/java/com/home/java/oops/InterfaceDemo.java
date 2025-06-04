/*
 * Interface.java
 *
 * Created on November 27, 2008, 4:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.oops;

interface Vehicle {
	public String Car="Car";
	public String MotorCycle="Motorcycle";
	public String defaultMotorcycleFuelType="ELECTRIC";
	public String defaultCarFuelType="PETROL";
	
    void run(String fuelType);
    
    //This Method applicable from Java 9    
	/*
	 * private String getVehicleName(Integer i) {
	 *  if (i==4) 
	 *  	return Car;
	*   else 
	*   	return MotorCycle; 
	 * }
	 */
}

class Car implements Vehicle {
	private Integer wheelsCount=4;
	public String maxSpeed="200 KM";
	public String fuelType=null;
	public String brandName=null;
	public Integer viperCount=2;
	
	public void run(String ft) {		
		System.out.println("This is a " + Car + " which has " + wheelsCount.intValue() + " wheels and its maxSpeed is "+ maxSpeed );
		printFuelType(ft);
	}
	
	public Integer getViperCount() {
		return viperCount;
	}
	
	private void printFuelType(String ft) {
		fuelType=ft!=null?ft:defaultCarFuelType;
		System.out.println("FuelType="+fuelType);
	}
}

class Motorcycle implements Vehicle {
	private Integer wheelsCount=2;
	public String maxSpeed="100 KM";
	public String fuelType=null;
	public String brandName=null;
	public Boolean sideStand=true;

	public void run(String ft) {		
		System.out.println("This is a " + MotorCycle  + "  which has " + wheelsCount.intValue() + " wheels and its maxSpeed is "+ maxSpeed );
		printFuelType(ft);
	}
	
	public Boolean isSideStand() {
		return sideStand;
	}
	
	private void printFuelType(String ft) {
		fuelType=ft!=null?ft:defaultMotorcycleFuelType;
		System.out.println("FuelType="+fuelType);
	}
}

public class InterfaceDemo {
    
  public static void main(String args[]) {
	  
	  	Vehicle car = new Car();         
	  	
        Vehicle motorCycle = new Motorcycle();
        
        tryout(car);
        System.out.println("===========================================================================");
        tryout(motorCycle);
      }

  private static void tryout(Vehicle vehicle) {
	  
	  vehicle.run(null);
	  if (vehicle instanceof Car)	{	   
		  Car car=(Car) vehicle;
		  car.brandName="TOYOTA";
		  System.out.println("Brand Name="+car.brandName);
		  System.out.println("Viper Count="+car.getViperCount());
	  }else if (vehicle instanceof Motorcycle)	{
		  Motorcycle motorCycle=(Motorcycle) vehicle;
		  motorCycle.brandName="HONDA";
		  System.out.println("Brand Name="+motorCycle.brandName);
		  System.out.println("Side Stand="+motorCycle.isSideStand());		  
	  }
  }
    
}
