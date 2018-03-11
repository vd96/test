package poop_2015_predrok.Fajl;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

import poop_2015_predrok.*;


public class SnimanjeSlike implements ActionListener{

	private HashMap<String, Stampac> hmap = new HashMap<String, Stampac>();
	
	public SnimanjeSlike(){
		postaviHashMapu();
	}
	
	private void postaviHashMapu()
	{
		hmap.put("Pravougaonik", pisiPravougaonik);
		hmap.put("Linija", pisiLiniju);
		hmap.put("IzlomljenaLinija", pisiIzlomljenuLiniju);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
    	try
    	{
    		if(Program.putanjaDoFajla != "")
    			snimi(); 
    	}
    	catch(Exception ex) { }
   	}
	
	
	public void snimi() throws Exception
	{
		PrintWriter izlaz = new PrintWriter(Program.putanjaDoFajla, "UTF-8");

	    izlaz.println(Program.kolekcija.size() + "\n");
	    
	    for(Figura f : Program.kolekcija)
	    {
	    	Stampac stampac = hmap.get(f.toString());
	    	stampac.pisi(izlaz, f);
	    	izlaz.println();
	    }
	    	    
	    izlaz.close();
	}
	
	
	interface Stampac{
		void pisi(PrintWriter izlaz, Figura f);
	}
	
	private Stampac pisiLiniju = new Stampac(){ 
		public void pisi(PrintWriter izlaz, Figura f)
		{
			izlaz.println(f.toString());
			izlaz.println(f.debljina());
			izlaz.println(f.boja().getRed() + " " + f.boja().getGreen() + " " + f.boja().getBlue());		
			izlaz.print(f.pocetnaTacka().x + " " + f.pocetnaTacka().y+ " ");
			izlaz.println(f.krajnjaTacka().x + " " + f.krajnjaTacka().y);		
		}
	};
	
	private Stampac pisiPravougaonik = new Stampac(){ 
		public void pisi(PrintWriter izlaz, Figura f)
		{
			izlaz.println(f.toString());
			izlaz.println(f.debljina());
			izlaz.println(f.boja().getRed() + " " + f.boja().getGreen() + " " + f.boja().getBlue());		
			izlaz.print(f.pocetnaTacka().x + " " + f.pocetnaTacka().y+ " ");
			izlaz.println(f.krajnjaTacka().x + " " + f.krajnjaTacka().y);		
		}
	};
	
	private Stampac pisiIzlomljenuLiniju = new Stampac(){ 
		public void pisi(PrintWriter izlaz, Figura f)
		{
			izlaz.println(f.toString());
			izlaz.println(f.debljina());
			izlaz.println(f.boja().getRed() + " " + f.boja().getGreen() + " " + f.boja().getBlue());	
			izlaz.print(f.pocetnaTacka().x + " " + f.pocetnaTacka().y+ " ");
			izlaz.println(f.krajnjaTacka().x + " " + f.krajnjaTacka().y);
			
			int brojTacaka = ((IzlomljenaLinija)f).brojTacaka();
			izlaz.println(brojTacaka);
			for(int i=0; i<brojTacaka; i++)
			{
				Point tacka = (((IzlomljenaLinija)f).lista(i));
				izlaz.print(tacka.x + " " + tacka.y + " ");
			}
			izlaz.println();
		}
	};
	
}
