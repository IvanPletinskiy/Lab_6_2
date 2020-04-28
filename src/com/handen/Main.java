package com.handen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        printInfoMessage();
        int n = readSquareSize();
        int[][] magicSquare = createMagicSquare(n);
        printResult(magicSquare);
    }

    private static int readSquareSize() {
        printInputMessage();

        int side = -1;
        boolean isReaded = false;
        while(!isReaded) {
            side = readNumber();

            if(isSideValid(side)) {
                isReaded = true;
            }
            else {
                showInvalidSideMessage();
            }
        }
        return side;
    }

    private static int readNumber() {
        boolean isReaded = false;
        int number = -1;
        while(!isReaded) {
            String line = readLine();

            boolean isLineValid = isLineValid(line);
            if(isLineValid) {
                isReaded = true;
                number = Integer.parseInt(line);
            }
            else {
                printIncorrectInputMessage();
            }
        }
        return number;
    }

    private static String readLine() {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(streamReader);
        boolean isReaded = false;
        String line = "";
        while(!isReaded) {
            try {
                line = reader.readLine().trim();
                isReaded = true;
            }
            catch(Exception e) {
                printErrorMessage();
            }
        }
        return line;
    }

    private static boolean isLineValid(String line) {
        boolean isValid = !line.isEmpty();
        for(char c : line.toCharArray()) {
            if(c < '0' || c > '9') {
                isValid = false;
            }
        }
        return isValid;
    }

    private static boolean isSideValid(int side) {
        return side <= 60 && side > 0 && side % 2 == 0;
    }

    private static int[][] createMagicSquare(int n) {
        int[][] first = createFirstSquare(n);
        printFirstSquare(first);

        int[][] second = createSecondSquare(first);
        printSecondSquare(second);

        int[][] magicSquare = createMagicSquare(first, second);
        return magicSquare;
    }

    private static int[][] createFirstSquare(int n) {
        int[][] square = new int[n][n];
        int[] previousSquare = createFirstRow(n);
        square[0] = previousSquare;

        for(int row = 1; row < n; row++) {
            for(int i = 0; i < n; i++) {
                if(row % 2 == 0) {
                    int index = (i + row) % n;
                    square[row][i] = previousSquare[index];
                }
                else {
                    square[row][i] = previousSquare[n - 1 - i];
                }
            }
            previousSquare = square[row];
        }
        return square;
    }

    private static int[] createFirstRow(int n) {
        int[] firstRow = new int[n];
        for(int i = 0; i < n; i++) {
            firstRow[i] = i;
        }
        return firstRow;
    }

    private static int[][] createSecondSquare(int[][] firstSquare) {
        int n = firstSquare.length;
        int[][] second = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                second[j][n - 1 - i] = firstSquare[i][j];
            }
        }
        return second;
    }

    private static int[][] createMagicSquare(int[][] first, int[][] second) {
        int n = first.length;
        int[][] magicSquare = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                magicSquare[i][j] = 4 * first[i][j] + second[i][j] + 1;
            }
        }
        return magicSquare;
    }

    private static void printResult(int[][] magicSquare) {
        System.out.println("The magic square is: ");
        printSquare(magicSquare);
    }

    private static void printSecondSquare(int[][] second) {
        System.out.println("Rotate first square by 90 degrees to get second latin square");
        System.out.println("Second latin square is: ");
        printSquare(second);
        System.out.println();
    }

    private static void printFirstSquare(int[][] first) {
        System.out.println("Fill the first latin square using 0..n-1 numbers.");
        System.out.println("First latin square is: ");
        printSquare(first);
        System.out.println();
    }

    private static void printSquare(int[][] square) {
        int n = square.length;
        for(int[] row : square) {
            for(int j = 0; j < n; j++) {
                System.out.printf("%4s", row[j]);
            }
            System.out.println();
        }
    }

    private static void printIncorrectInputMessage() {
        System.out.println("Incorrect input. Please, repeat enter.");
    }

    private static void printErrorMessage() {
        System.out.println("An error occured. Repeat input.");
    }

    private static void showInvalidSideMessage() {
        System.out.println("Side should divide by 4 without a rest and be not greater than 60. Repeat enter.");
    }

    private static void printInputMessage() {
        System.out.println("Enter a single number representing square side. \nSide should divide by 4 without a rest and be not greater than 60.");
    }

    private static void printInfoMessage() {
        System.out.println("This program prints a magic square of specified side.");
    }
}