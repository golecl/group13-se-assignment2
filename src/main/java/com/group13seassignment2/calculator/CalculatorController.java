package com.group13seassignment2.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String calculatorForm(Model model) {
        model.addAttribute("calculator", new Calculator());
        return "CalculatorGUI";
    }
    @PostMapping("/calculator")
    public String calculatorSubmit(@ModelAttribute Calculator calculator, Model model, @RequestParam(name = "input") String input) {
        calculator.setInput(input);
        calculator.calculate(calculator.getInput());
        model.addAttribute("calculator", calculator);

        return "Results";
    }
}
