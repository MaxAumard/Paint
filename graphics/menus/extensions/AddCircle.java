package graphics.menus.extensions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.*;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddCircle {

	public void add(ShapesView sview,Color colorBorder,Color colorFill) {

		JTextField rayon = new JTextField(5);
		JCheckBox tFill = new JCheckBox("Transparent Circle");
		JCheckBox tBorder = new JCheckBox("Transparent Border");

		
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(4,2,4,10));
		myPanel.add(new JLabel("Rayon:"));
		myPanel.add(rayon);
		myPanel.add(tFill);
		myPanel.add(tBorder);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Circle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel,tFill,tBorder,colorBorder)) {
			int r = Integer.parseInt(rayon.getText());
			
			SCircle circle = new SCircle(new Point(15,15),r);
			circle.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
			circle.addAttributes(new SelectionAttributes());
			SCollection coll = (SCollection) sview.getModel();
			coll.add(circle);
		}
		else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			
		}
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