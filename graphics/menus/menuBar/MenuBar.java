package graphics.menus.menuBar;


import graphics.menus.toolBar.ToolBar;
import graphics.shapes.ui.ShapeDraftman;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;

import graphics.menus.extensions.AddImage;
import graphics.menus.extensions.Save;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.SelectionAttributes;

public class MenuBar extends java.awt.MenuBar {
	JMenuBar menuBar;
	private ShapesController controller;
	ShapesView sview;
	private String fileName;
	private ToolBar toolbar;
	
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
		newItem.setIcon(new ImageIcon(new ImageIcon("icon/new.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));//icone
		newItem.setBorderPainted(false);
		file.add(newItem);

		JMenuItem openItem = new JMenuItem("Open File...");
		openItem.setBackground(new Color(239, 239, 239));//fond
		openItem.setForeground(Color.black);//text
		openItem.setIcon(new ImageIcon(new ImageIcon("icon/open.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		openItem.setBorderPainted(false);
		file.add(openItem);
		
		JMenuItem exportToPng = new JMenuItem("Export to PNG");
		exportToPng.addActionListener( this::exportToPng);
		exportToPng.setBackground(new Color(239, 239, 239));//fond
		exportToPng.setForeground(Color.black);//text
		exportToPng.setMnemonic('P');//demande d'ouvrir le menu
		exportToPng.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));//pas besoin d'ouvrir le menu
		exportToPng.setIcon(new ImageIcon(new ImageIcon("icon/export.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));//icone
		exportToPng.setBorderPainted(false);
		file.add(exportToPng);

		file.addSeparator();

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this::mnuSaveListerner);
		saveItem.setBackground(new Color(239, 239, 239));
		saveItem.setForeground(Color.black);
		saveItem.setMnemonic('S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		saveItem.setIcon(new ImageIcon(new ImageIcon("icon/save.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		saveItem.setBorderPainted(false);
		file.add(saveItem);

		JMenuItem saveasItem = new JMenuItem("Save as...");
		saveasItem.addActionListener(this::mnuSaveAsListerner);
		saveasItem.setBackground(new Color(239, 239, 239));
		saveasItem.setForeground(Color.black);
		saveasItem.setIcon(new ImageIcon(new ImageIcon("icon/saveas.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		saveasItem.setBorderPainted(false);
		file.add(saveasItem);

		file.addSeparator();

		JMenuItem openPictureItem = new JMenuItem("OpenPicture...");
		openPictureItem.addActionListener(this::mnuOpenPictureListerner);
		openPictureItem.setBackground(new Color(239, 239, 239));
		openPictureItem.setForeground(Color.black);
		openPictureItem.setIcon(new ImageIcon(new ImageIcon("icon/OpenPicture.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
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
		CopyItem.setIcon(new ImageIcon(new ImageIcon("icon/copy.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		CopyItem.setMnemonic('C');
		CopyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		CopyItem.setBorderPainted(false);
		home.add(CopyItem);

		JMenuItem PasteItem = new JMenuItem("Paste");
		PasteItem.addActionListener(this::pasteShape);
		PasteItem.setBackground(new Color(239, 239, 239));//fond
		PasteItem.setForeground(Color.black);//text
		PasteItem.setIcon(new ImageIcon(new ImageIcon("icon/paste.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
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
		addPicture.setIcon(new ImageIcon(new ImageIcon("icon/addPicture.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addPicture.setBorderPainted(false);
		shapes.add(addPicture);

		shapes.addSeparator();

		JMenuItem addRectangle = new JMenuItem("Add Rectangle");
		addRectangle.addActionListener(this::addRectangle);
		addRectangle.setBackground(new Color(239, 239, 239));//fond
		addRectangle.setForeground(Color.black);//text
		addRectangle.setIcon(new ImageIcon(new ImageIcon("icon/addRect.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addRectangle.setBorderPainted(false);
		shapes.add(addRectangle);

		JMenuItem addCircle = new JMenuItem("Add Circle");
		addCircle.addActionListener(this::addCircle);
		addCircle.setBackground(new Color(239, 239, 239));//fond
		addCircle.setForeground(Color.black);//text
		addCircle.setIcon(new ImageIcon(new ImageIcon("icon/addCircle.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addCircle.setBorderPainted(false);
		shapes.add(addCircle);

		JMenuItem addTriangle = new JMenuItem("Add Triangle");
		addTriangle.addActionListener(this::addTriangle);
		addTriangle.setBackground(new Color(239, 239, 239));//fond
		addTriangle.setForeground(Color.black);//text
		addTriangle.setIcon(new ImageIcon(new ImageIcon("icon/addTriangle.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addTriangle.setBorderPainted(false);
		shapes.add(addTriangle);

		JMenuItem addText = new JMenuItem("Add Text");
		addText.addActionListener(this::addText);
		addText.setBackground(new Color(239, 239, 239));//fond
		addText.setForeground(Color.black);//text
		addText.setIcon(new ImageIcon(new ImageIcon("icon/addText.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		addText.setBorderPainted(false);
		shapes.add(addText);

		JMenu Extensions = new JMenu("Extensions");
		Extensions.setForeground(Color.black);
		Extensions.setMnemonic('E');
		Extensions.setBorderPainted(false);

		menuBar.add(Extensions);

		final JCheckBoxMenuItem viewAddText = new JCheckBoxMenuItem("Add Text", true);
		viewAddText.setIcon(new ImageIcon(new ImageIcon("icon/addText.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		Extensions.add(viewAddText);

		final JCheckBoxMenuItem viewChangeTheme = new JCheckBoxMenuItem("Change Theme", true);
		viewChangeTheme.setIcon(new ImageIcon(new ImageIcon("icon/LightThemeIcon.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		Extensions.add(viewChangeTheme);

		final JCheckBoxMenuItem viewPippette = new JCheckBoxMenuItem("Pippette", true);
		viewPippette.setIcon(new ImageIcon(new ImageIcon("icon/pipette.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		Extensions.add(viewPippette);

		final JCheckBoxMenuItem viewBucket = new JCheckBoxMenuItem("Bucket", true);
		viewBucket.setIcon(new ImageIcon(new ImageIcon("icon/bucket.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		Extensions.add(viewBucket);

		final JCheckBoxMenuItem viewBrush = new JCheckBoxMenuItem("Brush", true);
		viewBrush.setIcon(new ImageIcon(new ImageIcon("icon/dessine.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
		Extensions.add(viewBrush);

		for(int i = 0; i<5;i++){
			JCheckBoxMenuItem chckbx = (JCheckBoxMenuItem) Extensions.getItem(i);
			chckbx.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(chckbx.getText(),chckbx.getState());
				}
			});
		}

		JMenu layer = new JMenu("Layer");
		layer.setForeground(Color.black);
		layer.setMnemonic('L');
		layer.setBorderPainted(false);
		menuBar.add(layer);

		JMenuItem setUp = new JMenuItem("Move layer up");
		setUp.addActionListener(this::addText);
		setUp.setBackground(new Color(239, 239, 239));//fond
		setUp.setForeground(Color.black);//text
		layer.add(setUp);

		JMenuItem setDown = new JMenuItem("Move layer down");
		setDown.addActionListener(this::addText);
		setDown.setBackground(new Color(239, 239, 239));//fond
		setDown.setForeground(Color.black);//text
		layer.add(setDown);
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

	public void setToolBar(ToolBar t){
		this.toolbar = t;
	}

	public void setVisible(String s, boolean bool){
		Component button = (Component) toolbar.getButtonMap().get(s);
		button.setVisible(bool);
	}
	
	public void exportToPng(ActionEvent e) {
		
		BufferedImage bufferedImage = new BufferedImage(sview.getWidth(), sview.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = bufferedImage.createGraphics();
		
		sview.printAll(graphics);
		graphics.dispose();
		
		JFileChooser pathField =  new JFileChooser();
		pathField.showSaveDialog(pathField);

		String path = pathField.getSelectedFile().getAbsolutePath() + ".png";
		
		File file = new File(path);
		try {
			ImageIO.write(bufferedImage, "PNG", file);
			System.out.println(file.getAbsolutePath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}