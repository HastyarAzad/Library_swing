package Views;

import javax.swing.*;
import java.awt.*;

public class Main_Interface extends JPanel {



    Main_Interface(){

        setSize(new Dimension(900,700));
        setLayout(new GridBagLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2)+700-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel h_box = new JPanel();
//        h_box.setBorder(BorderFactory.createLineBorder(Color.black));
        h_box.setLayout(new BoxLayout(h_box,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome to KUST Library");
        title.setFont(new Font("Verdana", Font.PLAIN, 38));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        h_box.add(title);
        h_box.add(Box.createVerticalStrut(50));

        JPanel buttons = new JPanel();
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

        h_box.add(buttons);
        add(h_box);

        student_btn.addActionListener(e -> {
            Views.Main main = Views.Main.main;
            main.getContentPane().removeAll();
            main.add(new Views.Departments_view());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
        });

        admin_btn.addActionListener(e -> {
            Views.Main main = Main.main;
            main.getContentPane().removeAll();
            main.add(new Views.Login());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
        });
    }
}
