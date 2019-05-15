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
        masage ="98765432112345678";
         temp = rsa.rsaCore(masage);
        String lines1[] = temp.split("\\r?\\n");
        String words1[] = lines1[lines.length-1].split(" ");
        result = words1[words1.length-1];
        assertEquals(masage,result);
    }
}