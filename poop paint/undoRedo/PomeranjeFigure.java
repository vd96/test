package poop_2015_predrok.undoRedo;

import poop_2015_predrok.Figura;
import poop_2015_predrok.Program;

public class PomeranjeFigure extends UndoRedoAkcija{

	private Figura staraFigura, novaFigura;
	
	public PomeranjeFigure(Figura staraFigura, Figura novaFigura){
		this.staraFigura = staraFigura;
		this.novaFigura = novaFigura;
	}
	
	public void radnjaUndo(){
	
		Figura figuraPrePromene = novaFigura.kopija();		
		novaFigura.promeni(staraFigura);
		
		
		Program.redoList.add(new PomeranjeFigure(figuraPrePromene, novaFigura));
		Program.glavnaPloca.repaint();
	}
	
	public void radnjaRedo(){
		Figura figuraPrePromene = novaFigura.kopija();		
		novaFigura.promeni(staraFigura);
		
		
		Program.undoList.add(new PomeranjeFigure(figuraPrePromene, novaFigura));
		Program.glavnaPloca.repaint();
	}
}
