package listeners;

import ui.viewpanel.ViewCarPanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarMenuListener implements ActionListener {
    private JFrame mainFrame;
    private ViewCarPanel viewCarPanel;

    public CarMenuListener(JFrame mainFrame, ViewCarPanel viewCarPanel) {
        this.mainFrame = mainFrame;
        this.viewCarPanel = viewCarPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewCarPanel == null) {
            viewCarPanel = new ViewCarPanel();
            Main.viewCarPanel = this.viewCarPanel;
        }
        viewCarPanel.setVisible(true);
        mainFrame.add(viewCarPanel, BorderLayout.CENTER);
    }
}
