package poop_2015_predrok;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;

public class AlatkaPravougaonik extends Alatka {

	public AlatkaPravougaonik(ArrayList<Figura> kol, JComponent komp) {
		super(kol, komp);
	}

	public void klikMisa(MouseEvent e, Color boja){
		figura = new Pravougaonik(boja);
		figura.postaviPocetnuTacku(e.getPoint());
		figura.postaviKrajnjuTacku(e.getPoint());
		
		kolekcija.add(figura);
	}
}
