package graphics.menus.extensions;

import graphics.menus.toolBar.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorChooser {
        Color color = Color.BLACK;
        public ColorChooser(JButton ccButton){
                        JColorChooser colorChooser = new JColorChooser();
                        color = colorChooser.showDialog(null,"Color chooser", Color.black);
                        ccButton.setBackground(color);
        }
        public Color getColorChoosed(){
                return this.color;
        }
}
