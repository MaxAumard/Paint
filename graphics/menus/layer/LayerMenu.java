package graphics.menus.layer;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeDraftman;
import graphics.shapes.ui.ShapesController;
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

public class LayerMenu {
    JToolBar layerMenu;
    SCollection model;
    ShapesView cpsview;
    ShapeDraftman draftman;
    Graphics g;
    ArrayList<JButton> buttons;
    private Color bcolor = new Color(239, 239, 239);

    public LayerMenu(ShapesView sview){

        this.cpsview = new ShapesView((SCollection) sview.getModel());
        this.model = (SCollection) cpsview.getModel();
        this.draftman = cpsview.getDraftman();
        refreshLayer(sview);
    }
    public void refreshLayer(ShapesView sview){

        layerMenu = new JToolBar();
        layerMenu.setBorder( new EtchedBorder());
        layerMenu.setBorderPainted(true);
        layerMenu.setBackground(new Color(239, 239, 239));
        layerMenu.setLayout(new GridLayout(20,1));


        int count = 0;
        Iterator<Shape> shapeIterator = this.model.iterator();
        buttons = new ArrayList<>();
        while(shapeIterator.hasNext()) {
            count++;
            Shape s = shapeIterator.next();
            if (s.getBounds() != null && s.getClass() != SPoint.class) {
                int max = Math.max(s.getBounds().width+1,s.getBounds().height+1);
                BufferedImage bi = new BufferedImage(max, max, TYPE_INT_RGB);

                Point p = s.getLoc();
                if (s.getClass() == SCollection.class){
                    ((SCollection) s).setLocCollection();
                }
                else if(s.getClass() == SText.class){
                    s.setLoc(new Point(0,16));
                }
                else{
                    s.setLoc(new Point(0,0));
                }
                if (s.getClass() == STriangle.class) {
                    count--;
                    STriangle tri =(STriangle) s;
                    STriangle triZero = new STriangle(new Point(50,0),new Point(0,100),new Point(100,100),3);
                    ColorAttributes co = (ColorAttributes)tri.getAttributes("Color");
                    triZero.addAttributes(new ColorAttributes(co.stroked,co.filled,co.strokeColor,co.fillColor));
                    triZero.addAttributes(new SelectionAttributes());

                    bi = new BufferedImage(triZero.getBounds().width, triZero.getBounds().height, TYPE_INT_RGB);
                    g = bi.getGraphics();
                    g.setColor(bcolor);
                    g.fillRect(0, 0,100, 100);

                    draftman.setGraphics(g);
                    draftman.visitShape(triZero);

                }
                else {

                    g = bi.getGraphics();
                    g.setColor(bcolor);
                    g.fillRect(0, 0,max, max);
                    draftman.setGraphics(g);
                    draftman.visitShape(s);
                }
                if (s.getClass() != SCollection.class){s.setLoc(p);}
                JButton btn = new JButton(nameShape(s,count),new ImageIcon(new ImageIcon(bi).getImage().getScaledInstance(30,30,SCALE_SMOOTH)));
                btn.setPreferredSize(new Dimension(170, 40));
                btn.setFocusable(false);
                btn.setBorder(BorderFactory.createMatteBorder(
                        0, 0, 2, 0, Color.gray));

                //Action listener press button
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SelectionAttributes sa = (SelectionAttributes)s.getAttributes("Selected");
                        for (Shape s : model.collection) {
                            SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
                            sAtt.unselect();
                        }
                        sa.select();
                        sview.repaint();
                    }
                });

                buttons.add(btn);
                btn.setHorizontalAlignment(SwingConstants.LEFT);
                layerMenu.add(btn);
            }
        }
    }

    public String nameShape(Shape s, int count) {
        if (s.getClass() == SRectangle.class) {
            return count + ". Rectangle";
        } else if (s.getClass() == SCircle.class) {
            return count + ". Circle";
        } else if (s.getClass() == SText.class) {
            return count + ". Text";
        } else if (s.getClass() == STriangle.class) {
            return count + ". Triangle";
        } else if (s.getClass() == SImage.class) {
            return count + ". Image";
        } else if (s.getClass() == SCollection.class) {
            return count + ". Collection";
        }
        return null;
    }

    public JToolBar getMyJMenuBar(){
        return this.layerMenu;
    }
    public ArrayList<JButton> getButtons(){
        return this.buttons;
    }
    public void setBackgroundColor(Color c){
        this.bcolor = c;
    }
}
