package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CalenderGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel calModel;
	private Calendar cal;
	private JTable daysOfMonthTable;
	private JLabel monthLabel;
	private JFrame frame;
	private String month;
	private int year;
	
	/**
	 * Much of the logic for the calendar was made with help from
	 * https://www.javacodex.com/Swing/Swing-Calendar.
	 * It has been modified to fit the project
	 */
	
	public CalenderGUI() {
		cal = new GregorianCalendar();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 11;
		gbc_panel_4.gridy = 0;
		add(panel_4, gbc_panel_4);

		JButton btnPrevious = new JButton("Forrige");
		btnPrevious.addActionListener((x) -> {
			cal.add(Calendar.MONTH, -1);
			updateMonth();
		});

		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.fill = GridBagConstraints.BOTH;
		gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevious.gridx = 0;
		gbc_btnPrevious.gridy = 1;
		add(btnPrevious, gbc_btnPrevious);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);

		monthLabel = new JLabel("");
		GridBagConstraints gbc_month = new GridBagConstraints();
		gbc_month.fill = GridBagConstraints.BOTH;
		gbc_month.insets = new Insets(0, 0, 5, 5);
		gbc_month.gridx = 5;
		gbc_month.gridy = 1;
		add(monthLabel, gbc_month);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 9;
		gbc_panel_3.gridy = 1;
		add(panel_3, gbc_panel_3);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 10;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);

		JButton btnNext = new JButton("N\u00E6ste");
		btnNext.addActionListener((x) -> {
			cal.add(Calendar.MONTH, + 1);
			updateMonth();
		});

		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.fill = GridBagConstraints.BOTH;
		gbc_btnNext.gridx = 11;
		gbc_btnNext.gridy = 1;
		add(btnNext, gbc_btnNext);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 12;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		String [] columns = {"Søndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"};
		calModel = new DefaultTableModel(null,columns) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//Cellerne i kalenderen må ikke kunne redigeres.
				return false;
			}
			
		};
		daysOfMonthTable = new JTable(calModel);
		daysOfMonthTable.setRowSelectionAllowed(false);
		daysOfMonthTable.setColumnSelectionAllowed(false);
		
		daysOfMonthTable.setFont(new Font("Arial", 1, 50));
		daysOfMonthTable.setRowHeight(55);
		daysOfMonthTable.setSelectionBackground(Color.red);
		daysOfMonthTable.getTableHeader().setReorderingAllowed(false);
		
		JTableUtil.setCellsAlignment(daysOfMonthTable, SwingConstants.CENTER);
		
		scrollPane.setViewportView(daysOfMonthTable);
		
		JButton btnSelectDate = new JButton("V\u00E6lg dato");
		btnSelectDate.addActionListener((x) -> selectDate());
		btnSelectDate.setPreferredSize(new Dimension(75, 50));
		btnSelectDate.setFont(new Font("Arial", Font.PLAIN, 35));
		GridBagConstraints gbc_btnSelectDate = new GridBagConstraints();
		gbc_btnSelectDate.fill = GridBagConstraints.BOTH;
		gbc_btnSelectDate.gridx = 11;
		gbc_btnSelectDate.gridy = 3;
		add(btnSelectDate, gbc_btnSelectDate);

		updateMonth();

	}
	
	/** 
	 * Checks whether a date is selected,
	 * if not it notifies the user about it.
	 */
	private void selectDate() {
		try {
			int row = daysOfMonthTable.getSelectedRow();
			int column = daysOfMonthTable.getSelectedColumn();
			String chosenDay = daysOfMonthTable.getValueAt(row, column).toString();
			if(chosenDay != null) {
				String date = chosenDay + "-" + month + "-" + year;
				goToPlanner(date);
			} else {
				JOptionPane.showMessageDialog(frame, "Vælg en dato først");
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	/**
	 * Creates an instance of PlanningDialog,
	 * and gives it the chosen date
	 * @param date
	 */
	private void goToPlanner(String date) {
		PlanningDialog planGUI = new PlanningDialog(date);
		planGUI.setVisible(true);
		this.setVisible(false);
	}
	
	/**
	 * Changes the table of days verses weekdays
	 */
	private void updateMonth() {
		try {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			updateMonthLabel();
			int startDay = cal.get(Calendar.DAY_OF_WEEK);
			int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
			clearCalModel();
			calModel.setRowCount(weeks+2);
			int i = startDay - 1;
			for(int day=1;day<=numberOfDays;day++){
				calModel.setValueAt(day, i/7, i%7);    
				i = i + 1;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}

	/**
	 * Clears the content of the calendar
	 */
	private void clearCalModel() {
		calModel.setRowCount(0);
	}

	/**
	 * Updates the labels of month and year
	 */
	private void updateMonthLabel() {
		month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		year = cal.get(Calendar.YEAR);
		monthLabel.setText(month + " " + year);
	}
	
};
