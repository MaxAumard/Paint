package graphics.menus.extensions.rightclick;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenuText extends RightClickMenu  {
	JPopupMenu popMenu = new JPopupMenu();
	
	public void popMenu() {	
	
		super.popMenu();
		popMenu.add(new JMenuItem( "EditText" ));	
		
	}
}
