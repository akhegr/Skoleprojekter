package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerCtrl;
import controller.EmployeeCtrl;
import exceptions.PersonNotExistException;
import exceptions.TooHighDiscountException;
import model.Customer;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Point;

public class PersonUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private EmployeeCtrl eCtrl;
	private CustomerCtrl cCtrl;
	
	private int phoneNo;
	private JButton btnBack;
	private JButton btnChange;
	private JButton btnCreatePerson;
	private JButton btnSearch;
	private JButton btnUpdatePerson;
	private JFrame frame;
	private JLabel lblAccount2;
	private JLabel lblAccount;
	private JLabel lblAddress2;
	private JLabel lblAddress;
	private JLabel lblCreatePerson;
	private JLabel lblDiscount2;
	private JLabel lblDiscount;
	private JLabel lblEmail2;
	private JLabel lblEmail;
	private JLabel lblName2;
	private JLabel lblName;
	private JLabel lblPhone2;
	private JLabel lblPhone;
	private JLabel lblPhoneNo;
	private JLabel lblPassword;
	private JLabel lblPosition2;
	private JLabel lblPosition;
	private JLabel lblSearchPerson;
	private JLabel lblTopBarItem;
	private JLabel lblUpdatePerson;
	private JLabel lblEmployee;
	private JTextField txtAccount2;
	private JTextField txtAccount;
	private JTextField txtAddress2;
	private JTextField txtAddress;
	private JTextField txtDiscount2;
	private JTextField txtDiscount;
	private JTextField txtEmail2;
	private JTextField txtEmail;
	private JTextField txtName2;
	private JTextField txtName;
	private JTextField txtPhone2;
	private JTextField txtPhone;
	private JTextField txtPhoneNo;
	private JTextField txtPassword;
	private JTextField txtPosition2;
	private JTextField txtPosition;
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonUI frame = new PersonUI(0);
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
	public PersonUI(int phoneNo) {
		this.phoneNo = phoneNo;
		initUI();
		checkEmployeeStatus();
		toDo();
	}
	
	public void initUI()
	{
		eCtrl = new EmployeeCtrl();
		cCtrl = new CustomerCtrl();
		
		this.setTitle("Vestbjerg Byggecenter A/S");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 13, 665, 51);
		contentPane.add(topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {50, 375, 100, 100};
		gbl_topPanel.rowHeights = new int[] {49};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_topPanel.rowWeights = new double[]{0.0};
		topPanel.setLayout(gbl_topPanel);
		
		btnBack = new JButton("Tilbage");
		btnBack.setBounds(5, 5, 76, 49);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 0;
		topPanel.add(btnBack, gbc_btnBack);
		
		lblTopBarItem = new JLabel();
		lblTopBarItem.setBounds(86, 5, 400, 49);
		lblTopBarItem.setIcon(new ImageIcon(OrderUI.class.getResource("/ico/Vestbjerg.png")));
		//lblTopBarItem.setIcon(new ImageIcon(OrderUI.class.getResource("/ico/byggecenter.jpg")));
		lblTopBarItem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTopBarItem.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTopBarItem = new GridBagConstraints();
		gbc_lblTopBarItem.insets = new Insets(0, 0, 0, 5);
		gbc_lblTopBarItem.gridx = 1;
		gbc_lblTopBarItem.gridy = 0;
		topPanel.add(lblTopBarItem, gbc_lblTopBarItem);
		
		btnChange = new JButton("Kunde");
		btnChange.setBounds(486, 5, 81, 49);
		GridBagConstraints gbc_btnChange = new GridBagConstraints();
		gbc_btnChange.insets = new Insets(0, 0, 0, 5);
		gbc_btnChange.gridx = 2;
		gbc_btnChange.gridy = 0;
		topPanel.add(btnChange, gbc_btnChange);
		
		lblEmployee = new JLabel("navn");
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmployee.gridx = 3;
		gbc_lblEmployee.gridy = 0;
		topPanel.add(lblEmployee, gbc_lblEmployee);
		MainUI mainUI = new MainUI(phoneNo);
		mainUI.setEmployee(lblEmployee);
		
		
		
		
		JPanel findPanel = new JPanel();
		findPanel.setBounds(5, 77, 155, 129);
		contentPane.add(findPanel);
		
		lblSearchPerson = new JLabel("Find medarbejder");
		lblSearchPerson.setHorizontalAlignment(SwingConstants.CENTER);
		findPanel.add(lblSearchPerson);
		lblSearchPerson.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblPhoneNo = new JLabel("Indtast personens tlf.nr.");
		findPanel.add(lblPhoneNo);
		lblPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		findPanel.add(txtPhoneNo);
		txtPhoneNo.setColumns(5);
		
		btnSearch = new JButton("S\u00F8g");
		findPanel.add(btnSearch);

		
		
		
		
		JPanel createPanel = new JPanel();
		createPanel.setBounds(162, 77, 251, 216);
		contentPane.add(createPanel);
		GridBagLayout gbl_createPanel = new GridBagLayout();
		gbl_createPanel.columnWidths = new int[]{32, 0, 0, 0, 0, 79, 36, 0};
		gbl_createPanel.rowHeights = new int[] {19, 0, 22, 16, 22, 0, 0};
		gbl_createPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_createPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		createPanel.setLayout(gbl_createPanel);
		
		lblCreatePerson = new JLabel("Opret medarbejder");
		GridBagConstraints gbc_lblCreatePerson = new GridBagConstraints();
		gbc_lblCreatePerson.anchor = GridBagConstraints.NORTH;
		gbc_lblCreatePerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreatePerson.gridwidth = 5;
		gbc_lblCreatePerson.gridx = 1;
		gbc_lblCreatePerson.gridy = 0;
		createPanel.add(lblCreatePerson, gbc_lblCreatePerson);
		lblCreatePerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatePerson.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblName = new JLabel("Navn");
		lblName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		createPanel.add(lblName, gbc_lblName);
		lblName.setToolTipText(":Indtast personens navn i feltet");
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridwidth = 4;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 1;
		createPanel.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		lblAddress = new JLabel("Adresse");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 2;
		createPanel.add(lblAddress, gbc_lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridwidth = 4;
		gbc_txtAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress.gridx = 2;
		gbc_txtAddress.gridy = 2;
		createPanel.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		createPanel.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridwidth = 4;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		createPanel.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		lblPhone = new JLabel("Telefon nr.");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.WEST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 1;
		gbc_lblPhone.gridy = 4;
		createPanel.add(lblPhone, gbc_lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridwidth = 4;
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.gridx = 2;
		gbc_txtPhone.gridy = 4;
		createPanel.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);
		
		lblAccount = new JLabel("Kontobel\u00F8b");
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAccount = new GridBagConstraints();
		gbc_lblAccount.anchor = GridBagConstraints.WEST;
		gbc_lblAccount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccount.gridx = 1;
		gbc_lblAccount.gridy = 5;
		createPanel.add(lblAccount, gbc_lblAccount);
		lblAccount.setVisible(false);
		
		txtAccount = new JTextField();
		txtAccount.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtAccount = new GridBagConstraints();
		gbc_txtAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAccount.gridwidth = 4;
		gbc_txtAccount.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccount.gridx = 2;
		gbc_txtAccount.gridy = 5;
		createPanel.add(txtAccount, gbc_txtAccount);
		txtAccount.setColumns(10);
		txtAccount.setVisible(false);
		
		lblDiscount = new JLabel("Rabatprocent");
		lblDiscount.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
		gbc_lblDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscount.gridx = 1;
		gbc_lblDiscount.gridy = 6;
		createPanel.add(lblDiscount, gbc_lblDiscount);
		lblDiscount.setVisible(false);
		
		txtDiscount = new JTextField();
		txtDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiscount.setBounds(new Rectangle(50, 0, 0, 0));
		GridBagConstraints gbc_txtDiscount = new GridBagConstraints();
		gbc_txtDiscount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDiscount.gridwidth = 4;
		gbc_txtDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_txtDiscount.gridx = 2;
		gbc_txtDiscount.gridy = 6;
		createPanel.add(txtDiscount, gbc_txtDiscount);
		txtDiscount.setColumns(10);
		txtDiscount.setVisible(false);
		
		lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPosition = new GridBagConstraints();
		gbc_lblPosition.anchor = GridBagConstraints.WEST;
		gbc_lblPosition.insets = new Insets(0, 0, 5, 5);
		gbc_lblPosition.gridx = 1;
		gbc_lblPosition.gridy = 5;
		createPanel.add(lblPosition, gbc_lblPosition);
		lblPosition.setVisible(true);
		
		txtPosition = new JTextField();
		txtPosition.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosition.setLocation(new Point(0, 10));
		GridBagConstraints gbc_txtPosition = new GridBagConstraints();
		gbc_txtPosition.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPosition.gridwidth = 4;
		gbc_txtPosition.insets = new Insets(0, 0, 5, 5);
		gbc_txtPosition.gridx = 2;
		gbc_txtPosition.gridy = 5;
		createPanel.add(txtPosition, gbc_txtPosition);
		txtPosition.setColumns(10);
		txtPosition.setVisible(true);
		
		lblPassword = new JLabel("Kodeord");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 6;
		createPanel.add(lblPassword, gbc_lblPassword);
		lblPassword.setVisible(true);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setLocation(new Point(0, 10));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridwidth = 4;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 6;
		createPanel.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setVisible(true);
		
		btnCreatePerson = new JButton("Opret");
		GridBagConstraints gbc_btnCreatePerson = new GridBagConstraints();
		gbc_btnCreatePerson.anchor = GridBagConstraints.NORTH;
		gbc_btnCreatePerson.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreatePerson.gridwidth = 5;
		gbc_btnCreatePerson.gridx = 1;
		gbc_btnCreatePerson.gridy = 7;
		createPanel.add(btnCreatePerson, gbc_btnCreatePerson);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBounds(418, 77, 269, 216);
		contentPane.add(updatePanel);
		GridBagLayout gbl_updatePanel = new GridBagLayout();
		gbl_updatePanel.columnWidths = new int[]{32, 0, 0, 0, 0, 79, 36, 0};
		gbl_updatePanel.rowHeights = new int[] {19, 0, 22, 16, 22, 0, 0, 0};
		gbl_updatePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_updatePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		updatePanel.setLayout(gbl_updatePanel);
		
		lblUpdatePerson = new JLabel("Opdater medarbejder");
		GridBagConstraints gbc_lblUpdatePerson = new GridBagConstraints();
		gbc_lblUpdatePerson.anchor = GridBagConstraints.NORTH;
		gbc_lblUpdatePerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpdatePerson.gridwidth = 5;
		gbc_lblUpdatePerson.gridx = 1;
		gbc_lblUpdatePerson.gridy = 0;
		updatePanel.add(lblUpdatePerson, gbc_lblUpdatePerson);
		lblUpdatePerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdatePerson.setFont(new Font("Tahoma", Font.BOLD, 15));
		

		lblPhone2 = new JLabel("Telefon nr.");
		lblPhone2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPhone2 = new GridBagConstraints();
		gbc_lblPhone2.anchor = GridBagConstraints.WEST;
		gbc_lblPhone2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone2.gridx = 1;
		gbc_lblPhone2.gridy = 1;
		updatePanel.add(lblPhone2, gbc_lblPhone2);
		
		txtPhone2 = new JTextField();
		txtPhone2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtPhone2 = new GridBagConstraints();
		gbc_txtPhone2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone2.gridwidth = 4;
		gbc_txtPhone2.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone2.gridx = 2;
		gbc_txtPhone2.gridy = 1;
		updatePanel.add(txtPhone2, gbc_txtPhone2);
		txtPhone2.setColumns(10);
		
		lblName2 = new JLabel("Navn");
		lblName2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblName2 = new GridBagConstraints();
		gbc_lblName2.anchor = GridBagConstraints.WEST;
		gbc_lblName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName2.gridx = 1;
		gbc_lblName2.gridy = 2;
		updatePanel.add(lblName2, gbc_lblName2);
		
		txtName2 = new JTextField();
		txtName2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtName2 = new GridBagConstraints();
		gbc_txtName2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName2.gridwidth = 4;
		gbc_txtName2.insets = new Insets(0, 0, 5, 5);
		gbc_txtName2.gridx = 2;
		gbc_txtName2.gridy = 2;
		updatePanel.add(txtName2, gbc_txtName2);
		txtName2.setColumns(10);
		
		lblAddress2 = new JLabel("Adresse");
		lblAddress2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAddress2 = new GridBagConstraints();
		gbc_lblAddress2.anchor = GridBagConstraints.WEST;
		gbc_lblAddress2.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress2.gridx = 1;
		gbc_lblAddress2.gridy = 3;
		updatePanel.add(lblAddress2, gbc_lblAddress2);
		
		txtAddress2 = new JTextField();
		txtAddress2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtAddress2 = new GridBagConstraints();
		gbc_txtAddress2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress2.gridwidth = 4;
		gbc_txtAddress2.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress2.gridx = 2;
		gbc_txtAddress2.gridy = 3;
		updatePanel.add(txtAddress2, gbc_txtAddress2);
		txtAddress2.setColumns(10);
		
		lblEmail2 = new JLabel("E-mail");
		lblEmail2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEmail2 = new GridBagConstraints();
		gbc_lblEmail2.anchor = GridBagConstraints.WEST;
		gbc_lblEmail2.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail2.gridx = 1;
		gbc_lblEmail2.gridy = 4;
		updatePanel.add(lblEmail2, gbc_lblEmail2);
		
		txtEmail2 = new JTextField();
		txtEmail2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtEmail2 = new GridBagConstraints();
		gbc_txtEmail2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail2.gridwidth = 4;
		gbc_txtEmail2.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail2.gridx = 2;
		gbc_txtEmail2.gridy = 4;
		updatePanel.add(txtEmail2, gbc_txtEmail2);
		txtEmail2.setColumns(10);
		
		lblAccount2 = new JLabel("Kontobel\u00F8b");
		lblAccount2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAccount2 = new GridBagConstraints();
		gbc_lblAccount2.anchor = GridBagConstraints.WEST;
		gbc_lblAccount2.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccount2.gridx = 1;
		gbc_lblAccount2.gridy = 5;
		updatePanel.add(lblAccount2, gbc_lblAccount2);
		lblAccount2.setVisible(false);
		
		txtAccount2 = new JTextField();
		txtAccount2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtAccount2 = new GridBagConstraints();
		gbc_txtAccount2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAccount2.gridwidth = 4;
		gbc_txtAccount2.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccount2.gridx = 2;
		gbc_txtAccount2.gridy = 5;
		updatePanel.add(txtAccount2, gbc_txtAccount2);
		txtAccount2.setColumns(10);
		txtAccount2.setVisible(false);
		
		lblDiscount2 = new JLabel("Rabatprocent");
		lblDiscount2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDiscount2 = new GridBagConstraints();
		gbc_lblDiscount2.anchor = GridBagConstraints.WEST;
		gbc_lblDiscount2.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscount2.gridx = 1;
		gbc_lblDiscount2.gridy = 6;
		updatePanel.add(lblDiscount2, gbc_lblDiscount2);
		lblDiscount2.setVisible(false);
		
		txtDiscount2 = new JTextField();
		txtDiscount2.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiscount2.setBounds(new Rectangle(50, 0, 0, 0));
		GridBagConstraints gbc_txtDiscount2 = new GridBagConstraints();
		gbc_txtDiscount2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDiscount2.gridwidth = 4;
		gbc_txtDiscount2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDiscount2.gridx = 2;
		gbc_txtDiscount2.gridy = 6;
		updatePanel.add(txtDiscount2, gbc_txtDiscount2);
		txtDiscount2.setColumns(10);
		txtDiscount2.setVisible(false);
		
		lblPosition2 = new JLabel("Position");
		lblPosition2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPosition2 = new GridBagConstraints();
		gbc_lblPosition2.anchor = GridBagConstraints.WEST;
		gbc_lblPosition2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPosition2.gridx = 1;
		gbc_lblPosition2.gridy = 5;
		updatePanel.add(lblPosition2, gbc_lblPosition2);
		lblPosition2.setVisible(true);
		
		txtPosition2 = new JTextField();
		txtPosition2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosition2.setLocation(new Point(0, 10));
		GridBagConstraints gbc_txtPosition2 = new GridBagConstraints();
		gbc_txtPosition2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPosition2.gridwidth = 4;
		gbc_txtPosition2.insets = new Insets(0, 0, 5, 5);
		gbc_txtPosition2.gridx = 2;
		gbc_txtPosition2.gridy = 5;
		updatePanel.add(txtPosition2, gbc_txtPosition2);
		txtPosition2.setColumns(10);
		txtPosition2.setVisible(true);
		
		btnUpdatePerson = new JButton("Opdater");
		GridBagConstraints gbc_btnUpdatePerson = new GridBagConstraints();
		gbc_btnUpdatePerson.anchor = GridBagConstraints.NORTH;
		gbc_btnUpdatePerson.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdatePerson.gridwidth = 5;
		gbc_btnUpdatePerson.gridx = 1;
		gbc_btnUpdatePerson.gridy = 7;
		updatePanel.add(btnUpdatePerson, gbc_btnUpdatePerson);
	}
	
	private void toDo()
	{
		btnCreatePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goCreate();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});

		btnUpdatePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goUpdate();
			}
		});
		
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goChange();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goSearch();
			}
		});
		
	}
	
	private void goBack()
	{
		MainUI mainUI = new MainUI(phoneNo);
		mainUI.setVisible(true);
		this.setVisible(false);
		clearTextFields();
	}
	
	private void goCreate()
	{
		try 
		{
			String strPhone = txtPhone.getText();
			int phone = Integer.parseInt(strPhone);
			
			String name = txtName.getText();
			String address = txtAddress.getText();
			String email = txtEmail.getText();
			
			if(btnChange.getText().contains("Medarbejder"))
			{
				try {
					String strAccount = txtAccount.getText();
					double account = Double.parseDouble(strAccount);
					
					String strDiscount = txtDiscount.getText();
					double discount = Double.parseDouble(strDiscount);
					
					cCtrl.createCustomer(address, email, name, phone, account, discount);
					JOptionPane.showMessageDialog(frame,"Kunden er nu oprettet.");
					clearTextFields();
				} catch (TooHighDiscountException e) {
					JOptionPane.showMessageDialog(frame, e.toString());
				}
			}
			else
			{
				String position = txtPosition.getText();
				String password = txtPassword.getText();
				
				eCtrl.createEmployee(address, email, name, phone, position, password);
				JOptionPane.showMessageDialog(frame,"Medarbejderen er nu oprettet.");
				clearTextFields();
			}
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Et af felterne rummer ikke tal!");
		}
	}
	
	private void goUpdate()
	{
		try {
			String strOldPhone = txtPhone2.getText();
			int oldPhone = Integer.parseInt(strOldPhone);
			
			String name = txtName2.getText();
			String address = txtAddress2.getText();
			String email = txtEmail2.getText();
			
			if(btnChange.getText().contains("Medarbejder"))
			{
				String strAccount = txtAccount2.getText();
				double account = Double.parseDouble(strAccount);
				
				String strDiscount = txtDiscount2.getText();
				double discount = Double.parseDouble(strDiscount);
				
				cCtrl.updateCustomer(oldPhone, address, email, name, oldPhone, account, discount);
				JOptionPane.showMessageDialog(frame, "Medarbejderen er nu opdateret.");
			}
			else
			{
				String position = txtPosition2.getText();
				eCtrl.updateEmployee(oldPhone, address, email, name, oldPhone, position);
				JOptionPane.showMessageDialog(frame, "Medarbejderen er nu opdateret.");
			}
		}
		catch(PersonNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Et af felterne rummer ikke tal!");
		}
		catch(TooHighDiscountException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		clearTextFields();
	}
	
	private void goChange()
	{
		//Feature to change visible fields, when switching between customer and employee
		if(btnChange.getText().contains("Kunde"))
		{
			changeTitle("Kunde");
			btnChange.setText("Medarbejder");
			
			lblPosition.setVisible(false);
			lblPosition2.setVisible(false);
			
			txtPosition.setVisible(false);
			txtPosition2.setVisible(false);
			
			txtPosition.setVisible(false);
			txtPosition2.setVisible(false);
			
			lblPassword.setVisible(false);
			txtPassword.setVisible(false);
			
			lblAccount.setVisible(true);
			lblAccount2.setVisible(true);
			
			txtAccount.setVisible(true);
			txtAccount2.setVisible(true);
			
			lblDiscount.setVisible(true);
			lblDiscount2.setVisible(true);
			
			txtDiscount.setVisible(true);
			txtDiscount2.setVisible(true);
		}
		else
		{
			changeTitle("Medarbejder");
			btnChange.setText("Kunde");
			
			lblPosition.setVisible(true);
			lblPosition2.setVisible(true);
			
			txtPosition.setVisible(true);
			txtPosition2.setVisible(true);
			
			lblPassword.setVisible(true);
			txtPassword.setVisible(true);
			
			lblAccount.setVisible(false);
			lblAccount2.setVisible(false);
			
			txtAccount.setVisible(false);
			txtAccount2.setVisible(false);
			
			lblDiscount.setVisible(false);
			lblDiscount2.setVisible(false);
			
			txtDiscount.setVisible(false);
			txtDiscount2.setVisible(false);
		}
		clearTextFields();
	}
	
	private void goSearch()
	{
		try
		{
			String strPhoneNo = txtPhoneNo.getText();
			int phoneNo = Integer.parseInt(strPhoneNo);
			String printOut = "";
			
			if(btnChange.getText().contains("Medarbejder"))
			{
				Customer customer = cCtrl.findCustomer(phoneNo);
				printOut += "Der findes nedenst\u00e5ende oplysninger om kunden";
				printOut += "\nNavn: " + customer.getName();
				printOut += "\nAdresse: " + customer.getAddress();
				printOut += "\nTelefon nr.: " + customer.getPhone();
				printOut += "\nE-mail adresse: " + customer.getEmail();
				printOut += "\nBel\u00f8b p\u00e5 kontoen: " + customer.getAccount();
				printOut += "\nRabat i butikken: " + customer.getDiscount() + "%";
			}
			else
			{
				Employee employee = eCtrl.findEmployee(phoneNo);
				printOut += "Der findes nedenst\u00e5ende oplysninger om medarbejderen";
				printOut += "\nNavn: " + employee.getName();
				printOut += "\nAdresse: " + employee.getAddress();
				printOut += "\nTelefon nr.: " + employee.getPhone();
				printOut += "\nE-mail adresse: " + employee.getEmail();
				printOut += "\nRang-position: " + employee.getPosition();
			}
			JOptionPane.showMessageDialog(frame, printOut.toString());
		}
		catch(PersonNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}

		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Du skal angive et telefonnummer!");
		}
		clearTextFields();
	}
	
	private void changeTitle(String title)
	{
		//Changes content on the titlebars
		lblSearchPerson.setText("Find " + title.toLowerCase());
		lblCreatePerson.setText("Opret " + title.toLowerCase());
		lblUpdatePerson.setText("Opdater " + title.toLowerCase());
	}
	
	private void clearTextFields()
	{
		txtName.setText("");
		txtName2.setText("");
		
		txtAddress.setText("");
		txtAddress2.setText("");
		
		txtEmail.setText("");
		txtEmail2.setText("");
		
		txtPhone.setText("");
		txtPhone2.setText("");
		txtPhoneNo.setText("");
		
		txtPosition.setText("");
		txtPosition2.setText("");
		
		txtAccount.setText("");
		txtAccount2.setText("");
		
		txtDiscount.setText("");
		txtDiscount2.setText("");
	}
	
	private void checkEmployeeStatus()
	{
		try {
			Employee employee = eCtrl.findEmployee(phoneNo);
			if(!(employee.getPosition().contains("CEO")))
			{
				//Disable editable of employee by employees
				goChange();
				btnChange.setVisible(false);
			}
		} catch (PersonNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
}
