package cz.sycha.notes.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import cz.sycha.notes.models.Note;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class NotesDB {
    public static final String DB_NAME = DBConfig.getDbName();
    public static final String DB_COLLECTION = DBConfig.getDbCollection();
    public static final String MONGO_HOST = DBConfig.getDbHost();
    public static final int MONGO_PORT = DBConfig.getDbPort();

    private static final String DB_USER = DBConfig.getDbUser();
    private static final String DB_PASS = DBConfig.getDbPass();

    protected MongoClient mongo;
    protected MongoOperations mongoOps;
    protected MongoCredential credential;
    protected ServerAddress server;

    /**
     * Constructor initiates the database connection
     */
    public NotesDB() {
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

    /**
     * Insert note into database
     * @param note instance of the note to be inserted
     */
    public void insertTask(Note note) {
        mongoOps.insert(note, DB_COLLECTION);
    }

    /**
     * Find a single note by ID
     * @param id of the note to be found
     * @return Note note
     */
    public Note findTaskById(double id) {
        Note note = mongoOps.findOne(new Query(Criteria.where("id").is(id)), Note.class, DB_COLLECTION);
        return note;
    }

    /**
     * Deletes a note by ID
     * @param id of the note to be deleted
     * @return boolean
     */
    public boolean deleteTaskById(double id) {
        Note note = mongoOps.findOne(new Query(Criteria.where("id").is(id)), Note.class, DB_COLLECTION);
        if(note != null) {
            mongoOps.findAndRemove(new Query(Criteria.where("id").is(id)), Note.class, DB_COLLECTION);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns all tasks in database
     * @return List<Note>
     */
    public List<Note> findAll() {
        List<Note> note = mongoOps.findAll(Note.class, DB_COLLECTION);
        return note;
    }

    /**
     * Safety destructor
     */
    @Override
    public void finalize() {
        mongo.close();
    }
}
