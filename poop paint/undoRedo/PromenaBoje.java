package poop_2015_predrok.undoRedo;

import java.awt.Color;
import poop_2015_predrok.*;

public class PromenaBoje extends UndoRedoAkcija {

	
	private Color boja;

	
	public PromenaBoje(Color boja){
		this.boja = boja;
	}
	
	public void radnjaUndo() {
		Program.redoList.add(new PromenaBoje(Program.glavnaPloca.boja()));
		Program.glavnaPloca.postaviBoju(boja);
	}


	public void radnjaRedo() {
		Program.undoList.add(new PromenaBoje(Program.glavnaPloca.boja()));
		Program.glavnaPloca.postaviBoju(boja);
	}
}
