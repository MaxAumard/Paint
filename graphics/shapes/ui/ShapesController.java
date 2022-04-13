package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.*;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {


	private boolean shiftHeld = false;
	private Point lastPoint;
	private SCollection selectColl = new SCollection();
	private ArrayList<Shape> copy;
	private ArrayList<Shape> cut;

	public ShapesController(Object newModel) {
		super(newModel);
	}

	public void mousePressed(MouseEvent e)
	{
		lastPoint = new Point(e.getPoint());
		//System.out.println("mouse Pressed");
	}

	public void mouseReleased(MouseEvent e)
	{
		//System.out.println("mouse Released");
	}

	public void mouseClicked(MouseEvent e)
	{
		//System.out.println("mouse Click");
		Shape s = getTarget(e);
		if(s!=null){
			setSelection(s);
		}

	}

	public void mouseEntered(MouseEvent e)
	{
		//System.out.println("mouse Enter");
	}

	public void mouseExited(MouseEvent e)
	{
		//System.out.println("mouse Exit");
	}

	public void mouseMoved(MouseEvent evt)
	{
		//System.out.println("mouse Move");

		SCollection Scol = (SCollection) this.getModel();
		for(Shape s : new ArrayList<>(Scol.getShapes())) {
			if(s instanceof SPoint) {
				((SPoint) s).setText("("+evt.getX()+","+evt.getY()+")");
				this.getView().repaint();
			}

		}
	}

	public void mouseDragged(MouseEvent evt)
	{
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() ) {
				s.translate(evt.getX()-lastPoint.x, evt.getY()-lastPoint.y);
			}
		}
		this.lastPoint = evt.getPoint();
		this.getView().repaint();
	}

	public void keyTyped(KeyEvent evt)
	{
	}

	public void keyPressed(KeyEvent evt)
	{
		if(evt.isShiftDown()) {
			this.shiftHeld = true;
		}
		if(evt.isControlDown()) {
			if(evt.getKeyCode()==71 ) {
				groupShape();
				replaceCollec();
			}
		}
		if(evt.getKeyCode()==127) {
			deleteShape();
		}
		if(evt.getKeyCode()==37) {
			if(evt.isControlDown()){
				translateArrow(-10,0);
			}
			else {
				translateArrow(-1,0);
			}
		}
		if(evt.getKeyCode()==38) {
			if(evt.isControlDown()){
				translateArrow(0,-10);
			}
			else {
				translateArrow(0,-1);
			}
		}
		if(evt.getKeyCode()==39) {
			if(evt.isControlDown()){
				translateArrow(10,0);
			}
			else {
				translateArrow(1,0);
			}
		}
		if(evt.getKeyCode()==40) {
			if(evt.isControlDown()){
				translateArrow(0,10);
			}
			else {
				translateArrow(0,1);
			}
		}

	}

	public void keyReleased(KeyEvent evt)
	{
		if(evt.getKeyCode()==16){
			this.shiftHeld = false;
		}
	}

	public void unselectAll() {
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			sAtt.unselect();
		}
		this.getView().repaint();
		this.selectColl = new SCollection();
	}

	public void setSelection(Shape s) {
		if(!this.shiftHeld){unselectAll();}
		this.selectColl.add(s);
		if(s.getAttributes("Selection") == null) {
			SelectionAttributes sAtt = new SelectionAttributes();
			sAtt.select();
			s.addAttributes(sAtt);
		}
		else {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			sAtt.select();
		}
		this.getView().repaint();
	}

	public Shape getTarget(MouseEvent e) {
		ArrayList<Shape> list = ((SCollection) getModel()).collection;
		for (int i=list.size()-1;i>=0;i--) {
			if((list.get(i).getClass() != SDraw.class)) {
				if(list.get(i).getBounds().contains(e.getPoint().x,e.getPoint().y)) {
					return list.get(i);
				}
			}
		}
		unselectAll();
		return null;
	}

	public void groupShape() {
		SCollection newColl = new SCollection();
		SCollection tempModel = new SCollection();
		newColl.addAttributes(new SelectionAttributes());
		tempModel.addAttributes(new SelectionAttributes());

		for (Shape s : ((SCollection) this.getModel()).collection) {
			tempModel.add(s);
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() ) {
				newColl.add(s);
				sAtt.unselect();
				tempModel.collection.remove(s);
			}
		}

		tempModel.add(newColl);
		this.getView().setModel(tempModel);
		this.getView().repaint();
	}

	public void deleteShape() {
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());

		for (Shape s : ((SCollection) this.getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( !sAtt.isSelected() ) {
				tempModel.add(s);
			}
		}

		this.getView().setModel(tempModel);
		this.getView().repaint();
	}

	public void translateArrow(int x,int y) {
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() ) {
				s.translate(x,y);
			}
		}
		this.getView().repaint();
	}

	public ArrayList<Shape> selected()
	{
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		SCollection Scol = (SCollection) this.getModel();
		for (Iterator<Shape> it = Scol.iterator(); it.hasNext();) {
			Shape shape = it.next();
			SelectionAttributes selectAtt = (SelectionAttributes) shape.getAttributes("Selected");
			if (selectAtt != null) {
				if (selectAtt.isSelected()) {
					shapes.add(shape);
				}
			}
		}
		return shapes;
	}

	public void delete()
	{
		SCollection Scol = (SCollection) this.getModel();
		for(Shape s : this.selected()){
			System.out.println(s);
			Scol.getShapes().remove(s);
		}
		this.getView().repaint();
	}

	public void copy()
	{
		this.copy = selected();

	}

	public void paste()
	{
		SCollection Scol = (SCollection) this.getModel();

		if (this.copy != null) {
			for (Shape shape : this.copy) {
				Shape s = duplicate(shape);
				Scol.getShapes().add(s);
			}
			copy.clear();
			getView().repaint();
		}

		if (this.cut != null) {
			for (Shape shape : this.cut) {
				Shape s = duplicate(shape);
				Scol.getShapes().add(s);
			}
			cut.clear();
			getView().repaint();
		}
	}


	public void cut()
	{
		this.cut = selected();
		this.delete();
	}



	private Shape duplicate(Shape s)
	{
		Shape newShape = null;

		if (s instanceof SRectangle) {
			SRectangle rectangle = (SRectangle) s;
			newShape = new SRectangle(new Point(rectangle.getLoc().x, rectangle.getLoc().y), rectangle.getRect().width, rectangle.getRect().height);
			ColorAttributes ca = (ColorAttributes) rectangle.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.filled, ca.stroked, ca.fillColor, ca.strokeColor));
		}else if (s instanceof SCircle) {
			SCircle circle = (SCircle) s;
			newShape = new SCircle(new Point(circle.getLoc().x, circle.getLoc().y), circle.getRadius());
			ColorAttributes ca = (ColorAttributes) circle.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.filled, ca.stroked, ca.fillColor, ca.strokeColor));
		}else if (s instanceof STriangle) {
			STriangle triangle = (STriangle) s;
			newShape = new STriangle(new Point(triangle.p1), new Point(triangle.p2), new Point(triangle.p3), 3);
			ColorAttributes ca = (ColorAttributes) triangle.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.filled, ca.stroked, ca.fillColor, ca.strokeColor));
			newShape.addAttributes(new SelectionAttributes());
		}
		return newShape;
	}


	public void replaceCollec() {
		SCollection sview = (SCollection) (this.getModel());
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());
		for(Shape s : sview.collection) {
			if(s.getClass() == SCollection.class) {
				tempModel.add(s);
			}
		}
		for(Shape s : sview.collection) {
			if(s.getClass() != SCollection.class) {
				tempModel.add(s);
			}
		}
		this.getView().setModel(tempModel);
		this.getView().repaint();
	}
}
