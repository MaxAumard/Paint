package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	public ShapesView sview;
	public boolean crayon;
	public SCollection dessin;
	public SCollection repere;
	private int currentX, currentY, oldX, oldY;

	public ShapesController(Object newModel) {
		super(newModel);
		this.crayon = false;
		this.dessin = new SCollection();
		this.repere = new SCollection();
		dessin.addAttributes(new SelectionAttributes());
		repere.addAttributes(new SelectionAttributes());
	}

	public void mousePressed(MouseEvent e)
	{
		
		lastPoint = new Point(e.getPoint());
		oldX = e.getX();
		oldY = e.getY();
		//System.out.println("mouse Pressed");
	}

	public void mouseReleased(MouseEvent e)
	{
		//System.out.println("mouse Released");
		dessin = new SCollection();
		dessin.addAttributes(new SelectionAttributes());
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
		currentX = evt.getX();
        currentY = evt.getY();
		if(this.crayon) {
			SLine c = new SLine(new Point(oldX, oldY), new Point( currentX, currentY));
			c.addAttributes(new SelectionAttributes());
			c.addAttributes(new ColorAttributes(true, true, Color.BLACK,Color.BLACK));
			dessin.add(c);
			oldX = currentX;
	        oldY = currentY;
			SCollection view = (SCollection)(getModel());
			view.add(dessin);
			


		}
		else {
			for (Shape s : ((SCollection) getModel()).collection) {
				SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
				if( sAtt.isSelected() ) {
					s.translate(evt.getX()-lastPoint.x, evt.getY()-lastPoint.y);
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
				replaceCollec(SCollection.class);
			}
			if(evt.getKeyCode()==65){
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
		ArrayList<Shape> shapeList = new ArrayList<>();
		SCollection Scol = (SCollection) this.getModel();
		for (Iterator<Shape> it = Scol.iterator(); it.hasNext();) {
			Shape shape = it.next();
			SelectionAttributes selectAtt = (SelectionAttributes) shape.getAttributes("Selected");
			if (selectAtt != null) {
				if (selectAtt.isSelected()) {
					shapeList.add(shape);
				}
			}
		}
		return shapeList;
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
		Shape newS =null;

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
		}else if (s instanceof SLine) {
			SLine line = (SLine) s;
			newShape = new SLine(new Point((line.p1)), new Point(line.p2));
			ColorAttributes ca = (ColorAttributes) line.getAttributes("Color");
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

		}else if (s instanceof SImage) {
			SImage image = (SImage) s;
			try {
				newShape = new SImage(image.getPath(),new Point(image.getLoc().x, image.getLoc().y),sview);
			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SCollection) {
			Iterator<Shape> sInside = ((SCollection) s).iterator();
			newShape = new SCollection();
			newShape.addAttributes(new SelectionAttributes());
			while (sInside.hasNext()){
				Shape shapeInside = sInside.next();
				if (shapeInside.getBounds().contains(shapeInside.getLoc().x, shapeInside.getLoc().y)) {
					if (shapeInside.getClass() == SRectangle.class) {
						SRectangle rectangle = (SRectangle) shapeInside;
						newS = new SRectangle(new Point(rectangle.getLoc().x, rectangle.getLoc().y), rectangle.getRect().width, rectangle.getRect().height);
						ColorAttributes ca = (ColorAttributes) rectangle.getAttributes("Color");
						newS.addAttributes( new ColorAttributes( ca.stroked, ca.filled, ca.strokeColor,ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);

					} else if (shapeInside.getClass() == SCircle.class) {
						SCircle circle = (SCircle) shapeInside;
						newS = new SCircle(new Point(circle.getLoc().x, circle.getLoc().y), circle.getRadius());
						ColorAttributes ca = (ColorAttributes) circle.getAttributes("Color");
						newS.addAttributes( new ColorAttributes( ca.stroked, ca.filled, ca.strokeColor,ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					} else if (shapeInside.getClass() == SText.class) {
						SText txt = (SText) shapeInside;
						newS = new SText(new Point(txt.getLoc().x, txt.getLoc().y),txt.getText());
						ColorAttributes ca = (ColorAttributes) txt.getAttributes("Color");
						newS.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					}
					else if (shapeInside.getClass() == STriangle.class){
						STriangle triangle = (STriangle) shapeInside;
						newS = new STriangle(new Point(triangle.p1), new Point(triangle.p2), new Point(triangle.p3), 3);
						ColorAttributes ca = (ColorAttributes) triangle.getAttributes("Color");
						newS.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					}
					else if (shapeInside.getClass() == SPoint.class){
						SPoint coor = (SPoint) shapeInside;
						newS = new SPoint(new Point(coor.getLoc().x, coor.getLoc().y),coor.getText());
						ColorAttributes ca = (ColorAttributes) coor.getAttributes("Color");
						newS.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					}
					else if (shapeInside.getClass() == SLine.class){
						SLine line = (SLine) shapeInside;
						newS = new SLine(new Point(line.p1),new Point(line.p2));
						ColorAttributes ca = (ColorAttributes) line.getAttributes("Color");
						newS.addAttributes( new ColorAttributes(ca.stroked, ca.filled, ca.strokeColor, ca.fillColor));
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					}
					else if (shapeInside.getClass() == SImage.class) {
						SImage image = (SImage) shapeInside;
						try {
							newS = new SImage(image.getPath(),new Point(image.getLoc().x, image.getLoc().y),sview);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						newS.addAttributes(new SelectionAttributes());
						newShape.add(newS);
					}
					else if(shapeInside.getClass() == SCollection.class){
						//duplicate(shapeInside);
						newShape.add(duplicate(shapeInside));
					}
				}
			}
		}



		newShape.addAttributes(new SelectionAttributes());



		return newShape;
	}


	public void replaceCollec(Class c) {
		SCollection sview = (SCollection) (this.getModel());
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());
		for(Shape s : sview.collection) {
			if(s.getClass() == c) {
				tempModel.add(s);
			}
		}
		for(Shape s : sview.collection) {
			if(s.getClass() != c) {
				tempModel.add(s);
			}
		}
		this.getView().setModel(tempModel);
		this.getView().repaint();
	}

	public void setCrayon() {
		this.crayon=!crayon;
	}
	
	public void setRepere() {
		System.out.println("setrepere");
		/*SLine line1 = new SLine(new Point(300, 0), new Point(300,1000));
		line1.addAttributes(new ColorAttributes(true,true,Color.BLACK,Color.BLACK));
		line1.addAttributes(new SelectionAttributes());
		
		
		SLine line2 = new SLine(new Point(0, 334), new Point(1700,334));
		line2.addAttributes(new ColorAttributes(true,true,Color.BLACK,Color.BLACK));
		line2.addAttributes(new SelectionAttributes());
	*/
		
		SOrthonormal l = new SOrthonormal(new Point(300, 0), new Point(300,1000));
		l.addAttributes(new SelectionAttributes());
		l.addAttributes(new ColorAttributes(true, true, Color.BLACK,Color.BLACK));

		
		SOrthonormal l2 = new SOrthonormal(new Point(0, 350), new Point(1700,350));
		l2.addAttributes(new SelectionAttributes());
		l2.addAttributes(new ColorAttributes(true, true, Color.BLACK, Color.BLACK));
		
		SCollection view = (SCollection)(getModel());
		view.add(l);
		view.add(l2);
		replaceCollec(SOrthonormal.class);
	
	}
	
	public void cutRepere() {
	System.out.println("cutrepere");
	/*	for (Shape s :  repere1.collection) {
			System.out.println(s);
			repere1.getShapes().remove(s);
	}
		for (Shape s :  repere2.collection) {
			System.out.println(s);
			repere2.getShapes().remove(s);
	}*/	
		
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());
		
		for (Shape s : ((SCollection) this.getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( s.getClass()!=SOrthonormal.class ) {
				tempModel.add(s);
			}
		}
		
		this.getView().setModel(tempModel);
		this.getView().repaint();
	}
}
