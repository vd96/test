package poop_2015_predrok.undoRedo;
import poop_2015_predrok.Program;

public class PromenaDebljineLinije extends UndoRedoAkcija{

	private int debljina;
	
	public PromenaDebljineLinije(int debljina){
		this.debljina = debljina;
	}
	
	public void radnjaUndo() {
		
		Program.redoList.add(new PromenaDebljineLinije(Program.glavnaPloca.debljina()));
		Program.glavnaPloca.postaviDebljinu(debljina);
	}

	
	public void radnjaRedo() {
		Program.undoList.add(new PromenaDebljineLinije(Program.glavnaPloca.debljina()));
		Program.glavnaPloca.postaviDebljinu(debljina);
		
	}

	
}
