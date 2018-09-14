package net.nevadatechnical.demo;

import java.util.EventObject;

public class ModelEvent extends EventObject {

	public String name;
	public int value;

	public ModelEvent(Object source, String name, int value) {
		super(source);
		this.name = name;
		this.value = value;
	}

}
