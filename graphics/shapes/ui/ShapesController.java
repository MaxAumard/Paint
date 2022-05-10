package graphics.shapes.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

import graphics.menus.extensions.rightclick.RightClickMenu;
import graphics.menus.extensions.rightclick.RightClickMenuText;
import graphics.menus.layer.LayerMenu;
import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.attributes.SizeAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {


	private boolean shiftHeld = false;
	private Point lastPoint;
	private ArrayList<Shape> copy;
	private LayerMenu layerMenu;
	public ShapesView sview;
	public boolean crayon;
	public boolean rep;
	public SCollection dessin;
	public SCollection repere;
	private int paintSize = 4;
	private Shape shapeToResize;
	private boolean resize = false;

	public ShapesController(Object newModel, ShapesView sview) {
		super(newModel);
		this.crayon = false;
		this.rep = false;
		this.dessin = new SCollection();
		this.repere = new SCollection();
		this.sview = sview;
		dessin.addAttributes(new SelectionAttributes());
		repere.addAttributes(new SelectionAttributes());

	}

	public void mousePressed(MouseEvent e)
	{
		if(shapeToResize != null){testResize(e,shapeToResize);}
		lastPoint = new Point(e.getPoint());
		if(this.crayon){
			dessin = new SCollection();
			dessin.addAttributes(new SelectionAttributes());
			((SCollection)getModel()).add(dessin);
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		//layerMenu.setBackgroundColor(new Color(0,0,0));
		layerMenu.getMyJMenuBar().setBorder(BorderFactory.createMatteBorder(0,0,0,0, new Color(0,0,0)));

		layerMenu.refreshLayer(sview);
	}

	public void mouseClicked(MouseEvent e)
	{

		if(SwingUtilities.isLeftMouseButton(e)){
			Shape s = getTarget(e);
			if(s!=null){
				setSelection(s);
			}
		}

		if(SwingUtilities.isRightMouseButton(e)){
			RightClickMenu popMenu = new RightClickMenuText();
			popMenu.show(sview, 100, 100);
		}

		if(e.getClickCount() == 2){
			editShape(e);
		}
	}

	private void editShape(MouseEvent e) {
		Shape s = getTarget(e);
		if(s!=null) {
			editMenu(s);
		}
	}

	private void editMenu(Shape s) {
		JOptionPane editMenu = new JOptionPane();
		if(s.getClass() == SCircle.class){
			String text = (String) JOptionPane.showInputDialog(null, "Rayon : ", "Edit Circle",
					JOptionPane.QUESTION_MESSAGE, null, null, ((SCircle)s).getRadius());
			if (text != null && text.length() > 0) {
				try {((SCircle)s).setRadius(Integer.parseInt(text));} catch (NumberFormatException e) {}
			}
		}
		if(s.getClass() == SRectangle.class){
			JPanel myPanel = new JPanel();
			JTextField width = new JTextField(((SRectangle)s).getRect().width);
			JTextField height = new JTextField(((SRectangle)s).getRect().height);
			myPanel.add(new JLabel("Longueur:"));	myPanel.add(width);
			myPanel.add(new JLabel("Largeur:"));	myPanel.add(height);
			int result = JOptionPane.showConfirmDialog(null, myPanel,"Edit Rectangle", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					((SRectangle)s).getRect().setRect(s.getBounds().x,s.getBounds().y,
							Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
				}
				catch (NumberFormatException e) {}
			}
		}
		if(s.getClass() == SText.class){
			String text = (String) JOptionPane.showInputDialog(null, "Texte : ", "Edit Texte",
					JOptionPane.QUESTION_MESSAGE, null, null, ((SText)s).getText());
			if (text != null && text.length() > 0) {
				((SText)s).setText(text);
			}
		}
		getView().repaint();
	}

	public void mouseMoved(MouseEvent evt)
	{
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
		if(this.crayon) {
			drawing(evt);
		}
		else if(this.resize){
			resizeShape(evt);
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
			if(evt.getKeyCode()==evt.VK_G ) {
				groupShape();
				replaceCollec(SCollection.class);
			}
			if(evt.getKeyCode()==evt.VK_A){
				selectAll();
			}
		}
		if(evt.getKeyCode()==evt.VK_DELETE) {
			deleteShape();
		}
		if(evt.getKeyCode()==evt.VK_LEFT) {
			if(evt.isControlDown()){
				translateArrow(-10,0);
			}
			else {
				translateArrow(-1,0);
			}
		}
		if(evt.getKeyCode()==evt.VK_UP) {
			if(evt.isControlDown()){
				translateArrow(0,-10);
			}
			else {
				translateArrow(0,-1);
			}
		}
		if(evt.getKeyCode()==evt.VK_RIGHT) {
			if(evt.isControlDown()){
				translateArrow(10,0);
			}
			else {
				translateArrow(1,0);
			}
		}
		if(evt.getKeyCode()==evt.VK_DOWN) {
			if(evt.isControlDown()){
				translateArrow(0,10);
			}
			else {
				translateArrow(0,1);
			}
		}
		if(evt.getKeyCode()==evt.VK_X) {
			if(evt.isControlDown()){
				cut();
			}
		}
		if(evt.getKeyCode()==evt.VK_U) {
			if(evt.isControlDown()){
				if(evt.isShiftDown()){
					ungroupCollec();
				}
				else{
					ungroupLastAdd();
				}
			}
		}
		if(evt.getKeyCode() == evt.VK_ESCAPE) {
			unselectAll();
			this.crayon=false;
			this.rep=false;
			getView().setCursor(Cursor.getDefaultCursor());
		}


	}

	public void keyReleased(KeyEvent evt)
	{
		if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
			this.shiftHeld = false;
		}
		layerMenu.refreshLayer(sview);
	}

	public void unselectAll() {
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			sAtt.unselect();
		}
		this.getView().repaint();
	}


	public void selectAll() {
		for (Shape s : ((SCollection) getModel()).collection) {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			sAtt.select();
		}
		this.getView().repaint();
	}
	public void setSelection(Shape s) {
		if(!this.shiftHeld){unselectAll();}
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
		ArrayList<Shape> collection = ((SCollection) getModel()).collection;
		for (int i=collection.size()-1;i>=0;i--) {
			SelectionAttributes sAtt = (SelectionAttributes) collection.get(i).getAttributes("Selected");
			if(collection.get(i).getBounds().contains(e.getPoint().x,e.getPoint().y)) {
				shapeToResize = collection.get(i);
				return collection.get(i);
			}
		}
		unselectAll();
		return null;
	}

	private void testResize(MouseEvent e, Shape s) {
		Rectangle rect = new Rectangle(s.getBounds().x+s.getBounds().width,s.getBounds().y+s.getBounds().height,5,5);
		if(rect.getBounds().contains(e.getPoint())){
			this.shapeToResize = s;
			this.resize = true;
		}
		else{this.resize=false;}
	}

	public void groupShape() {
		SCollection newColl = new SCollection();
		newColl.addAttributes(new SelectionAttributes());
		ArrayList<Shape> selectedShapes = new ArrayList<>();

		for (Shape s : ((SCollection) this.getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() ) {
				selectedShapes.add(s);
				newColl.add(s);
				sAtt.unselect();
			}
		}
		((SCollection)this.getModel()).collection.add(newColl);
		for (Shape selectedShape : selectedShapes) {
			((SCollection) this.getModel()).collection.remove(selectedShape);
		}
		this.getView().repaint();
	}

	public void deleteShape() {
		ArrayList<Shape> selectedShape = new ArrayList<>();
		for (Shape s : ((SCollection) this.getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() ) {
				selectedShape.add(s);
			}
		}
		for (Shape shape : selectedShape) {
			((SCollection) this.getModel()).collection.remove(shape);
		}
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
	}


	public void cut()
	{
		this.copy = selected();
		this.delete();
	}



	private Shape duplicate(Shape s)
	{
		Shape newShape = null;

		if (s instanceof SRectangle) {
			SRectangle rectangle = (SRectangle) s;
			newShape = new SRectangle(new Point(rectangle.getLoc().x, rectangle.getLoc().y), rectangle.getRect().width, rectangle.getRect().height);
			newShape.addAttributes(new ColorAttributes((ColorAttributes) rectangle.getAttributes("Color")));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SCircle) {
			SCircle circle = (SCircle) s;
			newShape = new SCircle(new Point(circle.getLoc().x, circle.getLoc().y), circle.getRadius());
			newShape.addAttributes(new ColorAttributes((ColorAttributes) circle.getAttributes("Color")));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof STriangle) {
			STriangle triangle = (STriangle) s;
			newShape = new STriangle(new Point(triangle.p1), new Point(triangle.p2), new Point(triangle.p3), 3);
			newShape.addAttributes(new ColorAttributes((ColorAttributes) triangle.getAttributes("Color")));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SLine) {
			SLine line = (SLine) s;
			newShape = new SLine(new Point((line.p1)), new Point(line.p2));
			newShape.addAttributes(new ColorAttributes((ColorAttributes) line.getAttributes("Color")));
			newShape.addAttributes(new SelectionAttributes());
		}
		else if (s instanceof SText) {
			SText txt = (SText) s;
			newShape = new SText(new Point(txt.getLoc().x, txt.getLoc().y), txt.getText());
			newShape.addAttributes(new ColorAttributes((ColorAttributes) txt.getAttributes("Color")));
			newShape.addAttributes(new SelectionAttributes());
		}else if (s instanceof SImage) {
			SImage image = (SImage) s;
			try {
				newShape = new SImage(image.getPath(),new Point(image.getLoc().x, image.getLoc().y),sview);
			} catch (IOException e) {
				e.printStackTrace();
			}
			newShape.addAttributes(new SelectionAttributes());
		}
		else if (s instanceof SCollection) {
			newShape = new SCollection();
			newShape.addAttributes(new SelectionAttributes());
			for(Shape shapeInside : ((SCollection) s).collection){
				Shape inside = duplicate(shapeInside);
				newShape.add(inside);
			}
		}
		newShape.addAttributes(new SelectionAttributes());
		return newShape;
	}

	public void ungroupLastAdd() {
		SCollection newColl = new SCollection();
		SCollection tempModel = new SCollection();
		newColl.addAttributes(new SelectionAttributes());
		tempModel.addAttributes(new SelectionAttributes());

		for (Shape s : ((SCollection) this.getModel()).collection) {
			SelectionAttributes sAtt = 	(SelectionAttributes) s.getAttributes("Selected");
			if( sAtt.isSelected() && s.getClass() == SCollection.class) {
				Shape s2 = ((SCollection) s).collection.get(((SCollection)s).collection.size()-1);
				((SCollection) s).collection.remove(((SCollection)s).collection.size()-1);
				sAtt.unselect();
				tempModel.add(s2);
			}
			tempModel.add(s);
		}

		this.getView().setModel(tempModel);
		this.getView().repaint();
	}

	public void ungroupCollec(){
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());

		for(Shape s : ((SCollection)getModel()).collection ){
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			if(sAtt.isSelected()){
				if(s instanceof SCollection) {
					for (Shape s2 : ((SCollection) s).collection) {
						ungroupShape((SCollection) s,s2,tempModel);
					}
				}
				else {
					tempModel.add(s);
				}
			}
		}
		for(Shape s : ((SCollection)getModel()).collection ) {
			SelectionAttributes sAtt = (SelectionAttributes) s.getAttributes("Selected");
			if(!sAtt.isSelected()){
				tempModel.add(s);
			}
		}
		this.getView().setModel(tempModel);
		this.getView().repaint();
	}

	public void ungroupShape(SCollection coll, Shape s,SCollection tempModel){
		if(s instanceof SCollection){
			for (Shape inside : ((SCollection) s).collection){
				ungroupShape((SCollection) s,inside,tempModel);
			}
		}
		else {
			tempModel.add(s);
		}
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
		this.rep = true;

		SOrthonormal l = new SOrthonormal(new Point(getView().getWidth()/2, 0), new Point(getView().getWidth()/2,getView().getHeight()), true);
		l.addAttributes(new SelectionAttributes());
		l.addAttributes(new SizeAttributes(1));
		l.addAttributes(new ColorAttributes(true, true, Color.BLACK,Color.BLACK));


		SOrthonormal l2 = new SOrthonormal(new Point(0, getView().getHeight()/2), new Point(getView().getWidth(),getView().getHeight()/2), false);
		l2.addAttributes(new SelectionAttributes());
		l2.addAttributes(new SizeAttributes(2));
		l2.addAttributes(new ColorAttributes(true, true, Color.BLACK, Color.BLACK));

		SCollection view = (SCollection)(getModel());
		view.add(l);
		view.add(l2);
		replaceCollec(SOrthonormal.class);

	}

	public void cutRepere() {
		this.rep = false;

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

	public void setPenSize(int s) {
		this.paintSize  = s;
	}

	public void setLayerMenu(LayerMenu layerMenu){
		this.layerMenu = layerMenu;
	}

	public void drawing(MouseEvent evt){
		SLine line = new SLine(new Point(lastPoint.x, lastPoint.y), new Point(evt.getX(),evt.getY()));
		line.addAttributes(new SelectionAttributes());
		line.addAttributes(new ColorAttributes(true, true, Color.BLACK,Color.BLACK));
		line.addAttributes(new SizeAttributes(paintSize));
		SCollection oldDessin = dessin;
		dessin.add(line);
		if(oldDessin == ((SCollection)this.getModel()).collection.get(((SCollection)this.getModel()).collection.size()-1)){
			((SCollection)this.getModel()).collection.remove(((SCollection)this.getModel()).collection.size()-1);
			((SCollection)getModel()).add(dessin);
		}
	}

	public void resizeShape(MouseEvent evt){
		System.out.println(shapeToResize.getClass());
		if(shapeToResize.getClass() == SRectangle.class) {resizeRect((SRectangle) shapeToResize,evt);}
		if(shapeToResize.getClass() == SCircle.class){resizeCircle((SCircle)shapeToResize,evt);}
	}

	private void resizeCircle(SCircle shapeToResize, MouseEvent evt) {
		int dx = lastPoint.x-evt.getX();
		int dy = lastPoint.y-evt.getY();
		int radius = Integer.max(shapeToResize.getRadius()-dx,shapeToResize.getRadius()-dy);
		shapeToResize.setRadius(radius);
	}

	private void resizeRect(SRectangle shapeToResize,MouseEvent evt) {
		int dx = lastPoint.x-evt.getX();
		int dy = lastPoint.y-evt.getY();
		shapeToResize.getRect().setRect(shapeToResize.getLoc().x,shapeToResize.getLoc().y,shapeToResize.getBounds().width-dx,shapeToResize.getBounds().height-dy);
	}
}