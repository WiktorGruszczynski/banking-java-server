package com.bank.server.tools;


import java.util.Base64;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TokenEncoder {
    private String base64Key = "ZQHqNRAD4Z5hzKDYQrlWqZhCUZquIixu/wtA20ipvEE=";
    private SecretKey key;

    private void init(){
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public  String encrypt(String text){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(text.getBytes());
        
            StringBuilder hexString = new StringBuilder();
            for (byte b : encryptedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (Exception e) {
            return null;
        }
    }

    public  String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            

            byte[] encryptedBytes = hexStringToByteArray(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);

        } catch (Exception e) {
            return null;
        }
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public TokenEncoder(){
        init();
    }


}