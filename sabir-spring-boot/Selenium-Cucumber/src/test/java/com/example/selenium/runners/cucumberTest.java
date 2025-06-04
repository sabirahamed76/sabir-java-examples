package com.example.selenium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json", "junit:results/cucumber.xml"},
        glue = "com.example.selenium")
public class cucumberTest {

}