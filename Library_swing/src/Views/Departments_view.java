package Views;

import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Departments_view extends JPanel {


    Departments_view(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel big_Title = new JLabel("Welcome to KUST Library");
        big_Title.setFont(new Font("SansSerif",Font.PLAIN,50));
        big_Title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(big_Title);
        add(Box.createVerticalStrut(20));

        ImageIcon kust_image = new ImageIcon("src/Images/newKomarLogo.png");
        Image temp_image = kust_image.getImage();
        Image new_image = temp_image.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        kust_image = new ImageIcon(new_image);
        JLabel image = new JLabel(kust_image);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(image);
        add(Box.createVerticalStrut(20));

        JLabel small_title = new JLabel("Chose Your Department");
        small_title.setFont(new Font("SansSerif",Font.PLAIN,20));
        small_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(small_title);
        add(Box.createVerticalStrut(20));

        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(new BoxLayout(buttons_panel,BoxLayout.Y_AXIS));

        JPanel top_buttons = new JPanel();

        JPanel department_buttons1 = new JPanel();
        department_buttons1.setLayout(new BoxLayout(department_buttons1,BoxLayout.Y_AXIS));
        department_buttons1.add(create_button("Computer"));
        department_buttons1.add(Box.createVerticalStrut(10));
        department_buttons1.add(create_button("MLS"));
        department_buttons1.add(Box.createVerticalStrut(10));
        department_buttons1.add(create_button("Civil"));

        JPanel department_buttons2 = new JPanel();
        department_buttons2.setLayout(new BoxLayout(department_buttons2,BoxLayout.Y_AXIS));
        department_buttons2.add(create_button("Business"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("Pharmacy"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("Environment"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("English"));

        JPanel department_buttons3 = new JPanel();
        department_buttons3.setLayout(new BoxLayout(department_buttons3,BoxLayout.Y_AXIS));
        department_buttons3.add(create_button("Religion"));
        department_buttons3.add(Box.createVerticalStrut(10));
        department_buttons3.add(create_button("Dentistry"));
        department_buttons3.add(Box.createVerticalStrut(10));
        department_buttons3.add(create_button("Petroleum"));

        top_buttons.add(department_buttons1);
        top_buttons.add(Box.createHorizontalStrut(10));
        top_buttons.add(department_buttons2);
        top_buttons.add(Box.createHorizontalStrut(10));
        top_buttons.add(department_buttons3);

        buttons_panel.add(top_buttons);

        JPanel all_button = new JPanel();
        all_button.setLayout(new GridBagLayout());

        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.setPreferredSize(new Dimension(150,50));
        back.setMinimumSize(new Dimension(150,50));
        back.setMaximumSize(new Dimension(150,50));

        back.addActionListener(e -> {
            Main main = Main.main;
            main.getContentPane().removeAll();
            main.add(new Main_Interface());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
            main.setLayout(new GridBagLayout());
        });

        all_button.add(back);
        all_button.add(Box.createHorizontalStrut(10));
        all_button.add(create_button("All"));

        buttons_panel.add(Box.createVerticalStrut(20));
        buttons_panel.add(all_button);

        add(buttons_panel);

    }

    static JButton create_button(String name){
        JButton btn = new JButton(name);
        Dimension dim = new Dimension(120,30);
        if(name.equals("All")){
            dim = new Dimension(150,50);
        }
        btn.setPreferredSize(dim);
        btn.setMinimumSize(dim);
        btn.setMaximumSize(dim);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            Main main = Main.main;
            main.getContentPane().removeAll();
            main.add(new Library_view());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
            Library_view.fill_table(btn.getText());
        });
        return btn;
    }

}
