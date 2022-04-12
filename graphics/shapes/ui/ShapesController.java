package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import graphics.shapes.SCollection;
import graphics.shapes.SPoint;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {


	private boolean shiftHeld = false;
	private Point lastPoint;
	private SCollection selectColl = new SCollection();

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
		for (Shape s : ((SCollection) getModel()).collection) {
			if(!((s.getClass().toString())).contains("SDraw")) {
				if(s.getBounds().contains(e.getPoint().x,e.getPoint().y)) {
					return s;
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
}
