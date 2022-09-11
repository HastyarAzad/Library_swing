package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Books_Table extends JPanel {
    static DefaultTableModel model = new DefaultTableModel();
    static JTable table;

    Books_Table() {

        //setting the layout
        setLayout(new GridLayout());

        // instantiating  the table and setting it's grid color
        table = new JTable();
        table.setGridColor(Color.black);


        // create a table model and set a Column Identifiers to this model
        Object[] columns = { "Name", "Author","Department","ID","Page Number"};

        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.white);
        table.setForeground(Color.black);
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        table.setFont(font);
        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(700,300));
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setIntercellSpacing(new Dimension(20,0));

        add(pane);

    }
}