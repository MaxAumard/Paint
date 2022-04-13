package graphics.menus.layer;

import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;

public class LayerMenu extends java.awt.MenuBar{
    JMenuBar layerMenu= new JMenuBar();
    ShapesView sview;
    public LayerMenu(ShapesView sview){
        this.sview = sview;


    }


    public JMenuBar getMyJMenuBar(){
        return this.layerMenu;
    }

}
