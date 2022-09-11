package Controllers;

public class Book{

    // some properties of the book object
    String name;
    String author;
    String department;
    String route;
    int id;
    int page_number;

    // some getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    // the constructor of the book object
    public Book(String name, String author, String department, int id, int page_number, String route) {
        this.name = name;
        this.author = author;
        this.department = department;
        this.id = id;
        this.page_number = page_number;
        this.route = route;
    }
}
