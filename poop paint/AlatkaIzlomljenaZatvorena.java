package poop_2015_predrok;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

public class AlatkaIzlomljenaZatvorena extends AlatkaIzlomljenaLinija{

	public AlatkaIzlomljenaZatvorena(ArrayList<Figura> kol, JComponent komp) {
		super(kol, komp);
	}

	public void krajCrtanja()
	{
		Point tacka = ((IzlomljenaLinija)figura).lista.get(0);
		Point kopija = new Point();
		kopija.x = tacka.x;
		kopija.y = tacka.y;
		((IzlomljenaLinija)figura).lista.add(kopija);

	}
}
