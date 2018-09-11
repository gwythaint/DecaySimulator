package net.nevadatechnical.demo;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DecayDemo implements ActionListener {

	public Timer timer;
	private AtomSandbox sandbox;
	private DecayChart chart;
	private ControlPanel control;
	private JSplitPane split;
	private JPanel topPanel, left, right;

	public DecayDemo() {
		super();
		timer = new Timer(10, this);
		timer.setActionCommand("tick");
		
		
		control = new ControlPanel(this);
		sandbox = new AtomSandbox(100);
		chart = new DecayChart(500, 300, 100);
		

		left = new JPanel();
		right = new JPanel();

		left.setLayout(new FlowLayout());

		left.add(sandbox);
		left.add(control);
		right.add(chart);

		split = new JSplitPane( JSplitPane.VERTICAL_SPLIT);
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		topPanel.add(split);
		
		split.setTopComponent(left);
		split.setBottomComponent(right);
		split.setDividerLocation(500);
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Radioactive Decay Simulation");	
		DecayDemo demo = new DecayDemo();

		frame.getContentPane().add(demo.topPanel);

		frame.setSize(850, 850);
		frame.setVisible(true);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
	}

	private Component getControl() {
		return this.control;
	}

	private Component getSandbox() {
		return this.sandbox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* time interval happened */
		if (e.getActionCommand().equals("tick")) {
			sandbox.tick(e);
			chart.tick(e);
			chart.update(this.sandbox.nAtoms);
		}
		
		/* reset atoms */
		if (e.getActionCommand().equals("reset")) {
			timer.stop();
			chart.reset();
			sandbox = new AtomSandbox(2500);
		}

		/* start simulator */
		if (e.getActionCommand().equals("start")) {
			timer.start();
		}
			
	}
}


