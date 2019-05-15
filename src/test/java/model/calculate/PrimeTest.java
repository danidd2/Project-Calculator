package model.calculate;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeTest {

    @Test
    void isPrime()
    {
        Prime calculator = new Prime();
        BigInteger[] primes ={new BigInteger("7"), new BigInteger("11"),new BigInteger("17"),new BigInteger("31")};
        BigInteger[] notprimes = {new BigInteger("8"),new BigInteger("33"),new BigInteger("49"),new BigInteger("91")};
        for(BigInteger p : primes)
        {
            assertEquals(true,calculator.isPrime(p));
        }
        for (BigInteger p : notprimes)
        {
            assertEquals(false,calculator.isPrime(p));
        }
    }

    @Test
    void getBase()
    {
        Prime calculator = new Prime();
        BigInteger big =new  BigInteger("11819198332725551189");
        int test  = calculator.getBase(big,5);
        assertEquals(BigInteger.ONE,calculator.gcd(big,BigInteger.valueOf(test)));
    }

    @Test
    void gcd()
    {
        Prime prime = new Prime();
        BigInteger first  = new BigInteger("12312312312312312");
        BigInteger second = new BigInteger("332131231231232131");
        assertEquals(BigInteger.ONE,prime.gcd(first,second));
        first  = new BigInteger("7");
        second = new BigInteger("5");
        assertEquals(BigInteger.ONE,prime.gcd(first,second));
        first  = new BigInteger("33");
        second = new BigInteger("11");
        assertEquals(BigInteger.valueOf(11),prime.gcd(first,second));
        first  = new BigInteger("12");
        second = new BigInteger("6");
        assertEquals(BigInteger.valueOf(6),prime.gcd(first,second));
        first  = new BigInteger("57");
        second = new BigInteger("13");
        assertEquals(BigInteger.ONE,prime.gcd(first,second));
        first  = new BigInteger("112");
        second = new BigInteger("60");
        assertEquals(BigInteger.valueOf(4),prime.gcd(first,second));
    }

    @Test
    void getS()
    {
        Prime calculator = new Prime();
        BigInteger test  = new BigInteger("32544");
        int expected = 5;
        assertEquals(expected,calculator.getS(test));
        test = new BigInteger("12");
        expected = 2;
        assertEquals(expected,calculator.getS(test));
    }

    @Test
    void getD()
    {
        Prime calculator = new Prime();
        BigInteger temp = new BigInteger("524");
        int s = calculator.getS(temp);
        BigInteger test = temp.subtract(BigInteger.ONE).divide(BigInteger.TWO.pow(s));
        assertEquals(calculator.getD(temp,s),test);
    }
}