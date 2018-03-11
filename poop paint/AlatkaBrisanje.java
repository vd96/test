package poop_2015_predrok;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;

import poop_2015_predrok.undoRedo.BrisanjeFigure;

public class AlatkaBrisanje extends Alatka{

	private ArrayList<Figura> figure;	

	
	public AlatkaBrisanje(ArrayList<Figura> kol, JComponent komp) {
		super(kol, komp);
	}

	@Override 
	public void klikMisa(MouseEvent e, Color boja)
	{
		Point tacka = new Point(e.getX(), e.getY());
		int indeksPoslednjeDodateFigure = 0;
		
		figure = new ArrayList<Figura>();
		for(int i=0; i<kolekcija.size(); i++)
		{
			Figura f = kolekcija.get(i);
			
			if(f.sadrzi(tacka))
			{
				figure.add(f);
				indeksPoslednjeDodateFigure = i;
				if(f == Figura.oznacenaFigura)
				{
					Figura.oznacenaFigura = null;
					
					Program.undoList.add(new BrisanjeFigure(kolekcija.remove(i), i));
					Program.redoList.clear();
					
					komponenta.repaint();
					return;
				}					
			}
		}
		
		if(figure.size() > 0)
		{
			Figura fig = figure.get(figure.size() - 1);
			kolekcija.remove(fig);
			
			Program.undoList.add(new BrisanjeFigure(fig, indeksPoslednjeDodateFigure));
			Program.redoList.clear();
			
			komponenta.repaint();
		}
	}
	
	public void krajAktivnosti(MouseEvent e){ }
	public void pomeranjeFigure(MouseEvent e){ }
	
}
