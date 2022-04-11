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

public class SDraw extends Shape implements MouseMotionListener {

	ShapesView sview;
	private int x;
	private int y;
	public ArrayList<Point> point;
	JButton dessine;


	public SDraw(ShapesView sview,JButton dessine ) {
		this.sview=sview;
		point = new ArrayList<>();
		this.dessine=dessine;


	}


	public void mouseDragged(MouseEvent e) {
		if(!dessine.isSelected()) {
			return;
		}
		ShapesController sContr = (ShapesController) (this.sview.getController());
		sContr.unselectAll();
		x = e.getX();
		y = e.getY();
		point.add(new Point(x,y));
	}




	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub

	}


	@Override
	public void translate(int x, int y) {
		// TODO Auto-generated method stub

	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void accept(ShapeVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitDraw(this);
	}

	public void mousePressed(MouseEvent e)
	{
		ShapesController sContr = (ShapesController) (this.sview.getController());
		sContr.unselectAll();
	}
}