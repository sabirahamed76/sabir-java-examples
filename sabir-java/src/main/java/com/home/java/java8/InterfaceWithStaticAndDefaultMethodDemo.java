package com.home.java.java8;
/**
 * @author sabeer
 *
 */
interface Vehicle {
	 String getBrand();

	 String speedUp();

	 String slowDown();

	 default String turnAlarmOn() {
	  return "Turning the vehice alarm on.";
	 }

	 default String turnAlarmOff() {
	  return "Turning the vehicle alarm off.";
	 }

	 static int getHorsePower(int rpm, int torque) {
	  return (rpm * torque) / 5252;
	 }
	}

class Car implements Vehicle {

    private final String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String speedUp() {
        return "The car is speeding up.";
    }

    @Override
    public String slowDown() {
        return "The car is slowing down.";
    }
}


class Motorbike implements Vehicle {

    private final String brand;

    public Motorbike(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String speedUp() {
        return "The motorbike is speeding up.";
    }

    @Override
    public String slowDown() {
        return "The motorbike is slowing down.";
    }
}

public class InterfaceWithStaticAndDefaultMethodDemo {
	public static void main(String[] args) {

		  Vehicle car = new Car("BMW");
		  System.out.println(car.getBrand());
		  System.out.println(car.speedUp());
		  System.out.println(car.slowDown());
		  System.out.println(car.turnAlarmOn());
		  System.out.println(car.turnAlarmOff());
		  System.out.println(Vehicle.getHorsePower(2500, 480));
		  
		  System.out.println();
		  
		  Vehicle bike = new Motorbike("ACTIVA 4G");
		  System.out.println(bike.getBrand());
		  System.out.println(bike.speedUp());
		  System.out.println(bike.slowDown());
		  System.out.println(bike.turnAlarmOn());
		  System.out.println(bike.turnAlarmOff());
		  System.out.println(Vehicle.getHorsePower(2500, 480));
		  
		 }
}
