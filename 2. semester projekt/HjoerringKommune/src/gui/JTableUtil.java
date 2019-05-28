package gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class JTableUtil
{
	/**
	 * The following utility is used in GUI, and based on a comment on StackOverflow.
	 * @see  <a href="https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value">The question on StackOverflow</a>
	 * @author BullyWiiPlaza (StackOverflow)
	 * @param table JTable to an alignment
	 * @param alignment The alignment for the table
	 */
    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
}