package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes{

	public boolean filled;
	public boolean stroked;
	public Color strokeColor;
	public Color fillColor;
	
	public ColorAttributes(boolean stroke, boolean fill, Color bColor, Color fColor) {
		this.stroked = stroke;
		this.filled = fill;
		this.strokeColor = bColor;
		this.fillColor = fColor;
	}

	@Override
	public String getID() {
		return "Color";
	}
	
	public boolean isFilled() 
	{
		return filled;
	}

	public Color getFilledColor()
	{
		return fillColor;
	}

	public boolean isStroked()
	{
		return stroked;
	}
	
	public Color getStrokedColor()
	{
		return strokeColor;
	}
}
