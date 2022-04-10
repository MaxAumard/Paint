package graphics.menus.extensions;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BoxLayout;
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
				"New Rectangle", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && textNotEmpty()) {
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
	}
	
	public void textNotEmpty() {
		!xField.getText().isEmpty() && !yField.getText().isEmpty() && !longueur.getText().isEmpty() && !hauteur.getText().isEmpty()
	}
}
