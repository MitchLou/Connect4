package com.Lou;

import java.util.Scanner;
import java.util.*;

public class Connect4 {
    public static void main(String[] args) {
        System.out.println("Welcome to Connect 4");
        play();
    }

    static void play() {
        Scanner scnr = new Scanner(System.in);
        String[][] boardArray = new String[6][7];
        System.out.println(boardArray[0].length);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                boardArray[i][j] = "null";
            }
        }

        Boolean firstTurnBool = true;
        String currentTurn = "";
        int boardMoves = 0;
        int userColumn;

        System.out.println("Who has the first turn? (Player R or Player Y)");

        while (firstTurnBool) {
            String firstTurn = scnr.nextLine();

            if (firstTurn.equals("Player R")) {
                firstTurnBool = false;
                currentTurn = "R";

            } else if (firstTurn.equals("Player Y")) {
                firstTurnBool = false;
                currentTurn = "Y";

            } else {
                System.out.println("Wrong input, enter who has first turn again (Player R or Player Y)");
            }

        } //looping the player turns
        displayBoard(boardArray);
        while (true) {

            while (currentTurn.equals("R")) {
                if (boardMoves == 42){         //The Board is full and a winner was not decided
                    System.out.println("No Winner was decided");
                    playAgain();
                }
                System.out.println("Choose your column player R: ");
                userColumn = scnr.nextInt();
                if (validMove(currentTurn, userColumn, boardArray) == true) {
                    boardMoves += 1;
                    displayBoard(boardArray);
                    winnerCheck(currentTurn, boardArray);
                    currentTurn = "Y";
                } else {
                    System.out.println("You can't place your piece there Player R");
                }
            }

            while (currentTurn.equals("Y")) {

                if (boardMoves == 42){
                    System.out.println("No Winner was decided");
                    playAgain();
                }
                System.out.println("Choose your column player Y");
                userColumn = scnr.nextInt();

                if (validMove(currentTurn, userColumn, boardArray) == true) {
                    displayBoard(boardArray);
                    boardMoves += 1;
                    winnerCheck(currentTurn, boardArray);
                    currentTurn = "R";
                } else {
                    System.out.println("You can't place your piece there Player Y");
                }
            }
        }
    }

    static void displayBoard(String boardArray[][]) {
        System.out.println();

        System.out.println("   1   2   3   4   5   6   7");
        System.out.println(" ─────────────────────────────");

        for (int i = 0; i < boardArray.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < boardArray[0].length; j++) {
                if (boardArray[i][j].equals("null")) {
                    System.out.print("");
                    System.out.print("  | ");
                } else {
                    System.out.print(boardArray[i][j]);
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean validMove(String userTurn, int columnSelection, String boardArray[][]) {
        Integer [] possibleSelections = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> selectionList = new ArrayList<>(Arrays.asList(possibleSelections));

        if (selectionList.contains(columnSelection) == false){
            return false;
        }

        for (int i = 5; i >= 0; i--){
            if (boardArray[i][columnSelection - 1].equals("null")){
                boardArray[i][columnSelection - 1] = userTurn;
                return true;
            }
        }

        return false;
    }


    static boolean verticalWinner(String boardArray[][]) {
        String symbol = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if(boardArray[i][j].equals("R") || boardArray[i][j].equals("Y")){
                    if ((boardArray[i][j] == boardArray[i + 1][j]) && (boardArray[i][j] == boardArray[i + 2][j]) && (boardArray[i][j] == boardArray[i+3][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static Boolean horizontalWinner(String boardArray[][]) {
        String symbol = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(boardArray[i][j].equals("R") || boardArray[i][j].equals("Y")){
                    if ((boardArray[i][j] == boardArray[i][j+1]) && (boardArray[i][j] == boardArray[i][j+2]) && (boardArray[i][j] == boardArray[i][j+3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static Boolean leftDiagonalWinner(String boardArray[][]) {
        String symbol = null;
        for (int i = 0; i < boardArray.length - 3; i++) {
            for (int j = 0; j < boardArray[0].length - 3; j++) {
                if(boardArray[i][j].equals("R") || boardArray[i][j].equals("Y")){
                    if ((boardArray[i][j] == boardArray[i+1][j+1]) && (boardArray[i][j] == boardArray[i+2][j+2]) && (boardArray[i][j] == boardArray[i+3][j+3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static Boolean rightDiagonalWinner(String boardArray[][]) {
        String symbol = null;
        for (int i = 3; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length - 3; j++) {
                if(boardArray[i][j].equals("R") || boardArray[i][j].equals("Y")){
                    if ((boardArray[i][j] == boardArray[i-1][j+1]) && (boardArray[i][j] == boardArray[i-2][j+2]) && (boardArray[i][j] == boardArray[i-3][j+3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void winnerCheck(String currentPlayer, String boardArray[][]){
        if (verticalWinner(boardArray) == true){
            System.out.println("Congratulations, Player " + currentPlayer + " is the Winner");
            playAgain();

        } else if (horizontalWinner(boardArray) == true){
            System.out.println("Congratulations, Player " + currentPlayer + " is the Winner");
            playAgain();
        } else if (leftDiagonalWinner(boardArray) == true){
            System.out.println("Congratulations, Player " + currentPlayer + " is the Winner");
            playAgain();

        } else if (rightDiagonalWinner(boardArray) == true){
            System.out.println("Congratulations, Player " + currentPlayer + " is the Winner");
            playAgain();
        }
    }

    static void playAgain (){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Would you like to play again Y/N");
        String userPlayAgain = scnr.next();

        if (userPlayAgain.equals("Y")){
            play();
        } else {
            System.out.println("Thanks for Playing!");
            System.exit(0);
        }

    }

}
