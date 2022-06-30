import view.SettingsPanel;

/**
 * Main class. Start point of the application
 */

public class Main {

    /**
     * @param args args
     */
    public static void main(String[] args) {
        SettingsPanel settings = SettingsPanel.instance();
        settings.startApplicationSettings();
    }
}