package cz.sycha.notes.models;

import cz.sycha.notes.database.UsersDB;
import cz.sycha.notes.exceptions.NoteNotFoundException;
import cz.sycha.notes.exceptions.UserAlreadyExistsException;
import cz.sycha.notes.pojo_models.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;
import java.util.logging.Logger;

public class Authentication {
    private final UsersDB db;

    public Authentication() {
        db = new UsersDB();
    }

    /**
     * Attempts authentication and if successfull, returns the AUTH token in form of UUID
     * @return UUID Auth token
     */
    public UUID Authenticate(String username, String password) {

        User u = db.findUserByUsername(username);
        if(u == null) {
            return null;
        }
        else {
            System.out.println(password);
            System.out.println(u.getPassword());
            if(BCrypt.checkpw(password, u.getPassword())) {
                UUID token = UUID.randomUUID();
                u.setToken(token);

                db.updateUser(u);

                return token;
            }
            else {
                //return null;
                throw new NoteNotFoundException();
            }
        }
    }

    public void createUser(String username, String email, String password) {
        User u = db.findUserByUsername(username);
        if(u != null) { throw new UserAlreadyExistsException(); }

        String salt = BCrypt.gensalt(4);
        String pwdHash = BCrypt.hashpw(password, salt);

        User user = new User(username, email, pwdHash);
        db.saveUser(user);
    }
}
