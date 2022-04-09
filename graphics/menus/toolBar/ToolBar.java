package graphics.menus.toolBar;

import graphics.menus.extensions.ColorChooser;
import graphics.menus.extensions.DarkTheme;
import graphics.menus.extensions.Pipette;
import graphics.shapes.ui.ShapesView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ToolBar extends JToolBar{
    JToolBar jtoolBar;
    public ToolBar(JMenuBar menuBar, ShapesView sview) throws IOException {
        super();
        //cration of the toolBar
        jtoolBar = new JToolBar();
        //prevent flotability
        jtoolBar.setFloatable(false);
        //toolBar buttons will be stored here
        Collection buttons = new ArrayList();
        


        //colorChooser button
        JButton colorChooser = new JButton();
        ColorChooser cc = new ColorChooser();
        colorChooser.setBackground(Color.black);
        colorChooser.setBorder(BorderFactory.createEmptyBorder());
        colorChooser.setMargin(new Insets(-2,-2,-1,-1));
        colorChooser.setIcon(new ImageIcon(new ImageIcon("icon/lightColorChooser.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
        colorChooser.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ColorChooser cc = new ColorChooser();
                cc.setColorChooseed(Color.BLACK);
                cc.displayColorChooser(colorChooser);

            }});

        //pipette button
        ToggleButton pipette = new ToggleButton("icon/pipette.png","icon/pipette.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Pipette pp = new Pipette(cc, sview, e);

            }});
        buttons.add(pipette);
        pipette.setBackground(new Color(239, 239, 239));

        //darktheme button
        ToggleButton darkTheme = new ToggleButton("icon/LightThemeIcon.png","icon/DarkThemeIcon.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new DarkTheme(e, jtoolBar, menuBar, sview, buttons, colorChooser);
                }});




        //add icons to toolbar
        jtoolBar.addSeparator(new Dimension(15,0));
        jtoolBar.add(darkTheme);
        jtoolBar.addSeparator(new Dimension(10,0));
        jtoolBar.add(pipette);




        jtoolBar.addSeparator(new Dimension(10,0));
        jtoolBar.add(colorChooser);

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
        return this.jtoolBar;
    }
}
