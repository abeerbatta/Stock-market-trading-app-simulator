import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The SecondPage class is responsible for creating the user interface for login and account creation.
 */
public class AfterLogin extends JFrame implements ActionListener {

    private JTextField userIDField;
    private JLabel userIDLabel;

    private JTextField usernameField, balanceField;

    private BufferedImage backgroundImage;

    private JButton submitButton;
    private JPasswordField passwordField;
    private String userId;
    private int choice;

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


        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageFont", null);
        UIManager.put("OptionPane.messageForeground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
    }

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
     * Constructor for the SecondPage class.
     * @param choice Determines the action to perform (1 for login, 2 for create account).
     * @throws FileNotFoundException
     * @throws JSONException
     */
    public AfterLogin(int choice) throws FileNotFoundException, JSONException {
        this.choice = choice;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        if (backgroundImage != null) {
            ImagePanel mainPanel = new ImagePanel();
            mainPanel.setLayout(null);
            setContentPane(mainPanel);
        }

        setSize(500, 600);

        setupSubmitButton();
        setupPasswordField();
        addPasswordLabel();

        if (choice == 1) {
            setupLoginUI();
        } else if (choice == 2) {
            setupCreateAccountUI();
        }

        setVisible(true);
    }


    /**
     * Set up the password text field.
     */
    private void setupPasswordField() {
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 240, 220, 40);
        passwordField.setBackground(Color.DARK_GRAY);
        passwordField.setBorder(BorderFactory.createEtchedBorder());
        passwordField.setForeground(Color.ORANGE);
        add(passwordField);
    }

    /**
     * Add password label to the UI.
     */
    private void addPasswordLabel() {
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 240, 120, 40);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(Color.YELLOW);
        add(passwordLabel);
    }
    private void setupSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setBounds(190, 300, 120, 40);
        submitButton.addActionListener(this);
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.setBackground(new Color(12, 29, 40));
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setForeground(Color.YELLOW);
        submitButton.setFocusable(false);
        add(submitButton);
    }
    /**
     * Set up the login UI.
     */
    public void setupLoginUI() {
        userIDLabel = new JLabel("User ID");
        userIDLabel.setBounds(60, 180, 120, 40);
        userIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userIDLabel.setForeground(Color.YELLOW);

        userIDField = new JTextField();
        userIDField.setBounds(190, 180, 220, 40);
        userIDField.setBackground(Color.DARK_GRAY);
        userIDField.setBorder(BorderFactory.createEtchedBorder());
        userIDField.setForeground(Color.ORANGE);

        add(userIDField);
        add(userIDLabel);
    }



    /**
     * Display a message dialog with the given message.
     * @param message The message to display in the dialog.
     */

    /**
     * Set up the create account UI.
     */
    public void setupCreateAccountUI() {
        JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(60, 120, 120, 40);
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userNameLabel.setForeground(Color.YELLOW);

        usernameField = new JTextField();
        usernameField.setBounds(190, 120, 220, 40);
        usernameField.setBackground(Color.DARK_GRAY);
        usernameField.setForeground(Color.ORANGE);
        usernameField.setBorder(BorderFactory.createEtchedBorder());

        balanceField = new JTextField();
        balanceField.setBounds(190, 180, 220, 40);
        balanceField.setBackground(Color.DARK_GRAY);
        balanceField.setForeground(Color.ORANGE);
        balanceField.setBorder(BorderFactory.createEtchedBorder());

        JLabel userBalanceLabel = new JLabel("Balance");
        userBalanceLabel.setBounds(60, 180, 120, 40);
        userBalanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userBalanceLabel.setForeground(Color.YELLOW);

        add(userNameLabel);
        add(userBalanceLabel);
        add(usernameField);
        add(balanceField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LogIn log = null;
        try {
            log = new LogIn();
        } catch (JSONException | FileNotFoundException ex) {
            ex.printStackTrace();
        }

        String passwordEntered = passwordField.getText();
        if (e.getSource() == submitButton) {
            if (choice == 1) {
                String userIDEntered = userIDField.getText();
                Account acc = new Account(userIDEntered, passwordEntered);
                boolean check = false;
                try {
                    check = log.signIn(acc);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                if (check) {
                    System.out.println("KAGAK APP \n START TRADING");
                    try {
                        OnLogin login = new OnLogin(userIDEntered);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    displayMessage("Invalid credentials");
                }
            } else if (choice == 2) {
                String userNameEntered = usernameField.getText();
                if (userNameEntered.equals("")) {
                    displayMessage("Please enter a valid user name");
                    return;
                }
                if (balanceField.getText().equals("")) {
                    displayMessage("Please enter balance");
                    return;
                }
                if (passwordEntered.equals("")) {
                    displayMessage("Please enter a valid password");
                    return;
                }
                double balanceEntered = Double.parseDouble(balanceField.getText());
                Account acc = new Account(userNameEntered, passwordEntered, balanceEntered);
                LogIn log2 = null;
                try {
                    log2 = new LogIn();
                } catch (JSONException | FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                try {
                    log2.signUp(acc);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Your UserId is " + acc.getUserId());
                displayMessage("Your UserId is " + acc.getUserId() + "\nPlease note it for future references");
                try {
                    OnLogin login = new OnLogin(acc.getUserId());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
