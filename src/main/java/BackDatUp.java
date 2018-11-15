package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JFileChooser;

/**
 * Main BackDatUp.java class
 * Includes UI implementation and button/event listeners and handlers
 * FileChooser implementation included in ActionEvents
 * @author jbschmi3
 *
 */
public class BackDatUp {

	private JFrame BackDatUp;
	/**
	 * @wbp.nonvisual location=14,329
	 */
	private final JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackDatUp window = new BackDatUp();
					window.BackDatUp.setVisible(true);
					window.BackDatUp.setPreferredSize(new Dimension(800, 600));
					window.BackDatUp.pack();
					window.BackDatUp.setResizable(false);
					window.BackDatUp.setLocationRelativeTo(null);
					window.BackDatUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					window.BackDatUp.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent we) {
							String ObjButtons[] = {"Yes", "No"};
							int result = JOptionPane.showOptionDialog(null, "Scheduled jobs are only run when program is running!  Are you sure you want to quit? (Missed jobs will be run next launch)",
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
		
		//Create new schedule - should add logic here to add XML/save data to the schedule
		Schedule schedule = new Schedule();		
		//ArrayList of jobQueue
		ArrayList<String> jobQueue = new ArrayList<>();
		
		//test variables
		jobQueue.add("Job 1: Scheduled for 12/12/18 14:30\n");
		jobQueue.add("Job 2: Scheduled for 12/13/18 10:22\n");
		
		BackDatUp = new JFrame();
		BackDatUp.setBounds(100, 100, 450, 300);
		BackDatUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		BackDatUp.getContentPane().setLayout(springLayout);
		
		//Job queue label
		JLabel lblJobQueue = new JLabel("Job Queue:");
		springLayout.putConstraint(SpringLayout.NORTH, lblJobQueue, 0, SpringLayout.NORTH, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblJobQueue, 0, SpringLayout.WEST, BackDatUp.getContentPane());
		BackDatUp.getContentPane().add(lblJobQueue);
		
		//Job queue textarea
		JTextPane textPane = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, textPane, 19, SpringLayout.NORTH, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textPane, 0, SpringLayout.WEST, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textPane, 209, SpringLayout.NORTH, BackDatUp.getContentPane());
		textPane.setEditable(false);
		BackDatUp.getContentPane().add(textPane);
		
		//setup for adding to textPane
		Document doc = textPane.getDocument();
		//add each element of jobQueue ArrayList to the pane
		for(String s:jobQueue) {
			try {
				doc.insertString(doc.getLength(), s, null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		//schedule job button
		JButton btnScheduleJob = new JButton("Schedule a job");
		
		springLayout.putConstraint(SpringLayout.WEST, btnScheduleJob, -187, SpringLayout.EAST, BackDatUp.getContentPane());
		btnScheduleJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//display dialog				
				JOptionPane.showMessageDialog(null, "Choose a File/directory to Backup", "Backup a File", 1);
				//open the file chooser for location of file
				fileChooser.showOpenDialog(btnScheduleJob);
				//allow user to select files and directories
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//get path and filename of file to copy
				String path=fileChooser.getSelectedFile().getAbsolutePath();
				String filename=fileChooser.getSelectedFile().getName();
				
				//display dialog				
				JOptionPane.showMessageDialog(null, "Choose a Backup Location", "Directory to Backup", 1);
				//open file chooser for destination
				fileChooser.showOpenDialog(btnScheduleJob);
				//allow user to select a directory to copy to
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//get path to copy file to'
				ArrayList<String> destPath = new ArrayList<String>();
				String temp=fileChooser.getSelectedFile().getAbsolutePath();
				destPath.add(temp);
				
				//TODO: get user input here for backup interval/timing
				//get calendar timing
				//Calendar timing;
				//display dialog
				//JOptionPane.showMessageDialog(null, "Choose how often to backup", "Backup Interval", 1);
				
				//create job
				RecurringJob myJob = new RecurringJob(path+filename, destPath, 1, 1);
				schedule.addJob(myJob);
			}
		});
		
		//display backups button
		JButton btnDisplayBackups = new JButton("Display Backups");
		springLayout.putConstraint(SpringLayout.NORTH, btnScheduleJob, 6, SpringLayout.SOUTH, btnDisplayBackups);
		springLayout.putConstraint(SpringLayout.EAST, btnScheduleJob, 0, SpringLayout.EAST, btnDisplayBackups);
		springLayout.putConstraint(SpringLayout.EAST, textPane, -35, SpringLayout.WEST, btnDisplayBackups);
		springLayout.putConstraint(SpringLayout.EAST, btnDisplayBackups, -39, SpringLayout.EAST, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnDisplayBackups, -187, SpringLayout.EAST, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnDisplayBackups, 19, SpringLayout.NORTH, BackDatUp.getContentPane());
		BackDatUp.getContentPane().add(btnDisplayBackups);
		BackDatUp.getContentPane().add(btnScheduleJob);
		
		//delete a job button
		JButton btnDeleteJob = new JButton("Delete a job");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteJob, 6, SpringLayout.SOUTH, btnScheduleJob);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteJob, -187, SpringLayout.EAST, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteJob, 0, SpringLayout.EAST, btnDisplayBackups);
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		BackDatUp.getContentPane().add(btnDeleteJob);
		
		JButton btnEditJob = new JButton("Edit a job");
		springLayout.putConstraint(SpringLayout.NORTH, btnEditJob, 6, SpringLayout.SOUTH, btnDeleteJob);
		springLayout.putConstraint(SpringLayout.WEST, btnEditJob, -187, SpringLayout.EAST, BackDatUp.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnEditJob, 0, SpringLayout.EAST, btnDisplayBackups);
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BackDatUp.getContentPane().add(btnEditJob);
	}
}
