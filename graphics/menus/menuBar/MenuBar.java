package graphics.menus.menuBar;


import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import graphics.menus.extensions.AddImage;
import graphics.menus.extensions.Save;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.SelectionAttributes;

public class MenuBar extends java.awt.MenuBar {
	JMenuBar menuBar;
	private ShapesController controller;
	ShapesView sview;
	private String fileName; 

	public MenuBar(ShapesView sview) throws IOException {
		controller = (ShapesController) sview.getController();
		this.sview = sview;

		Font fontMenu = new Font("Sans Serif", Font.PLAIN, 15);
		UIManager.put("Menu.font", fontMenu);
		menuBar = new  JMenuBar();

		menuBar.setBorder( new EtchedBorder());
		menuBar.setBorderPainted(true);
		menuBar.add(Box.createRigidArea(new Dimension(35,25)));
		menuBar.setBackground(new Color(239, 239, 239));

		JMenu file = new JMenu("File");
		file.setForeground(Color.black);
		file.setMnemonic('F');
		file.setBorderPainted(false);
		menuBar.add(file);


		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener( this::mnuNewListerner);
		newItem.setBackground(new Color(239, 239, 239));//fond
		newItem.setForeground(Color.black);//text
		newItem.setMnemonic('N');//demande d'ouvrir le menu
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));//pas besoin d'ouvrir le menu
		newItem.setIcon(setImageSize("icon/new.png"));//icone
		newItem.setBorderPainted(false);
		file.add(newItem);

		JMenuItem openItem = new JMenuItem("Open File...");
		openItem.setBackground(new Color(239, 239, 239));//fond
		openItem.setForeground(Color.black);//text
		openItem.setIcon(setImageSize("icon/open.png"));
		openItem.setBorderPainted(false);
		file.add(openItem);

		file.addSeparator();

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this::mnuSaveListerner);
		saveItem.setBackground(new Color(239, 239, 239));
		saveItem.setForeground(Color.black);
		saveItem.setMnemonic('S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		saveItem.setIcon(setImageSize("icon/save.png"));
		saveItem.setBorderPainted(false);
		file.add(saveItem); 

		JMenuItem saveasItem = new JMenuItem("Save as...");
		saveasItem.addActionListener(this::mnuSaveAsListerner);
		saveasItem.setBackground(new Color(239, 239, 239));
		saveasItem.setForeground(Color.black);
		saveasItem.setIcon(setImageSize("icon/saveas.png"));
		saveasItem.setBorderPainted(false);
		file.add(saveasItem);

		file.addSeparator();

		JMenuItem openPictureItem = new JMenuItem("OpenPicture...");
		openPictureItem.addActionListener(this::mnuOpenPictureListerner);
		openPictureItem.setBackground(new Color(239, 239, 239));
		openPictureItem.setForeground(Color.black);
		openPictureItem.setIcon(setImageSize("icon/OpenPicture.png"));
		openPictureItem.setBorderPainted(false);
		file.add(openPictureItem); 
		
		JMenu home = new JMenu("Home");
		home.setForeground(Color.black);
		home.setMnemonic('H');
		home.setBorderPainted(false);

		menuBar.add(home);
		
		JMenuItem CopyItem = new JMenuItem("Copy");
		CopyItem.addActionListener(this::copyShape);
		CopyItem.setBackground(new Color(239, 239, 239));//fond
		CopyItem.setForeground(Color.black);//text
		CopyItem.setIcon(setImageSize("icon/copy.png"));
		CopyItem.setMnemonic('C');
		CopyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		CopyItem.setBorderPainted(false);
		home.add(CopyItem);

		JMenuItem PasteItem = new JMenuItem("Paste");
		PasteItem.addActionListener(this::pasteShape);
		PasteItem.setBackground(new Color(239, 239, 239));//fond
		PasteItem.setForeground(Color.black);//text
		PasteItem.setIcon(setImageSize("icon/paste.png"));
		PasteItem.setMnemonic('V');
		PasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		PasteItem.setBorderPainted(false);
		home.add(PasteItem);
		
		JMenu shapes = new JMenu("Shapes");
		shapes.setForeground(Color.black);
		shapes.setMnemonic('S');
		shapes.setBorderPainted(false);
		
		menuBar.add(shapes);

		JMenuItem addPicture = new JMenuItem("Add Picture...");
		addPicture.addActionListener(this::addPicture);
		addPicture.setBackground(new Color(239, 239, 239));//fond
		addPicture.setForeground(Color.black);//text
		addPicture.setIcon(setImageSize("icon/addPicture.png"));
		addPicture.setBorderPainted(false);
		shapes.add(addPicture);
		
		shapes.addSeparator();
		
		JMenuItem addRectangle = new JMenuItem("Add Rectangle");
		addRectangle.addActionListener(this::addRectangle);
		addRectangle.setBackground(new Color(239, 239, 239));//fond
		addRectangle.setForeground(Color.black);//text
		addRectangle.setIcon(setImageSize("icon/addRect.png"));
		addRectangle.setBorderPainted(false);
		shapes.add(addRectangle);
		
		JMenuItem addCircle = new JMenuItem("Add Circle");
		addCircle.addActionListener(this::addCircle);
		addCircle.setBackground(new Color(239, 239, 239));//fond
		addCircle.setForeground(Color.black);//text
		addCircle.setIcon(setImageSize("icon/addCircle.png"));
		addCircle.setBorderPainted(false);
		shapes.add(addCircle);
		
		JMenuItem addTriangle = new JMenuItem("Add Triangle");
		addTriangle.addActionListener(this::addTriangle);
		addTriangle.setBackground(new Color(239, 239, 239));//fond
		addTriangle.setForeground(Color.black);//text
		addTriangle.setIcon(setImageSize("icon/addTriangle.png"));
		addTriangle.setBorderPainted(false);
		shapes.add(addTriangle);
		
		JMenuItem addText = new JMenuItem("Add Text");
		addText.addActionListener(this::addText);
		addText.setBackground(new Color(239, 239, 239));//fond
		addText.setForeground(Color.black);//text
		addText.setIcon(setImageSize("icon/addText.png"));
		addText.setBorderPainted(false);
		shapes.add(addText);

		JMenu Extensions = new JMenu("Extensions");
		Extensions.setForeground(Color.black);
		Extensions.setMnemonic('E');
		Extensions.setBorderPainted(false);
		
		menuBar.add(Extensions);
		
		final JCheckBoxMenuItem viewAddText = new JCheckBoxMenuItem("Add Text", true);
		viewAddText.setIcon(setImageSize("icon/addText.png"));
		Extensions.add(viewAddText);
		
		final JCheckBoxMenuItem viewChangeTheme = new JCheckBoxMenuItem("Change Theme", true);
		viewChangeTheme.setIcon(setImageSize("icon/LightThemeIcon.png"));
		Extensions.add(viewChangeTheme);
		
		final JCheckBoxMenuItem viewPippette = new JCheckBoxMenuItem("Pippette", true);
		viewPippette.setIcon(setImageSize("icon/pipette.png"));
		Extensions.add(viewPippette);
		
		final JCheckBoxMenuItem viewBucket = new JCheckBoxMenuItem("Bucket", true);
		viewBucket.setIcon(setImageSize("icon/bucket.png"));
		Extensions.add(viewBucket);
		
		final JCheckBoxMenuItem viewBrush = new JCheckBoxMenuItem("Brush", true);
		viewBrush.setIcon(setImageSize("icon/dessine.png"));
		Extensions.add(viewBrush);
	}

	public ImageIcon setImageSize(String path) throws IOException {
		return new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(20,20,0));
	}
	
	public JMenuBar getMyJMenuBar(){
		return this.menuBar;
	}

	public void Menu(ShapesController controller) {
		this.controller = controller;
	}

	private void mnuNewListerner(ActionEvent event) {
		System.out.println("Creation New file");
		newFile();
	}

	private void addRectangle(ActionEvent event) {
		System.out.println("Add Rectangle...");
	}
	
	private void addPicture(ActionEvent event) {
		System.out.println("Add Picture...");
		AddImage newImage = new AddImage();
		newImage.add(sview);
	}
	
	private void addCircle(ActionEvent event) {
		System.out.println("Add Circle...");
	}
	
	private void addText(ActionEvent event) {
		System.out.println("Add Text...");
	}
	
	private void addTriangle(ActionEvent event) {
		System.out.println("Add Triangle...");
	}

	private void mnuOpenPictureListerner(ActionEvent event) {
		System.out.println("Open Image");
		newFile();
		AddImage newImage = new AddImage();
		newImage.add(sview);
	}

	private void mnuSaveListerner(ActionEvent event) {
		System.out.println("File save");
		Save save = new Save();

		if(fileName == null) {
			save.saveAs(sview);
			this.fileName = save.getpath();
		}
		else
		{
			save.save(sview,fileName);
		}
	}

	private void mnuSaveAsListerner(ActionEvent event) {
		System.out.println("File save as...");
		Save save = new Save();
		save.saveAs(sview);
		this.fileName = save.getpath();

	}

	private void newFile() {
		SCollection tempModel = new SCollection();
		tempModel.addAttributes(new SelectionAttributes());
		sview.setModel(tempModel);
		sview.repaint();
	}

	private void copyShape(ActionEvent event) {
		System.out.println("Shape copied");
		controller.copy();
	}

	private void pasteShape(ActionEvent event) {
		System.out.println("Shape pasted");
		controller.paste();
	}
}
