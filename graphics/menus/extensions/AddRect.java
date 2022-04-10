package graphics.menus.extensions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddRect {
	
	public void add(ShapesView sview) {
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField longueur = new JTextField(5);
		JTextField hauteur = new JTextField(5);

		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(3,4,4,10));
		myPanel.add(new JLabel("x:"));
		myPanel.add(xField);
		myPanel.add(new JLabel("y:"));
		myPanel.add(yField);
		myPanel.add(new JLabel("longueur:"));
		myPanel.add(longueur);
		myPanel.add(new JLabel("Hauteur:"));
		myPanel.add(hauteur);
		
		Color fillColor = new Color(0,0,0);
		JButton colorChooser = new JButton();
		colorChooser.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChooser cc = new ColorChooser();
				cc.setColorChooseed(Color.BLACK);
				cc.displayColorChooser(colorChooser);
			}});
		
		Color borderColor = new Color(0,0,0);
		JButton colorChooser2 = new JButton();
		colorChooser2.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		colorChooser2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ColorChooser cc2 = new ColorChooser();
				cc2.setColorChooseed(Color.BLACK);
				cc2.displayColorChooser(colorChooser2);
			}});
		
		myPanel.add(new JLabel("Fill color"));
		myPanel.add(colorChooser);
		myPanel.add(new JLabel("Border color"));
		myPanel.add(colorChooser2);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Rectangle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && textNotEmpty(xField,yField,longueur,hauteur) && textIsInt(xField,yField,longueur,hauteur)) {
			int x = Integer.valueOf(xField.getText());
			int y = Integer.valueOf(yField.getText());
			int l = Integer.valueOf(longueur.getText());
			int h = Integer.valueOf(hauteur.getText());
			
			SRectangle r = new SRectangle(new Point(x,y),l,h);
			r.addAttributes(new ColorAttributes(true,true,borderColor,fillColor));
			r.addAttributes(new SelectionAttributes());
			SCollection coll = (SCollection) sview.getModel();
			coll.add(r);
		}
		else if(result == JOptionPane.CANCEL_OPTION) {}
		else {
			JPanel errorPanel = new JPanel();
			errorPanel.add(new JLabel("Please enter valid numbers"));
			int showError = JOptionPane.showConfirmDialog(null, errorPanel,"Error",JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.add(sview);
			}
		}
	}
	
	public boolean textNotEmpty(JTextField xField,JTextField yField,JTextField lField,JTextField hField) {
		if(!xField.getText().isEmpty() && !yField.getText().isEmpty() && !lField.getText().isEmpty() && !hField.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean textIsInt(JTextField xField,JTextField yField,JTextField lField,JTextField hField) {		
		try {
		    int xValue = Integer.parseInt(xField.getText());
		    int yValue = Integer.parseInt(yField.getText());
		    int lValue = Integer.parseInt(lField.getText());
		    int hValue = Integer.parseInt(hField.getText());
		    return true;
		} catch (NumberFormatException e) {
		    System.out.println("Input are not numbers");
		    return false;
		}
	}
}
