package graphics.menus.extensions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddText {

	public void add(ShapesView sview,Color colorBorder,Color colorFill) {

		JTextField xField = new JTextField("0",5);
		JTextField yField = new JTextField("0",5);
		JTextField textInput = new JTextField(5);
		JCheckBox tFill = new JCheckBox("Transparent Background");
		JCheckBox tBorder = new JCheckBox("Transparent Font");


		JPanel myPanel = new JPanel();

		myPanel.setLayout(new GridLayout(4,2,4,10));
		myPanel.add(new JLabel("Position x:"));
		myPanel.add(xField);
		myPanel.add(new JLabel("Position y:"));
		myPanel.add(yField);
		myPanel.add(new JLabel("texte:"));
		myPanel.add(textInput);
		myPanel.add(tFill);
		myPanel.add(tBorder);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Text", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(xField,yField,tFill,tBorder)) {
			int x = Integer.valueOf(xField.getText());
			int y = Integer.valueOf(yField.getText());

			SText text = new SText(new Point(x,y),textInput.getText());
			text.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
			text.addAttributes(new SelectionAttributes());
			SCollection coll = (SCollection) sview.getModel();
			coll.add(text);
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

	public boolean tester(JTextField x,JTextField y,JCheckBox ch1,JCheckBox ch2) {
		if(textIsEmpty(x) || textIsEmpty(y)) {return false;};
		if(!textIsInt(x) || !textIsInt(y)) {return false;};
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
