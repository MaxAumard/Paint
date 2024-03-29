package graphics.menus.extensions;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Bucket implements MouseListener {
    Color oldColor,newColor;
    int x,y;
    ShapesView sview;

    public Bucket(Color color, ShapesView sview)  {
        this.sview = sview;

        this.newColor = color;
        this.sview.addMouseListener(this);
        ImageIcon im = new ImageIcon(new ImageIcon("icon/bucket2.png").getImage().getScaledInstance(31,31, Image.SCALE_SMOOTH));
        this.sview.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(im.getImage(), new Point(im.getIconWidth()-2,2*im.getIconHeight()/3 ), "pipette cursor"));


        //setPixelColor(10,10, Color.BLACK);
    }

    public void flood(int x, int y, Color oldColor,Color newColor){
        if (x<0) return;
        if (y<0) return;
        if(x>= sview.getWidth()) return;
        if (y>= sview.getHeight()) return;
        if (getPixelColor(x, y) != oldColor) return;
        setPixelColor(x,y,newColor);

        flood(x-1,y, oldColor,newColor);
        flood(x+1,y, oldColor,newColor);
        flood(x,y-1, oldColor,newColor);
        flood(x,y+1, oldColor,newColor);
    }

    public Color getPixelColor(int x, int y){
        BufferedImage image = new BufferedImage(sview.getWidth(), sview.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        return new Color(image.getRGB(x,y));
    }

    public void setPixelColor(int x, int y, Color color) {
        Graphics2D g2d = (Graphics2D) new BufferedImage(1,1,1).getGraphics().create();
        SRectangle rectSelection = new SRectangle(new Point(x,y),1,1);
        g2d.setColor(color);
        g2d.drawRect(rectSelection.getLoc().x, rectSelection.getLoc().y, rectSelection.getRect().width ,rectSelection.getRect().height);
    }

    public SRectangle fillRect(SRectangle r){
        ColorAttributes caRect = (ColorAttributes) r.getAttributes("Color");
        caRect.filled = true;
        if (caRect.fillColor == caRect.strokeColor){
            caRect.fillColor = newColor;
            caRect.strokeColor = newColor;

        }
        else{
            caRect.fillColor = newColor;
        }
        return r;

    }
    private SCircle fillCircle(SCircle c) {
        ColorAttributes caRect = (ColorAttributes) c.getAttributes("Color");
        caRect.filled = true;
        if (caRect.fillColor == caRect.strokeColor){
            caRect.fillColor = newColor;
            caRect.strokeColor = newColor;

        }
        else{
            caRect.fillColor = newColor;
        }
        return c;

    }

    private SText fillText(SText t) {
        ColorAttributes caCircle = (ColorAttributes) t.getAttributes("Color");
        caCircle.filled = true;
        if (caCircle.fillColor == caCircle.strokeColor){
            caCircle.fillColor = newColor;
            caCircle.strokeColor = newColor;
        }
        else{
            caCircle.fillColor = newColor;
        }
        return t;

    }
    private STriangle fillTriangle(STriangle tr) {
        ColorAttributes caTri = (ColorAttributes) tr.getAttributes("Color");
        caTri.filled = true;
        if (caTri.fillColor == caTri.strokeColor){
            caTri.fillColor = newColor;
            caTri.strokeColor = newColor;

        }
        else{
            caTri.fillColor = newColor;
        }
        return tr;
    }

    private void fillImage(SImage i,int x, int y) {
        Image image = i.image.getImage();
        flood(x,y,oldColor,newColor);
        //g.drawImage(i.image.getImage(),i.getLoc().x, i.getLoc().y, i.sview);
    }

    public void paintShape(int x, int y, SCollection c){
        ColorAttributes co = (ColorAttributes) c.getAttributes("Color");
        Iterator<Shape> si = c.iterator();
        this.x = x;
        this.y = y;
        this.oldColor = getPixelColor(x,y);

        while (si.hasNext()) {
            Shape s = si.next();

            if (s.getBounds() != null) {
                if (s.getBounds().contains(x, y)) {
                    if (s.getClass() == SRectangle.class) {
                        fillRect((SRectangle) s);
                    } else if (s.getClass() == SCircle.class) {
                        fillCircle((SCircle) s);
                    }else if (s.getClass() == SText.class) {
                        fillText((SText) s);
                    }
                    else if (s.getClass() == STriangle.class){
                        fillTriangle((STriangle) s);
                    }
                    else if (s.getClass() == SImage.class){
                        fillImage((SImage)s, x, y);
                    }
                    else if (s.getClass() == SCollection.class){
                        Iterator<Shape> sInside = ((SCollection) s).iterator();
                        while (sInside.hasNext()){
                            Shape shapeInside = sInside.next();
                            if (shapeInside.getBounds().contains(x, y)) {
                                if (shapeInside.getClass() == SRectangle.class) {
                                    fillRect((SRectangle) shapeInside);
                                } else if (shapeInside.getClass() == SCircle.class) {
                                    fillCircle((SCircle) shapeInside);
                                } else if (shapeInside.getClass() == SText.class) {
                                    fillText((SText) shapeInside);
                                }
                                else if (shapeInside.getClass() == STriangle.class){
                                    fillTriangle((STriangle) shapeInside);
                                }
                                else if(shapeInside.getClass() == SCollection.class){
                                    paintShape(x,y,(SCollection) shapeInside);
                                }
                            }
                        }
                    }
                }

            }
        }

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        SCollection c = (SCollection) sview.getModel();
        paintShape(e.getX(),e.getY(),c);
        sview.repaint();
        this.sview.setCursor(Cursor.getDefaultCursor());
        sview.removeMouseListener(this);
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
