package cz.sycha.notes.database;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import cz.sycha.notes.models.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class UsersDB {
    public static final String DB_NAME = DBConfig.getDbName();
    public static final String DB_COLLECTION = "Users";
    public static final String MONGO_HOST = DBConfig.getDbHost();
    public static final int MONGO_PORT = DBConfig.getDbPort();

    private static final String DB_USER = DBConfig.getDbUser();
    private static final String DB_PASS = DBConfig.getDbPass();

    protected MongoClient mongo;
    protected MongoOperations mongoOps;
    protected MongoCredential credential;
    protected ServerAddress server;

    public UsersDB() {
        if(DB_USER != null || DB_PASS != null) {
            credential = MongoCredential.createCredential(DB_USER, DB_NAME, DB_PASS.toCharArray());
            try {
                server = new ServerAddress(MONGO_HOST, MONGO_PORT);
                mongo = new MongoClient(server, Arrays.asList(credential));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }

        mongoOps = new MongoTemplate(mongo, DB_NAME);
    }

    public List<User> findAll() {
        List<User> users = mongoOps.findAll(User.class, DB_COLLECTION);
        return users;
    }

    public User findUserById(String id) {
        User user = mongoOps.findOne(new Query(Criteria.where("id").is(id)), User.class, DB_COLLECTION);
        return user;
    }
}
