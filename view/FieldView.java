package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerColor;

/**
 * Class for the GUI parts for field
 */

public class FieldView extends JPanel {
    private final JLabel diceNumberText;
    private Color defaultColor;

    public FieldView() {
        setLayout(new GridBagLayout());
        diceNumberText = new JLabel();
        diceNumberText.setFont(new Font("Sans-serif", Font.BOLD, 24));
        diceNumberText.setForeground(Color.WHITE);
        add(diceNumberText);
        setBorder(BorderFactory.createLineBorder(Color.white));
    }

    /**
     * Shows the number of dices on the field(on GUI)
     *
     * @param diceNumber Number of dices
     */

    public void setDiceNumberText(int diceNumber) {
        diceNumberText.setText(Integer.toString(diceNumber));
    }

    /**
     * Colorfill the fields according to the color of the player
     *
     * @param COLOR Color of the player
     */

    public void setBackgroundColor(PlayerColor COLOR) {
        switch (COLOR) {
            case USER:
                setBackground(Color.BLUE);
                defaultColor = Color.BLUE;
                break;

            case RED:
                setBackground(Color.RED);
                defaultColor = Color.RED;
                break;

            case GREEN:
                setBackground(Color.GREEN);
                defaultColor = Color.GREEN;
                break;

            case PINK:
                setBackground(Color.PINK);
                defaultColor = Color.PINK;
                break;

            case YELLOW:
                setBackground(Color.YELLOW);
                defaultColor = Color.YELLOW;
                break;

            default:
                break;
        }
    }

    /**
     * Background reset to default
     */

    public void resetBackgroundColor() {
        setBackground(defaultColor);
    }

    /**
     * Sets the background if the field is selected (when we want to attack with this field)
     */

    public void highlightBackgroundColorForSelect() {
        setBackground(Color.DARK_GRAY);
    }
}