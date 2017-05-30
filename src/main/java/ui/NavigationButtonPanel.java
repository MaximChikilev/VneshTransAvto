package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class NavigationButtonPanel extends JPanel {
    private JButton first;
    private JButton previous;
    private JButton next;
    private JButton last;
    private JButton add;
    private JButton remove;

    public NavigationButtonPanel() {
        first = new JButton("<<");
        previous = new JButton("<");
        next = new JButton(">");
        last = new JButton(">>");
        add = new JButton("+");
        remove = new JButton("-");
        this.setLayout(new GridLayout(1, 4));
        this.add(first);
        this.add(previous);
        this.add(next);
        this.add(last);
        this.add(add);
        this.add(remove);
        this.setVisible(true);
    }

    public void addActionListenerOnFirst(ActionListener actionListener) {
        first.addActionListener(actionListener);
    }

    public void addActionListenerOnPrevios(ActionListener actionListener) {
        previous.addActionListener(actionListener);
    }

    public void addActionListenerOnNext(ActionListener actionListener) {
        next.addActionListener(actionListener);
    }

    public void addActionListenerOnLast(ActionListener actionListener) {
        last.addActionListener(actionListener);
    }

    public void addActionListenerOnRemove(ActionListener actionListener) {
        remove.addActionListener(actionListener);
    }

    public void addActionListenerOnAdd(ActionListener actionListener) {
        add.addActionListener(actionListener);
    }
}