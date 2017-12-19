package calculator_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorController {
	
	@FXML private TextField currentNumber = new TextField();
	private String firstComponentOfEquation = "0";
	private String secondComponentOfEquation = "0";
	private String arithmeticCommand = "";
	private String lastCommand = "";
	private boolean newEntry = true;
	private boolean decimalPoint = false;
	
	public CalculatorController() {
	}
	
	@FXML
	protected void insertNewDigitAction(ActionEvent event) {
		if((newEntry == true) || currentNumber.getText().equals("0")) {
			currentNumber.setText("");
			newEntry = false;
		}
		currentNumber.setText(currentNumber.getText() + ((Button)event.getSource()).getText());
	}
	
	@FXML
	protected void decimalPointAction(ActionEvent event) {
		if(decimalPoint == false) {
			if(newEntry == true) {
				currentNumber.setText("0");
				newEntry = false;
			}
			currentNumber.setText(currentNumber.getText() + ".");
			decimalPoint = true;
		}
	}
	
	@FXML
	protected void negateAction(ActionEvent event) {
		double negativeNumber = Double.parseDouble(currentNumber.getText());
		
		if(negativeNumber != 0) {
			negativeNumber = -negativeNumber;
		}
		
		if(isFraction(negativeNumber)) {
			currentNumber.setText(String.valueOf(negativeNumber));
		} else {
			String formattedString = String.format("%.0f", negativeNumber);
			currentNumber.setText(formattedString);
		}
	}
	
	@FXML
	protected void backspaceAction(ActionEvent event) {
		String backspace = null;
		if(currentNumber.getText().length() > 1) {
			StringBuilder stringBuilder = new StringBuilder(currentNumber.getText());
			stringBuilder.deleteCharAt(currentNumber.getText().length() - 1);
			backspace = stringBuilder.toString();
			currentNumber.setText(backspace);
		}
		else {
			if(currentNumber.getText().length() == 1) {
				currentNumber.setText("0");
			}
		}
	}
	
	@FXML
	protected void resetAction(ActionEvent event) {
		firstComponentOfEquation = "0";
		secondComponentOfEquation = "0";
			
		newEntry = true;
		decimalPoint = false;
		arithmeticCommand = "";
		lastCommand = "";
		
		currentNumber.setText("0");
	}
	
	@FXML
	protected void arithmeticAction(ActionEvent event) {
		arithmeticCommand = ((Button)event.getSource()).getText();
		firstComponentOfEquation = currentNumber.getText();
		
		newEntry = true;
		decimalPoint = false;
		
		lastCommand = arithmeticCommand;
	}
	
	@FXML
	protected void equalsAction(ActionEvent event) {
		if(lastCommand == "=") {
			firstComponentOfEquation = currentNumber.getText();
		}
		else {
			secondComponentOfEquation = currentNumber.getText();
			lastCommand = "=";
		}
		
		double result = 0;
		result = calculate(arithmeticCommand, firstComponentOfEquation, secondComponentOfEquation);
		
		if(isFraction(result)) {
			Double toBeTruncated = new Double(result);
			Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
				    .setScale(6, RoundingMode.HALF_UP).doubleValue();
			currentNumber.setText(String.valueOf(truncatedDouble));
		} else {
			String formattedString = String.format("%.0f", result);
			currentNumber.setText(formattedString);
		}
		
		newEntry = true;
		decimalPoint = false;
	}
	
	private boolean isFraction(double result) {
		if(result == Math.floor(result)) {
			return false;
		} else {
			return true;
		}
	}
	
	private double calculate(String arithmeticCommand, 
			String firstComponent, String secondComponent) {
		double result = 0;
		switch(arithmeticCommand) {
			case "+": {
				result = Double.parseDouble(firstComponent) + Double.parseDouble(secondComponent);
				break;
			}
			case "-": {
				result = Double.parseDouble(firstComponent) - Double.parseDouble(secondComponent);
				break;
			}
			case "*": {
				result = Double.parseDouble(firstComponent) * Double.parseDouble(secondComponent);
				break;
			}
			case "/": {
				result = Double.parseDouble(firstComponent) / Double.parseDouble(secondComponent);
				break;
			}
			case "mod": {
				result = Double.parseDouble(firstComponent) % Double.parseDouble(secondComponent);
				break;
			}
		}
		
		if(result == 0) {
			result = 0;
		}
		
		return result;
	}
}
