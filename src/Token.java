//Token that will be moved around the board. Only needs initial x, y, and value.
public class Token
{
    private int x,y, tokenValue;
    private int tempX, tempY, tempTokenValue;
    private int previousX, previousY,previousTokenValue;
    private int moveCounter = 0;
    private boolean canUndo = false;

    public enum Direction{UP, DOWN, LEFT, RIGHT};

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

    public int getMoveCounter() {
        return moveCounter;
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

    public void undoLast() { //sets
        x = previousX;
        y = previousY;
        tokenValue = previousTokenValue;
        canUndo = false;
    }

    public boolean getUndoState() {
        return canUndo;
    }

    public void moveToken(Direction direction, Gameboard board) { // Moves the token on the board and changes its value and location accordingly.
        tempX = x;
        tempY = y;
        tempTokenValue = tokenValue;
        if (direction == Direction.UP) { //Addition
            y -= 1;
            verifyLocation(board.getSize());
            tokenValue += board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.DOWN) { //Subtraction
            y += 1;
            verifyLocation(board.getSize());
            tokenValue -= board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.LEFT) { //Multiplication
            x -= 1;
            verifyLocation(board.getSize());
            tokenValue *= board.getBoardValueAt(x, y);
        }
        else if (direction == Direction.RIGHT) { //Division. Only allowed if perfectly divisible.
            x += 1;
            verifyLocation(board.getSize());
            if (tokenValue % board.getBoardValueAt(x, y) != 0) {
                x = tempX;
                y = tempY;
                tokenValue = tempTokenValue;
                System.err.println("Can only slide right if perfectly divisible");
                return;
            }
            tokenValue /= board.getBoardValueAt(x, y);
        }
        if (x == previousX && y == previousY) {
            x = tempX;
            y = tempY;
            tokenValue = tempTokenValue;
            System.err.println("Cannot move back to previous space!");
            return;
        } else {
            previousX = tempX;
            previousY = tempY;
            previousTokenValue = tempTokenValue;
        }
        canUndo = true; //after a successful slide, the ability to undo that slide is enabled.
        moveCounter++;
    }
}
