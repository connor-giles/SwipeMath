package com.example.myfirstapplication;

public class Gameboard
{
    private int size;
    private int[][] layout;
    private Token token;

    public Gameboard(int boardSize, int[] numberList, Token token) {
        size = boardSize;
        layout = new int[size][size];
        this.token = token;
        int listPointer = 0;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                try {
                    layout[j][i] = numberList[listPointer++];
                } catch (Exception e) {}
            }
        }
    }

    public void printBoard() {
        int[] tokenLocation = token.getLocation();
        for (int i = 0; i < size*4 + 1; i++) {
            System.out.print('-');
        }
        System.out.println();
        for (int j = 0; j < layout.length; j++) {
            System.out.print('|');
            for (int i = 0; i < layout[j].length; i++) {
                System.out.print(((tokenLocation[0] == i && tokenLocation[1] == j) ? ("*" + token.getTokenValue() + "*") : (" " + layout[j][i] + " ")) + "|");
            }
            System.out.println();
            for (int i = 0; i < size*4 + 1; i++) {
                System.out.print('-');
            }
            System.out.println();
        }
    }

    public Token getToken() {
        return token;
    }

    public int getBoardValueAt(int x, int y) {
        return layout[y][x];
    }

    public int getSize() {
        return size;
    }
}