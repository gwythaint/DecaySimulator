package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel implements ChangeListener {
	private JButton reset;
	private JButton start;
	private SpinnerNumberModel nSpinner;
	private JSpinner nAtomSelect;
	private String simulatorCommand;

	public ControlPanel (DecayDemo decayDemo) {
		this.setLayout(new FlowLayout());
		
		reset = new JButton("Reset");
		start = new JButton("Start");
		nSpinner = new SpinnerNumberModel(100, 10, 10000, 100);
		nAtomSelect = new JSpinner(nSpinner);

		reset.setActionCommand("reset");
		start.setActionCommand("start");
		this.setSimulatorCommand("sim");
	
		reset.addActionListener(decayDemo);
		start.addActionListener(decayDemo);
		this.addSimulatorListener(decayDemo);
		
		this.add(reset);
		this.add(start);
		this.add(nAtomSelect);
		
		nAtomSelect.addChangeListener(this);

//		this.addLabeledSpinner(this, "Number of Atoms", nSpinner);
	}
	
	public String getSimulatorCommand() {
		return this.simulatorCommand;
	}
	
	public void setSimulatorCommand(String simulatorCommand) {
		this.simulatorCommand = simulatorCommand;
	}

	public void labelUpdate(String label){
		start.setText(label);
	}
	
    static protected JSpinner addLabeledSpinner(Container c,
            									String label,
            									SpinnerModel model) {
    	JLabel l = new JLabel(label);
    	c.add(l);

    	JSpinner spinner = new JSpinner(model);
    	l.setLabelFor(spinner);
    	c.add(spinner);

    	return spinner;
    }

	@Override
	public void stateChanged(ChangeEvent e) {
		long when;
		when  = System.currentTimeMillis();

		JSpinner spinner = (JSpinner) e.getSource();
		int nAtoms = (int) spinner.getValue();

		System.out.println("New nAtoms " + nAtoms);
		fireModelChange(nAtoms);
	}
	
	public void removeSimulatorListener(SimulatorListener listener) {
		listenerList.remove(SimulatorListener.class, listener);
	}
	 
	public void addSimulatorListener(SimulatorListener listener) {
		listenerList.add(SimulatorListener.class, listener);
	 }

	protected void fireModelChange(int atoms) {
		ModelEvent event = new ModelEvent(this, "atoms", atoms);
		SimulatorListener[] listeners = getListeners(SimulatorListener.class);
		
		for (int i = 0; i < listeners.length; i++)
			listeners[i].modelChange(event);

	}

}
