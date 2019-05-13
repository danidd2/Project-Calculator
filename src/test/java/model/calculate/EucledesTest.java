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
    }
}