package graphics.menus.extensions;


import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Pipette implements MouseListener {
    Color colorPicked = Color.BLACK;
    ShapesView sview;

    public Pipette(ColorChooser cc, ShapesView sview, ActionEvent e) {
        if (((JToggleButton) e.getSource()).isSelected()) {
            addMouseListener(this);

            this.sview = sview;
            sview.setCursor(Cursor.getDefaultCursor());
            cc.setColorChooseed(colorPicked);
            System.out.println("1 " + colorPicked);

        }
    }

    public Color getPixelColor(int x, int y) throws AWTException {
        Robot robot = new Robot();
        System.out.println("3 "+colorPicked);
        return robot.getPixelColor(x, y);

    }


    @Override
    public void mousePressed(MouseEvent e) {
        try {
            this.colorPicked = getPixelColor(e.getX(),e.getY());
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
        System.out.println("2 "+colorPicked);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
