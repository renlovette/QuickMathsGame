package model;

import model.exception.EndGameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    private Grid testGrid;

    @BeforeEach
    void setUp(){
        testGrid = new Grid(3);
    }

    @Test
    void testConstructor(){
        assertEquals(9, testGrid.getGridLayout().size());
    }

    @Test
    void testPlaceOnGrid(){
        testGrid.placeOnGrid("A", 2);
        assertEquals("A", testGrid.getPosition(2));

    }

    @Test
    void testBoardIsFull() {
        testGrid.placeOnGrid("A", 0);
        testGrid.placeOnGrid("A", 1);
        testGrid.placeOnGrid("A", 2);
        testGrid.placeOnGrid("A", 3);
        testGrid.placeOnGrid("A", 4);
        testGrid.placeOnGrid("A", 5);
        testGrid.placeOnGrid("A", 6);
        testGrid.placeOnGrid("A", 7);
        testGrid.placeOnGrid("A", 8);

        try {
            testGrid.boardFull();
        } catch (EndGameException e) {
        }
    }

    @Test
    void testBoardNotFull() {
        testGrid.placeOnGrid("A", 0);
        try {
            testGrid.boardFull();
        } catch (EndGameException e) {
          fail("not meant to catch exception");
        }

        testGrid.placeOnGrid("A", 1);
        testGrid.placeOnGrid("A", 2);
        try {
            testGrid.boardFull();
        } catch (EndGameException e) {
            fail("not meant to catch exception");
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(testGrid.empty(0));
        assertTrue(testGrid.empty(1));
        assertTrue(testGrid.empty(2));

        testGrid.placeOnGrid("A",0);
        assertFalse(testGrid.empty(0));

        testGrid.placeOnGrid("B", 2);
        assertFalse(testGrid.empty(0));
        assertFalse(testGrid.empty(2));
    }
}