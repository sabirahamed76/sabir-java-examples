package com.home.sabir.spring.DependencyInjection.DependencyInjectionConstructor;

import org.springframework.stereotype.Component;

@Component("myCoachConstructor")
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

}










