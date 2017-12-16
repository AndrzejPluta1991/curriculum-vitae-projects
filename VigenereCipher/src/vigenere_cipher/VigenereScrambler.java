
package vigenere_cipher;

public class VigenereScrambler {
	
	private String encryptionKey = new String();
	private String plainText = new String();
	private String cipherText = new String();
	
	public VigenereScrambler(String key, String plainText) {
		encryptionKey = key.toUpperCase().trim();
		this.plainText = plainText;
	}
	
	public String getEncryptionKey() {
		return encryptionKey;
	}
	
	public void setEncryptionKey(String key) {
		encryptionKey = key.toUpperCase().trim();
	}
	
	public String getPlainText() {
		return plainText;
	}
	
	public void setPlainText(String text) {
		plainText = text;
	}
	
	public String getCipherText() {
		return cipherText;
	}
	
	public void encrypt() {
		String text = preparePlainText();
		cipherText = encryptPlainText(text);
	}
	
	private String preparePlainText() {
		String preparedText = removeNonAlphabeticChars(plainText);
		return preparedText.toUpperCase();
	}
	
	private String removeNonAlphabeticChars(String text) { 
		return text.replaceAll("[^A-Za-z]*", "");
	}
	
	private String encryptPlainText(String text) {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < text.length(); i++) {
			char keyLetter = encryptionKey.charAt(i % encryptionKey.length());
			result.append(calculateEncryptedLetter(text.charAt(i), keyLetter));
		}
		return result.toString();
	}
	
	private char calculateEncryptedLetter(char plainLetter, char keyLetter) {
		int plainLetterPosition = plainLetter - 'A';
		int keyLetterPosition = keyLetter - 'A';
		int encryptedLetter = (plainLetterPosition + keyLetterPosition) % 26;
		return (char) (encryptedLetter + 'A');
	}
}
