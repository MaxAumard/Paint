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

public class FontChooser {

	public void add(ShapesView sview) {

		JCheckBox he = new JCheckBox("Helvetica");
		JCheckBox tr = new JCheckBox("TimesRoman");
		JCheckBox cr = new JCheckBox("Courrier");
		JCheckBox ar = new JCheckBox("Arial");
		JCheckBox ca = new JCheckBox("Calibri");


		JPanel myPanel = new JPanel();

		myPanel.setLayout(new GridLayout(3,4,4,10));
		myPanel.add(he);
		myPanel.add(tr);
		myPanel.add(cr);
		myPanel.add(ar);
		myPanel.add(ca);


		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Font", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			if(he.isSelected()) {
			
			}	
			if(tr.isSelected()) {

			}	
			if(cr.isSelected()) {

			}	
			if(ar.isSelected()) {

			}	
			if(ca.isSelected()) {

			}	
		}
		else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {}
		else {
			JPanel errorPanel = new JPanel();
			errorPanel.add(new JLabel("Please enter valid numbers"));
			int showError = JOptionPane.showConfirmDialog(null, errorPanel,"Error",JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				this.add(sview);
			}
		}

	}
}
