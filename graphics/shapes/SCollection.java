package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.ui.ShapeVisitor;

public class SCollection extends Shape {

	public ArrayList<Shape> collection = new ArrayList<>();
	
	public SCollection() {
	}
	
	@Override
	public Point getLoc() {
		return collection.get(0).getLoc();
	}

	@Override
	public void setLoc(Point p) {
		for( Shape s : collection) {
			s.setLoc(p);
		}
	}

	@Override
	public void translate(int x, int y) {
		for( Shape s : collection) {
			s.translate(x, y);
		}
	}

	public Iterator<Shape> iterator(){
		return this.collection.iterator();
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle bounds = collection.get(0).getBounds();
		if(collection.size() > 0) {
			for(Shape s:collection) {
				bounds = bounds.union(s.getBounds());
			}
		}
		return bounds;
	}
	
	public void add(Shape shape) {
		collection.add(shape);
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitCollection(this);
	}
	
	public ArrayList getCollection() {
		return this.collection;
	}
}
