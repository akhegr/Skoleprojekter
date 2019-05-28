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
import exception.PersonWithCprNoNotExistException;
import exception.TransactionFailedException;
import model.Dependent;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.sql.SQLException;

public class UpdateDependentDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fNametxt;
	private JTextField lNametxt;
	private JTextField searchCPRtxt;
	private JTextField sextxt;
	private JTextField phonetxt;
	private JTextField addresstxt;
	private JTextField ziptxt;
	private JTextField citytxt;
	private JTextField partnerNametxt;
	private JTextField emergencyCalltxt;
	
	private JLabel lblCpr;
	private JButton btnSearch; 
	private JLabel lblFName;
	private JLabel lblLName;
	private JLabel lblSex;
	private JLabel lblAddress;
	private JLabel lblPhone;
	private JLabel lblZipCode;
	private JLabel lblPartnerName;
	private JLabel lblCity;
	private JLabel lblEmergencyCall;
	private JFrame frame;
	private static JFrame jFrame;
	private DependentCtrl dependentCtrl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateDependentDialog dialog = new UpdateDependentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateDependentDialog() {
		try {
			dependentCtrl = new DependentCtrl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
		setModal(true);
		setBounds(100, 100, 510, 631);
		setModalityType(DEFAULT_MODALITY_TYPE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblCpr = new JLabel("CPR: ");
			GridBagConstraints gbc_lblCpr = new GridBagConstraints();
			gbc_lblCpr.anchor = GridBagConstraints.WEST;
			gbc_lblCpr.insets = new Insets(0, 0, 5, 5);
			gbc_lblCpr.gridx = 0;
			gbc_lblCpr.gridy = 0;
			contentPanel.add(lblCpr, gbc_lblCpr);
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
			searchCPRtxt = new JTextField();
			GridBagConstraints gbc_searchCPRtxt = new GridBagConstraints();
			gbc_searchCPRtxt.insets = new Insets(0, 0, 5, 5);
			gbc_searchCPRtxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_searchCPRtxt.gridx = 2;
			gbc_searchCPRtxt.gridy = 0;
			contentPanel.add(searchCPRtxt, gbc_searchCPRtxt);
			searchCPRtxt.setColumns(10);
		}
		{
			btnSearch = new JButton("S\u00F8g");
			btnSearch.addActionListener((e) -> findDependent());
			GridBagConstraints gbc_btnSearch = new GridBagConstraints();
			gbc_btnSearch.fill = GridBagConstraints.VERTICAL;
			gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
			gbc_btnSearch.gridx = 3;
			gbc_btnSearch.gridy = 0;
			contentPanel.add(btnSearch, gbc_btnSearch);
		}
		{
			lblFName = new JLabel("Fornavn: ");
			GridBagConstraints gbc_lblFName = new GridBagConstraints();
			gbc_lblFName.anchor = GridBagConstraints.WEST;
			gbc_lblFName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFName.gridx = 0;
			gbc_lblFName.gridy = 2;
			contentPanel.add(lblFName, gbc_lblFName);
		}
		{
			fNametxt = new JTextField();
			GridBagConstraints gbc_fNametxt = new GridBagConstraints();
			gbc_fNametxt.insets = new Insets(0, 0, 5, 5);
			gbc_fNametxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_fNametxt.gridx = 2;
			gbc_fNametxt.gridy = 2;
			contentPanel.add(fNametxt, gbc_fNametxt);
			fNametxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
		}
		{
			lblLName = new JLabel("Efternavn: ");
			GridBagConstraints gbc_lblLName = new GridBagConstraints();
			gbc_lblLName.anchor = GridBagConstraints.WEST;
			gbc_lblLName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLName.gridx = 0;
			gbc_lblLName.gridy = 4;
			contentPanel.add(lblLName, gbc_lblLName);
		}
		{
			lNametxt = new JTextField();
			GridBagConstraints gbc_lNametxt = new GridBagConstraints();
			gbc_lNametxt.insets = new Insets(0, 0, 5, 5);
			gbc_lNametxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_lNametxt.gridx = 2;
			gbc_lNametxt.gridy = 4;
			contentPanel.add(lNametxt, gbc_lNametxt);
			lNametxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 5;
			contentPanel.add(panel, gbc_panel);
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
			lblSex = new JLabel("K\u00F8n: ");
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
			gbc_sextxt.insets = new Insets(0, 0, 5, 5);
			gbc_sextxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblPhone = new JLabel("Telefon nr: ");
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
			gbc_phonetxt.insets = new Insets(0, 0, 5, 5);
			gbc_phonetxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblAddress = new JLabel("Adresse:");
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
			gbc_addresstxt.insets = new Insets(0, 0, 5, 5);
			gbc_addresstxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblZipCode = new JLabel("Postnummer:");
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
			gbc_ziptxt.insets = new Insets(0, 0, 5, 5);
			gbc_ziptxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblCity = new JLabel("By:");
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
			gbc_citytxt.insets = new Insets(0, 0, 5, 5);
			gbc_citytxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblPartnerName = new JLabel("Partner navn: ");
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
			gbc_partnerNametxt.insets = new Insets(0, 0, 5, 5);
			gbc_partnerNametxt.fill = GridBagConstraints.HORIZONTAL;
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
			lblEmergencyCall = new JLabel("N\u00F8dopkaldskontakt: ");
			GridBagConstraints gbc_lblEmergencyCall = new GridBagConstraints();
			gbc_lblEmergencyCall.anchor = GridBagConstraints.WEST;
			gbc_lblEmergencyCall.insets = new Insets(0, 0, 0, 5);
			gbc_lblEmergencyCall.gridx = 0;
			gbc_lblEmergencyCall.gridy = 19;
			contentPanel.add(lblEmergencyCall, gbc_lblEmergencyCall);
		}
		{
			emergencyCalltxt = new JTextField();
			GridBagConstraints gbc_emergencyCalltxt = new GridBagConstraints();
			gbc_emergencyCalltxt.insets = new Insets(0, 0, 0, 5);
			gbc_emergencyCalltxt.fill = GridBagConstraints.HORIZONTAL;
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
				JButton okButton = new JButton("Opdater");
				okButton.addActionListener((e) -> updateDependent());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fortryd");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Find dependents based on cpr-number
	 */
	private void findDependent() {
		Dependent dep = null;
		try {
			dep = dependentCtrl.findByCprNo(searchCPRtxt.getText());
			
			fNametxt.setText(dep.getfName());
			lNametxt.setText(dep.getlName());
			sextxt.setText(dep.getSex());
			phonetxt.setText(dep.getPhone());
			addresstxt.setText(dep.getAddress());
			ziptxt.setText(dep.getZipCity().getZipCode());
			citytxt.setText(dep.getZipCity().getCity());
			partnerNametxt.setText(dep.getPartnerName());
			emergencyCalltxt.setText(dep.getEmergencyContact());
		} catch (CprNoIsNotCorrectException e2) {
			JOptionPane.showMessageDialog(frame, e2.toString());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(frame, e1.toString());
		} catch (PersonWithCprNoNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} 
	}
	
	/**
	 * Updates dependents based on textfields
	 */
	private void updateDependent() {
		try {
			String cprNo = searchCPRtxt.getText();
			String fName = fNametxt.getText();
			String lName = lNametxt.getText();
			String sex = sextxt.getText();
			String phone = phonetxt.getText();
			String address = addresstxt.getText();
			String zipCode = ziptxt.getText();
			String partnerName = partnerNametxt.getText();
			String emergencyContact = emergencyCalltxt.getText();
			dependentCtrl.updateDependent(fName, lName, address, zipCode, phone, cprNo, sex, partnerName, emergencyContact);
		} catch (CprNoIsNotCorrectException e2) {
			JOptionPane.showMessageDialog(frame, e2.toString());
		}  catch (SQLException e1) {
			JOptionPane.showMessageDialog(frame, e1.toString());
		} catch (PersonWithCprNoNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (TransactionFailedException e3) {
			JOptionPane.showMessageDialog(frame, e3.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}

}
