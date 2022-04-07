package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes{
	private boolean selected;
	
	public SelectionAttributes() {
		this.selected = false;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public void toggleSelection() {
		if (this.selected){
			unselect();
		}
		else{
			select();
		}
	}

	@Override
	public String getID() {
		return "Selected";
	}
}
