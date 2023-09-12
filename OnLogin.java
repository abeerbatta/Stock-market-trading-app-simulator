import org.jfree.chart.ChartFactory;import org.jfree.chart.ChartPanel;import org.jfree.chart.JFreeChart;import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Day;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.json.JSONException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;



public class OnLogin extends JFrame implements ActionListener{
    private JFrame fromSA;
    JButton TB, SB , PB;
    JButton MPB, NF, MAB;

    JButton Info;
    JLabel textLabel,welcomeLabel;
    private Portfolio port;
    private JTable table;

    private BufferedImage backgroundImage;

    /**
     * Custom JPanel class to paint the background image.
     */
    class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    String UserID;

    public OnLogin(String UserID) {
        this.UserID = UserID;
        try { port=new Portfolio(UserID);
        } catch (Exception e) { e.printStackTrace();
        }
        initialize();
    }
    public static void main(String args[]) {
    }

//    public void displayMessage(String message) {
//
//
//        // Display the message with custom UI
//        JFrame frame = null;
//        JOptionPane.showMessageDialog(frame, message, "Custom Message", JOptionPane.INFORMATION_MESSAGE);
//
//        // Reset UI to default
//        UIManager.put("OptionPane.background", null);
//        UIManager.put("Panel.background", null);
//        UIManager.put("OptionPane.messageFont", null);
//        UIManager.put("OptionPane.messageForeground", null);
//        UIManager.put("Button.background", null);
//        UIManager.put("Button.foreground", null);
//        frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Set custom font
//        Font font = new Font("Arial", Font.PLAIN, 18);
//
//        // Create a custom UI for JOptionPane
//        UIManager.put("OptionPane.background", Color.DARK_GRAY);
//        UIManager.put("Panel.background", Color.DARK_GRAY);
//        UIManager.put("OptionPane.messageFont", font);
//        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
//        UIManager.put("Button.background", Color.LIGHT_GRAY);
//        UIManager.put("Button.foreground", Color.BLACK);
//    }

    public void displayMessage(String message) {

        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.BLACK);

        JFrame frame = null;
        JOptionPane.showMessageDialog(frame, message, "Custom Message", JOptionPane.INFORMATION_MESSAGE);


        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageFont", null);
        UIManager.put("OptionPane.messageForeground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
    }

    public void showStock() throws Exception {
        Portfolio port=new Portfolio(UserID);
        LinkedList<String> list= new LinkedList<>();
        list=port.displaySL();
        String str="Your stock list";
        for(int i=0;i<list.size();i++) {
            str+="\n"+(i+1)+"."+list.get(i);
        }
        str+="\nPlease check the portfolio for more details";
        displayMessage(str);
//
    }
    private JPanel createWalmartChart() {
        TimeSeries series = new TimeSeries("Walmart Stock Price");

        // Add your Walmart stock data here, for example:
        series.add(new Day(1, 1, 2023), 139);
        series.add(new Day(1, 2, 2023), 142);
        series.add(new Day(1, 3, 2023), 145);
        // ...

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Walmart Stock Price",
                "Date",
                "Price",
                dataset,
                true,
                true,
                false);

        return new ChartPanel(chart);
    }

    private JPanel createTeslaChart() {
        TimeSeries series = new TimeSeries("Tesla Stock Price");

        // Add your Tesla stock data here, for example:
        series.add(new Day(1, 1, 2023), 130);
        series.add(new Day(1, 2, 2023), 160);
        series.add(new Day(1, 3, 2023), 190);
        // ...

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Tesla Stock Price",
                "Date",
                "Price",
                dataset,
                true,
                true,
                false);

        return new ChartPanel(chart);
    }
    private JPanel createMicrosoftChart() {
        TimeSeries series = new TimeSeries("Microsoft Stock Price");

        // Add your Tesla stock data here, for example:
        series.add(new Day(1, 1, 2023), 272);
        series.add(new Day(1, 2, 2023), 272);
        series.add(new Day(1, 3, 2023), 272);
        // ...

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Microsoft Stock Price",
                "Date",
                "Price",
                dataset,
                true,
                true,
                false);

        return new ChartPanel(chart);
    }

    private JPanel createGoogleChart() {
        TimeSeries series = new TimeSeries("Google Stock Price");

        series.add(new Day(1, 1, 2023), 91);
        series.add(new Day(1, 2, 2023), 96);
        series.add(new Day(1, 3, 2023), 101);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Google Stock Price",
                "Date",
                "Price",
                dataset,
                true,
                true,
                false);

        return new ChartPanel(chart);
    }
    private void initialize() {
        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (backgroundImage != null) {
            OnLogin.ImagePanel mainPanel = new OnLogin.ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }
        setVisible(true);
        fromSA = this;
        fromSA.getContentPane().setForeground(Color.YELLOW);
        fromSA.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
        fromSA.getContentPane().setName("Buy Stock");
        fromSA.setTitle("KAGAK");
        fromSA.setBounds(100, 100, 1500, 900);
        fromSA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fromSA.getContentPane().setBackground(new Color(69,69,69));
        fromSA.getContentPane().setLayout(null); // Add this line to set the layout manager to null
        fromSA.setVisible(true);
        NF = new JButton("NewsFeed");
        NF.setBackground(Color.BLACK);
        NF.setForeground(Color.YELLOW);
        NF.setBorder(BorderFactory.createEtchedBorder());
        NF.setFont(new Font("Ariel", Font.PLAIN, 22));
        NF.setBounds(650, 322, 220, 38);
        fromSA.getContentPane().add(NF);
        Info = new JButton("Information");
        Info.setBackground(Color.BLACK);
        Info.setForeground(Color.YELLOW);
        Info.setBorder(BorderFactory.createEtchedBorder());
        Info.setFont(new Font("Ariel",Font.PLAIN, 22));
        Info.setBounds(650,574,220,38);
        fromSA.getContentPane().add(Info);
        SB = new JButton("My Stocks");
        SB.setBackground(Color.BLACK);
        SB.setForeground(Color.YELLOW);
        SB.setBorder(BorderFactory.createEtchedBorder());
        PB = new JButton("Portfolio");
        PB.setBackground(Color.BLACK);
        PB.setForeground(Color.YELLOW);
        PB.setBorder(BorderFactory.createEtchedBorder());
        MAB = new JButton("My Account");
        MAB.setBackground(Color.BLACK);
        MAB.setForeground(Color.YELLOW);
        MAB.setBorder(BorderFactory.createEtchedBorder());
        MPB = new JButton("Market Watch");
        MPB.setBackground(Color.BLACK);
        MPB.setForeground(Color.YELLOW);
        MPB.setBorder(BorderFactory.createEtchedBorder());
        TB = new JButton("Trade");
        TB.setBackground(Color.BLACK);
        TB.setForeground(Color.YELLOW);
        TB.setBorder(BorderFactory.createEtchedBorder());
        welcomeLabel = new JLabel("KAGAK");
        welcomeLabel.setForeground(Color.YELLOW);
        textLabel=new JLabel();
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.YELLOW);

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Ariel", Font.BOLD, 24));
        welcomeLabel.setBounds(650, 10, 220, 30);
        fromSA.getContentPane().add(welcomeLabel);
        TB.setFont(new Font("Ariel", Font.PLAIN, 22));
        TB.setBounds(650, 73, 220, 38);
        fromSA.getContentPane().add(TB);
        SB.setFont(new Font("Ariel", Font.PLAIN, 22));
        SB.setBounds(650, 157, 220, 38);
        fromSA.getContentPane().add(SB);


        PB.setFont(new Font("Ariel", Font.PLAIN, 22));
        PB.setBounds(650, 238, 220, 38);
        fromSA.getContentPane().add(PB);
        MAB.setFont(new Font("Ariel", Font.PLAIN, 22));
        MAB.setBounds(650, 406, 220, 38);
        fromSA.getContentPane().add(MAB);
        MPB.setFont(new Font("Ariel", Font.PLAIN, 22));
        MPB.setBounds(650, 490, 220, 38);
        fromSA.getContentPane().add(MPB);
        textLabel.setBounds(400, 450, 702, 702);
        String intro ="<html>Business hours: Monday - Friday 8am to 4pm\n";
        intro += "Business hours  may be subject to change on holidays\nContact: Toll free: Krish(CEO)- (647)-949-9946\nAbeer (COO) (647)-581-5709<html>";
        textLabel.setText(intro);
        textLabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        fromSA.getContentPane().add(textLabel);
        TB.addActionListener(this);
        MAB.addActionListener(this);
        MPB.addActionListener(this);
        PB.addActionListener(this);
        SB.addActionListener(this);
        NF.addActionListener(this);
        Info.addActionListener(this);
        JPanel teslaChartPanel = createTeslaChart();
        JPanel walmartChartPanel = createWalmartChart();
        JPanel microsoftChartPanel = createMicrosoftChart();
        JPanel googleChartPanel = createGoogleChart();

        int frameWidth = 900; // Your frame's width
        int graphWidth = 288; // 3 inches * 96 PPI
        int graphHeight = 288; // 3 inches * 96 PPI
        int margin = 20;
        int yPos = (fromSA.getHeight() - graphHeight) / 12; // Center the chart vertically in the frame
        teslaChartPanel.setBounds(frameWidth - graphWidth - margin + 600, yPos + 300, graphWidth, graphHeight);
        walmartChartPanel.setBounds(frameWidth - graphWidth - margin + 600, yPos, graphWidth, graphHeight);
        microsoftChartPanel.setBounds(frameWidth - graphWidth - margin - 550, yPos, graphWidth, graphHeight);
        googleChartPanel.setBounds(frameWidth - graphWidth - margin - 550, yPos + 300, graphWidth, graphHeight);
        fromSA.getContentPane().add(walmartChartPanel);
        fromSA.getContentPane().add(teslaChartPanel);
        fromSA.getContentPane().add(microsoftChartPanel);
        fromSA.getContentPane().add(googleChartPanel);





    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == NF) {
            System.out.println("NewsFeed!");
            try {
                Desktop.getDesktop().browse(new URI("https://finance.yahoo.com/topic/stock-market-news/?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAADpw17vo__S6lzo5iATMby6Xw4OjYbCo4h-OeGAuVQ16aeDCtF2ViS-gnkaa-HtqbGBKWfDZSfK5SSlEU_bVS8FnhrNDB9jkVlj1iEZkc6UOJyn0yp1DP6gwiguLWBZqmYQpfcx_wNHqmIxeXuu24elL9h0VpsRn7mbVtqQM_Azg"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource()==TB){

            System.out.println("Trading ! ");
            TradeApp trade=new TradeApp(UserID);
        }
        if(e.getSource()==SB){

            System.out.println("My Stocks ! ");
            try {
                showStock();
            } catch (JSONException | IOException e2) {
                e2.printStackTrace();
            }
            catch (Exception e1) {

                e1.printStackTrace();
            }

            fromSA.getContentPane().add(textLabel);
        }
        if(e.getSource()==MPB){

            System.out.println("Market performance");
            MarketWatch watch=new MarketWatch();
        }
        if(e.getSource()==MAB){

            System.out.println("My Account ! ");
            try {
                MyAccount acc=new MyAccount(UserID);
            } catch (JSONException | IOException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource()==PB){

            System.out.println("Portfolio ! ");
            PortfolioGui port=new PortfolioGui(UserID);
        }

        if (e.getSource() == Info) {
            displayMessage("The stock market trading app was developed in 2023 by a team of talented individuals, Krish, Abeer, Gurloveleen, Anas, and Kush, collectively known as KAGAK. \n The team has extensive experience in software development, particularly in Java and AWS technologies. \n They worked tirelessly to develop a feature-rich, user-friendly, and reliable trading app that meets the needs of modern-day investors.\n" +
                    "\n" +
                    "Krish Panjwani, the CEO of KAGAK, led the project and ensured that the app met the highest standards of quality, security, and performance. \n Abeer Batta, the COO, oversaw the app's development process and ensured that it was delivered on time and within budget. \n Kush Panjwani, the CFO, managed the project's financial aspects, ensuring that resources were allocated efficiently. \n Gurloveleen Dhillon, the MD, oversaw the app's marketing and sales efforts, ensuring that it reached the right audience. \n Anas, the CIO, ensured that the app's technology stack was up-to-date and that it met the latest industry standards.\n" +
                    "\n" +
                    "For any support or queries related to the app, users can contact KAGAK at kagakatsupport@kagak.com.");
        }

    }
}