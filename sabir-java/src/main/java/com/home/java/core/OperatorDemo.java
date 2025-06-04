/*
 * Operator.java
 *
 * Created on November 20, 2008, 1:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.core;

public class OperatorDemo {
  

  private static String init_train() {


    String strMsg = "";

    strMsg += "Operators" + "\n";

    strMsg += ex_Op_Standard();
    strMsg += ex_Op_Unary();
    strMsg += ex_Op_Trinary();
    strMsg += ex_Op_Assignment();
    strMsg += ex_Op_Comparison();
    
    strMsg += ex_Op_Objects();
    strMsg += ex_Op_Instanceof();
    strMsg += ex_Op_Bitwise_Boolean();
    strMsg += ex_Op_Bitwise_Shift();
    
    return strMsg;
  }    

  private static String ex_Op_Standard()  { 

    String strMsg = "";

    byte   bytMy = 0;
    short  shtMy = 0;
    int    intMy = 0;
    long   lngMy = 0L;    
    double dblMy = 0.0;
    float  fltMy = 0.0F;

    byte   bytTest;
    short  shtTest;
    int    intTest;
    long   lngTest;
    double dblTest;
    float  fltTest;
    
    strMsg += "Arithmentic Precedence rules: (), unary, */%, +-" + "\n";
    intTest = 3 + 2 * 3;    strMsg += "  3 + 2 * 3 = " + intTest + " (not 15)" + "\n";
    intTest = (3 + 2) * 3;  strMsg += "  (3 + 2) * 3 = " + intTest + "\n";
    strMsg += "  intMy = 1; (look at following code)" + "\n";
    intMy = 1;
    intTest = 3 + intMy++ * 3; strMsg += "  3 + intMy++ * 3 = " + intTest + " intMy=" + intMy + "\n";
    strMsg += "  intMy = 1; (look at following code)" + "\n";
    intMy = 1;
    intTest = 3 + ++intMy * 3; strMsg += "  3 + ++intMy * 3 = " + intTest + " intMy=" + intMy + "\n";
    
    strMsg += "Arithmetic operators" + "\n";
    dblTest = 3 * 4;  strMsg += "  3 * 4 = "  + dblTest + "\n";
    dblTest = 12 / 3; strMsg += "  12 / 3 = " + dblTest + "\n";
    dblTest = 12 % 3; strMsg += "  12 % 3 = " + dblTest + "\n";
    dblTest = 13 % 3; strMsg += "  13 % 3 = " + dblTest + "\n";
    intTest = 3 + 4;  strMsg += "  3 + 4 = "  + intTest + "\n";
    intTest = 3 - 4;  strMsg += "  3 - 4 = "  + intTest + "\n";
    
    strMsg += "  * '%'-Modulus  (remainder from division): ( 100 % 10 ) = "+
              ( 100 % 10 ) + "\n";

    //Example of Modulas - %
    int y = 0;
    for (int x = 1; x <= 1000; x++) {
      if ( (x % 100) == 0 ) {
        y++;  // 100 / 100 = 1 with 0 remainder!
      }
    }
    
    strMsg += "  * See code for example of % in a for loop: y = " + y + "\n";

    //Integer Division
    //Note:  Integers do not have decimals!
    //If there is a floating point involved, the answer is floating point!
    strMsg += "  * Integer Divison: 1 / 2 = "   + ( 1 / 2 ) + "\n";
    strMsg += "  * Floating Point: 1 / 2.0 = " + ( 1 / 2.0 ) + "\n";

    strMsg += "Unary: Operator Types and Conversions" + "\n";
    strMsg += "  * Remains the same type." + "\n";
    strMsg += "  * See code for examples!" + "\n";
    
    bytTest = bytMy++;
    shtTest = shtMy++;
    intTest = intMy++;
    lngTest = lngMy++;
    fltTest = fltMy++;
    dblTest = dblMy++;
    
    strMsg += "Binary: Operator Types and Conversions" + "\n";
    strMsg += "  * If either operand is double - convert to double." + "\n";
    strMsg += "    else if either operand is float - convert to float." + "\n";
    strMsg += "    else if either operand is long - convert to long." + "\n";
    strMsg += "    else convert to int." + "\n";
    strMsg += "  * See code for examples!" + "\n";

    //bytTest = bytMy + bytMy; //Error: Can't convert int to byte.
    //shtTest = shtMy + shtMy; //Error: Can't convert int to short.
    bytTest = (byte) (bytMy + bytMy);
    intTest = bytMy + bytMy;

    lngTest = intMy + lngMy;
    //intTest = intMy + lngMy; //Error: Can't convert long to int.
    
    dblTest = fltMy + dblMy;
    //fltTest = fltMy + dblMy;  //Error: Can't convert double to float.
    
    dblTest = intMy + dblMy;
    //intTest = intMy + dblMy; //Error: Can't convert double to int.
    
    return strMsg;
  }

  private static String ex_Op_Unary() {

    String strMsg = "";
    
    int x = 0;
    int y = 0;

    strMsg += "Uninary: Increment/Decrement:  Pre ++y / --y, Post y++ / y--" + "\n";
    y = 0;
    strMsg += "  * Post Increment: y=0; y++ = " + y++ + " after " + y + "\n";
    y = 0;
    strMsg += "  * Pre  Increment: y=0; ++y = " + ++y + " after " + y + "\n";

    y = 0; x = y++;
    strMsg += "  * Post Increment: y=0; x=y++; Results: y=" + y + " x=" + x + "\n";
    y = 0; x = ++y;
    strMsg += "  * Pre  Increment: y=0; x=++y; Results: y=" + y + " x=" + x + "\n";

    return strMsg;
  }

  private static String ex_Op_Trinary() {

    String strMsg = "";
    String strMy = "";

    strMsg += "Trinary (<boolean> ? <true> : <false> )" + "\n";
    
    strMy = ( (5 > 3) ? "Yes" : "No" );
    strMsg += "  * (see code) Ex: is 5 > 3 - " + strMy + "\n";
    
    strMy = ( (5 < 3) ? "Yes" : "No" );
    strMsg += "  * (see code) Ex: is 5 < 3 - " + strMy + "\n";

    return strMsg;
  }

  private static String ex_Op_Assignment()  {

    String strMsg = "";
    int intMy = 0;
   
    strMsg += "Assignment Operators" + "\n";
    
    intMy  = 5; strMsg += "  intMy  = 5; //value is " + intMy + "\n"; //5
    intMy += 1; strMsg += "  intMy += 1; //value is " + intMy + "\n"; //6
    intMy -= 1; strMsg += "  intMy -= 1; //value is " + intMy + "\n"; //5
    intMy *= 3; strMsg += "  intMy *= 1; //value is " + intMy + "\n"; //15
    intMy /= 3; strMsg += "  intMy /= 1; //value is " + intMy + "\n"; //5
    intMy %= 3; strMsg += "  intMy %= 1; //value is " + intMy + "\n"; //2
    
    // Others: &= |= ^= <<= >>= >>>=
    
    return strMsg;
  }

  private static String ex_Op_Comparison()  {

    String strMsg = "";
    String strMy = "";
    int intMy = 0;
    boolean blnMy = false;
   
    strMsg += "Comparison Operators" + "\n";
    
    strMsg += "Operators: ==, !=, >, <, >=, <= (see code)" + "\n";
    
    strMy = ( 5 > 2 )  ? "true" : "false"; strMsg += "  ( 5 > 2 ) ? \"true\" : \"false\"; //returns " + strMy + "\n";
    strMy = ( 5 > 5 )  ? "true" : "false"; strMsg += "  ( 5 > 5 ) ? \"true\" : \"false\"; //returns " + strMy + "\n"; 
    strMy = ( 5 >= 5 ) ? "true" : "false"; strMsg += "  ( 5 >= 5 ) ? \"true\" : \"false\"; //returns " + strMy + "\n"; 
    strMy = ( 5 >= 2 ) ? "true" : "false"; strMsg += "  ( 5 >= 2 ) ? \"true\" : \"false\"; //returns " + strMy + "\n"; 
    
    strMy = ( 2 < 5 )  ? "true" : "false"; strMsg += "  ( 2 < 5 ) ? \"true\" : \"false\";  //returns " + strMy + "\n"; 
    strMy = ( 5 < 5 )  ? "true" : "false"; strMsg += "  ( 5 < 5 ) ? \"true\" : \"false\";  //returns " + strMy + "\n"; 
    strMy = ( 5 <= 5 ) ? "true" : "false"; strMsg += "  ( 5 <= 5 ) ? \"true\" : \"false\";  //returns " + strMy + "\n"; 
    strMy = ( 2 <= 5 ) ? "true" : "false"; strMsg += "  ( 2 <= 5 ) ? \"true\" : \"false\";  //returns " + strMy + "\n"; 
    
    strMsg += "Bitwise Comparison - &,| (Both sides are always evaluated)" + "\n";
    intMy = 0;
    strMsg += "  intMy=0;" + "\n";
    strMy = ( false & (++intMy == 1) ) ? "true" : "false";
    strMsg += "  ( false & (++intMy == 1) ) ? \"true\" : \"false\"; strMy=" + 
              strMy + " intMy=" + intMy + "\n";

    intMy = 0;
    strMsg += "  intMy=0;" + "\n";
    strMy = ( true | (++intMy == 1) ) ? "true" : "false";
    strMsg += "  ( true & (++intMy == 1) ) ? \"true\" : \"false\"; strMy=" + 
              strMy + " intMy=" + intMy + "\n";

    strMsg += "Shortcut Comparison - &&,|| (Sides are evaluated if needed.)" + "\n";
    intMy = 0;
    strMsg += "  intMy=0;" + "\n";
    strMy = ( false && (++intMy == 1) ) ? "true" : "false";
    strMsg += "  ( false && (++intMy == 1) ) ? \"true\" : \"false\"; strMy=" + 
              strMy + " intMy=" + intMy + "\n";

    intMy = 0;
    strMsg += "  intMy=0;" + "\n";
    strMy = ( true || (++intMy == 1) ) ? "true" : "false";
    strMsg += "  ( true || (++intMy == 1) ) ? \"true\" : \"false\"; strMy=" + 
              strMy + " intMy=" + intMy + "\n";

    strMsg += "NOT Operator" + "\n";
    
    blnMy = ! false;
    strMsg += "  * blnMy = ! false; //value is " + blnMy + "\n";
    
    blnMy = ! true;
    strMsg += "  * blnMy = ! true; //value is " + blnMy + "\n";

    
    return strMsg;
  }

  private static String ex_Op_Objects()  {


    String strMsg = "";
    
    String strTest1 = "";
    String strTest2 = "";
    
    strMsg += "Objects: equals() and ==" + "\n";
    strMsg += "  == tests is object references point to same object." + "\n";
    strMsg += "  equals(), if overridden by the class, tests the value of." + "\n";
    strMsg += "  Object.equals() and '==' test the same thing!" + "\n";

    Boolean b1 = new Boolean(true);
    Boolean b2 = new Boolean(true);
    strMsg += "  * b1.equals( b2 ) = " + ( b1.equals( b2 ) ) + "\n";
    strMsg += "  * b1 == b2        = " + ( b1 == b2 )        + "\n";
    Object  b3 = (Object) b2;  //Warning, this is tricky !!!
    //Tricky, still calls Boolean.equals() because it was a Boolean
    //  before the cast!
    strMsg += "  * b3.equals( b2 ) = " + ( b3.equals( b2) )  + "\n";

    //Arrays - use Object.equals()  Remember arrays are objects!
    int [] aintX = {1};
    int [] aintY = {1};
    strMsg += "  * Arrays use Object.equals(): aintX.equals( aintY ) = " + ( aintX.equals( aintY ) )  + "\n";  //Tricky, still calls Boolean.equals()

    strTest1 = new String("Hello");
    strTest2 = new String("Hello");
    strMsg += "  * See source.  How is strTest1 & strTest2 initialized. " + "\n";
    strMsg += "  * object references: (strTest1 == strTest2) = "+
              (strTest1 == strTest2) + "\n";
    strMsg += "  * value of object: ( strTest1.equals( strTest2 ) ) = "+
              ( strTest1.equals( strTest2 ) ) + "\n";

    strTest2 = strTest1;
    strMsg += "  * If strTest2 = strTest1; then " + "\n";
    strMsg += "  * object reference: ( strTest1 == strTest2 ) = "+
              ( strTest1 == strTest2 ) + "\n";

    strTest1 = "Hello";
    strTest2 = "Hello";
    strMsg += "  * See source.  How is strTest1 & strTest2 initialized. " + "\n";
    strMsg += "    Tricky: compiler choose to reference the same object!" + "\n";
    strMsg += "    object reference: ( strTest1 == strTest2 ) = "+
              ( strTest1 == strTest2 ) + "\n";

    return strMsg;
  }

  private static String ex_Op_Instanceof()  {

    String strMsg = "";
    
    strMsg += "instanceof:" + "\n";
    //strMsg += "  * interface: this instanceof ActionListener = "+
 //             (this instanceof java.awt.event.ActionListener) + "\n";
   // strMsg += "  * subclass: this instanceof Applet = "+
    //          (this instanceof java.applet.Applet) + "\n";
    strMsg += "  * subclass: y instanceof Number = "+
              ((new Integer(5)) instanceof Number) + "\n";

    return strMsg;
  }

  private static String ex_Op_Bitwise_Boolean()  {


    String strMsg = "";
    int y = 0;
    
    strMsg += "Bitwise: &, | " + "\n";

    strMsg += "  * 17 = " + Integer.toBinaryString( 17 ) + "\n";
    strMsg += "  * 18 = " + Integer.toBinaryString( 18 ) + "\n";
    strMsg += "  * Bitwise And (17 & 18) = "+Integer.toBinaryString( 17 & 18 ) + "\n";
    strMsg += "  * Bitwise Or  (17 | 18) = "+Integer.toBinaryString( 17 | 18 ) + "\n";

    strMsg += "Bitwise Boolean: | (or) and & (and)" + "\n";
    y = 0;
    strMsg += "  * &,| - Always Evaluates both sides: "+ (true | (y=5) == 5) + "\n";
    strMsg += "  * y = "+ y + "\n";

    strMsg += "Logical Boolean: || (or) and && (and)" + "\n";
    y = 0;
    strMsg += "  * &&,|| - Evaluates if it needs to!: "+ (true || (y=5) == 5) + "\n";
    strMsg += "  * y = "+ y + "\n";

    return strMsg;
  }

  private static String ex_Op_Bitwise_Shift()  {


    String strMsg = "";
    int intTest;
    
    strMsg += "Bitwise: >>, <<, >>> (Hint: 2 right keep tight! )" + "\n";

    intTest = 0x00000001;  //  1
    strMsg += "  * intTest = 0x00000001" + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " base 2 = " + intTest + "\n";

    intTest = 0xffffffff;  // -1
    strMsg += "  * intTest = 0xffffffff" + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = -2;
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = -3;
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = -4;
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = 0x80000008;
    strMsg += "  * intTest = 0x80000008" + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest >>> 1;
    strMsg += "  * intTest >>> 1 = " + "\n";
    strMsg += "  * _"+Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest << 1;
    strMsg += "  * intTest << 1  = " + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest << 1;
    strMsg += "  * intTest << 1  = " +
              Integer.toBinaryString( intTest ) + " = " + intTest +
              " - sign bit dropped" + "\n";

    intTest = 0x80000008;
    strMsg += "  * Reset back to: intTest = 0x80000008, then ..."  + "\n";

    intTest = intTest >> 1;
    strMsg += "  * intTest >> 1  = " + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest >> 1;
    strMsg += "  * intTest >> 1  = " + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = 0x00000001;
    intTest = intTest << 31;
    strMsg += "  * intTest = 0x1, then intTest << 31 = " + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + "\n";

    //Bitwise Operator returns an int.
    
    strMsg += "Bitwise Operator and primitive data types." + "\n";
    
    byte byteTest = 1;
    char charTest = 'a';
    short shortTest = 1;
    long longTest = 1;
    float floatTest = 1;
    double doubleTest = 1;
    
    strMsg += "  * Returns an int if applied to: byte, char, short, int" + "\n";

    strMsg += "    intTest = byteTest >> 1" + "\n";
    intTest = byteTest >> 1;
    
    strMsg += "    intTest = charTest >> 1" + "\n";
    intTest = charTest >> 1;

    strMsg += "    intTest = shortTest >> 1" + "\n";
    intTest = shortTest >> 1;
    
    strMsg += "    intTest = intTest >> 1" + "\n";
    intTest = intTest >> 1;
    
    strMsg += "  * Returns a long if applied to a long." + "\n";
    
    strMsg += "    longTest = longTest >> 1" + "\n";
    longTest = longTest >> 1;
    //intTest = longTest >> 1; //Error: possible loss of precision
    
    strMsg += "  * Can not be applied to a real number (float or double)." + "\n";
    strMsg += "    Compile Error for float or double: operator >> cannot be applied to double,int" + "\n";
    //intTest = floatTest >> 1; //Error: operator >> cannot be applied to double,int
    //intTest = doubleTest >> 1; //Error: operator >> cannot be applied to double,int
    //floatTest = floatTest >> 1; //Error: operator >> cannot be applied to double,int
    //doubleTest = doubleTest >> 1; //Error: operator >> cannot be applied to double,int
    
    strMsg += "  * Bitwise Operator will cast to int first, then shifts bits (for: byte,char,short)." + "\n";
    strMsg += "    (That's why bitwise operators don't seem to work on negatives for: byte, char, and short)" + "\n";

    strMsg += "    Example with an int." + "\n";
    intTest = -1;
    strMsg += "    * " + Integer.toBinaryString( intTest ) + " = " + intTest + " (all bits are 1)" + "\n";

    intTest = intTest >>> 1;
    strMsg += "      intTest = intTest >>> 1;" + "\n";
    strMsg += "      _" + Integer.toBinaryString( intTest ) + " - intTest = " + intTest + "\n"; //Value is 2147483647
    
    strMsg += "    For a byte you would expect: " + "\n";
    strMsg += "      1111 1111 = -1 " + "\n";
    strMsg += "      0111 1111 = 127 (Expect this after shifting bits '>>> 1') " + "\n";
    strMsg += "      Warning: This is not the results !!!!! (see below)" + "\n";

    strMsg += "    Byte converted is to an int, then bitwise shift is performed." + "\n";
    byteTest = -1;
    intTest = byteTest >>> 1;
    strMsg += "      intTest = byteTest >>> 1;" + "\n";
    strMsg += "      intTest = " + intTest + " - Notice results is the same as an int. " + "\n"; //Value is 2147483647

    strMsg += "    * Note: Negative #'s & bits" + "\n";
    strMsg += "      Left most bit is the sign bit.  If set, then the number is negative." + "\n";
    strMsg += "      To determine the value of the negative #,  apply 2's compliment, then add 1." + "\n";
    strMsg += "        Steps: " + "\n";
    strMsg += "        11111111111111111111111111111111 - Bits for -1 " + "\n";
    strMsg += "        00000000000000000000000000000000 - After 2's compliment" + "\n";
    strMsg += "        00000000000000000000000000000001 - After adding 1." + "\n";
    strMsg += "        Value is -1." + "\n";

    strMsg += "  * Casting to a lower data type (int to byte)." + "\n";
    strMsg += "    The bits to the left are droped. " + "\n";
    
    intTest = 255;
    strMsg += "    " + Integer.toBinaryString( intTest ) + " - int " + intTest + "\n";
    
    byte byteTestCast;
    byteTestCast = (byte) 255;
    strMsg += "    byteTestCast = (byte) 255;" + "\n";
    strMsg += "    byteTestCast = " + byteTestCast + " - 1111 1111 as a byte = -1" + "\n";

    strMsg += "Bitwise Operators on a negative int." + "\n";
    intTest = -8;
    strMsg += "  * intTest = " + intTest + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";
    
    intTest = intTest >> 1;
    strMsg += "  * intTest = intTest >> 1; (Note: 2 Right, keep tight - negative bit)" + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest << 1;
    strMsg += "  * intTest << 1;" + "\n";
    strMsg += "  * " + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    intTest = intTest >>> 1;
    strMsg += "  * intTest >>> 1; (Note:  Negative bit is dropped.)" + "\n";
    strMsg += "  * _" + Integer.toBinaryString( intTest ) + " = " + intTest + "\n";

    return strMsg;
  }

      
  public static void main( String args [] ) {
      
      System.out.println(OperatorDemo.init_train());
      System.out.println(OperatorDemo.ex_Op_Standard() );
      System.out.println(OperatorDemo.ex_Op_Unary());
      System.out.println(OperatorDemo.ex_Op_Trinary());
      System.out.println(OperatorDemo.ex_Op_Assignment());
      System.out.println(OperatorDemo.ex_Op_Comparison());
      System.out.println(OperatorDemo.ex_Op_Objects());
      System.out.println(OperatorDemo.ex_Op_Instanceof());
      System.out.println(OperatorDemo.ex_Op_Bitwise_Boolean());
      System.out.println(OperatorDemo.ex_Op_Bitwise_Shift());


  } 

} //eoc
//eof