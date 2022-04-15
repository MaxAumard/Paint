package graphics.menus.extensions;



import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;



public class Pipette implements MouseListener {
    Color colorPicked = Color.BLACK;
    ShapesView sview;
    ColorChooser cc;
    JButton colorChooserBtn;

    BufferedImage bi;

    public Pipette(ColorChooser cc, JButton colorChooserBtn, ShapesView sview) {

        this.cc = cc;
        this.colorChooserBtn = colorChooserBtn;
        this.sview = sview;
        this.bi = new BufferedImage(sview.getWidth(), sview.getHeight(), BufferedImage.TYPE_INT_RGB);

        this.sview.addMouseListener(this);
        ImageIcon im = new ImageIcon(new ImageIcon("icon/pipetteCursor.png").getImage().getScaledInstance(31,31, Image.SCALE_SMOOTH));
        this.sview.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(im.getImage(), new Point(2,im.getIconHeight() ), "pipette cursor"));

    }
    
    public Pipette(JButton colorChooserBtn){
    	this.colorPicked = colorChooserBtn.getBackground();
    }

    public Color getColorPicked() {
        return colorPicked;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Graphics2D g = bi.createGraphics();
        sview.paint(g);
        colorPicked = new Color(bi.getRGB(e.getX(),e.getY()));
        colorChooserBtn.setBackground(colorPicked);
        this.sview.setCursor(Cursor.getDefaultCursor());
        sview.removeMouseListener(this);
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
