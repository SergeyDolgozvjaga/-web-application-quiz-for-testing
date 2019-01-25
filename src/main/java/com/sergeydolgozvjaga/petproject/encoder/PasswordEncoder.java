package com.sergeydolgozvjaga.petproject.encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Encode password
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class PasswordEncoder {

    public static String passwordEncoder(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");

        // must be 16 symbols
        String topSecretKey = "FourFourFourFour";
        SecretKeySpec key = new SecretKeySpec(topSecretKey.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(password.getBytes());
        return new String(bytes);
    }
}
