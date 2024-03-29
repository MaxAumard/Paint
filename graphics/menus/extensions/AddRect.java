package graphics.menus.extensions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	
	public AddRect() {}

	public void add(ShapesView sview,Color colorBorder,Color colorFill) {

		JTextField longueur = new JTextField(5);
		JTextField hauteur = new JTextField(5);
		JCheckBox tFill = new JCheckBox("Transparent Rectangle");
		JCheckBox tBorder = new JCheckBox("Transparent Border");

		
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(3,4,4,10));
		myPanel.add(new JLabel("Longueur:"));
		myPanel.add(longueur);
		myPanel.add(new JLabel("Hauteur:"));
		myPanel.add(hauteur);
		myPanel.add(tFill);
		myPanel.add(tBorder);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Rectangle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel,tFill,tBorder,colorBorder)) {
			int l = Integer.valueOf(longueur.getText());
			int h = Integer.valueOf(hauteur.getText());
			
			SRectangle r = new SRectangle(new Point(15,15),l,h);
			r.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
			r.addAttributes(new SelectionAttributes());
			SCollection coll = (SCollection) sview.getModel();
			coll.add(r);
		}
		else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {}
		else {
			JPanel errorPanel = new JPanel();
			errorPanel.add(new JLabel("Please enter valid numbers"));
			int showError = JOptionPane.showConfirmDialog(null, errorPanel,"Error",JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.add(sview,colorBorder,colorFill);
			}
		}
	}
	
	public boolean tester(JPanel jpanel,JCheckBox ch1,JCheckBox ch2,Color colorBorder) {
		for(int i=0;i< jpanel.getComponentCount();i++) {
			if((jpanel.getComponent(i).getClass().toString()).equals("class javax.swing.JTextField")) {
				if(textIsEmpty((JTextField)jpanel.getComponent(i))) {return false;};
				if(!textIsInt((JTextField)jpanel.getComponent(i))) {
					return false;};
			}
		}
		if(ch1.isSelected() && ch2.isSelected()) {return false;}
		return true;
	}
	
	public boolean textIsEmpty(JTextField field) {
		if(field.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean textIsInt(JTextField field) {		
		try {
		    Integer.parseInt(field.getText());
		    return true;
		} catch (NumberFormatException e) {
		    return false;
		}
	}
}
