package com.home.sabir.spring.DependencyInjection.AnnotationBeanDI;

public interface Coach {

	public String getDailyWorkout();
	public String getDailyFortune();

	public FortuneServiceInterface getFortuneService();
	
}
