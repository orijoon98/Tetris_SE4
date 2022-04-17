package component;

import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class NormalGameOver extends Canvas {

    public Frame normalGameOverFrame;
    public Panel normalGameOverPanel, titlePanel, buttonPanel, contentPanel;
    public TextField textField;
    public int score, rank = 0;

    public NormalGameOver(Home home) {
        normalGameOverFrame = new Frame("Seoultech SE4 Tetris");
        normalGameOverFrame.setSize(800, 600);
        normalGameOverFrame.setResizable(false);
        normalGameOverFrame.setLayout(null);
        normalGameOverFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        normalGameOverPanel = new Panel();
        normalGameOverPanel.setSize(800, 600);
        normalGameOverPanel.setBackground(Color.black);
        normalGameOverPanel.setLayout(null);
        normalGameOverPanel.setFont(new Font("Dialog", Font.PLAIN, 16));

        titlePanel = new Panel();
        titlePanel.setBounds(250, 100, 300, 80);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, 50));

        Label title = new Label("Game Over");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(150, 470, 500, 50);
        buttonPanel.setLayout(new GridLayout(1, 1));

        Button next = new Button("Continue");

        buttonPanel.add(next);

        normalGameOverPanel.add(titlePanel);
        normalGameOverPanel.add(buttonPanel);

        normalGameOverFrame.add(normalGameOverPanel);

        normalGameOverFrame.setVisible(false);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreBoard scoreBoard = home.scoreBoardGUI;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", textField.getText());
                    jsonObject.put("score", score);
                    scoreBoard.postTopTen("normal", jsonObject);
                    List<ScoreBoard.Scores> scoresList = scoreBoard.getTopTen("normal");
                    for (int i = 0; i < scoresList.size(); i++) {
                        if (scoresList.get(i).getName().equals(textField.getText()) && scoresList.get(i).getScore() == score) {
                            rank = i + 1;
                            break;
                        }
                    }
                } catch (Exception ne) {}
                ScoreBoardNormal scoreBoardNormal = home.scoreBoardGUI.scoreBoardNormalGUI;
                scoreBoard.prepareScoreBoardNormalTable(rank);
                scoreBoardNormal.scoreBoardNormalFrame.setVisible(true);
                normalGameOverFrame.setVisible(false);
                scoreBoardNormal.scoreBoardNormalFrame.requestFocus();
                normalGameOverPanel.remove(contentPanel);
            }
        });
    }
}
