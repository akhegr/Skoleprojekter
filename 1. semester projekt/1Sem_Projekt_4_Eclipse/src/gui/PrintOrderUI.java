package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import exceptions.OrderNotExistException;
import controller.OrderCtrl;
import model.Customer;
import model.Order;
import model.OrderLineItem;
import model.Product;


public class PrintOrderUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitle;
	private JLabel lblOrderList;
	private JButton okButton;
	private JButton cancelButton;
	private JScrollPane scrollPane;
	//private JFrame frame;
	
	private OrderCtrl oCtrl;
	private Order order;
	private int orderId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrintOrderUI dialog = new PrintOrderUI(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PrintOrderUI(int orderId) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		oCtrl = new OrderCtrl();
		this.orderId = orderId;
		
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{89, 107, 0, 187, 0};
		gbl_contentPanel.rowHeights = new int[]{31, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		lblTitle = new JLabel("Ordre: " + orderId);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		contentPanel.add(lblTitle, gbc_lblTitle);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridwidth = 4;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			
			lblOrderList = new JLabel("");
			lblOrderList.setVerticalAlignment(SwingConstants.TOP);
			scrollPane.setViewportView(lblOrderList);
			lblOrderList.setHorizontalAlignment(SwingConstants.LEFT);
			lblOrderList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Se faktura");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Annuller");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		checkExist();
		toDo();
		createList();
	}
	
	private void toDo()
	{
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openInvoice();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelOrder();
			}
		});
	}
	
	private void checkExist()
	{
		try {
			order = oCtrl.findOrder(orderId);
		} catch (OrderNotExistException e) {
			this.closeDialog();
		}
	}
	
	private void createList()
    {
        try {
			Map<Integer, OrderLineItem> map;
			map = oCtrl.getOli(orderId);
			
			if(map.isEmpty())
	        {
				okButton.setEnabled(false);
				lblOrderList.setText("Ordrelisten er tom!");
	        }
	        else
	        {
	        	// Print the content of the hashMap
	        	String orderToPrint = "<html><p style=\"width=50px\">";
	        	
            	Customer customer = order.getCustomer();
            	
            	for(int i = 0; i < map.size(); i++) {
	            	OrderLineItem tempOLI = map.get(i);
	            	Product tempProduct = tempOLI.getProduct();
	        		
	        		orderToPrint += "Produkt: " + tempProduct.getName();
	            	orderToPrint += "<br>M\u00e6ngde: " + tempOLI.getAmount();
	            	orderToPrint += "<br>Pris pr. stk.: " + currencyFormat(tempProduct.getPrice());
	            	double productPrice = tempOLI.getAmount() * tempProduct.getPrice();
					orderToPrint += "<br>Pris samlet: " + currencyFormat(productPrice);
					orderToPrint += "<br><br>";
	            }
	        	
	        	if(!(customer == null))
            	{
	        		double discount = customer.getDiscount();
	        		double sumWithDiscount = oCtrl.sumWithDiscount(orderId);
	        		
	        		orderToPrint += "<br><br>Rabat: " + currencyFormat(discount) + "%";
	        		orderToPrint += "<br>Sum inkl. rabat: " + currencyFormat(sumWithDiscount) + " kr.";
	        		
	        		double currentAccount = customer.getAccount();
	        		customer.setAccount(currentAccount - sumWithDiscount);
            	}
	        	else
	        	{
	        		double sum = order.getTotal();
	        		orderToPrint += "<br><br>Den samlede pris er: " + currencyFormat(sum);
	        	}
	        	
            	orderToPrint += "</p></html>";
            	lblOrderList.setText(orderToPrint);
	        }
		} catch (OrderNotExistException e) {
			e.toString();
		}
    }
	
	private String currencyFormat(double price)
	{
		return oCtrl.getCurrencyFormat(price);
	}
	
	private void openInvoice()
	{
		PayInvoice payInvoice = new PayInvoice(orderId);
		payInvoice.setVisible(true);
		closeDialog();
	}
	
	private void cancelOrder()
	{
		String[] choices = {"Ja", "Nej"};
		
		//http://www.fredosaurus.com/notes-java/GUI/containers/20dialogs/10joptionpane-2.html
		int response = JOptionPane.showOptionDialog(
                null                       			// Center in window.
              , "Vil du lukke ordren?"        // Message
              , "Message"							// Title in titlebar
              , JOptionPane.YES_NO_OPTION			// Option type
              , JOptionPane.QUESTION_MESSAGE		// messageType
              , null								// Icon (none)
              , choices								// Button text as above.
              , null								// Default button's label
            );
		
		if(response == 0)
		{
			closeDialog();
		}
	}
	
	private void closeDialog()
	{
		this.setVisible(false);
		this.setModal(false);
	}
}
