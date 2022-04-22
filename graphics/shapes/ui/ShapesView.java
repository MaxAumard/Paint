package graphics.shapes.ui;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import graphics.shapes.SCollection;
import graphics.shapes.SOrthonormal;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View implements ComponentListener{

	private ShapeDraftman draftman;


	public ShapesView(Object model) {
		super(model);
		this.draftman = new ShapeDraftman();
		this.setBackground(Color.white);
		this.addComponentListener(this);
	}



	public boolean isFocusable() {
		return true;
	}

	public Controller defaultController(Object model)
	{
		return new ShapesController(model, this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		SCollection model = (SCollection) this.getModel();
		if (model == null) { return; }
		this.draftman.setGraphics(g);
		model.accept(draftman);
	}

	public ShapeDraftman getDraftman(){
		return this.draftman;
	}



	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getComponent().getWidth());
		ArrayList<Shape> shapes = ((SCollection)getModel()).getCollection();
		for(Shape s : shapes) {
			if(s instanceof SOrthonormal) {
				SOrthonormal so = (SOrthonormal) s;
				if(so.isHorizontal()) {
					so.setP1(new Point(e.getComponent().getWidth()/2, 0));
					so.setP2(new Point(e.getComponent().getWidth()/2, e.getComponent().getHeight()));
				}
				else {
					so.setP1(new Point(0,e.getComponent().getHeight()/2));
					so.setP2(new Point(e.getComponent().getWidth(), e.getComponent().getHeight()/2));
				}
			}
		}
	}



	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
