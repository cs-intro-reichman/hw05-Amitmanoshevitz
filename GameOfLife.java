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
        int gen = Ngen;
        for (int gen1 = 0; gen1 < gen; gen1++) {
            System.out.println("Generation " + gen1 + ":");
            print(board);
            board = evolve(board);
        }
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
        int rows = Integer.parseInt(in.readLine());
        int cols = Integer.parseInt(in.readLine());
        int[][] board = new int[rows + 2][cols + 2];
        String line = in.readLine();

        for (int i = 1; i <= rows; i++) {
            line = in.readLine();
            if (!line.equals(" ")) {
                for (int j = 1; j <= cols; j++) {
                    if (j - 1 < line.length()) {
                        if (line.charAt(j - 1) == 'x') {
                            board[i][j] = 1;
                        }
                    }
                }
            }
        }
        return board;
    }

    public static int[][] evolve(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] newboard = new int[rows][cols];
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                newboard[i][j] = cellValue(board, i, j);
            }
        }
        return newboard;
    }

    public static int cellValue(int[][] board, int i, int j) {
        int cell = count(board, i, j);

        if (board[i][j] == 1) {
            if (cell < 2 || cell > 3) {
                cell = 0;
            } else if (cell == 2 || cell == 3) {
                cell = 1;
            }
        } else if (board[i][j] == 0) {
            if (cell == 3) {
                cell = 1;
            } else {
                cell = 0;
            }
        }
        return cell;
    }

    public static int count(int[][] board, int i, int j) {
        int livingcells = 0;
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (!(x == 0 && y == 0) && (board[i + y][j + x] == 1)) {
                    livingcells++;
                }
            }
        }
        return livingcells;
    }

    public static void print(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void show(int[][] board) {
        // Implement the show method according to your specific requirements
    }
}
