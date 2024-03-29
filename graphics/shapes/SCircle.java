package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeVisitor;

public class SCircle extends Shape {
	private int radius;
	private Point location;

	public SCircle(Point point, int radius) {
		this.location = point;
		this.radius = radius;    
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int newRadius) {
		this.radius = newRadius;
	}

	public Point getLoc() {
		return this.location;
	}

	public void setLoc(Point newLocation) {
		this.location = newLocation;
	}

	public void translate(int x,int y) {
		this.location.translate(x, y);
	}

	public Rectangle getBounds() {
		return new Rectangle(location.x,location.y, 2*radius, 2*radius);
	}
	
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitCircle(this);
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
		sClass += "(" + this.getLoc().x + "," + this.getLoc().y + "," + this.getRadius() + ")";
		sClass += ";ColorAttribute(" +colorAtt.stroked + "," + colorAtt.filled + "," + strokeColor + "," + fillColor + ")";
		return sClass;
	}
}