package graphics.menus.layer;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeDraftman;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import static java.awt.Image.SCALE_SMOOTH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class LayerMenu extends java.awt.MenuBar implements MouseListener, KeyListener {
    JToolBar layerMenu= new JToolBar();
    SCollection model;
    ShapesView cpsview;
    ShapeDraftman draftman;

    public LayerMenu(ShapesView sview){
        this.cpsview = new ShapesView((SCollection) sview.getModel());
        this.model = (SCollection) cpsview.getModel();
        this.draftman = cpsview.getDraftman();
        layerMenu.setBorder( new EtchedBorder());
        layerMenu.setBorderPainted(true);
        layerMenu.setBackground(new Color(239, 239, 239));
        layerMenu.setLayout(new GridLayout(20,1));


        Iterator<Shape> shapeIterator = this.model.iterator();
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        while(shapeIterator.hasNext()) {
            Shape s = shapeIterator.next();
            if (s.getBounds() != null && s.getClass() != SPoint.class) {
                int max = Math.max(s.getBounds().width+1,s.getBounds().height+1);
                BufferedImage bi = new BufferedImage(max, max, TYPE_INT_RGB);

                Point p = s.getLoc();
                if (s.getClass() == SCollection.class){
                    ((SCollection) s).setLocCollection();
                }
                else{
                    s.setLoc(new Point(0,0));
                }
                if (s.getClass() == STriangle.class) {
                    STriangle tri =(STriangle) s;
                    STriangle triZero = new STriangle(new Point(50,0),new Point(0,100),new Point(100,100),3);
                    ColorAttributes co = (ColorAttributes)tri.getAttributes("Color");
                    triZero.addAttributes(new ColorAttributes(co.stroked,co.filled,co.strokeColor,co.fillColor));
                    triZero.addAttributes(new SelectionAttributes());

                    bi = new BufferedImage(triZero.getBounds().width, triZero.getBounds().height, TYPE_INT_RGB);
                    Graphics g = bi.getGraphics();
                    g.setColor(new Color(239, 239, 239));
                    g.fillRect(0, 0,100, 100);

                    draftman.setGraphics(g);
                    draftman.visitShape(triZero);

                }
                else {
                    if (s.getClass() == SText.class){
                        s.setLoc(new Point(0,16));
                    }

                    Graphics g = bi.getGraphics();
                    g.setColor(new Color(239, 239, 239));
                    g.fillRect(0, 0,max, max);
                    draftman.setGraphics(g);
                    draftman.visitShape(s);
                }
                if (s.getClass() != SCollection.class){s.setLoc(p);}
                JButton btn = new JButton(nameShape(s),new ImageIcon(new ImageIcon(bi).getImage().getScaledInstance(40,40,SCALE_SMOOTH)));
                buttons.add(btn);
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                layerMenu.add(btn);
                //TODO ACTION LISTENER ON BUTTONS ! en attendant bonne nuit
            }
        }
    }

    public String nameShape(Shape s) {
        if (s.getClass() == SRectangle.class) {
            return "Rectangle";
        } else if (s.getClass() == SCircle.class) {
            return "Circle";
        } else if (s.getClass() == SText.class) {
            return "Text";
        } else if (s.getClass() == STriangle.class) {
            return "Triangle";
        } else if (s.getClass() == SImage.class) {
            return "Image";
        } else if (s.getClass() == SCollection.class) {
            return "Collection";
        }
        return null;
    }

    public JToolBar getMyJMenuBar(){
        return this.layerMenu;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
