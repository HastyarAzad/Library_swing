package Views;

import Controllers.DBConnection;
import Controllers.Main_work;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main extends JFrame {

    static Main main = new Main();

    Main(){
        setSize(new Dimension(900,700));
        setLayout(new GridBagLayout());
        setTitle("KUST Library");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2)-this.getSize().width/2, dim.height/2-this.getSize().height/2);

//        Image img = Toolkit.getDefaultToolkit().getImage("NewKomarLogo.png");
//        this.setContentPane(new JPanel() {
//            @Override
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(img, 0, 0, null);
//            }
//        });
//        revalidate();
//        repaint();
//

        add(new Main_Interface());

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main_work.start();
        new DBConnection();
    }
}
