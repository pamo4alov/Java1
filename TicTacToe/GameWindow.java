package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    // Main window data
    private static final int WIN_HEIGHT = 550;
    private static final int WIN_WIDTH = 500;
    private static Field field;
    private static StartNewGameWindow startNewGameWindow;

    public GameWindow() {

        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo ( null );
        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JButton btnNewGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        startNewGameWindow = new StartNewGameWindow(this);

        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnExit);

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        field = new Field();
        add(field, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);

    }


    void startNewGame(int mode, int filedSizeX, int filedSizeY, int winLen) {
        field.startNewGame(mode, filedSizeX, filedSizeY, winLen);

    }

}
