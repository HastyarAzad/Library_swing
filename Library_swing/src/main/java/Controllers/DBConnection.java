package Controllers;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection{

    // our database details
    String url = "jdbc:mysql://localhost:3306/library_swing_application_programming";
    String username = "hastyar";
    String password = "Hastyar123";
    Connection conn;// a connection object for every instance of this class

    public DBConnection() throws ClassNotFoundException, SQLException {
        // loading the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // making the connection
        conn = DriverManager.getConnection(url,username,password);

    }

    // getting all the users
    public ArrayList<Admin> get_users() throws SQLException {

        String query = "select * from users";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        ArrayList<Admin> users = new ArrayList<>();

        while(resultSet.next()){
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String code = resultSet.getString("code");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            users.add(new Admin(name,email,address,code,username,password));
        }
        conn.close();
        return users;
    }

    // inserting a book object to the books table in the database
    public void insert_book(Book book) throws SQLException {

        String query="insert into books (id, name, author, department, page_number, route)values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, null);
        ps.setString(2, book.getName());
        ps.setString(3, book.getAuthor());
        ps.setString(4, book.getDepartment());
        ps.setInt(5, book.getPage_number());
        ps.setString(6, book.getRoute());
        int i = ps.executeUpdate();
        System.out.println("no. of rows updated ="+i);
        ps.close();
        conn.close();

    }

    // retrieving all the books from the database
    public ArrayList<Book> retrieve_book() throws SQLException {
        String query = "select * from books";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        ArrayList<Book> books = new ArrayList<>();

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            String department = resultSet.getString("department");
            int page_number = resultSet.getInt("page_number");
            String route = resultSet.getString("route");
            books.add(new Book(name,author,department,id,page_number,route));
        }
        conn.close();
        return books;
    }

    // getting the route of a book from the database using it's id
    public String retrieve_route(String id) throws SQLException {
        String query = "select route from books where (id = '" + id + "');";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        resultSet.next();
        return resultSet.getString("route");
    }

}
