package model.calculate;

import java.math.BigInteger;

/**
 * Contains a method capable of calculating the modulo power of big integers. For this
 * it also includes a binary convertioan method.
 */
public class ModPower
{

    /**
     * Calculates the modulo power.
     * @param base the baes of the calculation
     * @param exponent the exponenet of the calculation
     * @param mod the modulo of the calculation
     * @return The modulo of the power in a BigInteger format
     */
    public BigInteger modpower(BigInteger base, BigInteger exponent, BigInteger mod)
    {
        BigInteger ret = new BigInteger("1");
        String binaryForm = toBinary(exponent);

        for(int i=0;i<binaryForm.length();i++) {
            if (binaryForm.charAt(binaryForm.length() - 1 - i) == '1') {
                ret = ret.multiply(base).mod(mod);
            }
            base = base.multiply(base).mod(mod);
        }
        return ret;
    }

    /**
     * Converts the given BigInteger to a binary form.
     * @param bigInteger the number to be converted
     * @return A string containing the BigIntegers binary digits
     */
    public String toBinary(BigInteger bigInteger)
    {
        return bigInteger.toString(2);
    }
}
