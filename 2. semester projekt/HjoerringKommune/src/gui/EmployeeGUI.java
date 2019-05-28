package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.EmployeeCtrl;
import exception.DbLayerException;
import model.Employee;

public class EmployeeGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable empTable;
	private DefaultTableModel empModel;
	private JFrame frame;
	private EmployeeCtrl employeeCtrl;
	
	/**
	 * Create the panel.
	 */
	public EmployeeGUI() {
		try {
			employeeCtrl = new EmployeeCtrl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 29, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{22, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener((x) -> createEmployeeDialog());
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.fill = GridBagConstraints.BOTH;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 0;
		add(btnCreate, gbc_btnCreate);
		
		JButton btnEdit = new JButton("Rediger");
		btnEdit.addActionListener((x) -> updateEmployeeDialog());
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 1;
		gbc_btnEdit.gridy = 0;
		add(btnEdit, gbc_btnEdit);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 4;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		JButton btnDelete = new JButton("Slet");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 5;
		gbc_btnDelete.gridy = 0;
		add(btnDelete, gbc_btnDelete);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 6;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 7;
		gbc_panel_4.gridy = 0;
		add(panel_4, gbc_panel_4);
		
		txtSearch = new JTextField();
		txtSearch.setText("S\u00F8g");
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.gridx = 8;
		gbc_txtSearch.gridy = 0;
		add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("S\u00F8g");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 9;
		gbc_btnSearch.gridy = 0;
		add(btnSearch, gbc_btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		setUpTableModel();
		scrollPane.setViewportView(empTable);
		updateTable();

	}
	
	/**
	 * Creates an instance of the class CreateEmployeeDialog,
	 * when the class is closed, it updates the table
	 */
	private void createEmployeeDialog() {
		CreateEmployeeDialog createEmployee = new CreateEmployeeDialog();
		createEmployee.setVisible(true);
		while(createEmployee.isVisible()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			}
		}
		updateTable();
	}
	
	/**
	 * Creates an instance of the class UpdateEmployeeDialog,
	 * when the class is closed, it updates the table
	 */
	private void updateEmployeeDialog() {
		UpdateEmployeeDialog updateEmployee = new UpdateEmployeeDialog();
		updateEmployee.setVisible(true);
		while(updateEmployee.isVisible()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			}
		}
		updateTable();
	}

	/**
	 * Setup the layout of the table
	 */
	private void setUpTableModel() {
		String [] columns = {"ID", "Fornavn", "Efternavn", "Adresse", "Postnummer", "By", "Telefon", "Køn", "Nødopkaldskontakt", "Jobtitel", "Picture", "Email"};
		empModel = new DefaultTableModel(null,columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//Cellerne i kalenderen må ikke kunne redigeres.
				return false;
			}
			
		};
		empTable = new JTable(empModel) {
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
		empTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
	/**
	 * Loops through all employees in its list,
	 * and adds them to the table
	 */
	private void populateTable() {
		try {
			List<Employee> empList = employeeCtrl.findAll();
			
			for(int i = 0; i < empList.size();i++) {
				Object rowData[] = new Object[13] ;
				rowData[0] = empList.get(i).getId();
				rowData[1] = empList.get(i).getfName();
				rowData[2] = empList.get(i).getlName();
				rowData[3] = empList.get(i).getAddress();
				rowData[4] = empList.get(i).getZipCity().getZipCode();
				rowData[5] = empList.get(i).getZipCity().getCity();
				rowData[6] = empList.get(i).getPhone();
				rowData[7] = empList.get(i).getSex();
				rowData[8] = empList.get(i).getEmergencyContact();
				rowData[9] = empList.get(i).getJobTitle();
				rowData[10] = empList.get(i).getPicture();
				rowData[11] = empList.get(i).getEmail();
				
				empModel.addRow(rowData);
				empModel.fireTableDataChanged();
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	/**
	 * Clears all rows on the table
	 */
	private void clearTable() {
		empModel.setRowCount(0);
	}
	
	/**
	 * Clears and fills the table with employees
	 */
	private void updateTable() {
		clearTable();
		populateTable();
	}
}
