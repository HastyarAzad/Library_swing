package Views;

import Controllers.Book;
import Controllers.DBConnection;
import Controllers.Main_work;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Locale;

public class Library_view extends JPanel {

    static String current_department;

    public Library_view() {
        Main main = Main.main;
        main.setLayout(new FlowLayout());

        setAlignmentY(TOP_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel top_pictures = new JPanel();
        top_pictures.setLayout(new BoxLayout(top_pictures, BoxLayout.X_AXIS));

        ImageIcon the_library_image = new ImageIcon("src/Images/theLibrary.png");
        Image temp_image = the_library_image.getImage();
        Image new_image = temp_image.getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        the_library_image = new ImageIcon(new_image);
        JLabel image = new JLabel(the_library_image);

        ImageIcon kust_image = new ImageIcon("src/Images/newKomarLogo.png");
        Image temp_image2 = kust_image.getImage();
        Image new_image2 = temp_image2.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        kust_image = new ImageIcon(new_image2);
        JLabel image2 = new JLabel(kust_image);

        top_pictures.add(image);
//        top_pictures.add(Box.createHorizontalStrut(100));
        top_pictures.add(image2);

        add(top_pictures);
        add(Box.createVerticalStrut(20));

        JPanel search_panel = new JPanel();
        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.X_AXIS));

        Dimension dimension = new Dimension(150, 28);

        JTextField name_txt_field = new JTextField();
        name_txt_field.setMaximumSize(dimension);
        name_txt_field.setPreferredSize(dimension);
        name_txt_field.setMinimumSize(dimension);

        JTextField author_txt_field = new JTextField();
        author_txt_field.setMaximumSize(dimension);
        author_txt_field.setPreferredSize(dimension);
        author_txt_field.setMinimumSize(dimension);

        JButton search = new JButton("Search");

        JButton reset = new JButton("Reset");

        JLabel name_lbl = new JLabel("Name: ");
        JLabel author_lbl = new JLabel("Author: ");

        search_panel.add(name_lbl);
        search_panel.add(name_txt_field);
        search_panel.add(Box.createHorizontalGlue());
        search_panel.add(author_lbl);
        search_panel.add(author_txt_field);
        search_panel.add(Box.createHorizontalGlue());
        search_panel.add(search);
        search_panel.add(Box.createHorizontalGlue());
        search_panel.add(reset);

        add(search_panel);

        add(Box.createVerticalStrut(20));
        add(new Books_Table());
        add(Box.createVerticalStrut(20));

        JPanel bottom_buttons = new JPanel();
        bottom_buttons.setLayout(new BoxLayout(bottom_buttons, BoxLayout.X_AXIS));

        JButton back = new JButton("Back");
        JButton download = new JButton("Download");
        JButton view = new JButton("View");

        search.addActionListener(e ->{

            String book_name = name_txt_field.getText().toLowerCase().replaceAll("\\s", "").trim();
            String book_author = author_txt_field.getText().toLowerCase().replaceAll("\\s", "").trim();

            if (book_author.equals("") && book_name.equals("")){
                Books_Table.model.setNumRows(0);
                fill_table(current_department);
                return;
            }

            if (book_name.equals("")){
                book_name = "asldkfja;sdlfjas;dlfjkasdlk;fj;sdlakjfsd;lkfj;asklj";
            }

            if (book_author.equals("")){
                book_author = "a;sldkjf;alskdjfl;askdjf;alsdkjfl;asdkjf;lsdkajf;lsdkaj";
            }



            Books_Table.model.setNumRows(0);

            for (Book book : Main_work.books) {

                String name = book.getName().toLowerCase().replaceAll("\\s", "").trim();
                String author = book.getAuthor().toLowerCase().replaceAll("\\s", "").trim();

                if(name.contains(book_name) || author.contains(book_author)){
                    add_to_table(book);
                }

            }

        });

        reset.addActionListener(e -> {

            Books_Table.model.setNumRows(0);
            fill_table(current_department);

        });

        back.addActionListener(e -> {
            main.getContentPane().removeAll();
            main.add(new Departments_view());
            main.getContentPane().revalidate();
            main.getContentPane().repaint();
            main.setLayout(new GridBagLayout());
        });

        view.addActionListener(e -> {
            JTable table = Books_Table.table;
            if(table.getSelectedRow() != -1){
                try {
                    DBConnection dbConnection = new DBConnection();
                    String route = dbConnection.retrieve_route(table.getValueAt(table.getSelectedRow(), 3).toString());
                    File file = new File(route);

                    if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                    {
                        System.out.println("not supported");
                        return;
                    }

                    Desktop desktop = Desktop.getDesktop();
                    if(file.exists())         //checks file exists or not
                        desktop.open(file);              //opens the specified file


                } catch (ClassNotFoundException | SQLException | IOException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }


        });

        download.addActionListener(e -> {
            JTable table = Books_Table.table;
            if(table.getSelectedRow() != -1){
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choosertitle");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    DBConnection dbConnection;
                    File file;

                    try {
                        dbConnection = new DBConnection();
                        String route = dbConnection.retrieve_route(table.getValueAt(table.getSelectedRow(), 3).toString());
                        file = new File(route);
                        File dest_file = new File(String.valueOf(chooser.getSelectedFile()) + "\\" + file.getName());
                        Files.copy(file.toPath(),dest_file.toPath());

                    } catch (ClassNotFoundException | SQLException | IOException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                } else {
                    System.out.println("No Selection ");
                }
            }

        });

        bottom_buttons.add(back);
        bottom_buttons.add(Box.createHorizontalGlue());
        bottom_buttons.add(download);
        bottom_buttons.add(Box.createHorizontalStrut(10));
        bottom_buttons.add(view);

        add(bottom_buttons);

    }

    static void fill_table(String department) {
        current_department = department;
        Books_Table.model.setNumRows(0);
        if (department.equals("All")) {
            for (Book book : Main_work.books) {
                add_to_table(book);
            }
        } else {
            for (Book book : Main_work.books) {
                if (book.getDepartment().equals(department)) {
                    add_to_table(book);
                }
            }
        }
    }

    private static void add_to_table(Book book) {
        Object[] row = new Object[5];
        row[0] = book.getName();
        row[1] = book.getAuthor();
        row[2] = book.getDepartment();
        row[3] = book.getId();
        row[4] = book.getPage_number();
        Books_Table.model.addRow(row);
    }
}
