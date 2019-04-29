package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Field extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;
    private static final char PLAYER_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '.';
    private static final int DELTA_DRAW = 10;
    private static Random random = new Random();
    private GameOverWindow gameOverWindow;

    char[][] field;
    int fieldSizeX;
    int fieldSizeY;
    int winLenght;
    boolean gameOver;
    boolean isWait;
    int mode;
    int cellHeight;
    int cellWidth;
    boolean isInitialized = false;
    boolean stepPlayer2;

    Field() {
        setBackground(Color.lightGray);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        gameOverWindow = new GameOverWindow();
    }

    void playerStep(int y,int x, char symbol) {
        setSym(y, x, symbol);
    }

    void aiStep() {
        //Searching foa a winning stroke of computer
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellValid(i, j)) {
                    setSym(i, j, AI_DOT);
                    if (checkWin(AI_DOT)) return;
                    setSym(i, j, EMPTY_DOT);
                }
            }
        // Check the player, does he has a future winning stroke
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++) {
                if (isCellValid(i, j)) {
                    setSym(i, j, PLAYER_DOT);
                    if (checkWin(PLAYER_DOT)) {
                        setSym(i, j, AI_DOT);
                        return;
                    }
                    setSym(i, j, EMPTY_DOT);
                }
            }

        // If there is nothing winning, then we do it as in a lesson
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellValid(y, x));
        setSym(y, x, AI_DOT);
    }

    void update(MouseEvent e) {
        if (!gameOver && !isWait) {
            int cellX = e.getX()/cellWidth;
            int cellY = e.getY()/cellHeight;
            if (mode == 0) modePlayAI(cellY, cellX);
            else modeTwoPlayer(cellY, cellX);
        }
    }

    // Two player mode
    void modeTwoPlayer(int y, int x) {
        char symbol =  stepPlayer2 ? AI_DOT : PLAYER_DOT;
        playerStep(y,x,symbol);
        repaint();
        if (checkWin(symbol)) {
            gameOverWindow.setMessage(stepPlayer2 ? "Second player WIN!!" : "First player WIN!!",this);
            return;
        }
        if (isFuelFull()) {
            gameOverWindow.setMessage("DRAW!",this);
            return;
        }
        stepPlayer2 = !stepPlayer2;
    }

    // Against computer mode
    void modePlayAI(int y, int x) {
        playerStep(y,x,PLAYER_DOT);
        repaint();
        if (checkWin(PLAYER_DOT)) {
            gameOverWindow.setMessage("PLAYER WIN!",this);
            return;
        }
        if (isFuelFull()) {
            gameOverWindow.setMessage("DRAW!",this);
            return;
        }
        isWait = true;
        aiStep();
        isWait = false;
        repaint();
        if (checkWin(AI_DOT)) {
            gameOverWindow.setMessage("AI WIN!",this);
            return;
        }
        if (isFuelFull()) {
            gameOverWindow.setMessage("DRAW!",this);
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        System.out.println(mode + " " + filedSizeX + " " + winLen);
        gameOver = false;
        stepPlayer2 = false;
        isWait = false;
        this.fieldSizeX = filedSizeX;
        this.fieldSizeY = filedSizeY;
        this.winLenght = winLen;
        this.mode = mode;
        field = new char[filedSizeY][filedSizeX];
        initFields();
        isInitialized = true;
        repaint();
    }

    void render(Graphics g) {
        if(!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        cellWidth = panelWidth/fieldSizeY;
        cellHeight = panelHeight/fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x,0,x, panelHeight);
        }

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] != EMPTY_DOT) {
                    if (field[j][i] == PLAYER_DOT) {
                        // Drawing a cross
                        g.setColor(Color.blue);
                        g.drawLine((i * cellWidth) + DELTA_DRAW, (j * cellHeight)+ DELTA_DRAW, (i + 1) * cellWidth - DELTA_DRAW, (j + 1) * cellHeight - DELTA_DRAW);
                        g.drawLine((i + 1) * cellWidth - DELTA_DRAW, (j * cellHeight) + DELTA_DRAW , (i * cellWidth) + DELTA_DRAW, (j + 1) * cellHeight - DELTA_DRAW);
                    }
                    if (field[j][i] == AI_DOT) {
                        // Drawing a zero
                        g.setColor(Color.red);
                        g.drawOval((i * cellWidth) + DELTA_DRAW, (j * cellHeight) + DELTA_DRAW, cellWidth - DELTA_DRAW * 2, cellHeight - DELTA_DRAW * 2);
                    }
                }
            }
        }
    }

    private void initFields() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // Checking a line
    private boolean checkLine(int y, int x, int vy, int vx, char sym) {
        int wayX = x + (winLenght - 1) * vx;
        int wayY = y + (winLenght - 1) * vy;
        if (wayX < 0 || wayY < 0 || wayX > fieldSizeX - 1 || wayY > fieldSizeY - 1) return false;
        for (int i = 0; i < winLenght; i++) {
            int itemY = y + i * vy;
            int itemX = x + i * vx;
            if (field[itemY][itemX] != sym) return false;
        }
        return true;
    }

    private boolean checkWin(char sym) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (checkLine(i, j, 0, 1,  sym)) return true;   // checking a line of X
                if (checkLine(i, j, 1, 1,  sym)) return true;   // checking a diagonal of X Y
                if (checkLine(i, j, 1, 0,  sym)) return true;   // checking a line of Y
                if (checkLine(i, j, -1, 1, sym)) return true;   // checking a diagonal of X -Y
            }
        }
        return false;
    }

    boolean isFuelFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > fieldSizeX - 1 || y > fieldSizeY - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }
}
