package main.java;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import javax.swing.table.DefaultTableModel;

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
	protected static ArrayList<File> dest;
	protected static JTable jobsTable;
	protected static DefaultTableModel jobsModel;
	protected static int lastSelectedRow = -1;
	protected static SimpleDateFormat sdf = new SimpleDateFormat("M/d/y k:m", Locale.ENGLISH);

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
		
		/* checks for due jobs periodically and performs them */
		Runnable checkJobs = new Runnable() {
		    public void run() {
		        Schedule.getInstance().checkForDueJobs();
		        updateJobsTable();
		    }
		};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(checkJobs, 0, 5, TimeUnit.SECONDS);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		BackDatUp = new JFrame();
		BackDatUp.setFocusable(true);
		BackDatUp.setBounds(100, 100, 633, 718);
		BackDatUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Table creation
		 */
		tableData = new Object[][] {};
		
		String[] columns = {"Source:",
				"Destination:",
				"Next Backup:",
				"Repeats Scheduled:"};
		BackDatUp.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jobsModel = new DefaultTableModel(columns, 0);
		jobsTable = new JTable(jobsModel);
		jobsTable.setFillsViewportHeight(true);
		BackDatUp.getContentPane().add(new JScrollPane(jobsTable));
		
		updateJobsTable();		
		
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
		BackDatUp.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("key was pressed");
				if(e.getKeyChar() == 'a')
				{
					addJob(btnScheduleJob);
					BackDatUp.requestFocus();
					//System.out.println("a was pressed");
				}
				else if(e.getKeyChar() == 'd')
				{
					deleteJob(btnDeleteJob);
					BackDatUp.requestFocus();
					//System.out.println("d was pressed");
				}
				else if(e.getKeyChar() == 'e')
				{
					editJob(btnEditJob);
					BackDatUp.requestFocus();
					//System.out.println("e was pressed");
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
 			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		
		btnScheduleJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addJob(btnScheduleJob);
				BackDatUp.requestFocus();
			}
		});
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastSelectedRow == -1)
					JOptionPane.showMessageDialog(null, "No row is selected. Please select a row before pressing Delete Job.");
				else
					deleteJob(btnDeleteJob);
				BackDatUp.requestFocus();
			}
		});
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastSelectedRow == -1)
					JOptionPane.showMessageDialog(null, "No row is selected. Please select a row before pressing Edit Job.");
				else
					editJob(btnEditJob);
				BackDatUp.requestFocus();
			}
		});
		jobsTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lastSelectedRow = jobsTable.getSelectedRow();
				BackDatUp.requestFocus();
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
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
	protected void updateJobsTable() {
		System.out.println("Updating jobs table...");
		jobsModel.setRowCount(0); // empties table
		
		Object a[] = new Object[4];
		a[0] = "C:\\Sample.txt";
		a[1] = "C:\\destination\\";
		a[2] = "12/2/2032 1:50";
		a[3] = "0";
		jobsModel.addRow(a);
		
		Object rowData[] = new Object[4];
		for(Job j : Schedule.getInstance().getJobs()) {
			rowData[0] = j.getSourceFile().toString();
			rowData[1] = j.getDestinationPaths().get(0).toString();
			rowData[2] = sdf.format(j.getTiming().getTime());
			rowData[3] = j.getTimesToRepeat();
			jobsModel.addRow(rowData);
		}
		System.out.println("Jobs table updated.");
	}
	
	private void addJob(JButton parent) {
		JFrame frame = new JFrame("Schedule a Job");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setPreferredSize(new Dimension(500, 250));
		frame.setPreferredSize(new Dimension(500, 250));
		frame.setLocationRelativeTo(parent);
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
		JTextArea timingText = new JTextArea(sdf.format(Calendar.getInstance().getTime()));
		
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
				
				dest = new ArrayList<File>();
				String temp = fileChooser.getSelectedFile().getAbsolutePath() + "\\" + src.getName();
				dest.add(new File(temp));
				destText.setText(temp);
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Job myJob = null;
				Calendar timing = Calendar.getInstance();
		
				if(instantJobRadio.isSelected()){
					try {
						Date date = sdf.parse(timingText.getText());
						timing.setTime(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					myJob = new InstantJob(src, dest, timing);
					Schedule.getInstance().addJob(myJob);
				} else if(recurringJobRadio.isSelected()){
					int interval = 0;
					int timesToRepeat = 0;
					try {
						Date date = sdf.parse(timingText.getText());
						timing.setTime(date);
						
						interval = Integer.parseInt(intervalText.getText());
						timesToRepeat = Integer.parseInt(timesToRepeatText.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					myJob = new RecurringJob(src, dest, timing, interval, timesToRepeat);
					Schedule.getInstance().addJob(myJob);
				}
				src = null;
				dest = null;
				updateJobsTable();
				frame.dispose();
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
		int result = JOptionPane.showConfirmDialog(null, "Do you want to delete this job's existing backups too?",
				"Delete Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
		boolean deleteBackupsToo = false;
		if(result == JOptionPane.YES_OPTION)
			deleteBackupsToo = true;
		if(result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION){
			// File sourceToDelete = new File((String) jobsTable.getValueAt(lastSelectedRow, 0));
			File destinationToDelete = new File((String) jobsTable.getValueAt(lastSelectedRow, 1));
			jobsModel.removeRow(lastSelectedRow);
			for(Job j : Schedule.getInstance().getJobs()){
				if(j.destinationPaths.contains(destinationToDelete)){
					Schedule.getInstance().deleteJob(j.destinationPaths.get(0), deleteBackupsToo);
				}
			}
		}
		updateJobsTable();
	}
	
	private void editJob(JButton parent) {
		JFrame frame = new JFrame("Edit Job");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setPreferredSize(new Dimension(500, 250));
		frame.setPreferredSize(new Dimension(500, 250));
		frame.setLocationRelativeTo(parent);
		frame.pack();
		frame.setVisible(true);
		
		for(Job j : Schedule.getInstance().getJobs()) {
			if(j.sourceFile.equals(jobsTable.getValueAt(lastSelectedRow, 0))
					&& j.destinationPaths.get(0).equals(jobsTable.getValueAt(lastSelectedRow, 1))) {
				// TODO: open editor panel
				updateJobsTable();
			}
		}
	}
}
