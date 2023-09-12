import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogInTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private LogIn logIn;
    private File userDataFile;
    private File userAccountDataFile;

    @Before
    public void setUp() throws IOException, JSONException {
        userDataFile = tempFolder.newFile("USERDATA.json");
        userAccountDataFile = tempFolder.newFile("UserAccount_Data.json");

        // Creating a sample JSON file to simulate existing user data.
        String sampleUserData = "{ \"1001\": { \"userName\": \"Abeer_batta\", \"password\": \"abeer\", \"balance\": \"5000\" } }";
        FileWriter userDataFileWriter = new FileWriter(userDataFile);
        userDataFileWriter.write(sampleUserData);
        userDataFileWriter.close();

        logIn = new LogIn(userDataFile.getAbsolutePath(), userAccountDataFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        userDataFile.delete();
        userAccountDataFile.delete();
    }

    @Test
    public void testAccountExists() throws JSONException, FileNotFoundException {
        assertTrue(logIn.accountExists("1001"));
        assertFalse(logIn.accountExists("1002"));
    }

    @Test
    public void testSignIn() throws JSONException {
        Account validAccount = new Account("1001", "Abeer_batta", "abeer", 5000);
        assertTrue(logIn.signIn(validAccount));

        Account invalidAccount = new Account("1001", "Abeer_batta", "wrong_password", 5000);
        assertFalse(logIn.signIn(invalidAccount));
    }

    @Test
    public void testSignUp() throws JSONException, FileNotFoundException {
        Account newAccount = new Account("1002", "aarushi_batta", "aarushi_password", 4000);
        assertFalse(logIn.accountExists(newAccount.getUserId()));
        logIn.signUp(newAccount);
        assertTrue(logIn.accountExists(newAccount.getUserId()));
        assertTrue(logIn.signIn(newAccount));
    }
}

