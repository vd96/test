package poop_2015_predrok;
import java.awt.*;


public class Linija extends Figura{

	public Linija(Color boja) {
		super(boja);
	}

	@Override
	public void crtaj(Graphics2D g){		
		int x1 = pocetnaTacka.x;
		int x2 = krajnjaTacka.x;
		int y1 = pocetnaTacka.y;
		int y2 = krajnjaTacka.y;
		
		g.setStroke(new BasicStroke(debljinaLinije));
		g.setPaint(boja);
		g.drawLine(x1, y1, x2, y2);
	}
	
	public Linija kopija()
	{
		Linija novaLinija =  new Linija(boja);
		novaLinija.pocetnaTacka = pocetnaTacka;
		novaLinija.krajnjaTacka = krajnjaTacka;
		novaLinija.debljinaLinije = debljinaLinije;
		
		return novaLinija;
	}
	
	public String toString(){
		return "Linija";
	}
	
}
