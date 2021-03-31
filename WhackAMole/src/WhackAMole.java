
import java.lang.Math;
import java.util.*;

public class WhackAMole {
	int score, molesLeft, attemptsLeft;
	char[][] moleGrid;

	public WhackAMole(int numAttempts, int gridDimensions) {
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimensions][gridDimensions];
		this.score = 0;
		this.molesLeft = 0;
		for (int i = 0; i < gridDimensions; i++) {
			for (int j = 0; j < gridDimensions; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}

	public boolean place(int x, int y) {
		if (this.moleGrid[x][y] == '*') {
			this.moleGrid[x][y] = 'M';
			this.molesLeft++;
			return true;
		} else
			return false;
	}

	public void whack(int x, int y) {
		if (this.moleGrid[x][y] == 'M') {
			this.moleGrid[x][y] = 'W';
			this.molesLeft--;
			this.score++;
		}
		this.attemptsLeft--;
	}

	public void printGridToUser() {
		int dimensions = this.moleGrid.length;
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				if (this.moleGrid[i][j] == 'M')
					System.out.print('*');
				else
					System.out.print(this.moleGrid[i][j]);
			}
			System.out.println();
		}
	}

	public void printGrid() {
		int dimensions = this.moleGrid.length;
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				System.out.print(this.moleGrid[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		WhackAMole grid = new WhackAMole(50, 10);
		for (int i = 0; i < 10; i++) {
			int x = (int) Math.round(9 * Math.random());
			int y = (int) Math.round(9 * Math.random());
			if (!grid.place(x, y))
				i--;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("You have a maximum of 50 attempts to quit " 
				+ "enter -1,-1 at any time");
		while (grid.attemptsLeft > 0 && grid.molesLeft > 0) {
			System.out.println("Whack at:\nX-Coordinate: ");
			int x = scanner.nextInt();
			System.out.println("Y-coordinate: ");
			int y = scanner.nextInt();
			if ((x == -1) || (y == -1))
				break;
			else if((x < 0) || (y < 0) || (y > 10) || (x > 10)) {
				System.out.println("Please Enter Valid Values");
				continue;
			}
			grid.whack(x, y);
			System.out.println("Total Socre: " + grid.score + "\tAttemps Left:" 
					+ grid.attemptsLeft);
			grid.printGridToUser();
		}
		System.out.println("Total Score: "+ grid.score);
		grid.printGrid();
		if(grid.score == 10)
			System.out.println("Yay, you've won!");
	}

}
