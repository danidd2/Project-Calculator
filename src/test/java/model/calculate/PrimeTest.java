package model.calculate;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeTest {

    @Test
    void isPrime()
    {
        Prime calculator = new Prime();
        assertEquals(true,calculator.isPrime(BigInteger.valueOf(7)));
        assertEquals(false,calculator.isPrime(BigInteger.valueOf(58)));
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
    }

    @Test
    void getS()
    {
        Prime calculator = new Prime();
        BigInteger test  = new BigInteger("32544");
        int expected = 5;
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