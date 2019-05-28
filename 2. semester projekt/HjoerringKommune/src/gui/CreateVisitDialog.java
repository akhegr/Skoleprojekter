package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.CarePackage;
import model.Dependent;
import model.Employee;
import model.ZipCity;
import controller.CarePackageCtrl;
import exception.DbLayerException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CreateVisitDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFrame frame;
	private static JFrame jFrame;
	private JLabel lblSelectedDepName;
	private JLabel lblSelectedDepPhone;
	private JLabel lblSelectedDepAddress;

	private JLabel lblSelectedEmpName;
	private JLabel lblSelectedEmpPhone;
	private JLabel lblSelectedEmpAddress;
	private JComboBox<String> comboCarePackage;
	private Employee employee;
	private Dependent dependent;
	private CarePackageCtrl carePackageCtrl;
	private JTextField txtComment;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateVisitDialog dialog = new CreateVisitDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 * @param emp the employee to visit a dependent
	 * @param hour the start hour
	 * @param minutes the start minute
	 * @throws SQLException
	 */
	public CreateVisitDialog(Employee emp) throws SQLException {
		this.employee = emp;
		try {
			carePackageCtrl = new CarePackageCtrl();
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
		setModal(true);
		setBounds(100, 100, 927, 577);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton btnSelectDependent = new JButton("V\u00E6lg plejekr\u00E6vende");
			btnSelectDependent.addActionListener((x) -> {
				try {
 					selectDependent();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, e.toString());
				}
			});
			GridBagConstraints gbc_btnSelectDependent = new GridBagConstraints();
			gbc_btnSelectDependent.insets = new Insets(0, 0, 5, 5);
			gbc_btnSelectDependent.gridx = 0;
			gbc_btnSelectDependent.gridy = 0;
			contentPanel.add(btnSelectDependent, gbc_btnSelectDependent);
		}
		{
			JLabel lblDepName = new JLabel("Navn:");
			GridBagConstraints gbc_lblDepName = new GridBagConstraints();
			gbc_lblDepName.anchor = GridBagConstraints.WEST;
			gbc_lblDepName.insets = new Insets(0, 0, 5, 5);
			gbc_lblDepName.gridx = 0;
			gbc_lblDepName.gridy = 2;
			contentPanel.add(lblDepName, gbc_lblDepName);
		}
		{
			lblSelectedDepName = new JLabel("");
			lblSelectedDepName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedDepName = new GridBagConstraints();
			gbc_lblSelectedDepName.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedDepName.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectedDepName.gridx = 1;
			gbc_lblSelectedDepName.gridy = 2;
			contentPanel.add(lblSelectedDepName, gbc_lblSelectedDepName);
		}
		{
			JLabel lblEmpName = new JLabel("Navn:");
			lblEmpName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblEmpName = new GridBagConstraints();
			gbc_lblEmpName.anchor = GridBagConstraints.WEST;
			gbc_lblEmpName.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmpName.gridx = 2;
			gbc_lblEmpName.gridy = 2;
			contentPanel.add(lblEmpName, gbc_lblEmpName);
		}
		{
			lblSelectedEmpName = new JLabel("");
			lblSelectedEmpName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedEmpName = new GridBagConstraints();
			gbc_lblSelectedEmpName.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedEmpName.insets = new Insets(0, 0, 5, 0);
			gbc_lblSelectedEmpName.gridx = 3;
			gbc_lblSelectedEmpName.gridy = 2;
			contentPanel.add(lblSelectedEmpName, gbc_lblSelectedEmpName);
		}
		{
			JLabel lblDepPhone = new JLabel("Telefon:");
			GridBagConstraints gbc_lblDepPhone = new GridBagConstraints();
			gbc_lblDepPhone.anchor = GridBagConstraints.WEST;
			gbc_lblDepPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblDepPhone.gridx = 0;
			gbc_lblDepPhone.gridy = 3;
			contentPanel.add(lblDepPhone, gbc_lblDepPhone);
		}
		{
			lblSelectedDepPhone = new JLabel("");
			lblSelectedDepPhone.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedDepPhone = new GridBagConstraints();
			gbc_lblSelectedDepPhone.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedDepPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectedDepPhone.gridx = 1;
			gbc_lblSelectedDepPhone.gridy = 3;
			contentPanel.add(lblSelectedDepPhone, gbc_lblSelectedDepPhone);
		}
		{
			JLabel lblEmpPhone = new JLabel("Telefon:");
			GridBagConstraints gbc_lblEmpPhone = new GridBagConstraints();
			gbc_lblEmpPhone.anchor = GridBagConstraints.WEST;
			gbc_lblEmpPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmpPhone.gridx = 2;
			gbc_lblEmpPhone.gridy = 3;
			contentPanel.add(lblEmpPhone, gbc_lblEmpPhone);
		}
		{
			lblSelectedEmpPhone = new JLabel("");
			lblSelectedEmpPhone.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedEmpPhone = new GridBagConstraints();
			gbc_lblSelectedEmpPhone.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedEmpPhone.insets = new Insets(0, 0, 5, 0);
			gbc_lblSelectedEmpPhone.gridx = 3;
			gbc_lblSelectedEmpPhone.gridy = 3;
			contentPanel.add(lblSelectedEmpPhone, gbc_lblSelectedEmpPhone);
		}
		{
			JLabel lblDepAddress = new JLabel("Adresse:");
			GridBagConstraints gbc_lblDepAddress = new GridBagConstraints();
			gbc_lblDepAddress.anchor = GridBagConstraints.WEST;
			gbc_lblDepAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblDepAddress.gridx = 0;
			gbc_lblDepAddress.gridy = 4;
			contentPanel.add(lblDepAddress, gbc_lblDepAddress);
		}
		{
			lblSelectedDepAddress = new JLabel("");
			lblSelectedDepAddress.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedDepAddress = new GridBagConstraints();
			gbc_lblSelectedDepAddress.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedDepAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblSelectedDepAddress.gridx = 1;
			gbc_lblSelectedDepAddress.gridy = 4;
			contentPanel.add(lblSelectedDepAddress, gbc_lblSelectedDepAddress);
		}
		{
			JLabel lblEmpAddress = new JLabel("Adresse:");
			lblEmpAddress.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblEmpAddress = new GridBagConstraints();
			gbc_lblEmpAddress.anchor = GridBagConstraints.WEST;
			gbc_lblEmpAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmpAddress.gridx = 2;
			gbc_lblEmpAddress.gridy = 4;
			contentPanel.add(lblEmpAddress, gbc_lblEmpAddress);
		}
		{
			lblSelectedEmpAddress = new JLabel("");
			lblSelectedEmpAddress.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblSelectedEmpAddress = new GridBagConstraints();
			gbc_lblSelectedEmpAddress.anchor = GridBagConstraints.WEST;
			gbc_lblSelectedEmpAddress.insets = new Insets(0, 0, 5, 0);
			gbc_lblSelectedEmpAddress.gridx = 3;
			gbc_lblSelectedEmpAddress.gridy = 4;
			contentPanel.add(lblSelectedEmpAddress, gbc_lblSelectedEmpAddress);
		}
		{
			JLabel lblCarePackage = new JLabel("Plejepakke:");
			lblCarePackage.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblCarePackage = new GridBagConstraints();
			gbc_lblCarePackage.anchor = GridBagConstraints.WEST;
			gbc_lblCarePackage.insets = new Insets(0, 0, 5, 5);
			gbc_lblCarePackage.gridx = 0;
			gbc_lblCarePackage.gridy = 5;
			contentPanel.add(lblCarePackage, gbc_lblCarePackage);
		}
		{
			comboCarePackage = new JComboBox<String>();
			GridBagConstraints gbc_comboCarePackage = new GridBagConstraints();
			gbc_comboCarePackage.insets = new Insets(0, 0, 5, 5);
			gbc_comboCarePackage.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboCarePackage.gridx = 1;
			gbc_comboCarePackage.gridy = 5;
			contentPanel.add(comboCarePackage, gbc_comboCarePackage);
		}
		{
			JLabel lblComment = new JLabel("Bem\u00E6rkninger:");
			lblComment.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblComment = new GridBagConstraints();
			gbc_lblComment.anchor = GridBagConstraints.WEST;
			gbc_lblComment.fill = GridBagConstraints.VERTICAL;
			gbc_lblComment.insets = new Insets(0, 0, 5, 5);
			gbc_lblComment.gridx = 0;
			gbc_lblComment.gridy = 6;
			contentPanel.add(lblComment, gbc_lblComment);
		}
		{
			txtComment = new JTextField();
			GridBagConstraints gbc_txtComment = new GridBagConstraints();
			gbc_txtComment.insets = new Insets(0, 0, 5, 5);
			gbc_txtComment.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtComment.gridx = 1;
			gbc_txtComment.gridy = 6;
			contentPanel.add(txtComment, gbc_txtComment);
			txtComment.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Gem");
				saveButton.addActionListener((x) -> saveVisit());
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
			}
		}
		updateEmployeeLabels(employee);
	}
	
	/**
	 * Creates an instance of ListDependentDialog, and waits until
	 * user has selected a dependent, and updates fields with the chosen data
	 * @throws SQLException
	 */
	private void selectDependent() throws SQLException {
		ListDependentDialog listDependentDialog = new ListDependentDialog();
		listDependentDialog.setVisible(true);
		while(listDependentDialog.isVisible()) {
			try {
				wait();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(frame, e1.toString());
			}
		}
		dependent = listDependentDialog.getDependent();
		updateDependentLabels();
	}

	/**
	 * Updates labels based on the chosen dependent
	 * @param dependent
	 */
	private void updateDependentLabels() {
		String name = dependent.getfName() + " " + dependent.getlName();
		lblSelectedDepName.setText(name);
		lblSelectedDepPhone.setText(dependent.getPhone());
		ZipCity zipCity = dependent.getZipCity();
		String address = dependent.getAddress() + ", " + zipCity.getZipCode() + " " + zipCity.getCity();
		lblSelectedDepAddress.setText(address);
		LinkedList<CarePackage> carePackageList = dependent.getCarePackages();
		for (CarePackage cp : carePackageList) {
			comboCarePackage.addItem(cp.getType());
		}
	}
	
	/**
	 * Updates labels based on the chosen employee
	 * @param employee
	 */
	private void updateEmployeeLabels(Employee employee) {
		String name = employee.getfName() + " " + employee.getlName();

		lblSelectedEmpName.setText(name);
		lblSelectedEmpPhone.setText(employee.getPhone());
		ZipCity zipCity = employee.getZipCity();
		String address = employee.getAddress() + ", " + zipCity.getZipCode() + " " + zipCity.getCity();
		lblSelectedEmpAddress.setText(address);
	}
	
	/**
	 * If a dependent and an employee is chosen,
	 * it closes
	 */
	private void saveVisit() {
		if(dependent != null && employee != null) {
			this.setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(jFrame, "Du skal vælge en plejekrævende først.");
		}
	}
	
	/**
	 * Returns the chosen dependent
	 * @return dependent The chosen dependent
	 */
	public Dependent getDependent() {
		return dependent;
	}
	
	/**
	 * Returns the chosen carePackage
	 * @return carePackage The chosen carePackage
	 * @throws SQLException
	 */
	public CarePackage getCarePackage() throws SQLException {
		String text = comboCarePackage.getSelectedItem().toString();
		return carePackageCtrl.findByType(text);
	}
	
	/**
	 * Returns the written comment
	 * @return String comment
	 * @throws SQLException
	 */
	public String getComment() throws SQLException {
		return txtComment.getText();
	}
}