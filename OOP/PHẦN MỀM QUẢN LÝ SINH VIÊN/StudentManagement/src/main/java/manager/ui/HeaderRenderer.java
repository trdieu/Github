/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dell
 */
public class HeaderRenderer extends JCheckBox implements TableCellRenderer {

    private class HeaderCheckBoxHandler implements TableModelListener {

        private final JTable table;

        public HeaderCheckBoxHandler(JTable table) {
            this.table = table;
        }

        @Override
        public void tableChanged(javax.swing.event.TableModelEvent e) {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE && e.getColumn() == 0) {
                int mci = 0;
                int vci = table.convertColumnIndexToView(mci);
                TableColumn column = table.getColumnModel().getColumn(vci);
                Object title = column.getHeaderValue();
                if (!Status.INDETERMINATE.equals(title)) {
                    column.setHeaderValue(Status.INDETERMINATE);
                } else {
                    int selected = 0, deselected = 0;
                    TableModel m = table.getModel();
                    for (int i = 0; i < m.getRowCount(); i++) {
                        if (Boolean.TRUE.equals(m.getValueAt(i, mci))) {
                            selected++;
                        } else {
                            deselected++;
                        }
                    }
                    if (selected == 0) {
                        column.setHeaderValue(Status.DESELECTED);
                    } else if (deselected == 0) {
                        column.setHeaderValue(Status.SELECTED);
                    } else {
                        return;
                    }
                }
                table.getTableHeader().repaint();
            }
        }
    }

    public HeaderRenderer(JTableHeader header, final int targetColumnIndex) {
        super((String) null);
        setOpaque(true);
        setFont(header.getFont());
        setBackground(new Color(72, 3, 111));
        setForeground(Color.white);
        setHorizontalAlignment(SwingConstants.CENTER);
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // compiled code
                JTableHeader header = (JTableHeader) e.getSource();
                JTable table = header.getTable();
                TableColumnModel columnModel = table.getColumnModel();
                int vci = columnModel.getColumnIndexAtX(e.getX());
                int mci = table.convertColumnIndexToModel(vci);
                if (mci == targetColumnIndex) {
                    TableColumn column = columnModel.getColumn(vci);
                    Object v = column.getHeaderValue();
                    boolean b = Status.DESELECTED.equals(v) ? true : false;
                    TableModel m = table.getModel();
                    for (int i = 0; i < m.getRowCount(); i++) {
                        m.setValueAt(b, i, mci);
                    }
                    column.setHeaderValue(b ? Status.SELECTED : Status.DESELECTED);
                }
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable tbl, Object val, boolean isS, boolean hasF, int row, int col) {
        if (val instanceof Status) {
            switch ((Status) val) {
                case SELECTED:
                    setSelected(true);
                    setEnabled(true);
                    break;
                case DESELECTED:
                    setSelected(false);
                    setEnabled(true);
                    break;
                case INDETERMINATE:
                    setSelected(true);
                    setEnabled(false);
                    break;
            }
        } else {
            setSelected(false);
            setEnabled(true);
        }

        return this;
    }

    enum Status {

        SELECTED, DESELECTED, INDETERMINATE
    }
}
