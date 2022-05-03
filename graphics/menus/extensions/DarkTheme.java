package graphics.menus.extensions;

import graphics.menus.layer.LayerMenu;
import graphics.menus.toolBar.ToggleButton;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class DarkTheme {

    public DarkTheme(ActionEvent e, JToolBar toolBar, JMenuBar menuBar, LayerMenu layerMenu, ShapesView sview, Map<String,Component> tbButtons) throws IOException {
        Component[] buttons = tbButtons.values().toArray(new Component[0]);
        String[] buttonsName = tbButtons.keySet().toArray(new String[0]);

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
            int count = 0;
            for (Component btn : buttons) {
                btn.setBackground(interfaceDarkColor);
                String path = "icon/"+buttonsName[count]+"Dark.png";
                if ((new File(path)).exists()){
                    ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
                    ((JButton) btn).setIcon(icon);
                }
                else if(buttonsName[count] == "ColorChooser2"){
                    ImageIcon icon = new ImageIcon(new ImageIcon("icon/ColorChooserDark.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
                    ((JButton) btn).setIcon(icon);
                }


                count++;
            }
            
            //layerMenu
            layerMenu.getMyJMenuBar().setBackground(interfaceDarkColor);
            layerMenu.setBackgroundColor(interfaceDarkColor);

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
            int count = 0;
            for (Component btn : buttons) {
                btn.setBackground(interfaceLightColor);
                String path = "icon/"+buttonsName[count]+".png";
                if ((new File(path)).exists()){
                    ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
                    ((JButton) btn).setIcon(icon);
                }
                else if(buttonsName[count] == "ColorChooser2"){
                    ImageIcon icon = new ImageIcon(new ImageIcon("icon/ColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
                    ((JButton) btn).setIcon(icon);
                }


                count++;
            }

            
            //layerMenu
            layerMenu.getMyJMenuBar().setBackground(interfaceLightColor);
            layerMenu.setBackgroundColor(interfaceLightColor);
        }
        layerMenu.refreshLayer(sview);
    }

}
