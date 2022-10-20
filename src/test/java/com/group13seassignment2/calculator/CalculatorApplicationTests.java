package com.group13seassignment2.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculatorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCalculate(){
        assertEquals(0, CalculatorApplication.calculate("12313"));
    }
}
