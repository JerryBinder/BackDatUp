package main.java;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 * Main BackDatUp.java class
 * Includes UI implementation and button/event listeners and handlers
 * FileChooser implementation included in ActionEvents
 * @author jbschmi3
 *
 */
public class BackDatUp {
	Object[][] tableData;
	private JFrame BackDatUp;
	private final JFileChooser fileChooser = new JFileChooser();
	
	protected static File src;
	protected static ArrayList<String> dest;
	protected static JTable jobsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackDatUp window = new BackDatUp();
					window.BackDatUp.setTitle("Back Dat Up");
					window.BackDatUp.setVisible(true);
					window.BackDatUp.setPreferredSize(new Dimension(600, 500));
					window.BackDatUp.pack();
					window.BackDatUp.setResizable(false);
					window.BackDatUp.setLocationRelativeTo(null);
					window.BackDatUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					window.BackDatUp.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent we) {
							String ObjButtons[] = {"Yes", "No"};
							int result = JOptionPane.showOptionDialog(null, "Scheduled jobs are only run when program is running!  Are you sure you want to quit? (Missed jobs will be run next launch.)",
									"Exit Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
							if(result == JOptionPane.YES_OPTION) {
								System.exit(0);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BackDatUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		BackDatUp = new JFrame();
		BackDatUp.setBounds(100, 100, 633, 718);
		BackDatUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Table creation
		 */
		tableData = new Object[][] {};
		String[] columns = {"Source:",
				"Destination:",
				"Next Backup:",
				"Repetitions:"};
		BackDatUp.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jobsTable = new JTable(tableData, columns);
		jobsTable.setFillsViewportHeight(true);
		BackDatUp.getContentPane().add(new JScrollPane(jobsTable));
		
		// TODO: periodically call updateJobsTable()
		
		/*
		 * Button creation
		 */
		JPanel buttonPanel = new JPanel();
		BackDatUp.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JButton btnScheduleJob = new JButton("Add Job");
		buttonPanel.add(btnScheduleJob);
		JButton btnDeleteJob = new JButton("Delete Job");
		buttonPanel.add(btnDeleteJob);
		JButton btnEditJob = new JButton("Edit Job");
		buttonPanel.add(btnEditJob);
		
		/*
		 * Button behavior 
		 */
		btnScheduleJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addJob(btnScheduleJob);
			}
		});
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteJob(btnDeleteJob);
			}
		});
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editJob(btnEditJob);
			}
		});
	}
	
	/**
	 * Updates JList with the current list of Jobs.
	 * Called whenever the list is updated:
	 * 	- by adding a job
	 * 	- by removing a job
	 *  - by editing a job
	 *  - by job being executed
	 */
	private void updateJobsTable() {
		
	}
	
	
	
	private void addJob(JButton parent) {
		JFrame frame = new JFrame("Schedule a Job");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setPreferredSize(new Dimension(500, 250));
		frame.setPreferredSize(new Dimension(500, 250));
		frame.pack();
		
		JTextArea sourceText = new JTextArea();
		sourceText.setEditable(false);
		JTextArea destText = new JTextArea();
		destText.setEditable(false);
		
		
		JButton sourceBrowse = new JButton();
		sourceBrowse.setText("Browse for Source File...");
		JButton destBrowse = new JButton();
		destBrowse.setText("Browse for Destination Directory...");
		JButton ok = new JButton();
		ok.setText("Submit");
		
		JRadioButton instantJobRadio = new JRadioButton();
		instantJobRadio.setText("Instant Job");
		JRadioButton recurringJobRadio = new JRadioButton();
		recurringJobRadio.setText("Recurring Job");
		
		JTextArea intervalText = new JTextArea("Interval (Minutes)");
		JTextArea timesToRepeatText = new JTextArea("Times To Repeat");
		JTextArea timingText = new JTextArea("MM/DD HR:MN");	// TODO 
		
		sourceBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Choose Source File...");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				fileChooser.showOpenDialog(sourceBrowse);
				
				src = fileChooser.getSelectedFile();
				sourceText.setText(src.toString());
			}
		});
		destBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Choose Destination Directory...");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				fileChooser.showOpenDialog(destBrowse);
				
				dest = new ArrayList<String>();
				String temp = fileChooser.getSelectedFile().getAbsolutePath() + "\\" + src.getName();
				dest.add(temp);
				destText.setText(temp.toString());
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Job myJob = null;
				if(instantJobRadio.isSelected()) {
					myJob = new InstantJob(src, dest);
					Schedule.getInstance().addJob(myJob);
				} else if(recurringJobRadio.isSelected()) {
					// TODO
					// myJob = new RecurringJob(src, dest, timing, interval, timesToRepeat);
					// Schedule.getInstance().addJob(myJob);
				}
				
				src = null;
				dest = null;
			}
		});
		instantJobRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(instantJobRadio.isSelected())
					recurringJobRadio.setSelected(false);
			}
		});
		recurringJobRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recurringJobRadio.isSelected())
					instantJobRadio.setSelected(false);
			}
		});
		
		frame.add(panel);
		panel.add(sourceText);
		panel.add(sourceBrowse);
		panel.add(destText);
		panel.add(destBrowse);
		panel.add(ok);
		panel.add(instantJobRadio);
		panel.add(recurringJobRadio);
		panel.add(intervalText);
		panel.add(timingText);
		panel.add(timesToRepeatText);
		
		frame.setVisible(true);
	}
	
	private void deleteJob(JButton parent) {
		String ObjButtons[] = {"Yes", "No"};
		int result = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the selected job?",
				"Delete Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
		if(result == JOptionPane.YES_OPTION) {
			// TODO: refer to selected job, find it in jobslist, and delete it
		}
	}
	
	private void editJob(JButton parent) {
		JFrame frame = new JFrame("Schedule a Job");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setPreferredSize(new Dimension(500, 250));
		frame.setPreferredSize(new Dimension(500, 250));
		frame.pack();
		
		// TODO: fetch selected job
		// Job temp = JTable.selectedRow
	}
}


