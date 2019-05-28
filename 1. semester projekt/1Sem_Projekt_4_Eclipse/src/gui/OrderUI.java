package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controller.OrderCtrl;
import controller.ProductCtrl;
import exceptions.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OrderUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private OrderCtrl oCtrl;
	private ProductCtrl pCtrl;
	
	private int phoneNo;
	private JButton btnBack;
	private JButton btnCreateOrder;
	private JButton btnSearch;
	private JButton btnUpdateProduct;
	private JLabel lblCustomer2;
	private JLabel lblCreateOrder;
	private JLabel lblDate2;
	private JLabel lblBarcode3;
	private JLabel lblOrderNo3;
	private JLabel lblOrderNo1;
	private JLabel lblSearchOrder;
	private JLabel lblTopBarItem;
	private JLabel lblAddProduct;
	private JLabel lblEmployee;
	private JFrame frame;
	private JTextField txtAmount3;
	private JTextField txtCustomer2;
	private JTextField txtDate2;
	private JTextField txtBarcode3;
	private JTextField txtOrderNo3;
	private JTextField txtOrderNo1;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUI frame = new OrderUI(0);
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
	public OrderUI(int phoneNo) {
		this.phoneNo = phoneNo;
		initUI();
		toDo();
	}
	
	public void initUI()
	{
		oCtrl = new OrderCtrl();
		pCtrl = new ProductCtrl();
		
		this.setTitle("Vestbjerg Byggecenter A/S");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(5, 10, 665, 51);
		contentPane.add(topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {50, 470, 100};
		gbl_topPanel.rowHeights = new int[]{49, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		btnBack = new JButton("Tilbage");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.setBounds(5, 5, 76, 49);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
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
		
		lblEmployee = new JLabel("navn");
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmployee.gridx = 2;
		gbc_lblEmployee.gridy = 0;
		topPanel.add(lblEmployee, gbc_lblEmployee);
		MainUI mainUI = new MainUI(phoneNo);
		mainUI.setEmployee(lblEmployee);
		
		
		JPanel findPanel = new JPanel();
		findPanel.setBounds(3, 77, 163, 129);
		contentPane.add(findPanel);
		
		lblSearchOrder = new JLabel("Find ordre");
		findPanel.add(lblSearchOrder);
		lblSearchOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblOrderNo1 = new JLabel("Indtast ordrenr.");
		findPanel.add(lblOrderNo1);
		lblOrderNo1.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtOrderNo1 = new JTextField();
		findPanel.add(txtOrderNo1);
		txtOrderNo1.setColumns(8);
		
		btnSearch = new JButton("S\u00F8g");
		findPanel.add(btnSearch);
		contentPane.add(new JSeparator(SwingConstants.VERTICAL));
		
		JPanel createPanel = new JPanel();
		createPanel.setBounds(175, 77, 236, 135);
		contentPane.add(createPanel);
		GridBagLayout gbl_createPanel = new GridBagLayout();
		gbl_createPanel.columnWidths = new int[] {95, 100};
		gbl_createPanel.rowHeights = new int[] {19, 22, 22, 30, 22};
		gbl_createPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_createPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		createPanel.setLayout(gbl_createPanel);
		
		lblCreateOrder = new JLabel("Opret ordre");
		GridBagConstraints gbc_lblCreateOrder = new GridBagConstraints();
		gbc_lblCreateOrder.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCreateOrder.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateOrder.gridwidth = 2;
		gbc_lblCreateOrder.gridx = 1;
		gbc_lblCreateOrder.gridy = 0;
		createPanel.add(lblCreateOrder, gbc_lblCreateOrder);
		lblCreateOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreateOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblCustomer2 = new JLabel("Kunde tlf.nr.");
		lblCustomer2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCustomer2 = new GridBagConstraints();
		gbc_lblCustomer2.fill = GridBagConstraints.VERTICAL;
		gbc_lblCustomer2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCustomer2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer2.gridx = 0;
		gbc_lblCustomer2.gridy = 1;
		createPanel.add(lblCustomer2, gbc_lblCustomer2);
		
		txtCustomer2 = new JTextField();
		txtCustomer2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_txtCustomer2 = new GridBagConstraints();
		gbc_txtCustomer2.anchor = GridBagConstraints.NORTH;
		gbc_txtCustomer2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomer2.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomer2.gridx = 1;
		gbc_txtCustomer2.gridy = 1;
		createPanel.add(txtCustomer2, gbc_txtCustomer2);
		txtCustomer2.setColumns(10);
		
		lblDate2 = new JLabel("Dagens dato");
		lblDate2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDate2 = new GridBagConstraints();
		gbc_lblDate2.fill = GridBagConstraints.VERTICAL;
		gbc_lblDate2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDate2.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate2.gridx = 0;
		gbc_lblDate2.gridy = 2;
		createPanel.add(lblDate2, gbc_lblDate2);
		
		
		txtDate2 = new JTextField();
		txtDate2.setHorizontalAlignment(SwingConstants.LEFT);
		txtDate2.setEditable(false);
		GridBagConstraints gbc_txtDate2 = new GridBagConstraints();
		gbc_txtDate2.fill = GridBagConstraints.BOTH;
		gbc_txtDate2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate2.gridx = 1;
		gbc_txtDate2.gridy = 2;
		createPanel.add(txtDate2, gbc_txtDate2);
		txtDate2.setColumns(10);
		
		btnCreateOrder = new JButton("Opret");
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateOrder.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCreateOrder.gridx = 1;
		gbc_btnCreateOrder.gridy = 3;
		createPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBounds(418, 77, 269, 135);
		contentPane.add(updatePanel);
		GridBagLayout gbl_updatePanel = new GridBagLayout();
		gbl_updatePanel.columnWidths = new int[]{32, 0, 0, 0, 0, 79, 36, 0};
		gbl_updatePanel.rowHeights = new int[] {19, 22, 22, 22, 16};
		gbl_updatePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_updatePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		updatePanel.setLayout(gbl_updatePanel);
		
		lblAddProduct = new JLabel("Tilf\u00F8j produkt");
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.anchor = GridBagConstraints.NORTH;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddProduct.gridwidth = 5;
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 0;
		updatePanel.add(lblAddProduct, gbc_lblAddProduct);
		lblAddProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		

		lblOrderNo3 = new JLabel("Ordre nr.");
		GridBagConstraints gbc_lblOrderNo3 = new GridBagConstraints();
		gbc_lblOrderNo3.fill = GridBagConstraints.VERTICAL;
		gbc_lblOrderNo3.anchor = GridBagConstraints.WEST;
		gbc_lblOrderNo3.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderNo3.gridx = 1;
		gbc_lblOrderNo3.gridy = 1;
		updatePanel.add(lblOrderNo3, gbc_lblOrderNo3);
		
		txtOrderNo3 = new JTextField();
		GridBagConstraints gbc_txtOrderNo3 = new GridBagConstraints();
		gbc_txtOrderNo3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderNo3.gridwidth = 4;
		gbc_txtOrderNo3.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrderNo3.gridx = 2;
		gbc_txtOrderNo3.gridy = 1;
		updatePanel.add(txtOrderNo3, gbc_txtOrderNo3);
		txtOrderNo3.setColumns(10);
		
		lblBarcode3 = new JLabel("Varens stregkode");
		lblBarcode3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBarcode3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBarcode3 = new GridBagConstraints();
		gbc_lblBarcode3.fill = GridBagConstraints.VERTICAL;
		gbc_lblBarcode3.anchor = GridBagConstraints.WEST;
		gbc_lblBarcode3.insets = new Insets(0, 0, 5, 5);
		gbc_lblBarcode3.gridx = 1;
		gbc_lblBarcode3.gridy = 2;
		updatePanel.add(lblBarcode3, gbc_lblBarcode3);
		
		txtBarcode3 = new JTextField();
		GridBagConstraints gbc_txtBarcode3 = new GridBagConstraints();
		gbc_txtBarcode3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode3.gridwidth = 4;
		gbc_txtBarcode3.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode3.gridx = 2;
		gbc_txtBarcode3.gridy = 2;
		updatePanel.add(txtBarcode3, gbc_txtBarcode3);
		txtBarcode3.setColumns(10);
		
		JLabel lblAmount3 = new JLabel("M\u00E6ngde af varen");
		GridBagConstraints gbc_lblAmount3 = new GridBagConstraints();
		gbc_lblAmount3.fill = GridBagConstraints.VERTICAL;
		gbc_lblAmount3.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount3.gridx = 1;
		gbc_lblAmount3.gridy = 3;
		updatePanel.add(lblAmount3, gbc_lblAmount3);

		txtAmount3 = new JTextField();
		GridBagConstraints gbc_txtAmount3 = new GridBagConstraints();
		gbc_txtAmount3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmount3.gridwidth = 4;
		gbc_txtAmount3.insets = new Insets(0, 0, 5, 5);
		gbc_txtAmount3.gridx = 2;
		gbc_txtAmount3.gridy = 3;
		updatePanel.add(txtAmount3, gbc_txtAmount3);
		txtAmount3.setColumns(10);
		
		btnUpdateProduct = new JButton("Opdater");
		GridBagConstraints gbc_btnUpdateProduct = new GridBagConstraints();
		gbc_btnUpdateProduct.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnUpdateProduct.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdateProduct.gridwidth = 5;
		gbc_btnUpdateProduct.gridx = 2;
		gbc_btnUpdateProduct.gridy = 4;
		updatePanel.add(btnUpdateProduct, gbc_btnUpdateProduct);
	}
	
	private void toDo()
	{
		setDate();
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goCreate();
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});

		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goUpdate();
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
	
	private void setDate()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String thisDate = sdf.format(timestamp);
		txtDate2.setText(thisDate);
	}
	
	private void goCreate()
	{
		try 
		{
			//String strEmployee = txtEmployee2.getText();
			//int employeePhone = Integer.parseInt(strEmployee);
			
			String date = txtDate2.getText();
			String strCustomer = txtCustomer2.getText();
			
			int orderId = oCtrl.createOrder(date, phoneNo);
			String submitText = "Der er nu oprettet en ordre.\n";
			String printId = "Den fik ordrenummer: " + orderId;
		    JOptionPane.showMessageDialog(frame, submitText + printId);
			
			if(!strCustomer.isEmpty())
			{
				int customerPhone = Integer.parseInt(strCustomer);
				oCtrl.addCustomer(customerPhone, orderId);
				JOptionPane.showMessageDialog(frame,"Kunden er tilf\u00f8jet til ordren.");
			}
		}
		catch(OrderNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(PersonNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Et af felterne rummer ikke tal!");
		}
		clearTextFields();
	}
	
	private void goUpdate()
	{
		try {
			String strOrderId = txtOrderNo3.getText();
			int orderId = Integer.parseInt(strOrderId);
			
			String strBarcode = txtBarcode3.getText();
			int barcode = Integer.parseInt(strBarcode);
			
			String strAmount = txtAmount3.getText();
			int amount = Integer.parseInt(strAmount);
			
			pCtrl.retrieveAmount(barcode, amount);
			oCtrl.addOli(amount, barcode, orderId);
			JOptionPane.showMessageDialog(frame, "Produktet er nu tilf\u00F8jet til ordren!");
		}
		catch(OrderNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(ProductNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(ProductNotOnStockException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame, "Et af felterne rummer ikke tal!");
		}
		clearTextFields();
	}
	
	private void goSearch()
	{
		String strOrderNo = txtOrderNo1.getText();
		int orderNo = Integer.parseInt(strOrderNo);
		goOrderList(orderNo);
	}
	
	private void goOrderList(int orderID)
	{
		PrintOrderUI printOrderUI = new PrintOrderUI(orderID);
		printOrderUI.setVisible(true);
	}
	
	private void clearTextFields()
	{
		txtOrderNo1.setText("");
		
		txtCustomer2.setText("");
		//txtDate2.setText("");
		
		txtBarcode3.setText("");
		txtOrderNo3.setText("");
		txtAmount3.setText("");
	}
}
