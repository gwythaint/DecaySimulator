package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private JButton reset;
	private JButton start;

	
	public ControlPanel (DecayDemo decayDemo) {
		this.setLayout(new FlowLayout());
		
		reset = new JButton("Reset");
		start = new JButton("Start");

		reset.setActionCommand("reset");
		start.setActionCommand("start");
	
		reset.addActionListener(decayDemo);
		start.addActionListener(decayDemo);
		
		this.add(reset);
		this.add(start);
		
		this.setBackground(Color.blue);
	}

}
