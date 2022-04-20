package component;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import component.game.BoardCell;
import component.game.GameSetting;
import input.GameInput;

import blocks.BlockType;
import setting.UserSetting;

public class Game extends Canvas {

    public JFrame gameFrame;
    private GameSetting game = new GameSetting();
    private final BufferStrategy strategy;

    private final int BOARD_CORNER_X = 300;
    private final int BOARD_CORNER_Y = 50;

    private final GameInput keyboard = new GameInput();
    private long lastIteration = System.currentTimeMillis();

    private static final int BLOCK_WIDTH = 20;

    public Game() {
        gameFrame = new JFrame("SeoulTech SE4 Tetris");
        
        JPanel panel = (JPanel) gameFrame.getContentPane();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);

        setBounds(0, 0, 800, 600);
        
        panel.add(this);
        
        setIgnoreRepaint(true);

        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(false);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(keyboard);
        requestFocus();

        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void gameLoop(Frame homeFrame, Home home) {
        while (true) {
            if (keyboard.newGame()) {
                game = new GameSetting();
                game.startGame();
            }
            if (game.isPlaying()) {
                if (!game.isPaused()) {
                    tetrisLoop();
                }
                if (keyboard.pauseGame()) {
                    game.pauseGame();
                }
            }
            if (keyboard.home()) {
                game = new GameSetting();
                homeFrame.setVisible(true);
                gameFrame.setVisible(false);
                homeFrame.requestFocus();
            }
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
            draw(homeFrame, home);
        }
    }

    void tetrisLoop() {
        if (game.isDropping()) {
            game.moveDown();
        } else if (System.currentTimeMillis() - lastIteration >= game.getIterationDelay()) {
            game.moveDown();
            lastIteration = System.currentTimeMillis();
        }
        if (keyboard.rotate()) {
            game.rotate();
        } else if (keyboard.left()) {
            game.moveLeft();
        } else if (keyboard.right()) {
            game.moveRight();
        } else if (keyboard.down()) {
            game.moveDown();
        }
        else if (keyboard.drop()) {
            game.drop();
        }
    }

    public void draw(Frame homeFrame, Home home) {
        Graphics2D g = getGameGraphics();
        drawEmptyBoard(g);
        drawHelpBox(g);
        drawPiecePreviewBox(g);

        if (game.isPlaying()) {
            drawCells(g);
            drawBlockPreview(g, game.getNextBlock().getType());

            if (game.isPaused()) {
                drawGamePaused(g);
            }
        }

        if (game.isGameOver()) {
            drawCells(g);
            drawGameOver(g);

            ScoreBoard scoreBoard = home.scoreBoardGUI;

            int min = scoreBoard.min("normal");
            if (min < game.getTotalScore()) {
                addNewRecordPanel(home, game.getTotalScore());
            } else {
                addGameOverPanel(home, game.getTotalScore());
            }

            home.normalGameOverGUI.rank = 0;
            home.normalGameOverGUI.score = game.getTotalScore();
            home.normalGameOverGUI.normalGameOverFrame.setVisible(true);
            gameFrame.setVisible(false);
            game = new GameSetting();
        }

        drawStatus(g);
        drawPlayTetris(g);

        g.dispose();
        strategy.show();
    }

    private void addNewRecordPanel(Home home, int score) {
        home.normalGameOverGUI.contentPanel = new Panel();
        home.normalGameOverGUI.contentPanel.setBounds(150, 220, 500, 210);
        home.normalGameOverGUI.contentPanel.setLayout(new GridLayout(2, 1));
        home.normalGameOverGUI.contentPanel.setFont(new Font("Dialog", Font.PLAIN, 50));
        home.normalGameOverGUI.contentPanel.setForeground(Color.white);

        Label textLabel = new Label("New Record " + Integer.toString(score));
        textLabel.setAlignment(Label.CENTER);

        home.normalGameOverGUI.textField = new TextField("Your Name");
        home.normalGameOverGUI.textField.setForeground(Color.black);

        home.normalGameOverGUI.contentPanel.add(textLabel);
        home.normalGameOverGUI.contentPanel.add(home.normalGameOverGUI.textField);

        home.normalGameOverGUI.normalGameOverPanel.add(home.normalGameOverGUI.contentPanel);
    }

    private void addGameOverPanel(Home home, int score) {
        home.normalGameOverGUI.contentPanel = new Panel();
        home.normalGameOverGUI.contentPanel.setBounds(150, 220, 500, 210);
        home.normalGameOverGUI.contentPanel.setLayout(new GridLayout(2, 1));
        home.normalGameOverGUI.contentPanel.setFont(new Font("Dialog", Font.PLAIN, 50));
        home.normalGameOverGUI.contentPanel.setForeground(Color.white);

        Label textLabel = new Label("Your Score");
        Label scoreLabel = new Label(Integer.toString(score));
        textLabel.setAlignment(Label.CENTER);
        scoreLabel.setAlignment(Label.CENTER);

        home.normalGameOverGUI.contentPanel.add(textLabel);
        home.normalGameOverGUI.contentPanel.add(scoreLabel);

        home.normalGameOverGUI.normalGameOverPanel.add(home.normalGameOverGUI.contentPanel);
    }

    private Graphics2D getGameGraphics() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    private void drawCells(Graphics2D g) {
        BoardCell[][] cells = game.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, BOARD_CORNER_X + i * 20, BOARD_CORNER_Y + (19 - j) * 20, getBoardCellColor(cells[i][j]));
            }
        }
    }

    private void drawEmptyBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.GRAY);
        g.drawRect(BOARD_CORNER_X - 1, BOARD_CORNER_Y - 1, 10 * BLOCK_WIDTH + 2, 20 * BLOCK_WIDTH + 2);
    }

    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("Mode: Normal", 10, 20);
        g.drawString(getLevel(), 10, 40);
        g.drawString(getLines(), 10, 60);
        g.drawString(getDifficultyLevl(),10,80);
        g.drawString(getScore(), 20, 100);
    }

    private void drawGameOver(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 350, 550);
    }

    private void drawGamePaused(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("GAME PAUSED", 350, 550);
    }


    private void drawPlayTetris(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Play TETRIS !", 350, 500);
    }

    private String getLevel() {
        return String.format("Your level: %1s", game.getLevel());
    }

    private String getLines() {
        return String.format("Full lines: %1s", game.getLines());
    }

    private String getScore() {
        return String.format("Score     %1s", game.getTotalScore());
    }

    private String getDifficultyLevl(){return String.format("Difficulty Level: %1s", UserSetting.getDifficultyLevel());}

    private void drawPiecePreviewBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("Next:", 50, 420);
    }

    private void drawHelpBox(Graphics2D g) {
    	UserSetting UserSetting = new UserSetting();
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("H E L P", 50, 140);
        g.drawString("F1: New Game", 10, 160);
        g.drawString("ESC: Pause Game/Continue", 10, 180);
        g.drawString(UserSetting.getStringKey("ROTATE") +" : Rotate", 10, 200);
        g.drawString(UserSetting.getStringKey("LEFT") + " : Move Left", 10, 220);
        g.drawString(UserSetting.getStringKey("RIGHT") + " : Move Right", 10, 240);
        g.drawString(UserSetting.getStringKey("DOWN")+ " : Move Down", 10, 260);
        g.drawString(UserSetting.getStringKey("DROP") + " : Drop", 10, 280);
        g.drawString("F2: Home", 10, 300);
    }

    private void drawBlockPreview(Graphics2D g, BlockType type) {
        for (Point p : type.getPoints()) {
            drawBlock(g, 60 + p.x * BLOCK_WIDTH, 380 + (3 - p.y) * 20, getBlockColor(type));
        }
    }

    private Color getBoardCellColor(BoardCell boardCell) {
        if (boardCell.isEmpty()) {
            return Color.BLACK;
        }
        return getBlockColor(boardCell.getBlockType());
    }

    private Color getBlockColor(BlockType blockType) {
        switch (blockType) {
            case I:
                return Color.RED;
            case J:
                return Color.GRAY;
            case L:
                return Color.CYAN;
            case O:
                return Color.BLUE;
            case S:
                return Color.GREEN;
            default:
                return Color.MAGENTA;
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);
        g.drawRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);
    }
}
