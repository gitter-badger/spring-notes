package cz.sycha.notes;

import com.mongodb.MongoClient;
import cz.sycha.notes.models.Note;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;
import java.util.List;

public class MongoDB {
    public static final String DB_NAME = "notes";
    public static final String TASK_COLLECTION = "Note";
    public static final String MONGO_HOST = System.getenv("SPRING_TASK_DB_HOST");
    //public static final String MONGO_HOST = "99.99.99.9";
    public static final int MONGO_PORT = 27017;

    protected MongoClient mongo;
    protected MongoOperations mongoOps;

    /**
     * Constructor initiates the database connection
     */
    public MongoDB() {
        try {
            mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        mongoOps = new MongoTemplate(mongo, DB_NAME);
    }

    /**
     * Insert note into database
     * @param note instance of the note to be inserted
     */
    public void insertTask(Note note) {
        mongoOps.insert(note, TASK_COLLECTION);
    }

    /**
     * Find a single note by ID
     * @param id of the note to be found
     * @return Note note
     */
    public Note findTaskById(double id) {
        Note note = mongoOps.findOne(new Query(Criteria.where("id").is(id)), Note.class, TASK_COLLECTION);
        return note;
    }

    /**
     * Deletes a note by ID
     * @param id of the note to be deleted
     * @return boolean
     */
    public boolean deleteTaskById(double id) {
        Note note = mongoOps.findOne(new Query(Criteria.where("id").is(id)), Note.class, TASK_COLLECTION);
        if(note != null) {
            mongoOps.findAndRemove(new Query(Criteria.where("id").is(id)), Note.class, TASK_COLLECTION);
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
        List<Note> note = mongoOps.findAll(Note.class, TASK_COLLECTION);
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
