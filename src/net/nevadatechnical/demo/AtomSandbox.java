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

	public AtomSandbox () {
		int i,j;
		this.nAtoms = 2500;
		this.atomTable = new Atom[GRIDSIZE][GRIDSIZE];

		/* generate a 2D array of atoms */
		for (i = 0; i <GRIDSIZE; i++) {
			for ( j = 0; j < GRIDSIZE; j++) {
				this.atomTable[i][j] = new Atom(Color.blue, i, j);
			}
		}
		this.setPreferredSize(new Dimension(500,500));
		this.repaint();
	}

	public AtomSandbox(int nAtoms) {
		int i,j;
		this.nAtoms = nAtoms;
		nRow = (int) Math.sqrt(nAtoms);
		nCol = nRow;
		atomTable = new Atom[nCol][nRow];

		/* generate a 2D array of atoms */
		for (i = 0; i < nRow; i++) {
			for ( j = 0; j < nCol; j++) {
				atomTable[i][j] = new Atom(Color.blue, i, j);
			}
		}
		setPreferredSize(new Dimension(500,500));
		repaint();
	}

	public void paintComponent(Graphics g){
		int i,j;
		

		for (i = 0; i < nRow; i++) {
			for ( j = 0; j < nCol; j++) {
				atomTable[i][j].draw(g);
			}
		}
		this.setBackground(Color.red);
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

}
