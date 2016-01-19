package cz.sycha.notes.controllers;

import cz.sycha.notes.database.NotesDB;
import cz.sycha.notes.exceptions.NoteNotFoundException;
import cz.sycha.notes.pojo_models.Note;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class NotesController {
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Note> notes = new HashMap<Long, Note>();
    private final NotesDB db;

    private final Random rand;

    public NotesController() {
        db = new NotesDB();
        rand = new Random();
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> tasks() {
        return db.findAll();
    }

    @RequestMapping(value = "/add-note", method = RequestMethod.PUT)
    public Map addTask(@RequestParam(value="title", required=true) String title,
                          @RequestParam(value="content", required=true) String content) {

        db.insertTask(new Note(rand.nextInt(9999), title, content));

        Map<String, String> response = new HashMap<String, String>();
        response.put("status", "OK");
        return response;
    }

    @RequestMapping(value = "/get-note", method = RequestMethod.GET)
    public Note getTask(@RequestParam(value = "id", required = true) long id) {
        Note note = db.findTaskById(id);

        if(note != null) {
            return note;
        }
        else {
            throw new NoteNotFoundException();
        }
    }

    @RequestMapping(value = "/delete-note", method = RequestMethod.DELETE)
    public Map deleteTask(@RequestParam(value = "id", required = true) long id) {
        Map<String, String> response = new HashMap<String, String>();

        if(db.deleteTaskById(id)) {
            response.put("status", "OK");
        }
        else {
            response.put("status", "ERROR");
            response.put("message", "You are trying to delete an non-existent note");
        }

        return response;
    }
}
