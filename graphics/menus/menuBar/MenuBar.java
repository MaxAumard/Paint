package graphics.menus.menuBar;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MenuBar extends java.awt.MenuBar {
    JMenuBar menuBar= new JMenuBar();
    public MenuBar() throws IOException {
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

        newItem.setBackground(new Color(239, 239, 239));
        newItem.setForeground(Color.black);
        newItem.setMnemonic('N');
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newItem.setIcon(setImageSize("icon/LightThemeIcon.png"));
        newItem.setBorderPainted(false);
        file.add(newItem);


        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setBackground(new Color(239, 239, 239));
        saveItem.setForeground(Color.black);
        saveItem.setMnemonic('S');
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        saveItem.setBorderPainted(false);
        file.add(saveItem);
    }
    public ImageIcon setImageSize(String path) throws IOException {
        return new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(20,20,0));
    }
    public JMenuBar getMyJMenuBar(){
        return this.menuBar;
    }
}
