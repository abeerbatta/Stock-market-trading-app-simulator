import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonFAccountTest {

    @Test
    public void testGenerateIdPerformance() {
        Account account = new Account("TestUser", "TestPassword", 1000);

        int numberOfTests = 1000;
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfTests; i++) {
            account.generateId();
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long elapsedTimePerTest = elapsedTime / numberOfTests;

        // Set a threshold for the time taken for each generateId() call
        long threshold = 500000; // 500 microseconds
        assertTrue(elapsedTimePerTest < threshold, "generateId() took longer than the expected threshold");
    }
}