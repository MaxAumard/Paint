package graphics.menus.toolBar;

import graphics.menus.extensions.*;
import graphics.shapes.ui.ShapesView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ToolBar extends JToolBar{
	JToolBar jtoolBar;
	private static Color color1 = Color.BLACK ;
	private static Color color2 = Color.WHITE ;
	public JButton draw;

	public ToolBar(JMenuBar menuBar, ShapesView sview) {
		super();
		//creation of the toolBar
		jtoolBar = new JToolBar();
		//prevent flotability
		jtoolBar.setFloatable(false);
		//toolBar buttons will be stored here
		Collection buttons = new ArrayList<Button>();


		draw = new JButton();
		draw.isSelected();
		draw.setIcon(new ImageIcon(new ImageIcon("icon/dessine.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(draw.isSelected());
				draw.setSelected(!draw.isSelected());
				if(draw.isSelected()) {


					ImageIcon im = new ImageIcon(new ImageIcon("icon/dessine.png").getImage().getScaledInstance(31,31, Image.SCALE_SMOOTH));
					sview.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(im.getImage(), new Point(2,im.getIconHeight() ), "dessine cursor"));
				}
				else {
					sview.setCursor(Cursor.getDefaultCursor());

				}
			}
		});

		//colorChooser button for color1
		JButton colorChooser = new JButton();
		ColorChooser cc = new ColorChooser();
		colorChooser.setBackground(Color.black);
		colorChooser.setBorder(BorderFactory.createEmptyBorder());
		colorChooser.setMargin(new Insets(-2,-2,-1,-1));
		colorChooser.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc = new ColorChooser();
				cc.setColorChooseed(Color.BLACK);
				cc.displayColorChooser(colorChooser);
				color1 = cc.getColorChoosed() ;
			}});

		//colorChooser button for color2
		JButton colorChooser2 = new JButton();
		ColorChooser cc2 = new ColorChooser();
		colorChooser2.setBackground(Color.white);
		colorChooser2.setBorder(BorderFactory.createEmptyBorder());
		colorChooser2.setMargin(new Insets(-2,-2,-1,-1));
		colorChooser2.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc2 = new ColorChooser();
				cc2.setColorChooseed(Color.BLACK);
				cc2.displayColorChooser(colorChooser2);
				color2 = cc2.getColorChoosed() ;
			}});


		//pipette button
		Button pipette = new Button("icon/pipette.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Pipette pp = null;
				try {
					pp = new Pipette(cc, colorChooser, sview, menuBar);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				color1 = pp.getColorPicked();

			}});

		buttons.add(pipette);
		pipette.setBackground(new Color(239, 239, 239));


		//darktheme button
		ToggleButton darkTheme = new ToggleButton("icon/LightThemeIcon.png","icon/DarkThemeIcon.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DarkTheme dt = new DarkTheme(e, jtoolBar, menuBar, sview, buttons, colorChooser);
			}});


		//AddRect button
		JButton addRect = new Button("icon/addRect.png","icon/addRect.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddRect addRect = new AddRect();
				addRect.add(sview, color2, color1);
				sview.repaint();
			}});

		//AddCircle button
		JButton addCircle = new Button("icon/addCircle.png","icon/addCircle.png",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCircle addCircle = new AddCircle();
				addCircle.add(sview,color1,color2);
				sview.repaint();
			}
		});
		
		//AddText button
		JButton addText = new Button("icon/addText.png","icon/addText.png",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddText addText = new AddText();
				addText.add(sview,color1,color2);
				sview.repaint();
			}
		});
		
		//AddTriangle 
				JButton addTriangle = new Button("icon/addTriangle.png","icon/addTriangle.png",new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						AddTriangle addTriangle = new AddTriangle();
						addTriangle.add(sview, color2, color1);
						sview.repaint();
					}});


		//bucket button
		Button bucket = new Button("icon/bucket.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(color1);
				Bucket bu = new Bucket(color1,sview);

			}});

		//add icons to toolbar
		jtoolBar.addSeparator(new Dimension(15,0));
		jtoolBar.add(darkTheme);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(pipette);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(addRect);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(addCircle);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(addText);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(addTriangle);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(bucket);




		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(colorChooser);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(colorChooser2);

		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(draw);


		//Enlever le focus sur chaque bouttons 
		for(int i=0; i<jtoolBar.getComponentCount();i++) {
			String className = (jtoolBar.getComponent(i).getClass()).toString();
			if(className.equals("class javax.swing.JButton") || className.equals("class graphics.menus.toolBar.ToggleButton") || className.equals("class graphics.menus.toolBar.Button")) {
				jtoolBar.getComponent(i).setFocusable(false);
			}
		}
	}

	public void setColor1(Color color1){
		this.color1 = color1;
	}

	public JToolBar getJToolBar(){
		return this.jtoolBar;
	}
}
