package poop_2015_predrok;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JComponent;

import poop_2015_predrok.undoRedo.CrtanjeFigure;
import java.awt.*;

public abstract class Alatka {

	protected Figura figura;
	protected ArrayList<Figura> kolekcija;
	protected JComponent komponenta;
	
	public Alatka(ArrayList<Figura> kol, JComponent komp){
		kolekcija = kol;
		komponenta = komp;
	}
	
	public abstract void klikMisa(MouseEvent e, Color boja);

	public void pomeranjeMisa(MouseEvent e){
		if(figura == null) return;
		figura.postaviKrajnjuTacku(e.getPoint());
		Program.postaviDimenzije(figura.sirina(), figura.visina());
		komponenta.repaint();
	}
	
	public void krajAktivnosti(MouseEvent e){
		
		Program.undoList.add(new CrtanjeFigure(figura));
		Program.redoList.clear();
	}
	
	public void postaviBoju(Color boja){
		if(figura != null) figura.postaviBoju(boja);
		komponenta.repaint();
	}
	
	public void postaviDebljinu(int deb){
		if(figura != null) figura.postaviDebljinu(deb);
		komponenta.repaint();
	}


}
