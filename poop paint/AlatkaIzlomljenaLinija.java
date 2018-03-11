package poop_2015_predrok;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;

import poop_2015_predrok.undoRedo.CrtanjeFigure;

public class AlatkaIzlomljenaLinija extends Alatka{

	private boolean spremnaFigura = false;
	public AlatkaIzlomljenaLinija(ArrayList<Figura> kol, JComponent komp) {
		super(kol, komp);
	}

	@Override
	public void klikMisa(MouseEvent e, Color boja) {

		if(spremnaFigura == false)
		{
			spremnaFigura = true;
			figura = new IzlomljenaLinija(boja);
			figura.postaviPocetnuTacku(e.getPoint());
			figura.postaviKrajnjuTacku(e.getPoint());
			kolekcija.add(figura);
			
			Program.undoList.add(new CrtanjeFigure(figura));
			Program.redoList.clear();
			
			komponenta.repaint();
		}
		
		Point tacka = e.getPoint();
		
		
		((IzlomljenaLinija)figura).lista.add(tacka);
			
		if(figura.pocetnaTacka.x > tacka.x) figura.pocetnaTacka.x = tacka.x;
		if(figura.krajnjaTacka.x < tacka.x) figura.krajnjaTacka.x = tacka.x;
		
		if(figura.pocetnaTacka.y > tacka.y) figura.pocetnaTacka.y = tacka.y;
		if(figura.krajnjaTacka.y < tacka.y) figura.krajnjaTacka.y = tacka.y;
		
		if(((IzlomljenaLinija)figura).lista.size() >= 2)
		{
			int duzinaListe = ((IzlomljenaLinija)figura).lista.size();
			Point t1 = ((IzlomljenaLinija)figura).lista.get(duzinaListe - 2);
			int xOsa = Math.abs(t1.x - tacka.x);
			int yOsa = Math.abs(t1.y - tacka.y);
			Program.postaviDimenzije(xOsa, yOsa);
		}
				
		
		komponenta.repaint();
		
		if(e.getClickCount() == 2)	
		{
			spremnaFigura = false;
			krajCrtanja();
		}
			
	}
	
	public void krajCrtanja() { }
	public void krajAktivnosti(MouseEvent e){ }
	public void pomeranjeMisa(MouseEvent e)
	{
		if(figura == null) return;
		komponenta.repaint();
	}
	


}
