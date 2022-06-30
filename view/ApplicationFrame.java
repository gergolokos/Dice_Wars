package view;

import javax.swing.*;
import java.awt.*;

/**
 * Application creation class
 */

public class ApplicationFrame extends JFrame {
    private static ApplicationFrame instance = null;

    private ApplicationFrame() {
    }

    /**
     * @return Return an instace of the Frame if it is created. Else this creates and returns the frame
     */

    static public ApplicationFrame instance() {
        if (instance == null) {
            instance = new ApplicationFrame();
        }

        return instance;
    }

    /**
     * Frame settings
     */

    public void showApplicationFrame() {
        setSize(1080, 720);
        setTitle("Dice Wars");
        setLocation(425,150);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Gery\\Desktop\\Egyetem\\Kötprog\\Dice.png");
        setIconImage(icon);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void finalize() {
        instance = null;
    }
}