package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FontAttributes extends Attributes{
	public static final Graphics GRAPHICS = new BufferedImage(1,1,1).getGraphics();

	public Font font = GRAPHICS.getFont();
	public Color fontColor;
	
	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
	}
	public FontAttributes(){
		this.font = GRAPHICS.getFont();
		this.fontColor = Color.BLACK;
	}

	public Font getFont() {
		return this.font;
	}
	
	@Override
	public String getID() {
		return "Font";
	}

}
