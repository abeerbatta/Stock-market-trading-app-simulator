import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonFStockAccountTest {

    private StockAccount stockAccount;

    @BeforeEach
    public void setup() throws JSONException, IOException {
        stockAccount = new StockAccount("1001");
    }

    @Test
    public void testDepositPerformance() throws JSONException, IOException {
        long startTime = System.nanoTime();
        stockAccount.deposit(1000);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        long threshold = 500000000; // 5000 microseconds
        assertTrue(elapsedTime < threshold, "deposit() took longer than the expected threshold");
    }

    @Test
    public void testWithdrawPerformance() throws JSONException, IOException {
        long startTime = System.nanoTime();
        stockAccount.withdraw(500);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        long threshold = 5000000; // 500 microseconds
        assertTrue(elapsedTime < threshold, "withdraw() took longer than the expected threshold");
    }

    @Test
    public void testBuyStockPerformance() throws JSONException, IOException {
        String symbol = "AAPL";
        int noOfShares = 1;

        long startTime = System.nanoTime();
        stockAccount.buyStock(symbol, noOfShares);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        long threshold = 1000000000; // 1000 microseconds
        assertTrue(elapsedTime < threshold, "buyStock() took longer than the expected threshold");
    }

    @Test
    public void testSellStockPerformance() throws JSONException, IOException {
        String symbol = "AAPL";
        int noOfShares = 2;

        long startTime = System.nanoTime();
        stockAccount.sellStock(symbol, noOfShares);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        long threshold = 1000000000; // 1000 microseconds
        assertTrue(elapsedTime < threshold, "sellStock() took longer than the expected threshold");
    }
}