package listeners;

import ui.viewpanel.ViewExpensesPanel;
import ui.Main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpensesMenuListener implements ActionListener {
    private JFrame mainFrame;
    private ViewExpensesPanel viewExpensesPanel;

    public ExpensesMenuListener(JFrame mainFrame, ViewExpensesPanel viewExpensesPanel) {
        this.mainFrame = mainFrame;
        this.viewExpensesPanel = viewExpensesPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.checkVisible();
        if (viewExpensesPanel == null) {
            viewExpensesPanel = new ViewExpensesPanel();
            Main.viewExpensesPanel = this.viewExpensesPanel;
        }
        viewExpensesPanel.setVisible(true);
        mainFrame.add(viewExpensesPanel, BorderLayout.CENTER);
    }
}
