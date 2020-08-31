package de.bjm.rsa;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class in use for creating a private and a public key with the RSA scheme
 *
 * @author Benjamin J. Meyer
 * @version 1.0
 */
class KeyPair {

    private static boolean found = false;

    /**
     * Creates a set of two RSA keys and an RSA Module
     * @return  Array of long with the following indexes
     *          0 = public key
     *          1 = private key
     *          2 = RSA Module N
     */
     static Long[] createKeypair() {
         found = false;
         while (!found) {
             try {
                 return createKeypair(ThreadLocalRandom.current().nextLong(100, 10000), ThreadLocalRandom.current().nextLong(100, 10000));
                 //return createKeypair(61, 53);
             } catch (IllegalArgumentException e) {
                 //ignore and continue iteration
             }
         }
         return new Long[]{};
    }

    /**
     * The logic that lays behind the key creation
     * @param p A random number
     * @param q Another random number
     * @return  A set of two keys and the corresponding RSA Module (N)
     * @throws IllegalArgumentException If: 1. The numbers p & q are no prims
     *                                      2. If the numbers p & q don't fit with the required space in between 0.1 < (log2(p)-log2(q) < 30
     */
    private static Long[] createKeypair(long p, long q) throws IllegalArgumentException {

        if(((log2(p)-log2(q)) < 0.1 && (log2(p)-log2(q) < 30))) {
            throw new IllegalArgumentException("Die log2 der zahlen subtrahiert mmuss größer als 0,1 und kleiner als 30 sein");
        }

        if(!isPrim(p) || !isPrim(q)) {
            throw new IllegalArgumentException("Die Zahlen müssen Primzahlen seien!");
        }

        // N = RSA MODULE
        long N = p*q;
        // φ(N) = (p-1)*(q-1)
        long eulN = kgv(p-1, q-1);


        found = true;

        //public key
        long e;
        do {
            e = ThreadLocalRandom.current().nextLong(100, 100000);
        } while (!(ggt(eulN, e) == 1));

        // Berechnung der Inverse zu e bezüglich modulu(eulN)
        // Es gilt: e * d + k * eul(N) = 1 = ggt(e, N)

        //private key
        long d = modInverse(e, eulN);


        return new Long[]{e, d, N};
    }

    //UTILITIES


    //???MODULO INVERSE???

    /**
     * Creates the "Modular Inverse" for a (mod m)
     * The modular inverse of A (mod M) is A^-1
     * (A * A^-1) ≡ 1 (mod C) or equivalently (A * A^-1) mod C = 1
     * @param a The number a
     * @param m The modulo
     * @return  The calculated inverse
     */
    private static long modInverse(long a, long m)
    {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    /**
     * Checks if the number value if a prim
     * @param value The number to check
     * @return      true = prim, false = no prim
     */
    private static boolean isPrim(final long value) {
        if (value <= 2) {
            return (value == 2);
        }
        for (long i = 2; i * i <= value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the logarithm base 2
     * @param i The number to calculate the logarithm of
     * @return  The log2(i)
     */
    private static double log2(double i) {
        return Math.log(i)/Math.log(2);
    }

    /**
     * greatest common divisor
     * Calculates the greatest common divisor of a and b
     * @param a Number a
     * @param b Number b
     * @return  The gcd of a and b
     */
    private static long ggt(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    /**
     * last common multiple
     * Calculates the last common multiple of a and b
     * @param a Number a
     * @param b Number b
     * @return  The gcd od a and b
     */
    private static long kgv(long a, long b)
    {
        return a * (b / ggt(a, b));
    }

}
