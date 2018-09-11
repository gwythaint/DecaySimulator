package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DecayChart extends JPanel {
	int xpos, ypos;
	int xscale, yscale;
	private Image offScreenImage = null;
	private Graphics osg = null;
	private int denominator;
	private int numerator;
	private int xoffset = 10;
	private int yoffset = 10;
	
	public DecayChart(int xscale, int yscale, int nAtoms) {
		xpos = 0;
		ypos = 0;
		this.xscale = xscale;
		this.yscale = yscale;
		this.numerator = nAtoms;
		this.denominator = nAtoms;
		this.setPreferredSize(new Dimension(xscale + 20, yscale + 20));
		
	    setBorder(new TitledBorder(new LineBorder(Color.black, 1),
	        "Exponential Decay Curve"));
	    setBackground(Color.green);

	}
	public void renderOffScreen(final Graphics g) {
        g.setColor(Color.red);
		g.fillOval (xpos + xoffset, ypos + yoffset, 5, 5);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (offScreenImage == null) {
			offScreenImage = this.createImage(xscale, yscale); 
		}
		osg = offScreenImage.getGraphics();

		
		if ((xpos < xscale) && (ypos > 0) && (osg != null))
			renderOffScreen(osg);
		
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void tick(ActionEvent e) {
		xpos++;
		repaint();
		System.out.println("x: " + xpos + " y: " + ypos);
	}

	public void update(int nAtoms) {
		numerator = nAtoms;
		ypos = yscale - (int) (((double)numerator / (double)denominator) * yscale);
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		xpos = 0;
		ypos = 0;
	}
}
