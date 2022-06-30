package model;

/**
 * Player class. Represent every player with the instance of this class during the game
 */

public class Player {
    private PlayerColor color;
    private int numberOfTerritories;
    private int numberOfDices;

    /**
     * @param color               Color of the player (This where we can color the fields that the player owns later)
     * @param numberOfTerritories Number of Territories
     * @param numberOfDices       Number of dices
     */

    public Player(PlayerColor color, int numberOfTerritories, int numberOfDices) {
        super();
        this.color = color;
        this.numberOfTerritories = numberOfTerritories;
        this.numberOfDices = numberOfDices;
    }

    /**
     * @return Gets the color of the player
     */

    public PlayerColor getColor() {
        return color;
    }

    /**
     * Sets the color of the player
     *
     * @param color Color or the player
     */

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    /**
     * @return Number of territories
     */

    public int getNumberOfTerritories() {
        return numberOfTerritories;
    }

    /**
     * Sets the number of territories
     *
     * @param numberOfTerritories Number of territories
     */

    public void setNumberOfTerritories(int numberOfTerritories) {
        this.numberOfTerritories = numberOfTerritories;
    }

    /**
     * @return Number of dices
     */

    public int getNumberOfDices() {
        return numberOfDices;
    }

    /**
     * Sets the number of dices
     *
     * @param numberOfDices Number of dices
     */
    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    /**
     * Increase the current number of dices
     *
     * @param dices number of dices
     */

    public void addDices(int dices) {
        this.numberOfDices += dices;
    }

    /**
     * Decrease the current number of dices
     *
     * @param dices number of dices
     */

    public void removeDices(int dices) {
        this.numberOfDices -= dices;
    }

    /**
     * Increase the current number of territories
     */

    public void increaseTerritoriesNumber() {
        this.numberOfTerritories += 1;
    }

    /**
     * Increase the current number of territories
     */

    public void decreaseTerritoriesNumber() {
        this.numberOfTerritories -= 1;
    }

    @Override
    public String toString() {
        return "Player [color=" + color + ", numberOfTerritories="
                + numberOfTerritories + ", numberOfDices=" + numberOfDices
                + "]";
    }
}