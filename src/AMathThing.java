import java.util.Scanner;

public class AMathThing {
    public static void main(String[] args) {
        final int GAME_BOARD_SIZE = 3;
        Scanner in = new Scanner(System.in);
        Token player = new Token(1,1,1);
        int[] numList = {8, 1, 6, 3, 5, 7, 4, 9, 2};
        Gameboard board = new Gameboard(GAME_BOARD_SIZE, numList, player);

        boolean continueGame = true;
        while (continueGame) {
            board.printBoard();
            boolean validDirection = false;
            while (!validDirection) {
                System.out.print("Slide in a direction (UP/U = Addition; DOWN/D = Subtraction; LEFT/L = Multiplication; RIGHT/R = Division): ");
                String availableDirections = "UuDdLlRr";
                String input = in.nextLine().toUpperCase();
                char direction = input.charAt(0);
                if (direction == 'Q') { //key to prematurely quit game
                    System.out.println("Ok, bye!");
                    System.exit(0);
                }
                else if (direction == 'U') {
                    player.moveToken(Token.Direction.UP, board);
                    validDirection = true;
                }
                else if (direction == 'D') {
                    player.moveToken(Token.Direction.DOWN, board);
                    validDirection = true;
                }
                else if (direction == 'L') {
                    player.moveToken(Token.Direction.LEFT, board);
                    validDirection = true;
                }
                else if (direction == 'R') {
                    player.moveToken(Token.Direction.RIGHT, board);
                    validDirection = true;
                }
                else { //No valid direction selected
                    System.out.println("Please enter a valid direction.");
                }

            }
        }
    }
}
