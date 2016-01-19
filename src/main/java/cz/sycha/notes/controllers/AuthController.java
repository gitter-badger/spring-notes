package cz.sycha.notes.controllers;

import cz.sycha.notes.exceptions.WrongCredentialsException;
import cz.sycha.notes.models.Authentication;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthController {
    private Authentication auth = new Authentication();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String password) {
        Map<String, String> response = new HashMap<String, String>();

        UUID token = auth.Authenticate(username, password);

        if(token == null) {
            throw new WrongCredentialsException();
        }
        else {
            response.put("status", "success");
            response.put("username", username);
            response.put("token", token.toString());
        }
        return response;
    }

    @RequestMapping("/checkhash")
    public boolean checkhash(@RequestParam(value = "password", required = true) String password,
                                     @RequestParam(value = "hash", required = true) String hash) {
        return BCrypt.checkpw(password, hash);
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.PUT)
    public Map<String, String> createUser(@RequestParam(value = "username", required = true) String username,
                                          @RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "password", required = true) String password) {

        auth.createUser(username, email, password);

        Map<String, String> response = new HashMap<String, String>();

        response.put("status", "success");
        response.put("username", username);
        response.put("email", email);

        return response;
    }
}
