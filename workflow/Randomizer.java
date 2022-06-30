package workflow;

import java.util.Random;

public class Randomizer {
    /**
     * Randomizer from a min to max value
     *
     * @param min min interval
     * @param max max interval
     * @return Random number between min and max interval
     */

    public static int generateIntBetweenRange(int min, int max) {
        if (max < min) {
            max = min;
        }

        Random random = new Random();

        return random.nextInt(max - min + 1) + min;
    }
}