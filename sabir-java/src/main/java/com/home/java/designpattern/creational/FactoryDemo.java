package com.home.java.designpattern.creational;

public class FactoryDemo {

	public static void main(String[] args) {
		Computer computer1 = ComputerFactory.getComputer("pc","2 GB","500 GB","2.4 GHz");
		Computer computer2 = ComputerFactory.getComputer("server","16 GB","1 TB","2.9 GHz");
		System.out.println("Computer1 Config::::::::"+computer1);
		System.out.println("Computer2 Config::::::::"+computer2);
	}

}

abstract class Computer {

	public abstract String getTYPE();
	public abstract String getRAM();
	public abstract String getHDD();
	public abstract String getCPU();
	
	@Override
	public String toString(){
		return "TYPE=" + this.getTYPE() + ", RAM="+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
	}
}

class PC extends Computer {

	private String type;
	private String ram;
	private String hdd;
	private String cpu;
	
	public PC(String type,String ram, String hdd, String cpu){
		this.type=type;
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	
	@Override
	public String getTYPE() {
		return this.type;
	}
	
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}

class Server extends Computer {

	private String type;
	private String ram;
	private String hdd;
	private String cpu;
	
	public Server(String type,String ram, String hdd, String cpu){
		this.type=type;
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	

	@Override
	public String getTYPE() {
		return this.type;
	}
	
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}

class ComputerFactory {

	public static Computer getComputer(String type, String ram, String hdd, String cpu){
		if("PC".equalsIgnoreCase(type)) return new PC(type,ram, hdd, cpu);
		else if("Server".equalsIgnoreCase(type)) return new Server(type,ram, hdd, cpu);
		
		return null;
	}
}



