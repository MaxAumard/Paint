package graphics.menus.toolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Button extends JButton {
    public Button(String pathIcon, ActionListener actionListener){
        super();
        setBorder(BorderFactory.createEmptyBorder());
        ImageIcon icon = new ImageIcon(new ImageIcon(pathIcon).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        this.setIcon(icon);


        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }

}
