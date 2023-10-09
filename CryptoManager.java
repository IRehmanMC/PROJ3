/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
/*
 * Class: CMSC203 
 * Instructor: Dr. Kuijt 
 * Description: de/encrypter
 * Due: 10/08/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: Ilyas Rehman
*/

public class CryptoManager {
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds(String plainText) 
	{
	
		for(int x = 0; x < plainText.length(); x++)
		{
			if(plainText.charAt(x) > UPPER_RANGE || plainText.charAt(x) < LOWER_RANGE)
			{
				return false;
			}
		
		}
		
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
        StringBuilder encrypted = new StringBuilder();
        //reduce key
        key =  key % RANGE;

        if(isStringInBounds(plainText)) {
            for (int charNum = 0; charNum < plainText.length(); charNum++) {
                //generate new char and append to string
                char encryptChar = (char)((plainText.charAt(charNum) + key - LOWER_RANGE)%RANGE + LOWER_RANGE);
                encrypted.append(encryptChar);
            }
            return encrypted.toString();
        }
        //if string not in bounds return error
        return "The selected string is not in bounds, Try again.";
    }
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
        StringBuilder encrypted = new StringBuilder();
        if(isStringInBounds(plainText)) {
            for (int charNum = 0, encryptCharNum = 0; charNum < plainText.length(); charNum++, encryptCharNum++) {
                //get current key from string and reduce
                encryptCharNum = encryptCharNum % bellasoStr.length();
                int offset = bellasoStr.charAt(encryptCharNum) % RANGE;
                //modify character and append
                char encryptChar = (char)((plainText.charAt(charNum) + offset - LOWER_RANGE)%RANGE + LOWER_RANGE);
                encrypted.append(encryptChar);
            }
            return encrypted.toString();
        }
        return "The selected string is not in bounds, Try again.";
    }
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
        //reduce key
        key = key % RANGE;
        StringBuilder decrypted = new StringBuilder();
        for (int charNum = 0; charNum < encryptedText.length(); charNum++) {
            //reverse encryption addition
            char encryptChar = (char)((encryptedText.charAt(charNum) - key - LOWER_RANGE + RANGE)%RANGE + LOWER_RANGE);
            decrypted.append(encryptChar);

        }
        return decrypted.toString();
    }
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
        StringBuilder decrypted = new StringBuilder();
        for (int charNum = 0, decryptCharNum = 0; charNum < encryptedText.length(); charNum++, decryptCharNum++) {
            //gets current key from string and reduces
            decryptCharNum = decryptCharNum % bellasoStr.length();
            int offset = bellasoStr.charAt(decryptCharNum) % RANGE;
            //reverse encryption addition
            char decryptChar = (char)((encryptedText.charAt(charNum) - offset - LOWER_RANGE + RANGE)%RANGE + LOWER_RANGE);
            decrypted.append(decryptChar);
        }
        return decrypted.toString();
    }
}

