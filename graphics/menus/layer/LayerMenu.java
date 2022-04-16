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
import java.awt.image.BufferedImage;
import java.util.Iterator;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class LayerMenu extends java.awt.MenuBar{
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
        layerMenu.setLayout(new GridLayout(5,2));


        Iterator<Shape> shapeIterator = this.model.iterator();

        while(shapeIterator.hasNext()) {
            Shape s = shapeIterator.next();
            if (s.getBounds() != null && s.getClass() != SPoint.class) {
                BufferedImage bi = new BufferedImage(s.getBounds().width+1, s.getBounds().height+1, TYPE_INT_RGB);
                System.out.println(s.getClass().getName().toString());
                Point p = s.getLoc();
                if (s.getClass() == SCollection.class){
                    ((SCollection) s).setLocCollection();
                }
                else{
                    s.setLoc(new Point(0,0));
                }
                if (s.getClass() == STriangle.class) {
                    STriangle tri =(STriangle) s;
                    STriangle triZero = new STriangle(new Point(tri.p1.x-tri.p2.x,0),new Point(0,tri.p2.y-tri.p1.y),new Point(tri.p3.x-tri.p2.x,tri.p3.y-tri.p1.y),3);
                    triZero.addAttributes(new ColorAttributes(true,true,Color.DARK_GRAY,Color.PINK));
                    triZero.addAttributes(new SelectionAttributes());

                    bi = new BufferedImage(triZero.getBounds().width, triZero.getBounds().height, TYPE_INT_RGB);
                    Graphics g = bi.getGraphics();
                    draftman.setGraphics(g);
                    draftman.visitShape(triZero);
                }
                else {
                    if (s.getClass() == SText.class){
                        s.setLoc(new Point(0,16));
                    }

                    Graphics g = bi.getGraphics();
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, s.getBounds().width, s.getBounds().height);
                    draftman.setGraphics(g);
                    draftman.visitShape(s);
                }
                s.setLoc(p);
                layerMenu.add(new JButton(s.getClass().getName().toString(),new ImageIcon(bi)));
            }
        }
    }

    public JToolBar getMyJMenuBar(){
        return this.layerMenu;
    }


}
