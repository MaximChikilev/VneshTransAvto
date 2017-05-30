package listeners;

import ui.viewpanel.ViewBrandPanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrandMenuListener implements ActionListener {
    private JFrame mainFrame;
    private ViewBrandPanel viewBrandPanel;

    public BrandMenuListener(JFrame mainframe, ViewBrandPanel viewBrandPanel) {
        this.mainFrame = mainframe;
        this.viewBrandPanel = viewBrandPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewBrandPanel == null) {
            viewBrandPanel = new ViewBrandPanel();
            Main.viewBrandPanel = this.viewBrandPanel;
        }
        viewBrandPanel.setVisible(true);
        mainFrame.add(viewBrandPanel, BorderLayout.CENTER);
    }
}
