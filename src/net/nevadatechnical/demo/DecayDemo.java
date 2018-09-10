package net.nevadatechnical.demo;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DecayDemo implements ActionListener {

	public Timer timer;
	private AtomSandbox sandbox;
	private ControlPanel control;
	private JSplitPane split;
	private JPanel topPanel;

	public DecayDemo() {
		super();
		this.timer = new Timer(10, this);
		this.timer.setActionCommand("tick");
		
		
		control = new ControlPanel(this);
		sandbox = new AtomSandbox(); 	
		
		split = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		topPanel.add(split);
		
		topPanel.add(split, BorderLayout.CENTER);
		split.setRightComponent(sandbox);
		split.setLeftComponent(control);
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Radioactive Decay Simulation");	
		DecayDemo demo = new DecayDemo();

		frame.getContentPane().add(demo.topPanel);

		frame.setSize(800, 800);
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
		/* time interval happend */
		if (e.getActionCommand().equals("tick"))
			this.sandbox.tick(e);
		
		/* reset atoms */
		if (e.getActionCommand().equals("reset")) {
			timer.stop();
			sandbox = new AtomSandbox();
			split.setRightComponent(sandbox);
		}

		/* start simulator */
		if (e.getActionCommand().equals("start")) {
			timer.start();
		}
			
	}
}


