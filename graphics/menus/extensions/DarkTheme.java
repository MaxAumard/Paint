package graphics.menus.extensions;

import graphics.menus.toolBar.Button;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

public class DarkTheme {

    public DarkTheme(ActionEvent e, JToolBar toolBar, JMenuBar menuBar, ShapesView sview, Collection<Button> buttons){
        Iterator<Button> iterator = buttons.iterator();

        if (((JToggleButton)e.getSource()).isSelected()){ // Darkmode
            Color interfaceDarkColor = new Color(31,31,31);

            //toolbar color
            toolBar.setBackground(interfaceDarkColor);

            //menuBar color
            menuBar.setBackground(interfaceDarkColor);

            //TODO ITEM IN MENUBAR
            int menu = 1;
            while (menuBar.getMenu(menu)!= null){
                menuBar.getMenu(menu).setForeground(Color.WHITE);
                menu++;
            }

            // Drawing area color
            sview.setBackground(Color.darkGray);

            while (iterator.hasNext()) {
                iterator.next().setBackground(interfaceDarkColor);
            }
        }
        else{
            Color interfaceLightColor = new Color(239, 239, 239);
            Font fontMenu = new Font("Sans Serif", Font.PLAIN, 15);

            //toolbar color
            toolBar.setBackground(interfaceLightColor);

            //menubar color
            menuBar.setBackground(interfaceLightColor);

            //TODO ITEM IN MENUBAR
            int menu = 1;
            while (menuBar.getMenu(menu)!= null){
                menuBar.getMenu(menu).setForeground(Color.black);
                menu++;
            }
            sview.setBackground(Color.white);

            while (iterator.hasNext()) {
                iterator.next().setBackground(interfaceLightColor);
            }
        }
    }
}
