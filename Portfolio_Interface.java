import org.json.JSONException;

public interface Portfolio_Interface {
    public int GetSH(Stock s) throws JSONException;
    public double getStockValue(Stock s) throws NumberFormatException, JSONException;
    public double getSI(Stock s) throws JSONException;
    public double getSS(Stock s) throws JSONException;
    public double getSP(Stock s) throws NumberFormatException, JSONException;
    public int getOH() throws JSONException;

    public double	getOV() throws JSONException;
    public double getOI() throws JSONException;
    public double getOS() throws JSONException;
    public double getOP() throws NumberFormatException, JSONException;

}