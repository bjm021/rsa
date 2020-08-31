package de.bjm.rsa;

/**
 * Class in use for de and encrypting longs with the RSA algorithm
 *
 * @author Benjamin J. Meyer
 * @version 1.0
 */
class Crypto {

    /**
     * Encrypts a long
     * @param m                         The long to encrypt
     * @param e                         The key in use for encrypting (public key)
     * @param N                         The RSA Module (N) of the key
     * @return                          The encrypted long
     * @throws IllegalArgumentException If the arguments don't fit the requirements
     */
    public static long enCrypt(long m, long e, long N) throws IllegalArgumentException {
        // c === m^e (mod N)
        long c;
        if(m >= N) {
            throw new IllegalArgumentException("The message N must be smaller than the RSA Module N = " + N);
        }

        c = modExp(m, e, N);

        return c;
    }

    /**
     * Decrypts a long
     * @param c The long to decrypt
     * @param d The key in use for decrypting (private key)
     * @param N The RSA Module (N) of the key
     * @return  The decrypted long
     */
    public static long deCrypt(long c, long d, long N) {
        return modExp(c, d, N);
    }

    //utilities

    /**
     *  Modular exponentiation
     *  The heart of RSA
     *  statement: c ≡ m^e (mod. N)
     */
    private static long modExp(long x, long y, long p)
    {
        // Initialize result
        long res = 1;

        // Update x if it is more
        // than or equal to p
        x = x % p;

        while (y > 0)
        {
            // If y is odd, multiply x
            // with result
            if((y & 1)==1)
                res = (res * x) % p;

            // y must be even now
            // y = y / 2
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

}
