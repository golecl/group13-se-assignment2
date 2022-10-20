package com.group13seassignment2.calculator;

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
    public String validateInput(String rawInput){
        String correctInput = rawInput;
        return correctInput;
    }

    // calculate function
    // Parameters: String rawInput
    // Return: nothing
    // Takes in the raw input, calls the validateInput function
    // CURRENTLY: just sets the result to the raw input with " echoed" added to it
    // IN THE FUTURE: will call the validateInput function, and then run all the calculation functions
    // and then set the result to the answer to the equation (or an error message if input string was invalid)
    public void calculate(String rawInput){
        String correctInput = validateInput(rawInput);

        // IMPORTANT: the result has to be set at the end of the function, otherwise nothing will show up on the webpage
        setResult(correctInput + " echoed");
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
