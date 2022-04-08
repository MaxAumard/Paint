package graphics.menus.toolBar;

import graphics.menus.extensions.DarkTheme;
import graphics.shapes.ui.ShapesView;
import graphics.ui.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ToolBar {
    JToolBar toolBar;
    public ToolBar(JMenuBar menuBar, ShapesView sview) throws IOException {
        super();
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        Collection<Button> buttons = new ArrayList();

        Button pipette = new Button("icon/pipette.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            }});
        buttons.add(pipette);
        pipette.setBackground(new Color(239, 239, 239));

        ToggleButton darkTheme = new ToggleButton("icon/LightThemeIcon.png","icon/DarkThemeIcon.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new DarkTheme(e, toolBar, menuBar, sview, buttons);
                }});
        toolBar.addSeparator(new Dimension(15,0));
        toolBar.add(darkTheme);
        toolBar.addSeparator(new Dimension(10,0));
        toolBar.add(pipette);



        /*// ////////////////////////////////////////////////////////////////////////////////
        JButton btnWhite = createButton("icon/sunWhiteTheme.png");
        btnWhite.setMargin(new Insets(0,0,0,0));
        btnWhite.setBackground(new Color(240,240,240));
        toolBar.add(btnWhite);
        ///Button dark
        JButton btnDark = createButton("icon/sunDarkTheme.png");
        btnDark.setBackground(new Color(27,27,27));
        btnDark.setForeground(Color.GRAY);
        btnDark.setMargin(new Insets(0,0,0,0));
        toolBar.add(btnDark);
*/
    }



    public JToolBar getJToolBar(){
        return this.toolBar;
    }
}
