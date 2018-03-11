package poop_2015_predrok;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class IzlomljenaLinija extends Figura {

	
	protected ArrayList<Point> lista = new ArrayList<Point>();
	
	
	public IzlomljenaLinija(Color boja) {
		super(boja);
	}


	@Override
	public void crtaj(Graphics2D g) {
		for(int i=0; i<lista.size()-1; i++){
			Point p1 = lista.get(i);
			Point p2 = lista.get(i+1);
			
			g.setStroke(new BasicStroke(debljinaLinije));
			g.setPaint(boja);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
		
	}
	
	public void azurirajKoordinate(int dx, int dy)
	{
		for(int i=0; i < lista.size(); i++)
		{
			lista.get(i).x+=dx;
			lista.get(i).y+=dy;
		}
	}
	
	public Figura kopija()
	{
		IzlomljenaLinija novaLinija = new IzlomljenaLinija(boja);
		novaLinija.pocetnaTacka = pocetnaTacka;
		novaLinija.krajnjaTacka = krajnjaTacka;
		novaLinija.debljinaLinije = debljinaLinije;
		
		for(int i=0; i<lista.size(); i++){
			Point t = lista.get(i);
			Point novaTacka  = new Point(t.x, t.y);
			novaLinija.lista.add(novaTacka);
		}
		
		return novaLinija;
	}
	
	public void promeni(Figura figura)
	{
		super.promeni(figura);
		
		IzlomljenaLinija linija = (IzlomljenaLinija)figura;
		for(int i=0; i<lista.size(); i++){
			lista.get(i).x = linija.lista.get(i).x;
			lista.get(i).y = linija.lista.get(i).y;
		}
	}
	
	public int brojTacaka(){
		return lista.size();
	}
	
	public Point lista(int i){
		return lista.get(i);
	}
	
	public void dodaj(Point tacka){
		lista.add(tacka);
	}
	
	public String toString(){
		return "IzlomljenaLinija";
	}
	
}
