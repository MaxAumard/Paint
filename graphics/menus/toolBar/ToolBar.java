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


	public ToolBar(JMenuBar menuBar, ShapesView sview) throws IOException {
		super();
		//cration of the toolBar
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
		colorChooser.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc = new ColorChooser();
				cc.setColorChooseed(Color.BLACK);
				cc.displayColorChooser(colorChooser);
				System.out.println(cc.getColorChoosed());
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
				System.out.println(cc.getColorChoosed());
				color2 = cc2.getColorChoosed() ;

			}});


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
				DarkTheme dt = new DarkTheme(e, jtoolBar, menuBar, sview, buttons, colorChooser);
			}});


		//AddRect button
		JButton addRect = new JButton();
		addRect.setBackground(Color.black);
		addRect.setBorder(BorderFactory.createEmptyBorder());
		addRect.setMargin(new Insets(-2,-2,-1,-1));
		addRect.setIcon(new ImageIcon(new ImageIcon("icon/addRect.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addRect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AddRect addRect = new AddRect();
					addRect.add(sview,color1,color2);
					sview.repaint();
				}
			}
		
		);
		
		//AddCircle button
		JButton addCircle = new JButton();
		addCircle.setBackground(Color.black);
		addCircle.setBorder(BorderFactory.createEmptyBorder());
		addCircle.setMargin(new Insets(-2,-2,-1,-1));
		addCircle.setIcon(new ImageIcon(new ImageIcon("icon/addCircle.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addCircle.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AddCircle addCircle = new AddCircle();
					addCircle.add(sview,color1,color2);
					sview.repaint();
				}
			}
		
		);
		
		//AddText button
				JButton addText = new JButton();
				addText.setBackground(Color.black);
				addText.setBorder(BorderFactory.createEmptyBorder());
				addText.setMargin(new Insets(-2,-2,-1,-1));
				addText.setIcon(new ImageIcon(new ImageIcon("icon/addText.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
				addText.addActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							AddText addText = new AddText();
							addText.add(sview,color1,color2);
							sview.repaint();
						}
					}
				
				);

		//bucket button
		Button bucket = new Button("icon/bucket.png",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Bucket bu = new Bucket();

			}});
		buttons.add(pipette);
		pipette.setBackground(new Color(239, 239, 239));

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
		jtoolBar.add(colorChooser);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(colorChooser2);

		/*// ////////////////////////////////////////////////////////////////////////////////
        JButton btnWhite = createButton("icon/sunWhiteTheme.png");
        btnWhite.setMargin(new Insets(0,0,0,0));
        btnWhite.setBackground(new Color(240,240,240));
        toolBar.add(btnWhite);
        ///Button dark
        JButton btnDark = createButton("icon/sunDarkTheme.png");
        btnDark.setBackground(new Color(27,27,27));
        btnDark.setForeground(Color.GRAY);
        btnDark.setMargin(new Insets(0,0,0,0));
        toolBar.add(btnDark);
		 */

		//Enlever le focus sur chaque bouttons 
		for(int i=0; i<jtoolBar.getComponentCount();i++) {
			String className = (jtoolBar.getComponent(i).getClass()).toString();
			if(className.equals("class javax.swing.JButton") || className.equals("class graphics.menus.toolBar.ToggleButton") || className.equals("class graphics.menus.toolBar.Button")) {
				jtoolBar.getComponent(i).setFocusable(false);
			}
		}
	}



	public JToolBar getJToolBar(){
		return this.jtoolBar;
	}
}
