/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ramsoware.classes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jkram
 */
public class CipherUserData {
    
    Cipher cipher = null;
    String key = "AnonymusMasCrack";//Llave de 32 caracteres. Puede ser de 16c
    //String key = "QW5vbnltdXNNYXNDcmFjaw==";//Llave de 32 caracteres. Puede ser de 16c
    
    public String encrypt(String plainText) {
        String encryptedText = null;
        try {
            String base64Key = new String(Base64.getEncoder().encode(key.getBytes()));
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            //byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println(base64Key);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException 
                    | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(CipherUserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedText;
    }
    
    public String decrypt(String encryptedText, String keyToDecrypt) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(keyToDecrypt);
            SecretKey keyDecrypt = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keyDecrypt);
            
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            
            return new String(decryptedBytes, "UTF-8");
            
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException 
                    | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(CipherUserData.class.getName()).log(Level.SEVERE, null, ex);
            return "Error decrypting the text: " + ex.getMessage();
        }
    }
    
}
