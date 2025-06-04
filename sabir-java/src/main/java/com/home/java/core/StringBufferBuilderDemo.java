package com.home.java.core;

public class StringBufferBuilderDemo {
	
	public static void StrConcat(String str1) {
        str1 = str1 + "Sabir";
    } 
   
    public static void StrBufConcat(StringBuffer str2) {
        str2.append("Sabir");
    } 
   
    public static void StrBuildConcat(StringBuilder str3)  {
        str3.append("Sabir");
    } 
   
   
    
	public static void main(String args[]) {
		System.out.println("-----------Mutability------------");
		mutabilityDemo();
		System.out.println("-----------Comparison------------");
		compareDemo();
		System.out.println("-----------Replace------------");
		replaceDemo();
	}
	
	public static void compareDemo() {
		String s1 = "Java";
		String s2 = "Java";
		StringBuilder sb1 = new StringBuilder();
		sb1.append("Ja").append("va");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(sb1.toString() == s1);
		System.out.println(sb1.toString().equals(s1));
	}
	
	public static void mutabilityDemo() {
		String str1 = "Hello!"; 
        StrConcat(str1); 
        System.out.println("The final String is - " + str1); 
   
        StringBuffer str2 = new StringBuffer("Hello!"); 
        StrBufConcat(str2); 
        System.out.println("The final String is - " + str2); 
   
        StringBuilder str3 = new StringBuilder("Hello!"); 
        StrBuildConcat(str3);
        System.out.println("The final String is -" + str3); 
	}
	
	public static void replaceDemo() {
		String rule1="AB";  String res1="AA";
        String rule2="BA";  String res2="AA";
        String rule3="CB";  String res3="CC";
        String rule4="BC";  String res4="CC";
        String rule5="AA";  String res5="A";
        String rule6="CC";  String res6="C";
        
        String[] rule = {"AB","BA","CB","BC","AA","CC"};
        String[] res = {"AA","AA","CC","CC","A","C"};
        
        String S="ABBCC";
        
        
        for(int i=0; i<rule.length; i++){
       	 if (S.contains(rule[i])){
       		S= S.replace(rule[i], res[i]);
       	 }
       	 
        }
        
        S= S.replace(rule[5], res[5]);
        System.out.println(S);
        
	}
}
