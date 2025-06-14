package com.home.sabir.spring.BeanHooks;

public class TrackCoach implements Coach {

	private FortuneServiceInterface fortuneService;

	public TrackCoach(FortuneServiceInterface fortuneService){
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public void initMyObject(){
		System.out.println("Object ini successful");
	}

	@Override
	public void closeMyObject(){
		System.out.println("Object is closed successfully");
	}

}










