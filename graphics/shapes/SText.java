package graphics.shapes;

import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.ui.ShapeVisitor;

public class SText extends Shape {

	private Point location;
	private String text;	

	public SText(Point point, String string) {
		this.location = point;
		this.text = string;
	}

	public Point getLoc() {
		return this.location;
	}

	public void setLoc(Point newLocation) {
		this.location = newLocation;
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void translate(int x,int y) {
		this.getLoc().translate(x, y);
	}

	public Rectangle getBounds() {
		FontMetrics fm = FontAttributes.GRAPHICS.getFontMetrics(FontAttributes.GRAPHICS.getFont());
		Rectangle bounds = new Rectangle(location.x,location.y-fm.getHeight(),fm.stringWidth(text),fm.getHeight());
		return bounds;
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitText(this);
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
		sClass += "(" + this.getLoc().x + "," + this.getLoc().y + "," + this.getText() + ")";
		sClass += ";ColorAttribute"+ "(" + colorAtt.stroked + "," + colorAtt.filled + "," + strokeColor + "," + fillColor +")";
		return sClass;

	}
}