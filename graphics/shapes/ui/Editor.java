package graphics.shapes.ui;

import graphics.menus.menuBar.MenuBar;
import graphics.menus.toolBar.ToolBar;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Editor extends JFrame
{
	ShapesView sview;
	SCollection model;
	MenuBar menuBar;
	ToolBar toolBar;
	//jijuoijiojj
	public Editor() throws IOException {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});

		this.buildModel();

		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(400,400));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);

		this.menuBar = new MenuBar();
		this.getContentPane().add(menuBar.getMyJMenuBar(), BorderLayout.NORTH);

		//TODO JTOOLBAR FAIT BUGER LE SHIFT
		this.toolBar = new ToolBar(menuBar.getMyJMenuBar(), sview);
		this.getContentPane().add(toolBar.getJToolBar(), BorderLayout.WEST);
		toolBar.getJToolBar().setOrientation(SwingConstants.VERTICAL);

	}

	private void buildModel()
	{
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());

		SRectangle r = new SRectangle(new Point(10,10),20,30);
		r.addAttributes(new ColorAttributes(true,true,Color.RED,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		this.model.add(r);

		SCircle c = new SCircle(new Point(100,100),10);
		c.addAttributes(new ColorAttributes(false,true,Color.RED,Color.RED));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);

		SText t= new SText(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);

		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r= new SRectangle(new Point(20,30),30,30);
		r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		c = new SCircle(new Point(150,100),20);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		this.model.add(sc);
	}

	public static void main(String[] args) throws IOException {
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}