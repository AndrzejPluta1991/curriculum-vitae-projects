package vigenere_cipher;

public class Data {
	
	private String key;
	private String plainText;
	private String encryptedText;
	
	public Data() {
		key = new String("deceptge");
		plainText = new String();
		encryptedText = new String();
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getPlainText() {
		return plainText;
	}
	
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	
	public String getEncryptedText() {
		return encryptedText;
	}
	
	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}
}
