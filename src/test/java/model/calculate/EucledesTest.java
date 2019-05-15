package model.calculate;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class EucledesTest {

    @Test
    void calculate()
    {
        Eucledes calculator = new Eucledes();
        Eucledes test = calculator.calculate(BigInteger.valueOf(180),BigInteger.valueOf(150));
        BigInteger a = BigInteger.valueOf(-1);
        BigInteger b = BigInteger.ONE;
        assertEquals(a,test.getY());
        assertEquals(b,test.getX());
        test = calculator.calculate(BigInteger.valueOf(123),BigInteger.valueOf(321));
         a = BigInteger.valueOf(-18);
         b = BigInteger.valueOf(47);
        assertEquals(a,test.getY());
        assertEquals(b,test.getX());
        test = calculator.calculate(BigInteger.valueOf(1998),BigInteger.valueOf(8));
        a = BigInteger.valueOf(250);
        b = BigInteger.valueOf(-1);
        assertEquals(a,test.getY());
        assertEquals(b,test.getX());
        test = calculator.calculate(BigInteger.valueOf(8),BigInteger.valueOf(28));
        a = BigInteger.valueOf(1);
        b = BigInteger.valueOf(-3);
        assertEquals(a,test.getY());
        assertEquals(b,test.getX());
    }
}