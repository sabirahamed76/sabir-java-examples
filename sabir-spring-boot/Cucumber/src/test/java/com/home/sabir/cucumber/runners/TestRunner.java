package com.home.sabir.cucumber.runners;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features", 
glue= {"com.home.sabir.cucumber.steps"}, 
plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"})
public class TestRunner {

}
