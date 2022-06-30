package workflow;

import java.util.ArrayList;

import controller.BoardControl;
import model.Player;
import view.GameView;

/**
 * Class that contains the game logic
 */

public class Logic {
    private final GameView view;
    private final int calculatedTableWidth;
    private final ArrayList<Player> players;
    private String winnerPlayer;

    /**
     * @param view                 GameView panel. We can access every GUI which we need during the game
     * @param calculatedTableWidth Table size
     * @param players              Players
     */

    public Logic(GameView view, int calculatedTableWidth, ArrayList<Player> players) {
        this.view = view;
        this.calculatedTableWidth = calculatedTableWidth;
        this.players = players;
    }

    /**
     * Fight. The attacker attacks the defender they are rolling dices.
     * If the attacker wins and conquers the territory. We move every dice to the new territory if he wins except 1.
     * The dices of the defender player disappear. If the defender wins the dices of the attacker disappear except 1
     * There will be no change for the defender player
     *
     * @param attacker Attacker field controller
     * @param defender Defender field controller
     */

    public void fight(BoardControl attacker, BoardControl defender) {
        int defenderDicesOnThisField = defender.getModel().getNumberOfDices();
        int attackerDicesOnThisField = attacker.getModel().getNumberOfDices();

        int attackNumber = throwDices(attacker.getModel().getNumberOfDices()); //Score that the attacker rolled
        int defendNumber = throwDices(defender.getModel().getNumberOfDices()); //Score that the defender rolled

        System.out.println(attacker.getModel().getOwnerOfThisField().getColor().toString() + " attacked " + defender.getModel().getOwnerOfThisField().getColor().toString() + ". Result is:  "
                + attackNumber + " : " + defendNumber);

        view.setAttackerScore(attacker.getModel().getOwnerOfThisField().getColor().toString() + ": " + attackNumber);
        view.setDefenderScore(defender.getModel().getOwnerOfThisField().getColor().toString() + ": " + defendNumber);

        if (attackNumber > defendNumber) { //Attacker won
            attacker.getModel().getOwnerOfThisField().increaseTerritoriesNumber();
            defender.getModel().getOwnerOfThisField().decreaseTerritoriesNumber();

            System.out.println(attacker.getModel().getOwnerOfThisField().getNumberOfTerritories() + " " + defender.getModel().getOwnerOfThisField().getNumberOfTerritories());

            defender.getModel().getOwnerOfThisField().removeDices(defenderDicesOnThisField);
            defender.getModel().setOwnerOfThisField(attacker.getModel().getOwnerOfThisField());
            defender.getModel().setNumberOfDices(attackerDicesOnThisField - 1);
            attacker.getModel().setNumberOfDices(1);

            defender.getView().setBackgroundColor(attacker.getModel().getOwnerOfThisField().getColor());
            defender.getView().setDiceNumberText(attackerDicesOnThisField - 1);
        } else { //Defender won
            attacker.getModel().removeDices(attackerDicesOnThisField - 1);
            attacker.getModel().getOwnerOfThisField().removeDices(attackerDicesOnThisField - 1);
        }
        attacker.getView().setDiceNumberText(1);

        //End of the game if only 1 player alive.

        if (isGameEnded()) {
            view.showGameOverText(winnerPlayer);
        }
    }

    /**
     * Simulating computer attacks
     *
     * @param fields  Field controllers on the table
     * @param players Players who play the game
     */

    public void computersAttack(BoardControl[][] fields, ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {

            while (Randomizer.generateIntBetweenRange(1, 4) <= 3) {
                for (int row = 0; row < calculatedTableWidth; row++) {
                    for (int column = 0; column < calculatedTableWidth; column++) {
                        if (fields[row][column].getModel().getOwnerOfThisField() == players.get(i) && fields[row][column].getModel().getNumberOfDices() > 1) {
                            BoardControl attacker = fields[row][column];
                            BoardControl defender = selectFieldForAttack(row, column, fields);

                            if (defender != null) {
                                this.fight(attacker, defender);
                            }
                        }
                    }
                }
            }

            this.addAndRandomizeNewDicesForPlayer(players.get(i), fields);
        }
    }


    /**
     * @param row    Row where we select field
     * @param column Column where we select field
     * @param fields Controllers of the fields
     */

    private BoardControl selectFieldForAttack(int row, int column, BoardControl[][] fields) {
        for (int i = 0; i < calculatedTableWidth; i++) {
            for (int j = 0; j < calculatedTableWidth; j++) {
                if (isNeighBour(i, j, row, column) && fields[i][j].getModel().getOwnerOfThisField() != fields[row][column].getModel().getOwnerOfThisField()) {
                    return fields[i][j];
                }
            }
        }

        return null;
    }

    /**
     * Dice rolling simulation. We roll with dices that are on the field.
     * Sum the rolled values
     *
     * @param numberOfDices Number of dices
     * @return Sum of rolled values
     */

    private int throwDices(int numberOfDices) {
        int sumOfThrewDiceValues = 0;

        for (int i = 0; i < numberOfDices; i++) {
            sumOfThrewDiceValues += Randomizer.generateIntBetweenRange(1, 6);
        }

        return sumOfThrewDiceValues;
    }

    /**
     * @param tryingRowToSelect    The row of the field what the player try to select
     * @param tryingColumnToSelect The column of the field what the player try to select
     * @param selectedRow          The row of the field what the player selected
     * @param selectedColumn       The column of the field what the player selected
     * @return Is the field neighbour?
     */

    private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
        return Math.abs(tryingRowToSelect - selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) < 2;
    }

    /**
     * Randomly we add dices to the player who ended the turn. A kockák száma
     * The number of dices that we add to the fields calculated by: number of territories per player /2
     *
     * @param player Players
     * @param fields Controllers of the field
     */

    public void addAndRandomizeNewDicesForPlayer(Player player, BoardControl[][] fields) {
        int numberOfNewDices = player.getNumberOfTerritories() % 2 == 0
                ? player.getNumberOfTerritories() / 2
                : player.getNumberOfTerritories() / 2 + 1;

        System.out.println(numberOfNewDices + " dices added to the territories of the " + player.getColor().toString() + " player!");

        while (numberOfNewDices > 0) {
            int randomRow = Randomizer.generateIntBetweenRange(0, calculatedTableWidth - 1);
            int randomColumn = Randomizer.generateIntBetweenRange(0, calculatedTableWidth - 1);

            if (fields[randomRow][randomColumn].getModel().getOwnerOfThisField() == player) {
                fields[randomRow][randomColumn].getModel().addDices(1);
                fields[randomRow][randomColumn].getView().setDiceNumberText(fields[randomRow][randomColumn].getModel().getNumberOfDices());
                numberOfNewDices--;
            }
        }
    }

    /**
     * Is there more than one player who has territory?
     *
     * @return Does the game ended?
     */

    private boolean isGameEnded() {
        int numberOfPlayersWhoHaveTerritorries = 0;

        for (Player player : players) {
            if (player.getNumberOfTerritories() > 0) {
                numberOfPlayersWhoHaveTerritorries++;
                winnerPlayer = player.getColor().toString();
            }
        }

        return numberOfPlayersWhoHaveTerritorries == 1;
    }
}