package persistence;

import model.Grid;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Grid g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

//    @Test
//    public void testReaderEmpty() {
//        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
//        try {
//            Grid g = reader.read();
//            assertEquals(0, g.getGridLayout().size());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

    @Test
    public void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            Grid g = reader.read();
            assertEquals(1, g.getGridLayout().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
