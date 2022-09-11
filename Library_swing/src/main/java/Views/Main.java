package Views;

import Controllers.Main_work;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main extends JFrame {

    // this is our mainFrame
    // we will use this to reset it's components and add new views without having to close the JFrame
    static Main main = new Main();

    // the constructor
    Main(){
        setSize(new Dimension(900,700));
        setLayout(new GridBagLayout());
        setTitle("KUST Library");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2)-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        add(new Main_Interface());// adding Main_Interface

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main_work.start();// starting the Main_work to get all the admin users and books
    }
}
