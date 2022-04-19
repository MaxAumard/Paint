package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeVisitor;

public class SLine extends Shape{

	private Point location;
	public Point p1;
	public Point p2;
	private int minx,miny,maxx,maxy;
	int size;
	
	public SLine(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.location = new Point(p1);
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return this.location.getLocation();
	}

	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		this.location.setLocation(this.getBounds().getLocation());
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}

	@Override
	public void translate(int x, int y) {
		// TODO Auto-generated method stub
		this.p1.translate(x,y);
		this.p2.translate(x,y);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		minx(p1,p2);
		maxx(p1,p2);
		miny(p1,p2);
		maxy(p1,p2);
	
		return new Rectangle(minx,miny,maxx-minx,maxy-miny).getBounds();
		
		
	}

	public int minx(Point p1,Point p2) {

		this.minx = Math.min(p1.x,p2.x);
		return minx;

	}

	public int maxx(Point p1,Point p2) { 

		this.maxx = Math.max(p1.x,p2.x);
		return maxx;

	}
	public int miny(Point p1,Point p2) {

		this.miny = Math.min(p1.y,p2.y);
		return miny;

	}	
	public int maxy(Point p1,Point p2) {

		this.maxy = Math.max(p1.y,p2.y);
		return maxy;	
	}
	
	

	@Override
	public void accept(ShapeVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitLine(this);
	}

	@Override
	public void add(Shape shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		ColorAttributes colorAtt = (ColorAttributes)this.getAttributes("Color");
		String sClass = this.getClass().toString().replace("class graphics.shapes.","");
		String strokeColor = String.valueOf(colorAtt.strokeColor).replaceAll("[^0-9,]","");
		String fillColor = String.valueOf(colorAtt.fillColor).replaceAll("[^0-9,]","");
		return sClass + ";" + String.valueOf(this.getLoc().x) + ";" + String.valueOf(this.getLoc().y) + ";" + String.valueOf(this.getP1().x) + ";" + String.valueOf(this.getP1().y) + ";" + String.valueOf(this.getP2().x) + ";" + String.valueOf(this.getP2().y) + ";" + colorAtt.stroked + ";" + colorAtt.filled + ";" + strokeColor + ";" + fillColor;
	}
	

}
