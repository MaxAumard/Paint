package graphics.shapes;

import java.awt.Point;

import graphics.shapes.ui.ShapeVisitor;

public class SOrthonormal extends SLine {
	
	private boolean horizontal;
	
	public SOrthonormal(Point p1, Point p2, boolean horizontal) {
		super(p1,p2);
		this.horizontal = horizontal;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
}
