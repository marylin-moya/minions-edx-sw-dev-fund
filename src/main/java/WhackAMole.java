import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class WhackAMole {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;

    WhackAMole(int numAttempts, int gridDimension) {
        this.moleGrid = new char[gridDimension][gridDimension];
        this.attemptsLeft = numAttempts;

        for (char[] row : this.moleGrid)
            Arrays.fill(row, '*');
    }

    boolean place(int x, int y) {
        if (this.moleGrid[x][y] != 'M') {
            this.moleGrid[x][y] = 'M';
            this.molesLeft++;
            return true;
        }
        return false;
    }

    void whack(int x, int y) {
        if (this.moleGrid[x][y] == 'M') {
            this.moleGrid[x][y] = 'W';
            this.score++;
            this.molesLeft--;
        }
        this.attemptsLeft--;
    }

    void printGridToUser() {
        for (int i = 0; i < this.moleGrid.length; i++) {
            for (int j = 0; j < this.moleGrid.length; j++) {
                if (this.moleGrid[i][j] == 'M') {
                    System.out.print("*  ");
                } else {
                    System.out.print(this.moleGrid[i][j] + "  ");
                }
            }
            System.out.println("");
        }
    }

    void printGrid() {
        for (int i = 0; i < this.moleGrid.length; i++) {
            for (int j = 0; j < this.moleGrid.length; j++) {
                System.out.print(this.moleGrid[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int numberOfMoles = 10;
        int numAttemps = 50;
        int gridLenght = 10;

        //create a 10 by 10 grid
        WhackAMole whackAMole = new WhackAMole(numAttemps, gridLenght);

        //Place randomly a total of 10 moles
        for (int i = 0; i < numberOfMoles; i++) {
            Random rand = new Random();
            int x = rand.nextInt(numberOfMoles);
            int y = rand.nextInt(numberOfMoles);
            if (!whackAMole.place(x, y)) {
                System.out.println("There is already a mole at " + x + "," + y + ". Trying again!");
                i--;
            } else {
                System.out.println("Mole places at " + x + "," + y);
            }
        }

        //Try to whack 50 times the moles given the x and y
        //Finish the game if we enter xAxis -1 and yAxis -1 printing the grid
        //The game ends if the user is able to uncover all the moles or if the user runs out of attempts.

        System.out.println("----------------------------");
        whackAMole.printGrid();

        System.out.println("-----You have a maximum of " + numAttemps + " attempts to get all the moles wacked-----");
        int xAxis;
        int yAxis;
        Scanner xScanner;
        Scanner yScanner;
        for (int i = numAttemps; i > 0; i++) {
            xScanner = new Scanner(System.in);
            System.out.print("Enter the x coordinates: ");
            xAxis = xScanner.nextInt();

            yScanner = new Scanner(System.in);
            System.out.print("Enter the y coordinates: ");
            yAxis = yScanner.nextInt();

            if (xAxis == -1 || yAxis == -1) {
                System.out.println("Giving up!!");
                whackAMole.printGrid();
                break;
            } else {
                System.out.println("State of Game");
                whackAMole.whack(xAxis, yAxis);
                whackAMole.printGridToUser();
            }

            if (whackAMole.molesLeft == 0) {
                System.out.println("All moles were whacked with score: " + whackAMole.score);
                break;
            }

            if (whackAMole.attemptsLeft == 0) {
                System.out.println("There are no more attemps with score: " + whackAMole.score);
                break;
            }
        }
    }
}
