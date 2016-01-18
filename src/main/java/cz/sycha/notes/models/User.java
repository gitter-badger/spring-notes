package cz.sycha.notes.models;

import cz.sycha.notes.database.UsersDB;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class User {
    @Id
    private final long id;

    private final String username;
    private final String email;
    private final String password;

    public User(String username, String email, String password) {
        UsersDB db = new UsersDB();

        String salt = BCrypt.gensalt(4);
        this.password = BCrypt.hashpw(password, salt);

        this.username = username;
        this.email = email;

        Date d = new Date();
        Timestamp time = new Timestamp(d.getTime());

        Random rand = new Random(time.getNanos());
        Random rand2 = new Random();

        long id = Math.abs(rand.nextLong() * rand2.nextLong());

        this.id = id;
    }

    public long getId() {
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
}
