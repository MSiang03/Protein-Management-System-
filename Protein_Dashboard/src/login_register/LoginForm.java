package login_register;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm {

    private final JFrame frame;
    private final JPanel titleBar;
    private final JLabel titleLabel;
    private final JLabel closeLabel;
    private JLabel minimizeLabel;
    private JPanel contentPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton buttonLogin;
    private JButton buttonRegister;

    //dragging the form
    private boolean isDragging = false;
    private Point mouseOffset;

    //database connection
    private DatabaseConnection dbConnection;

    public LoginForm()
    {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        titleBar = new JPanel();
        titleBar.setLayout(null);
        titleBar.setBackground(new Color(200, 255, 208));
        titleBar.setPreferredSize(new Dimension(frame.getWidth(),30));
        frame.add(titleBar,BorderLayout.NORTH);

        titleLabel = new JLabel("Login");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Courier New",Font.BOLD,16));
        titleLabel.setBounds(10,0,200,30);
        titleBar.add(titleLabel);

        closeLabel = new JLabel("X");
        closeLabel.setForeground(Color.BLACK);
        closeLabel.setFont(new Font("Courier New",Font.BOLD,16));
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeLabel.setBounds(frame.getWidth()-30,0,30,30);

        closeLabel.addMouseListener(new MouseAdapter(){
            //close the login form
            @Override
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }

            //mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e){
                closeLabel.setForeground(new Color(60,179,113));
            }

            @Override
            public void mouseExited(MouseEvent e){
                closeLabel.setForeground(Color.BLACK);
            }

        });

        titleBar.add(closeLabel);

        minimizeLabel = new JLabel("-");
        minimizeLabel.setForeground(Color.BLACK);
        minimizeLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minimizeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeLabel.setBounds(frame.getWidth()-60,0,30,30);

        minimizeLabel.addMouseListener(new MouseAdapter() {
            // iconify (minimize) the login form
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }

            //mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e){
                minimizeLabel.setForeground(new Color(60,179,113));
            }

            @Override
            public void mouseExited(MouseEvent e){
                minimizeLabel.setForeground(Color.BLACK);
            }
        });

        titleBar.add(minimizeLabel);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(236,240,241));
        contentPanel.setBounds(10,30,frame.getWidth()-20, frame.getHeight()-40);

        frame.add(contentPanel);

        //Username label and input field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50,40,90,25);
        usernameLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        contentPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150,40,200,25);
        contentPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,80,90,25);
        passwordLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150,80,200,25);
        contentPanel.add(passwordField);

        // login button
        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(100,120,100,35);
        buttonLogin.setFont(new Font("Courier New",Font.BOLD,14));
        buttonLogin.setBackground(new Color(255,102,0));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setFocusPainted(false);
        buttonLogin.setBorderPainted(false);
        buttonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // open dashboard form
        buttonLogin.addActionListener((e) -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean loginState = checkLogin(username, password);

            if(loginState){
                frame.dispose();
                new Protein_Dashboard();

            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Invalid Username OR Password","Invalid Data",JOptionPane.ERROR_MESSAGE);
            }

        });

        buttonLogin.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e)
            {
                buttonLogin.setBackground(new Color(255,51,0));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                buttonLogin.setBackground(new Color(255,102,0));
            }

        });

        contentPanel.add(buttonLogin);

        // register button
        buttonRegister = new JButton("Register");
        buttonRegister.setBounds(220,120,100,35);
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 14));
        buttonRegister.setBackground(new Color(0,102,255));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setFocusPainted(false);
        buttonRegister.setBorderPainted(false);
        buttonRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // open register form
        buttonRegister.addActionListener((e) -> {

            frame.dispose();
            new RegisterForm();

        });


        buttonRegister.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e)
            {
                buttonRegister.setBackground(new Color(0,51,204));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                buttonRegister.setBackground(new Color(0,102,255));
            }

        });


        contentPanel.add(buttonRegister);


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
            public void mouseDragged(MouseEvent e)
            {
                if(isDragging)
                {
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

        // make the form visible
        frame.setVisible(true);
    }

    // create a function to check the username and password
    private boolean checkLogin(String username, String password)
    {
        Connection connection = dbConnection.getConnection();

        if(connection != null)
        {
            try{

                String query = "SELECT * FROM `user` WHERE `username` = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if(rs.next())
                {
                    String storedPassword = rs.getString("password");

                    return password.equals(storedPassword);
                }

            }
            catch(SQLException ex){ ex.printStackTrace(); }
        }

        return false;

    }


    public static void main(String[] args) {
        new LoginForm();
    }


}
