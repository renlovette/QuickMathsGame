package persistence;

import model.Grid;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Grid g = new Grid(3);
            JsonWriter writer = new JsonWriter("./data/my\0invalideName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralGrid() {
        try {
            Grid g = new Grid(3);
            g.placeOnGrid("1",0);
            g.placeOnGrid("0", 1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGrid.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGrid.json");
            g = reader.read();
            assertEquals(9, g.getGridLayout().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
