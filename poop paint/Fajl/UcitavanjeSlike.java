package poop_2015_predrok.Fajl;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import poop_2015_predrok.*;



public class UcitavanjeSlike implements ActionListener{

	
	private JFrame prozor;
	private Scanner x;
	private HashMap<String, Citac> hmap = new HashMap<String, Citac>();
	
	
	public UcitavanjeSlike(JFrame prozor){
		this.prozor = prozor;
		postaviHashMapu();
	}
	
	private void postaviHashMapu()
	{
		hmap.put("Pravougaonik", citajPravougaonik);
		hmap.put("Linija", citajLiniju);
		hmap.put("IzlomljenaLinija", citajIzlomljenuLiniju);
	}
	
	public void actionPerformed(ActionEvent e)
	{
    	try
    	{
    		JFileChooser chooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    		chooser.setFileFilter(filter);	
    		int option = chooser.showOpenDialog(prozor);
    		
    		
    	    if (option == JFileChooser.APPROVE_OPTION)
    	    {
    	    	Program.kolekcija.clear();
    	    	Program.glavnaPloca.repaint();
    	    	File file = chooser.getSelectedFile();
    	    	
    	    	Program.putanjaDoFajla = file.getAbsolutePath();
    	    	
    	    	System.out.println(Program.putanjaDoFajla);
    	    	System.out.println(file.getName());
    	    	
  
    	    	
    	    	
    	    	x = new Scanner(file);
    	    	//x = new Scanner(new File(file.getName()));

    	    		
    	    	
    	    	int brojFigura = x.nextInt();
    	    	
    	    	System.out.println(brojFigura);
    	    	for(int i=0; i<brojFigura; i++)
    	    	{
    	    		String str = x.next();
    	    		Citac citac = hmap.get(str);
    	    		
    	    		Figura figura = citac.citaj();
    	    		
    	    		Program.kolekcija.add(figura);
    	    	} 
    	    	
    	    	if(Program.glavnaPloca.kolekcija() != Program.kolekcija)
    	    		System.out.println("ISTE");
    	    	
    	    	Program.glavnaPloca.repaint();
    	    	
    	     }    	     
    	}
    	catch(FileNotFoundException ex) { System.out.println("KRAJ_KRAJ"); }
    	catch(Exception ex) { System.out.println("not found"); ex.printStackTrace();}
   	}
	
	interface Citac {
	    Figura citaj();
	}
	
	
	private Citac citajPravougaonik = new Citac(){ 
		public Figura citaj()
		{
			int debljina = x.nextInt();		
			int crvena = x.nextInt();
			int zelena = x.nextInt();
			int plava = x.nextInt();
			
			Pravougaonik figura = new Pravougaonik(new Color(crvena, zelena, plava));
			
			int x1 = x.nextInt();
			int y1 = x.nextInt();
			int x2 = x.nextInt();
			int y2 = x.nextInt();
			figura.postaviPocetnuTacku(new Point(x1, y1));
			figura.postaviKrajnjuTacku(new Point(x2, y2));
			figura.postaviDebljinu(debljina);
			
			return figura;
			
		}
	};
	
	private Citac citajLiniju = new Citac(){ 
		public Figura citaj()
		{
			int debljina = x.nextInt();		
			int crvena = x.nextInt();
			int zelena = x.nextInt();
			int plava = x.nextInt();
			
			Linija figura = new Linija(new Color(crvena, zelena, plava));
			
			int x1 = x.nextInt();
			int y1 = x.nextInt();
			int x2 = x.nextInt();
			int y2 = x.nextInt();
			figura.postaviPocetnuTacku(new Point(x1, y1));
			figura.postaviKrajnjuTacku(new Point(x2, y2));
			figura.postaviDebljinu(debljina);
			
			return figura;
		}
	};
	
	private Citac citajIzlomljenuLiniju = new Citac(){ 
		public Figura citaj()
		{
			int debljina = x.nextInt();		
			int crvena = x.nextInt();
			int zelena = x.nextInt();
			int plava = x.nextInt();
			
			IzlomljenaLinija figura = new IzlomljenaLinija(new Color(crvena, zelena, plava));
			
			int x1 = x.nextInt();
			int y1 = x.nextInt();
			int x2 = x.nextInt();
			int y2 = x.nextInt();
			figura.postaviPocetnuTacku(new Point(x1, y1));
			figura.postaviKrajnjuTacku(new Point(x2, y2));
			figura.postaviDebljinu(debljina);
						
			
			int brojTacaka = x.nextInt();
			for(int i=0; i<brojTacaka; i++)
			{
				x1 = x.nextInt();
				y1 = x.nextInt();
				figura.dodaj(new Point(x1, y1));
			}
			
			return figura;
		}
	};
	
}
