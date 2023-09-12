import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Stock {
    JSONObject js;
    private String op;
    private String cp;
    private String volume;
    private String Symbol;

    Stock(String Symbol) throws JSONException {
        this.Symbol = Symbol;
        String link = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + Symbol + "&interval=1min&apikey=YOUR_API_KEY";
        Parser ps = new Parser(link);
        String thisStockData = ps.giveString();
        try {
            js = new JSONObject(thisStockData);
        } catch (JSONException e) {
            js = new JSONObject();
        }

        if (js.length() > 0) {
            String latestRefresh = js.getJSONObject("Meta Data").getString("3. Last Refreshed");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime latestRefreshDateTime = LocalDateTime.parse(latestRefresh, formatter);

            this.op = js.getJSONObject("Time Series (1min)").getJSONObject(latestRefresh).getString("1. open");
            this.cp = js.getJSONObject("Time Series (1min)").getJSONObject(latestRefresh).getString("4. close");
            this.volume = js.getJSONObject("Time Series (1min)").getJSONObject(latestRefresh).getString("5. volume");
        }
    }

    public double getop() {
        return Double.parseDouble(op);
    }

    public double getop(String datetime) throws JSONException {
        try {
            return Double.parseDouble(js.getJSONObject("Time Series (1min)").getJSONObject(datetime).getString("1. open"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public double getcp() {
        return Double.parseDouble(cp);
    }

    public double getcp(String date) throws JSONException {
        try {
            return Double.parseDouble(js.getJSONObject("Time Series (1min)").getJSONObject(date).getString("4. close"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public double getVolume() throws JSONException {
        return Double.parseDouble(this.volume);
    }

    public double getVolume(String datetime) throws JSONException {
        try {
            return Double.parseDouble(js.getJSONObject("Time Series (1min)").getJSONObject(datetime).getString("5. volume"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public String getSymbol() {
        return Symbol;
    }

    public String StockTrend(String datetime1, String datetime2) throws JSONException {
        double price1 = this.getcp(datetime1);
        double price2 = this.getcp(datetime2);

        if (price1 == -1 || price2 == -1) {
            return "Data not available for the specified dates.";
        }

        if (price1 > price2) {
            return String.format("Stock Increased by %.4f ", (price1 - price2) * 100 / price2) + "%";
        }
        return String.format("Stock Decreased by %.4f ", Math.abs((price1 - price2) * 100 / price2)) + "%";
    }
}
