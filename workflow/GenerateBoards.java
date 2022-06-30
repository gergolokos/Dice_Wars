package workflow;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.Field;
import model.Player;
import controller.BoardControl;
import view.FieldView;

/**
 * Field generator class
 */

public class GenerateBoards {
    /**
     * Static method, table generation
     *
     * @param numberOfPlayers      Number of players
     * @param calculatedTableWidth Calculated table width
     * @param players              Players
     * @param gameView             Game panel
     * @return Matrix of the field controller
     */

    public static BoardControl[][] generate(int numberOfPlayers, int calculatedTableWidth, ArrayList<Player> players, JPanel gameView) {
        Map<Integer, Integer> availableFieldsPerPlayer = new HashMap<>();
        Map<Integer, Integer> availableDicesPerPlayer = new HashMap<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            availableFieldsPerPlayer.put(i, players.get(i).getNumberOfTerritories());
            availableDicesPerPlayer.put(i, players.get(i).getNumberOfDices());
        }

        BoardControl[][] boardControls = new BoardControl[calculatedTableWidth][calculatedTableWidth];
        gameView.setLayout(new GridLayout(calculatedTableWidth, calculatedTableWidth));

        for (int x = 0; x < calculatedTableWidth; x++) {
            for (int y = 0; y < calculatedTableWidth; y++) {
                int randomPlayerIndex = Randomizer.generateIntBetweenRange(0, players.size() - 1);
                int randomPlayerFieldNumber = availableFieldsPerPlayer.get(randomPlayerIndex);
                int randomPlayerDicesNumber = availableDicesPerPlayer.get(randomPlayerIndex);

                if (randomPlayerFieldNumber == 0) {
                    y--;
                    continue;
                }

                availableFieldsPerPlayer.put(randomPlayerIndex, --randomPlayerFieldNumber);
                int randomDiceNumberOnThisField = Math.min(8, Randomizer.generateIntBetweenRange(1, availableDicesPerPlayer.get(randomPlayerIndex) - availableFieldsPerPlayer.get(randomPlayerIndex)));
                availableDicesPerPlayer.put(randomPlayerIndex, randomPlayerDicesNumber - randomDiceNumberOnThisField);

                boardControls[x][y] = new BoardControl(new FieldView(), new Field(randomDiceNumberOnThisField, players.get(randomPlayerIndex)));
                gameView.add(boardControls[x][y].getView());
            }
        }

        return boardControls;
    }

    /**
     * Table size(height,width) creation according to the number of players and size of the table(S,M,L)
     *
     * @param nmbOfPlayers Number of players
     * @param tableSize    Selected table size(S,M,L)
     * @return Integer, the size of the table
     */


    public static int getTableSizeRelatedToNumberOfPlayers(int nmbOfPlayers, String tableSize) {
        int tableWidth = 0;

        switch (tableSize) {
            case "Small":
                tableWidth = nmbOfPlayers * 2;
                break;

            case "Medium":
                tableWidth = nmbOfPlayers * 3;
                break;

            case "Large":
                tableWidth = nmbOfPlayers * 4;
                break;

            default:
                break;
        }

        return tableWidth;
    }

}