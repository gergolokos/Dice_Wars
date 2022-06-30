package controller;

import model.Field;
import model.PlayerColor;
import view.FieldView;


/**
 * BoardControl class which is responsible for the tiles on the field.
 * The application is based on MVC so we can access the datas (FieldBean) through the FieldController and we can acces the UI too (FieldView)
 */

public class BoardControl {
    private FieldView view;
    private Field model;
    private boolean isSelected = false;

    /**
     * Constructor. Initialize and build up the GUI.
     *  @param boardView Responsible for the GUI
     * @param model Datas for the fields
     */

    public BoardControl(FieldView boardView, Field model) {
        super();
        this.view = boardView;
        this.model = model;

        this.initGUI();
    }

    /**
     * Builds the field GUI up.
     */

    private void initGUI() {
        setBackgroundColor(model.getOwnerOfThisField().getColor());
        setDiceNumberText(model.getNumberOfDices());
    }

    /**
     * @return Returns the field view
     */

    public FieldView getView() {
        return view;
    }

    /**
     * @param view Sets the view
     */

    public void setView(FieldView view) {
        this.view = view;
    }

    /**
     * @return Returns the datas
     */

    public Field getModel() {
        return model;
    }

    /**
     * @param model Sets the modell
     */

    public void setModel(Field model) {
        this.model = model;
    }

    /**
     * Sets the color
     *
     * @param COLOR What color does the player have
     */

    public void setBackgroundColor(PlayerColor COLOR) {
        view.setBackgroundColor(COLOR);
    }

    /**
     * Sets the number of dices on the fields
     *
     * @param diceNumber Number of dices
     */

    public void setDiceNumberText(int diceNumber) {
        view.setDiceNumberText(diceNumber);
    }

    /**
     * @return Is the fields selected?
     */

    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Field selection method
     *
     * @param isSelected Select
     */

    public void setSelected(boolean isSelected) {
        if (!isSelected) {
            view.resetBackgroundColor();
        } else {
            view.highlightBackgroundColorForSelect();
        }

        this.isSelected = isSelected;
    }


}