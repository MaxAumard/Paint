package graphics.menus.toolBar;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Button extends JButton {
	public Button(String pathIcon, ActionListener actionListener){
        super();
        setBorder(BorderFactory.createEmptyBorder());
        ImageIcon icon = new ImageIcon(new ImageIcon(pathIcon).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        this.setIcon(icon);

        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }
	
	public Button(String pathIcon,String darkPathIcon, ActionListener actionListener){
        super();
        setBorder(BorderFactory.createEmptyBorder());
        ImageIcon icon = new ImageIcon(new ImageIcon(pathIcon).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        this.setIcon(icon);


        setMargin(new Insets(-2,-2,-1,-1));
        addActionListener(actionListener);
    }


}
