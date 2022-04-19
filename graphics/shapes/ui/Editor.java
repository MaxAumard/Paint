package graphics.shapes.ui;

import graphics.menus.extensions.Pipette;
import graphics.menus.layer.LayerMenu;
import graphics.menus.menuBar.MenuBar;
import graphics.menus.toolBar.ToolBar;
import graphics.shapes.*;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Editor extends JFrame
{
	private ShapesView sview;
	private SCollection model;
	private MenuBar menuBar;
	private LayerMenu layerMenu;
	private ToolBar toolBar;

	public Editor() throws IOException {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});

		this.model = new SCollection();
		this.sview = new ShapesView(this.model);
		
		this.menuBar = new MenuBar(sview);
		this.getContentPane().add(menuBar.getMyJMenuBar(), BorderLayout.NORTH);


		
		this.buildModel();
		
		this.sview.setPreferredSize(new Dimension(600,600));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);

		this.layerMenu = new LayerMenu(sview);
		this.getContentPane().add(layerMenu.getMyJMenuBar(), BorderLayout.EAST);
		layerMenu.getMyJMenuBar().setFocusable(false);
		this.layerMenu.getMyJMenuBar().setVisible(true);

		this.toolBar = new ToolBar(menuBar.getMyJMenuBar(), sview, layerMenu);
		this.getContentPane().add(toolBar.getJToolBar(), BorderLayout.WEST);
		toolBar.getJToolBar().setOrientation(SwingConstants.VERTICAL);




	}
	
	private void buildModel() throws IOException {
		
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
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.YELLOW));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		this.model.add(sc);
		
		//triangle à l'envers
		STriangle tri = new STriangle(new Point(100, 200), new Point(175,300), new Point(250,200),3);
		tri.addAttributes(new ColorAttributes(true,true,Color.PINK,Color.BLUE));
		tri.addAttributes(new SelectionAttributes());
		this.model.add(tri);

		
		SPoint p= new SPoint(new Point(20,20),"(0;0)");
        p.addAttributes(new ColorAttributes(false,false,Color.WHITE,Color.WHITE));
        p.addAttributes(new FontAttributes());
        p.addAttributes(new SelectionAttributes());
        this.model.add(p);
		
		/*triangle à l'endroit*/
		STriangle tri2 = new STriangle(new Point(175, 20), new Point(100,80), new Point(250,80),3);
		tri2.addAttributes(new ColorAttributes(true,true,Color.DARK_GRAY,Color.PINK));
		tri2.addAttributes(new SelectionAttributes());
		this.model.add(tri2);
		
		/*SLine line1 = new SLine(new Point(300, 0), new Point(300,1000));
		line1.addAttributes(new ColorAttributes(true,true,Color.BLACK,Color.BLACK));
		line1.addAttributes(new SelectionAttributes());
		this.model.add(line1);
		
		SLine line2 = new SLine(new Point(0, 334), new Point(1700,334));
		line2.addAttributes(new ColorAttributes(true,true,Color.BLACK,Color.BLACK));
		line2.addAttributes(new SelectionAttributes());
		this.model.add(line2);
		*/

		SImage im = new SImage("https://c.tenor.com/mCiM7CmGGI4AAAAM/naruto.gif",new Point(250,175),sview);
		//im.addAttributes(new ColorAttributes(true,true,Color.black,Color.black));
		im.addAttributes(new SelectionAttributes());
		this.model.add(im);
		
		((ShapesController)this.sview.getController()).replaceCollec();
	}

	public static void main(String[] args) throws IOException {
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}