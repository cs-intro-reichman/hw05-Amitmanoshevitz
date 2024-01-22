/** 
 *  Game of Life.
 *  Usage: "java GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the homework document.
 */

 public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		 test3(fileName, 3);
	}
	
	
	private static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);
	}
		
	private static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		int gen;

		for (gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);}
	}
		
	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	
	
	public static int[][] read(String fileName) {
		In in = new In(fileName);
		int row = Integer.parseInt(in.readLine());
		int col = Integer.parseInt(in.readLine());

		int[][] board = new int[row + 2][col + 2];
		String line;
		int i;
		for (i = 1; i <= row; i++) {
			line = in.readLine();
			if (line != " ") {
				for (int j = 1 ; j <= col ; j++) {
				if (j-1 < line.length()) {
				if (line.charAt(j-1)== 'x') {
							board[i][j] = 1;
				}
			}
			}
		}
		}
		return board;
	}
	
	
	public static int[][] evolve(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		int [][] newBoard = new int [row][col];

		for (int i = 1; i < row-1 ; i++) {
			for (int j = 1 ; j < col-1 ; j++) {
				if (cellValue(board, i, j) == 1) {
					newBoard[i][j] = 1;
				} else {
					newBoard[i][j] = 0;
			}
	}
	}
		return newBoard;
	}

	
	public static int cellValue(int[][] board, int i, int j) {
		int cell = count(board,i,j);
		if (board[i][j] == 1) {
			if (cell < 2 || cell > 3) {
			cell = 0;}
			else if ((cell == 2) || (cell == 3)) 
			{cell = 1;} }

		else if (board[i][j] == 0) {
			if (cell >= 3) {
				cell = 1;
			} else {
			cell = 0;
		}
	}
		return cell;
	}
	

	public static int count(int[][] board, int i, int j) {
		int livingcells = 0;
		for (int y = -1 ; y <= 1 ; y++) {
			for (int x = -1 ; x <= 1 ; x++) {
			    if (!(x == 0 && y == 0) && (board[i+y][j+x] == 1)) {
				livingcells++;
			    }
			}
		}
		return livingcells;	
	}

	
	
    public static void print(int[][] arr) {
		int rows = arr.length;
		int col = arr[0].length;

		for (int i = 1; i < rows-1 ; i++) {
			for (int j =1; j < col-1 ; j++) {
				System.out.printf("%3s",arr[i][j]);
			}
			System.out.println();
		}
	}
		
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);
		StdDraw.enableDoubleBuffering();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}
}
