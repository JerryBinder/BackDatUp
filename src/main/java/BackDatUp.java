package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class BackDatUp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackDatUp window = new BackDatUp();
					window.frame.setVisible(true);
					window.frame.setPreferredSize(new Dimension(800, 600));
					window.frame.pack();
					window.frame.setResizable(false);
					window.frame.setLocationRelativeTo(null);
					window.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					window.frame.addWindowListener(new WindowAdapter() {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblJobQueue = new JLabel("Job Queue:");
		GridBagConstraints gbc_lblJobQueue = new GridBagConstraints();
		gbc_lblJobQueue.anchor = GridBagConstraints.WEST;
		gbc_lblJobQueue.insets = new Insets(0, 0, 5, 0);
		gbc_lblJobQueue.gridx = 0;
		gbc_lblJobQueue.gridy = 0;
		frame.getContentPane().add(lblJobQueue, gbc_lblJobQueue);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridheight = 5;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 1;
		frame.getContentPane().add(textPane, gbc_textPane);
		
		JButton btnScheduleJob = new JButton("Schedule a job");
		btnScheduleJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDisplayBackups = new JButton("Display Backups");
		GridBagConstraints gbc_btnDisplayBackups = new GridBagConstraints();
		gbc_btnDisplayBackups.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisplayBackups.gridx = 0;
		gbc_btnDisplayBackups.gridy = 6;
		frame.getContentPane().add(btnDisplayBackups, gbc_btnDisplayBackups);
		GridBagConstraints gbc_btnScheduleJob = new GridBagConstraints();
		gbc_btnScheduleJob.insets = new Insets(0, 0, 5, 0);
		gbc_btnScheduleJob.gridx = 0;
		gbc_btnScheduleJob.gridy = 7;
		frame.getContentPane().add(btnScheduleJob, gbc_btnScheduleJob);
		
		JButton btnDeleteJob = new JButton("Delete a job");
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnDeleteJob = new GridBagConstraints();
		gbc_btnDeleteJob.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeleteJob.gridx = 0;
		gbc_btnDeleteJob.gridy = 8;
		frame.getContentPane().add(btnDeleteJob, gbc_btnDeleteJob);
		
		JButton btnEditJob = new JButton("Edit a job");
		btnEditJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnEditJob = new GridBagConstraints();
		gbc_btnEditJob.gridx = 0;
		gbc_btnEditJob.gridy = 9;
		frame.getContentPane().add(btnEditJob, gbc_btnEditJob);
	}

}
