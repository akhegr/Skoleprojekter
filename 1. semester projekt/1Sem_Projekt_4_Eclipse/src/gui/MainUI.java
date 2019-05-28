package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeCtrl;
import exceptions.PersonNotExistException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int phoneNo;
	private JPanel contentPane;
	private JPanel topPanel;
	private JLabel lblTopBarItem;
	private JButton btnLogout;
	private JButton btnOrder;
	private JButton btnProduct;
	private JButton btnUser;
	private JLabel lblEmployee;
	private EmployeeCtrl eCtrl;
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI(0);
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
	public MainUI(int phoneNo) {
		eCtrl = new EmployeeCtrl();
		this.phoneNo = phoneNo;
		initUI();
		toDo();
	}
	
	public void initUI()
	{
		this.setTitle("Vestbjerg Byggecenter A/S");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setBounds(5, 15, 465, 51);
		contentPane.add(topPanel);
		
		lblTopBarItem = new JLabel();
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {50, 280, 100};
		gbl_topPanel.rowHeights = new int[]{49, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		btnLogout = new JButton("Log ud");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.WEST;
		gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogout.gridx = 0;
		gbc_btnLogout.gridy = 0;
		topPanel.add(btnLogout, gbc_btnLogout);
		
		lblTopBarItem = new JLabel();
		lblTopBarItem.setIcon(new ImageIcon(OrderUI.class.getResource("/ico/Vestbjerg.png")));
		lblTopBarItem.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTopBarItem = new GridBagConstraints();
		gbc_lblTopBarItem.insets = new Insets(0, 0, 0, 5);
		gbc_lblTopBarItem.gridx = 1;
		gbc_lblTopBarItem.gridy = 0;
		topPanel.add(lblTopBarItem, gbc_lblTopBarItem);
		
		lblEmployee = new JLabel("navn");
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmployee.gridx = 2;
		gbc_lblEmployee.gridy = 0;
		topPanel.add(lblEmployee, gbc_lblEmployee);
		setEmployee(lblEmployee);
		
		btnUser = new JButton("Person menu");
		btnUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUser.setIcon(new ImageIcon(MainUI.class.getResource("/ico/user.jpg")));
		btnUser.setBounds(30, 75, 120, 165);
		contentPane.add(btnUser);

		btnUser.setBorderPainted(false);
		btnUser.setBorder(null);
		btnUser.setContentAreaFilled(false);
		
		btnProduct = new JButton("Produkt menu");
		btnProduct.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProduct.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProduct.setIcon(new ImageIcon(MainUI.class.getResource("/ico/product.jpg")));
		btnProduct.setBounds(162, 77, 134, 165);
		contentPane.add(btnProduct);

		btnProduct.setBorderPainted(false);
		btnProduct.setBorder(null);
		btnProduct.setContentAreaFilled(false);
		
		btnOrder = new JButton("Ordre menu");
		btnOrder.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOrder.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOrder.setIcon(new ImageIcon(MainUI.class.getResource("/ico/shop.jpg")));
		btnOrder.setBounds(314, 80, 117, 160);
		contentPane.add(btnOrder);

		btnOrder.setBorderPainted(false);
		btnOrder.setBorder(null);
		btnOrder.setContentAreaFilled(false);
	}
	
	public void toDo()
	{
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goLogout();
			}
		});
		
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goUserUI();
			}
		});
		
		String userText = "<html><font size=\"5\">H&aring;ndtering af medarbejdere og kunder</font></html>";
		btnUser.setToolTipText(userText);
		
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goProductUI();
			}
		});
		
		String productText = "<html><font size=\"5\">H&aring;ndtering af produkter</font></html>";
		btnProduct.setToolTipText(productText);
		
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goShopUI();
			}
		});
		
		String orderText = "<html><font size=\"5\">H&aring;ndtering af ordrer</font></html>";
		btnOrder.setToolTipText(orderText);
	}

	public void goLogout()
	{
		LoginUI loginUI = new LoginUI();
		loginUI.setVisible(true);
		this.setVisible(false);
	}
	
	public void goUserUI()
	{
		PersonUI personUI = new PersonUI(phoneNo);
		personUI.setVisible(true);
		this.setVisible(false);
	}
	
	public void goProductUI()
	{
		ProductUI productUI = new ProductUI(phoneNo);
		productUI.setVisible(true);
		this.setVisible(false);
	}
	
	public void goShopUI()
	{
		OrderUI orderUI = new OrderUI(phoneNo);
		orderUI.setVisible(true);
		this.setVisible(false);	
	}
	
	public void setEmployee(JLabel employee)
	{
		try {
			String employeeName = eCtrl.findName(phoneNo);
			String content = "<html>Medarbejder:<br>" + employeeName + "</html>";
			employee.setText(content);
		} catch (PersonNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
}