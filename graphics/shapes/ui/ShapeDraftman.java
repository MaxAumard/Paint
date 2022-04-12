package graphics.shapes.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.ImageIcon;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SDraw;
import graphics.shapes.SImage;
import graphics.shapes.SPoint;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.*;

public class ShapeDraftman implements ShapeVisitor{

	final ColorAttributes DEFAULTCOLORATTRIBUTE = new ColorAttributes(true,false,Color.BLACK,Color.BLACK);
	private Graphics g;


	public void setGraphics(Graphics g) {
		this.g = g;
	}

	public void drawSelected(Shape s){
		Graphics2D g2d = (Graphics2D) g.create();

		Rectangle border = s.getBounds();
		SRectangle rectSelection = new SRectangle(new Point(border.x,border.y),border.width,border.height);

		Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
		g2d.setStroke(dashed);
		g2d.setColor(new Color(161,146,125));
		g2d.drawRect(rectSelection.getLoc().x, rectSelection.getLoc().y, rectSelection.getRect().width ,rectSelection.getRect().height);
	}


	public void visitRectangle(SRectangle r) {
		ColorAttributes color = (ColorAttributes) r.getAttributes("Color");
		SelectionAttributes selectAtt = (SelectionAttributes) r.getAttributes("Selected");

		if(color.filled) {
			g.setColor(color.fillColor);
			g.fillRect(r.getLoc().x,r.getLoc().y,r.getRect().width,r.getRect().height);
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		if(color.stroked) {
			g.setColor(color.strokeColor);
			g.drawRect(r.getLoc().x,r.getLoc().y,r.getRect().width,r.getRect().height);
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		if(selectAtt.isSelected()) {
			drawSelected(r);		}
	}

	public void visitCircle(SCircle c) {
		ColorAttributes color = (ColorAttributes) c.getAttributes("Color");
		SelectionAttributes selectAtt = (SelectionAttributes) c.getAttributes("Selected");

		if(color.filled) {
			g.setColor(color.fillColor);
			g.fillOval(c.getLoc().x,c.getLoc().y, 2*c.getRadius(), 2*c.getRadius());
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		if(color.stroked) {
			g.setColor(color.strokeColor);
			g.drawOval(c.getLoc().x,c.getLoc().y, 2*c.getRadius(), 2*c.getRadius());
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		if(selectAtt.isSelected()) {
			drawSelected(c);		}
	}

	public void visitText(SText t) {
		//FontAttributes fo = (FontAttributes)t.getAttributes("font");
		ColorAttributes color = (ColorAttributes) t.getAttributes("Color");
		SelectionAttributes selectAtt = (SelectionAttributes) t.getAttributes("Selected");

		if (color.filled) {
			//g.setFont(fo.font);
			g.setColor(color.fillColor);
			g.fillRect(t.getBounds().x, t.getBounds().y, t.getBounds().width, t.getBounds().height);
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		if (color.stroked) {
			//g.setFont(fo.font);
			g.setColor(DEFAULTCOLORATTRIBUTE.fillColor);
		}
		g.drawString(t.getText(), t.getLoc().x, t.getLoc().y-2);

		if (selectAtt.isSelected()) {
			drawSelected(t);
		}
	}

	public void visitDraw(SDraw d) {

		for(Point p:d.point) {
			g.fillOval(p.x, p.y, 10, 10);
		}

	}
	

	public void visitTriangle(STriangle t) {
		ColorAttributes ca = (ColorAttributes) t.getAttributes("Color");
		SelectionAttributes selectAtt = (SelectionAttributes) t.getAttributes("Selected");

		if(ca.filled) {
			g.setColor(ca.fillColor);
			g.fillPolygon(new int[] {t.getP1().x, t.getP2().x, t.getP3().x}, new int[] {t.getP1().y, t.getP2().y, t.getP3().y}, 3);
		}
		if(ca.stroked) {
			g.setColor(ca.strokeColor);
			g.drawPolygon(new int[] {t.getP1().x, t.getP2().x, t.getP3().x}, new int[] {t.getP1().y, t.getP2().y, t.getP3().y}, 3);
		}
		if (selectAtt.isSelected()) {
			drawSelected(t);
		}
				
	}
	
	
	
	public void visitCollection(SCollection c) {
		Iterator<Shape> shapeIterator = c.iterator();
		while(shapeIterator.hasNext()) {
			shapeIterator.next().accept(this);
		}
		SelectionAttributes selectAtt = (SelectionAttributes) c.getAttributes("Selected");
		if(selectAtt.isSelected()) {
			drawSelected(c);		}
	}

	public void visitImage(SImage image) {
	}
}
