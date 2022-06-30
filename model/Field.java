package model;

/**
 * Field class to store the datas from the field
 */

public class Field {
    private int numberOfDices;
    private Player ownerOfThisField;

    /**
     * @param numberOfDices     Number of Dices on the field
     * @param ownerOfThisField Owner of the field, (aka the player)
     */

    public Field(int numberOfDices, Player ownerOfThisField) {
        super();
        this.numberOfDices = numberOfDices;
        this.ownerOfThisField = ownerOfThisField;
    }

    /**
     * @return Gets the number of dices on the field
     */

    public int getNumberOfDices() {
        return numberOfDices;
    }

    /**
     * Sets the number of dices on the field
     *
     * @param numberOfDices Number of Dices
     */

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    /**
     * @return Gets the player who is on the territory
     */

    public Player getOwnerOfThisField() {
        return ownerOfThisField;
    }

    /**
     * @param ownerOfThisField Owner of the field (aka the player)
     */

    public void setOwnerOfThisField(Player ownerOfThisField) {
        this.ownerOfThisField = ownerOfThisField;
    }

    /**
     * Increase the number of dices
     *
     * @param dices number of dices
     */

    public void addDices(int dices) {
        this.numberOfDices += dices;
    }

    /**
     * Decrease the number of dices
     *
     * @param dices number of dices
     */

    public void removeDices(int dices) {
        this.numberOfDices -= dices;
    }

    @Override
    public String toString() {
        return "Field [numberOfDices=" + numberOfDices
                + ", ownnerOfThisField=" + ownerOfThisField + "]";
    }
}