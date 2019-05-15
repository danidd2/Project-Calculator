package model.calculate;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ModPowerTest {

    @Test
    void modpower()
    {
        ModPower calculator =  new ModPower();
        BigInteger expected = BigInteger.ZERO;
        assertEquals(expected,calculator.modpower(BigInteger.TWO,BigInteger.valueOf(12344),BigInteger.valueOf(4)));
        expected = BigInteger.valueOf(3);
        assertEquals(expected,calculator.modpower(BigInteger.valueOf(12),BigInteger.valueOf(7),BigInteger.valueOf(5)));
        expected = BigInteger.valueOf(16);
        assertEquals(expected,calculator.modpower(BigInteger.valueOf(1998),BigInteger.valueOf(8),BigInteger.valueOf(28)));
        expected = BigInteger.valueOf(100);
        assertEquals(expected,calculator.modpower(BigInteger.valueOf(8),BigInteger.valueOf(28),BigInteger.valueOf(1998)));
        expected = BigInteger.valueOf(699);
        assertEquals(expected,calculator.modpower(BigInteger.valueOf(123),BigInteger.valueOf(456),BigInteger.valueOf(789)));
    }
}