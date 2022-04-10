package graphics.menus.extensions;


import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Pipette implements MouseListener {
    Color colorPicked = Color.BLACK;
    ShapesView sview;
    ColorChooser cc;
    JButton colorChooserBtn;


    public Pipette(ColorChooser cc, JButton colorChooserBtn, ShapesView sview) {

        this.cc = cc;
        this.colorChooserBtn = colorChooserBtn;
        this.sview = sview;

        this.sview.addMouseListener(this);
        this.sview.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("icon/pipetteCursor.png").getImage(),new Point(0,25),"pipette cursor"));
    }

    public Color getPixelColor(int x, int y) throws AWTException {

        Robot robot = new Robot();

        // The pixel color information at 20, 20
        Color color = robot.getPixelColor(100, 100);

        return color;
    }


    @Override
    public void mousePressed(MouseEvent evt){
        try {
            this.colorPicked = getPixelColor(evt.getX(),evt.getY());
        } catch (AWTException ex) {}
        cc.setColorChooseed(colorPicked);
        colorChooserBtn.setBackground(colorPicked);
        System.out.println("2 "+colorPicked);
        this.sview.setCursor(Cursor.getDefaultCursor());
        sview.removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent evt) {

    }
    @Override
    public void mouseReleased(MouseEvent evt) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
