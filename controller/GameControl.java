package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.Player;
import model.PlayerColor;
import view.FieldView;
import view.GameView;
import workflow.GenerateBoards;
import workflow.Logic;
import workflow.GeneratePlayers;

/**
 *
 * This class is created to control the game during the game
 * and that is where we place the events
 */

public class GameControl {
    private final int tableWitdh;
    private final GameView view;
    private final BoardControl[][] boards;
    private final ArrayList<Player> players;
    private int selectedFieldRow;
    private int selectedFieldColumn;
    private final Logic logic;

    /**
     * Responsible for initialization
     *
     * @param numberOfPlayers Number of players
     * @param sizeOfTable     Size of table
     */

    public GameControl(int numberOfPlayers, String sizeOfTable) {
        this.view = new GameView();
        this.tableWitdh = GenerateBoards.getTableSizeRelatedToNumberOfPlayers(numberOfPlayers, sizeOfTable);
        this.players = GeneratePlayers.createPlayers(numberOfPlayers, tableWitdh);
        this.logic = new Logic(view, tableWitdh, players);
        this.boards = GenerateBoards.generate(numberOfPlayers, tableWitdh, players, view.getGameTablePanel());

        listeners();
    }


    /**
     * This method is responsible for event handling, initializing. We have to add different listeners for every tiles.
     * Add event handling for the end turn button as well.
     */

    private void listeners() {
        for (int i = 0; i < tableWitdh; i++) {
            for (int j = 0; j < tableWitdh; j++) {
                boards[i][j].getView().putClientProperty("row", i);
                boards[i][j].getView().putClientProperty("column", j);
                boards[i][j].getView().addMouseListener(new MouseListener() {
                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        FieldView view = (FieldView) arg0.getSource();
                        int row = (Integer) view.getClientProperty("row");
                        int column = (Integer) view.getClientProperty("column");
                        BoardControl boardControl = boards[row][column];

                        if (boardControl.getModel().getOwnerOfThisField().getColor() == PlayerColor.USER && boardControl.getModel().getNumberOfDices() > 1) {
                            removeSelect();
                            boardControl.setSelected(true);
                            selectedFieldRow = row;
                            selectedFieldColumn = column;
                        } else if (isAnyFieldSelected() && boardControl.getModel().getOwnerOfThisField().getColor() != PlayerColor.USER) {
                            if (isNeighBour(row, column, selectedFieldRow, selectedFieldColumn)) {
                                boardControl.setSelected(true);
                                logic.fight(boards[selectedFieldRow][selectedFieldColumn], boardControl);
                                resetSelect();
                            }
                        }
                    }
                });
            }
        }

        view.getEndTurnButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logic.addAndRandomizeNewDicesForPlayer(players.get(0), boards);
                logic.computersAttack(boards, players);
            }
        });
    }

    /**
     * Selection remove from all the fields
     */

    private void removeSelect() {
        for (int i = 0; i < tableWitdh; i++) {
            for (int j = 0; j < tableWitdh; j++) {
                boards[i][j].setSelected(false);
            }
        }
    }

    /**
     * @return Is any field selected?
     */

    private boolean isAnyFieldSelected() {
        for (int i = 0; i < tableWitdh; i++) {
            for (int j = 0; j < tableWitdh; j++) {
                if (boards[i][j].isSelected())
                    return true;
            }
        }

        return false;
    }

    /**
     * @param tryingRowToSelect    Row of the field which we want to select from the field(where we press)
     * @param tryingColumnToSelect Column of the field which we want to select from the field(where we press)
     * @param selectedRow          Row of an earlier selected field
     * @param selectedColumn       Column of an earlier selected field
     * @return Are we trying to attack a neighbour? 
     */

    private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
        return Math.abs(tryingRowToSelect - selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) < 2;
    }

    /**
     * Selection delete (Deselection)
     */

    private void resetSelect() {
        selectedFieldColumn = -1;
        selectedFieldRow = -1;

        removeSelect();
    }
}