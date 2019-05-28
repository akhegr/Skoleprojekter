package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DependentCtrl;
import exception.DbLayerException;
import model.Dependent;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.List;
import java.awt.GridBagConstraints;

public class ListDependentDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel;
	private static JFrame jFrame;
	private JFrame frame;
	private DependentCtrl depCtrl;
	private Dependent dep;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListDependentDialog dialog = new ListDependentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jFrame, e.toString());
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListDependentDialog() {
		try {
			depCtrl = new DependentCtrl();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		} catch (DbLayerException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
		contentPanel = new JPanel();
		setModal(true);
		setBounds(100, 100, 430, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{119, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		
		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
		JList<String> depList = new JList<String>(defaultListModel);
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				scrollPane.setViewportView(depList);
				for(Dependent d : listDependents()) {
					((DefaultListModel<String>) depList.getModel()).addElement(d.getfName() + " " + d.getlName());
				}
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("V\u00E6lg plejekr\u00E6vende");
				/*
				 * Runs selectDependent() with the given position on the list + 1,
				 * caused the list starts on 0
				 */
					  
				okButton.addActionListener((x) -> selectDependent(depList.getSelectedIndex() + 1));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	/**
	 * Returns all dependents in a list
	 * @return list of dependents
	 */
	private List<Dependent> listDependents() {
		List<Dependent> listOfDependents = null;
		try {
			listOfDependents = depCtrl.findAll();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return listOfDependents;
	}

	/**
	 * The user gets found by id 
	 * @param id
	 */
	private void selectDependent(int id) {
		try {
			dep = depCtrl.findById(id);
			this.setVisible(false);	
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Returns the chosen user
	 * @return dependent
	 */
	public Dependent getDependent() {
		return dep;
	}
}
