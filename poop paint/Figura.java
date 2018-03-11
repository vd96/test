package poop_2015_predrok;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Figura {

	protected Point pocetnaTacka = new Point(1200, 500);
	protected Point krajnjaTacka = new Point(0, 0);
	protected Color boja;
	protected int debljinaLinije = Program.slider.getValue()  * 3;
	
	protected static Figura oznacenaFigura;
	
		
	public Figura(Color boja)
	{
		this.boja = boja;
	}
	
	public void postaviPocetnuTacku(Point pocetna){
		pocetnaTacka = pocetna;
	}
	
	public void postaviKrajnjuTacku(Point krajnja){
		krajnjaTacka = krajnja;
	}
	
	public void postaviBoju(Color b){
		boja = b;
	}
	
	public void postaviDebljinu(int d){
		debljinaLinije = d;
	}
	
	public int sirina(){
		return Math.abs(pocetnaTacka.x - krajnjaTacka.x);
	}
	
	public int visina(){
		return Math.abs(pocetnaTacka.y - krajnjaTacka.y);
	}
	
	public Color boja() { 
		return boja;
	}
	
	public Point pocetnaTacka(){
		return pocetnaTacka;
	}
	
	public Point krajnjaTacka(){
		return krajnjaTacka;
	}
	
	public int debljina(){
		return debljinaLinije;
	}
	
	public abstract void crtaj(Graphics2D g);
	public abstract Figura kopija();
	
	public void promeni(Figura f)
	{
		pocetnaTacka = f.pocetnaTacka;
		krajnjaTacka = f.krajnjaTacka;
		debljinaLinije = f.debljinaLinije;
		boja = f.boja;
	}
	
	public boolean sadrzi(Point tacka)
	{
		int x = (pocetnaTacka.x < krajnjaTacka.x) ? pocetnaTacka.x : krajnjaTacka.x; 
		int y = (pocetnaTacka.y < krajnjaTacka.y) ? pocetnaTacka.y : krajnjaTacka.y;
		
		Rectangle2D pravougaonik = new Rectangle2D.Float(x, y, sirina(), visina());
		
		return pravougaonik.contains((Point2D)tacka);
	}
}

