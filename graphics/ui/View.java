package graphics.ui;

import javax.swing.JPanel;

import graphics.shapes.SCollection;
import graphics.shapes.ui.ShapeDraftman;

import java.awt.*;

public abstract class View extends JPanel
{
	private Object model;
	private Controller controller;
	protected ShapeDraftman draftman = new ShapeDraftman();

	public View(Object model)
	{
		super();
		this.model = model;
		this.controller = defaultController(model);
		this.controller.setView(this);
		this.addMouseListener(this.controller);
		this.addMouseMotionListener(this.controller);
		this.addKeyListener(this.controller);
	}
	
	public void setModel(Object model)
	{
		this.model = model;
		this.controller.setModel(model);
	}
	
	public Object getModel()
	{
		return this.model;
	}
	
	public Controller defaultController(Object model)
	{
		return new Controller(model);
	}
	
	final public Controller getController()
	{
		return this.controller;
	}
	
}


