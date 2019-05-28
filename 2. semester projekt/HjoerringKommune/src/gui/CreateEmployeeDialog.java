package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeCtrl;
import exception.DbLayerException;
import exception.TransactionFailedException;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.sql.SQLException;

public class CreateEmployeeDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField fNametxt;
	private JTextField lNametxt;
	private JTextField emailtxt;
	private JTextField sextxt;
	private JTextField phonetxt;
	private JTextField addresstxt;
	private JTextField ziptxt;
	private JTextField emergencyCalltxt;
	private JTextField jobTitletxt;
	private JFrame frame;
	private static JFrame jFrame;
	private EmployeeCtrl employeeCtrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateEmployeeDialog dialog = new CreateEmployeeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateEmployeeDialog() {
		try {
			employeeCtrl = new EmployeeCtrl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 956, 631);
		setModalityType(DEFAULT_MODALITY_TYPE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblFName = new JLabel("Fornavn: ");
			GridBagConstraints gbc_lblFName = new GridBagConstraints();
			gbc_lblFName.anchor = GridBagConstraints.WEST;
			gbc_lblFName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFName.gridx = 0;
			gbc_lblFName.gridy = 1;
			contentPanel.add(lblFName, gbc_lblFName);
		}
		{
			fNametxt = new JTextField();
			GridBagConstraints gbc_fNametxt = new GridBagConstraints();
			gbc_fNametxt.insets = new Insets(0, 0, 5, 0);
			gbc_fNametxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_fNametxt.gridx = 2;
			gbc_fNametxt.gridy = 1;
			contentPanel.add(fNametxt, gbc_fNametxt);
			fNametxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblLName = new JLabel("Efternavn: ");
			GridBagConstraints gbc_lblLName = new GridBagConstraints();
			gbc_lblLName.anchor = GridBagConstraints.WEST;
			gbc_lblLName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLName.gridx = 0;
			gbc_lblLName.gridy = 3;
			contentPanel.add(lblLName, gbc_lblLName);
		}
		{
			lNametxt = new JTextField();
			GridBagConstraints gbc_lNametxt = new GridBagConstraints();
			gbc_lNametxt.insets = new Insets(0, 0, 5, 0);
			gbc_lNametxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_lNametxt.gridx = 2;
			gbc_lNametxt.gridy = 3;
			contentPanel.add(lNametxt, gbc_lNametxt);
			lNametxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblEmail = new JLabel("Email: ");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 5;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			emailtxt = new JTextField();
			GridBagConstraints gbc_emailtxt = new GridBagConstraints();
			gbc_emailtxt.insets = new Insets(0, 0, 5, 0);
			gbc_emailtxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_emailtxt.gridx = 2;
			gbc_emailtxt.gridy = 5;
			contentPanel.add(emailtxt, gbc_emailtxt);
			emailtxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 6;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblJobTitle = new JLabel("Job titel: ");
			GridBagConstraints gbc_lblJobTitle = new GridBagConstraints();
			gbc_lblJobTitle.anchor = GridBagConstraints.WEST;
			gbc_lblJobTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblJobTitle.gridx = 0;
			gbc_lblJobTitle.gridy = 7;
			contentPanel.add(lblJobTitle, gbc_lblJobTitle);
		}
		{
			jobTitletxt = new JTextField();
			GridBagConstraints gbc_jobTitletxt = new GridBagConstraints();
			gbc_jobTitletxt.insets = new Insets(0, 0, 5, 0);
			gbc_jobTitletxt.fill = GridBagConstraints.BOTH;
			gbc_jobTitletxt.gridx = 2;
			gbc_jobTitletxt.gridy = 7;
			contentPanel.add(jobTitletxt, gbc_jobTitletxt);
			jobTitletxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 8;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblSex = new JLabel("K\u00F8n: ");
			GridBagConstraints gbc_lblSex = new GridBagConstraints();
			gbc_lblSex.anchor = GridBagConstraints.WEST;
			gbc_lblSex.insets = new Insets(0, 0, 5, 5);
			gbc_lblSex.gridx = 0;
			gbc_lblSex.gridy = 9;
			contentPanel.add(lblSex, gbc_lblSex);
		}
		{
			sextxt = new JTextField();
			GridBagConstraints gbc_sextxt = new GridBagConstraints();
			gbc_sextxt.insets = new Insets(0, 0, 5, 0);
			gbc_sextxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_sextxt.gridx = 2;
			gbc_sextxt.gridy = 9;
			contentPanel.add(sextxt, gbc_sextxt);
			sextxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 10;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblPhone = new JLabel("Telefon nr: ");
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 11;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			phonetxt = new JTextField();
			GridBagConstraints gbc_phonetxt = new GridBagConstraints();
			gbc_phonetxt.insets = new Insets(0, 0, 5, 0);
			gbc_phonetxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_phonetxt.gridx = 2;
			gbc_phonetxt.gridy = 11;
			contentPanel.add(phonetxt, gbc_phonetxt);
			phonetxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 12;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblAddress = new JLabel("Adresse:");
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.WEST;
			gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 13;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			addresstxt = new JTextField();
			GridBagConstraints gbc_addresstxt = new GridBagConstraints();
			gbc_addresstxt.insets = new Insets(0, 0, 5, 0);
			gbc_addresstxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_addresstxt.gridx = 2;
			gbc_addresstxt.gridy = 13;
			contentPanel.add(addresstxt, gbc_addresstxt);
			addresstxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 14;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblZipCode = new JLabel("Postnummer:");
			GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
			gbc_lblZipCode.anchor = GridBagConstraints.WEST;
			gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblZipCode.gridx = 0;
			gbc_lblZipCode.gridy = 15;
			contentPanel.add(lblZipCode, gbc_lblZipCode);
		}
		{
			ziptxt = new JTextField();
			GridBagConstraints gbc_ziptxt = new GridBagConstraints();
			gbc_ziptxt.insets = new Insets(0, 0, 5, 0);
			gbc_ziptxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_ziptxt.gridx = 2;
			gbc_ziptxt.gridy = 15;
			contentPanel.add(ziptxt, gbc_ziptxt);
			ziptxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 16;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblEmergencyCall = new JLabel("N\u00F8dopkaldskontakt: ");
			GridBagConstraints gbc_lblEmergencyCall = new GridBagConstraints();
			gbc_lblEmergencyCall.anchor = GridBagConstraints.WEST;
			gbc_lblEmergencyCall.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmergencyCall.gridx = 0;
			gbc_lblEmergencyCall.gridy = 17;
			contentPanel.add(lblEmergencyCall, gbc_lblEmergencyCall);
		}
		{
			emergencyCalltxt = new JTextField();
			GridBagConstraints gbc_emergencyCalltxt = new GridBagConstraints();
			gbc_emergencyCalltxt.insets = new Insets(0, 0, 5, 0);
			gbc_emergencyCalltxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_emergencyCalltxt.gridx = 2;
			gbc_emergencyCalltxt.gridy = 17;
			contentPanel.add(emergencyCalltxt, gbc_emergencyCalltxt);
			emergencyCalltxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 18;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JLabel lblImage = new JLabel("Billede: ");
			GridBagConstraints gbc_lblImage = new GridBagConstraints();
			gbc_lblImage.anchor = GridBagConstraints.WEST;
			gbc_lblImage.insets = new Insets(0, 0, 5, 5);
			gbc_lblImage.gridx = 0;
			gbc_lblImage.gridy = 19;
			contentPanel.add(lblImage, gbc_lblImage);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 20;
			contentPanel.add(panel, gbc_panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Opret");
				okButton.addActionListener((x) -> createEmployee());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fortryd");
				cancelButton.addActionListener((x) -> setVisible(false));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void createEmployee() {
		try {
			String fName = fNametxt.getText();
			String lName = lNametxt.getText();
			String address = addresstxt.getText();
			String zipCode = ziptxt.getText();
			String phone = phonetxt.getText();
			String sex = sextxt.getText();
			String jobTitle = jobTitletxt.getText();
			String email = emailtxt.getText();
			String emergencyContact = emergencyCalltxt.getText();
			employeeCtrl.createEmployee(fName, lName, address, zipCode, phone, sex, emergencyContact, jobTitle, null, email);
			JOptionPane.showMessageDialog(frame, "Medarbejderen er nu oprettet i systemet.");
			setVisible(false);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (TransactionFailedException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}

}
