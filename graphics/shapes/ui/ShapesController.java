package graphics.shapes.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import com.sun.source.tree.Scope;
import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {

	private boolean shiftHeld = false;
	private Point lastPoint;
	private SCollection selectColl = new SCollection();
	private ArrayList<Shape> copy;
	private ArrayList<Shape> cut;
	private boolean crayon;
	public ShapesView sview;
	private SCollection dessin;
	
	public ShapesController(Object newModel) {
		super(newModel);
		this.crayon = false;
		this.dessin = new SDraw();
	}

	public void mousePressed(MouseEvent e)
	{
		lastPoint = new Point(e.getPoint());
		if(this.crayon){
			// this.dessin = new SCollection();
			this.dessin.addAttributes(new SelectionAttributes());
		}
	}

	public void mouseReleased(MouseEvent e)
	{
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

	public void mouseDragged(MouseEvent evt) {
		if (this.crayon) {
			SCircle c = new SCircle(new Point(evt.getX(),evt.getY()),2);
			c.addAttributes(new SelectionAttributes());
			c.addAttributes(new ColorAttributes(false,true, Color.black,Color.black));
			dessin.add(c);
			SCollection view =  (SCollection)(getModel());
			view.add(dessin);
			//this.groupShape();
		}
		else {
			for (Shape s : ((SCollection) getModel()).collection) {
				SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
				if (sAtt.isSelected()) {
					if(s.getClass() == SCollection.class){
					}
					s.translate(evt.getX() - lastPoint.x, evt.getY() - lastPoint.y);
					if(s.getClass() == SCollection.class){
					}
				}
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
			if(evt.getKeyChar()=='a'){
				selectAll();
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


	public void selectAll() {
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			sAtt.select();
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
				if(list.get(i).getBounds().contains(e.getPoint().x,e.getPoint().y)) {
					System.out.println(list.get(i).getLoc());
					return list.get(i);
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
				((SCollection) this.getModel()).collection.add(s);
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
			newShape.addAttributes( new ColorAttributes( ca.stroked, ca.filled, ca.strokeColor,ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SCircle) {
			SCircle circle = (SCircle) s;
			newShape = new SCircle(new Point(circle.getLoc().x, circle.getLoc().y), circle.getRadius());
			ColorAttributes ca = (ColorAttributes) circle.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes( ca.stroked, ca.filled, ca.strokeColor,ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof STriangle) {
			STriangle triangle = (STriangle) s;
			newShape = new STriangle(new Point(triangle.p1), new Point(triangle.p2), new Point(triangle.p3), 3);
			ColorAttributes ca = (ColorAttributes) triangle.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}
		else if (s instanceof SText) {
			SText txt = (SText) s;
			newShape = new SText(new Point(txt.getLoc().x, txt.getLoc().y),txt.getText());
			ColorAttributes ca = (ColorAttributes) txt.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SPoint) {
			SPoint coor = (SPoint) s;
			newShape = new SPoint(new Point(coor.getLoc().x, coor.getLoc().y),coor.getText());
			ColorAttributes ca = (ColorAttributes) coor.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}/*else if (s instanceof SCollection) {
			SCollection col = (SCollection) s;
			newShape = new SCollection();
			for(Shape forme:col.getCollection()) {
				ColorAttributes ca = (ColorAttributes) forme.getAttributes("Color");
				newShape.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
				newShape.addAttributes(new SelectionAttributes());
			}
		}else if (s instanceof SImage) {
			SImage image = (SImage) s;
			newShape = new SImage(image.getPath(),new Point(image.getLoc().x, image.getLoc().y),sview);
			ColorAttributes ca = (ColorAttributes) image.getAttributes("Color");
			newShape.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
			newShape.addAttributes(new SelectionAttributes());
		}*/
		
		
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

	public void setCrayon(){
		this.crayon = !crayon;
	}
}
