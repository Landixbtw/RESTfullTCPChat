package com.mws.guessgame;

import javax.crypto.*;
import java.util.Base64;

/**
 * @author Markus Schäffter, 2024
 */
public class Model {
    /**
     * @author Almas Baimagambetov (almaslvl@gmail.com)
     * changed by Markus Schäffter
     */

    private String pass = "123secret456789";
    private String salt = "MySalt";

    public Model()  {
    }


    public String encodeBase64URL(String plainText) {

        String raw = AES256.encrypt(plainText, pass, salt);
        Base64.Encoder encoder = Base64.getUrlEncoder();
        String cipherText = encoder.encodeToString(raw.getBytes());
        return (cipherText);
    }

    public String decodeBase64URL(String cipherText) throws IllegalBlockSizeException, BadPaddingException {

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String decoded = new String(decoder.decode(cipherText));
        String decipheredText = AES256.decrypt(decoded, pass, salt);
        return (decipheredText);
    }

    public String init(int min, int max) {
        int rand = min + (int)(Math.random() * ((max - min) + 1));
        String ref = encodeBase64URL(String.valueOf(rand));
        return (ref);

    }

    public String guess(String ref, int guess) throws IllegalBlockSizeException, BadPaddingException {
        String ret = "";
        int realRef = Integer.parseInt(decodeBase64URL(ref));
        if (realRef == guess) {ret = "OK";}
        if (realRef < guess) {ret = "Guess smaller";}
        if (realRef > guess) {ret = "Guess larger";}
        return ret;
    }
}
