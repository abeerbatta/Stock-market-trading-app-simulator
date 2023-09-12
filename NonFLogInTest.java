import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonFLogInTest {

    private LogIn logIn;

    @BeforeEach
    public void setup() throws JSONException, FileNotFoundException {
        logIn = new LogIn("USERDATA.json", "UserAccount_Data.json");
    }

    @Test
    public void testSignInPerformance() throws JSONException {
        Account account = new Account("1001", "Abeer_batta", "abeer", 5000);

        long startTime = System.nanoTime();
        logIn.signIn(account);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Set a threshold for the time taken for signIn() call
        long threshold = 500000; // 500 microseconds
        assertTrue(elapsedTime < threshold, "signIn() took longer than the expected threshold");
    }

    @Test
    public void testSignUpPerformance() throws JSONException {
        Account newAccount = new Account("TestUser", "TestPassword", 1000);

        long startTime = System.nanoTime();
        logIn.signUp(newAccount);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Set a threshold for the time taken for signUp() call
        long threshold = 10000000;
        assertTrue(elapsedTime < threshold, "signUp() took longer than the expected threshold");
    }
}