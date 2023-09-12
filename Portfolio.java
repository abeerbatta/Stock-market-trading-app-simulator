import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.json.*;
/**
 * Portfolio class implements the Portfolio_Interface and provides methods
 * to manage a user's stock portfolio.
 */

//Constructor
public class Portfolio implements Portfolio_Interface {
    private String userId;
    private JSONObject Object1,Object2;
    /**
     * Retrieves the number of stocks held in the portfolio for the given stock.
     *
     * @param s The Stock object.
     * @return The number of stocks held in the portfolio for the given stock.
     * @throws JSONException
     */
    public int GetSH(Stock s) throws JSONException
    {
        JSONObject jstock=Object2.getJSONObject(s.getSymbol());
        return (Integer.parseInt(jstock.getString("CurrentHoldings")));
    }

    /**
     * Retrieves the latest day and time.
     *
     * @param dt Date and time as String.
     * @return Date and time in date and time format.
     */

    public String getLatest(String dt) {
        LocalDate date=LocalDate.parse(dt);
        String d=date.toString();
        String day = date.getDayOfWeek().toString();
        String LatestDay ;
        if(day.equals("SUNDAY")){
            LatestDay = date.minusDays((long) 2.0).toString();
        }
        else if(day.equals("SATURDAY")){
            LatestDay = date.minusDays((long) 1.0).toString();
        }
        else{
            LatestDay = d;
        }
        return LatestDay;
    }
    /**
     * Retrieves the latest value of stock.
     *
     * @param s The Stock object.
     * @return The value of stock at that time.
     * @throws NumberFormatException
     * @throws JSONException
     */
    public double getStockValue(Stock s) throws NumberFormatException, JSONException
    {
        JSONObject jstock=Object2.getJSONObject(s.getSymbol());
        int n=Integer.parseInt(jstock.getString("CurrentHoldings"));
        return (n*s.getcp());
    }
    public double getSI(Stock s) throws JSONException
    {
        JSONObject jstock=Object2.getJSONObject(s.getSymbol());
        JSONObject jpurch=jstock.getJSONObject("buys");
        Iterator it =jpurch.keys();
        String date="";
        double invest=0.0;
        while(it.hasNext())
        {
            date=it.next().toString();
            //	date=getLatest(date);
            invest+=(s.getcp(getLatest(date))*Double.parseDouble(jpurch.getString(date)));
        }
        return invest;
    }
    public double getSS(Stock s) throws JSONException
    {
        JSONObject jstock=Object2.getJSONObject(s.getSymbol());
        JSONObject jsale=jstock.getJSONObject("sells");
        Iterator it =jsale.keys();
        String date="";
        double sale=0.0;
        while(it.hasNext())
        {
            date=it.next().toString();
            //	date=getLatest(date);
            sale+=(s.getcp(getLatest(date))*Double.parseDouble(jsale.getString(date)));
        }
        return sale;
    }
    public double getSP(Stock s) throws NumberFormatException, JSONException
    {
        double profit=this.getSS(s)+this.getStockValue(s)-this.getSI(s);
        return profit;
    }
    public int getOH() throws JSONException
    {
        Iterator it=Object2.keys();
        int tot_hold=0;
        while(it.hasNext())
        {
            String symbol=it.next().toString();
            Stock st=new Stock(symbol);
            tot_hold+=this.GetSH(st);
        }
        return tot_hold;
    }
    public Portfolio(String userId) throws Exception
    {
        this.userId=userId;
        FileReader reader = new FileReader("UserAccount_Data.json");
        String s="";
        Scanner scan = new Scanner(reader);
        while(scan.hasNext()){
            s = s + scan.nextLine();
        }
        reader.close();

        Object1 =new JSONObject(s);
        Object2=Object1.getJSONObject(userId);
    }
    public double getOV() throws JSONException
    {
        Iterator it=Object2.keys();
        double tot_val=0.0;
        while(it.hasNext())
        {
            String symbol=it.next().toString();
            Stock st=new Stock(symbol);
            tot_val+=this.getStockValue(st);
        }
        return tot_val;

    }
    public double getOI() throws JSONException
    {
        Iterator it=Object2.keys();
        double tot_invest=0.0;
        while(it.hasNext())
        {
            String symbol=it.next().toString();
            Stock st=new Stock(symbol);
            tot_invest+=this.getSI(st);
        }
        return tot_invest;

    }
    public double getOS() throws JSONException
    {
        Iterator it=Object2.keys();
        double tot_sale=0.0;
        while(it.hasNext())
        {
            String symbol=it.next().toString();
            Stock st=new Stock(symbol);
            tot_sale+=this.getSS(st);
        }
        return tot_sale;

    }
    public double getOP() throws NumberFormatException, JSONException
    {
        double tot_profit=this.getOS()+this.getOV()-this.getOI();
        return tot_profit;
    }

    public LinkedList<String> displaySL()
    {
        Iterator it=Object2.keys();
        int i=1;
        LinkedList<String> list=new LinkedList<>();
        System.out.println("Your stock list :");
        while(it.hasNext())
        {
            String s=it.next().toString();
            System.out.println(i+". "+s);
            list.add(s);
            i++;
        }

        return list;

    }

    public  void displaySP(Stock s) throws JSONException
    {
        if(!Object2.has(s.getSymbol()))
        {
            System.out.println("This stock does not exist in portfolio..");
            return ;
        }
        System.out.println("DETAILS OF "+s.getSymbol()+":");
        String data[][]=new String[5][2];
        data[0][0]="Current Holding";
        data[1][0]="Current Value";
        data[2][0]="Total Investment (till date)";
        data[3][0]="Total Sales(till date)";
        data[4][0]="Total Profit(as of now)";
        data[0][1]=String.format("%d", this.GetSH(s));
        data[1][1]=String.format("%.2f", this.getStockValue(s));
        data[2][1]=String.format("%.2f", this.getSI(s));
        data[3][1]=String.format("%.2f", this.getSS(s));
        data[4][1]=String.format("%.2f", this.getSP(s));
        String col[]= {"a","b"};



        JFrame pop=new JFrame();
        JTable table =new JTable(data,col);
        table.setRowHeight(20);
        pop.add(table);
        pop.setSize(300,400);
        pop.setVisible(true);


    }

    public void displayFP() throws JSONException
    {

        Iterator it=Object2.keys();
        int l=Object2.length();
        String data[][]=new String[l+2][6];
        data[0][0]="Stock Name";
        data[0][1]="Current Holding";
        data[0][2]="Current Value";
        data[0][3]="Total Investment (till date)";
        data[0][4]="Total Sales(till date)";
        data[0][5]="Total Profit(as of now)";
        int i=1,holding=0;
        float invest=0,sale=0,value=0,profit=0;
        for(;it.hasNext();i++)
        {
            String name=it.next().toString();
            Stock st=new Stock(name);
            data[i][0]=name;
            data[i][1]=String.format("%d", this.GetSH(st));
            holding+=Integer.parseInt(data[i][1]);
            data[i][2]=String.format("%.2f", this.getStockValue(st));
            value+=Float.parseFloat(data[i][2]);
            data[i][3]=String.format("%.2f", this.getSI(st));
            invest+=Float.parseFloat(data[i][3]);
            data[i][4]=String.format("%.2f", this.getSS(st));
            sale+=Float.parseFloat(data[i][4]);
            data[i][5]=String.format("%.2f", (Float.parseFloat(data[i][2])+Float.parseFloat(data[i][4])-Float.parseFloat(data[i][3])));
            profit+=Float.parseFloat(data[i][5]);
        }

        data[i][0]="TOTAL";
        data[i][1]=String.format("%d", holding);
        data[i][2]=String.format("%.2f", value);
        data[i][3]=String.format("%.2f", invest);
        data[i][4]=String.format("%.2f", sale);
        data[i][5]=String.format("%.2f", profit);
        String col[]= {"a","b","c","d","e","f"};



        JFrame pop=new JFrame();
        JTable table =new JTable(data,col);
        table.setRowHeight(20);
        pop.add(table);
        pop.setSize(300,400);
        pop.setVisible(true);

    }
    @SuppressWarnings("deprecation")

    public void displayPH() throws JSONException
    {
        System.out.println("Purchase History");
        String data[][]=new String[20][4];
        data[0][0]="Purchase Date";
        data[0][1]="Stock Purchased";
        data[0][2]="Quantity Purchased";
        data[0][3]="Amount invested";
        int i=1;
        Iterator it=Object2.keys();
        while(it.hasNext())
        {
            String name=it.next().toString();
            JSONObject jstock=Object2.getJSONObject(name);
            JSONObject jpurch=jstock.getJSONObject("buys");
            Iterator jt=jpurch.keys();
            while(jt.hasNext())
            {
                data[i][0]=jt.next().toString();
                data[i][1]=name;
                Stock st=new Stock(name);
                data[i][2]=jpurch.getString(data[i][0]);
                data[i][3]=String.format("%.2f", st.getcp(getLatest(data[i][0]))*Double.parseDouble(data[i][2]));
                i++;
            }}

        for(int j=1;data[j+1][0]!=null;j++)
        {
            String temp[]=new String[4];
            for(int k=1;data[k+1][0]!=null;k++)
            {
                String date1[]=data[k][0].split("-");
                String date2[]=data[k+1][0].split("-");
                Date d1=new Date(Integer.parseInt(date1[0]),Integer.parseInt(date1[1]),Integer.parseInt(date1[2]));
                Date d2=new Date(Integer.parseInt(date2[0]),Integer.parseInt(date2[1]),Integer.parseInt(date2[2]));
                if(d2.after(d1))
                {
                    temp=data[k];
                    data[k]=data[k+1];
                    data[k+1]=temp;
                }


            }
        }
        String col[]= {"a","b","c","d"};



        JFrame pop=new JFrame();
        JTable table =new JTable(data,col);
        table.setRowHeight(20);
        pop.add(table);
        pop.setSize(300,400);
        pop.setVisible(true);



    }
    public void displaySH() throws JSONException
    {
        System.out.println("Sale History");
        String data[][]=new String[20][4];
        data[0][0]="Sale Date";
        data[0][1]="Stock sold";
        data[0][2]="Quantity Sold";
        data[0][3]="Amount received";
        int i=1;
        Iterator it=Object2.keys();
        while(it.hasNext())
        {
            String name=it.next().toString();
            JSONObject jstock=Object2.getJSONObject(name);
            JSONObject jsale=jstock.getJSONObject("sells");
            Iterator jt=jsale.keys();
            while(jt.hasNext())
            {
                data[i][0]=jt.next().toString();
                data[i][1]=name;
                Stock st=new Stock(name);
                data[i][2]=jsale.getString(data[i][0]);
                data[i][3]=String.format("%.2f", st.getcp(getLatest(data[i][0]))*Double.parseDouble(data[i][2]));
                i++;
            }}

        for(int j=1;data[j+1][0]!=null;j++)
        {
            String temp[]=new String[4];
            for(int k=1;data[k+1][0]!=null;k++)
            {
                String date1[]=data[k][0].split("-");
                String date2[]=data[k+1][0].split("-");
                Date d1=new Date(Integer.parseInt(date1[0]),Integer.parseInt(date1[1]),Integer.parseInt(date1[2]));
                Date d2=new Date(Integer.parseInt(date2[0]),Integer.parseInt(date2[1]),Integer.parseInt(date2[2]));
                if(d2.after(d1))
                {
                    temp=data[k];
                    data[k]=data[k+1];
                    data[k+1]=temp;
                }


            }
        }
        String col[]= {"a","b","c","d"};

        JFrame pop=new JFrame();
        JTable table =new JTable(data,col);
        table.setRowHeight(20);
        pop.add(table);
        pop.setSize(300,400);
        pop.setVisible(true);

    }


    public static void main(String[] args) throws Exception {

    }

}



