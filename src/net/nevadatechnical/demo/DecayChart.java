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
	private int xoffset = 0;
	private int yoffset = 5;
	
	public DecayChart(int nAtoms, Dimension dim) {
		xpos = 0;
		ypos = 0;
		this.xscale = dim.width;
		this.yscale = dim.height;
		this.numerator = nAtoms;
		this.denominator = nAtoms;

		dim.setSize(new Dimension(dim.width + xoffset, dim.height + yoffset));
		this.setPreferredSize(dim);
		
	    setBorder(new TitledBorder(new LineBorder(Color.black, 1),
	        "Exponential Decay Curve"));
	    setBackground(Color.gray);
	    reset(nAtoms);

	}
	public void renderOffScreen(final Graphics g) {
		int x1, y1, x2, y2, xstride, ystride;
		xstride = xscale / 10;
		ystride = yscale / 10;

		g.setColor(Color.blue);

		for (x1 = xstride; x1 < xscale; x1 += xstride) {
			x2 = x1;
			y1 = yscale - 5;
			y2 = yscale;
			g.drawLine(x1, y1, x2, y2);
		}
		
		g.setColor(Color.green);
		for (y1 = ystride; y1 < yscale; y1 += ystride) {
			x1 = 5;
			x2 = 15;
			y2 = y1;
			g.drawLine(x1, y1, x2, y2);
		}

		g.setColor(Color.red);
		g.fillOval (xpos + xoffset, ypos + yoffset, 5, 5);
		}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (offScreenImage == null) {
			offScreenImage = createImage(xscale + xoffset, yscale + yoffset);
			osg = offScreenImage.getGraphics();
		}

		
		if ((xpos < xscale) && (ypos > 0) && (osg != null))
			renderOffScreen(osg);
		
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void tick(ActionEvent e) {
		if (xpos < (xscale - 1)) {
			repaint();
			xpos++;
			System.out.println("tick x: " + xpos + " y: " + ypos);
		}
	}

	public void update(int nAtoms) {
		numerator = nAtoms;
		double fraction = ((double)numerator / (double)denominator);

		ypos = yscale - (int) (fraction * yscale);

	}

	public void reset(int nAtoms) {
		xpos = 0;

		numerator = nAtoms;
		denominator = nAtoms;
		
		update(nAtoms);
		offScreenImage = null;
		repaint();
	}
}
