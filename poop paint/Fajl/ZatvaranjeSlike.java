package poop_2015_predrok.Fajl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poop_2015_predrok.Program;

public class ZatvaranjeSlike implements ActionListener{

	public void actionPerformed(ActionEvent e)
	{
		new SnimanjeSlike().actionPerformed(e);
    	Program.kolekcija.clear();
    	Program.glavnaPloca.repaint();
    	Program.putanjaDoFajla = "";
   	}
		
}