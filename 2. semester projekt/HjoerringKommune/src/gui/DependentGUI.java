package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

import controller.DependentCtrl;
import exception.DbLayerException;
import model.Dependent;


public class DependentGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable depTable;
	private DefaultTableModel depModel;
	private DependentCtrl dependentCtrl;
	private JFrame frame;


	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public DependentGUI() throws SQLException {
		try {
			dependentCtrl = new DependentCtrl();
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
		btnCreate.addActionListener((x) -> createDependentDialog());
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.fill = GridBagConstraints.BOTH;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 0;
		gbc_btnCreate.gridy = 0;
		add(btnCreate, gbc_btnCreate);
		
		JButton btnUpdate = new JButton("Rediger");
		btnUpdate.addActionListener((x) -> updateDependentDialog());
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 0;
		add(btnUpdate, gbc_btnUpdate);
		
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
		scrollPane.setViewportView(depTable);
		
		updateTable();

	}
	
	/**
	 * Creates an instance of the class CreateDependentDialog,
	 * when the class is closed, it updates the table
	 */
	private void createDependentDialog() {
		CreateDependentDialog createDependent = new CreateDependentDialog();
		createDependent.setVisible(true);
		while(createDependent.isVisible()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			}
		}
		updateTable();
	}
	
	/**
	 * Creates an instance of the class UpdateDependentDialog,
	 * when the class is closed, it updates the table
	 */
	private void updateDependentDialog() {
		UpdateDependentDialog updateDependent = new UpdateDependentDialog();
		updateDependent.setVisible(true);
		while(updateDependent.isVisible()) {
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
		String [] columns = {"ID", "CPR-nr.", "Fornavn", "Efternavn", "Adresse", "Postnummer",
							"By", "Telefon", "Køn", "Partner navn", "Nødopkaldskontakt"};
		depModel = new DefaultTableModel(null,columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//Cellerne i kalenderen må ikke kunne redigeres.
				return false;
			}
		};

		depTable = new JTable(depModel);	
	}
	
	/**
	 * Loops through all dependents in its list,
	 * and adds them to the table
	 */
	private void populateTable() {
		try {
			List<Dependent> depList = dependentCtrl.findAll();
			
			for(int i = 0; i < depList.size();i++) {
				Object rowData[] = new Object[11] ;
				rowData[0] = depList.get(i).getId();
				rowData[1] = depList.get(i).getJournal().getCprNo();
				rowData[2] = depList.get(i).getfName();
				rowData[3] = depList.get(i).getlName();
				rowData[4] = depList.get(i).getAddress();
				rowData[5] = depList.get(i).getZipCity().getZipCode();
				rowData[6] = depList.get(i).getZipCity().getCity();
				rowData[7] = depList.get(i).getPhone();
				rowData[8] = depList.get(i).getSex();
				rowData[9] = depList.get(i).getPartnerName();
				rowData[10] = depList.get(i).getEmergencyContact();
				
				depModel.addRow(rowData);
				depModel.fireTableDataChanged();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	/**
	 * Clears all rows on the table
	 */
	private void clearTable() {
		depModel.setRowCount(0);
	}
	
	/**
	 * Clears and fills the table with dependents
	 */
	private void updateTable() {
		clearTable();
		populateTable();
	}
}
