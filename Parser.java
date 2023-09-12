
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {
    private static HttpURLConnection connection;
    String link;
    Parser(String link){
        this.link = link;
    }
    public  String giveString() {
        BufferedReader reader;
        String line;
        StringBuffer responceContent = new StringBuffer();
        try {
            URL url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responceContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responceContent.append(line);
                }
                reader.close();
            }

            String rawJsonData = responceContent.toString();
            return rawJsonData;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public static  void main(){


    }
}
