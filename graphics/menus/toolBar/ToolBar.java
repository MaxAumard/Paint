package graphics.menus.toolBar;

import graphics.menus.extensions.ColorChooser;
import graphics.menus.extensions.DarkTheme;
import graphics.menus.extensions.Pipette;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
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
	public ToolBar(JMenuBar menuBar, ShapesView sview) throws IOException {
		super();
		//cration of the toolBar
		jtoolBar = new JToolBar();
		//prevent flotability
		jtoolBar.setFloatable(false);
		//toolBar buttons will be stored here
		Collection buttons = new ArrayList<Button>();



		//colorChooser button
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
				new DarkTheme(e, jtoolBar, menuBar, sview, buttons, colorChooser);
			}});

		//AddRect button
		JButton addRect = new JButton();
		addRect.setBackground(Color.black);
		addRect.setBorder(BorderFactory.createEmptyBorder());
		addRect.setMargin(new Insets(-2,-2,-1,-1));
		addRect.setIcon(new ImageIcon(new ImageIcon("icon/addRect.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addRect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField xField = new JTextField(10);
				JTextField yField = new JTextField(10);
				JTextField longueur = new JTextField(10);
				JTextField hauteur = new JTextField(10);

				JPanel myPanel = new JPanel();
				myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
				myPanel.add(new JLabel("x:"));
				myPanel.add(xField);
				myPanel.add(new JLabel("y:"));
				myPanel.add(yField);
				myPanel.add(new JLabel("longueur:"));
				myPanel.add(longueur);
				myPanel.add(new JLabel("Hauteur:"));
				myPanel.add(hauteur);

				int result = JOptionPane.showConfirmDialog(null, myPanel, 
						"Please Enter X, Y, Lenght and Height Values", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION && !xField.getText().isEmpty() && !yField.getText().isEmpty() && !longueur.getText().isEmpty() && !hauteur.getText().isEmpty()) {
					int x = Integer.valueOf(xField.getText());
					int y = Integer.valueOf(yField.getText());
					int l = Integer.valueOf(longueur.getText());
					int h = Integer.valueOf(hauteur.getText());
					SRectangle r = new SRectangle(new Point(x,y),l,h);
					r.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.PINK));
					r.addAttributes(new SelectionAttributes());
					SCollection coll = (SCollection) sview.getModel();
					coll.add(r);
				}
				sview.repaint();
			}});

		//add icons to toolbar
		jtoolBar.addSeparator(new Dimension(15,0));
		jtoolBar.add(darkTheme);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(pipette);
		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(addRect);




		jtoolBar.addSeparator(new Dimension(10,0));
		jtoolBar.add(colorChooser);

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
		pipette.setFocusable(false);
		colorChooser.setFocusable(false);
		darkTheme.setFocusable(false);
		addRect.setFocusable(false);
	}



	public JToolBar getJToolBar(){
		return this.jtoolBar;
	}
}
