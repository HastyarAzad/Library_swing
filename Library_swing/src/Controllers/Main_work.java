package Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main_work {

    static public ArrayList<Admin> Admin_users = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();

    public static void start() throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = new DBConnection();
        Admin_users = dbConnection.insert_users();

        DBConnection dbConnection1 = new DBConnection();
        books = dbConnection1.retrieve_book();

//        Admin bamo = new Admin("Bamo nadir","bamo.nadir@komar.edu.iq","iraq/slemani/rzgari","f10001","", "");
//        Admin_users.add(bamo);
//
//        Book java = new Book("Java Development","Jaza abdullah","Computer",123123,5000);
//        Book data_structure = new Book("Data Structures and Algorithms","Jaza abdullah","Computer",324242,900);
//        Book artificial_intelligence= new Book("AI Algorithms","Jaza abdullah","Computer",345344,1232);
//        books.add(java);
//        books.add(data_structure);
//        books.add(artificial_intelligence);


    }

    public static void fill_Books() throws SQLException, ClassNotFoundException {
        DBConnection dbConnection1 = new DBConnection();
        books = dbConnection1.retrieve_book();
    }


}
