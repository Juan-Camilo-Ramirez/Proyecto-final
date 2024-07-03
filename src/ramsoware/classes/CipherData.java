/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ramsoware.classes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.HexFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jhoan
 */
public class CipherData {
    
    String password = "udecaldas";
    String salt = "ingudecaldas";
    SecretKey key;
    SecretKey passwordKey;
    
    public void init() {
        try {
            key = generateKey();
            passwordKey = generateKey(password, salt);
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CipherData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CipherData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CipherData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String symmetricEncrypt(String text) throws Exception {
        
        byte[] keyEncrypted = encrypt(key, text);
        byte[] passwordEncrypted = encrypt(passwordKey, text);

        System.out.println("Plain text: " + password);
        System.out.println("Key encrypted: " + HexFormat.of().formatHex(keyEncrypted));
        System.out.println("Password key encrypted: " + HexFormat.of().formatHex(passwordEncrypted));
        System.out.println("Key decrypted: " + new String(decrypt(key, keyEncrypted)));
        System.out.println("Password key decrypted: " + new String(decrypt(passwordKey, passwordEncrypted)));
        System.out.println("HMAC: " + calculateHmac(key, text));
        
        return HexFormat.of().formatHex(keyEncrypted);
    }
    
    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    
    private SecretKey generateKey(String password, String salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }
    
    private byte[] encrypt(SecretKey key, String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }
    
    private byte[] decrypt(SecretKey key, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(encrypted);
    }
    
     private String calculateHmac(SecretKey key, String text) throws Exception {
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(key);
        byte[] bytes = mac.doFinal(text.getBytes());
        return HexFormat.of().formatHex(bytes);
    }
    
}
