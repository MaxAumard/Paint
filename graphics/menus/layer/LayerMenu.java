package graphics.menus.layer;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeDraftman;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
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
    private Color fcolor = Color.black;

    public LayerMenu(ShapesView sview){

        this.cpsview = new ShapesView((SCollection) sview.getModel());
        this.model = (SCollection) cpsview.getModel();
        this.draftman = cpsview.getDraftman();
        layerMenu = new JToolBar();
        layerMenu.setBorder(BorderFactory.createMatteBorder(5,0,0,0, bcolor));
        layerMenu.setBorderPainted(true);
        layerMenu.setBackground(bcolor);
        layerMenu.setForeground(fcolor);
        layerMenu.setLayout(new GridLayout(20,1));
        refreshLayer(sview);
    }

    public void refreshLayer(ShapesView sview){

        if (buttons != null) {
            for (JButton button : buttons) {
                this.getMyJMenuBar().remove(button);
            }
        }

        buttons = new ArrayList<>();


        printbuttonsarray(buttons);

        int count = 0;
        Iterator<Shape> shapeIterator = ((SCollection)sview.getModel()).iterator();


        while(shapeIterator.hasNext()) {

            Shape s = shapeIterator.next();

            if (s.getBounds() != null && s.getClass() != SPoint.class) {
                count++;
                int max = Math.max(s.getBounds().width+1,s.getBounds().height+1);
                BufferedImage bi = new BufferedImage(max, max, TYPE_INT_RGB);

                Point p = s.getLoc();
                if (s.getClass() == SCollection.class){
                    ((SCollection) s).setLocCollectionZero();
                }
                else if(s.getClass() == SText.class){
                    s.setLoc(new Point(0,16));
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
                    displayBackgroundColor(bi,max);
                    draftman.visitShape(triZero);
                }
                else {

                    displayBackgroundColor(bi,max);
                    draftman.visitShape(s);
                }
                if (s.getClass() != SCollection.class){s.setLoc(p);}
                else {((SCollection) s).setLocCollection(p.x,p.y);}


                JButton btn = new JButton(nameShape(s,count),new ImageIcon(new ImageIcon(bi).getImage().getScaledInstance(30,30,SCALE_SMOOTH)));
                btn.setVisible(true);
                btn.setPreferredSize(new Dimension(170, 40));
                btn.setFocusable(false);
                btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
                btn.setBackground(bcolor);
                btn.setForeground(fcolor);

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
        JButton btndefault = new JButton("");
        btndefault.setVisible(false);
        layerMenu.add(btndefault);
        buttons.add(btndefault);
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
        if (c.getGreen() > 200 && c.getGreen() > 200 && c.getRed() > 200 &&c.getBlue() > 200 ){
            this.fcolor = Color.black;
        }
        else{
            this.fcolor = Color.white;
        }
    }

    public void displayBackgroundColor(BufferedImage bi, int max){
        g = bi.getGraphics();
        g.setColor(bcolor);
        g.fillRect(0, 0,max, max);
        draftman.setGraphics(g);
    }

    public void printbuttonsarray(ArrayList btns){
        for (Object btn : btns){
            System.out.println(btn.toString());
        }
    }
}
