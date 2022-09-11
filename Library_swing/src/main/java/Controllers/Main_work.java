package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main_work {

    // two arrayLists to store the books and admin users for easier use
    static public ArrayList<Admin> Admin_users = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();

    public static void start() throws SQLException, ClassNotFoundException {

        // getting admin users from database
        DBConnection dbConnection = new DBConnection();
        Admin_users = dbConnection.get_users();

        // getting books from database
        DBConnection dbConnection1 = new DBConnection();
        books = dbConnection1.retrieve_book();

    }

    // filling books arrayList
    public static void fill_Books() throws SQLException, ClassNotFoundException {
        DBConnection dbConnection1 = new DBConnection();
        books = dbConnection1.retrieve_book();
    }


}
