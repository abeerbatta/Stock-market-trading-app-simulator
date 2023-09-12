import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONException;

public class MarketWatch extends JFrame implements ActionListener {


    JButton getDataButton, popularStocksButton, IPOSButton;
    JTextField stockNameField, stockValueField;

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

    /**
     * Displays a custom message using JOptionPane with a more attractive appearance.
     *
     * @param message The message to display.
     */
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

    /**
     * Constructs the MarketWatch window with the appropriate components and layout.
     */

    public MarketWatch() {

        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        if (backgroundImage != null) {
            MarketWatch.ImagePanel mainPanel = new MarketWatch.ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }
        setVisible(true);


        popularStocksButton = new JButton("Popular Stocks");
        popularStocksButton.setBackground(new Color(12, 29, 40));
        popularStocksButton.setBorder(BorderFactory.createEtchedBorder());
        popularStocksButton.setForeground(Color.YELLOW);
        popularStocksButton.setBounds(180, 180, 150, 30);
        popularStocksButton.addActionListener(this);
        popularStocksButton.setBorder(BorderFactory.createEtchedBorder());
        popularStocksButton.setFont(new Font("Arial", Font.BOLD, 16));
        popularStocksButton.setFocusable(false);


        stockNameField = new JTextField();
        stockNameField.setBackground(Color.DARK_GRAY);
        stockNameField.setForeground(Color.ORANGE);
        stockNameField.setBorder(BorderFactory.createEtchedBorder());
        stockNameField.setBounds(180, 70, 200, 30);


        stockValueField = new JTextField();
        stockValueField.setBackground(Color.DARK_GRAY);
        stockValueField.setForeground(Color.ORANGE);
        stockValueField.setBorder(BorderFactory.createEtchedBorder());
        stockValueField.setBounds(180, 120, 200, 30);
        stockValueField.setEditable(false);

        JLabel stockNameLabel = new JLabel("Stock Name");
        stockNameLabel.setBounds(70, 70, 140, 25);
        stockNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        stockNameLabel.setForeground(Color.YELLOW);

        getDataButton = new JButton("Get Data");
        getDataButton.setBackground(new Color(12, 29, 40));
        getDataButton.setBorder(BorderFactory.createEtchedBorder());
        getDataButton.setForeground(Color.YELLOW);
        getDataButton.setBounds(410, 70, 100, 30);
        getDataButton.addActionListener(this);
        getDataButton.setBorder(BorderFactory.createEtchedBorder());
        getDataButton.setFont(new Font("Arial", Font.BOLD, 16));
        getDataButton.setFocusable(false);



        IPOSButton = new JButton("Upcoming IPOS");
        IPOSButton.setBackground(new Color(12, 29, 40));
        IPOSButton.setBorder(BorderFactory.createEtchedBorder());
        IPOSButton.setForeground(Color.YELLOW);
        IPOSButton.setBounds(360, 180, 150, 30);
        IPOSButton.addActionListener(this);
        IPOSButton.setBorder(BorderFactory.createEtchedBorder());
        IPOSButton.setFont(new Font("Arial", Font.BOLD, 16));
        IPOSButton.setFocusable(false);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(69, 69, 69));
        this.setVisible(true);


        this.add(getDataButton);
        this.add(stockNameLabel);
        this.add(stockNameField);
        this.add(stockValueField);
        this.add(popularStocksButton);
        this.add(IPOSButton);
    }
    /**
     * Formats the stock data for display.
     *
     * @param s The Stock object to format.
     * @return A formatted string representing the stock data.
     * @throws JSONException if there's an issue with the JSON data.
     */
    public String formatStockData(Stock s) throws JSONException {
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        String latestDay = date.toString().substring(0, 10);
        String day = date.getDayOfWeek().toString();
        String earlierDay = date.minusDays(7).toString().substring(0, 10);

        if (day.equals("SUNDAY")) {
            latestDay = date.minusDays(2).toString().substring(0, 10);
            earlierDay = date.minusDays(9).toString().substring(0, 10);
        } else if (day.equals("SATURDAY")) {
            latestDay = date.minusDays(1).toString().substring(0, 10);
            earlierDay = date.minusDays(8).toString().substring(0, 10);
        }

        return (String.format("%s | %.3f | %s", s.getSymbol(), s.getcp(), s.StockTrend(latestDay, earlierDay)));
    }

    public void popularTechStocks() throws JSONException {
        System.out.println("*".repeat(50));
        System.out.println("Stock : Current price : Performance");
        Stock[] stockArr = {new Stock("GOOGL"), new Stock("TXN"), new Stock("AAPL")};
        System.out.println("*".repeat(50));
        String s = "";
        s += "\n" + "*".repeat(50) + "\n";
        for (Stock stock : stockArr) {
            s += "\n" + formatStockData(stock);
        }
        s += "\n" + "*".repeat(50);
        displayMessage(s);
    }

    public void IPOS() {
        String s = "";
        s += "\t\tUpcoming IPOs";
        s += "\n" + "-".repeat(50);
        s += "\n" + "STRIPE";
        s += "\n" + "DATABRICKS";
        s += "\n" + "REDDIT";
        s += "\n" + "INSTACART";
        s += "\n" + "DISCORD";
        s += "\n" + "CHIME FINANCIAL";
        s += "\n" + "SPACEX";
        s += "\n" + "-".repeat(50);
        System.out.println(s);
        displayMessage(s);
    }

    public void completeStockData(String symbol) throws JSONException {
        System.out.println("*".repeat(50));
        Stock stock = new Stock(symbol);
        formatStockData(stock);
        System.out.println("*".repeat(50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getDataButton) {
            try {
                System.out.println("*".repeat(50));
                System.out.println("Stock : Current price : Performance");
                Stock stock = new Stock(stockNameField.getText().toString());
                formatStockData(stock);
                stockValueField.setText(String.format("%.2f", stock.getcp()));
                System.out.println("*".repeat(50));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
                stockValueField.setText("Error: Invalid Stock Symbol");
            }

        }
        if (e.getSource() == IPOSButton) {
            IPOS();
        }
        if (e.getSource() == popularStocksButton) {
            try {
                popularTechStocks();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws JSONException {
        MarketWatch marketWatch = new MarketWatch();
    }
}