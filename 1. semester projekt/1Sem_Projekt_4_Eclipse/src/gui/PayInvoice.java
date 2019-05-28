package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

import model.Order;
import model.OrderLineItem;
import model.Product;
//import model.Product;
import model.Customer;
import model.Employee;
import controller.OrderCtrl;
import exceptions.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;



public class PayInvoice extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitle;
	private JPanel buttonPane;
	private JButton payButton;
	private JButton cancelButton;
	private int orderId;
	private JFrame frame;
	
	private OrderCtrl oCtrl;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PayInvoice dialog = new PayInvoice(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PayInvoice(int orderId) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		
		this.orderId = orderId;
		oCtrl = new OrderCtrl();
		
		initUI();
		toDo();
		checkIsPayed();
		initOrder();
	}

	public void initUI()
	{
		setBounds(100, 100, 600, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{572, 0};
		gbl_contentPanel.rowHeights = new int[]{49, 404, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setIcon(new ImageIcon(PayInvoice.class.getResource("/ico/Vestbjerg.png")));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.NORTH;
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPanel.add(lblTitle, gbc_lblTitle);
		{
			
			String strCompany = "<html>Vestbjerg Byggecenter";
			strCompany += "<br>9380 Vestbjerg</html>";
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			
			table = new JTable();
			table.setEnabled(false);
			scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, strCompany},
					{null, null, null, null, null},
					{"Vare id", "Produkt", "Antal", "Pris pr. stk.", "Pris"},
					{null, null, null, null, null},
				},
				new String[] {
					"", "", "", "", ""
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			table.getColumnModel().getColumn(0).setMinWidth(120);
			table.getColumnModel().getColumn(0).setMaxWidth(120);
			table.getColumnModel().getColumn(1).setPreferredWidth(110);
			table.getColumnModel().getColumn(1).setMinWidth(110);
			table.getColumnModel().getColumn(1).setMaxWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(40);
			table.getColumnModel().getColumn(2).setMinWidth(40);
			table.getColumnModel().getColumn(2).setMaxWidth(60);
			table.getColumnModel().getColumn(3).setPreferredWidth(70);
			table.getColumnModel().getColumn(3).setMinWidth(70);
			table.getColumnModel().getColumn(3).setMaxWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(140);
			table.getColumnModel().getColumn(4).setMinWidth(140);
			table.getColumnModel().getColumn(4).setMaxWidth(300);
			model = (DefaultTableModel) table.getModel();
			updateRowHeights();
		}
		
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				payButton = new JButton("Betal ordre");
				payButton.setActionCommand("pay");
				buttonPane.add(payButton);
				getRootPane().setDefaultButton(payButton);
	 		}
			{
				cancelButton = new JButton("Afbryd");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void initOrder()
	{
		try {
			Map<Integer, OrderLineItem> map = oCtrl.getOli(orderId);
			if(!map.isEmpty())
	        {	
				Order order = oCtrl.findOrder(orderId);
	        	Customer customer = order.getCustomer();
	        	Employee employee = order.getEmployee();
				
            	if(customer != null)
            	{
            		String strCustomer = "<html>" + customer.getName();
            		strCustomer += "<br>" + customer.getAddress();
            		strCustomer += "<br>" + customer.getEmail();
            		strCustomer += "<br>" + customer.getPhone();
            		strCustomer += "<br>Bel&oslash;b på konto:<br>" + currencyFormat(customer.getAccount()) + " kr.";
            		model.setValueAt(strCustomer, 0, 0);
            	}
            	
            	
            	String strInvoiceData = "<html><b>FAKTURA</b>";
	        	strInvoiceData += "<br>Faktura nr. " + orderId;
            	strInvoiceData += "<br>Fakturadato " + order.getDate();
            	strInvoiceData += "<br>Medarbejder " + employee.getName() + "</html>";
            	model.setValueAt(strInvoiceData, 1, 4);
            	
            	int currentCell = 3;
            	for(int i = 0; i < map.size(); i++) {
	            	OrderLineItem tempOLI = map.get(i);
	            	Product tempProduct = tempOLI.getProduct();
	        		
	            	model.addRow(new Object[]{"", "", "", "", ""});
	            	model.setValueAt(tempProduct.getBarcode(), currentCell, 0);
	            	model.setValueAt(tempProduct.getName(), currentCell, 1);
	            	model.setValueAt(tempOLI.getAmount(), currentCell, 2);
	            	model.setValueAt(currencyFormat(tempProduct.getPrice()), currentCell, 3);
	            	
	            	double sum = tempOLI.getAmount() * tempProduct.getPrice();
	            	model.setValueAt(currencyFormat(sum) + " kr.", currentCell, 4);
	            	currentCell++;
	            }
            	
            	model.addRow(new Object[]{"", "", "", "", ""});
            	
            	if(customer != null)
            	{
            		model.addRow(new Object[]{"", "", "", "Rabat:", currencyFormat(customer.getDiscount()) + "%"});
            		model.addRow(new Object[]{"", "", "", "Total:", currencyFormat(oCtrl.sumWithDiscount(orderId)) + " kr."});
            	}
            	else
            	{
            		model.addRow(new Object[]{"", "", "", "Total:", currencyFormat(order.getTotal()) + " kr."});
            	}
            	
            	updateRowHeights();
	        }
		} catch (OrderNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	
	}
	
	private String currencyFormat(double price)
	{
		return oCtrl.getCurrencyFormat(price);
	}
	
	private void checkIsPayed()
	{
		try {
			if(oCtrl.checkIsPayed(orderId))
			{
				payButton.setEnabled(false);
				payButton.setVisible(false);
			}
		} catch (OrderNotExistException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	// https://stackoverflow.com/questions/1783607/auto-adjust-the-height-of-rows-in-a-jtable
	private void updateRowHeights()
	{
		for (int row = 0; row < table.getRowCount(); row++)
	    {
	        int rowHeight = table.getRowHeight();

	        for (int column = 0; column < table.getColumnCount(); column++)
	        {
	            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        table.setRowHeight(row, rowHeight);
	    }
	}
	
	// https://stackoverflow.com/questions/17627431/auto-resizing-the-jtable-column-widths
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	private void toDo() {
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				payOrder();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelOrder();
			}
		});
	}
	
	private void payOrder()
	{
		try
		{
			if(!(oCtrl.checkIsPayed(orderId)))
			{
				if(oCtrl.finishOrder(orderId))
				{
					JOptionPane.showMessageDialog(frame, "Ordren er betalt!");
					payButton.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Kunden har ikke nok penge p\u00e5 kontoen!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Kunden har allerede betalt ordren!");
				
			}
		}
		catch(OrderNotExistException e)
		{
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	private void cancelOrder()
	{
		String[] choices = {"Ja", "Nej"};
		
		//http://www.fredosaurus.com/notes-java/GUI/containers/20dialogs/10joptionpane-2.html
		int response = JOptionPane.showOptionDialog(
                null                       			// Center in window.
              , "Vil du afbryde betalingen?"        // Message
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

