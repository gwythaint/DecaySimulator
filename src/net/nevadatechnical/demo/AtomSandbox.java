package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class AtomSandbox extends JPanel {
	private Atom atomTable[][];

	public AtomSandbox () {
		int i,j;
		this.atomTable = new Atom[50][50];

		/* generate a 2D array of atoms */
		for (i = 0; i < 50; i++) {
			for ( j = 0; j < 50; j++) {
				this.atomTable[i][j] = new Atom(Color.blue, i, j);
			}
		}
	}

	/* paint a 50x50 grid of atoms */
	public void paint(Graphics g){
		int i,j;
		
		for (i = 0; i < 50; i++) {
			for ( j = 0; j < 50; j++) {
				atomTable[i][j].draw(g);
			}
		}
	}

	public void tick(ActionEvent e) {
		int i,j;
		
		for (i = 0; i < 50; i++) {
			for ( j = 0; j < 50; j++) {
					Atom atom = this.atomTable[i][j];
					if (!atom.decayed)
						if(atom.decay())
							repaint();
				}
			}

	}

}
