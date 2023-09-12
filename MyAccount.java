import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.json.JSONException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


/**
 * This class represents a user's account in a stock trading application.
 */
public class MyAccount extends JFrame implements ActionListener {
    private String userId;
    private JButton historyButton, addMoneyButton, deductMoneyButton;
    private JTextField moneyTextField;
    private StockAccount userStockAccount;
    private JLabel balanceLabelData;

    private BufferedImage backgroundImage;

    /**
     * Custom JPanel class to paint the background image.
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
    class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    public static void main(String args[]) {

    }

    /**
     * This method displays user details in the UI.
     */
    public void displayUserDetails() throws JSONException {
        JLabel balanceLabel = new JLabel("Balance");
        balanceLabelData = new JLabel(String.valueOf(userStockAccount.getBalance()));
        balanceLabel.setBounds(70, 110, 120, 25);
        balanceLabel.setForeground(Color.YELLOW);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        balanceLabelData.setBounds(140, 110, 120, 25);
        balanceLabelData.setForeground(Color.YELLOW);
        balanceLabelData.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel userIdLabel = new JLabel("User Id");
        JLabel userIdLabelData = new JLabel(userStockAccount.getUserID());
        userIdLabel.setBounds(70, 50, 120, 25);
        userIdLabel.setForeground(Color.YELLOW);
        userIdLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        userIdLabelData.setBounds(140, 50, 120, 25);
        userIdLabelData.setForeground(Color.YELLOW);
        userIdLabelData.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel passwordLabel = new JLabel("Password");
        JLabel passwordLabelData = new JLabel(userStockAccount.getPassword());
        passwordLabel.setBounds(70, 80, 120, 25);
        passwordLabel.setForeground(Color.YELLOW);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabelData.setBounds(140, 80, 120, 25);
        passwordLabelData.setForeground(Color.YELLOW);
        passwordLabelData.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel usernameLabel = new JLabel("Username");
        JLabel usernameLabelData = new JLabel(userStockAccount.getUsername());
        usernameLabel.setBounds(70, 20, 120, 25);
        usernameLabel.setForeground(Color.YELLOW);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameLabelData.setBounds(140, 20, 120, 25);
        usernameLabelData.setForeground(Color.YELLOW);
        usernameLabelData.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(userIdLabelData);
        this.add(usernameLabel);
        this.add(usernameLabelData);
        this.add(userIdLabel);
        this.add(passwordLabel);
        this.add(passwordLabelData);
        this.add(balanceLabel);
        this.add(balanceLabelData);
    }

    /**
     * Constructor for MyAccount class.
     */
    MyAccount(String userId) throws JSONException, IOException {

        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (backgroundImage != null) {
            MyAccount.ImagePanel mainPanel = new MyAccount.ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }
        setVisible(true);
        userStockAccount = new StockAccount(userId);
        this.userId = userId;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(69, 69, 69));
        addMoneyButton = new JButton("Add");
        addMoneyButton.setBounds(70, 188, 120, 25);
        addMoneyButton.setBackground(new Color(12, 29, 40));
        addMoneyButton.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        addMoneyButton.setForeground(Color.YELLOW);
        addMoneyButton.setBorder(BorderFactory.createEtchedBorder());
        addMoneyButton.setFocusable(false);
        addMoneyButton.addActionListener(this);
        historyButton = new JButton("History");
        historyButton.setBounds(70, 358, 120, 25);
        historyButton.addActionListener(this);
        historyButton.setBackground(new Color(12, 29, 40));
        historyButton.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        historyButton.setForeground(Color.YELLOW);
        historyButton.setBorder(BorderFactory.createEtchedBorder());
        historyButton.setFocusable(false);
        moneyTextField = new JTextField();
        moneyTextField.setBounds(220, 223, 120, 25);
        moneyTextField.setPreferredSize(new Dimension(120, 25));
        moneyTextField.setForeground(Color.ORANGE);
        moneyTextField.setBackground(Color.DARK_GRAY);
        deductMoneyButton = new JButton("Deduct");
        deductMoneyButton.setBounds(70, 218, 120, 25);
        deductMoneyButton.setBackground(new Color(12, 29, 40));
        deductMoneyButton.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        deductMoneyButton.setForeground(Color.YELLOW);
        deductMoneyButton.setBorder(BorderFactory.createEtchedBorder());
        deductMoneyButton.setFocusable(false);
        deductMoneyButton.addActionListener(this);
        this.add(moneyTextField);
        this.setVisible(true);
        this.add(historyButton);
        this.add(addMoneyButton);
        this.add(deductMoneyButton);
        this.displayUserDetails();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == historyButton) {
            // History
            System.out.println("History Of Stocks traded :");
            LinkedList userHistory = userStockAccount.StockList();
            String str = "History Of Stocks traded :";
            for (int i = 0; i < userHistory.size(); i++) {
                String s = userHistory.get(i).toString();
                System.out.println(s);
                str += "\n" + (i + 1) + "." + s;
            }
            displayMessage(str);
        }
        if (e.getSource() == addMoneyButton) {

            try {
                userStockAccount.deposit(Double.parseDouble(moneyTextField.getText()));
                balanceLabelData.setText(String.valueOf(userStockAccount.getBalance()));
                System.out.println(String.format("Added %s to account balance ! ", moneyTextField.getText()));
                displayMessage(String.format("Added %s to account balance ! ", moneyTextField.getText()));
            } catch (JSONException | IOException jsonException) {
                jsonException.printStackTrace();
            }
        }
        if (e.getSource() == deductMoneyButton) {

            try {
                userStockAccount.withdraw(Double.parseDouble(moneyTextField.getText()));
                balanceLabelData.setText(String.valueOf(userStockAccount.getBalance()));
                System.out.println(String.format("Deducted %s from account balance ! ", moneyTextField.getText()));
                displayMessage(String.format("Deducted %s from account balance ! ", moneyTextField.getText()));
            }
            catch (IOException | JSONException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}