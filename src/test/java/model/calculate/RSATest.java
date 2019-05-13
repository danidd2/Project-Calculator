package model.calculate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {

    @Test
    void bigPrimeFinder()
    {
        RSA rsa = new RSA();
        Prime prime = new Prime();
        boolean test = false;
        if (prime.isPrime(rsa.bigPrimeFinder(1024)))
        {
            test=true;
        }
        assertTrue(test);
    }

    @Test
    void rsaCore()
    {
        RSA rsa = new RSA();
        String masage ="1234";
        String temp = rsa.rsaCore(masage);
        String lines[] = temp.split("\\r?\\n");
        String words[] = lines[lines.length-1].split(" ");
        String result = words[words.length-1];
        assertEquals(masage,result);
    }
}