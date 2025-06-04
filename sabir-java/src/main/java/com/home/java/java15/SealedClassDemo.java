package com.home.java.java15;


sealed class Vehicle permits Car, Truck {
    public void drive() {
        System.out.println("Vehicle is driving");
    }
}

final class Car extends Vehicle {

}  // Cannot be extended further

sealed class Truck extends Vehicle permits HeavyTruck {

}  // Allows only HeavyTruck

non-sealed class HeavyTruck extends Truck {

}  // Can be extended freely


public class SealedClassDemo {

    public static void main(String[] args) {
        Vehicle v = new Car();
        v.drive();  // Output: Vehicle is driving
    }
}
