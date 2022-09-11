package Views;

import javax.swing.*;
import java.awt.*;

public class Departments_view extends JPanel {

    // this is the department's view constructor
    Departments_view(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel big_Title = new JLabel("Welcome to KUST Library");// title
        big_Title.setFont(new Font("SansSerif",Font.PLAIN,50));
        big_Title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(big_Title);
        add(Box.createVerticalStrut(20));

        // kust logo
        ImageIcon kust_image = new ImageIcon("src/Images/newKomarLogo.png");
        Image temp_image = kust_image.getImage();
        Image new_image = temp_image.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        kust_image = new ImageIcon(new_image);
        JLabel image = new JLabel(kust_image);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(image);
        add(Box.createVerticalStrut(20));

        JLabel small_title = new JLabel("Chose Your Department");// second title
        small_title.setFont(new Font("SansSerif",Font.PLAIN,20));
        small_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(small_title);
        add(Box.createVerticalStrut(20));

        JPanel buttons_panel = new JPanel();// buttons panel
        buttons_panel.setLayout(new BoxLayout(buttons_panel,BoxLayout.Y_AXIS));

        JPanel top_buttons = new JPanel();// big container

        JPanel department_buttons1 = new JPanel();// first column
        department_buttons1.setLayout(new BoxLayout(department_buttons1,BoxLayout.Y_AXIS));
        department_buttons1.add(create_button("Computer"));
        department_buttons1.add(Box.createVerticalStrut(10));
        department_buttons1.add(create_button("MLS"));
        department_buttons1.add(Box.createVerticalStrut(10));
        department_buttons1.add(create_button("Civil"));

        JPanel department_buttons2 = new JPanel();// second column
        department_buttons2.setLayout(new BoxLayout(department_buttons2,BoxLayout.Y_AXIS));
        department_buttons2.add(create_button("Business"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("Pharmacy"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("Environment"));
        department_buttons2.add(Box.createVerticalStrut(10));
        department_buttons2.add(create_button("English"));

        JPanel department_buttons3 = new JPanel();// third column
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

        JPanel all_button = new JPanel();// last panel
        all_button.setLayout(new GridBagLayout());

        JButton back = new JButton("Back");// back button
        back.setFocusable(false);
        back.setPreferredSize(new Dimension(150,50));
        back.setMinimumSize(new Dimension(150,50));
        back.setMaximumSize(new Dimension(150,50));

        // adding actionListener to back button
        back.addActionListener(e -> {
            Main main = Main.main;// calling the main frame
            main.getContentPane().removeAll();// removing everything
            main.add(new Main_Interface());// adding a new View
            main.getContentPane().revalidate();// revalidating
            main.getContentPane().repaint();// repainting
            main.setLayout(new GridBagLayout());// setting main's layout
        });

        all_button.add(back);
        all_button.add(Box.createHorizontalStrut(10));
        all_button.add(create_button("All"));

        buttons_panel.add(Box.createVerticalStrut(20));
        buttons_panel.add(all_button);

        add(buttons_panel);

    }

    // we are creating all the buttons and adding actionListener to them
    static JButton create_button(String name){
        JButton btn = new JButton(name);
        Dimension dim = new Dimension(120,30);

        if(name.equals("All")){// if the text is all, change dimension
            dim = new Dimension(150,50);
        }

        // design the button
        btn.setPreferredSize(dim);
        btn.setMinimumSize(dim);
        btn.setMaximumSize(dim);
        btn.setFocusable(false);

        // add actionListener to it
        btn.addActionListener(e -> {
            Main main = Main.main;
            main.getContentPane().removeAll();
            main.add(new Library_view());// adding new Library view
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
            Library_view.fill_table(btn.getText());// filling the table according to the buttons text
        });

        return btn;
    }

}
