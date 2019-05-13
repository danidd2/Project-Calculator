package model.calculate;

import java.math.BigInteger;

/**
 * Extended Euclides calculator.
 */
public class Eucledes
{
    /**
     * The three results.
     */
    public BigInteger x, y, gcd;

    /**
     * Empty constructor.
     */
    public Eucledes()
    {
    }

    /**
     * Runs an extended euclidian algorithm and saves tem into a Euclides object.
     * @param a the first number
     * @param b the second number
     * @return an {@link Eucledes} object containing the results in the x and y variables
     */
    public  Eucledes calculate(BigInteger a, BigInteger b)
    {
        BigInteger x = BigInteger.ZERO;
        BigInteger lastx = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger[] quotientAndRemainder = a.divideAndRemainder(b);
            BigInteger quotient = quotientAndRemainder[0];

            BigInteger temp = a;
            a = b;
            b = quotientAndRemainder[1];

            temp = x;
            x = lastx.subtract(quotient.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(quotient.multiply(y));
            lasty = temp;
        }

        Eucledes result = new Eucledes();
        result.x = lastx;
        result.y = lasty;
        result.gcd = a;
        return result;
    }

    /**
     * Getter.
     * @return gives back one of the results
     */
    public BigInteger getX() {
        return x;
    }
    /**
     * Getter.
     * @return gives back one of the results
     */
    public BigInteger getY() {
        return y;
    }
    /**
     * Greatest common divider.
     * @return gives back one of the results
     */
    public BigInteger getGcd() {
        return gcd;
    }
}
