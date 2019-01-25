package com.sergeydolgozvjaga.petproject.encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Decode password
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class PasswordDecoder {

    public static String passwordDecoder(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher decryptCipher = Cipher.getInstance("AES");

        // must be 16 symbols
        String topSecretKey = "FourFourFourFour";
        SecretKeySpec key = new SecretKeySpec(topSecretKey.getBytes(), "AES");

        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = decryptCipher.doFinal(password.getBytes());

        byte[] decryptBytes = decryptCipher.doFinal(bytes);
        return new String(decryptBytes);
    }
}
