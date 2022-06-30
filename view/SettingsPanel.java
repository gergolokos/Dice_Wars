package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameControl;

/**
 * Single class to initialize the Game
 */

public class SettingsPanel extends JPanel {
    private static SettingsPanel instance = null;
    private final LayoutManager gameSettingsLayout;
    private final JLabel playersNumberText;
    private final JLabel defaultColorText;
    private final JButton[] settingsButtons;
    private final JPanel buttonContainer;
    private final JPanel tableSizeContainer;
    private final JComboBox<String> tableSizeCb;
    private final JLabel tableSizeLabel;

    private final ApplicationFrame frameInstance;

    private SettingsPanel() {
        frameInstance = ApplicationFrame.instance();

        gameSettingsLayout = new GridLayout(4, 1, 20, 20);
        playersNumberText = new JLabel("Select the number of players", JLabel.CENTER);
        playersNumberText.setFont(new Font("Sans-serif", Font.BOLD, 22));
        playersNumberText.setHorizontalTextPosition(JLabel.LEADING);
        defaultColorText = new JLabel("(The default user color is blue)");
        defaultColorText.setFont(new Font("Sans-serif", Font.CENTER_BASELINE, 12));
        defaultColorText.setHorizontalAlignment(JLabel.CENTER);
        settingsButtons = new JButton[4];
        tableSizeContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 180, 0));
        String[] tableSizesText = {"Small", "Medium", "Large"};
        tableSizeCb = new JComboBox<>(tableSizesText);
        tableSizeLabel = new JLabel("Choose table size: ");
        tableSizeLabel.setFont(new Font("Sans-serif", Font.CENTER_BASELINE, 16));
        buttonContainer = new JPanel();
    }

    public static SettingsPanel instance() {
        if (instance == null) {
            instance = new SettingsPanel();
        }

        return instance;
    }

    /**
     * Settings panel initialization  (where we can select the table size and the player number)
     */

    private void initSettingsPanel() {
        setLayout(gameSettingsLayout);
        buttonContainer.setLayout(new FlowLayout());

        for (int i = 0; i <= 3; i++) {
            settingsButtons[i] = new JButton(i + 2 + " players");
            settingsButtons[i].setActionCommand(Integer.toString(i + 2));
            settingsButtons[i].addActionListener(e -> {
                int numberOfPlayers = Integer.parseInt(e.getActionCommand());
                removePanel();
                new GameControl(numberOfPlayers, (String) tableSizeCb.getSelectedItem());
            });

            buttonContainer.add(settingsButtons[i]);
        }

        tableSizeContainer.add(tableSizeLabel);
        tableSizeContainer.add(tableSizeCb);

        add(playersNumberText);
        add(buttonContainer);
        add(tableSizeContainer);
        add(defaultColorText);
    }

    /**
     * Add panel to the frame
     */

    public void startApplicationSettings() {
        initSettingsPanel();

        frameInstance.showApplicationFrame();
        frameInstance.add(this);
    }

    /**
     * Delete Panel from the frame
     */

    private void removePanel() {
        JPanel framePanel = (JPanel) frameInstance.getContentPane();
        framePanel.remove(this);
        framePanel.revalidate();
        framePanel.repaint();
    }

    public void finalize() {
        instance = null;
    }
}