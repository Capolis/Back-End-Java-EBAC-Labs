package br.com.ebac.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DatabaseCryptoUtil {

    private static final String SECRET_KEY = "My16ByteSecretKy"; 
    private static final String ALGORITHM = "AES";

    public static String encryptString(String rawData) throws Exception {
        SecretKeySpec keySpecification = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipherInstance = Cipher.getInstance(ALGORITHM);
        
        cipherInstance.init(Cipher.ENCRYPT_MODE, keySpecification);
        byte[] encryptedBytes = cipherInstance.doFinal(rawData.getBytes());
        
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptString(String encryptedData) throws Exception {
        SecretKeySpec keySpecification = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipherInstance = Cipher.getInstance(ALGORITHM);
        
        cipherInstance.init(Cipher.DECRYPT_MODE, keySpecification);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipherInstance.doFinal(decodedBytes);
        
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String databasePassword = "minhasenhaquenaoquerocompartilhar";
            
            String encryptedPassword = encryptString(databasePassword);
            System.out.println("\nCopie o valor abaixo para o seu arquivo .properties");
            System.out.println("db.password: " + encryptedPassword);
            
            String decryptedPassword = decryptString(encryptedPassword);
            System.out.println("\nValidando descriptografia: " + decryptedPassword.equals(databasePassword));
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}