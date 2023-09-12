import org.json.JSONException;
import org.junit.Test;
import org.junit.Assert;

public class AccountTest {
    @Test
    public void testAccountConstructorWithUserNamePasswordAndBalance() throws JSONException {
        Account account = new Account("Abeer Batta", "abeer", 1000.0);

        Assert.assertNotNull(account.getUserId());
        Assert.assertEquals("Abeer Batta", account.getUserName());
        Assert.assertEquals("abeer", account.getPassword());
        Assert.assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    public void testAccountConstructorWithUserIdAndPassword() {
        Account account = new Account("AZZA", "abeer");

        Assert.assertEquals("AZZA", account.getUserId());
        Assert.assertEquals("abeer", account.getPassword());
    }

    @Test
    public void testAccountConstructorWithUserIdUserNamePasswordAndBalance() throws JSONException {
        Account account = new Account("AZZA", "Abeer Batta", "abeer", 1000.0);

        Assert.assertEquals("AZZA", account.getUserId());
        Assert.assertEquals("Abeer Batta", account.getUserName());
        Assert.assertEquals("abeer", account.getPassword());
        Assert.assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    public void testAccountConstructorWithUserId() {
        Account account = new Account("AZZA");

        Assert.assertEquals("AZZA", account.getUserId());
    }

    @Test
    public void testGenerateId() {
        Account account = new Account("Abeer Batta", "abeer", 1000.0);
        String id = account.generateId();

        Assert.assertNotNull(id);
        Assert.assertEquals(4, id.length());

        boolean validChars = id.chars().allMatch(c -> Character.isLetterOrDigit(c));
        Assert.assertTrue(validChars);
    }
}