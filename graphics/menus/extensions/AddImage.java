package graphics.menus.extensions;

import java.awt.GridLayout;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddImage {
	
	public void add(ShapesView sview) {
				
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JFileChooser pathField =  new JFileChooser();
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(3,2,4,10));
		myPanel.add(new JLabel("Position x:"));
		myPanel.add(xField);
		myPanel.add(new JLabel("Position y:"));
		myPanel.add(yField);
		
		myPanel.add(pathField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Image", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel)) {
			int x = Integer.valueOf(xField.getText());
			int y = Integer.valueOf(yField.getText());
			String path = pathField.getSelectedFile().getAbsolutePath();
			SCollection coll = (SCollection) sview.getModel();
			try {
				SImage image = new SImage(path, new Point(x,y), sview);
				image.addAttributes(new SelectionAttributes());
				coll.add(image);
				sview.repaint();
			} catch (IOException e) {
				e.printStackTrace();
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
	
	public boolean tester(JPanel jpanel) {
		for(int i=0;i< jpanel.getComponentCount();i++) {
			if((jpanel.getComponent(i).getClass().toString()).equals("class javax.swing.JTextField")) {
				if(textIsEmpty((JTextField)jpanel.getComponent(i))) {return false;};
				if(!textIsInt((JTextField)jpanel.getComponent(i))) {return false;};
			}
		}
		
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
