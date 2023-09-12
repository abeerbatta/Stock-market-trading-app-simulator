import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

public class LogIn {
    private JSONObject userInformation;
    private String userDataPath;
    private String userAccountDataPath;

    public LogIn(String userDataPath, String userAccountDataPath) throws JSONException, FileNotFoundException {
        this.userDataPath = "/Users/abeerbatta/Desktop/CPS406/Stock_market_trading_app/USERDATA.json";
        this.userAccountDataPath = "/Users/abeerbatta/Desktop/CPS406/Stock_market_trading_app/UserAccount_Data.json";
        String content = "";
        FileReader reader = new FileReader(userDataPath);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()) {
            content = content + scanner.nextLine();
        }
        userInformation = new JSONObject(content);
    }

    public LogIn() throws JSONException, FileNotFoundException {
        this("USERDATA.json", "UserAccount_Data.json");
    }


    public boolean signIn(Account account) throws JSONException {
        Iterator<String> iterator = userInformation.keys();
        while (iterator.hasNext()) {
            String userId = iterator.next();
            if (account.getUserId().equals(userId) && account.getPassword().equals(userInformation.getJSONObject(userId).get("password"))) {
                return true;
            }
        }
        return false;
    }
    public boolean accountExists(String enteredId) throws FileNotFoundException {
        Iterator<String> iterator = userInformation.keys();
        while (iterator.hasNext()) {
            String userId = iterator.next();
            if (enteredId.equals(userId)) {
                return true;
            }
        }
        return false;
    }
    public void signUp(Account account) throws JSONException {
        JSONObject newUserData = new JSONObject();
        newUserData.put("userName", account.getUserName());
        newUserData.put("password", account.getPassword());
        newUserData.put("balance", String.valueOf(account.getBalance()));

        userInformation.accumulate(account.getUserId(), newUserData);

        try {
            FileWriter fileWriter = new FileWriter(userDataPath);
            fileWriter.write(userInformation.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader reader = null;
        try {
            reader = new FileReader(userAccountDataPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(reader);
        JSONObject userAccountData;
        String content = "";
        while (scanner.hasNext()) {
            content = content + scanner.nextLine();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userAccountData = new JSONObject(content);
        JSONObject newUserAccountData = new JSONObject();
        userAccountData.put(account.getUserId(), newUserAccountData);

        try {
            FileWriter fileWriter2 = new FileWriter(userAccountDataPath);
            fileWriter2.write(userAccountData.toString());
            fileWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
