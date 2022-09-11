package Views;

import Controllers.Admin;
import Controllers.Main_work;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Login extends JPanel {

    // this is the login constructor
    Login() {
        Main main = Main.main;// getting the main frame
        main.getContentPane().setLayout(new GridBagLayout());// setting it's layout

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension uniform_dimension = new Dimension(100, 30);

        // KUST logo
        ImageIcon kust_logo = new ImageIcon("src/main/java/Images/NewKomarLogo.png");
        Image temp_image = kust_logo.getImage();
        Image new_image = temp_image.getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        kust_logo = new ImageIcon(new_image);
        JLabel image = new JLabel(kust_logo);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(image);
        add(Box.createVerticalStrut(50));

        JPanel user_name_panel = new JPanel();// username panel
        JLabel user_name_lbl = new JLabel("Username: ");
        user_name_lbl.setPreferredSize(uniform_dimension);
        JTextField user_name_txtField = new JTextField();
        user_name_txtField.setPreferredSize(uniform_dimension);
        user_name_panel.add(user_name_lbl);
        user_name_panel.add(user_name_txtField);


        add(user_name_panel);

        JPanel password_panel = new JPanel();// password panel
        JLabel password_lbl = new JLabel("Password: ");
        password_lbl.setPreferredSize(uniform_dimension);
        JPasswordField password_txtField = new JPasswordField();
        password_txtField.setPreferredSize(uniform_dimension);
        password_panel.add(password_lbl);
        password_panel.add(password_txtField);

        add(password_panel);
        add(Box.createVerticalStrut(10));


        JPanel warning_panel = new JPanel();// warning label
        JLabel warning_lbl = new JLabel();
        warning_lbl.setFont(new Font("SanSerif", Font.BOLD, 12));
        warning_lbl.setForeground(Color.red);
        warning_panel.add(warning_lbl);

        add(warning_panel);
        add(Box.createVerticalStrut(10));

        JPanel button_panel = new JPanel();// buttons panel for back and login

        JButton back_btn = new JButton("back");
        back_btn.setPreferredSize(uniform_dimension);

        JButton login_btn = new JButton("Login");
        login_btn.setPreferredSize(uniform_dimension);

        button_panel.add(back_btn);
        button_panel.add(Box.createHorizontalGlue());
        button_panel.add(login_btn);

        // adding action listener to the login button
        login_btn.addActionListener(e -> {
            validate_login(user_name_txtField, password_txtField, warning_lbl);// calling validate login and passing parameters
        });

        // adding a key listener to both textFields so that we can login using enter button
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validate_login(user_name_txtField, password_txtField, warning_lbl);
                }
            }
        };

        user_name_txtField.addKeyListener(keyListener);
        password_txtField.addKeyListener(keyListener);


        // adding actionListener to back button
        back_btn.addActionListener(e -> {
            main.getContentPane().removeAll();// removing everything
            main.add(new Main_Interface());// adding a new View
            main.getContentPane().revalidate();// revalidating
            main.getContentPane().repaint();// repainting
            main.setLayout(new GridBagLayout());
        });

        add(button_panel);

    }

    // here we are validating our login
    private void validate_login(JTextField user_name_txtField, JPasswordField password_txtField, JLabel warning_lbl) {

        // getting both texts
        String username = user_name_txtField.getText().toLowerCase();
        String password = new String(password_txtField.getPassword());
        ArrayList<Admin> admin_users = Main_work.Admin_users;// getting all the users

        // looping through users and checking for a match
        for (Admin admin : admin_users) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                login_successful(new Insert_Book());
                warning_lbl.setText("");
            } else if (username.equals("") || password.equals("")) {// checking if fields are empty
                warning_lbl.setText("Please enter both username and password");
            } else {// checking if there is a wrong combination
                warning_lbl.setText("Wrong Combination!!!");
            }
        }
    }

    void login_successful(JPanel view) {
        Main main = Main.main;// calling the main frame
        main.getContentPane().removeAll();// removing everything
        main.add(view);// adding a new view
        main.getContentPane().revalidate();// revalidating
        main.getContentPane().repaint();// repainting

    }
}
