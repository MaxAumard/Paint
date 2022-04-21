package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public void setLocCollection(int x, int y){
		setLocCollectionZero();
		for( Shape s : collection) {
			s.setLoc(new Point(s.getBounds().x+ x, s.getBounds().y + y));
		}
	}

	public void setLocCollectionZero(){
		int x = this.getBounds().x;
		int y = this.getBounds().y;
		for( Shape s : collection) {
			s.setLoc(new Point(s.getBounds().x - x,s.getBounds().y - y));
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
	
	public List<Shape> getShapes() {
		
		return this.collection;
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
	
	public ArrayList<Shape> getCollection() {
		return this.collection;
	}

	@Override
	public String getValues() {
		String values = "SCollection{\r\n\r\n";
		for(Shape s : this.collection) {
			values += s.getValues() + "\r\n\r\n";
		}
		return values+"}";
	}
}
