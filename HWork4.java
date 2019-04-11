import java.util.Random;
import java.util.Scanner;

/*1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
        2. Переделать проверку победы, чтобы она не была реализована просто набором условий,
        например, с использованием циклов.
        3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
        Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
        4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.*/


public class HWork4 {

    final int SIZE = 5;             // Size of the playing field SIZExSIZE
    final int SIZE_LINE_WIN = 4;    // Winning line size
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map;
    int winX, winY;                 // To find a potentially winning stroke
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new HWork4().game();
    }


    void game() {
        initMap();
        random = new Random();
        scanner = new Scanner(System.in);

        while (true) {

            humanTurn();
            if (checkWinNew(DOT_X)) {
                System.out.println("YOU Win!");
                break;
            }


            if (isMapFull()) {
                System.out.println("Sorry, Drawn game!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWinNew(DOT_O)) {
                System.out.println("AI Win!");
                break;
            }

            if (isMapFull()) {
                System.out.println("Sorry, Drawn game!");
                break;
            }
        }
        printMap();
        System.out.println("GAME OVER");

    }


    void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1.." + SIZE + "):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void aiTurn() {

        int x, y;

        // Check "artificial intelligence" for one stroke to win
        // Try to find a potentially winning stroke
        if (checkWinTurn(DOT_X) && isCellValid(winX, winY)) {
            map[winY][winX] = DOT_O;
        }
        else {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            map[y][x] = DOT_O;

        }

    }

    /* 3. * Попробовать переписать логику проверки победы, чтобы она работала
    для поля 5х5 и количества фишек 4. Очень желательно не делать это просто
    набором условий для каждой из возможных ситуаций;*/

    boolean checkWinNew(char dt) {
        // Variable counters for horizontal and vertical lines
        int checkHorizontalCounter, checkVerticalCounter;
        // Variable counters for diagonals that are higher or coincide with the main and secondary diagonals
        int checkMainDiagonalCounter, checkSideDiagonalCounter;
        // Variable counters for diagonals that are lower than the main and secondary diagonals
        int checkBelowMainDiagonalCounter, checkBelowSideDiagonalCounter;

        for (int i = 0; i < SIZE; i++) {

            checkHorizontalCounter = 0;
            checkVerticalCounter = 0;
            checkMainDiagonalCounter = 0;
            checkSideDiagonalCounter = 0;
            checkBelowMainDiagonalCounter = 0;
            checkBelowSideDiagonalCounter = 0;
            for (int j = 0; j < SIZE; j++) {

                // Check of diagonals that are higher or coincide with the primary and secondary diagonals
                if (j+i < SIZE) {
                    if (map[j][j+i] == dt)                   { checkMainDiagonalCounter++;   }
                    else if (checkMainDiagonalCounter > 0)   { checkMainDiagonalCounter = 0; }

                    if (map[SIZE-j-1][j+i] == dt)            { checkSideDiagonalCounter++;   }
                    else if (checkSideDiagonalCounter > 0)   { checkSideDiagonalCounter = 0; }

                    if (checkMainDiagonalCounter == SIZE_LINE_WIN || checkSideDiagonalCounter == SIZE_LINE_WIN)
                        return true;
                }
                else {
                    checkMainDiagonalCounter = 0;
                    checkSideDiagonalCounter = 0;
                }

                // Check of diagonals that are lower than the main and secondary diagonals
                if (j-i >=0) {
                    if (map[j][j-i] == dt)                       { checkBelowMainDiagonalCounter++;   }
                    else if (checkBelowMainDiagonalCounter > 0)  { checkBelowMainDiagonalCounter = 0; }

                    if (map[SIZE-j-1][j-i] == dt)                { checkBelowSideDiagonalCounter++;   }
                    else if (checkBelowSideDiagonalCounter > 0)  { checkBelowSideDiagonalCounter = 0; }

                    if (checkBelowMainDiagonalCounter == SIZE_LINE_WIN || checkBelowSideDiagonalCounter == SIZE_LINE_WIN)
                        return true;
                }
                else {
                    checkBelowMainDiagonalCounter = 0;
                    checkBelowSideDiagonalCounter = 0;
                }

                // Check of horizontal and vertical lines
                if (map[j][i] == dt)                 { checkHorizontalCounter++;   }
                else if (checkHorizontalCounter > 0) { checkHorizontalCounter = 0; }

                if (map[i][j] == dt)                 { checkVerticalCounter++;     }
                else if (checkVerticalCounter > 0)   { checkVerticalCounter = 0;   }

                if (checkHorizontalCounter == SIZE_LINE_WIN || checkVerticalCounter == SIZE_LINE_WIN)
                    return true;
            }

        }

        return false;
    }

    // 2. Переделать проверку победы, чтобы она не была реализована
    // просто набором условий, например, с использованием циклов.

    boolean checkWin(char dt) {
        boolean checkHorizontal;
        boolean checkVertical;
        boolean checkMainDiagonal, checkSideDiagonal;

        checkMainDiagonal = true;
        checkSideDiagonal = true;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] != dt)         { checkMainDiagonal = false; }
            if (map[SIZE-i-1][i] != dt)  { checkSideDiagonal = false; }

            checkHorizontal = true;
            checkVertical = true;
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] != dt)     { checkHorizontal   = false; }
                if (map[i][j] != dt)     { checkVertical     = false; }
            }
            if (checkHorizontal || checkVertical) return true;

        }
        if (checkMainDiagonal || checkSideDiagonal) return true;

        return false;
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY;
    }

    //4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.

    boolean checkWinTurn(char dt) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[j][i]=dt;        // Temporarily to mark the next stroke and check if he wins in this case.
                    if (checkWinNew(dt)) {
                        // Found a potentially winning stroke
                        winX = i;
                        winY = j;
                        map[j][i]=DOT_EMPTY;    // Certainly to cancel the temporary stroke
                        return true;
                    }
                    map[j][i]=DOT_EMPTY;    // Certainly to cancel the temporary stroke
                }
            }
        }

        return false;
    }

}