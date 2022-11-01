package com.group13seassignment2.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void contextLoads() {
    }

    // Please do not change anything above this comment (other than adding imports), it's essential
    // for the whole thing to work properly!

    //Tests for calculate without stringValidation.
 
    @Test
    void testCalculate() {
        Calculator calc = new Calculator();
        String div0 = "Math Error: division by zero is not defined.";
        String badLog = "Math Error: log is only valid for positive values.";
        String[][] testValues = {{"6/2*(1+2)", "6/(2*(1+2))", "4^4",   "(3+5*8^4.2)/(5+7)", "1/0", "log(9^2/exp(3))", "log(0)", "exp(0)", "181^0", "2/2-2"},
                                 {"9.0",        "1.0",        "256.0", "2587.073",          div0,   "0.606",           badLog,   "1",       "1",    div0}};

        for (int i = 0; i < testValues[0].length; i++) {
            String result = unitTestCalc(testValues[0][i], calc);
            assertEquals(testValues[1][i],result);
        }
    }
    //test for String Validation. These test assume that if the string has whitespace the function will remove them. It also assumes that 
    //upon an invalid input the error message, "Error: invalid input", will be returned.

    @Test
    void testIsValidString(){
        String er = "Error: invalid input";
        Calculator calc = new Calculator();
        String [][] testValues ={{"1  +  1", ")4+4(", "3+**8", "hello", "1!", "@", "~", "+" },
        {"1+1", er, er, er, er,er, er, er}};

        for(int i = 0; i < testValues[0].length; i++)
        {
            String validated = unitTestValidString(testValues[0][i], calc);
            assertEquals(testValues[1][i], validated);
        }

    }

    String unitTestValidString(String expr, Calculator calc){
        String validatedString;
        try {
            validatedString = calc.validateInput(expr);
        } catch (Exception e) {
            validatedString = "Error: invalid input";
        }
        return validatedString;
    }

    String unitTestCalc(String expr, Calculator calc){
        calc.calculate(expr);
        return calc.getResult();
    }



}
