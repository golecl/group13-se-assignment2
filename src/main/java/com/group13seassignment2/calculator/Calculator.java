package com.group13seassignment2.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;


public class Calculator {
    private String input;
    private String result;

    public Calculator(){
        // Constructor used for the GUI
        setInput("");
        setResult("");
    }
    // validateInput function
    // Parameters: String rawInput
    // If input is valid then it returns it in whatever format is needed for the calculator functions
    // If input is invalid, it returns an error message
    public String validateInput(String rawInput) throws Exception{
        rawInput = rawInput.replaceAll("\\s+","");
        String correctInput = rawInput;

        String falseInput = "False Input";
        if(isValidString(rawInput)){
            return correctInput;
        }
        
        throw new Exception("Incorrect input");
    }
       
       


    public static boolean isValidString(String inputString){
        Stack digitStack = new Stack<>();
        Stack charStack = new Stack<>();
        boolean isTrue = true;
        for(int i = 0;i < inputString.length();i++){
            char currentChar = inputString.charAt(i);
            if(isLog(inputString,i) != 0) {
            	i = isLog(inputString,i);
            	digitStack.push("111");
            }
            else if(isExp(inputString,i) != 0) {
            	i = isExp(inputString,i);
            	digitStack.push("222");
            }
            else if(isNumb(currentChar)){
                if(isFloat(inputString,i)) {
                	int floatLength = howLongFloat(inputString,i);
                	double floatNumb = getFloatNumb(inputString,i,i+floatLength);
                	i = i + floatLength-1;
                	digitStack.push(floatNumb);
                }
                else {
                	int intLength = howLongInt(inputString,i);
                	int intNumb = getIntNumb(inputString,i,i+intLength);
                	i = i + intLength-1;
                	digitStack.push(intNumb);
                if(isTrue == true){
                    isTrue = false;
                }
                else{
                    return false;
                }
            }
            }
            else if(isOperator(currentChar)){
                charStack.push(currentChar);
                isTrue = true;
            }
            else{
                if(currentChar == '('){
                    charStack.push(currentChar);
                }
                else{
                    boolean failFlag = true;
                    while(charStack.isEmpty() == false){
                        char currChar = (char) charStack.pop();
                        if(currChar == '('){
                            failFlag = false;
                            break;
                        }
                        else{
                            if(digitStack.size() < 2){
                                return false;
                            }
                            else{
                                digitStack.pop();
                            }
                        }
                    }
                    if(failFlag == true){
                        return false;
                    }
                }
            }
        }
        while(charStack.isEmpty() == false){
            char currChar = (char) charStack.pop();
            if(isOperator(currChar) == false){
                return false;
            }
            if(digitStack.size() < 2){
                return false;
            }
            else{
                digitStack.pop();
            }
        }
        if(digitStack.size() > 1 || charStack.isEmpty() == false){
            return false;
        }
        return true;
    }
  
    
    /*checks if the given character is a digit.*/
    public static boolean isNumb(char numb) {
        if (numb >= '0' && numb <= '9') {
            return true;
        }
        return false;
    }
    public static boolean isOperator(char c) {
        if ( c == '/' || c == '+' || c == '-' || c == '*' || c == '^') {
            return true;
        }
        return false;
    }
    public static int isLog(String s,int n) {
    	int result = 0;
    	
    		if(s.charAt(n) == 'l'){
    			if(s.charAt(n+1) == 'o'){
    				if(s.charAt(n+2) == 'g'){
    					if(s.charAt(n+3) == '('){
    						int k = findBracket(s,n+4);
    						String subString = s.substring(n+4,(k));
    						if(isValidString(subString)) {
    							n = k;
    							if(s.charAt(n) == ')') {
    		    					return n;
    		    				}
    						}
    		    		}
    					result = 3;
    	    		}
    				result = 2;
        		}
    			result = 1;
    		}
    	
    	
    	return result;
    }
    public static int findBracket(String s,int n) {
    	int result = 0;
    	int bracketCount = 0;
    	for(int i =n;i<s.length();i++) {
    		if(s.charAt(i) == '(') {
    			bracketCount++;
    		}
    		if(s.charAt(i) == ')' && bracketCount == 0) {
    			return i;
    		}
    		if(s.charAt(i) == ')' && bracketCount != 0) {
    			bracketCount--;
    		}
    		
    	}
    	return result;
    }
    public static int isExp(String s,int n) {
    	int result = 0;
    	
    		if(s.charAt(n) == 'e'){
    			if(s.charAt(n+1) == 'x'){
    				if(s.charAt(n+2) == 'p'){
    					if(s.charAt(n+3) == '('){
    						int k = findBracket(s,n+4);
    						String subString = s.substring(n+4,(k));
    						if(isValidString(subString)) {
    							n = k;
    							if(s.charAt(n) == ')') {
    		    					return n;
    		    				}
    						}
    		    		}
    					
    					result = 3;
    	    		}
    				result = 2;
        		}
    			result = 1;
    		}
    	
    	
    	return result;
    }
    public static int howLongInt(String s,int n ) {
    	int result = 0;
    	for(int i = n;i < s.length();i++) {
    		if(isNumb(s.charAt(i))) {
    			result++;
    		}
    		else {
    			break;
    		}
    	}
    	return result;
    }
    public static int getIntNumb(String s,int startIndex,int endIndex) {
    	String result = s.substring(startIndex,endIndex);
    	return Integer.parseInt(result);
    }
    
    public static boolean isFloat(String s,int n){
    	boolean result = false;
    	boolean firstDot = false;
    	for(int i = n;i < s.length();i++) {
    		if(isNumb(s.charAt(i)) == false && s.charAt(i) != '.') {
    			break;
    		}
    		else if(isNumb(s.charAt(i))) {
    			result= true;
    		}
    		else if((s.charAt(i) == '.' && firstDot == false && s.charAt(n) != '.')){
    			firstDot = true;
    			result= true;
    		}
    		else if(isNumb(s.charAt(i)) && firstDot == true) {
    			result = true;
    		}
    		else {
    			break;
    		}
    	}
    	return result;
    }
    public static int howLongFloat(String s,int n ) {
    	int result = 0;
    	boolean firstDot = false;
    	for(int i = n;i < s.length();i++) {
    		if(isNumb(s.charAt(i)) == false && s.charAt(i) != '.') {
    			break;
    		}
    		else if(isNumb(s.charAt(n))) {
    			result++;
    		}
    		else if((s.charAt(i) == '.' && firstDot == false && s.charAt(n) != '.')){
    			firstDot = true;
    			result++;
    		}
    		else if(isNumb(s.charAt(i)) && firstDot == true) {
    			result++;
    		}
    		else {
    			break;
    		}
    	}
    	return result;
    }
    public static Double getFloatNumb(String s,int startIndex,int endIndex) {
    	String result = s.substring(startIndex,endIndex);
    	return Double.parseDouble(result);
    }
    // calculate function
    // Parameters: String rawInput
    // Return: nothing
    // Takes in the raw input, calls the validateInput function
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

    private double eval(String correctInput) throws Exception {
        ArrayList<String> tokens = getTokens(correctInput);
        Stack<Double> nums = new Stack<Double>();
        Stack<String> ops = new Stack<String>();

        // each token is either a string representing an integer/double, an operator, parentheses
        for(String token : tokens) {
            if (isNumber(token)) {
                if (token.equals("e")) {
                    nums.push(Math.E);
                }
                else if (token.equals("l")) {
                    nums.push(1.0);      // dummy value that does not get used
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

    private void evaluateStack(Stack<Double> nums, Stack<String> ops) throws Exception {
        double right = nums.pop();
        double left = nums.pop();
        String op = ops.pop();
        if (right == 0.0 && op.equals("/")) {
            throw new Exception("Math Error: division by zero is not defined.");
        }
        if (right <= 0.0 && op.equals("#")) {
            throw new Exception("Math Error: log is only valid for positive values.");
        }
        nums.push(applyOp(left, right, op));
    }

    private int getPrecedence(String op) {
        return switch (op) {
            case "^", "#" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }

    private Double applyOp(double left, double right, String op) {
        return switch(op) {
            case "#" -> Math.log(right);
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
            else if (expr.charAt(i) == 'l') {
                tokens.add("l");   // Dummy operand so we can use existing logic in evaluateStack
                tokens.add("#");   // Operator representing log
                i += 2;            // move index to the 'g' in "log"
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
        if (value.equals("e") || value.equals("l")) {
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


    // getter and setter functions

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
