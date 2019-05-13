package model.calculate;

import java.math.BigInteger;

/**.
 * Can test a Big Integer for being a prime
 */
public class Prime
{
    /**
     * Tests a given number for being a BigInteger.
     * @param bigInteger the number to be tested
     * @return returns true it its a prime and false if its not
     */
    public  boolean isPrime(BigInteger bigInteger)
    {
        if (bigInteger.mod(BigInteger.TWO).equals(BigInteger.ZERO))
        {
            return false;
        }
        ModPower modPower = new ModPower();
        int s = getS(bigInteger);
        BigInteger d = getD(bigInteger,s);
        int a = 1;


        loop:for(int i=0;i<10;i++)
        {
            a=getBase(bigInteger,a);
            if (!(modPower.modpower(BigInteger.valueOf(a),d,bigInteger).equals(BigInteger.ONE)))
            {
                for(int j = 1;j<i-1;j++)
                {
                    if (!(modPower.modpower(BigInteger.valueOf(a),d,bigInteger).equals(bigInteger.subtract(BigInteger.ONE))))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Creates a random int as the base of the test, in a wa that is different from the old base and
     * its a relative prime for the number int the test.
     * @param bigInteger the big integer  used in the test
     * @param base the old base
     * @return the new base
     */
    public int getBase(BigInteger bigInteger,int base)
    {
        base++;
        while (!gcd(bigInteger,BigInteger.valueOf(base)).equals(BigInteger.ONE))
        {
            base++;
        }
        return base;
    }

    /**
     * Generates the greatest common divider of two values.
     * @param firstValue first value
     * @param secondValue second value
     * @return returns the gdc in a BIgInteger format
     */
    public BigInteger gcd(BigInteger firstValue,BigInteger secondValue)
    {
        if(firstValue.compareTo(BigInteger.ZERO) == 0) return secondValue;
        if(secondValue.compareTo(BigInteger.ZERO) == 0) return firstValue;
        if(firstValue.compareTo(secondValue) == 1) return gcd(firstValue.mod(secondValue),secondValue);
        else return gcd(secondValue.mod(firstValue),firstValue);
    }

    /**
     * Generates the highest power of two still dividing the bigInteger number.
     * @param bigInteger the number based on the s going to be generated
     * @return the power of two still dividing the number
     */
    public int getS(BigInteger bigInteger)
    {
        int r=0;
        while(bigInteger.mod(BigInteger.TWO).equals(BigInteger.ZERO))
        {
            bigInteger = bigInteger.divide(BigInteger.TWO);
            r++;
        }
        return  r;
    }

    /**
     * Generates d by dividing the given number with s.
     * @param bigInteger the given nuber
     * @param s the generated power of two
     * @return the division of thees two
     */
    public  BigInteger getD(BigInteger bigInteger, int s)
    {
        return bigInteger.subtract(BigInteger.ONE).divide(BigInteger.TWO.pow(s));
    }
}
