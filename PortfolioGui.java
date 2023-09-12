
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.JSONException;

public class PortfolioGui extends JFrame implements ActionListener {

    private JFrame frmPortfolio;
    private String userId;
    private JButton btnViewPortfolio;
    private JButton btnPurchaseHistory;
    private JButton btnSaleHistory;
    private JButton btnViewDetails;
    private JTextField textFieldStock;

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
     * Launch the application.
     */
    public static void main(String[] args) {
        // PortfolioGui gui = new PortfolioGui("EsNN");
    }

    /**
     * Construct a new Portfolio GUI.
     *
     * @param userId The user ID for the portfolio.
     */
    public PortfolioGui(String userId) {
        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        if (backgroundImage != null) {
            PortfolioGui.ImagePanel mainPanel = new PortfolioGui.ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }
        setVisible(true);



        this.userId = userId;
        initialize();
    }

    /**
     * Display a message in a dialog box.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {



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


        Font font = new Font("Arial", Font.PLAIN, 18);


        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.messageForeground", Color.YELLOW);
        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.BLACK);
    }

    private void initialize() {

        frmPortfolio = this;
        frmPortfolio.setTitle("KAGAK");
        frmPortfolio.getContentPane().setForeground(Color.DARK_GRAY);
        frmPortfolio.setBounds(100, 100, 800, 600);
        frmPortfolio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPortfolio.getContentPane().setLayout(null);
        frmPortfolio.getContentPane().setBackground(new Color(69, 69, 69));
        frmPortfolio.setVisible(true);

        btnViewPortfolio = new JButton("View Portfolio");
        btnViewPortfolio.setBackground(new Color(12, 29, 40));
        btnViewPortfolio.setForeground(Color.YELLOW);
        btnViewPortfolio.setBorder(BorderFactory.createEtchedBorder());
        btnPurchaseHistory = new JButton("Purchase History");
        btnPurchaseHistory.setBackground(new Color(12, 29, 40));
        btnPurchaseHistory.setForeground(Color.YELLOW);
        btnPurchaseHistory.setBorder(BorderFactory.createEtchedBorder());
        btnSaleHistory = new JButton("Sale History");
        btnSaleHistory.setBackground(new Color(12, 29, 40));
        btnSaleHistory.setForeground(Color.YELLOW);
        btnSaleHistory.setBorder(BorderFactory.createEtchedBorder());
        btnViewDetails = new JButton(" View Details");
        btnViewDetails.setBackground(new Color(12, 29, 40));
        btnViewDetails.setForeground(Color.YELLOW);
        btnViewDetails.setBorder(BorderFactory.createEtchedBorder());
        textFieldStock = new JTextField();
        textFieldStock.setBackground(Color.DARK_GRAY);
        textFieldStock.setForeground(Color.ORANGE);
        textFieldStock.setBorder(BorderFactory.createEtchedBorder());

        btnViewPortfolio.setFont(new Font("Ariel", Font.PLAIN, 18));
        btnViewPortfolio.setBounds(71, 62, 177, 43);
        frmPortfolio.getContentPane().add(btnViewPortfolio);

        btnPurchaseHistory.setFont(new Font("Ariel", Font.PLAIN, 18));
        btnPurchaseHistory.setBounds(71, 143, 210, 43);
        frmPortfolio.getContentPane().add(btnPurchaseHistory);

        btnSaleHistory.setFont(new Font("Ariel", Font.PLAIN, 18));
        btnSaleHistory.setBounds(71, 233, 177, 43);
        frmPortfolio.getContentPane().add(btnSaleHistory);

        btnViewDetails.setFont(new Font("Ariel", Font.PLAIN, 18));
        btnViewDetails.setBounds(498, 143, 177, 43);
        frmPortfolio.getContentPane().add(btnViewDetails);

        textFieldStock.setBounds(498, 62, 177, 43);
        frmPortfolio.getContentPane().add(textFieldStock);

        btnViewPortfolio.addActionListener(this);
        btnSaleHistory.addActionListener(this);
        btnPurchaseHistory.addActionListener(this);
        btnViewDetails.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Portfolio portfolio = null;
        try {
            portfolio = new Portfolio(userId);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            if (e.getSource() == btnViewPortfolio) {
                portfolio.displayFP();
            }
            if (e.getSource() == btnPurchaseHistory) {
                portfolio.displayPH();
            }
            if (e.getSource() == btnSaleHistory) {
                portfolio.displaySH();
            }
            if (e.getSource() == btnViewDetails) {
                String stock = textFieldStock.getText();
                if (stock.equals("")) {
                    displayMessage("Please enter a stock id");
                    return;
                }
                Stock stockObject = new Stock(stock);
                portfolio.displaySP(stockObject);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
}