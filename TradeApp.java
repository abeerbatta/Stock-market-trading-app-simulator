import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class TradeApp extends JFrame implements ActionListener {
    StockAccount stockAccounts;
    private String userId;
    private String trans;
    private String stockId;
    private int amount;
    private JFrame frmBuyStock;
    private JTextField StockId;
    private JTextField textField;
    private JButton clearbutt;
    private JButton confirmbutt;
    private JComboBox typeoftrade;
    private JTextArea areaofbill;
    private JButton billbutt;
    private JLabel account;
    private JCheckBox checkbocks;

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
        // Display the message with custom UI
        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.BLACK);

        JFrame frame = null;
        JOptionPane.showMessageDialog(frame, message, "Custom Message", JOptionPane.INFORMATION_MESSAGE);

        // Reset UI to default
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageFont", null);
        UIManager.put("OptionPane.messageForeground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
    }
    private JComboBox<String> stockSymbolComboBox;


    public static void main(String[] args) {

    }

    public TradeApp (String userId) {
        this.userId = userId;
        try {
            stockAccounts = new StockAccount(userId);
        } catch (JSONException | IOException e) {

            e.printStackTrace();
        }
        initialize();
    }

    public boolean amountExists(String stock, int amount) {
        int no = stockAccounts.howmuchstock(stock);
        if (no >= amount) return true;
        return false;
    }

    /**
     * Initialize the contents of the frame.
     */

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
    private void initialize() {

        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        if (backgroundImage != null) {
            TradeApp.ImagePanel mainPanel = new TradeApp.ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }
        setVisible(true);


        frmBuyStock = this;
        frmBuyStock.setTitle("KAGAK");
        frmBuyStock.setBounds(100, 100, 800, 600);
        frmBuyStock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBuyStock.getContentPane().setLayout(null);
        frmBuyStock.getContentPane().setBackground(new Color(69, 69, 69));

        String[] famousStockSymbols = {"AAPL", "GOOGL", "MSFT", "AMZN", "META", "TSLA", "NVDA", "JNJ", "WMT", "V"};

        stockSymbolComboBox = new JComboBox<>(famousStockSymbols);
        stockSymbolComboBox.setFont(new Font("Ariel", Font.PLAIN, 18));
        stockSymbolComboBox.setBounds(161, 116, 147, 36);
        stockSymbolComboBox.setBackground(Color.DARK_GRAY);
        stockSymbolComboBox.setForeground(Color.ORANGE);
        stockSymbolComboBox.setEditable(true);
        ((JTextField) stockSymbolComboBox.getEditor().getEditorComponent()).setBackground(Color.DARK_GRAY);
        ((JTextField) stockSymbolComboBox.getEditor().getEditorComponent()).setForeground(Color.ORANGE);
        frmBuyStock.getContentPane().add(stockSymbolComboBox);

        JLabel lblNewLabel = new JLabel("Stock Id");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Ariel", Font.PLAIN, 18));
        lblNewLabel.setBounds(28, 119, 127, 33);
        lblNewLabel.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(lblNewLabel);

        JLabel lblNoOfShares = new JLabel("No of shares");
        lblNoOfShares.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNoOfShares.setFont(new Font("Ariel", Font.PLAIN, 18));
        lblNoOfShares.setBounds(28, 177, 127, 33);
        lblNoOfShares.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(lblNoOfShares);

        textField = new JTextField();
        textField.setFont(new Font("Ariel", Font.PLAIN, 18));
        textField.setColumns(10);
        textField.setBounds(161, 175, 147, 36);
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.ORANGE);
        frmBuyStock.getContentPane().add(textField);

        JLabel lbltrans = new JLabel("trans");
        lbltrans.setHorizontalAlignment(SwingConstants.RIGHT);
        lbltrans.setFont(new Font("Ariel", Font.PLAIN, 18));
        lbltrans.setBounds(28, 72, 127, 33);
        lbltrans.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(lbltrans);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        typeoftrade = new JComboBox(new String[]{"buy","sell"});
        JComboBox editableComboBox = new JComboBox(new String[]{"buy","sell"});
        typeoftrade.setForeground(Color.ORANGE);
        typeoftrade.setFont(new Font("Serif", Font.BOLD, 16));
        typeoftrade.setEditable(true);
        typeoftrade.getEditor().getEditorComponent().setBackground(Color.DARK_GRAY);
        ((JTextField) typeoftrade.getEditor().getEditorComponent()).setForeground(Color.ORANGE);
        JTextField text1 = ((JTextField) editableComboBox.getEditor().getEditorComponent());
        text1.setBackground(Color.DARK_GRAY);
        text1.setForeground(Color.ORANGE);
        JComboBox coloredArrowsCombo = editableComboBox;
        Component[] comp = coloredArrowsCombo.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof MetalComboBoxButton) {
                MetalComboBoxButton coloredArrowsButton = (MetalComboBoxButton) comp[i];
                coloredArrowsButton.setBackground(null);
                break;
            }
        }

        typeoftrade.setToolTipText("Buy");
        typeoftrade.setEditable(true);

        typeoftrade.setBounds(161, 71, 127, 34);
        frmBuyStock.getContentPane().add(typeoftrade);

        checkbocks = new JCheckBox("Send confirmation message");
        checkbocks.setSelected(true);
        checkbocks.setFont(new Font("Ariel", Font.PLAIN, 22));
        checkbocks.setBounds(130, 231, 346, 50);
        checkbocks.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(checkbocks);
        String text = "";
        try {
            double bal = stockAccounts.getBalance();
            String val = String.format("%.2f", bal);
            text += "Balance: " + val ;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        account = new JLabel(text);
        account.setHorizontalAlignment(SwingConstants.CENTER);
        account.setFont(new Font("Ariel", Font.PLAIN, 18));
        account.setBounds(427, 20, 349, 190);
        account.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(account);

        billbutt = new JButton("Generate bill");
        billbutt.setFont(new Font("Ariel", Font.PLAIN, 20));
        billbutt.setBounds(116, 301, 171, 44);
        billbutt.setBackground(new Color(12, 29, 40));
        billbutt.setBorder(BorderFactory.createEtchedBorder());
        billbutt.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(billbutt);
        clearbutt = new JButton("Clear");
        clearbutt.setFont(new Font("Ariel", Font.PLAIN, 20));
        clearbutt.setBounds(511, 301, 171, 44);
        clearbutt.setBackground(new Color(12, 29, 40));
        clearbutt.setBorder(BorderFactory.createEtchedBorder());
        clearbutt.setForeground(Color.YELLOW);
        frmBuyStock.getContentPane().add(clearbutt);

        areaofbill = new JTextArea();
        areaofbill.setEnabled(true);
        areaofbill.setEditable(false);
        areaofbill.setLineWrap(true);
        areaofbill.setFont(new Font("Monospaced", Font.PLAIN, 18));
        areaofbill.setForeground(Color.ORANGE);
        areaofbill.setBounds(117, 402, 504, 135);
        areaofbill.setBackground(Color.DARK_GRAY);
        areaofbill.setBorder(BorderFactory.createEtchedBorder());
        frmBuyStock.getContentPane().add(areaofbill);

        confirmbutt = new JButton("Confirm");
        this.setVisible(true);
        confirmbutt.setFont(new Font("Ariel", Font.PLAIN, 20));
        confirmbutt.setBounds(319, 301, 171, 44);
        confirmbutt.setBackground(new Color(12, 29, 40));
        confirmbutt.setForeground(Color.YELLOW);
        confirmbutt.setBorder(BorderFactory.createEtchedBorder());
        frmBuyStock.getContentPane().add(confirmbutt);
        confirmbutt.addActionListener(this);
        clearbutt.addActionListener(this);
        billbutt.addActionListener(this);

        StockId = new JTextField();

    }



    public double possibleAmount(String stockId, int amount) {
        double price;
        double total = 0;
        try {
            Stock stock = new Stock(stockId);
            price = stock.getcp();
            total = amount * price;
        } catch (JSONException e1) {
            System.out.println("Enter a valid stock symbol");
            e1.printStackTrace();
        }
        return total;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        trans = typeoftrade.getSelectedItem().toString();

        stockId = ((JTextField) stockSymbolComboBox.getEditor().getEditorComponent()).getText();
        String message = "";
        if (stockId.equals("")) {

            displayMessage("Please enter a valid stock symbol");
            return;
        }
        stockId = stockId.toUpperCase();
        String num = textField.getText();
        if (num.equals("")) {

            displayMessage("Please enter a valid number");
            return;
        }
        amount = Integer.parseInt(num);
        if (e.getSource() == confirmbutt) {
            double diff = 0;
            double prev = 0;
            boolean success = true;
            try {
                prev = stockAccounts.getBalance();
                System.out.println("Balance before transaction:" + String.format("%.2f", prev));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            double after = prev;

            if (trans.equals("buy")) {
                if (possibleAmount(stockId, amount) > prev) {
                    displayMessage("transaction failed!\nYou do not have enough balance");
                    return;
                }
                try {
                    stockAccounts.buyStock(stockId, amount);
                    after = stockAccounts.getBalance();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (trans.equals("sell")) {
                if (!stockAccounts.checkifstock(stockId)) {
                    success = false;
                }
                if (!amountExists(stockId, amount)) {
                    success = false;
                }

                if (success) {
                    try {
                        stockAccounts.sellStock(stockId, amount);
                        after = stockAccounts.getBalance();
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }

            String bal = String.format("%.2f", after);
            System.out.println("Balance after transaction:" + bal);
            diff = after - prev;
            String diffstr = String.format("%.2f", Math.abs(diff));

            String text =  bal ;
            account.setText(text);

            if (checkbocks.isSelected()) {
                message = "transaction successful!";
                if (!success || diff == 0) {
                    if (trans.equals("Sell")) message = "transaction failed!\nYou do not own enough stocks";
                    else message = "transaction failed!";
                } else if (diff > 0) message += "\r\n" + diffstr + " added to your account";
                else message += "\r\n" + diffstr + " deducted from your account";
                displayMessage(message);
            }
        }
        if (e.getSource() == billbutt) {
            double price = 0;
            double total = 0;
            try {
                Stock s = new Stock(stockId);
                price = s.getcp();
                total = amount * price;
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            String stprice = String.format("%.2f", price);

            String totalstr = String.format("%.2f", total);
            areaofbill.setText("Stock symbol:" + stockId + "\r\ntransaction type: " + trans + "\r\nStock price: " + stprice + "\r\nQuantity: " + amount + "\r\nAmount transacted: " + totalstr + "\r\nDate: " + LocalDate.now().toString());
        }
        if (e.getSource() == clearbutt) {
            StockId.setText("");
            textField.setText("");
            areaofbill.setText("");
        }
    }
}