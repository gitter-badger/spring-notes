package cz.sycha.notes;

import cz.sycha.notes.models.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class InventoryApplicationTests {
	@Test
	public void contextLoads() {
	}

	@Test
	public void databaseConnectsAndWorks() {
		MongoDB db = new MongoDB();

		// This is just a precaution in a case, there already is a record with id 1
		db.deleteTaskById(1);

		db.insertTask(new Note(1, "Test", "Test123"));
		Note note = db.findTaskById(1);

		org.junit.Assert.assertNotNull("Should not be null", note);

		List<Note> notes = db.findAll();

		org.junit.Assert.assertNotNull("Should not be null", notes);

		org.junit.Assert.assertTrue(db.deleteTaskById(1));
	}
}
