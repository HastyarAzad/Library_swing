package Views;

import javax.swing.*;
import java.awt.*;

public class Main_Interface extends JPanel {

    // this is our main interface
    // it contains a title and two buttons
    Main_Interface(){

        setSize(new Dimension(900,700));
        setLayout(new GridBagLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2)+700-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel v_box = new JPanel();// we are putting everything in a vertical box called b_box

        v_box.setLayout(new BoxLayout(v_box,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome to KUST Library");// the label
        title.setFont(new Font("Verdana", Font.PLAIN, 38));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        v_box.add(title);
        v_box.add(Box.createVerticalStrut(50));

        JPanel buttons = new JPanel();// buttons panel
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
        Dimension button_size = new Dimension(200,40);

        Font font = new Font("SansSerif",Font.PLAIN,20);
        JButton admin_btn = new JButton("Admin");
        admin_btn.setFocusable(false);
        admin_btn.setFont(font);
        admin_btn.setPreferredSize(button_size);
        admin_btn.setMaximumSize(button_size);
        admin_btn.setMinimumSize(button_size);

        JButton student_btn = new JButton("Student");
        student_btn.setFocusable(false);
        student_btn.setFont(font);
        student_btn.setMinimumSize(button_size);
        student_btn.setMaximumSize(button_size);
        student_btn.setPreferredSize(button_size);

        buttons.add(admin_btn);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(student_btn);

        // adding everything
        v_box.add(buttons);
        add(v_box);

        // adding actionListener to student button
        student_btn.addActionListener(e -> {
            Views.Main main = Views.Main.main;// calling the main frame
            main.getContentPane().removeAll();// removing everything
            main.add(new Views.Departments_view());// adding a new View
            main.getContentPane().revalidate();// revalidating
            main.getContentPane().repaint();// repainting
        });
        // adding actionListener to admin button
        admin_btn.addActionListener(e -> {
            Views.Main main = Main.main;// calling the main frame
            main.getContentPane().removeAll();// removing everything
            main.add(new Views.Login());// adding a new View
            main.getContentPane().revalidate();// revalidating
            main.getContentPane().repaint();// repainting
        });
    }
}
