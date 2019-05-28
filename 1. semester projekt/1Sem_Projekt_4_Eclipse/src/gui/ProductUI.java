package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductCtrl;
import exceptions.ProductNotExistException;
import model.Product;

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

public class ProductUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ProductCtrl pCtrl;

	private int phoneNo;
	private JButton btnBack;
	private JButton btnCreateProduct;
	private JButton btnSearch;
	private JButton btnUpdateProduct;
	private JFrame frame;
	private JLabel lblAmount2;
	private JLabel lblAmount3;
	private JLabel lblCategory2;
	private JLabel lblCreateProduct;
	private JLabel lblName2;
	private JLabel lblPrice3;
	private JLabel lblBarcode2;
	private JLabel lblBarcode3;
	private JLabel lblPrice2;
	private JLabel lblBarcode1;
	private JLabel lblSearchProduct;
	private JLabel lblTopBarItem;
	private JLabel lblUpdateProduct;
	private JLabel lblEmployee;
	private JTextField txtCategory2;
	private JTextField txtName2;
	private JTextField txtPrice3;
	private JTextField txtBarcode2;
	private JTextField txtBarcode3;
	private JTextField txtPrice2;
	private JTextField txtBarcode1;
	private JTextField txtAmount2;
	private JTextField txtAmount3;
	private JPanel updatePanel;
	
	;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductUI frame = new ProductUI(0);
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
	public ProductUI(int phoneNo) {
		this.phoneNo = phoneNo;
		initUI();
		toDo();
	}
	
	public void initUI()
	{
		pCtrl = new ProductCtrl();
		
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
		findPanel.setBounds(5, 77, 155, 129);
		contentPane.add(findPanel);
		
		lblSearchProduct = new JLabel("Find produkt");
		findPanel.add(lblSearchProduct);
		lblSearchProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblBarcode1 = new JLabel("<html>Indtast produktets<br>stregkode</html>");
		findPanel.add(lblBarcode1);
		lblBarcode1.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtBarcode1 = new JTextField();
		findPanel.add(txtBarcode1);
		txtBarcode1.setColumns(5);
		
		btnSearch = new JButton("S\u00F8g");
		findPanel.add(btnSearch);

		
		
		
		
		JPanel createPanel = new JPanel();
		createPanel.setBounds(162, 77, 246, 187);
		contentPane.add(createPanel);
		GridBagLayout gbl_createPanel = new GridBagLayout();
		gbl_createPanel.columnWidths = new int[] {95, 100};
		gbl_createPanel.rowHeights = new int[]{19, 22, 22, 22, 22, 25, 0, 0};
		gbl_createPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_createPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		createPanel.setLayout(gbl_createPanel);
		
		lblCreateProduct = new JLabel("Opret produkt");
		GridBagConstraints gbc_lblCreateProduct = new GridBagConstraints();
		gbc_lblCreateProduct.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCreateProduct.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateProduct.gridwidth = 2;
		gbc_lblCreateProduct.gridx = 1;
		gbc_lblCreateProduct.gridy = 0;
		createPanel.add(lblCreateProduct, gbc_lblCreateProduct);
		lblCreateProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreateProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblBarcode2 = new JLabel("Stregkode");
		lblBarcode2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBarcode2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBarcode2 = new GridBagConstraints();
		gbc_lblBarcode2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblBarcode2.insets = new Insets(0, 0, 5, 5);
		gbc_lblBarcode2.gridx = 0;
		gbc_lblBarcode2.gridy = 1;
		createPanel.add(lblBarcode2, gbc_lblBarcode2);
		
		txtBarcode2 = new JTextField();
		GridBagConstraints gbc_txtBarcode2 = new GridBagConstraints();
		gbc_txtBarcode2.anchor = GridBagConstraints.NORTH;
		gbc_txtBarcode2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode2.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode2.gridx = 1;
		gbc_txtBarcode2.gridy = 1;
		createPanel.add(txtBarcode2, gbc_txtBarcode2);
		txtBarcode2.setColumns(10);
		
		lblCategory2 = new JLabel("Kategori");
		GridBagConstraints gbc_lblCategory2 = new GridBagConstraints();
		gbc_lblCategory2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCategory2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory2.gridx = 0;
		gbc_lblCategory2.gridy = 2;
		createPanel.add(lblCategory2, gbc_lblCategory2);
		
		txtCategory2 = new JTextField();
		GridBagConstraints gbc_txtCategory2 = new GridBagConstraints();
		gbc_txtCategory2.anchor = GridBagConstraints.NORTH;
		gbc_txtCategory2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategory2.insets = new Insets(0, 0, 5, 5);
		gbc_txtCategory2.gridx = 1;
		gbc_txtCategory2.gridy = 2;
		createPanel.add(txtCategory2, gbc_txtCategory2);
		txtCategory2.setColumns(10);
		
		lblName2 = new JLabel("Varens navn");
		GridBagConstraints gbc_lblName2 = new GridBagConstraints();
		gbc_lblName2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblName2.insets = new Insets(0, 0, 5, 5);
		gbc_lblName2.gridwidth = 2;
		gbc_lblName2.gridx = 0;
		gbc_lblName2.gridy = 3;
		createPanel.add(lblName2, gbc_lblName2);
		
		txtName2 = new JTextField();
		GridBagConstraints gbc_txtName2 = new GridBagConstraints();
		gbc_txtName2.anchor = GridBagConstraints.NORTH;
		gbc_txtName2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName2.insets = new Insets(0, 0, 5, 5);
		gbc_txtName2.gridx = 1;
		gbc_txtName2.gridy = 3;
		createPanel.add(txtName2, gbc_txtName2);
		txtName2.setColumns(10);
		
		lblPrice2 = new JLabel("Varens pris");
		GridBagConstraints gbc_lblPrice2 = new GridBagConstraints();
		gbc_lblPrice2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPrice2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice2.gridx = 0;
		gbc_lblPrice2.gridy = 4;
		createPanel.add(lblPrice2, gbc_lblPrice2);
		
		txtPrice2 = new JTextField();
		GridBagConstraints gbc_txtPrice2 = new GridBagConstraints();
		gbc_txtPrice2.anchor = GridBagConstraints.NORTH;
		gbc_txtPrice2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrice2.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrice2.gridx = 1;
		gbc_txtPrice2.gridy = 4;
		createPanel.add(txtPrice2, gbc_txtPrice2);
		txtPrice2.setColumns(10);
		
		lblAmount2 = new JLabel("M\u00E6ngde");
		GridBagConstraints gbc_lblAmount2 = new GridBagConstraints();
		gbc_lblAmount2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblAmount2.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount2.gridx = 0;
		gbc_lblAmount2.gridy = 5;
		createPanel.add(lblAmount2, gbc_lblAmount2);
		
		txtAmount2 = new JTextField();
		txtAmount2.setColumns(10);
		GridBagConstraints gbc_txtAmount2 = new GridBagConstraints();
		gbc_txtAmount2.insets = new Insets(0, 0, 5, 5);
		gbc_txtAmount2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmount2.gridx = 1;
		gbc_txtAmount2.gridy = 5;
		createPanel.add(txtAmount2, gbc_txtAmount2);
		

		btnCreateProduct = new JButton("Opret");
		GridBagConstraints gbc_btnCreateProduct = new GridBagConstraints();
		gbc_btnCreateProduct.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateProduct.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCreateProduct.gridx = 1;
		gbc_btnCreateProduct.gridy = 6;
		createPanel.add(btnCreateProduct, gbc_btnCreateProduct);
		
		
		updatePanel = new JPanel();
		updatePanel.setBounds(418, 77, 269, 129);
		contentPane.add(updatePanel);
		GridBagLayout gbl_updatePanel = new GridBagLayout();
		gbl_updatePanel.columnWidths = new int[]{32, 0, 0, 0, 0, 79, 36, 0};
		gbl_updatePanel.rowHeights = new int[] {19, 22, 16, 0, 0};
		gbl_updatePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_updatePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		updatePanel.setLayout(gbl_updatePanel);
		
		lblUpdateProduct = new JLabel("Opdater produkt");
		GridBagConstraints gbc_lblUpdateProduct = new GridBagConstraints();
		gbc_lblUpdateProduct.anchor = GridBagConstraints.NORTH;
		gbc_lblUpdateProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpdateProduct.gridwidth = 5;
		gbc_lblUpdateProduct.gridx = 1;
		gbc_lblUpdateProduct.gridy = 0;
		updatePanel.add(lblUpdateProduct, gbc_lblUpdateProduct);
		lblUpdateProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		lblBarcode3 = new JLabel("Stregkode");
		GridBagConstraints gbc_lblBarcode3 = new GridBagConstraints();
		gbc_lblBarcode3.anchor = GridBagConstraints.WEST;
		gbc_lblBarcode3.insets = new Insets(0, 0, 5, 5);
		gbc_lblBarcode3.gridx = 1;
		gbc_lblBarcode3.gridy = 1;
		updatePanel.add(lblBarcode3, gbc_lblBarcode3);
		
		txtBarcode3 = new JTextField();
		GridBagConstraints gbc_txtBarcode3 = new GridBagConstraints();
		gbc_txtBarcode3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBarcode3.gridwidth = 4;
		gbc_txtBarcode3.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode3.gridx = 2;
		gbc_txtBarcode3.gridy = 1;
		updatePanel.add(txtBarcode3, gbc_txtBarcode3);
		txtBarcode3.setColumns(10);
		
		lblPrice3 = new JLabel("Varens pris");
		lblPrice3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrice3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPrice3 = new GridBagConstraints();
		gbc_lblPrice3.anchor = GridBagConstraints.WEST;
		gbc_lblPrice3.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice3.gridx = 1;
		gbc_lblPrice3.gridy = 2;
		updatePanel.add(lblPrice3, gbc_lblPrice3);
		
		txtPrice3 = new JTextField();
		GridBagConstraints gbc_txtPrice3 = new GridBagConstraints();
		gbc_txtPrice3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrice3.gridwidth = 4;
		gbc_txtPrice3.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrice3.gridx = 2;
		gbc_txtPrice3.gridy = 2;
		updatePanel.add(txtPrice3, gbc_txtPrice3);
		txtPrice3.setColumns(10);
			
		lblAmount3 = new JLabel("M\u00E6ngde");
		GridBagConstraints gbc_lblAmount3 = new GridBagConstraints();
		gbc_lblAmount3.anchor = GridBagConstraints.SOUTHWEST;
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
		gbc_btnUpdateProduct.gridwidth = 5;
		gbc_btnUpdateProduct.gridx = 2;
		gbc_btnUpdateProduct.gridy = 4;
		updatePanel.add(btnUpdateProduct, gbc_btnUpdateProduct);
	}
	
	private void toDo()
	{
			
		btnCreateProduct.addActionListener(new ActionListener() {
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
	
	private void goCreate()
	{
		try 
		{
			String strBarcode = txtBarcode2.getText();
			int barcode = Integer.parseInt(strBarcode);
			
			String category = txtCategory2.getText();
			String name = txtName2.getText();
			
			String strPrice = txtPrice2.getText();
			double price = Double.parseDouble(strPrice);
			
			String strAmount = txtAmount2.getText();
			int amount = Integer.parseInt(strAmount);
			
			pCtrl.createProduct(barcode, category, name, price, amount);
			JOptionPane.showMessageDialog(frame,"Produktet er nu oprettet.");
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame,"Et af felterne rummer ikke tal!");
		}
		clearTextFields();
	}
	
	private void goUpdate()
	{
		try {
			String strBarcode = txtBarcode3.getText();
			int barcode = Integer.parseInt(strBarcode);
			
			String strPrice = txtPrice3.getText();
			double price = Double.parseDouble(strPrice);
			
			String strAmount = txtAmount3.getText();
			int amount = Integer.parseInt(strAmount);
			
			pCtrl.updateProduct(barcode, price);
			pCtrl.updateAmount(barcode, amount);
			JOptionPane.showMessageDialog(frame, "Produktet er nu opdateret.");
		}
		catch(ProductNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame,"Et af felterne rummer ikke tal!");
		}
		clearTextFields();
	}
	
	private void goSearch()
	{
		try
		{
			String strBarcode = txtBarcode1.getText();
			int barcode = Integer.parseInt(strBarcode);
			Product product = pCtrl.findProduct(barcode);
			
			String printOut = "Der findes nedenst\u00e5ende oplysninger om produktet";
			printOut += "\nNavn: " + product.getName();
			printOut += "\nKategori: " + product.getCategory();
			printOut += "\nPris: " + product.getPrice();
			printOut += "\nM\u00e6ngde p\u00e5 lager: " + product.getAmount();
			JOptionPane.showMessageDialog(frame, printOut.toString());
		}
		catch(ProductNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}

		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(frame,"Du skal angive en stregkode!");
		}
		clearTextFields();
	}
	
	private void clearTextFields()
	{
		txtBarcode1.setText("");
		
		txtBarcode2.setText("");
		txtCategory2.setText("");
		txtName2.setText("");
		txtAmount2.setText("");
		txtPrice2.setText("");
		
		txtBarcode3.setText("");
		txtPrice3.setText("");
		txtAmount3.setText("");
	}
}
