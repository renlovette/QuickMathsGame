# Connect-4-Quick-Maths

## An implementation of a 2-player connect-4 game with a mathematical twist.

This game is aimed at young children who want to improve their mental maths abilities but would prefer to do so in a 
fun and interactive manner. Following the rules and format a regular connect-4 game, the aim of the game is for the 
user to place their corresponding counters on a square grid to make a row of 4 of their corresponding counters. For 
this specific application, the user must first correctly answer a randomly generated simple addition question in the 
at the start of their turn. If they give an incorrect answer, that user's turn will end without placing their counter 
down. It is then the next player's turn and the game continues until one of the players makes a row of 4 of their
counters.

### Why did I choose this project?

This project interests me because when I was younger, I remember spending hours practicing my mental maths using the
traditional pen and paper method. Although this method works, I felt very unmotivated to practice because it felt so 
repetitive. By creating a game that incorporates educational aspects, I hope that this application would encourage young
children to practice their math skills. Additionally, because this is a two player game, the user can play with their 
friends, adding a competitive aspect to the game. *Who doesn't love a little competition after all?*

### User Stories
* As a User, I want to be able to add a counter to the grid
* As a User, I want to be able to provide an answer the given math question
* As a User, I want to be able to view the counter placements on the grid
* As a User, I want to specify the dimensions of the grid
* As a User, I want to be able to save the current state of all the counters on the grid
* As a User, I want to be able to have the option to either start a new game or continue a previously saved game.

### Phase 4: Task 2
Sample of events that occurs when program runs:

Thu Mar 31 22:23:08 PDT 2022  
Player answered correctly  
Thu Mar 31 22:23:09 PDT 2022  
Player 1 placed down a counter  
Thu Mar 31 22:23:11 PDT 2022  
Player answered incorrectly  
Thu Mar 31 22:23:18 PDT 2022  
Player answered correctly  
Thu Mar 31 22:23:19 PDT 2022  
Player 2 placed down a counter  
Thu Mar 31 22:23:22 PDT 2022  
Player answered correctly  
Thu Mar 31 22:23:24 PDT 2022  
Player 1 placed down a counter  
Thu Mar 31 22:23:25 PDT 2022  
Player answered incorrectly  
Thu Mar 31 22:23:26 PDT 2022  
Player answered incorrectly  
Thu Mar 31 22:23:37 PDT 2022  
Player answered correctly  

Process finished with exit code 1

### Phase 4: Task 3
* refactor to reduce coupling between the BoardGame, Grid class, JsonReader class
* refactor to reduce coupling between the Grid, JsonReader, and JsonWriter class
* refactor to reduce coupling between EventLog, Grid, and BoardGrid class.


##### References
* TellerApp.java
* lab3-GamePanel.java
* coin-TestCoin.java
* JsonSerializationDemo.java
* AlarmSystem.java