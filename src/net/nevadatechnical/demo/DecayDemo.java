package net.nevadatechnical.demo;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DecayDemo implements ActionListener, SimulatorListener {

	public Timer timer;
	private AtomSandbox sandbox;
	private DecayChart chart;
	private ControlPanel control;
	private JSplitPane split;
	private JPanel topPanel, left, right;
	private int nAtoms;

	public DecayDemo(int nAtoms) {
		super();
		this.nAtoms = nAtoms;
		timer = new Timer(10, this);
		timer.setActionCommand("tick");
		
		
		control = new ControlPanel(this);
		sandbox = new AtomSandbox(nAtoms);
		chart = new DecayChart(500, 300, nAtoms);
		

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
		DecayDemo demo = new DecayDemo(2500);

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
			chart.update(sandbox.nAtoms);
		}
		
		/* reset atoms */
		if (e.getActionCommand().equals("reset")) {
			timer.stop();
			sandbox.reset();
			chart.reset();
			chart.update(sandbox.nAtoms);
			control.labelUpdate("Start");
		}
		

		/* start simulator */
		if (e.getActionCommand().equals("start")) {
			if(timer.isRunning()) {
				timer.stop();
				control.labelUpdate("Start");
			} else {
			timer.start();
				control.labelUpdate("Pause");
			}
		}
			
	}

	@Override
	public void modelChange(ModelEvent event) {
		timer.stop();
		sandbox.nAtomsStart = event.value;
		sandbox.reset();
		chart.reset();
		chart.update(sandbox.nAtoms);
		control.labelUpdate("Start");
	}
}


