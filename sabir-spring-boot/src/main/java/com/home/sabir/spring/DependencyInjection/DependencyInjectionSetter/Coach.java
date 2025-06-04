package com.home.sabir.spring.DependencyInjection.DependencyInjectionSetter;

public interface Coach {

	public String getDailyWorkout();
	public String getDailyFortune();

	public void setFortuneService(FortuneServiceInterface fortuneService);
	
}
