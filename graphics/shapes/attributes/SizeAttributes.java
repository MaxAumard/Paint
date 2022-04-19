package graphics.shapes.attributes;

import java.awt.Color;

public class SizeAttributes extends Attributes {

	public int size;
	
	public SizeAttributes(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}

	@Override
	public String getID() {
		return "Size";
	}
}
