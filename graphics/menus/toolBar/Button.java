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
        ImageIcon icon= new ImageIcon();
        try {icon = new ImageIcon((ImageIO.read(new File(pathIcon)).getScaledInstance(25,25,0)));} catch (IOException e) {}
        this.setIcon(icon);


        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }

}
