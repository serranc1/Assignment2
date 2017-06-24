package com.example.christian.candycrushassignment_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Board {
    public static int rows;
    public static int columns;
    public static int empty = 0;
    private static int red = 1;
    private static int green = 2;
    private static int blue = 3;
    private static int yellow = 4;
    private static int orange = 5;
    private static int purple = 6;
    private static int red_stripedv = 7;
    private static int green_stripedv = 8;
    private static int blue_stripedv = 9;
    private static int yellow_stripedv = 10;
    private static int orange_stripedv = 11;
    private static int purple_stripedv = 12;
    private static int red_stripedh = 13;
    private static int green_stripedh = 14;
    private static int blue_stripedh = 15;
    private static int yellow_stripedh = 16;
    private static int orange_stripedh = 17;
    private static int purple_stripedh = 18;
    private static int red_bomb = 19;
    private static int green_bomb = 20;
    private static int blue_bomb = 21;
    private static int yellow_bomb = 22;
    private static int orange_bomb = 23;
    private static int purple_bomb = 24;
    public static int rainbow = 25;
    private static int red_count = 0;
    private static int green_count = 0;
    private static int blue_count = 0;
    private static int yellow_count = 0;
    private static int orange_count = 0;
    private static int purple_count = 0;
    private static int score = 0;
    public Board(int newRows, int newColumns) {
        rows = newRows;
        columns = newColumns;
    }
    public static void generate0Board (int[][] board){
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                board[i][j] = 0;
            } //end for
        } //end for
    }
    public static void generateRandBoard (int[][] board) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                board[i][j] = (int)(Math.random() * 6 + 1);
            } //end for
        } //end for
    }
    public static void printBoard (int[][] board) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "  ");
            } //end for
            System.out.println("\n\n");
        } //end for
        System.out.println();
    }
    public static void columnMatches(int[][] board, int[][] deleting) {
        for(int j = 0; j < columns; j++) {
            for(int i = 0; i < rows - 1; i++) {
                if(deleting[i][j] != 0) { //if deleting array is already going to delete something at position, increment count of the type of candy that's there to handle overlapping matches
                    if(deleting[i][j] == 1) {
                        red_count++;
                    }
                    if(deleting[i][j] == 2) {
                        green_count++;
                    }
                    if(deleting[i][j] == 3) {
                        blue_count++;
                    }
                    if(deleting[i][j] == 4) {
                        yellow_count++;
                    }
                    if(deleting[i][j] == 5) {
                        orange_count++;
                    }
                    if(deleting[i][j] == 6) {
                        purple_count++;
                    }
                    if(red_count >= 3 && i >= 2) {
                        deleting[i][j] = red;
                        deleting[i - 1][j] = red;
                        deleting[i - 2][j] = red;
                    }
                    if(green_count >= 3 && i >= 2) {
                        deleting[i][j] = green;
                        deleting[i - 1][j] = green;
                        deleting[i - 2][j] = green;
                    }
                    if(blue_count >= 3 && i >= 2) {
                        deleting[i][j] = blue;
                        deleting[i - 1][j] = blue;
                        deleting[i - 2][j] = blue;
                    }
                    if(yellow_count >= 3 && i >= 2) {
                        deleting[i][j] = yellow;
                        deleting[i - 1][j] = yellow;
                        deleting[i - 2][j] = yellow;
                    }
                    if(orange_count >= 3 && i >= 2) {
                        deleting[i][j] = orange;
                        deleting[i - 1][j] = orange;
                        deleting[i - 2][j] = orange;
                    }
                    if(purple_count >= 3 && i >= 2) {
                        deleting[i][j] = purple;
                        deleting[i - 1][j] = purple;
                        deleting[i - 2][j] = purple;
                    }
                } //end if
                else { //if nothing is specified for deletion on the deleting array at the position, check for what color is there and check how many of the same color come consequtively
                    if(board[i][j] == red || board[i][j] == red_stripedv || board[i][j] == red_stripedh || board[i][j] == red_bomb) {
                        red_count++;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(red_count >= 3 && i >= 2) {
                            deleting[i][j] = red;
                            deleting[i - 1][j] = red;
                            deleting[i - 2][j] = red;
                        }
                        if(board[i + 1][j] != red && board[i + 1][j] != red_stripedv && board[i + 1][j] != red_stripedh && board[i + 1][j] != red_bomb) {
                            red_count = 0;
                        } //end if
                    } //end if
                    else if(board[i][j] == green || board[i][j] == green_stripedv || board[i][j] == green_stripedh || board[i][j] == green_bomb) {
                        green_count++;
                        red_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(green_count >= 3 && i >= 2) {
                            deleting[i][j] = green;
                            deleting[i - 1][j] = green;
                            deleting[i - 2][j] = green;
                        }
                        if(board[i + 1][j] != green && board[i + 1][j] != green_stripedv && board[i+1][j] != green_stripedh && board[i + 1][j] != green_bomb) {
                            green_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == blue || board[i][j] == blue_stripedv || board[i][j] == blue_stripedh || board[i][j] == blue_bomb) {
                        blue_count++;
                        red_count = 0;
                        green_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(blue_count >= 3 && i >= 2) {
                            deleting[i][j] = blue;
                            deleting[i - 1][j] = blue;
                            deleting[i - 2][j] = blue;
                        }
                        if(board[i + 1][j] != blue && board[i + 1][j] != blue_stripedv && board[i+1][j] != blue_stripedh && board[i + 1][j] != blue_bomb) {
                            blue_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == yellow && board[i + 1][j] != blue_stripedv && board[i + 1][j] != blue_stripedh || board[i][j] == yellow_bomb) {
                        yellow_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(yellow_count >= 3 && i >= 2) {
                            deleting[i][j] = yellow;
                            deleting[i - 1][j] = yellow;
                            deleting[i - 2][j] = yellow;
                        }
                        if(board[i + 1][j] != yellow && board[i + 1][j] != yellow_stripedv && board[i+1][j] != yellow_stripedh && board[i + 1][j] != yellow_bomb) {
                            yellow_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == orange || board[i][j] == orange_stripedv || board[i][j] == orange_stripedh || board[i][j] == orange_bomb) {
                        orange_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        purple_count = 0;
                        if(orange_count >= 3 && i >= 2) {
                            deleting[i][j] = orange;
                            deleting[i - 1][j] = orange;
                            deleting[i - 2][j] = orange;
                        }
                        if(board[i + 1][j] != orange && board[i + 1][j] != orange_stripedv && board[i+1][j] != orange_stripedh && board[i + 1][j] != orange_bomb) {
                            orange_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == purple || board[i][j] == purple_stripedv || board[i][j] == purple_stripedh || board[i][j] == purple_bomb) {
                        purple_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        if(purple_count >= 3 && i >= 2) {
                            deleting[i][j] = purple;
                            deleting[i - 1][j] = purple;
                            deleting[i - 2][j] = purple;
                        }
                        if(board[i + 1][j] != purple && board[i + 1][j] != purple_stripedv && board[i+1][j] != purple_stripedh && board[i + 1][j] != purple_bomb) {
                            purple_count = 0;
                        } //end if
                    }
                    else {
                        continue;
                    } //end else
                } //end else
            } //end for
            //handling cases that reach end of array index of i
            if(board[rows - 1][j] == 1 && board[rows - 2][j] == 1 && board[rows - 3][j] == 1) {
                deleting[rows - 1][j] = 1;
                deleting[rows - 2][j] = 1;
                deleting[rows - 3][j] = 1;
            }
            if(board[rows - 1][j] == 2 && board[rows - 2][j] == 2 && board[rows - 3][j] == 2) {
                deleting[rows - 1][j] = 2;
                deleting[rows - 2][j] = 2;
                deleting[rows - 3][j] = 2;
            }
            if(board[rows - 1][j] == 3 && board[rows - 2][j] == 3 && board[rows - 3][j] == 3) {
                deleting[rows - 1][j] = 3;
                deleting[rows - 2][j] = 3;
                deleting[rows - 3][j] = 3;
            }
            if(board[rows - 1][j] == 4 && board[rows - 2][j] == 4 && board[rows - 3][j] == 4) {
                deleting[rows - 1][j] = 4;
                deleting[rows - 2][j] = 4;
                deleting[rows - 3][j] = 4;
            }
            if(board[rows - 1][j] == 5 && board[rows - 2][j] == 5 && board[rows - 3][j] == 5) {
                deleting[rows - 1][j] = 5;
                deleting[rows - 2][j] = 5;
                deleting[rows - 3][j] = 5;
            }
            if(board[rows - 1][j] == 6 && board[rows - 2][j] == 6 && board[rows - 3][j] == 6) {
                deleting[rows - 1][j] = 6;
                deleting[rows - 2][j] = 6;
                deleting[rows - 3][j] = 6;
            }
            //set counts to 0 at the end of each column
            red_count = 0;
            green_count = 0;
            blue_count = 0;
            yellow_count = 0;
            orange_count = 0;
            purple_count = 0;
        } //end for
        red_count = 0;
        green_count = 0;
        blue_count = 0;
        yellow_count = 0;
        orange_count = 0;
        purple_count = 0;
    }
    public static void rowMatches (int[][] board, int[][] deleting) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns - 1; j++) {
                if(deleting[i][j] != 0) {
                    if(deleting[i][j] == 1) {
                        red_count++;
                    }
                    if(deleting[i][j] == 2) {
                        green_count++;
                    }
                    if(deleting[i][j] == 3) {
                        blue_count++;
                    }
                    if(deleting[i][j] == 4) {
                        yellow_count++;
                    }
                    if(deleting[i][j] == 5) {
                        orange_count++;
                    }
                    if(deleting[i][j] == 6) {
                        purple_count++;
                    }
                    if(red_count >= 3 && j >= 2) {
                        deleting[i][j] = red;
                        deleting[i][j - 1] = red;
                        deleting[i][j - 2] = red;
                    }
                    if(green_count >= 3 && j >= 2) {
                        deleting[i][j] = green;
                        deleting[i][j - 1] = green;
                        deleting[i][j - 2] = green;
                    }
                    if(blue_count >= 3 && j >= 2) {
                        deleting[i][j] = blue;
                        deleting[i][j - 1] = blue;
                        deleting[i][j - 2] = blue;
                    }
                    if(yellow_count >= 3 && j >= 2) {
                        deleting[i][j] = yellow;
                        deleting[i][j - 1] = yellow;
                        deleting[i][j - 2] = yellow;
                    }
                    if(orange_count >= 3 && j >= 2) {
                        deleting[i][j] = orange;
                        deleting[i][j - 1] = orange;
                        deleting[i][j - 2] = orange;
                    }
                    if(purple_count >= 3 && j >= 2) {
                        deleting[i][j] = purple;
                        deleting[i][j - 1] = purple;
                        deleting[i][j - 2] = purple;
                    }
                } //end ifs
                else {
                    if(board[i][j] == red || board[i][j] == red_stripedv || board[i][j] == red_stripedh || board[i][j] == red_bomb) {
                        red_count++;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(red_count >= 3 && j >= 2) {
                            deleting[i][j] = red;
                            deleting[i][j - 1] = red;
                            deleting[i][j - 2] = red;
                        }
                        if(board[i][j + 1] != red && board[i][j + 1] != red_stripedv && board[i][j + 1] != red_stripedh && board[i][j + 1] != red_bomb) {
                            red_count = 0;
                        } //end if
                    } //end if
                    else if(board[i][j] == green || board[i][j] == green_stripedv || board[i][j] == green_stripedh || board[i][j] == green_bomb) {
                        green_count++;
                        red_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(green_count >= 3 && j >= 2) {
                            deleting[i][j] = green;
                            deleting[i][j - 1] = green;
                            deleting[i][j - 2] = green;
                        }
                        if(board[i][j + 1] != green && board[i][j + 1] != green_stripedv && board[i][j + 1] != green_stripedh && board[i][j + 1] != green_bomb) {
                            green_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == blue || board[i][j] == blue_stripedv || board[i][j] == blue_stripedh || board[i][j] == blue_bomb) {
                        blue_count++;
                        red_count = 0;
                        green_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(blue_count >= 3 && j >= 2) {
                            deleting[i][j] = blue;
                            deleting[i][j - 1] = blue;
                            deleting[i][j - 2] = blue;
                        }
                        if(board[i][j + 1] != blue && board[i][j + 1] != blue_stripedv && board[i][j + 1] != blue_stripedh && board[i][j + 1] != blue_bomb) {
                            blue_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == yellow || board[i][j] == yellow_stripedv || board[i][j] == yellow_stripedh || board[i][j] == yellow_bomb) {
                        yellow_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        orange_count = 0;
                        purple_count = 0;
                        if(yellow_count >= 3 && j >= 2) {
                            deleting[i][j] = yellow;
                            deleting[i][j - 1] = yellow;
                            deleting[i][j - 2] = yellow;
                        }
                        if(board[i][j + 1] != yellow && board[i][j + 1] != yellow_stripedv && board[i][j + 1] != yellow_stripedh && board[i][j + 1] != yellow_bomb) {
                            yellow_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == orange || board[i][j] == orange_stripedv || board[i][j] == orange_stripedh || board[i][j] == orange_bomb) {
                        orange_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        purple_count = 0;
                        if(orange_count >= 3 && j >= 2) {
                            deleting[i][j] = orange;
                            deleting[i][j - 1] = orange;
                            deleting[i][j - 2] = orange;
                        }
                        if(board[i][j + 1] != orange && board[i][j + 1] != orange_stripedv && board[i][j + 1] != orange_stripedh && board[i][j + 1] != orange_bomb) {
                            orange_count = 0;
                        } //end if
                    }
                    else if(board[i][j] == purple || board[i][j] == purple_stripedv || board[i][j] == purple_stripedh || board[i][j] == purple_bomb) {
                        purple_count++;
                        red_count = 0;
                        green_count = 0;
                        blue_count = 0;
                        yellow_count = 0;
                        orange_count = 0;
                        if(purple_count >= 3 && j >= 2) {
                            deleting[i][j] = purple;
                            deleting[i][j - 1] = purple;
                            deleting[i][j - 2] = purple;
                        }
                        if(board[i][j + 1] != purple && board[i][j + 1] != purple_stripedv && board[i][j + 1] != purple_stripedh && board[i][j + 1] != purple_bomb) {
                            purple_count = 0;
                        } //end if
                    }
                    else {
                        continue;
                    } //end else
                } //end else
            } //end for
            if(board[i][columns - 1] == red && board[i][columns - 2] == red && board[i][columns - 3] == red) {
                deleting[i][columns - 1] = red;
                deleting[i][columns - 2] = red;
                deleting[i][columns - 3] = red;
            }
            if(board[i][columns - 1] == green && board[i][columns - 2] == green && board[i][columns - 3] == green) {
                deleting[i][columns - 1] = green;
                deleting[i][columns - 2] = green;
                deleting[i][columns - 3] = green;
            }
            if(board[i][columns - 1] == blue && board[i][columns - 2] == blue && board[i][columns - 3] == blue) {
                deleting[i][columns - 1] = blue;
                deleting[i][columns - 2] = blue;
                deleting[i][columns - 3] = blue;
            }
            if(board[i][columns - 1] == yellow && board[i][columns - 2] == yellow && board[i][columns - 3] == yellow) {
                deleting[i][columns - 1] = yellow;
                deleting[i][columns - 2] = yellow;
                deleting[i][columns - 3] = yellow;
            }
            if(board[i][columns - 1] == orange && board[i][columns - 2] == orange && board[i][columns - 3] == orange) {
                deleting[i][columns - 1] = orange;
                deleting[i][columns - 2] = orange;
                deleting[i][columns - 3] = orange;
            }
            if(board[i][columns - 1] == purple && board[i][columns - 2] == purple && board[i][columns - 3] == purple) {
                deleting[i][columns - 1] = purple;
                deleting[i][columns - 2] = purple;
                deleting[i][columns - 3] = purple;
            }
            red_count = 0;
            green_count = 0;
            blue_count = 0;
            yellow_count = 0;
            orange_count = 0;
            purple_count = 0;
        } //end for
        red_count = 0;
        green_count = 0;
        blue_count = 0;
        yellow_count = 0;
        orange_count = 0;
        purple_count = 0;
    }
    public static void deleteCandies(int[][] board, int[][] deleting) {
        //System.out.println("Printing deleting board...\n");
        //printBoard(deleting);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(deleting[i][j] != 0) {
                    board[i][j] = 0;
                    score += 10;
                    deleting[i][j] = 0;
                }
            }
        }
        System.out.println("Deleting matched candies...\n");
        printBoard(board);
    }
    public static int returnScore() {
        System.out.println("Score: "+score+"\n");
        return score;
    }
    public static void fallingCandies(int[][] board) {
        System.out.println("Candies falling...\n");
        for(int k = 0; k < rows; k++) {
            for(int j = 0; j < columns; j++) {
                for(int i = rows - 1; i >= 0; i--) {
                    if(board[i][j] == 0) {
                        if(i == 0) {
                            continue;
                        }
                        else {
                            board[i][j] = board[i - 1][j];
                            board[i - 1][j] = 0;
                        }
                    }
                }
            }
        }
        printBoard(board);
    }
    public static void replacingCandies(int[][] board) {
        for(int j = 0; j < columns; j++) {
            for(int i = rows - 1; i >= 0; i--) {
                if(board[i][j] == 0) {
                    board[i][j] = (int)(Math.random() * 6 + 1);
                }
            }
        }
        System.out.println("Replacing empty spaces with new candies...\n");
        printBoard(board);
    }
    public static void checkPossibleVMoves(int[][] board, int[][] deleting, int[][] swappable) {
        for(int j = 0; j < columns; j++) {
            for(int i = 0; i < rows - 1; i++) {
                int temp1 = board[i][j];
                int temp2 = board[i + 1][j];
                board[i][j] = temp2;
                board[i + 1][j] = temp1;
                columnMatches(board, deleting);
                rowMatches(board, deleting);
                loop1:
                for(int k = 0; k < rows; k++) {
                    for(int m = 0; m < columns; m++) {
                        if(deleting[k][m] != 0) {
                            swappable[i][j] = 1;
                            swappable[i + 1][j] = 1;
                            break loop1;
                        }
                    }
                }
                board[i][j] = temp1;
                board[i + 1][j] = temp2;
                generate0Board(deleting);
            }
        }
    }
    public static void checkPossibleHMoves(int[][] board, int[][] deleting, int[][] swappable) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns - 1; j++) {
                int temp1 = board[i][j];
                int temp2 = board[i][j + 1];
                board[i][j] = temp2;
                board[i][j + 1] = temp1;
                columnMatches(board, deleting);
                rowMatches(board, deleting);
                loop1:
                for(int k = 0; k < rows; k++) {
                    for(int m = 0; m < columns; m++) {
                        if(deleting[k][m] != 0) {
                            swappable[i][j] = 1;
                            swappable[i][j + 1] = 1;
                            break loop1;
                        }
                    }
                }
                board[i][j] = temp1;
                board[i][j + 1] = temp2;
                generate0Board(deleting);
            }
        }
    }
    public static int[] getUserCoordinates() {
        int candy1y, candy1x, candy2y, candy2x;
        int[] coordinates = new int[4];
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            System.out.println("Enter the  y coordinate of the candy you want to move, where 0 is the top row:");
            inputLine = br.readLine();
            candy1y = Integer.parseInt(inputLine);
            System.out.println("Enter the x coordinate of the candy you want to move, where 0 is the leftmost column:");
            inputLine = br.readLine();
            candy1x = Integer.parseInt(inputLine);
            System.out.println("Enter the  y coordinate of the candy you want to swap with, where 0 is the top row:");
            inputLine = br.readLine();
            candy2y = Integer.parseInt(inputLine);
            System.out.println("Enter the x coordinate of the candy you want to swap with, where 0 is the leftmost column:");
            inputLine = br.readLine();
            candy2x = Integer.parseInt(inputLine);
            coordinates[0] = candy1y;
            coordinates[1] = candy1x;
            coordinates[2] = candy2y;
            coordinates[3] = candy2x;
        }
        catch(IOException e) {
        }
        return coordinates;
    }
    public static int checkUserCoordinates(int[] coordinates, int[][] board, int[][] swappable) {
        int temp1 = board[(coordinates[0])][(coordinates[1])];
        int temp2 = board[(coordinates[2])][(coordinates[3])];
        if(swappable[(coordinates[0])][(coordinates[1])] == 1 && swappable[(coordinates[2])][(coordinates[3])] == 1) {
            board[(coordinates[0])][(coordinates[1])] = temp2;
            board[(coordinates[2])][(coordinates[3])] = temp1;
            return 1;
        }
        else {
            System.out.println("Invalid coordinates for moving selected! Try again.\n");
            return 0;
        }
    }
}