package apiCreateUser;

public class UserJson {
    private final String email;
    private final String password;
    private final String name;

    public UserJson(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
