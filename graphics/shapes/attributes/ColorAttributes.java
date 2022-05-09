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

	public ColorAttributes(ColorAttributes ca){
		this.stroked = ca.stroked;
		this.filled = ca.filled;
		this.strokeColor = ca.strokeColor;
		this.fillColor = ca.fillColor;
	}

	@Override
	public String getID() {
		return "Color";
	}

}
