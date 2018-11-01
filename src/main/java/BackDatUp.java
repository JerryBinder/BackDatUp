package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //display
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
