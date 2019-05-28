package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import controller.VisitCtrl;
import exception.CreateVisitDialogClosedException;
import exception.DbLayerException;
import exception.DependentNotSetException;

import exception.TransactionFailedException;
import model.CarePackage;
import model.Dependent;
import model.Employee;
import model.Visit;

public class PlanningDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable planningTable;
	private DefaultTableModel planModel;
	private JFrame frame;
	private static JFrame jFrame;
	private CreateVisitDialog createVisitDialog;
	
	private String startDate;
	private VisitCtrl visitCtrl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlanningDialog dialog = new PlanningDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlanningDialog(String startDate) {
		try {
			visitCtrl = new VisitCtrl();
			visitCtrl.findByDate(startDate);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
		setBounds(100, 100, 960, 660);
		this.startDate = startDate;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{713, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{832, 0, 0};
			gbl_panel.rowHeights = new int[]{93, 490, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 2;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel.add(scrollPane, gbc_scrollPane);
				{
					String[] columns = {"Medarbejder", "00:00", "00:05", "00:10", "00:15", "00:20", "00:25", "00:30",
							"00:35", "00:40", "00:45", "00:50", "00:55", "01:05", "01:10", "01:15",
							"01:20", "01:25", "01:30", "01:35", "01:40", "01:45", "01:50", "01:55",
							"02:00", "02:05", "02:10", "02:15", "02:20", "02:25", "02:30", "02:35",
							"02:40", "02:45", "02:50", "02:55", "03:00", "03:05", "03:10", "03:15",
							"03:20", "03:25", "03:30", "03:35", "03:40", "03:45", "03:50", "03:55",
							"04:00", "04:05", "04:10", "04:15", "04:20", "04:25", "04:30", "04:35",
							"04:40", "04:45", "04:50", "04:55", "05:00", "05:05", "05:10", "05:15",
							"05:20", "05:25", "05:30", "05:35", "05:40", "05:45", "05:50", "05:55",
							"06:00", "06:05", "06:10", "06:15", "06:20", "06:25", "06:30", "06:35",
							"06:40", "06:45", "06:50", "06:55", "07:00", "07:05", "07:10", "07:15",
							"07:20", "07:25", "07:30", "07:35", "07:40", "07:45", "07:50", "07:55"};

					planModel = new DefaultTableModel(null,columns) {
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column) {
							//Cellerne i kalenderen må ikke kunne redigeres.
							return false;
						}

					};

					planningTable = new JTable(planModel) {
						private static final long serialVersionUID = 1L;
						@Override
						public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
							Component component = super.prepareRenderer(renderer, row, column);
							int rendererWidth = component.getPreferredSize().width;
							TableColumn tableColumn = getColumnModel().getColumn(column);
							tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
							return component;
						}
					};
					planningTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					planningTable.setFillsViewportHeight(true);
					planningTable.setCellSelectionEnabled(true);
					planningTable.getTableHeader().setReorderingAllowed(false);
					scrollPane.setViewportView(planningTable);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[] {0};
				gbl_panel_1.rowHeights = new int[] {0};
				gbl_panel_1.columnWeights = new double[]{0.0};
				gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0};
				panel_1.setLayout(gbl_panel_1);
				{
					JButton btnAddVisit = new JButton("Tilf\u00F8j bes\u00F8g");
					GridBagConstraints gbc_btnAddVisit = new GridBagConstraints();
					gbc_btnAddVisit.insets = new Insets(0, 0, 5, 5);
					gbc_btnAddVisit.gridx = 0;
					gbc_btnAddVisit.gridy = 0;
					panel_1.add(btnAddVisit, gbc_btnAddVisit);
					btnAddVisit.setBackground(Color.GREEN);
					btnAddVisit.addActionListener((x) -> createVisit());

				}
				{
					JButton btnDeleteVisit = new JButton("Fjern bes\u00F8g");
					btnDeleteVisit.addActionListener((x) -> deleteVisit());
					
					GridBagConstraints gbc_btnDeleteVisit = new GridBagConstraints();
					gbc_btnDeleteVisit.insets = new Insets(0, 0, 5, 5);
					gbc_btnDeleteVisit.gridx = 0;
					gbc_btnDeleteVisit.gridy = 2;
					panel_1.add(btnDeleteVisit, gbc_btnDeleteVisit);
					btnDeleteVisit.setBackground(Color.RED);
				}
				{
					JPanel buttonPane = new JPanel();
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					getContentPane().add(buttonPane, BorderLayout.SOUTH);
					{
						JButton okButton = new JButton("OK");
						okButton.addActionListener((x) -> finishPlanning());
						okButton.setActionCommand("OK");
						buttonPane.add(okButton);
						getRootPane().setDefaultButton(okButton);
					}
				}
				updateTable();
			}
		}
	}
	
	/**
	 * Close the planningwork by setting the window invisible
	 */
	private void finishPlanning() {
		this.setVisible(false);
	}

	/**
	 * Loops through all visits in its list,
	 * and adds them to the table
	 */
	private void populateTable() {
		try {
			List<Employee> empList = visitCtrl.findAllEmployees();
			List<Visit> visitList = visitCtrl.findByDate(startDate);
			Object empty[] = new Object[1];
			planModel.addRow(empty);
			
			for(int i = 0; i < empList.size();i++) {
				Object rowData[] = new Object[1];
				Employee emp = empList.get(i);
				rowData[0] = emp.getfName() + " " + emp.getlName();

				planModel.addRow(rowData);	
			}
			for(int i = 0; i < visitList.size();i++) {
				int empRow = visitList.get(i).getEmployee().getId();
				
				String startTimeCol = visitList.get(i).getStartTime().toString();
				int startTimeColNo = getColumnIndex(planningTable, startTimeCol);
				
				Dependent dep = visitList.get(i).getDependent();
				String depName = dep.getfName() + " " + dep.getlName();
				planningTable.setValueAt(depName, empRow, startTimeColNo);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		planModel.fireTableDataChanged();
	}
	
	/**
	 * The following utility is used in GUI, and based on a comment on StackOverflow.
	 * It gets the column number for a column with a specific text 
	 * @param table the jTable 
	 * @param header
	 * @see  <a href="https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value">The question on StackOverflow</a>
	 * @author BullyWiiPlaza (StackOverflow)
	 * @return column number with the header 
	 */
	private int getColumnIndex(JTable table, String header) {
	    for (int i=0; i < table.getColumnCount(); i++) {
	        if (table.getColumnName(i).equals(header)) return i;
	    }
	    return -1;
	}
	
	/**
	 * Clears all rows on the table
	 */
	private void clearTable() {
		planModel.setRowCount(0);
	}

	/**
	 * Clears and fills the table with employees
	 */
	private void updateTable() {
		clearTable();
		populateTable();
	}
	
	private boolean checkValidCell(int rowIndex, int colIndex) {
		boolean status = false;
		if(rowIndex > 0 && colIndex > 0)
		{
			status = true;
		}
		return status;
	}
	
	/**
	 * Creates a visit by using content from CreateVisitDialog,
	 * and the chosen row and column
	 */
	private void createVisit() {
		boolean status = false;
		int rowIndex = planningTable.getSelectedRow();
		int colIndex = planningTable.getSelectedColumn();
		Employee employee = null;
		visitCtrl.clearVisit();
		String date = startDate;
		
		int hour = 0;
		int minutes = 0;
		
		if(checkValidCell(rowIndex, colIndex)) {
			status = true;
		}
		else {
			JOptionPane.showMessageDialog(frame, "For at oprette et besøg, skal der først vælges en kombination af medarbejder og tidspunkt.");
		}
		
		if(status) {
			Dependent dependent = null;
			try {
				employee = visitCtrl.findEmployeeById(rowIndex);
				
				String subHour = planningTable.getColumnName(colIndex).substring(0, 2); //00 - 23
				String subMinute = planningTable.getColumnName(colIndex).substring(3); // 00 - 59
				hour = Integer.parseInt(subHour);
				minutes = Integer.parseInt(subMinute);
				visitCtrl.selectDate(date);
				createVisitDialog(employee);
				visitCtrl.selectTimeAndEmployee(employee, hour, minutes);
				dependent = createVisitDialog.getDependent();
				visitCtrl.selectDependent(dependent);
				CarePackage cp = createVisitDialog.getCarePackage();
				visitCtrl.setCarePackage(cp);
				String comment = createVisitDialog.getComment();
				visitCtrl.editNote(comment);
				LocalTime startTime = visitCtrl.getStartTime();
				visitCtrl.setVisitEnd(startTime, cp);
				visitCtrl.insertVisit();
				
				updateTable();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			} catch (TransactionFailedException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (DependentNotSetException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (CreateVisitDialogClosedException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (DbLayerException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			}
		}
	}
	
	private void deleteVisit() {
		boolean status = false;
		int rowIndex = planningTable.getSelectedRow();
		int colIndex = planningTable.getSelectedColumn();
		String date = startDate;
		Employee employee = null;
		
		if (rowIndex != -1 && colIndex != -1) {
		    status = true;
		}
		else {
			JOptionPane.showMessageDialog(frame, "For at slette et besøg, skal der først vælges en kombination af dato og tidspunkt.");
		}
		
		if(status) {
			try {
				employee = visitCtrl.findEmployeeById(rowIndex);
				
				String subHour = planningTable.getColumnName(colIndex).substring(0, 2); //00 - 23
				String subMinute = planningTable.getColumnName(colIndex).substring(3); // 00 - 59
				
				String time = subHour + ":" + subMinute;
				
				Visit visit = visitCtrl.findByEmpAndTime(date, time, employee);
				visitCtrl.deleteVisitById(visit.getId());
				
				updateTable();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			} catch (TransactionFailedException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			} catch (DbLayerException e) {
				JOptionPane.showMessageDialog(frame, e.toString());
			}
		}
	}
	
	/**
	 * Creates an instance of CreateVisitDialog based
	 * on the chosen employee and time
	 * @param employee The chosen employee
	 * @param hour the start hour of the visit 
	 * @param minutes the start minute of the visit
	 * @throws SQLException
	 */
	private void createVisitDialog(Employee employee) throws SQLException {
		createVisitDialog = new CreateVisitDialog(employee);
		createVisitDialog.setVisible(true);
		
		while(createVisitDialog.isVisible()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			}
		}
		updateTable();	
		
	}
	
}
