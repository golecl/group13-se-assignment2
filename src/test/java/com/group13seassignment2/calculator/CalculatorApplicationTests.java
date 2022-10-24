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

    @Test
    void testCalculate(){
        Calculator calc = new Calculator();
        String [][] testValues = {{"6/2*(1+2)","6/(2*(1+2))","3+5*exp(4.2)/(5+7)"},{"9.0","1.0","30.786"}};
        for(int i = 0; i < testValues[0].length; i++)
        {
            String result = unitTestCalc(testValues[0][i], calc);
            System.out.println(result + "  " + testValues[1][i]);
            assertEquals(result, testValues[1][i]);
        }
        
    }

    String unitTestCalc(String expr, Calculator calc){
        calc.calculate(expr);
        return calc.getResult();
    }



}
