package graphics.menus.extensions;

import graphics.shapes.SRectangle;
import graphics.shapes.ui.ShapesView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Bucket implements MouseListener {
    Color oldColor,newColor;
    int x,y;
    ShapesView sview;

    public Bucket(Color color, ShapesView sview)  {
        this.x = x;
        this.y = y;
        this.oldColor = getPixelColor(x,y);
        this.newColor = color;

        setPixelColor(10,10, Color.BLACK);
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
        System.out.println(new Color(image.getRGB(x,y)));
        return new Color(image.getRGB(x,y));
    }

    public void setPixelColor(int x, int y, Color color) {
        Graphics2D g2d = (Graphics2D) new BufferedImage(1,1,1).getGraphics().create();
        SRectangle rectSelection = new SRectangle(new Point(x,y),1,1);
        g2d.setColor(color);
        g2d.drawRect(rectSelection.getLoc().x, rectSelection.getLoc().y, rectSelection.getRect().width ,rectSelection.getRect().height);
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
