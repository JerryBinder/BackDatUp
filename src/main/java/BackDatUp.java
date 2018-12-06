package main.java;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.Label;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
					window.BackDatUp.setPreferredSize(new Dimension(640, 480));
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
	private void initialize() {
		BackDatUp = new JFrame();
		BackDatUp.setBounds(100, 100, 709, 566);
		BackDatUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BackDatUp.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tableData = new Object[][] {};
		String[] columns = {"Source:",
				"Destination:",
				"Next Backup:",
				"Repetitions:"};
		JTable jobsTable = new JTable(tableData, columns);
		JScrollPane scrollPane = new JScrollPane(jobsTable);
		jobsTable.setFillsViewportHeight(true);
		BackDatUp.getContentPane().add(scrollPane);
		
		JPanel buttonPanel = new JPanel();
		BackDatUp.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//display backups button
		JButton btnDisplayBackups = new JButton("Display Backups");		
		buttonPanel.add(btnDisplayBackups);
		
//		//setup for adding to textPane
//		Document doc = textPane.getDocument();
//		
//		//add each element of jobQueue ArrayList to the pane
//		for(String s:jobQueue) {
//			try {
//				doc.insertString(doc.getLength(), s, null);
//				doc.insertString(doc.getLength(), "\n", null);
//			} catch (BadLocationException e) {
//				e.printStackTrace();
//			}
//		}
		
		//schedule job button
		JButton btnScheduleJob = new JButton("Schedule a job");
		buttonPanel.add(btnScheduleJob);
		
		//delete a job button
		JButton btnDeleteJob = new JButton("Delete a job");
		buttonPanel.add(btnDeleteJob);
		
		//edit a job button
		JButton btnEditJob = new JButton("Edit a job");
		buttonPanel.add(btnEditJob);
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "TODO: Add edit ? select from job list in text area or separate popup?", "edit item from job list", 1);
			}
		});
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "TODO: Add delete ? select from job list in text area or separate popup?", "delete item from job list", 1);
			}
		});
		btnScheduleJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel scheduler = new JPanel();
				scheduler.add(new JLabel("Source file:"));
				//display dialog
				fileChooser.setDialogTitle("Choose Source File...");
				
				//open the file chooser for location of file
				fileChooser.showOpenDialog(btnScheduleJob);
				
				//allow user to select files and directories
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				//get path and filename of file to copy
				// String path = fileChooser.getSelectedFile().getAbsolutePath();
				File file = fileChooser.getSelectedFile();
				// String filename=fileChooser.getSelectedFile().getName();
				
				//display dialog
				fileChooser.setDialogTitle("Choose Destination Directory...");
				
				//allow user to select a directory to copy to
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				//open file chooser for destination
				fileChooser.showOpenDialog(btnScheduleJob);
				
				//get path to copy file to
				ArrayList<String> destPath = new ArrayList<String>();
				String temp = fileChooser.getSelectedFile().getAbsolutePath();
				destPath.add(temp);
				
				//TODO open a dialog with a selection of instant or recurring job
					// depending on selection, Times To Repeat field is enabled/disabled
					// when user hits "Ok" it creates a job of the specified type and adds it
				
				//create job
				InstantJob myJob = new InstantJob(file, destPath);
				Schedule.getInstance().addJob(myJob);
			}
		});
		btnDisplayBackups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//display all backup locations
				JOptionPane.showMessageDialog(null, "TODO: Add backup locations", "backup locations", 1);
			}
		});
		
	}
	/**
	 * Updates JList with the current list of Jobs.
	 * Called whenever the list is updated:
	 * 	- by adding a job
	 * 	- by removing a job
	 *  - by editing a job
	 */
	private void updateJobsList() {
		
	}
}
