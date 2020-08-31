package de.bjm.rsa;

import java.util.List;

class Test {

    public static void main(String[] args) {
        Long[] keys = RSA.createKeypair();
        System.out.println("Private Key: " + keys[1] + " N: " + keys[2]);
        System.out.println("Public Key: " + keys[0] + " N: " + keys[2]);
        System.out.println();

        String message = "Hallo wie gehts?";
        System.out.println("Orig: " + message);
        List<Long> encryptedMessage = RSA.encryptMessage(message, keys[0], keys[2]);

        StringBuilder sb = new StringBuilder();
        for (long l:encryptedMessage) {
            sb.append((char) l);
        }
        System.out.println("Encrypted: " + sb.toString());

        String result = RSA.decryptMessage(encryptedMessage, keys[1], keys[2]);
        System.out.println("Result: " + result);
    }

}
