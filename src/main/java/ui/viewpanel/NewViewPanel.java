package ui.viewpanel;

import ui.NavigationButtonPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class NewViewPanel<T> extends JPanel {
    protected List<T> resultAR;
    protected int idDeletedRecord;
    protected JPanel innerPanel;
    protected JTable viewJTable;
    protected JScrollPane scrollPane;
    protected NavigationButtonPanel navigationButtonPanel;

    protected NewViewPanel() {
        setLayout(new BorderLayout());
        innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        navigationButtonPanel = new NavigationButtonPanel();
        viewJTable = new JTable();
        scrollPane = new JScrollPane(viewJTable);
        innerPanel.add(navigationButtonPanel, BorderLayout.SOUTH);
        innerPanel.add(scrollPane, BorderLayout.CENTER);
        innerPanel.setVisible(true);
        setOpaque(true);
        setVisible(true);
        listenersInitialization();
    }

    protected abstract void removeMethod();

    protected abstract void addMethod();

    public int getIdDeletedRecord() {
        return idDeletedRecord;
    }

    public void setDefaultCBEditor(int indexColumn, JComboBox comboBox) {
        TableColumn column = viewJTable.getColumnModel().getColumn(indexColumn);
        column.setCellEditor(new DefaultCellEditor(comboBox));
    }

    private void listenersInitialization() {

        navigationButtonPanel.addActionListenerOnFirst(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewJTable.setRowSelectionInterval(0, 0);
            }
        });
        navigationButtonPanel.addActionListenerOnLast(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewJTable.setRowSelectionInterval(resultAR.size() - 1, resultAR.size() - 1);
            }
        });
        navigationButtonPanel.addActionListenerOnPrevios(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewJTable.getSelectedRow() != 0) {
                    viewJTable.setRowSelectionInterval(viewJTable.getSelectedRow() - 1, viewJTable.getSelectedRow() - 1);
                }
            }
        });
        navigationButtonPanel.addActionListenerOnNext(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewJTable.getSelectedRow() != resultAR.size() - 1) {
                    viewJTable.setRowSelectionInterval(viewJTable.getSelectedRow() + 1, viewJTable.getSelectedRow() + 1);
                }
            }
        });
        viewJTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (viewJTable.getSelectedRow() >= 0) {
                    viewJTable.setRowSelectionInterval(viewJTable.getSelectedRow(), viewJTable.getSelectedRow());
                } else {
                    if (viewJTable.getEditingRow() >= 0) {
                        viewJTable.setRowSelectionInterval(viewJTable.getEditingRow(), viewJTable.getEditingRow());
                    } else {
                        viewJTable.setRowSelectionInterval(0, 0);
                    }

                }

            }
        });
        navigationButtonPanel.addActionListenerOnRemove(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int responce = JOptionPane.showConfirmDialog(null, "Do you really want to delete record?");
                switch (responce) {
                    case JOptionPane.YES_OPTION: {
                        removeMethod();
                        viewJTable.setRowSelectionInterval(0, 0);
                    }
                }
            }
        });
        navigationButtonPanel.addActionListenerOnAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMethod();
            }
        });
    }

}
