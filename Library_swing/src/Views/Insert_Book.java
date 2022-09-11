package Views;

import Controllers.Book;
import Controllers.DBConnection;
import Controllers.Main_work;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Objects;

public class Insert_Book extends JPanel {

    static Dimension lbl_dimension = new Dimension(100,20);
    static Dimension txt_field_dimension = new Dimension(300,30);
    static File file;

    Insert_Book(){


        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel department = new JPanel();
        department.setLayout(new BoxLayout(department,BoxLayout.X_AXIS));
        JLabel department_lbl = new JLabel("Department");
        department_lbl.setPreferredSize(lbl_dimension);
        department_lbl.setMaximumSize(lbl_dimension);
        department_lbl.setMinimumSize(lbl_dimension);

        JComboBox departments = new JComboBox();
        departments.addItem("Select Department");
        departments.addItem("Computer");
        departments.addItem("Civil");
        departments.addItem("Petroleum");
        departments.addItem("English");
        departments.addItem("Dentistry");
        departments.addItem("Pharmacy");
        departments.addItem("MLS");
        departments.addItem("Religion");
        departments.addItem("Environment");
        departments.addItem("Business");

        department.add(department_lbl);
        department.add(departments);
        department.add(Box.createHorizontalGlue());

        JPanel route = new JPanel();

        route.setLayout(new BoxLayout(route,BoxLayout.X_AXIS));
        JLabel route_lbl = new JLabel("Select Route");
        route_lbl.setPreferredSize(lbl_dimension);
        route_lbl.setMinimumSize(lbl_dimension);
        route_lbl.setMaximumSize(lbl_dimension);

        JTextField route_txt = new JTextField();
        route_txt.setPreferredSize(txt_field_dimension);
        route_txt.setMaximumSize(txt_field_dimension);
        route_txt.setMinimumSize(txt_field_dimension);

        route_txt.setEnabled(false);
        JButton select = new JButton("Select");

        route.add(route_lbl);
        route.add(route_txt);
        route.add(Box.createHorizontalStrut(20));
        route.add(select);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        JButton back = new JButton("Back");
        JButton insert = new JButton("Insert");
        JLabel warning_lbl = new JLabel();
        warning_lbl.setFont(new Font("SanSerif",Font.BOLD,20));
        warning_lbl.setForeground(Color.red);

        buttons.add(back);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(warning_lbl);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(insert);



        JPanel name_panel = new JPanel();
        name_panel.setLayout(new BoxLayout(name_panel,BoxLayout.X_AXIS));
        JLabel name_label = new JLabel("Name");
        name_label.setPreferredSize(lbl_dimension);
        JTextField name_textField = new JTextField();
        name_textField.setPreferredSize(txt_field_dimension);
        name_textField.setMaximumSize(txt_field_dimension);
        name_textField.setMinimumSize(txt_field_dimension);
        name_panel.add(name_label);
        name_panel.add(name_textField);
        name_panel.add(Box.createHorizontalGlue());

        JPanel author_panel = new JPanel();
        author_panel.setLayout(new BoxLayout(author_panel,BoxLayout.X_AXIS));
        JLabel author_label = new JLabel("Author");
        author_label.setPreferredSize(lbl_dimension);
        JTextField author_textField = new JTextField();
        author_textField.setPreferredSize(txt_field_dimension);
        author_textField.setMaximumSize(txt_field_dimension);
        author_textField.setMinimumSize(txt_field_dimension);
        author_panel.add(author_label);
        author_panel.add(author_textField);
        author_panel.add(Box.createHorizontalGlue());

        JPanel page_number_panel = new JPanel();
        page_number_panel.setLayout(new BoxLayout(page_number_panel,BoxLayout.X_AXIS));
        JLabel page_number_label = new JLabel("Page Number");
        page_number_label.setPreferredSize(lbl_dimension);
        JTextField page_number_textField = new JTextField();
        page_number_textField.setPreferredSize(txt_field_dimension);
        page_number_textField.setMaximumSize(txt_field_dimension);
        page_number_textField.setMinimumSize(txt_field_dimension);
        page_number_panel.add(page_number_label);
        page_number_panel.add(page_number_textField);
        page_number_panel.add(Box.createHorizontalGlue());

        add(name_panel);
        add(Box.createVerticalStrut(20));
        add(author_panel);
        add(Box.createVerticalStrut(20));
        add(department);
        add(Box.createVerticalStrut(20));
        add(page_number_panel);
        add(Box.createVerticalStrut(20));
        add(route);
        add(Box.createVerticalStrut(20));
        add(buttons);
        add(Box.createVerticalStrut(20));

        select.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            int result = fileChooser.showOpenDialog(Insert_Book.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                route_txt.setText(file.getPath());
            }
            fileChooser.setVisible(true);

        });

        back.addActionListener(e -> {
            Main main = Main.main;
            main.getContentPane().removeAll();
            main.add(new Login());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
            main.setLayout(new GridBagLayout());
        });

        insert.addActionListener(e -> {
            if(!(name_textField.getText().equals("") || author_textField.equals("") || Objects.requireNonNull(departments.getSelectedItem()).toString().equals("Select Department") || page_number_textField.getText().equals("") || route_txt.getText().equals(""))){
                try {
                    String pathname = "books/" + departments.getSelectedItem().toString() + "/" + file.getName();
                    File dest_file = new File(pathname);
                    Files.copy(file.toPath(),dest_file.toPath());
                    DBConnection dbConnection = new DBConnection();
                    dbConnection.insert_book(new Book(name_textField.getText(), author_textField.getText(), departments.getSelectedItem().toString(),0, Integer.parseInt(page_number_textField.getText()),pathname));
                    Main_work.fill_Books();
                    reset_fields(departments, route_txt, warning_lbl, name_textField, author_textField, page_number_textField);

                } catch (ClassNotFoundException | SQLException | IOException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }else{
                warning_lbl.setText("Please fill in all the fields");
            }


        });

    }

    private void reset_fields(JComboBox departments, JTextField route_txt, JLabel warning_lbl, JTextField name_textField, JTextField author_textField, JTextField page_number_textField) {
        warning_lbl.setText("");
        name_textField.setText("");
        author_textField.setText("");
        departments.setSelectedIndex(0);
        page_number_textField.setText("");
        route_txt.setText("");
    }

}
