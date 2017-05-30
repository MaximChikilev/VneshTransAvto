package listeners;

import ui.viewpanel.ViewClientPanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsMenuListeners implements ActionListener {
    private JFrame mainFrame;
    private ViewClientPanel viewClientPanel;

    public ClientsMenuListeners(JFrame mainFrame, ViewClientPanel viewClientPanel) {
        this.mainFrame = mainFrame;
        this.viewClientPanel = viewClientPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewClientPanel == null) {
            viewClientPanel = new ViewClientPanel();
            Main.viewClientPanel = this.viewClientPanel;
        }
        viewClientPanel.setVisible(true);
        mainFrame.add(viewClientPanel, BorderLayout.CENTER);
    }
}
