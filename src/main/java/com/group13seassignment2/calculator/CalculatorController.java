package com.group13seassignment2.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    private String result = "";

    @PostMapping("/calculator")
    public String calculatorSubmit(@ModelAttribute Calculator calculator, @RequestParam(name = "expression") String expression) {
        calculator.setInput(expression);
        calculator.calculate(calculator.getInput());
        result = calculator.getResult();
        return "redirect:/calculator";
    }

    @GetMapping("/calculator")
    public String calculatorForm(Model model) {
        model.addAttribute("result", result);
        return "CalculatorGUI";
    }
}
