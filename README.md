# Conway's Game of Life

This program calculates the next generation of Conway’s Game of Life given any initial state.

The playing field for the game of life consists of a two-dimensional grid of cells. Each cell is identified as either alive or dead.

To determine the next generation of the grid, follow these rules:

1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
2. Any live cell with more than three live neighbors dies, as if by overcrowding.
3. Any live cell with two or three live neighbors lives on to the next generation.
4. Any dead cell with exactly three live neighbors becomes a live cell.
5. A cell’s neighbors are those cells which are horizontally, vertically or diagonally adjacent.

(For more information about the "game", click [here](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).)

### How to run and test this software

After extracting the contents of the .zip file, import the directory into Eclipse.
From Eclipse, follow these prompts:

1. File > Import
2. In the Maven folder, choose Existing Maven Projects
3. Browse to the game-of-life folder (extracted from the .zip file) and click Open
4. Click Finish

To run the software:

1. In the Project Explorer, navigate to src/main/java/com/annawaltrip/GameOfLifeCLI.java
2. Right-click on this file and select Run As > Java Application. 

To test the software:

1. In the Eclipse Project Explorer, navigate to src/test/java/com/annawaltrip/GameOfLifeTest.java
2. Right-click on this file and select Run As > JUnit Test.

### My thought process

After researching the Game of Life, I first wrote a JUnit test for the simplest "grid" I could think of: a grid containing only one cell, which should die of loneliness. For the grid, I decided to use a 2-D array of integers with 3 rows and 3 columns. I created a `GameOfLife` class, and created one method called "life" (later renamed to "play") which would calculate the next generation of the grid. 

I developed the "play" method by writing further tests for commonly-known, simple patterns and their evolutions. Initially, the method could only handle a 3x3 grid. Driven by testing, I expanded the functionality to handle a 6x8 grid, then to handle a grid of any size. I made the method static, as I did not find it necessary to create an instance of the `GameOfLife` class when running or testing the program.

Next, I added a method to print the initial grid and its next generation to the console, utilizing the "play" method.

Finally, I added a "main" method to accept a user-input grid, and perform the printing method. The user specifies how many rows and columns they would like the grid to be, then inputs each row. The program then displays the grid from the user-input rows, calculates the next generation, and displays that next generation.

I added a `GameOfLifeCLI` class and refactored the code so that this class handles the main method and printing method - i.e. any methods that deal with the console.

### What I could have done differently

The data structure I chose for the grid was a 2-D integer array (`int[][]`). I realized that I could have also used a 1-D `String[]` array. This may have made it simpler to handle user input and print the grids to the console.

There were some other class structures I could imagine, such as having a `Row` class and/or a `Cell` class.

I think an additional feature of my program that would be nice is letting the user choose NOT to input a grid, and instead use a randomly generated grid, or a pre-programmed grid with a common pattern.

