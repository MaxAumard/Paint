package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.ui.ShapeVisitor;

public abstract class Shape {
	
	private HashMap<String,Attributes> attribute;
	
	public Shape() {
		this.attribute = new HashMap<>();
	}
	
	public void addAttributes(Attributes attribute2add) {
		this.attribute.put(attribute2add.getID(), attribute2add);
	}
	
	public Attributes getAttributes(String s) {
		Attributes attributs = attribute.getOrDefault(s, null);
		return attributs;
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point p);
	
	public abstract void translate(int x,int y);
	
	public abstract Rectangle getBounds();
	
	public abstract void accept(ShapeVisitor visitor);

	public abstract void add(Shape shape);
}
