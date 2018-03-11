package poop_2015_predrok.undoRedo;

import poop_2015_predrok.*;

public class CrtanjeFigure extends UndoRedoAkcija{

	private Figura figura;
	
	public CrtanjeFigure(Figura figura){
		this.figura = figura;
	}

	public void radnjaUndo() {	
		Program.kolekcija.remove(figura);
		Program.redoList.add(new CrtanjeFigure(figura));	
		Program.glavnaPloca.repaint();
	}
	
	public void radnjaRedo(){
		Program.kolekcija.add(figura);
		Program.undoList.add(new CrtanjeFigure(figura));
		Program.glavnaPloca.repaint();
	}



}
