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
import graphics.shapes.SLine;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.attributes.SizeAttributes;
import graphics.shapes.ui.ShapesView;
import graphics.shapes.ui.ShapesController;

public class SizeChooser {
	
	int size;


	public SizeChooser() {
	}

	public void add(ShapesView sview) {

		JTextField sizeChooser = new JTextField(5);

		JPanel myPanel = new JPanel();

		myPanel.setLayout(new GridLayout(4,2,4,10));
		myPanel.add(new JLabel("Taille du trait:"));
		myPanel.add(sizeChooser);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Size", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(sizeChooser)) {
			int s = Integer.valueOf(sizeChooser.getText());
			
			((ShapesController)sview.getController()).setPenSize(s);
			
		}
		else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {

		}
		else {
			JPanel errorPanel = new JPanel();
			errorPanel.add(new JLabel("Please enter valid numbers"));
			int showError = JOptionPane.showConfirmDialog(null, errorPanel,"Error",JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.add(sview);
			}
		}
	}

	public boolean tester(JTextField x) {
		if(textIsEmpty(x) ) {return false;};
		if(!textIsInt(x) ) {return false;}
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




