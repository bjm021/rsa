package de.bjm.rsa;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the RSA algorithm in Java
 *
 * @author Benjamin J. Meyer
 * @version 1.0
 */
public class RSA {

    /**
     * Encrypts a String by splitting it to characters and encode them
     * @param message   The message to encrypt
     * @param publicKey The key in use for encrypting (public key)
     * @param rsaModule The RSA Module (N) of the key
     * @return          A {@link List<Long>} with Long(s) representing the char's of the message.
     *                  The chars are in the correct order to represent the string
     */
    public static List<Long> encryptMessage(String message, Long publicKey, Long rsaModule) {
        List<Long> encodedMessage = new ArrayList<>();
        char[] messageChars = message.toCharArray();
        for (char c:messageChars) {
            long origCharValue = (long) c;
            long enCharValue = Crypto.enCrypt(origCharValue, publicKey, rsaModule);
            encodedMessage.add(enCharValue);
        }
        return encodedMessage;
    }

    /**
     * Decrypts a {@link List} of Long(s) that represent encoded chars in the correct order for a String
     * @param encryptedMessage  The List of encoded chats (as Long(s))
     * @param privateKey        The key in use for decrypting (private key)
     * @param rsaModule         The RSA Module (N) of the key
     * @return                  The decoded message
     */
    public static String decryptMessage(List<Long> encryptedMessage, Long privateKey, Long rsaModule) {
        StringBuilder sb = new StringBuilder();
        for (Long enCharValue:encryptedMessage) {
            long deCharValue = Crypto.deCrypt(enCharValue, privateKey, rsaModule);
            char deChar = (char) deCharValue;
            sb.append(deChar);
        }
        return sb.toString();
    }

    /**
     * Encrypts a int
     * @param i         The Integer to encrypt
     * @param publicKey The key in use for encrypting (public key)
     * @param rsaModule The RSA Module (N) of the key
     * @return          The encrypted int
     */
    public static Integer encryptInt(int i, Long publicKey, Long rsaModule) {
        return (int) Crypto.enCrypt(i, publicKey, rsaModule);
    }

    /**
     * Decrypts a int
     * @param i         The Integer to decrypt
     * @param privateKey The key in use for decrypting (private key)
     * @param rsaModule The RSA Module (N) of the key
     * @return          The decrypted int
     */
    public static Integer decryptInt(int i, Long privateKey, Long rsaModule) {
        return (int) Crypto.deCrypt(i, privateKey, rsaModule);
    }

    /**
     * Creates a set of two RSA keys and an RSA Module
     * @return  Array of long with the following indexes
     *          0 = public key
     *          1 = private key
     *          2 = RSA Module N
     */
    public static Long[] createKeypair() {
        return KeyPair.createKeypair();
    }

}
