package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathQuestionTest {
    private MathQuestion testMathQuestion;

    @BeforeEach
    void setTestMathQuestion() {
        testMathQuestion = new MathQuestion();
    }

    @Test
    void testIntegerRandomness() {
        int lastNumber = 0;
        int longestNumOfSameAnswer = 0;
        int currentNumOfSameAnswer = 0;

        for (int i = 0; i < 10; i++) {
            if (testMathQuestion.getIntOne() == lastNumber) {
                currentNumOfSameAnswer++;
                if (currentNumOfSameAnswer > longestNumOfSameAnswer) {
                    longestNumOfSameAnswer = currentNumOfSameAnswer;
                }
            } else {
                lastNumber = testMathQuestion.getIntOne();
                currentNumOfSameAnswer = 0;
            }
            assertTrue(longestNumOfSameAnswer < 10);
        }

        for (int i = 0; i < 10; i++) {
            if (testMathQuestion.getIntTwo() == lastNumber) {
                currentNumOfSameAnswer++;
                if (currentNumOfSameAnswer > longestNumOfSameAnswer) {
                    longestNumOfSameAnswer = currentNumOfSameAnswer;
                }
            } else {
                lastNumber = testMathQuestion.getIntTwo();
                currentNumOfSameAnswer = 0;
            }
            assertTrue(longestNumOfSameAnswer < 10);
        }
    }


    @Test
    void testMakeMathQuestion() {
        assertEquals(testMathQuestion.getIntOne() + "+" + testMathQuestion.getIntTwo(),
                testMathQuestion.makeQuestion());
    }

    @Test
    void testCalculateAnswer() {
        assertEquals(testMathQuestion.getIntOne() + testMathQuestion.getIntTwo(),
                testMathQuestion.calculateAnswer());
    }

    @Test
    void testIsNotCorrect() {
        int answer = 999;
        assertFalse(testMathQuestion.isCorrect(answer));
    }

    @Test
    void testIsCorrect() {
        int correctAnswer = testMathQuestion.getIntOne() + testMathQuestion.getIntTwo();
        assertTrue(testMathQuestion.isCorrect(correctAnswer));
    }


}
