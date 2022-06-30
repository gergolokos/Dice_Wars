package workflow;

import java.util.ArrayList;

import model.Player;
import model.PlayerColor;

public class GeneratePlayers {
    /**
     * Player generating static method
     *
     * @param nmbOfPlayers How many players we want?
     * @param tableWidth   Size of the table
     * @return Plyers
     */

    public static ArrayList<Player> createPlayers(int nmbOfPlayers, int tableWidth) {
        int numberOfTerritoriesPerPlayer = (int) (Math.pow(tableWidth, 2) / nmbOfPlayers);
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < nmbOfPlayers; i++) {
            players.add(new Player(PlayerColor.values()[i], numberOfTerritoriesPerPlayer, numberOfTerritoriesPerPlayer * 3));
        }

        return players;
    }
}