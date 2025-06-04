package com.home.java.java8;
/**
 * @author sabeer
 *
 */
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//Must be Only one method should be abstract
//It can have any number of default and static methods
//It can have main Method
@FunctionalInterface  
interface FunctionInterfaceCustom{
    void say(String msg);   // abstract method

    default void display1(){
        System.out.println("default method in functional interface");
    }

    public static void main(String[] args){
        System.out.println("static main method in functional interface");
    }

    public static void display2(){
        System.out.println("static method in functional interface");
    }
}  


class Employee {
    private String name;
    private Integer age;

    public Employee(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class EmployeeDTO {
    private String name;
    private Integer age;

    public EmployeeDTO(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
    	//Custom Function Interface using lambda expression without implementing in class
        System.out.println("................................Custom functional Interface................................");
        FunctionInterfaceCustom customInterface = (msg) -> {
            System.out.println(msg);
        };
        customInterface.say("printing by lambda expression");
        customInterface.display1(); //calling default method
        FunctionInterfaceCustom.display2(); // calling static method
        System.out.println();





        // Predicate<T> – Takes an input and returns a boolean based condition Evaluation.
        // It has test method takes input Object and evaluate conditions then return boolean
        System.out.println("................................Predicate functional Interface................................");
        Predicate<Integer> predicateIsEven = num -> num % 2 == 0;
        System.out.println("Checking 4 is Even or not: " + predicateIsEven.test(4)); // Output: true
        //Predicate Chaining
        Predicate<Integer> predicateGreaterThanTen = num -> num > 10;
        Predicate<Integer> predicateLessThanTwenty = num -> num < 20;
        Predicate<Integer> predicateBetweenTenAndTwenty = predicateGreaterThanTen.and(predicateLessThanTwenty);
        System.out.println("Checking 15 is between 10 and 20: "+ predicateBetweenTenAndTwenty.test(15)); // Output: true
        //Predicate by checking Object
        Predicate <Employee> predicate = (employee) -> employee.getAge() > 28;
        boolean result = predicate.test(new Employee("abcd", 29));
        System.out.println("Checking age is greater than 28: " + result);





        // Function<T, R> – Takes an input and returns a result.
        //It has apply method to take input Object and return output Object
        System.out.println();
        System.out.println("................................Function functional Interface................................");
        Function <Integer, Double> functionCalculateCent2Faren = x ->  Double.valueOf((x * 9 / 5) + 32);
        System.out.println("Centigrade To Fahrenheit: " + functionCalculateCent2Faren.apply(37));
        Function <String, Integer> functionStringToInt = x -> Integer.valueOf(x);
        System.out.println("String to Int: " + functionStringToInt.apply("4"));
        //Function by checking Object
        Function <Employee, EmployeeDTO> functionEmp = (employee) -> {
            return new EmployeeDTO(employee.getName(), employee.getAge());
        };
        EmployeeDTO employeeDTO = functionEmp.apply(new Employee("ABC", 10));
        System.out.println("Employee Detail: " + employeeDTO.getName() + ", " + employeeDTO.getAge());






        // Consumer<T> – Takes an only one input Object but returns nothing means void.
        //It has accept method to take input Object and process
        System.out.println();
        System.out.println("................................Consumer functional Interface................................");
        Consumer <String> consumerPrint = x -> System.out.println(x);
        consumerPrint.accept("4");

        Consumer<Employee> consumer = (employee1) -> {
            System.out.println("Employee Detail: " + employee1.getName() + ", " + employee1.getAge());
        };
        consumer.accept(new Employee("XYZ", 30));






        // Supplier<T> – Take No input and return result object.
        //It has get method and return output Object by generate
        System.out.println();
        System.out.println("................................Supplier functional Interface................................");
        Supplier<EmployeeDTO> supplier = () -> {
            return new EmployeeDTO("PQR", 20 );
        };   
        EmployeeDTO employee2 = supplier.get();
        System.out.println("Employee Detail: " + employee2.getName() + ", " + employee2.getAge());



    }
}