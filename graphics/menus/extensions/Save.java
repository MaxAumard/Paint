package graphics.menus.extensions;

import java.awt.GridLayout;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesView;

import java.util.regex.*;
public class Save {
	private String path;
	
	public void saveAs(ShapesView sview) {

		JFileChooser pathField =  new JFileChooser();
		int result = pathField.showSaveDialog(pathField);
		if(result == JFileChooser.APPROVE_OPTION) {
			String path = pathField.getSelectedFile().getAbsolutePath() + ".view";
			System.out.println(path);

			File myFile = new File(path);
			try {
				if (!myFile.createNewFile()) {

				}
				transcript(sview, path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//SCollection coll = (SCollection) sview.getModel();
			System.out.println("Save As... fonction");
			this.path = path;
		}
		else{}
	}

	private void transcript(ShapesView sview, String path) {
		try {
			FileWriter fw = new FileWriter(path);
			SCollection scoll = (SCollection) sview.getModel();
			
			for(Shape s : scoll.collection) {

				String values = s.getValues();
				String[] sclass = values.split(";");
		        for(int i = 0 ; i< sclass.length ; i++) {
		        	fw.write( sclass[i] + "\r\n");
		        }
		        fw.write("\r\n");
			}
			fw.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(ShapesView sview , String path) {

		this.transcript(sview, path);
	}

	public String getpath() {
		return this.path;
	}
}
