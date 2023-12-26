package login_register;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterForm {

    private JFrame frame;
    private JPanel titleBar;
    private JLabel titleLabel;
    private JLabel closeLabel;
    private JLabel minimizeLabel;
    private JPanel contentPanel;
    private JTextField fullnameField;
    private JTextField usernameField;
//    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;
//    private JLabel profilePictureImage;
//    private JButton browseButton;
    private JButton buttonRegister;
    private JButton buttonLogin;
//    private String selectedImage;
//    private BufferedImage profileImage;

    // dragging the form
    private boolean isDragging = false;
    private Point mouseOffset;

    // the database variable
    private DatabaseConnection dbConnection;


    public RegisterForm() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        titleBar = new JPanel();
        titleBar.setLayout(null);
        titleBar.setBackground(new Color(255, 204, 0));
        titleBar.setPreferredSize(new Dimension(frame.getWidth(), 30));
        frame.add(titleBar, BorderLayout.NORTH);

        titleLabel = new JLabel("Register Form");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(10, 0, 200, 30);
        titleBar.add(titleLabel);

        closeLabel = new JLabel("X");
        closeLabel.setForeground(Color.BLACK);
        closeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setBounds(frame.getWidth() - 30, 0, 30, 30);

        closeLabel.addMouseListener(new MouseAdapter() {
            // close the register form
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            // mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setForeground(new Color(60, 179, 113));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeLabel.setForeground(Color.BLACK);
            }

        });

        titleBar.add(closeLabel);

        minimizeLabel = new JLabel("-");
        minimizeLabel.setForeground(Color.BLACK);
        minimizeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minimizeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeLabel.setBounds(frame.getWidth() - 60, 0, 30, 30);

        minimizeLabel.addMouseListener(new MouseAdapter() {
            // iconify (minimize) the login form
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }

            // mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeLabel.setForeground(new Color(60, 179, 113));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizeLabel.setForeground(Color.BLACK);
            }

        });

        titleBar.add(minimizeLabel);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(236, 240, 241));
        contentPanel.setBorder(new LineBorder(new Color(255, 204, 0), 2));
        contentPanel.setBounds(10, 30, frame.getWidth() - 20, frame.getHeight() - 40);

        frame.add(contentPanel);

        // labels and textfields
        JLabel fullnameLabel = new JLabel("Full Name:");
        fullnameLabel.setBounds(30, 20, 120, 25);
        contentPanel.add(fullnameLabel);

        fullnameField = new JTextField();
        fullnameField.setBounds(150, 20, 250, 25);
        contentPanel.add(fullnameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 50, 80, 25);
        contentPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 250, 25);
        contentPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 80, 80, 25);
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 250, 25);
        contentPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(30, 110, 120, 25);
        contentPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 110, 250, 25);
        contentPanel.add(confirmPasswordField);

//        JLabel phoneLabel = new JLabel("Phone:");
//        phoneLabel.setBounds(30, 140, 80, 25);
//        contentPanel.add(phoneLabel);
//
//        phoneField = new JTextField();
//        phoneField.setBounds(150, 140, 250, 25);
//        contentPanel.add(phoneField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 170, 80, 25);
        contentPanel.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 170, 100, 25);
        maleRadioButton.setFocusPainted(false);
        maleRadioButton.setBorderPainted(false);
        maleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maleRadioButton.setSelected(true);
        contentPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(260, 170, 100, 25);
        femaleRadioButton.setFocusPainted(false);
        femaleRadioButton.setBorderPainted(false);
        femaleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(femaleRadioButton);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);


        // button register
        buttonRegister = new JButton("Register");
        buttonRegister.setBounds(225, 380, 170, 35);
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 14));
        buttonRegister.setBackground(new Color(60, 179, 113));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setFocusPainted(false);
        buttonRegister.setBorderPainted(false);
        buttonRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonRegister.addActionListener((e) -> {

            registerUser();

        });

        buttonRegister.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonRegister.setBackground(new Color(46, 139, 87));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonRegister.setBackground(new Color(60, 179, 113));
            }

        });

        contentPanel.add(buttonRegister);

        // login button
        buttonLogin = new JButton("Back to Login");
        buttonLogin.setBounds(40, 380, 170, 35);
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonLogin.setBackground(new Color(70, 130, 180));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setFocusPainted(false);
        buttonLogin.setBorderPainted(false);
        buttonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // open login form
        buttonLogin.addActionListener((e) -> {

            frame.dispose();
            new LoginForm();

        });

        buttonLogin.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonLogin.setBackground(new Color(0, 102, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonLogin.setBackground(new Color(70, 130, 180));
            }

        });

        contentPanel.add(buttonLogin);

        // Mouse listener for window dragging
        titleBar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                isDragging = true;
                mouseOffset = e.getPoint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                isDragging = false;

            }

        });

        // Mouse motion listener for window dragging
        titleBar.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    // When the mouse is dragged, this event is triggered

                    // Get the current location of the mouse on the screen
                    Point newLocation = e.getLocationOnScreen();

                    // Calculate the new window location by adjusting for the initial mouse offset
                    newLocation.translate(-mouseOffset.x, -mouseOffset.y);

                    // Set the new location of the main window to achieve dragging effect
                    frame.setLocation(newLocation);
                }
            }

        });

        dbConnection = new DatabaseConnection();

        frame.setVisible(true);

    }

    // show error message method
    private void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
    }

    // show success message method
    private void showSuccessMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // close the registration form method
    private void closeRegisterForm()
    {
        frame.dispose();
    }

    // open login form method
    private void openLoginForm()
    {
        new LoginForm();
    }


    // create a function to check if a username already exists
    private boolean doesUsernameExists(String username)
    {
        try{

            Connection connection = dbConnection.getConnection();
            String query = "SELECT * FROM `user` WHERE `username` = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) { return true; }

        }
        catch(SQLException ex){
            System.out.println("userdoesnotexists");
            ex.printStackTrace(); }

        return false;
    }


    // create a method to register a new user
    private void registerUser()
    {
        String fullname = fullnameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        //String phone = phoneField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";

        // check for empty fields
        if(fullname.trim().isEmpty() || username.trim().isEmpty() ||
                password.trim().isEmpty() || confirmPassword.trim().isEmpty() )
        {
            showErrorMessage("All Field must be filled");
        }
        else if(!password.equals(confirmPassword))
        {
            showErrorMessage("Passwords do not match");
        }
//        else if(doesUsernameExists(username))
//        {
//            showErrorMessage("This username already exists");
//        }
        else
        {
            try
            {
                Connection connection = dbConnection.getConnection();
                String query = "INSERT INTO `user`(`fullname`, `username`, `password`, `gender`) VALUES (?, ?, ?, ?)";
                PreparedStatement prepareStatement = connection.prepareStatement(query);

                prepareStatement.setString(1, fullname);
                prepareStatement.setString(2, username);
                prepareStatement.setString(3, password);
                prepareStatement.setString(4, gender);

                int rowsAffected = prepareStatement.executeUpdate();

                if(rowsAffected > 0)
                {
                    showSuccessMessage("Registration Successful");
                }
                else{ showErrorMessage("Registration Failed"); }


            }
            catch(SQLException ex)
            {
                showErrorMessage("Error: " + ex.getMessage());
            }

        }


    }



    public static void main(String[] args) {
        new RegisterForm();
    }

}
