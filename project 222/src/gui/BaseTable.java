package src.gui;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane{
    private Font tableFont = new Font("Montserrat", Font.PLAIN, 15);
    public BaseTable(String[][] data, String[] columnNames)
    {
         DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(tableFont);
        table.setRowHeight(20);

        this.setViewportView(table);
    }
}
