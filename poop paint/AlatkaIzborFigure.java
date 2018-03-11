package poop_2015_predrok;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;

import poop_2015_predrok.undoRedo.PomeranjeFigure;

public class AlatkaIzborFigure extends Alatka{


	private ArrayList<Figura> figure;	
	private boolean oznacena;
	private int indeksOznaceneFigure;
	private Point trenutnaTacka;
	private boolean pomeraSe;
	
	
	public AlatkaIzborFigure(ArrayList<Figura> kol, JComponent komp) {
		super(kol, komp);
	}
	
	
	@Override 
	public void klikMisa(MouseEvent e, Color boja)
	{
		figureKojeSadrzeTacku(e);
		
		if(oznacena == true)
		{
			int indeksFigure = indeksOznaceneFigure -1;
			if(indeksFigure == -1) indeksFigure = figure.size() - 1;
			
			indeksOznaceneFigure = indeksFigure;
			Figura.oznacenaFigura = figure.get(indeksOznaceneFigure);
		}
		else if (figure.size() > 0)
		{
			indeksOznaceneFigure = figure.size() - 1;
			Figura.oznacenaFigura = figure.get(indeksOznaceneFigure);
		}
		else
			return;

				
		figura = figure.get(indeksOznaceneFigure);
		trenutnaTacka = e.getPoint();
		
		Program.postaviDimenzije(figura.sirina(), figura.visina());

		
	}
	

	public void pomeranjeMisa(MouseEvent e){ 
		

		figureKojeSadrzeTacku(e);
		
		if(oznacena == true)
		{
			
			if(pomeraSe == false)
			{
				Figura staraFigura = figura.kopija();
				Program.undoList.add(new PomeranjeFigure(staraFigura, figura));
				pomeraSe = true;
			}
			
			
			int dx = (int) (e.getX() - trenutnaTacka.getX());
			int dy = (int) (e.getY() - trenutnaTacka.getY());
		
			int novoPocetnoX = (int) (dx + figura.pocetnaTacka.getX());
			int novoPocetnoY = (int) (dy + figura.pocetnaTacka.getY());
			int novoKrajnjeX = (int) (dx + figura.krajnjaTacka.getX());
			int novoKrajnjeY = (int) (dy + figura.krajnjaTacka.getY());
			
			figura.postaviPocetnuTacku(new Point(novoPocetnoX, novoPocetnoY));
			figura.postaviKrajnjuTacku(new Point(novoKrajnjeX, novoKrajnjeY));
			
			if(figura instanceof IzlomljenaLinija){
				((IzlomljenaLinija) figura).azurirajKoordinate(dx, dy);
			}
			
			trenutnaTacka = e.getPoint();
			komponenta.repaint();
		}

	}
	
	
	public void krajAktivnosti(MouseEvent e){ 
		pomeraSe = false;
	}
	
	private void figureKojeSadrzeTacku(MouseEvent e){
		Point tacka = new Point(e.getX(), e.getY());
		oznacena = false;
		indeksOznaceneFigure = 0;
		
		figure = new ArrayList<Figura>();
		for(int i=0; i<kolekcija.size(); i++)
		{
			Figura f = kolekcija.get(i);
			
		
			if(f.sadrzi(tacka))
			{
				figure.add(f);
				if(f.equals(Figura.oznacenaFigura))
				{
					oznacena = true;
					indeksOznaceneFigure = figure.size()-1;
				}					
			}
		}
	}
	
	
}