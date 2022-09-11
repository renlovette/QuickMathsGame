package model;

import java.util.Random;

public class MathQuestion {

    private final int integerOne;
    private final int integerTwo;

    Random rand = new Random();

    //EFFECTS: randomly selects two integers less than 99 for math question
    public MathQuestion() {
        this.integerOne = rand.nextInt(99);
        this.integerTwo = rand.nextInt(99);

    }

    //EFFECTS: creates math question for user to answer
    public String makeQuestion() {
        return (getIntOne() + "+" + getIntTwo());
    }

    //EFFECTS: produce correct answer of the math question
    public int calculateAnswer() {
        return (this.integerOne + this.integerTwo);
    }

    //EFFECTS: checks if user answer is correct
    public boolean isCorrect(int answer) {
        if (answer == calculateAnswer()) {
            EventLog.getInstance().logEvent(new Event("Player answered correctly"));
        } else {
            EventLog.getInstance().logEvent(new Event("Player answered incorrectly"));
        }
        return (answer == calculateAnswer());
    }

    public int getIntOne() {
        return this.integerOne;
    }

    public int getIntTwo() {
        return this.integerTwo;
    }

}
