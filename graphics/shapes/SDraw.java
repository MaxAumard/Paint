package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import graphics.menus.toolBar.ToggleButton;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeVisitor;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

public class SDraw extends SCollection {

	public ArrayList<SCircle> collection = new ArrayList<>();

	@Override
	public void translate(int x, int y) {
		super.translate(x, y);
	}
}
