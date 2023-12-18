package TicTacToe;
import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    static void drawBoard(String[] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[0 + i * 3] + " | " + board[1 + i * 3] + " | " + board[2 + i * 3] + " |");
            System.out.println("-------------");
        }
    }

    static void takeInput(String playerToken) {
        boolean valid = false;
        while (!valid) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Where will we put " + playerToken + "? ");
            String playerAnswer = scanner.nextLine();
            try {
                int position = Integer.parseInt(playerAnswer);
                if (position >= 1 && position <= 9) {
                    if (!board[position - 1].equals("X") && !board[position - 1].equals("O")) {
                        board[position - 1] = playerToken;
                        valid = true;
                    } else {
                        System.out.println("This cell is already taken");
                    }
                } else {
                    System.out.println("Invalid input. Enter a number from 1 to 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Are you sure you entered a number?");
            }
        }
    }

    static String checkWin(String[] board) {
        int[][] winCoord = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int[] each : winCoord) {
            if (board[each[0]].equals(board[each[1]]) && board[each[1]].equals(board[each[2]])) {
                return board[each[0]];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int counter = 0;
        boolean win = false;

        while (!win) {
            drawBoard(board);

            if (counter % 2 == 0) {
                takeInput("X");
            } else {
                takeInput("O");
            }

            counter++;

            if (counter > 4) {
                String winner = checkWin(board);
                if (winner != null) {
                    System.out.println(winner + " won!");
                    win = true;
                    break;
                }
            }

            if (counter == 9) {
                System.out.println("Draw!");
                break;
            }
        }

        drawBoard(board);
    }
}
