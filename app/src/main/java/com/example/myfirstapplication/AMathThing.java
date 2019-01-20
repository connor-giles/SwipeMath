package com.example.myfirstapplication;

import java.util.Scanner;

public class AMathThing {
    public static void main(String[] args) {
        final int GAME_BOARD_SIZE = 3;
        final int TARGET_NUMBER = 101;
        Scanner in = new Scanner(System.in);
        Token player = new Token(1,1,1);
        int[] numList = {8, 1, 6, 3, 5, 7, 4, 9, 2};
        Gameboard board = new Gameboard(GAME_BOARD_SIZE, numList, player);

        boolean continueGame = true;
        while (continueGame) {
            System.out.println("\nTarget Value = " + TARGET_NUMBER);
            board.printBoard();
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Moves: " + player.getMoveCounter() + "; Slide in a direction: (UP/U = Addition; DOWN/D = Subtraction; LEFT/L = Multiplication; RIGHT/R = Division" + ((player.getUndoState()) ? "; Can undo with Z":"") + "): ");
                //String availableDirections = "UuDdLlRr";
                String input = in.nextLine().toUpperCase();
                if (input.length() == 0) continue;
                char direction = input.charAt(0);
                if (direction == 'Q') { //key to prematurely quit game
                    System.out.println("Ok, bye!");
                    System.exit(0);
                }
                else if (direction == 'Z') { //key to undo last move
                    if (player.getUndoState()) {
                        player.undoLast();
                    } else {
                        System.err.println("Must successfully move in a direction to enable undo");
                    }
                    validChoice = true;
                }
                else if (direction == 'U') {
                    player.moveToken(Token.Direction.UP, board);
                    validChoice = true;
                }
                else if (direction == 'D') {
                    player.moveToken(Token.Direction.DOWN, board);
                    validChoice = true;
                }
                else if (direction == 'L') {
                    player.moveToken(Token.Direction.LEFT, board);
                    validChoice = true;
                }
                else if (direction == 'R') {
                    player.moveToken(Token.Direction.RIGHT, board);
                    validChoice = true;
                }
                else { //No valid direction selected
                    System.out.println("Please enter a valid direction.");
                }
                if (player.getTokenValue() > 255 || player.getTokenValue() < 0) {
                    board.printBoard();
                    System.out.println("Went outside of the 0-255 limit! You Lose!");
                    System.exit(0);
                } else if (player.getTokenValue() == TARGET_NUMBER) {
                    System.out.println("\nTarget Value = " + TARGET_NUMBER);
                    board.printBoard();
                    System.out.println("Congratulation! You Win! Moves: " + player.getMoveCounter());
                    continueGame = false;
                }
            }
        }
    }
}
