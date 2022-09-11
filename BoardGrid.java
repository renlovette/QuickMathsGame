package ui;

import model.Event;
import model.EventLog;
import model.Grid;
import model.MathQuestion;
import model.Player;
import model.exception.EndGameException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class BoardGrid extends JFrame {
    JFrame gameWindow = new JFrame("Connect 4 Quick Maths");
    JFrame splashWindow = new JFrame("LOADING");
    JFrame endWindow = new JFrame("End of Game");
    JPanel boardGame = new JPanel();
    JPanel titlePanel = new JPanel();
    JButton[] gridSpaces;
    JLabel textField = new JLabel();
    ImageIcon image;
    Image img;
    JLabel splashImg;

    private static final String JSON_KEEP = "./data/lastGame.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Grid gridGame;
    private int gridDimension;
    private Player player1;
    private Player player2;
    private Boolean playerTurn;

    // EFFECT: constructs board game GUI
    // source: https://www.youtube.com/watch?v=rA7tfvpkw0I
    public BoardGrid() {
        setUpSplashScreen();
        splashWindow.setLocationRelativeTo(null);
        splashWindow.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashWindow.dispose();

        jsonReader = new JsonReader(JSON_KEEP);
        jsonWriter = new JsonWriter(JSON_KEEP);

        gameSetUp();
        setUpGrid();
        setGameWindow();
        setUpTextField();
        setUpTitlePanel();

        boardGame.setPreferredSize(new Dimension(450, 450));
        boardGame.setBackground(Color.yellow);
        boardGame.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        gameWindow.add(boardGame, BorderLayout.CENTER);

        addMenu();

        displayGameTitle();

        playGame();

    }

    // EFFECT: set up splash screen display
    // source: https://kensoftph.com/resize-an-image-in-java/
    private void setUpSplashScreen() {
        image = new ImageIcon((getClass().getResource("splashScreen1.png")));
        img = image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
        splashImg = new JLabel(image);
        splashWindow.add(splashImg);
        splashWindow.pack();
    }

    // MODIFY: this
    // EFFECT: display game text JPanel on board game GUI
    private void setUpTitlePanel() {
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 500, 150);
        titlePanel.add(textField);
        gameWindow.add(titlePanel, BorderLayout.NORTH);
    }

    // MODIFY: this
    // EFFECT: display game window JFrame on board game GUI
    private void setGameWindow() {
        gameWindow.setPreferredSize(new Dimension(600, 750));
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
    }

    // MODIFY: this
    // EFFECT: display game title on board game GUI
    private void displayGameTitle() {
        textField.setText("Connect-4-Quick-Maths");
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // MODIFY: this
    // EFFECT: setting up textField at top of JFrame window
    private void setUpTextField() {
        textField.setBackground(Color.ORANGE);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Typewriter", Font.BOLD, 40));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(true);
    }

    // EFFECTS: initialize game
    public void playGame() {
        // {}
        textField.setText(player1.getPlayerName() + "'s turn!");
        playerTurn = true;
        playersTurn();
        fullBoardChecker();

    }

    // MODIFIES: Grid
    // EFFECTS: enables player to place counter only on empty grid space board if math question is answered correctly
    //          if incorrect answer given, player's turn ends
    public void playersTurn() {
        fullBoardChecker();
        MathQuestion math = new MathQuestion();
        int userMathAnswer = Integer.parseInt(JOptionPane.showInputDialog(
                null, math.makeQuestion(), "Math Question", JOptionPane.QUESTION_MESSAGE));
        if (math.isCorrect(userMathAnswer)) {
            fullBoardChecker();
            textField.setText("Place Your Counter");
            enableAllButtons();
        } else {
            disableAllButtons();
            if (playerTurn) {
                textField.setText(player2.getPlayerName() + "'s turn!");
                playerTurn = false;
            } else {
                textField.setText(player1.getPlayerName() + "'s turn!");

            }
            playersTurn();
        }
    }

    // MODIFY: this
    // EFFECTS: set up grid of board game with JButtons
    public void setUpGrid() {
        boardGame.setLayout(new GridLayout(gridDimension, gridDimension, 0, 0));
        gridSpaces = new JButton[(gridDimension * gridDimension)];

        for (int i = 0; i < (gridDimension * gridDimension); i++) {
            gridSpacesSetUp(i);
            boardGame.add(gridSpaces[i]);
        }
        disableAllButtons();
    }

    // MODIFY: thus
    // EFFECT: sets up gridSpaces buttons
    private void gridSpacesSetUp(int i) {
        gridSpaces[i] = new JButton();
        gridSpaces[i].setFont(new Font("Typewriter", Font.BOLD, 45));
        gridSpaces[i].setBackground(Color.white);
        gridSpaces[i].addActionListener(new GridSpaceAction());
    }

    // MODIFY: this
    // EFFECTS: adds user interface for menu pop up to display
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game Menu");
        menu.setBackground(Color.ORANGE);
        menu.setMnemonic('M');

        makeSaveMenu(menu);
        makeLoadMenu(menu);
        makeLeaveMenu(menu);

        menuBar.add(menu);
        gameWindow.setJMenuBar(menuBar);
    }

    // MODIFY: this
    // EFFECTS: add leave game option to menu bar
    private void makeLeaveMenu(JMenu menu) {
        JMenuItem leaveMenu = new JMenuItem("Leave Game");
        leaveMenu.addActionListener(new ExitAction());
        leaveMenu.setBackground(Color.pink);
        menu.add(leaveMenu);
    }

    // MODIFY: this
    // EFFECTS: add load game option to menu bar
    private void makeLoadMenu(JMenu menu) {
        JMenuItem loadMenu = new JMenuItem("Load");
        loadMenu.addActionListener(new LoadAction());
        loadMenu.setBackground(Color.pink);
        menu.add(loadMenu);
    }

    // MODIFY: this
    // EFFECT: adds save game option to menu bar
    private void makeSaveMenu(JMenu menu) {
        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.addActionListener(new SaveAction());
        saveMenu.setBackground(Color.pink);
        menu.add(saveMenu);
    }


    // EFFECTS: action to be taken when the user wants to save the game
    private class SaveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent evt) {

            saveGridGame();
            textField.setText("Game saved");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playGame();
        }
    }

    // EFFECTS: action to be taken when the user wants to save the game
    private class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent evt) {
            printLog(EventLog.getInstance());
            System.exit(1);
        }
    }

    // EFFECTS: action to be taken when the user wants to load the game
    private class LoadAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent evt) {
            loadGridGame();
        }
    }

    // MODIFY: this
    // EFFECT: provides action for when grid space button is clicked, changes label of button
    private class GridSpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < (gridDimension * gridDimension); i++) {
                if (e.getSource() == gridSpaces[i]) {
                    if (!gridSpaces[i].getText().equals("O")) {
                        if (playerTurn) {
                            setPlayer1Counter(i);
                            playerTurn = false;
                            textField.setText(player2.getPlayerName() + "'s turn!");
                            gridGame.placeOnGrid("1", i);
                            fullBoardChecker();
                            playersTurn();
                        } else {
                            setPlayer2Counter(i);
                            playerTurn = true;
                            textField.setText(player1.getPlayerName() + "'s turn!");
                            gridGame.placeOnGrid("2", i);
                            fullBoardChecker();
                            playersTurn();
                        }
                    } else {
                        textField.setText("Space is taken, try again!");
                    }
                }

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if game is still valid, game invalid if board is full
    public void fullBoardChecker() {
        try {
            if (gridGame.boardFull()) {
                textField.setText("BOARD FULL");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textField.setText("END OF GAME");
                disableAllButtons();
            }
        } catch (EndGameException e) {
            textField.setText("GAME OVER");
            displayEndScreen();
            printLog(EventLog.getInstance());
        }
    }

    //MODIFY: this
    //EFFECTS: displays end screen for when game is over
    public void displayEndScreen() {
        gameWindow.dispose();
        image = new ImageIcon((getClass().getResource("endSplashScreen.png")));
        img = image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
        splashImg = new JLabel(image);
        endWindow.add(splashImg);
        endWindow.pack();
        endWindow.setLocationRelativeTo(null);
        endWindow.setVisible(true);
        endWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    //EFFECTS: set up board game dimensions and grid and identify players
    public void gameSetUp() {
        gridDimension = parseInt(JOptionPane.showInputDialog(
                null, "Grid Size", "Enter Grid Size: ", JOptionPane.QUESTION_MESSAGE));

        gridGame = new Grid(gridDimension);

        player1 = new Player((JOptionPane.showInputDialog(null, "Identify Player 1",
                "Enter player 1's name", JOptionPane.QUESTION_MESSAGE)), 1);
        player2 = new Player((JOptionPane.showInputDialog(null, "Identify Player 2",
                "Enter player 2's name", JOptionPane.QUESTION_MESSAGE)), 2);
    }


    // EFFECTS: saves the workroom to file and displays "game saved" message
    private void saveGridGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(gridGame);
            jsonWriter.close();
            System.out.println("Game saved to" + JSON_KEEP);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_KEEP);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads gridGame from file and displays "game loaded" message
    private void loadGridGame() {
        try {
            gridGame = jsonReader.read();
            gridDimension = (int) (Math.sqrt(gridGame.getGridLayout().size()));
            System.out.println("Game is loaded from " + JSON_KEEP);
            textField.setText("Game loaded");
            boardGame.removeAll();
            playLoadedGame();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_KEEP);
        }
    }


    // MODIFIES: this
    // EFFECTS: displays loaded game on GUI;
    public void playLoadedGame() {
        gameWindow.remove(boardGame);
        boardGame.setLayout(new GridLayout(gridDimension, gridDimension, 0, 0));
        gridSpaces = new JButton[(gridDimension * gridDimension)];

        for (int i = 0; i < (gridSpaces.length); i++) {
            if (gridGame.getPosition(i).equals("1")) {
                gridSpacesSetUp(i);
                setPlayer1Counter(i);
                boardGame.add(gridSpaces[i]);
            } else if (gridGame.getPosition(i).equals("2")) {
                gridSpacesSetUp(i);
                setPlayer2Counter(i);
                boardGame.add(gridSpaces[i]);
            } else {
                gridSpacesSetUp(i);
                boardGame.add(gridSpaces[i]);
            }
        }
        disableAllButtons();
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
        gameWindow.add(boardGame);
        playGame();

    }

    // EFFECTS: set-up counter for player 1
    public void setPlayer1Counter(int n) {
        gridSpaces[n].setText("O");
        gridSpaces[n].setForeground(Color.BLUE);
    }

    // EFFECTS: set-up counter for player 2
    public void setPlayer2Counter(int n) {
        gridSpaces[n].setText("O");
        gridSpaces[n].setForeground(Color.RED);
    }

    // MODIFY: this
    // EFFECTS: enable users to interact with board game to place counter
    public void enableAllButtons() {
        for (int i = 0; i < (gridDimension * gridDimension); i++) {
            gridSpaces[i].setEnabled(true);
        }
    }

    // MODIFY: this
    // EFFECTS: does not allow user to place any counter on board game
    public void disableAllButtons() {
        for (int i = 0; i < (gridDimension * gridDimension); i++) {
            gridSpaces[i].setEnabled(true);
        }
    }

    // EFFECTS: Prints the event log
    public void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }
}

