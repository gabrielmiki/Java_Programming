
/**
 * Alghorithm desined to decrypt messages written with the Ceasar cipher
 * 
 * @Gabriel Miki 
 * @07/22 
 */

import edu.duke.*;

public class CeasarCipher2 {
    public String alphabetShifter(String alphabet, int key) {
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
     
        return shiftedAlphabet;
    }
    
    public String encrypt(String message, int key) {
        // Function to encrypt a message based on index changes 
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabetShifter(alphabet, key);
        
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char currChar = message.charAt(i);
            char currCharLow = Character.toLowerCase(message.charAt(i));
            
            int currCharIndex = alphabet.indexOf(currCharLow);
            
            if (currCharIndex != -1 && currCharLow == currChar) {
                char newChar = shiftedAlphabet.charAt(currCharIndex);
                encrypted.append(newChar);
            }
            
            if (currCharIndex != -1 && currCharLow != currChar) {
                char newChar = Character.toUpperCase(shiftedAlphabet.charAt(currCharIndex));
                encrypted.append(newChar);
            }
            
            if (currCharIndex == -1) {
                encrypted.append(currChar);
            }
        }
        
        return encrypted.toString();
    }
    
    public void testEncryption() {
        int key = 15;
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        System.out.println("==============================================================================================\n");
        System.out.println("The encryption for: \n" + message + "\nIs: \n" + encrypt(message, key) + "\nWith key = " + key);
        System.out.println("\n==============================================================================================");
        System.out.println("==============================================================================================\n");
        System.out.println("The decryption for: \n" + encrypt(message, key) + "\nIs: \n" + encrypt(encrypt(message, key), 26 - key) + "\nWith key = " + (26 - key));
        System.out.println("\n==============================================================================================");
    }
    
    
    public int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        int[] alphabetCount = new int[26];
        
        for (int k = 0; k < message.length(); k++) {
            char currChar = message.charAt(k);
            
            int alphabetIndex = alphabet.indexOf(Character.toLowerCase(currChar));
            
            if (alphabetIndex != -1) {
                alphabetCount[alphabetIndex] += 1;
            }
        }
        
        return alphabetCount;
    }    
    
    public int maxIndex(int[] freqs) {
        int max = -1;
        
        int maxIndex = -1;
        for (int k = 0; k < freqs.length; k++) {
            if (freqs[k] > max) {
                max = freqs[k];
                
                maxIndex = k;
            }
        }
        
        return maxIndex;
    }
    
    public String decrypt(String encrypted) {
        int[] freqs = countLetters(encrypted);
        
        int maxDex = maxIndex(freqs);
        
        int dKey = maxDex - 4;
        
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        
        System.out.println(dKey);
        
        return encrypt(encrypted, 26 - dKey);
    }
    
    public String twoKeyDecryption(String encrypted) {
        StringBuilder output = new StringBuilder();
        StringBuilder pair = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        
        for (int k = 0; k < encrypted.length(); k++) {
            char currChar = encrypted.charAt(k);
            
            if (k % 2 == 0) {
                pair.append(currChar);
                
                odd.append(" ");
            }
            else {
                odd.append(currChar);
                
                pair.append(" ");
            }
        }
    
        String pairDecrypted = decrypt(pair.toString());
        String oddDecrypted = decrypt(odd.toString());
        
        // String pairDecrypted = encrypt(pair.toString(), 26 - 14);
        // String oddDecrypted = encrypt(odd.toString(), 26 - 24);
        
        for (int k = 0; k < encrypted.length(); k++) {
            char currChar;
            
            if (k % 2 == 0) {
                currChar = pairDecrypted.charAt(k);
            }
            else {
                currChar = oddDecrypted.charAt(k);
            }
            
            output.append(currChar);
        }
        
        return output.toString();
    }
    
    public void testTwoKeyDecryption() {
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        
        // String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!.";
        //String test = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbgbu";
        
        String twoKeyDecrypted = twoKeyDecryption(message);
        
        System.out.println("The decryption of: \n" + message + "\n" + "Is: \n" + twoKeyDecrypted);
    }
}
