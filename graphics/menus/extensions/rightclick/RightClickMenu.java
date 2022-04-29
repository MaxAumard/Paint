package graphics.menus.extensions.rightclick;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public abstract class RightClickMenu extends JPopupMenu{
	JPopupMenu popMenu = new JPopupMenu();
	public void popMenu() {
		//option.add(new JMenuItem( "Cut" ));	
		popMenu.add(new JMenuItem( "copy" ));	
		popMenu.add(new JMenuItem( "Paste" ));	
		popMenu.add(new JMenuItem( "Delete" ));	
		
		
	}

}
