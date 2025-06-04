package com.home.java.java8;

import java.util.Optional;

/**
 * @author sabeer
 *
 */
public class OptionalClassDemo {

	    public static void main(String[] args) {
	        isPresentOptionalAPI();
	        createEmptyOptionalObject();
	        createEmptyOptionalObjectWithStaticAPI();
	        ifPresentOptionalAPI();
	        orElseOptionalAPI();
	        orElseOptionalAPI();
	        orElseGetOptionalAPI();
	        //orElseThrowOptionalAPI();
	        getOptionalAPI();
	    }

	    // Returns an Optional with the specified present non-null value.
	    private static void isPresentOptionalAPI() {
	        Optional < String > opt = Optional.of("ABC");
	        System.out.println("Optional isPresent: "+opt.isPresent());
	    }

	    // Returns an Optional with the specified present non-null value.
	    private static void createEmptyOptionalObject() {
	        Optional < String > empty = Optional.empty();

	        // Optional object with the static of API:
	        String name = "ABC";
	        Optional.of(name);
			System.out.println("Optional empty: "+empty.isPresent());
	    }

	    private static void createEmptyOptionalObjectWithStaticAPI() {
	        String name = "baeldung";
	        Optional.of(name);
	    }

	// If a value is present, invoke the specified consumer with the value, otherwise do
	// nothing.
	    private static void ifPresentOptionalAPI() {
	   // The ifPresent API enables us to run some code on the wrapped value if it is
	        // found to be non-null.
	        // Before Optional, we would do something like this:
	        String name = "ABC";

	        Optional < String > opt = Optional.of("ABC");
	        opt.ifPresent(str -> System.out.println("Optional ifPresent: "+str.length()));
	    }

	// If a value is present, invoke the specified consumer with the value, otherwise do
	 // nothing.
	    private static void orElseOptionalAPI() {
	  // With orElse, the wrapped value is returned if it is present and the argument
	   // given to
	  // orElse is returned if the wrapped value is absent
	        String nullName = null;

	  // If a value is present, invoke the specified consumer with the value, otherwise
	     // do nothing.
	        //
	        String name = Optional.ofNullable(nullName).orElse("ABC");
	        System.out.println("Optional ofNullable orElse: "+name);
	    }

	    private static void orElseGetOptionalAPI() {
	        String nullName = null;
	        String name = Optional.ofNullable(nullName).orElseGet(() -> "ABC");
	        System.out.println("Optional ofNullable orElseGet: "+name);
	    }
	    

	    private static void getOptionalAPI() {
	        Optional < String > opt = Optional.of("ABC");
	        String name = opt.get();
	        System.out.println("Optional of Get: "+name);
	    }
	      
	    private static void orElseThrowOptionalAPI() {

	        // This will throw exception
	        String nullName = null;
	        String name = Optional.ofNullable(nullName)
	            .orElseThrow(IllegalArgumentException::new);
	        System.out.println(name);
	    }  


	}

