package listeners;

import ui.viewpanel.ViewCityPanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CityMenuListener implements ActionListener {
    private JFrame mainFrame;
    private ViewCityPanel viewCityPanel;

    public CityMenuListener(JFrame mainFrame, ViewCityPanel viewCityPanel) {
        this.mainFrame = mainFrame;
        this.viewCityPanel = viewCityPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewCityPanel == null) {
            viewCityPanel = new ViewCityPanel();
            Main.viewCityPanel = this.viewCityPanel;
        }
        viewCityPanel.setVisible(true);
        mainFrame.add(viewCityPanel, BorderLayout.CENTER);
    }
}
