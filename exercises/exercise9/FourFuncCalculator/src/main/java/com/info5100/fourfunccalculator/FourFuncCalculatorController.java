package com.info5100.fourfunccalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FourFuncCalculatorController {
    @FXML
    private Label result;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;

    private int resultNum;
    private int inputNum1;
    private int inputNum2;
    private String calMethod;


    protected void getResultNum() {
        result.setText(input1.getText()+" "+calMethod+" "+input2.getText()+" equals "+resultNum);
    }
    @FXML
    protected void onClearBtnClick(){
        result.setText("");
        input1.clear();
        input2.clear();
        inputNum1 = 0;
        inputNum2 = 0;
        resultNum = 0;
        calMethod = "";
    }
    @FXML
    protected void multiplication(){
        calMethod = "times";
        inputNum1 = Integer.parseInt(input1.getText());
        inputNum2 = Integer.parseInt(input2.getText());
        resultNum = inputNum1 * inputNum2;
        getResultNum();
    }
    @FXML
    protected void division(){
        calMethod = "divided by";
        inputNum1 = Integer.parseInt(input1.getText());
        inputNum2 = Integer.parseInt(input2.getText());
        resultNum = inputNum1/inputNum2;
        getResultNum();
    }
    @FXML
    protected void plus(){
        calMethod = "plus";
        inputNum1 = Integer.parseInt(input1.getText());
        inputNum2 = Integer.parseInt(input2.getText());
        resultNum = inputNum1 + inputNum2;
        getResultNum();
    }
    @FXML
    protected void minus(){
        calMethod = "minus";
        inputNum1 = Integer.parseInt(input1.getText());
        inputNum2 = Integer.parseInt(input2.getText());
        resultNum = inputNum1 - inputNum2;
        getResultNum();
    }
}