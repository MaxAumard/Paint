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
	
	public void add(ShapesView sview,Color colorBorder,Color colorFill) {
				
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField longueur = new JTextField(5);
		JTextField hauteur = new JTextField(5);
		JCheckBox tFill = new JCheckBox("tFill");
		JCheckBox tBorder = new JCheckBox("bFill");

		
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(3,4,4,10));
		myPanel.add(new JLabel("Position x:"));
		myPanel.add(xField);
		myPanel.add(new JLabel("Position y:"));
		myPanel.add(yField);
		myPanel.add(new JLabel("Longueur:"));
		myPanel.add(longueur);
		myPanel.add(new JLabel("Hauteur:"));
		myPanel.add(hauteur);
		myPanel.add(tFill);
		(tFill).setLabel("Transparent Fill");;
		myPanel.add(tBorder);
		(tBorder).setLabel("Transparent Border");

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Rectangle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel,tFill,tBorder,colorBorder)) {
			int x = Integer.valueOf(xField.getText());
			int y = Integer.valueOf(yField.getText());
			int l = Integer.valueOf(longueur.getText());
			int h = Integer.valueOf(hauteur.getText());
			
			SRectangle r = new SRectangle(new Point(x,y),l,h);
			r.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
			System.out.println(colorBorder);
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
				this.add(sview,colorBorder,colorFill);
			}
		}
	}
	
	public boolean tester(JPanel jpanel,JCheckBox ch1,JCheckBox ch2,Color colorBorder) {
		for(int i=0;i< jpanel.getComponentCount();i++) {
			if((jpanel.getComponent(i).getClass().toString()).equals("class javax.swing.JTextField")) {
				if(textIsEmpty((JTextField)jpanel.getComponent(i))) {return false;};
				if(!textIsInt((JTextField)jpanel.getComponent(i))) {
					System.out.println("isInt");
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
		    System.out.println("Input are not numbers");
		    return false;
		}
	}
}
