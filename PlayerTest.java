package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player testPlayer;

    @BeforeEach
    void setUp(){
        testPlayer = new Player("John", 1);
    }

    @Test
    void testConstructor(){
        assertEquals("John", testPlayer.getPlayerName());
        assertEquals(1, testPlayer.getPlayerNumber());
    }
}
