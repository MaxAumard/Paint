package graphics.menus.menuBar;


import graphics.shapes.ui.ShapesView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MenuBar extends java.awt.MenuBar {
    JMenuBar menuBar= new JMenuBar();
    public MenuBar(ShapesView sview) throws IOException {
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
        newItem.addActionListener(this::mnuNewListerner);
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
        saveasItem.setBackground(new Color(239, 239, 239));
        saveasItem.setForeground(Color.black);
        saveasItem.setIcon(setImageSize("icon/saveas.png"));
        saveasItem.setBorderPainted(false);
        file.add(saveasItem); 
    }
    public ImageIcon setImageSize(String path) throws IOException {
        return new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(20,20,0));
    }
    public JMenuBar getMyJMenuBar(){
        return this.menuBar;
    }
    
    private void mnuNewListerner(ActionEvent event) {
    	System.out.println("Creation New file");

    }
    
    private void mnuSaveListerner(ActionEvent event) {
    	System.out.println("File save");
    }
}
