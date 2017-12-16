package vigenere_cipher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
	
	private Data data = new Data();
	@FXML private TextField pathOfPlainTextFile;
	@FXML private TextField keyTextField;
	@FXML private TextField pathOfEncryptedTextFile;
	@FXML private TextArea plainTextArea;
	@FXML private TextArea encryptedTextArea;
	
	public Controller() {
	}
	
	@FXML
	private void readPlainTextFromFile(ActionEvent event) {
		try {
			String plainText = readTextFromFile(this.pathOfPlainTextFile.getText());
			data.setPlainText(plainText);
			this.plainTextArea.setText(plainText);
		} catch(IOException e) {
			System.out.println("File error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	@FXML
	private void savePlainTextToFile(ActionEvent event) {
		data.setPlainText(this.plainTextArea.getText());
		try {
			writeTextIntoFile(this.pathOfPlainTextFile.getText(),
							  breakTextIntoLines(data.getPlainText(), 50));
		} catch(IOException e) {
			System.out.println("File error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	@FXML
	private void encryptText(ActionEvent event) {
			data.setKey(this.keyTextField.getText());
			data.setPlainText(this.plainTextArea.getText());
			
			VigenereScrambler scrambler = new VigenereScrambler(data.getKey(), 
					data.getPlainText());
			
			scrambler.encrypt();
			String cipheredText = scrambler.getCipherText();
			data.setEncryptedText(cipheredText);
			this.encryptedTextArea.setText(cipheredText);;
	}
	
	@FXML
	private void decryptText(ActionEvent event) {
		data.setKey(this.keyTextField.getText());
		data.setEncryptedText(this.encryptedTextArea.getText());
		
		VigenereDescrambler descrambler = new VigenereDescrambler(data.getKey(), 
				data.getEncryptedText());
		
		descrambler.decrypt();
		String decryptedText = descrambler.getPlainText();
		data.setPlainText(decryptedText);
		this.plainTextArea.setText(decryptedText);
	}
	
	@FXML
	private void readEncryptedTextFromFile(ActionEvent event) {
		data.setEncryptedText(this.encryptedTextArea.getText());
		try {
			String encryptedText = readTextFromFile(this.pathOfEncryptedTextFile.getText());
			data.setEncryptedText(encryptedText);
			this.encryptedTextArea.setText(encryptedText);
		} catch(IOException e) {
			System.out.println("File error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	@FXML
	private void saveEncryptedTextToFile(ActionEvent event) {
		data.setEncryptedText(this.encryptedTextArea.getText());
		try {
			writeTextIntoFile(this.pathOfEncryptedTextFile.getText(), 
							  breakTextIntoLines(data.getEncryptedText(), 50));
		} catch (IOException e) {
			System.out.println("File error: " + e.getMessage());
			System.exit(1);
		}
	}
	
	private String readTextFromFile(String path) throws IOException {
		List<String> fileContent = Files.readAllLines(Paths.get(path),
													  StandardCharsets.UTF_8);
		StringBuilder result = new StringBuilder();
		for(String line : fileContent) {
			result.append(line);
		}
		return result.toString();
	}
	
	private void writeTextIntoFile(String path, List<String> output)
			throws IOException {
		Path filePath = Paths.get(path);
		
		Files.write(filePath, output, StandardCharsets.UTF_8, StandardOpenOption.WRITE,
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	
	private List<String> breakTextIntoLines(String input, int lineLength) {
		List<String> output = new ArrayList<>();
		for(int i = 0; i < input.length(); i += lineLength) {
			if(i + lineLength < input.length()) {
				output.add(input.substring(i, i + lineLength));
			} else {
				output.add(input.substring(i).trim());
			}
		}
		return output;
	}
}
