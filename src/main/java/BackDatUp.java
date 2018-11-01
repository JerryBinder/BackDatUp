package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class BackDatUp {
    private JPanel panelMain;
    private JButton scheduleAJobButton;
    private JButton deleteAJobButton;
    private JButton displayBackupsButton;
    private JButton editAJobButton;

    public BackDatUp() {
        scheduleAJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //schedule job logic
            }
        });
        deleteAJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show scheduled jobs to delete
            }
        });
        editAJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        displayBackupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        //create jframe for application
        JFrame frame = new JFrame("BackDatUp");
        frame.setContentPane(new BackDatUp().panelMain);
        //close program operation
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Scheduled jobs are only run when program is running!  Are you sure you want to quit? (Missed jobs will be run next launch)",
                            "Exit Confirmation: ", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else if(result == JOptionPane.NO_OPTION)
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });




        //display
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
