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
        Calculator testCalculator = new Calculator();
        testCalculator.calculate("sample input");
        assertEquals("sample input echoed", testCalculator.getResult());
    }
}
