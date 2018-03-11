package poop_2015_predrok.Fajl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import poop_2015_predrok.Program;



public class SnimanjeNoveSlike extends SnimanjeSlike {

	private JFrame prozor;	
	
	public SnimanjeNoveSlike(JFrame prozor){
		this.prozor = prozor;
	}
	

	public void actionPerformed(ActionEvent e)
	{
    	try
    	{
    		JFileChooser chooser = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    		chooser.setFileFilter(filter);	
    		int option = chooser.showSaveDialog(prozor);

    		
    	    if (option == JFileChooser.APPROVE_OPTION)
    	    {
    	    	File file = chooser.getSelectedFile();
    	    	Program.putanjaDoFajla = file.getAbsolutePath();
    	    	snimi();
    	     }    	     
    	}
    	catch(Exception ex) { }
   	}
}
