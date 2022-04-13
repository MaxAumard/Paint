package graphics.menus.layer;

import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class LayerMenu extends java.awt.MenuBar{
    JMenuBar layerMenu= new JMenuBar();
    ShapesView sview;
    public LayerMenu(ShapesView sview){
        this.sview = sview;
        layerMenu.setBorder( new EtchedBorder());
        layerMenu.setBorderPainted(true);
        layerMenu.add(Box.createRigidArea(new Dimension(35,25)));
        layerMenu.setBackground(new Color(239, 239, 239));
    }


    public JMenuBar getMyJMenuBar(){
        return this.layerMenu;
    }

}
