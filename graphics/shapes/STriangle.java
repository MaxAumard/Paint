package graphics.shapes;


import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeVisitor;

public class STriangle extends Shape {

	private Point loc;
	public Point p1;
	public Point p2;
	public Point p3;
	private int minx,miny,maxx,maxy;
	
	public STriangle(Point p1, Point p2, Point p3, int nbofpts) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.loc = new Point(p1);
		
	}
	
	@Override
	public Point getLoc() {
		return this.loc.getLocation();
	}

	@Override
	public void setLoc(Point p) {
		this.loc.setLocation(this.getBounds().getLocation());

	}

	@Override
	public void translate(int x, int y) {
		this.p1.translate(x,y);
		this.p2.translate(x,y);
		this.p3.translate(x,y);
		
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	public Point getP3() {
		return p3;
	}

	public int minx(Point p1,Point p2, Point p3) {
		
		this.minx = Math.min(p1.x,p2.x);
		
		this.minx = Math.min(minx, p3.x);
		
		return minx;
		
	}
	
	public int maxx(Point p1,Point p2, Point p3) { 
		
		this.maxx = Math.max(p1.x,p2.x);
		this.maxx = Math.max(maxx, p3.x);
		
		return maxx;
		
	}
	public int miny(Point p1,Point p2, Point p3) {
	
		this.miny = Math.min(p1.y,p2.y);
		this.miny = Math.min(miny, p3.y);
	
		return miny;
	
	}	
	public int maxy(Point p1,Point p2, Point p3) {
	
		this.maxy = Math.max(p1.y,p2.y);
		this.maxy = Math.max(maxy, p3.y);
	
		return maxy;	
	}
	
	@Override
	public Rectangle getBounds() {

		minx(p1,p2,p3);
		maxx(p1,p2,p3);
		miny(p1,p2,p3);
		maxy(p1,p2,p3);
	
		return new Rectangle(minx,miny,maxx-minx,maxy-miny).getBounds();
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitTriangle(this);
	}

	@Override
	public void add(Shape newS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValues() {
		ColorAttributes colorAtt = (ColorAttributes)this.getAttributes("Color");
		String sClass = this.getClass().toString().replace("class graphics.shapes.","");
		String strokeColor = String.valueOf(colorAtt.strokeColor).replaceAll("[^0-9,]","");
		String fillColor = String.valueOf(colorAtt.fillColor).replaceAll("[^0-9,]","");
		sClass += "(" + this.getP1().x + "," + this.getP1().y + ",";
		sClass += this.getP2().x + "," + this.getP2().y + ",";
		sClass += this.getP3().x + "," + this.getP3().y + ",";
		sClass += this.getLoc().x + "," + this.getLoc().y + ")";
		sClass += ";ColorAttribute"+ "(" + colorAtt.stroked + "," + colorAtt.filled + "," + strokeColor + "," + fillColor +")";
		return sClass;
	}
}
