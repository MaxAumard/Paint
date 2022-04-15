package graphics.menus.extensions;

import graphics.menus.toolBar.ToolBar;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import javax.swing.JColorChooser;


public class ColorChooser {
    Color color;

    public Color getColorChoosed() {
        return this.color;
    }

    public void setColorChooseed(Color color) {
        this.color = color;

    }



    public void displayColorChooser(JButton ccButton) {

        JColorChooser cc = new JColorChooser();
        AbstractColorChooserPanel[] panels = cc.getChooserPanels();

        JPanel p = new JPanel();

        p.add(panels[3]);
        JPanel gui = new JPanel(new BorderLayout(2,2));
        gui.add(p, BorderLayout.CENTER);

        gui.add(cc.getPreviewPanel(), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, gui, "Color chooser",JOptionPane.PLAIN_MESSAGE);
        Color newColor = cc.getColor();
        if (newColor!=null){
            color = newColor;
        }
        ccButton.setBackground(this.color);

    }
}