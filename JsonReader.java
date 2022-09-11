package persistence;

import model.Grid;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: reads grid from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Grid read() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGrid(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parse grid from JSON object & return grid
    private Grid parseGrid(JSONObject jsonObject) {
        int gridSize = (int) Math.sqrt(jsonObject.getInt("gridSize"));
        Grid grid = new Grid(gridSize);
        for (int i = 0; i < gridSize; i++) {
            placeString(jsonObject, i, grid);
        }
        return grid;
    }


    // EFFECTS: upload loaded game; put existing counters on game board
    private void placeString(JSONObject jsonObject, int position, Grid grid) {
        JSONArray jsonArray = jsonObject.getJSONArray("gameBoard");
        grid.placeOnGrid(jsonArray.getString(position), position);
    }


}
