package com.home.java.java8;
/**
 * @author sabeer
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Product {
    private int id;
    private String name;
    private float price;
    
    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}


public class StreamDemo {
	
	private static List < Product > productsList = new ArrayList < Product > ();
	
	
	public static void main(String[] args) {
       
		List<Integer> nums = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
		long sum1 = nums.parallelStream().mapToLong(n -> n).sum();
		System.out.println("Parallel Stream Sum: " + sum1);
		System.out.println("=============");
		 
		List<Double> numbers = Arrays.asList(3.5, 1.2, 4.8, 2.9, 0.5);
		
		// Sorting: Sort the filtered numbers in descending order using Stream
		List<Double> ascSortedNumbers = numbers.stream()
										                .sorted() //Sort by Ascending Order
										                .collect(Collectors.toList());
				
		// Filtering: Filter only numbers greater than 3 using Stream
		List<Double> filteredNumbers = ascSortedNumbers.stream()
														.filter(a -> a > 3)   // Sort the numbers by ascending
														.collect(Collectors.toList());
		
        
		
		// Sorting: Sort the filtered numbers in descending order using Stream
		List<Double> descSortedNumbers = filteredNumbers.stream()
			                                            .sorted((a, b) -> Double.compare(b, a)) //Sort numbers by Descending Order
			                                            .collect(Collectors.toList());
        
        // Mapping: Convert each number to its square of the filtered number using Stream
        List<Double> squaredNumbers  = descSortedNumbers.stream()
				                                            .map(a -> a * a) //Mapping: Convert each number to its square
				                                            .collect(Collectors.toList());

        // Reducing: Sum all the squaredNumbers using Stream
        double sum = squaredNumbers.stream()
        													.reduce(0.0, Double::sum);         //Reducing: Convert each number to its square
                
        
        System.out.println("Original Numbers: "+numbers);
        System.out.println("Sorted Numbers by Asc: "+ascSortedNumbers);
        System.out.println("Filtered Numbers greater than 3: "+filteredNumbers);
        System.out.println("Sorted Numbers by Desc: "+descSortedNumbers);
        System.out.println("Mapped Numbers to Convert each number to its square: "+squaredNumbers);
        System.out.println("Reduced by calculating the Sum : " + sum);
           
         // Filter, Sort, Map, Reduce all in one
        double sumAll = numbers.stream()
												.filter(a -> a > 3)                                     // Filter only numbers greater than 3
								                .sorted((a, b) -> Double.compare(b, a)) //Sort by Descending Order
								                .map(a -> a * a)                                   //Mapping: Convert each number to its square
								                .reduce(0.0, Double::sum);                 //Reducing: Compute the sum of all numbers
        System.out.println("Stream Filter Sort Map Reduced (Sum): " + sumAll);
        

        //Another way of Sort by Descending  with out stream.     
        Collections.reverse(ascSortedNumbers);
        System.out.println("Reverse the Sorted List: "+ascSortedNumbers);          

        System.out.println();
        System.out.println("=============");
        System.out.println("Stream with Object:");
        System.out.println("=============");
        List<Product> products = Arrays.asList(
                new Product(1,"APPLE", 10f),
                new Product(1,"ORANGE", 20f),
                new Product(1,"MANGO", 15f)
            );
        // Filtering: Get people older than 24
        List<Product> filteredList = products.stream()
                                          .filter(a -> a.getPrice() > 12f)
                                          .collect(Collectors.toList());
        System.out.println("Filtered (Price > 12): " + filteredList);

        // Sorting: Sort by age in ascending order
        List<Product> sortedList = products.stream()
                                        .sorted((a1, a2) -> Float.compare(a1.getPrice(), a2.getPrice()))
                                        .collect(Collectors.toList());
        System.out.println("Sorted by Price: " + sortedList);

        // Mapping: Extract only names from the list
        List<String> namesList = products.stream()
                                       .map(a -> a.getName())
                                       .collect(Collectors.toList());
        System.out.println("Names Only: " + namesList);
        

               
        // List of lists of names
        List<List<String>> listOfLists = Arrays.asList(
            Arrays.asList("Reflection", "Collection", "Stream"),
            Arrays.asList("Structure", "State", "Flow"),
            Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
        );
        System.out.println();
        System.out.println("=============");
        System.out.println("Initial List:");
        System.out.println("=============");
        listOfLists.forEach(System.out::println);
        
    
        
        
       // flatMap: the list of lists into a single stream
        List<String> names = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());;
        System.out.println();
        System.out.println("==========================");
        System.out.println("FlatMap: Convert to Single List");
        System.out.println("==========================");
        names.forEach(System.out::println);

       

        // count: Count the number of names
        long count = names.stream().count();
        System.out.println();
        System.out.println("==========================");
        System.out.println("count: Number of items");
        System.out.println("==========================");
        System.out.println(count);
        

        // findFirst: Find the first name
        Optional<String> firstName = names.stream().findFirst();
        System.out.println();
        System.out.println("==========================");
        System.out.println("findFirst: Get the First Item");
        System.out.println("==========================");
        firstName.ifPresent(System.out::println);
        

        // reduce: Concatenate all names into a single string
        String concatenatedNames = names.stream()
        		.reduce(
        				"",
            (partialString, element) -> partialString + " " + element
        );
        System.out.println();
        System.out.println("==========================");
        System.out.println("reduce: Concatenated Items");
        System.out.println("==========================");
        System.out.println(concatenatedNames.trim());
        
       // equals: Check a String Exisits
        Boolean bool = names.stream()
        		.equals("State");
        System.out.println();
        System.out.println("==========================");
        System.out.println("equals: Check if State exisits");
        System.out.println("==========================");
        System.out.println(bool);
        
        
        // collect: Collect names starting with 'S' into a list
        List<String> sNames = names.stream()
                                   .filter(name -> name.startsWith("S"))
                                   .collect(Collectors.toList());
        System.out.println();
        System.out.println("==========================");
        System.out.println("startsWith: Items starting with 'S'");
        System.out.println("==========================");
        sNames.forEach(System.out::println);


        // allMatch: Check if all names start with 'S'
        boolean allStartWithS = names.stream().allMatch(
            name -> name.startsWith("S")
        );
        System.out.println();
        System.out.println("==========================");
        System.out.println("allMatch: Check all start with 'S'");
        System.out.println("==========================");
        System.out.println(allStartWithS);

        // anyMatch: Check if any name starts with 'S'
        boolean anyStartWithS = names.stream().anyMatch(
            name -> name.startsWith("S")
        );
        System.out.println();
        System.out.println("==========================");
        System.out.println("anyMatch: Check any start with 'S'");
        System.out.println("==========================");
        System.out.println(anyStartWithS);
        
        
     // Create a set to hold intermediate results
        Set<String> intermediateResults = new HashSet<>();

        // Stream pipeline demonstrating various intermediate operations
        List<String> result = listOfLists.stream()
            .flatMap(List::stream)                             // Flatten the list of lists into a single stream
            .filter(s -> s.startsWith("S"))                   // Filter elements starting with "S"
            .map(String::toUpperCase)                     // Transform each element to uppercase
            .distinct()                                           // Remove duplicate elements
            .sorted()                                            // Sort elements
            .peek(s -> intermediateResults.add(s)) // Perform an action (add to set) on each element
            .collect(Collectors.toList());                // Collect the final result into a list

        // Print the intermediate results
        System.out.println();
        System.out.println("=================================");
        System.out.println("Intermediate Result:");
        System.out.println("=================================");
        intermediateResults.forEach(System.out::println);

        // Print the final result
        System.out.println();
        System.out.println("==========================");
        System.out.println("Processed Final Result:");
        System.out.println("==========================");
        result.forEach(System.out::println);
        
        
        
        // Adding Products
        productsList.add(new Product(1, "HP Laptop", 25000f));
        productsList.add(new Product(2, "Dell Laptop", 30000f));
        productsList.add(new Product(3, "Lenevo Laptop", 28000f));
        productsList.add(new Product(4, "Sony Laptop", 28000f));
        productsList.add(new Product(5, "Apple Laptop", 90000f));
        
        // Without Java 8 Stream API'S
        withoutStreamAPI();
        
        
        // With Java 8 Stream API'S
        withStreamAPI();
        
        
        
        
    }
	
	private static void withoutStreamAPI() {
        // without Stream API's
        List < Float > productPriceList = new ArrayList < Float > ();
        // filtering data of list
        for (Product product: productsList) {
        	//Filtering by check condition
            if (product.getPrice() > 25000) {
                productPriceList.add(product.getPrice());
            }
        }
        
         // displaying data by iterating the list       
        System.out.println("==========================");
        System.out.println("displaying data by iterating the list with out stream:");
        System.out.println("==========================");
        for (Float price: productPriceList) {
            System.out.println(price);
        }
    }
	
	private static void withStreamAPI() {
        // filtering data of list
        List < Float > productPriceListFiltered = productsList.stream()
        	.filter((product) -> product.getPrice() > 25000)
            .map((product) -> product.getPrice())
            .collect(Collectors.toList());
        
        // displaying data using lambda exp        
        System.out.println("==========================");
        System.out.println("displaying data using lambda exp with stream:");
        System.out.println("==========================");
        productPriceListFiltered.forEach((price) -> System.out.println(price));
        
       // Using Collectors's method to sum the prices.          
        System.out.println("==========================");
        System.out.println("Use stream to calculate sum:");
        System.out.println("==========================");  
        double totalPrice3 = productsList.stream()
            .collect(Collectors.summingDouble(product -> product.getPrice()));
        System.out.println(totalPrice3);
        

        
        // max() method to get max Product price
        System.out.println("==========================");
        System.out.println("Use stream to find max:");
        System.out.println("==========================");
        Product productA = productsList.stream()
            .max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1 : -1)
            .get();
        System.out.println(productA.getPrice());
        

        
        // min() method to get min Product price
        System.out.println("==========================");
        System.out.println("Use stream to find min:");
        System.out.println("==========================");
        Product productB = productsList.stream()
            .max((product1, product2) -> product1.getPrice() < product2.getPrice() ? 1 : -1)
            .get();
        System.out.println(productB.getPrice());
        

        // Converting product List into Set
        System.out.println("==========================");
        System.out.println("Use stream to convert set:");
        System.out.println("==========================");
        Set < Float > productPriceSet = productsList.stream()
        	//.filter(product -> product.getPrice() < 30000)
            .map(product -> product.getPrice())
            .collect(Collectors.toSet());
        System.out.println(productPriceSet);
        

        // Converting product List into Map
        System.out.println("==========================");
        System.out.println("Use stream to convert map:");
        System.out.println("==========================");
        Map < Integer, String > productPriceMap = productsList.stream()
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));
            System.out.println(productPriceMap);
        

            // Use method Reference in stream
        System.out.println("==========================");
        System.out.println("Use method Reference in stream :");
        System.out.println("==========================");
        List < Float > productPriceList = productsList.stream()
                .filter(p -> p.getPrice() > 30000) // filtering data 
                .map(Product::getPrice) // fetching price by referring getPrice method
                .collect(Collectors.toList()); // collecting as list
            System.out.println(productPriceList);
    }
}
