public class Token
{
    private int x,y;
    private int tokenValue;

    public static enum Direction{UP, DOWN, LEFT, RIGHT};

    public Token(int xInit, int yInit, int tokenValue) { // Constructor
        x = xInit;
        y = yInit;
        this.tokenValue = tokenValue;
    }

    public int[] getLocation() { // Return two-element array of {x,y} coordinates
        int[] coordinates = {x,y};
        return coordinates;
    }

    public int getTokenValue() { // Return current value of the token
        return tokenValue;
    }

    public int setTokenValue(int tokenValue) { // Performs operation on token's value and returns new value of the token
        this.tokenValue = tokenValue;
        return tokenValue;
    }

    public void setLocation(int newX, int newY, Gameboard board) { // Used to modify location of token, and verifies the new location fits on the board.
        x = newX;
        y = newY;
        verifyLocation(board.getSize());
    }

    public static int numberWrapper(int num, int max) { // Useful for keeping token within boundaries of the gameBoard.
        if (num >= max) {
            return num - max;
        }
        if (num < 0) {
            return num + max;
        }
        return num;
    }

    public void verifyLocation(int size) { // Uses numberWrapper() to keep the token inside the gameBoard of certain size.
        x = numberWrapper(x, size);
        y = numberWrapper(y, size);
    }

    public void moveToken(Direction direction, Gameboard board) { // Moves the token on the board and changes its value and location accordingly.
        if (direction == Direction.UP) {
            setLocation(x, y - 1, board);
            tokenValue += board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.DOWN) {
            setLocation(x, y + 1, board);
            tokenValue -= board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.LEFT) {
            setLocation(x - 1, y, board);
            tokenValue *= board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.RIGHT) {
            setLocation(x + 1, y, board);
            tokenValue /= board.getBoardValueAt(x, y);
        }
    }
}
