package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Atom {
	Color color;
	int halflife;
	int width;
	int height;
	int xpos;
	int ypos;
	public Boolean decayed;
	private Double lambda;
	static Random rng = new Random();

	public Atom(Color color, int i, int j) {
		this.color = color;
		this.setWidth(1);
		this.setHeight(1);
		this.setXpos(i);
		this.setYpos(j);
		this.setHalflife(100);
		
		/* synchronize half life and decay constant */
		this.lambda = 0.693 / this.getHalflife();
		this.decayed = false;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getHalflife() {
		return halflife;
	}

	public void setHalflife(int halflife) {
		this.halflife = halflife * 10; // in terms of milliseconds used in timer
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width * 10;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height * 10;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = (xpos * 10 + 1);
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = (ypos * 10 + 1);
	}

	public void draw(Graphics g) {
			g.setColor(color);
			g.fillOval (xpos, ypos, width, height);
	};

	public Boolean decay() {
		Double chance = rng.nextDouble();
		
		/* chance of decaying when chance is less than decay constant */
		if (chance < this.lambda) {
			this.decayed = true;
			color = Color.white;
		} else {
		}
		return this.decayed;
	}

}
