package cz.sycha.notes.pojo_models;

import cz.sycha.notes.database.UsersDB;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class User {
    @Id
    private final String id;

    private final String username;
    private String email;
    private String password;

    private String token = null;

    public User(String username, String email, String password) {
        UsersDB db = new UsersDB();

        // Generate password hash
        //String salt = BCrypt.gensalt(4);
        //this.password = BCrypt.hashpw(password, salt);
        this.password = password;

        // Set username and password
        this.username = username;
        this.email = email;

        // Generate UUID
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UUID getToken() { return UUID.fromString(token); }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        String salt = BCrypt.gensalt(4);
        this.password = BCrypt.hashpw(password, salt);
    }

    public void setToken(UUID token) {
        this.token = token.toString();
    }

}
