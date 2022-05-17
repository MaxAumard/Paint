package graphics.menus.extensions;

import java.awt.*;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

public class AddText {

	public void add(ShapesView sview,Color colorBorder,Color colorFill) {

		JTextField textSize = new JTextField("10",5);
		JTextField textInput = new JTextField(5);
		JCheckBox tFill = new JCheckBox("Transparent Background");
		JCheckBox tBorder = new JCheckBox("Transparent Font");
		JCheckBox he = new JCheckBox("Helvetica");
		he.setSelected(true);
		JCheckBox tr = new JCheckBox("TimesRoman");
		JCheckBox cr = new JCheckBox("Courrier");
		JCheckBox ar = new JCheckBox("Arial");
		JCheckBox ca = new JCheckBox("Calibri");
		JCheckBox cs = new JCheckBox("Comic Sans");


		JPanel myPanel = new JPanel();

		myPanel.setLayout(new GridLayout(6,2,4,10));
		myPanel.add(new JLabel("texte:"));
		myPanel.add(textInput);
		myPanel.add(new JLabel("taille:"));
		myPanel.add(textSize);
		myPanel.add(tFill);
		myPanel.add(tBorder);
		myPanel.add(he);
		myPanel.add(tr);
		myPanel.add(cr);
		myPanel.add(ar);
		myPanel.add(ca);
		myPanel.add(cs);


		int result = JOptionPane.showConfirmDialog(null, myPanel,"New Text", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION && this.tester(myPanel)) {
			int x = 15;
			int y = 15;
			int size = Integer.valueOf(textSize.getText());

			if(he.isSelected()) {  //FontAttributes(Font font, Color fontColor)
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"Helvetica" );
			}
			else if(tr.isSelected()){
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"TimesRoman" );
			}
			else if(cr.isSelected()) {
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"Courrier" );
	
			}
			else if(ar.isSelected()) {
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"Arial" );
				
			}
			else if(ca.isSelected()) {
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"Calibri" );
				
			}
			else if(cs.isSelected()) {
				text(x,  y, textInput, tFill,tBorder,colorBorder,colorFill,sview,size,"ComicSans" );
				
			}
			else {
				SText text = new SText(new Point(x,y),textInput.getText());
				text.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
				text.addAttributes(new SelectionAttributes());
				SCollection coll = (SCollection) sview.getModel();
				coll.add(text);
			}
			
			
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

	public boolean tester(JPanel panel) {
		int i=0;
		for(Component c : panel.getComponents()){
			if(c.getClass() == JCheckBox.class){
				if(((JCheckBox) c).isSelected()){
					i++;
				}
			}
		}
		return i != 0;
	}
	
	public void text(int x, int y, JTextField textInput, JCheckBox tFill, JCheckBox tBorder, Color colorBorder, Color colorFill, ShapesView sview, int size, String font ) {
		SText text = new SText(new Point(x,y),textInput.getText());
		text.addAttributes(new ColorAttributes(!tBorder.isSelected(),!tFill.isSelected(),colorBorder,colorFill));
		text.addAttributes(new SelectionAttributes());
		text.addAttributes(new FontAttributes(new Font(font, Font.PLAIN,size), Color.BLACK));
		SCollection coll = (SCollection) sview.getModel();
		coll.add(text);
	}

}
