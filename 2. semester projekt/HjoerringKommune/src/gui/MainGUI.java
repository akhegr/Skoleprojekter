package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.ConCheckCtrl;
import exception.DbLayerException;

public class MainGUI {
	private JFrame frame;
	private static JFrame jFrame;
	private JPanel dependentPanel;
	private JPanel employeePanel;
	private JPanel calenderPanel;
	private JProgressBar progBarStatus;
	private JPanel panel;
	private JLabel lblStatus;
	private ConCheckCtrl conCheckCtrl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(jFrame, e.toString());
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public MainGUI() throws SQLException {
		initialize();
		conCheckCtrl = new ConCheckCtrl();
		
		//A thread initialized, which checks the db connection, through a method in ctrl-class
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
		    		try {
						Thread.sleep(5000);
						boolean status = conCheckCtrl.conCheck();
						updateStatus(status);
					} catch (SQLException e) {
						updateStatus(false);
					} catch (InterruptedException e) {
						updateStatus(false);
					} catch (DbLayerException e) {
						JOptionPane.showMessageDialog(frame, e.toString());
					}
		    	}
			}
		});
		t.start();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		dependentPanel = new DependentGUI();
		employeePanel = new EmployeeGUI();
		calenderPanel = new CalenderGUI();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1234, 1013, 0};
		gridBagLayout.rowHeights = new int[]{0, 489, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lblStatus = new JLabel("Unders\u00F8ger forbindelse til db");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		frame.getContentPane().add(lblStatus, gbc_lblStatus);
		
		progBarStatus = new JProgressBar();
		GridBagConstraints gbc_progBarStatus = new GridBagConstraints();
		gbc_progBarStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_progBarStatus.insets = new Insets(0, 0, 5, 0);
		gbc_progBarStatus.gridx = 1;
		gbc_progBarStatus.gridy = 0;
		frame.getContentPane().add(progBarStatus, gbc_progBarStatus);
		progBarStatus.setToolTipText("");
		progBarStatus.setForeground(Color.decode("#90EE90"));
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1013, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel.add(tabbedPane, gbc_tabbedPane);
		GridBagLayout gridBagLayout_1 = (GridBagLayout) calenderPanel.getLayout();
		gridBagLayout_1.rowHeights = new int[] {30, 30, 520};
		tabbedPane.addTab("Kalender", calenderPanel);
		tabbedPane.addTab("Plejekr\u00E6vende", dependentPanel);
		tabbedPane.addTab("Medarbejder", employeePanel);
		
		JScrollPane scrollPane = new JScrollPane();
	}
	
	/**
	 * Shows the change of database connection status,
	 * on progress bar and label
	 * @param status
	 */
	public void updateStatus(boolean status) {
		if(status) {
			progBarStatus.setValue(100);
			lblStatus.setText("Forbindelse til db");
		}
		else {
			progBarStatus.setValue(0);
			lblStatus.setText("Ingen forbindelse til db");
		}
	}
}
