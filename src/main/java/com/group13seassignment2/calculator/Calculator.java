package com.group13seassignment2.calculator;
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
        String correctInput = rawInput.replaceAll("\\s+","");
        String falseInput = "False Input";
        if(isValidString(correctInput)){
            return correctInput;
        }
        
        throw new Exception("Incorrect input");
    }
       
       
    

    public boolean isValidString(String inputString){

        Stack digitStack = new Stack<>();
        Stack charStack = new Stack<>();
        boolean isTrue = true;
        for(int i = 0;i < inputString.length();i++){
            char currentChar = inputString.charAt(i);
            if(isNumb(currentChar)){
                digitStack.push(currentChar - '0');
                if(isTrue == true){
                    isTrue = false;
                }
                else{
                    return false;
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
            result = correctInput;
        }
        catch (Exception e) {
            result = e.getMessage();
        }

        // IMPORTANT: the result has to be set at the end of the function, otherwise nothing will show up on the webpage
        setResult(result + " echoed");
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
