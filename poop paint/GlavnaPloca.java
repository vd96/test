package poop_2015_predrok;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;

public class GlavnaPloca extends JPanel implements MouseMotionListener, MouseListener{
	
	private ArrayList<Figura> kolekcija ;
	private Color boja = Color.black;
	private int debljinaLinije = 3;
	private Alatka alatka;
	
	
	public GlavnaPloca(ArrayList<Figura> kol){
		kolekcija = kol;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	
	public void mouseDragged(MouseEvent e) {
		Program.postaviKoordinate(e.getX(), e.getY());
		if(alatka != null) alatka.pomeranjeMisa(e);
	}

	public void mousePressed(MouseEvent e) {
		if(alatka != null) alatka.klikMisa(e, boja);
	}
	

	public void mouseReleased(MouseEvent e) {
		if(alatka != null) alatka.krajAktivnosti(e);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		Program.postaviKoordinate(e.getX(), e.getY());
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	
	
	public void postaviAlatku(Alatka a){
		alatka = a;
	}
	
	public void postaviBoju(Color b){
		boja = b;
		if(alatka != null) alatka.postaviBoju(b);
	}
	
	public void postaviDebljinu(int d){
		debljinaLinije = d;
		if(alatka != null) alatka.postaviDebljinu(d);
	}
	
	public Color boja() { return boja; }
	public int debljina() { return debljinaLinije; }
	public ArrayList<Figura> kolekcija() { return kolekcija; }
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		
		g2D.setColor(Color.WHITE);
		g2D.fillRect(0, 0, getWidth(), getHeight());
		
		for(Figura f : kolekcija)
			if(f != null) f.crtaj(g2D);
		
	}

}
