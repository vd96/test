package poop_2015_predrok.undoRedo;

import poop_2015_predrok.*;

public class BrisanjeFigure extends UndoRedoAkcija{

	
	private Figura figura;
	private int pozicija;
	
	public BrisanjeFigure(Figura figura, int pozicija){
		this.figura = figura;
		this.pozicija = pozicija;
	}
	
	
	public void radnjaUndo() {
		Program.kolekcija.add(pozicija, figura);
		Program.redoList.add(new BrisanjeFigure(figura, pozicija));	
		Program.glavnaPloca.repaint();		
	}


	public void radnjaRedo() {
		Program.kolekcija.remove(figura);
		Program.undoList.add(new BrisanjeFigure(figura, pozicija));	
		Program.glavnaPloca.repaint();
		
	}

	
	
}
