package graphics.shapes.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View{

	private ShapeDraftman draftman;


	public ShapesView(Object model) {
		super(model);
		this.draftman = new ShapeDraftman();
		this.setBackground(Color.white);
	}



	public boolean isFocusable() {
		return true;
	}

	public Controller defaultController(Object model)
	{
		return new ShapesController(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		SCollection model = (SCollection) this.getModel();
		if (model == null) { return; }
		this.draftman.setGraphics(g);
		model.accept(draftman);
	}

}
