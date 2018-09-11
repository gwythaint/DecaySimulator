package net.nevadatechnical.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class AtomSandbox extends JPanel {
	private static final int GRIDSIZE = 50;
	private Atom atomTable[][];
	public int nAtoms, nRow, nCol;
	private int nAtomsStart;

	public AtomSandbox(int nAtoms) {
		nAtomsStart = nAtoms;
		setPreferredSize(new Dimension(500,500));
		reset();
	}

	public void paintComponent(Graphics g){
		int i,j;
		

		for (i = 0; i < nRow; i++) {
			for ( j = 0; j < nCol; j++) {
				atomTable[i][j].draw(g);
			}
		}
	}

	public void tick(ActionEvent e) {
		int i,j;
		
		for (i = 0; i < nRow; i++) {
			for ( j = 0; j < nCol; j++) {
					Atom atom = this.atomTable[i][j];
					if (!atom.decayed)
						if(atom.decay()) {
							nAtoms--;
							repaint();
						}
				}
			}

	}

	public void reset() {
		int i,j;
		nAtoms = nAtomsStart;

		nRow = (int) Math.sqrt(nAtoms);
		nCol = nRow;
		atomTable = new Atom[nCol][nRow];

		/* generate a 2D array of atoms */
		for (i = 0; i < nRow; i++) {
			for ( j = 0; j < nCol; j++) {
				atomTable[i][j] = new Atom(Color.blue, i, j);
			}
		}
		repaint();
	}

}
