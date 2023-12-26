package login_register;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard {

        private JFrame frame;
        private JPanel titleBar;
        private JLabel titleLabel;
        private JLabel closeLabel;
        private JLabel minimizeLabel;
        private JPanel dashboardPanel;

        // dragging the form
        private boolean isDragging = false;
        private Point mouseOffset;

        public Dashboard()
        {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);

            // Set the custom rounded border to the form
            frame.getRootPane().setBorder(BorderFactory.createCompoundBorder(
                    //outsideBorder
                    new RoundedBorder(10, new Color(255,204,0)),
                    new EmptyBorder(0, 0, 0, 0)

            ));


            titleBar = new JPanel();
            titleBar.setLayout(null);
            titleBar.setBackground(Color.DARK_GRAY);
            titleBar.setPreferredSize(new Dimension(frame.getWidth(), 30));
            frame.add(titleBar, BorderLayout.NORTH);

            titleLabel = new JLabel("Dashboard");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Courier New", Font.BOLD, 16));
            titleLabel.setBounds(10, 0, 200, 30);
            titleBar.add(titleLabel);

            closeLabel = new JLabel("X");
            closeLabel.setForeground(Color.WHITE);
            closeLabel.setFont(new Font("Courier New", Font.BOLD, 16));
            closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            closeLabel.setBounds(frame.getWidth() - 50, 0, 30, 30);

            closeLabel.addMouseListener(new MouseAdapter() {
                // close the login form
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }

                // mouse hover effect
                @Override
                public void mouseEntered(MouseEvent e) {
                    closeLabel.setForeground(Color.ORANGE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    closeLabel.setForeground(Color.WHITE);
                }

            });

            titleBar.add(closeLabel);

            minimizeLabel = new JLabel("-");
            minimizeLabel.setForeground(Color.WHITE);
            minimizeLabel.setFont(new Font("Courier New", Font.BOLD, 16));
            minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            minimizeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            minimizeLabel.setBounds(frame.getWidth() - 80, 0, 30, 30);

            minimizeLabel.addMouseListener(new MouseAdapter() {
                // iconify (minimize) the login form
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setState(JFrame.ICONIFIED);
                }

                // mouse hover effect
                @Override
                public void mouseEntered(MouseEvent e) {
                    minimizeLabel.setForeground(Color.ORANGE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    minimizeLabel.setForeground(Color.WHITE);
                }

            });

            titleBar.add(minimizeLabel);





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

            frame.setVisible(true);
        }


        public static void main(String[] args) {
            new Dashboard();
        }
    }

    // Create a Custom Border class for rounded corners
    class RoundedBorder implements Border {

        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        }

        @Override
        public Insets getBorderInsets(Component c) {

            return new Insets(radius, radius, radius, radius);

        }

        @Override
        public boolean isBorderOpaque() {

            return true;

        }

}