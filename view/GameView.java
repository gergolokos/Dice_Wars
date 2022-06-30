package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class which contains the game panel,the fields and the necessary texts for the current score, plus the end turn button
 */

public class GameView extends JPanel {
    private final JPanel gameTable;
    private JButton endTurnButton;
    private final JLabel attackerScore;
    private final JLabel defenderScore;

    /**
     * @return Returns the defender score label
     */

    public JLabel getDefenderScore() {
        return defenderScore;
    }

    /**
     * Sets the score for the defender player on the field (in JLabel)
     *
     * @param score Defender score
     */

    public void setDefenderScore(String score) {
        this.defenderScore.setText(score);
    }

    /**
     * @return Returns the attacker score label
     */

    public JLabel getAttackerScore() {
        return attackerScore;
    }

    /**
     * @param score Sets the attacker score
     */

    public void setAttackerScore(String score) {
        this.attackerScore.setText(score);
    }

    public GameView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        gameTable = new JPanel(new GridBagLayout());
        JPanel footer = new JPanel(new FlowLayout());
        gameTable.setPreferredSize(new Dimension(600, 550));
        endTurnButton = new JButton("Next Turn");


        add(gameTable);
        attackerScore = new JLabel("Attacker: ");
        defenderScore = new JLabel("Defender: ");
        footer.add(attackerScore);
        footer.add(defenderScore);
        footer.add(endTurnButton);
        add(footer);
        ApplicationFrame.instance().add(this);
    }

    /**
     * @return Returns the end turn button
     */

    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    /**
     * @param endTurnButton Sets the button
     */

    public void setEndTurnButton(JButton endTurnButton) {
        this.endTurnButton = endTurnButton;
    }

    /**
     * @return Return the table panel of the game
     */

    public JPanel getGameTablePanel() {
        return this.gameTable;
    }

    /**
     * The game has ended and we say it to the player too.
     *
     * @param winnerPlayer Name of the winner player (ID from the color ->PlayerColor from enum)
     */

    public void showGameOverText(String winnerPlayer) {
        JPanel framePanel = (JPanel) ApplicationFrame.instance().getContentPane();
        framePanel.remove(this);
        framePanel.revalidate();
        framePanel.repaint();

        JPanel panel = new JPanel(new GridBagLayout());
        JLabel gameOverLabel = new JLabel("Game Over!!! " + winnerPlayer + " has won the game");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 22));
        panel.add(gameOverLabel);
        framePanel.add(panel);
    }
}