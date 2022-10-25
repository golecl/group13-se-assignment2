package com.group13seassignment2.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;


public class Calculator {
    // VERY IMPORTANT: DO NOT ADD A MAIN() ANYWHERE
    // the entire thing will stop working if you add a main() method in any file
    private String input;
    private String result;

    public Calculator(){
        // please don't change this constructor, it is used for the GUI
        setInput("");
        setResult("");
    }

    // validateInput function
    // Parameters: String rawInput
    // Return: String correctInput
    // CURRENTLY: just returns the raw input
    // IN THE FUTURE: Takes in raw input, checks if the input is valid
    // If input is valid then it returns it in whatever format is needed for the calculator functions
    // If input is invalid, it returns an error message
    public String validateInput(String rawInput) throws Exception{
        rawInput = rawInput.replaceAll("\\s+","");
        String correctInput = rawInput;
        
        String falseInput = "False Input";
        if(isValidString(rawInput)){
            return correctInput; 
        }
    }

    // calculate function
    // Parameters: String rawInput
    // Return: nothing
    // Takes in the raw input, calls the validateInput function
    // CURRENTLY: just sets the result to the raw input with " echoed" added to it
    // IN THE FUTURE: will call the validateInput function, and then run all the calculation functions
    // and then set the result to the answer to the equation (or an error message if input string was invalid)
    public void calculate(String rawInput){
        String result = "";
        try {
            String correctInput = validateInput(rawInput);
            result = Double.toString(BigDecimal.valueOf(eval(correctInput))
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue());
        }
        catch (Exception e) {
            result = e.getMessage();
        }

        // IMPORTANT: the result has to be set at the end of the function, otherwise nothing will show up on the webpage
        setResult(result);
    }

    public boolean isValidString(String inputString){
        Stack digitStack = new Stack<>();
        Stack charStack = new Stack<>();
        boolean isTrue = true;
        for(int i = 0;i < inputString.length();i++){
            char currentChar = inputString.charAt(i);
            if(isNumber(currentChar)){
                digitStack.push(currentChar - '0');
                if(isTrue == true){
                    isTrue = false;
                }
            else{
                return false;
                }
            }
        }
    }

    private double eval(String correctInput) {
        ArrayList<String> tokens = getTokens(correctInput);
        Stack<Double> nums = new Stack<Double>();
        Stack<String> ops = new Stack<String>();

        // each token is either a string representing an integer/double, an operator, parentheses
        for(String token : tokens) {
            if (isNumber(token)) {
                if (token.equals("e")) {
                    nums.push(Math.E);
                }
                else nums.push(Double.parseDouble(token));
            }
            else if (token.equals("(")) {
                ops.push(token);
            }
            else if (token.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    evaluateStack(nums, ops);
                }
                ops.pop();   // pop the left parentheses
            }
            else {
                // token must be an operator given that the input is valid
                while (!ops.isEmpty() && getPrecedence(ops.peek()) >= getPrecedence(token)) {
                    evaluateStack(nums, ops);
                }
                ops.push(token);
            }
        }

        while (!ops.isEmpty()) {
            evaluateStack(nums, ops);
        }

        return nums.pop();
    }

    private void evaluateStack(Stack<Double> nums, Stack<String> ops) {
        double right = nums.pop();
        double left = nums.pop();
        String op = ops.pop();
        nums.push(applyOp(left, right, op));
    }

    private int getPrecedence(String op) {
        return switch (op) {
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }

    private Double applyOp(double left, double right, String op) {
        return switch(op) {
            case "^" -> Math.pow(left, right);
            case "*" -> left * right;
            case "/" -> left / right;
            case "+" -> left + right;
            case "-" -> left - right;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }

    private ArrayList<String> getTokens(String expr) {
        ArrayList<String> tokens = new ArrayList<String>();
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
                tokens.add(Character.toString(expr.charAt(i)));
            }
            else if (expr.charAt(i) == 'e') {
                tokens.add("e");
                tokens.add("^");
                i += 2; // move index to the 'p' in "exp"
            }
            else if (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    num.append(expr.charAt(i));
                    i++;
                }
                tokens.add(num.toString());
                i--; // return index to last digit
            }
            else tokens.add(Character.toString(expr.charAt(i)));
            i++;
        }

        return tokens;
    }

    private boolean isNumber(String value) {
        if (value.equals("e")) {
            return true;
        }

        try {
            Double.parseDouble(value);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    // getter and setter functions, please do not change these
    // also please do not set or get the variables "input" and "string" in any other way

    public String getInput(){
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }
}
