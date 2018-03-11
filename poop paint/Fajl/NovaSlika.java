package poop_2015_predrok.Fajl;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import poop_2015_predrok.Program;


public class NovaSlika implements ActionListener{

	public void actionPerformed(ActionEvent e)
	{
    	Program.kolekcija.clear();
    	Program.glavnaPloca.repaint();
   	}
		
}