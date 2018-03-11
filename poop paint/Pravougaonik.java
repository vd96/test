package poop_2015_predrok;

import java.awt.*;


public class Pravougaonik extends Figura{
	
	public Pravougaonik(Color boja) {
		super(boja);
	}

	public void crtaj(Graphics2D g){
		g.setPaint(boja);
		
		int x = (pocetnaTacka.x < krajnjaTacka.x) ? pocetnaTacka.x : krajnjaTacka.x; 
		int y = (pocetnaTacka.y < krajnjaTacka.y) ? pocetnaTacka.y : krajnjaTacka.y; 
		
		g.setStroke(new BasicStroke(debljinaLinije));
		//g.drawRect(x, y, sirina(), visina());
		g.fillRect(x, y, sirina(), visina());
	}
	
	public Pravougaonik kopija()
	{
		Pravougaonik novi =  new Pravougaonik(boja);
		novi.pocetnaTacka = pocetnaTacka;
		novi.krajnjaTacka = krajnjaTacka;
		novi.debljinaLinije = debljinaLinije;
		
		return novi;
	}

	public String toString(){
		return "Pravougaonik";
	}
}
