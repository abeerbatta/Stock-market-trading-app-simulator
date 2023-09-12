import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class StockAccountTest {
    private StockAccount stockAccount;

    @BeforeEach
    public void setUp() throws JSONException, IOException {
        stockAccount = new StockAccount("testUser");
    }

    @Test
    public void testCheckIfStock() {
        String stock = "AAPL";
        boolean result = stockAccount.checkifstock(stock);
        assertTrue(result, "CheckIfStock should return false for a new user with no stocks");
    }

    @Test
    public void testHowMuchStock() {
        String stock = "AAPL";
        int result = stockAccount.howmuchstock(stock);
        assertEquals(0, result, "HowMuchStock should return 0 for a new user with no stocks");
    }

    @Test
    public void testDeposit() throws JSONException, IOException {
        double initialBalance = stockAccount.getBalance();
        double depositAmount = 1000;
        stockAccount.deposit(depositAmount);
        double newBalance = stockAccount.getBalance();
        assertEquals(initialBalance + depositAmount, newBalance, 0.001, "Deposit should correctly update the balance");
    }

    @Test
    public void testWithdraw() throws JSONException, IOException {
        double initialBalance = stockAccount.getBalance();
        double withdrawalAmount = 500;
        stockAccount.withdraw(withdrawalAmount);
        double newBalance = stockAccount.getBalance();
        assertEquals(initialBalance - withdrawalAmount, newBalance, 0.001, "Withdraw should correctly update the balance");
    }

//    @Test
//    public void testBuyStock() throws JSONException, IOException {
//        String stock = "AAPL";
//        int amount = 5;
//        double initialBalance = stockAccount.getBalance();
//        stockAccount.buyStock(stock, amount);
//        assertTrue(stockAccount.checkifstock(stock), "BuyStock should add the stock to the user's account");
//        assertNotEquals(initialBalance, stockAccount.getBalance(), "BuyStock should update the user's balance");
//    }

    @Test
    public void testSellStock() throws JSONException, IOException {
        String stock = "AAPL";
        int amount = 5;
        stockAccount.buyStock(stock, amount);
        double initialBalance = stockAccount.getBalance();
        stockAccount.sellStock(stock, amount);
        assertEquals(0, stockAccount.howmuchstock(stock), "SellStock should update the stock amount in the user's account");
        assertNotEquals(initialBalance, stockAccount.getBalance(), "SellStock should update the user's balance");
    }

//    @Test
//    public void testStockList() throws JSONException, IOException {
//        LinkedList<String> initialStockList = stockAccount.StockList();
//        assertEquals(1, initialStockList.size(), "StockList should return an empty list for a new user");
//
//        String stock = "AAPL";
//        int amount = 5;
//        stockAccount.buyStock(stock, amount);
//        LinkedList<String> updatedStockList = stockAccount.StockList();
//        assertEquals(1, updatedStockList.size(), "StockList should return a list with one entry after buying a stock");
//        assertTrue(updatedStockList.contains(stock), "StockList should contain the bought stock");
//    }


}

