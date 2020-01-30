import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.PBEKeySpec;
import javax.crypto.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

public class EncodeDecode {
    private PBEKeySpec pbeKeySpecification = null;
    private PBEParameterSpec pbeParameterSpecification = null;
    
    public static void main (String [] args) throws Exception {
        String secretKeyPhrase = "ChangeMe";
        String passwordClearText = "newpassword";
        System.out.println("passwordClearText = " + passwordClearText);
        
        EncodeDecode encodeDecode = new EncodeDecode(secretKeyPhrase);
        
        String encryptedPassword = symCipher.encrypt(passwordClearText);
        System.out.println("encryptedPassword = " + encryptedPassword);
        
        String decryptedPassword = symCipher.decrypt(encryptedPassword);
        System.out.println("decryptedPassword = " + decryptedPassword);
        
        if (decryptedPassword.equals(passwordClearText) {
           System.out.println("Success: Values match");
        } else {
           System.out.println("Error: Values different");
        }
    }
    
    /**
     *  Constructor accepting a seed value
     * 
     *  @param secretKeyPhrase the seed value to use to encrypt and decrypt values.
     * 
     **/
    public EncodeDecode(String stringSeed){
        int intIterartionCount = 20;
        byte[] bytesSalt = {(byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99};
        
        if((stringSeed == null) || stringSeed.trim().length() < 1){
            stringSeed = "ChangeMe";
        }
        
        pbeKeySpecification = new PBEKeySpec(stringSeed.toCharArray(), bytesSalt, intIterartionCount);
        pbeParameterSpecification = new PBEParameterSpec(bytesSalt, intIterartionCount);
    }
    
    /**
     *  Decrypt the encrypted String
     * 
     *  @param stringEncrypted the encrypted String
     *  @return the decrypted String
     * 
     */
    public String decrypt(String stringEncrypted){
        if((stringEncrypted != null) && !stringEncrypted.isEmpty()){
            System.out.println("stringEncrypted = " + stringEncrypted);
        }
        
        try{
            final byte[] bytes = stringEncrypted != null ? Base64.decodeBase64(stringEncrypted) : new byte[0];
            SecretKeyFactory secretKeyFactory = new SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey secretKey = new SecretKeyFactory.generateSecret(pbeKeySpecification);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpecification);
            String stringDecrypted = new String(pbeCipher.doFinal(bytes), "UTF8");
            if (stringDecrypted != null && stringDecrypted.length() > 0){
                System.out.println("stringDecrypted = " + stringDecrypted);
            }
            return stringDecrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     *  Encrypt a clear text String.
     *  @param stringDecrypyted clear text to encrypt
     *  @return the encrypted String.
     * 
     */
    public String encrypt(String stringDecrypyted){
        try{
            System.out.println("stringDecrypted = " + stringDecrypted);
            final byte[] bytesToEncrypt = stringDecrypyted != null ? stringDecrypyted.getBytes("UTF8") : new byte[0];
            SecretKeyFactory secretKeyFactory = new SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey secretKey = new SecretKeyFactory.generateSecret(pbeKeySpecification);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpecification);
            String stringValueEncrypted = new String(Base64.encodeBase64(pbeCipher.doFinal(bytesToEncrypt)));
            if (stringValueEncrypted != null && stringValueEncrypted.length() > 0){
                System.out.println("stringValueEncrypted = " + stringValueEncrypted);
            }
            return stringValueEncrypted;
            
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
