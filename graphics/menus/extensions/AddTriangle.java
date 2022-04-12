package graphics.menus.extensions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCollection;
import graphics.shapes.STriangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddTriangle {
	
	public AddTriangle() {}

	public void add(ShapesView sview,Color colorBorder,Color colorFill) {
				
		JTextField xp1Field = new JTextField(5);
		JTextField yp1Field = new JTextField(5);
		JTextField xp2Field = new JTextField(5);
		JTextField yp2Field = new JTextField(5);
		JTextField xp3Field = new JTextField(5);
		JTextField yp3Field = new JTextField(5);
		JCheckBox tFill = new JCheckBox("Transparent Triangle");
		JCheckBox tBorder = new JCheckBox("Transparent Border");

		
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(8,6,6,10));
		myPanel.add(new JLabel("Position X de p1:"));
		myPanel.add(xp1Field);
		myPanel.add(new JLabel("Position Y de p1:"));
		myPanel.add(yp1Field);
		myPanel.add(new JLabel("Position X de p2:"));
		myPanel.add(xp2Field);
		myPanel.add(new JLabel("Position Y de p2:"));
		myPanel.add(yp2Field);
		myPanel.add(new JLabel("Position X de p3:"));
		myPanel.add(xp3Field);
		myPanel.add(new JLabel("Position Y de p3:"));
		myPanel.add(yp3Field);
		myPanel.add(tFill);
		myPanel.add(tBorder);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Triangle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel,tFill,tBorder,colorBorder)) {
			int xp1 = Integer.valueOf(xp1Field.getText());
			int yp1 = Integer.valueOf(yp1Field.getText());
			int xp2 = Integer.valueOf(xp2Field.getText());
			int yp2 = Integer.valueOf(yp2Field.getText());
			int xp3 = Integer.valueOf(xp3Field.getText());
			int yp3 = Integer.valueOf(yp3Field.getText());
			
			STriangle tr = new STriangle(new Point(xp1, yp1), new Point(xp2,yp2), new Point(xp3,yp3),3);
			tr.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
			tr.addAttributes(new SelectionAttributes());
			SCollection coll = (SCollection) sview.getModel();
			coll.add(tr);
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