package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.EmployeeCtrl;
import exceptions.*;

public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPhoneNo;
	private JPasswordField passwordField;
	private JLabel lblTopBarItem;
	private JLabel lblPhoneNo;
	private EmployeeCtrl eCtrl;
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		eCtrl = new EmployeeCtrl();
		createAdmin();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Vestbjerg Byggecenter A/S");
		setBounds(100, 100, 480, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 10, 445, 51);
		contentPane.add(topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {450};
		gbl_topPanel.rowHeights = new int[]{49, 0};
		gbl_topPanel.columnWeights = new double[]{0.0};
		gbl_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		lblTopBarItem = new JLabel();
		lblTopBarItem.setBounds(86, 5, 400, 49);
		lblTopBarItem.setIcon(new ImageIcon(OrderUI.class.getResource("/ico/Vestbjerg.png")));
		//lblTopBarItem.setIcon(new ImageIcon(OrderUI.class.getResource("/ico/byggecenter.jpg")));
		lblTopBarItem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTopBarItem.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTopBarItem = new GridBagConstraints();
		gbc_lblTopBarItem.insets = new Insets(0, 0, 0, 5);
		gbc_lblTopBarItem.gridx = 0;
		gbc_lblTopBarItem.gridy = 0;
		topPanel.add(lblTopBarItem, gbc_lblTopBarItem);

		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 100, 438, 196);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {120, 150, 150};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 5;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		panel.add(lblLogin, gbc_lblLogin);
		
		lblPhoneNo = new JLabel("Telefon nr.:");
		GridBagConstraints gbc_lblPhoneNo = new GridBagConstraints();
		gbc_lblPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNo.gridx = 0;
		gbc_lblPhoneNo.gridy = 2;
		panel.add(lblPhoneNo, gbc_lblPhoneNo);
		String buttonPhoneNo = "Indtast medarbejderen telefonnr.";
		buttonPhoneNo += "<br>(fx xxxxxxxx)";
		tooltips(buttonPhoneNo, lblPhoneNo);
		
		txtPhoneNo = new JTextField();
		GridBagConstraints gbc_txtPhoneNo = new GridBagConstraints();
		gbc_txtPhoneNo.gridwidth = 3;
		gbc_txtPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhoneNo.gridx = 1;
		gbc_txtPhoneNo.gridy = 2;
		panel.add(txtPhoneNo, gbc_txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		JLabel lblPass = new JLabel("Kodeord:");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 0;
		gbc_lblPass.gridy = 3;
		panel.add(lblPass, gbc_lblPass);
		
		tooltips("Indtast medarbejderens kodeord", lblPass);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		panel.add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 5;
		panel.add(btnLogin, gbc_btnLogin);
		
		JButton btnCancel = new JButton("Annullser");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 5;
		panel.add(btnCancel, gbc_btnCancel);
	}
	
	private void tooltips(String button, JLabel label)
	{
		String createText = "<html><font size=\"5\">" + button + "</font></html>";
		label.setToolTipText(createText);
	}
	
	
	private void createAdmin()
	{
		String address = "Bakmøllevej 5, 9380 Vestbjerg";
		String email = "ao@vb.dk";
		String name = "Anders Olesen";
		int phone = 25252525;
		String position = "CEO & Co-founder";
		String password = "1Password";
		
		eCtrl.createEmployee(address, email, name, phone, position, password);
	}
	
	private void clearFields()
	{
		txtPhoneNo.setText("");
		passwordField.setText("");
	}
	
	private void login()
	{
		try {
			String strPhone = txtPhoneNo.getText();
			String strPass = String.valueOf(passwordField.getPassword());
			int phoneNo = Integer.parseInt(strPhone);
			
			if(eCtrl.checkPassword(strPass, phoneNo))
			{
				MainUI mainUI = new MainUI(phoneNo);
				mainUI.setVisible(true);
				this.setVisible(false);
			}
		}
		catch (PasswordIsIncorrectException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Telefonnummeret er ikke et tal!");
		}
		catch (PersonNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} 
		
		clearFields();
	}
}
