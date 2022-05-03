package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeVisitor;

public class SRectangle extends Shape {
	private Rectangle rect;

	public SRectangle(Point p,int width, int height) {
		this.rect = new Rectangle(p.x,p.y,width,height);		
	}

	public Rectangle getRect() {
		return this.rect;
	}

	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}

	@Override
	public void setLoc(Point newLocation) {
		this.rect.setLocation(newLocation);
	}

	@Override
	public void translate(int x,int y) {
		this.rect.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitRectangle(this);
	}

	@Override
	public void add(Shape shape) {

	}

	@Override
	public String getValues() {
		ColorAttributes colorAtt = (ColorAttributes)this.getAttributes("Color");
		String sClass = this.getClass().toString().replace("class graphics.shapes.","");
		String strokeColor = String.valueOf(colorAtt.strokeColor).replaceAll("[^0-9,]","");
		String fillColor = String.valueOf(colorAtt.fillColor).replaceAll("[^0-9,]","");
		sClass += "("+this.getLoc().x+","+this.getLoc().y+","+this.getRect().width+","+this.getRect().height+")";
		sClass += ";ColorAttribute"+ "(" + colorAtt.stroked + ";" + colorAtt.filled + ";" + strokeColor + ";" + fillColor +")";
		return sClass;
			}
}
