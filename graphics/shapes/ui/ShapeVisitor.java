package graphics.shapes.ui;

import graphics.shapes.*;

public interface ShapeVisitor {
	
	public void visitRectangle(SRectangle r);
	
	public void visitCircle(SCircle c);
	
	public void visitCollection(SCollection c);
	
	public void visitText(SText t);
	
	public void visitDraw(SDraw d);
	
	public void visitImage(SImage image);
}
