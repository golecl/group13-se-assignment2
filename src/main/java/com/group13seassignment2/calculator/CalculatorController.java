package com.group13seassignment2.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String CalculatorGUI() {
        return "CalculatorGUI";
    }
}
