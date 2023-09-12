import org.json.JSONException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePageGUI implements ActionListener {
    // Declare buttons
    private JButton createAccountButton, loginButton, exitButton;

    // Declare background image
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

    public HomePageGUI() {
        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("/Users/abeerbatta/Downloads/stockimage2.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(140, 150, 220, 60);
        createAccountButton.addActionListener(this);
        createAccountButton.setBackground(new Color(12, 29, 40));
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 20));
        createAccountButton.setForeground(Color.YELLOW);
        createAccountButton.setBorder(BorderFactory.createEtchedBorder());
        createAccountButton.setFocusable(false);


        loginButton = new JButton("Log In");
        loginButton.setBounds(140, 230, 220, 60);
        loginButton.addActionListener(this);
        loginButton.setBackground(new Color(12, 29, 40));
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setForeground(Color.YELLOW);
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);


        exitButton = new JButton("Exit ");
        exitButton.setBounds(140, 310, 220, 60);
        exitButton.addActionListener(this);
        exitButton.setBackground(new Color(12, 29, 40));
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBorder(BorderFactory.createEtchedBorder());
        exitButton.setFocusable(false);


        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);


        if (backgroundImage != null) {
            ImagePanel mainPanel = new ImagePanel();
            mainPanel.setLayout(null);

            mainPanel.add(createAccountButton);
            mainPanel.add(loginButton);
            mainPanel.add(exitButton);
            mainFrame.add(mainPanel);
        }
        mainFrame.setVisible(true);
    }

    /**
     * actionPerformed is responsible for handling button clicks
     * and performing the corresponding actions.
     *
     * @param e An ActionEvent object that contains information about the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createAccountButton) {
            System.out.println("Create Account");
            try {
                AfterLogin np = new AfterLogin(2);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        } else if (e.getSource() == loginButton) {
            System.out.println("Log In");
            try {
                AfterLogin np = new AfterLogin(1);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        } else if (e.getSource() == exitButton) {
            System.out.println("Exit ! ");
            System.exit(0);
        }
    }
}