package graphics.menus.toolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class ToggleButton extends JToggleButton {

    public ToggleButton(String pathIconNotSelected,String pathIconSelected, ActionListener actionListener) {
        super();
        setBorder(BorderFactory.createEmptyBorder());

        //icon non selected
        ImageIcon iconNotSelected = new ImageIcon(new ImageIcon(pathIconNotSelected).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        this.setIcon(iconNotSelected);

        // icon selected
        ImageIcon iconSelected = new ImageIcon(new ImageIcon(pathIconSelected).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        this.setSelectedIcon(iconSelected);


        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }
}
