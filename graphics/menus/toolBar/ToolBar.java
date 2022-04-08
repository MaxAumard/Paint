package graphics.menus.toolBar;

import graphics.ui.View;
import graphics.menus.toolBar.ToggleButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ToolBar  {
    JToolBar toolBar;
    public ToolBar(JMenuBar menuBar, View sview) throws IOException {
        super();
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        Button pipette = new Button("icon/pipette.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {


            }});
        pipette.setBackground(new Color(239, 239, 239));




        ToggleButton darkTheme = new ToggleButton("icon/LightThemeIcon.png","icon/DarkThemeIcon.png",new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(getFrames()[0], ((JToggleButton)e.getSource()).isSelected());
                if (((JToggleButton)e.getSource()).isSelected()){
                    Color interfaceDarkColor = new Color(31,31,31);
                    toolBar.setBackground(interfaceDarkColor);
                    menuBar.setBackground(interfaceDarkColor);
                    int menu = 1;
                    while (menuBar.getMenu(menu)!= null){
                        menuBar.getMenu(menu).setForeground(Color.WHITE);
                        menu++;
                    }
                    sview.setBackground(Color.darkGray);
                    pipette.setBackground(interfaceDarkColor);
                }
                else{
                    Color interfaceLightColor = new Color(239, 239, 239);
                    Font fontMenu = new Font("Sans Serif", Font.PLAIN, 15);

                    toolBar.setBackground(interfaceLightColor);

                    menuBar.setBackground(interfaceLightColor);
                    int menu = 1;
                    while (menuBar.getMenu(menu)!= null){
                        menuBar.getMenu(menu).setForeground(Color.black);
                        menu++;
                    }
                    sview.setBackground(Color.white);
                    pipette.setBackground(interfaceLightColor);
                }}});

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
