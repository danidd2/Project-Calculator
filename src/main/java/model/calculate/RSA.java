package model.calculate;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * Class aimed to encrypt a BigInteger, also contains a prime generator.
 */
@Slf4j
public class RSA
{

    /**
     *Object capable of calculating modulo powers.
     */
    private ModPower modPower = new ModPower();
    /**
     * Prime finder object.
     */
    private Prime prime = new Prime();
    /**
     * Extended euclides calculator object.
     */
    private Eucledes eucledes = new Eucledes();

    /**
     * The method combining the result and all the required data into a string for
     * further use.
     * @param massage the "massage" to be converted
     * @return the String containing all the required data and the result
     */
    public String rsaCore(String massage)
    {
        int size = 1024;
        log.info("Searching for the first prime");
        BigInteger prime1 = bigPrimeFinder(size);
        log.info("Firt prime found " + prime1);
        log.info("Searching for the second prime");
        BigInteger prime2 = bigPrimeFinder(size);
        log.info("Second prime found " + prime2);
        BigInteger n = prime1.multiply(prime2);
        BigInteger Fn = prime1.subtract(BigInteger.ONE).multiply(prime2.subtract(BigInteger.ONE));
        log.info("Fn " + Fn + " n " + n);
        log.info("Creating the encrypter");
        BigInteger encrypter = generateE(Fn,size);
        log.info("Creating the decrypter");
        BigInteger decrypter = generateD(Fn,encrypter);
        log.info("Both the necrypter and decrypter are ready to use");

        BigInteger encryptedMassage = encrypt(massage,encrypter,n);
        BigInteger decryptedMassage = decrypter(encryptedMassage,decrypter,n);
        BigInteger cineseReturn = chinese(prime1,prime2,encryptedMassage,decrypter);

        String ret = "First prime is " + prime1 + "\n" +
                    "Second prime is " + prime2 + "\n" +
                    "The encrypter is " + encrypter + "\n" +
                    "The decrypter is " + decrypter + "\n"+
                    "The original massage is " + massage + "\n" +
                    "The encrypted massage is " + encryptedMassage + "\n" +
                    "The decrypted massage is " + decryptedMassage + "\n" +
                    "The chines decrypted massage is " + cineseReturn;
        return ret;
    }


    /**
     * Generates a prime with the given binary size.
     * @param size the binary size of the prime
     * @return the prime in a BigInteger format
     */
    public BigInteger bigPrimeFinder(int size)
    {
        BigInteger rnd = RandomBigInteger(size);
        while (true)
        {
            if (prime.isPrime(rnd)) {
                break;
            }
            rnd = RandomBigInteger(size);
        }
        return rnd;
    }

    /**
     * Generates a BigInteger with the given size.
     * @param size contains the binary size
     * @return returns teh BIgInteger
     */
    private BigInteger RandomBigInteger(int size)
    {
        BigInteger ret = BigInteger.valueOf(0);
        BigInteger two = BigInteger.TWO;
        Random random = new Random();
        for(int i = 0;i<size;i++)
        {
            byte temp = (byte)random.nextInt(2);
            if (temp == 1)
            {
                ret = ret.add(two.pow(i));
            }
        }
        return ret;
    }

    /**
     * Generates the encryter, a random number relative prime to Fn.
     * @param Fn number of relative primes till n
     * @param size the size of the number
     * @return a BigInteger which is relative prime to n
     */
    private BigInteger generateE(BigInteger Fn,int size)
    {
        BigInteger encrypter = RandomBigInteger(size-1);
        while (true)
        {
            if (prime.gcd(encrypter,Fn).compareTo(BigInteger.ONE) == 0)
            {
                break;
            }
            else
            {
                encrypter = encrypter.add(BigInteger.ONE);
            }
        }
        return encrypter;
    }

    /**
     * Generates the decrypter by running an extended eulides algorithm on Fn and the encrypter.
     * @param Fn the number of relative primes to n till n
     * @param encrypter the encrypter
     * @return the decrypter
     */
    private BigInteger generateD(BigInteger Fn, BigInteger encrypter)
    {
        Eucledes result = eucledes.calculate(Fn,encrypter);
        BigInteger decrypter =result.getY();
        if(decrypter.compareTo(BigInteger.ZERO)==-1)
        {
            decrypter = decrypter.add(Fn);
        }
        return decrypter;
    }

    /**
     * Encrypts the massage.
     * @param massage contains the massage to be converted
     * @param encrypter contains the encrypter
     * @param n contains the multiplication of the generated primes (p*q)
     * @return the encrypted massage
     */
    private BigInteger encrypt(String massage,BigInteger encrypter,BigInteger n)
    {
        BigInteger temp = new BigInteger(massage);
        temp = modPower.modpower(temp,encrypter,n);
        return temp;
    }

    /**
     * Decrypts the massage.
     * @param massage massage contains the massage to be converted
     * @param decrypter contains the decrypter
     * @param n contains the multiplication of the generated primes (p*q)
     * @return the decrypted massage
     */
    private BigInteger decrypter(BigInteger massage,BigInteger decrypter,BigInteger n)
    {
        BigInteger temp = massage;
        temp = modPower.modpower(temp,decrypter,n);
        return temp;
    }

    /**
     * Decrypts a {@code massage} with the chinese remain algorithm.
     * @param p first prime
     * @param q second prime
     * @param massage the massage to be decrypted
     * @param decrypter the decrypter
     * @return the decrypted massage
     */
    private BigInteger chinese(BigInteger p, BigInteger q,BigInteger massage,BigInteger decrypter)
    {
        BigInteger c1 = modPower.modpower(massage,decrypter.mod(p.subtract(BigInteger.valueOf(1))),p);
        BigInteger c2 = modPower.modpower(massage,decrypter.mod(q.subtract(BigInteger.valueOf(1))),q);
        BigInteger M = p.multiply(q);
        BigInteger M1 = q;
        BigInteger M2 = p;
        Eucledes calc = new Eucledes();
        Eucledes euclid = calc.calculate(q,p);
        BigInteger y1 = euclid.getX();
        BigInteger y2 = euclid.getY();

        BigInteger m = c1.multiply(y1).multiply(M1).add(c2.multiply(y2).multiply(M2)).mod(M);

        return m;
    }
}
