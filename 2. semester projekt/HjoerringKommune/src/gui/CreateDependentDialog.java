package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DependentCtrl;
import exception.CprNoIsNotCorrectException;
import exception.DbLayerException;
import exception.TransactionFailedException;
import exception.PersonWithCprNoNotExistException;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.sql.SQLException;

public class CreateDependentDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private JTextField fNametxt;
	private JTextField lNametxt;
	private JTextField CPRtxt;
	private JTextField sextxt;
	private JTextField phonetxt;
	private JTextField addresstxt;
	private JTextField ziptxt;
	private JTextField citytxt;
	private JTextField partnerNametxt;
	private JTextField emergencyCalltxt;
	private JFrame frame;
	private static JFrame jFrame;
	private DependentCtrl dependentCtrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateDependentDialog dialog = new CreateDependentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateDependentDialog() {
		try {
			dependentCtrl = new DependentCtrl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 366, 631);
		setModalityType(DEFAULT_MODALITY_TYPE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0};
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
			gbc_fNametxt.fill = GridBagConstraints.BOTH;
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
			gbc_lNametxt.fill = GridBagConstraints.BOTH;
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
			JLabel lblCpr = new JLabel("CPR: ");
			GridBagConstraints gbc_lblCpr = new GridBagConstraints();
			gbc_lblCpr.anchor = GridBagConstraints.WEST;
			gbc_lblCpr.insets = new Insets(0, 0, 5, 5);
			gbc_lblCpr.gridx = 0;
			gbc_lblCpr.gridy = 5;
			contentPanel.add(lblCpr, gbc_lblCpr);
		}
		{
			CPRtxt = new JTextField();
			GridBagConstraints gbc_CPRtxt = new GridBagConstraints();
			gbc_CPRtxt.insets = new Insets(0, 0, 5, 0);
			gbc_CPRtxt.fill = GridBagConstraints.BOTH;
			gbc_CPRtxt.gridx = 2;
			gbc_CPRtxt.gridy = 5;
			contentPanel.add(CPRtxt, gbc_CPRtxt);
			CPRtxt.setColumns(10);
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
			JLabel lblSex = new JLabel("K\u00F8n: ");
			GridBagConstraints gbc_lblSex = new GridBagConstraints();
			gbc_lblSex.anchor = GridBagConstraints.WEST;
			gbc_lblSex.insets = new Insets(0, 0, 5, 5);
			gbc_lblSex.gridx = 0;
			gbc_lblSex.gridy = 7;
			contentPanel.add(lblSex, gbc_lblSex);
		}
		{
			sextxt = new JTextField();
			GridBagConstraints gbc_sextxt = new GridBagConstraints();
			gbc_sextxt.insets = new Insets(0, 0, 5, 0);
			gbc_sextxt.fill = GridBagConstraints.BOTH;
			gbc_sextxt.gridx = 2;
			gbc_sextxt.gridy = 7;
			contentPanel.add(sextxt, gbc_sextxt);
			sextxt.setColumns(10);
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
			JLabel lblPhone = new JLabel("Telefon nr: ");
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 9;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			phonetxt = new JTextField();
			GridBagConstraints gbc_phonetxt = new GridBagConstraints();
			gbc_phonetxt.insets = new Insets(0, 0, 5, 0);
			gbc_phonetxt.fill = GridBagConstraints.BOTH;
			gbc_phonetxt.gridx = 2;
			gbc_phonetxt.gridy = 9;
			contentPanel.add(phonetxt, gbc_phonetxt);
			phonetxt.setColumns(10);
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
			JLabel lblAddress = new JLabel("Adresse:");
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.WEST;
			gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 11;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			addresstxt = new JTextField();
			GridBagConstraints gbc_addresstxt = new GridBagConstraints();
			gbc_addresstxt.insets = new Insets(0, 0, 5, 0);
			gbc_addresstxt.fill = GridBagConstraints.BOTH;
			gbc_addresstxt.gridx = 2;
			gbc_addresstxt.gridy = 11;
			contentPanel.add(addresstxt, gbc_addresstxt);
			addresstxt.setColumns(10);
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
			JLabel lblZipCode = new JLabel("Postnummer:");
			GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
			gbc_lblZipCode.anchor = GridBagConstraints.WEST;
			gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblZipCode.gridx = 0;
			gbc_lblZipCode.gridy = 13;
			contentPanel.add(lblZipCode, gbc_lblZipCode);
		}
		{
			ziptxt = new JTextField();
			GridBagConstraints gbc_ziptxt = new GridBagConstraints();
			gbc_ziptxt.insets = new Insets(0, 0, 5, 0);
			gbc_ziptxt.fill = GridBagConstraints.BOTH;
			gbc_ziptxt.gridx = 2;
			gbc_ziptxt.gridy = 13;
			contentPanel.add(ziptxt, gbc_ziptxt);
			ziptxt.setColumns(10);
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
			JLabel lblCity = new JLabel("By:");
			GridBagConstraints gbc_lblCity = new GridBagConstraints();
			gbc_lblCity.anchor = GridBagConstraints.WEST;
			gbc_lblCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblCity.gridx = 0;
			gbc_lblCity.gridy = 15;
			contentPanel.add(lblCity, gbc_lblCity);
		}
		{
			citytxt = new JTextField();
			GridBagConstraints gbc_citytxt = new GridBagConstraints();
			gbc_citytxt.insets = new Insets(0, 0, 5, 0);
			gbc_citytxt.fill = GridBagConstraints.BOTH;
			gbc_citytxt.gridx = 2;
			gbc_citytxt.gridy = 15;
			contentPanel.add(citytxt, gbc_citytxt);
			citytxt.setColumns(10);
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
			JLabel lblPartnerName = new JLabel("Partner navn: ");
			GridBagConstraints gbc_lblPartnerName = new GridBagConstraints();
			gbc_lblPartnerName.anchor = GridBagConstraints.WEST;
			gbc_lblPartnerName.insets = new Insets(0, 0, 5, 5);
			gbc_lblPartnerName.gridx = 0;
			gbc_lblPartnerName.gridy = 17;
			contentPanel.add(lblPartnerName, gbc_lblPartnerName);
		}
		{
			partnerNametxt = new JTextField();
			GridBagConstraints gbc_partnerNametxt = new GridBagConstraints();
			gbc_partnerNametxt.insets = new Insets(0, 0, 5, 0);
			gbc_partnerNametxt.fill = GridBagConstraints.BOTH;
			gbc_partnerNametxt.gridx = 2;
			gbc_partnerNametxt.gridy = 17;
			contentPanel.add(partnerNametxt, gbc_partnerNametxt);
			partnerNametxt.setColumns(10);
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
			JLabel lblEmergencyCall = new JLabel("N\u00F8dopkaldskontakt: ");
			GridBagConstraints gbc_lblEmergencyCall = new GridBagConstraints();
			gbc_lblEmergencyCall.anchor = GridBagConstraints.WEST;
			gbc_lblEmergencyCall.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmergencyCall.gridx = 0;
			gbc_lblEmergencyCall.gridy = 19;
			contentPanel.add(lblEmergencyCall, gbc_lblEmergencyCall);
		}
		{
			emergencyCalltxt = new JTextField();
			GridBagConstraints gbc_emergencyCalltxt = new GridBagConstraints();
			gbc_emergencyCalltxt.insets = new Insets(0, 0, 5, 0);
			gbc_emergencyCalltxt.fill = GridBagConstraints.BOTH;
			gbc_emergencyCalltxt.gridx = 2;
			gbc_emergencyCalltxt.gridy = 19;
			contentPanel.add(emergencyCalltxt, gbc_emergencyCalltxt);
			emergencyCalltxt.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Opret");
				okButton.addActionListener((e) -> createDependent());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fortryd");
				cancelButton.addActionListener((e) -> setVisible(false));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Creates dependents based on textfields
	 */
	private void createDependent() {
		try {
			String fName = fNametxt.getText();
			String lName = lNametxt.getText();
			String cprNo = CPRtxt.getText();
			String sex = sextxt.getText();
			String phone = phonetxt.getText();
			String address = addresstxt.getText();
			String zipCode = ziptxt.getText();
			String partnerName = partnerNametxt.getText();
			String emergencyContact = emergencyCalltxt.getText();
			dependentCtrl.createDependent(fName, lName, address, zipCode, phone, sex, emergencyContact, partnerName, cprNo);
			JOptionPane.showMessageDialog(frame, "Den plejekrævende er nu oprettet i systemet.");
			setVisible(false);
		} catch (CprNoIsNotCorrectException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}  catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (PersonWithCprNoNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (TransactionFailedException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	

}
