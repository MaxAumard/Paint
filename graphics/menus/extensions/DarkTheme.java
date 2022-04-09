package graphics.menus.extensions;

import graphics.menus.toolBar.Button;
import graphics.menus.toolBar.ToggleButton;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

public class DarkTheme {

    public DarkTheme(ActionEvent e, JToolBar toolBar, JMenuBar menuBar, ShapesView sview, Collection<ToggleButton> buttons, JButton cc){
        Iterator<ToggleButton> iterator = buttons.iterator();

        // Darkmode
        if (((JToggleButton)e.getSource()).isSelected()){
            Color interfaceDarkColor = new Color(31,31,31);

            //toolbar color
            toolBar.setBackground(interfaceDarkColor);

            //menuBar color
            menuBar.setBackground(interfaceDarkColor);

            //item in menuBar
            int menu = 1;
            while (menuBar.getMenu(menu)!= null){
                menuBar.getMenu(menu).setForeground(Color.WHITE);
                menu++;
            }

            // Drawing area color
            sview.setBackground(Color.darkGray);

            //buttons color
            while (iterator.hasNext()) {
                iterator.next().setBackground(interfaceDarkColor);
            }

            //colorchooser picture
            ImageIcon icon = new ImageIcon(new ImageIcon("icon/darkColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
            cc.setIcon(icon);

        }
        //LightMode
        else{
            Color interfaceLightColor = new Color(239, 239, 239);
            Font fontMenu = new Font("Sans Serif", Font.PLAIN, 15);

            //toolbar color
            toolBar.setBackground(interfaceLightColor);

            //menubar color
            menuBar.setBackground(interfaceLightColor);

            //item in menubar
            int menu = 1;
            while (menuBar.getMenu(menu)!= null){
                menuBar.getMenu(menu).setForeground(Color.black);
                menu++;
            }

            // Drawing area color
            sview.setBackground(Color.white);

            //buttons color
            while (iterator.hasNext()) {
                iterator.next().setBackground(interfaceLightColor);
            }

            //colorchooser picture
            ImageIcon icon = new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
            cc.setIcon(icon);
        }
    }
}
