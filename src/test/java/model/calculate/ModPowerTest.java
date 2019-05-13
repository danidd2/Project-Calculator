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
    }
}