package net.nevadatechnical.demo;

import java.util.EventListener;
import java.util.EventObject;

public interface SimulatorListener extends EventListener {
	void modelChange(ModelEvent event);
}
