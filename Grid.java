package model;

import model.exception.EndGameException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<String> gridLayout;
    private boolean boardStatus;

    // REQUIRES: n >= 4
    // EFFECTS: creates a grid of dimension nxn as specified by user of elements "" for each grid position
    public Grid(int n) {
        gridLayout = new ArrayList<>();
        for (int i = 0; i < (n * n); i++) {
            gridLayout.add(" ");
        }
    }

    // EFFECTS: checks if given position of grid is empty, true if empty, false otherwise
    public boolean empty(int x) {
        return (gridLayout.get(x).equals(" "));
    }

    // REQUIRES: x and y is in range [0,n]
    // MODIFY: this
    // EFFECTS: adds counter at specified position on grid
    public void placeOnGrid(String counterType, int x) {
        gridLayout.set(x, counterType);
        EventLog.getInstance().logEvent(new Event("Player " + counterType + " placed down a counter"));
    }

    // EFFECTS: checks if all places on grid have been filled by counter
    public boolean boardFull() throws EndGameException {
        int fullCount = 0;
        for (int i = 0; i < (gridLayout.size()); i++) {
            if (!(gridLayout.get(i).equals(" "))) {
                fullCount = fullCount + 1;
            }
        }
        if (fullCount >= (gridLayout.size())) {
            boardStatus = true;
            throw new EndGameException("BOARD FULL");
        } else if (fullCount < (gridLayout.size())) {
            boardStatus = false;
        }
        return boardStatus;
    }

    public String getPosition(int x) {
        return gridLayout.get(x);
    }

    public List<String> getGridLayout() {
        return gridLayout;
    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gameBoard", gridSpacesToJson());
        json.put("gridSize", gridLayout.size());
        return json;
    }

    // EFFECTS: returns each space into a JSON array.
    public JSONArray gridSpacesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < gridLayout.size(); i++) {
            jsonArray.put((gridLayout.get(i)));
        }
        return jsonArray;
    }

}
