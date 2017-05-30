package listeners;

import dao.AbstractJDBCDao;
import ui.viewpanel.NewViewPanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.List;

public class ViewPanelTableModelListener<T, U extends T> implements TableModelListener {
    private AbstractJDBCDao<T> dao;
    private NewViewPanel<U> viewPanel;
    private List<U> resultAR;
    private JTable viewJTable;

    public ViewPanelTableModelListener(AbstractJDBCDao<T> dao, List<U> resultAR, JTable viewJTable, NewViewPanel<U> viewPanel) {
        this.dao = dao;
        this.viewPanel = viewPanel;
        this.resultAR = resultAR;
        this.viewJTable = viewJTable;

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            final int selectedRow = viewJTable.getSelectedRow();
            final U entity = resultAR.get(selectedRow);
            dao.update(entity);
        } else if (e.getType() == TableModelEvent.INSERT) {
            final int index = resultAR.size() - 1;
            final U entity = resultAR.get(index);
            dao.create(entity);
        } else if (e.getType() == TableModelEvent.DELETE) {
            final int idOfDeletedRecord = viewPanel.getIdDeletedRecord();
            dao.delete(idOfDeletedRecord);
        }
    }
}
