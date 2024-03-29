package graphics.menus.toolBar;

import graphics.menus.extensions.*;
import graphics.menus.layer.LayerMenu;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ToolBar extends JToolBar{
	JToolBar jtoolBar;
	private static Color color1 = Color.BLACK ;
	private static Color color2 = Color.WHITE ;
	public Button draw;
	public Button repere;
	private ShapesView sview;
	private Map<String,Component> buttonMap = new HashMap<String,Component>();
	private LayerMenu layerMenu;


	public ToolBar(JMenuBar menuBar, ShapesView sview, LayerMenu layerMenu) throws IOException {
		super();
		
		this.sview = sview;
		this.layerMenu = layerMenu;
		//creation of the toolBar
		jtoolBar = new JToolBar();
		//prevent flotability
		jtoolBar.setFloatable(false);
		//toolBar buttons will be stored here
		Collection buttons = new ArrayList<Button>();


		//colorChooser button for color1
		JButton colorChooser = new JButton();
		ColorChooser cc = new ColorChooser();
		colorChooser.setBackground(Color.black);
		colorChooser.setBorder(BorderFactory.createEmptyBorder());
		colorChooser.setMargin(new Insets(-2,-2,-1,-1));
		colorChooser.setIcon(new ImageIcon(new ImageIcon("icon/ColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc = new ColorChooser();
				cc.setColorChooseed(Color.BLACK);
				cc.displayColorChooser(colorChooser);
				color1 = cc.getColorChoosed() ;
				layerMenu.refreshLayer(sview);
			}});

		//colorChooser button for color2
		JButton colorChooser2 = new JButton();
		colorChooser2.setBackground(Color.white);
		colorChooser2.setBorder(BorderFactory.createEmptyBorder());
		colorChooser2.setMargin(new Insets(-2,-2,-1,-1));
		colorChooser2.setIcon(new ImageIcon(new ImageIcon("icon/ColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc2 = new ColorChooser();
				cc2.setColorChooseed(Color.BLACK);
				cc2.displayColorChooser(colorChooser2);
				color2 = cc2.getColorChoosed() ;
				layerMenu.refreshLayer(sview);

			}});

		draw = new Button("icon/dessine.png",new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				draw.setSelected(!draw.isSelected());
				ShapesController controller = (ShapesController)sview.getController();
				if (draw.isSelected()) {
					
					ImageIcon im = new ImageIcon(new ImageIcon("icon/dessine.png").getImage().getScaledInstance(31, 31, Image.SCALE_SMOOTH));
					ImageIcon brush = new ImageIcon(new ImageIcon("icon/stylo.png").getImage().getScaledInstance(96,96, Image.SCALE_SMOOTH));
					sview.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(brush.getImage(), new Point(2, im.getIconHeight()), "dessine cursor"));
					controller.setCrayon();
				} else {
					sview.setCursor(Cursor.getDefaultCursor());
					controller.setCrayon();
				}
				layerMenu.refreshLayer(sview);
			}
		});

		repere = new Button("icon/repere.png",new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				repere.setSelected(!repere.isSelected());
				ShapesController controller = (ShapesController)sview.getController();
				if (repere.isSelected()) {
					
					ImageIcon im = new ImageIcon(new ImageIcon("icon/repere.png").getImage().getScaledInstance(31, 31, Image.SCALE_SMOOTH));
					controller.setRepere();
				} else {
					controller.cutRepere();
				}
				layerMenu.refreshLayer(sview);
			}
		});

		//pipette button
		Button pipette = new Button("icon/pipette.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Pipette pp = new Pipette(cc, colorChooser, sview);
			}});
		buttons.add(pipette);
		pipette.setBackground(new Color(239, 239, 239));


		//darktheme button
		ToggleButton darkTheme = new ToggleButton("icon/LightThemeIcon.png","icon/DarkThemeIcon.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					DarkTheme dt = new DarkTheme(e, jtoolBar, menuBar,layerMenu, sview, buttonMap);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				layerMenu.refreshLayer(sview);
			}});

		//AddRect button
		JButton addRect = new Button("icon/addRect.png","icon/addRect.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				color1 = new Pipette(colorChooser).getColorPicked();
				AddRect addRect = new AddRect();
				addRect.add(sview, color2, color1);
				sview.repaint();
				layerMenu.refreshLayer(sview);
			}});

		//AddCircle button
		JButton addCircle = new Button("icon/addCircle.png","icon/addCircle.png",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color1 = new Pipette(colorChooser).getColorPicked();
				AddCircle addCircle = new AddCircle();
				addCircle.add(sview,color2,color1);
				sview.repaint();
				layerMenu.refreshLayer(sview);
			}
		});
		
		//AddText button
		JButton addText = new Button("icon/addText.png","icon/addText.png",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color1 = new Pipette(colorChooser).getColorPicked();
				AddText addText = new AddText();
				addText.add(sview,color1,color2);

				sview.repaint();
				layerMenu.refreshLayer(sview);
			}
		});

		//AddTriangle 
				JButton addTriangle = new Button("icon/addTriangle.png","icon/addTriangle.png",new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						color1 = new Pipette(colorChooser).getColorPicked();
						AddTriangle addTriangle = new AddTriangle();
						addTriangle.add(sview, color2, color1);
						sview.repaint();
						layerMenu.refreshLayer(sview);
					}});


		//bucket button
		Button bucket = new Button("icon/bucket.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				color1 = new Pipette(colorChooser).getColorPicked();
				Bucket bu = new Bucket(color1,sview);
				layerMenu.refreshLayer(sview);

			}});
		
		JButton sizeChooser = new Button("icon/size.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				color1 = new Pipette(colorChooser).getColorPicked();
				SizeChooser sizeChooser = new SizeChooser();
				sizeChooser.add(sview);
				sview.repaint();
				layerMenu.refreshLayer(sview);
			}});

		buttonMap.put("addRect",addRect);
		buttonMap.put("addText",addText);
		buttonMap.put("addTriangle",addTriangle);
		buttonMap.put("addCircle",addCircle);
		buttonMap.put("bucket",bucket);
		buttonMap.put("pipette",pipette);
		buttonMap.put("Change Theme",darkTheme);
		buttonMap.put("ColorChooser",colorChooser);
		buttonMap.put("ColorChooser2",colorChooser2);
		buttonMap.put("Brush",draw);
		buttonMap.put("Repere",repere);
		buttonMap.put("Size", sizeChooser);

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
		
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(repere);
		
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(sizeChooser);


		//Enlever le focus sur chaque bouttons 
		for(int i=0; i<jtoolBar.getComponentCount();i++) {
			String className = (jtoolBar.getComponent(i).getClass()).toString();
			if(className.equals("class javax.swing.JButton") || className.equals("class graphics.menus.toolBar.ToggleButton") || className.equals("class graphics.menus.toolBar.Button")) {
				jtoolBar.getComponent(i).setFocusable(false);
			}
		}
	}

	public Map getButtonMap(){
		return this.buttonMap;
	}

	public JToolBar getJToolBar(){
		return this.jtoolBar;
	}
	public Color getColor1(){
		return color1;
	}

	public void refreshLayerMenu(){this.layerMenu.refreshLayer(sview);
	}
	//Commentaire MAX
}
