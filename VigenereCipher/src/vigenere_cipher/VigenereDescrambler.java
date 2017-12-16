
package vigenere_cipher;

public class VigenereDescrambler {
	
	private VigenereScrambler scrambler;
	private String decryptionKey = new String();
	
	public VigenereDescrambler(String key, String cipherText) {
		decryptionKey = key.toUpperCase().trim();
		scrambler = new VigenereScrambler(reverseKey(decryptionKey), cipherText);
	}
	
	public String getDecryptionKey() {
		return decryptionKey;
	}
	
	public void setDecryptionKey(String key) {
		decryptionKey = key.toUpperCase().trim();
		scrambler.setEncryptionKey(reverseKey(decryptionKey));
	}
	
	public String getPlainText() {
		return scrambler.getCipherText();
	}
	
	public String getCipherText() {
		return scrambler.getPlainText();
	}
	
	public void setCipherText(String text) {
		scrambler.setPlainText(text);
	}
	
	public void decrypt() {
		scrambler.encrypt();
	}
	
	private String reverseKey(String key) {
		StringBuilder reversedKey = new StringBuilder();
		for(int i = 0; i < key.length(); i++) {
			int keyLetter = (26 - (key.charAt(i) - 'A')) % 26;
			reversedKey.append((char) (keyLetter + 'A'));
		}
		return reversedKey.toString();
	}
}
