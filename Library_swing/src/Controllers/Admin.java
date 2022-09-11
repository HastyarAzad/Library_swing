package Controllers;

public class Admin{

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    final private String name;
    final private String email;
    final private String address;
    final private String code;
    final private String username;
    final private String password;

    public Admin(String name, String email, String address, String code, String username, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.code = code;
        this.username = username;
        this.password = password;
    }





}
