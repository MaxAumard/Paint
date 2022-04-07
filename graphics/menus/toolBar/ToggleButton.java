package graphics.menus.toolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class ToggleButton extends JToggleButton {

    public ToggleButton(String pathIconNotSelected,String pathIconSelected, ActionListener actionListener) {
        super();
        setBorder(BorderFactory.createEmptyBorder());
        ImageIcon iconNotSelected = new ImageIcon();
        try {iconNotSelected = new ImageIcon((ImageIO.read(new File(pathIconNotSelected)).getScaledInstance(25, 25, 0)));
        } catch (IOException e) {e.printStackTrace();}
        this.setIcon(iconNotSelected);

        ImageIcon iconSelected = new ImageIcon();
        try {iconSelected = new ImageIcon((ImageIO.read(new File(pathIconSelected)).getScaledInstance(25, 25, 0)));
        } catch (IOException e) {e.printStackTrace();}
        this.setSelectedIcon(iconSelected);


        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }
}
