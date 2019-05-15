package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import lombok.extern.slf4j.Slf4j;
import model.archive.Archive;
import model.calculate.Eucledes;
import model.calculate.ModPower;
import model.calculate.Prime;
import model.calculate.RSA;

import java.math.BigInteger;
import java.util.List;

/**
 * Handles the interactions between the Model and the View.
 */
@Slf4j
public class Controller
{
    /**
     * The text filed containing the results (left side of the gui).
     */
    @FXML
    private TextArea resultText;

    /**
     * Text field containing the base on the Power tab in the GUI.
     */
    @FXML
    private TextField baseTextField;
    /**
     * Text field containing the exponent on the Power tab in the GUI.
     */
    @FXML
    private TextField exponentTextField;
    /**
     * Text field containing the modulo on the Power tab in the GUI.
     */
    @FXML
    private TextField moduloTextField;

    /**
     * Button handling the events on the Prime tab.
     */
    @FXML
    private Button primeTestButton;
    /**
     * Text field containing the number on the Prime tab in the GUI.
     */
    @FXML
    private TextField primeTextFiled;
    /**
     * Button for changing from prime check to prime finder mode.
     */
    @FXML
    private ToggleButton primeToggleButton;

    /**
     * Text field containing the first number for the extended euclides algorithm.
     */
    @FXML
    private TextField eucledesX;
    /**
     * Text field containing the second number for the extended euclides algorithm.
     */
    @FXML
    private TextField eucledesY;

    /**
     * Text field containing the massage to be encrypted.
     */
    @FXML
    private TextField massageBox;


    /**
     * Calculates a modulo Power based on the given numbers in the text filed.
     */
    public void calculatePower()
    {
        if (!stringCheck(baseTextField.getText()) || !stringCheck(exponentTextField.getText()) || !stringCheck(moduloTextField.getText()))
        {
            resultText.setText("You must enter positive round numbers!");
            log.error("The user inserted an unacceptable input ");
        }
        else
        {
            if(baseTextField.getText().isEmpty() || exponentTextField.getText().isEmpty() || moduloTextField.getText().isEmpty())
            {
                resultText.setText("Something is empty!");
                log.error("The stupide user forgot to insert a number");

            }
            else
            {
                BigInteger base = new BigInteger(baseTextField.getText());
                BigInteger exponenet = new BigInteger(exponentTextField.getText());
                BigInteger modulo = new BigInteger(moduloTextField.getText());
                ModPower modPower = new ModPower();
                BigInteger result = modPower.modpower(base,exponenet,modulo);
                resultText.setText("The result is " + String.valueOf(result));
                Archive archive = Archive.builder()
                        .time(String.valueOf(System.currentTimeMillis()))
                        .type("Power")
                        .build();
                ComControll comControll=new ComControll();
                comControll.setOutput(archive);
                comControll.upload();
                comControll.closeInteraction();
                log.info("The operation was successful");
            }
        }
    }

    /**
     * Checks or generates a prime based on the state of the {@code primeToggleButton}.
     */
    public void testForPrime() {
        if (primeToggleButton.isSelected())
        {
            if (primeTextFiled.getText().isEmpty()) {
                resultText.setText("You have to insert a positive number!");
                log.error("The user inserted an unacceptable input ");
            }
            else
            {
                if (!stringCheck(primeTextFiled.getText()))
                {
                    resultText.setText("You must insert a positive round number");
                    log.error("The user inserted an unacceptable input ");
                }
                else
                { RSA rsa = new RSA();
                    int size = 0;
                    for(int i = 0;i<primeTextFiled.getText().length();i++)
                    {
                        char temp = primeTextFiled.getText().charAt(i);
                        int tempInt = Character.getNumericValue(temp);
                        System.out.println(tempInt);
                        size = size + tempInt*(int)Math.pow(10,primeTextFiled.getText().length() - i -1);
                    }
                    BigInteger result = rsa.bigPrimeFinder(size);
                    resultText.setText("The prime is " + result);
                    size = 0;
                    Archive archive = Archive.builder()
                            .time(String.valueOf(System.currentTimeMillis()))
                            .type("Prime generation")
                            .build();
                    ComControll comControll=new ComControll();
                    comControll.setOutput(archive);
                    comControll.upload();
                    comControll.closeInteraction();
                    log.info("The operation was successful");
                }
            }
        }
        else
        {
            if (!stringCheck(primeTextFiled.getText()))
            {
                resultText.setText("You must insert a positive round number");
                log.error("The user inserted an unacceptable input ");

            }
            else
            {
                if (primeTextFiled.getText().isEmpty()) {
                    resultText.setText("You have to insert a positive number!");
                    log.error("The user inserted an unacceptable input ");

                } else {
                    Prime prime = new Prime();
                    BigInteger number = new BigInteger(primeTextFiled.getText());
                    boolean result = prime.isPrime(number);
                    if (result) {
                        resultText.setText("The number is a prime.");
                    } else {
                        resultText.setText("The given number is not a prime!");
                    }
                    Archive archive = Archive.builder()
                            .time(String.valueOf(System.currentTimeMillis()))
                            .type("PrimeTest")
                            .build();
                    ComControll comControll=new ComControll();
                    comControll.setOutput(archive);
                    comControll.upload();
                    comControll.closeInteraction();
                }
            }

        }
    }


    /**
     * Runs an extended eucledes algorithm on the numbers reachble in the text fileds.
     */
    public void calculateEucledes()
    {
        if (!stringCheck(eucledesX.getText()) || !stringCheck(eucledesY.getText()))
        {
            resultText.setText("You must enter positive round numbers");
            log.error("The user inserted an unacceptable input ");
        }
        else
        {
            if (eucledesX.getText().isEmpty() || eucledesY.getText().isEmpty())
            {
                resultText.setText("Something is empty!");
                log.error("The user inserted an unacceptable input ");
            }
            else
            {
                Eucledes eucledes = new Eucledes();
                BigInteger x = new BigInteger(eucledesX.getText());
                BigInteger y = new BigInteger(eucledesY.getText());
                Eucledes result = eucledes.calculate(x,y);
                x = result.getX();
                y = result.getY();
                String resultStrin = new String("The results are " + x + " and " + y);
                resultText.setText(resultStrin);
                Archive archive = Archive.builder()
                        .time(String.valueOf(System.currentTimeMillis()))
                        .type("Euclides")
                        .build();
                ComControll comControll=new ComControll();
                comControll.setOutput(archive);
                comControll.upload();
                comControll.closeInteraction();
            }
        }
    }

    /**
     * Handles the switch between prime finding and prime check mode.
     */
    public void toggeling()
    {
        if (primeToggleButton.isSelected())
        {
            primeTestButton.setText("Get a Prime");
            primeTextFiled.setPromptText("Binary size of the Prime");
            resultText.setText("This might take some time");
        }
        else
        {
            primeTestButton.setText("Test");
            primeTextFiled.setPromptText("Insert a Number");
        }
    }

    /**
     * Encrypts the massage found in the text filed.
     */
    public void encrypt()
    {
        if (!stringCheck(massageBox.getText()))
        {
            resultText.setText("You must enter a positive round number");
            log.error("The user inserted an unacceptable input ");
        }
        else
        {
            if (massageBox.getText().isEmpty())
            {
                resultText.setText("You are trying to encrypt and empty massage!");
                log.error("The user inserted an unacceptable input ");
            }
            else
            {
                String massage = massageBox.getText();
                RSA rsa = new RSA();
                String result = rsa.rsaCore(massage);
                resultText.setText(result);
                Archive archive = Archive.builder()
                        .time(String.valueOf(System.currentTimeMillis()))
                        .type("RSA")
                        .build();
                ComControll comControll=new ComControll();
                comControll.setOutput(archive);
                comControll.upload();
                comControll.closeInteraction();
            }
        }
    }


    /**
     * Returns 10 enetries from the database.
     */
    public void getHistory()
    {
        log.info("in");
        ComControll comControll = new ComControll();
        List<Archive> archiveList = comControll.getHystorry();
        String result = "";
        for (Archive i:archiveList)
        {
            result = result + "\n" + i.toString();
        }
        resultText.setText(result);
    }

    /**
     * Checks if the given string contains any non-wanted characters like: letters,whitespace, unique characters.
     * @param massage the string to be checked
     * @return True if the character is good to go, false if the method found any non-wanted character
     */
    public boolean stringCheck(String massage)
    {
        boolean ret=true;
        for (int i=0;i<massage.length();i++)
        {
            if (!Character.isDigit(massage.charAt(i)))
                ret=false;
        }
        return ret;
    }
}
