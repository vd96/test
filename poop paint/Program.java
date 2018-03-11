package poop_2015_predrok;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;

import poop_2015_predrok.Fajl.*;
import poop_2015_predrok.undoRedo.*;


public class Program {
	
	private static final int DUZINA = 1200;
	private static final int VISINA = 600;

	private static final int BROJ_DUGMADI = 6;
	
	
	public static ArrayList<UndoRedoAkcija> undoList = new ArrayList<UndoRedoAkcija>();
	public static ArrayList<UndoRedoAkcija> redoList = new ArrayList<UndoRedoAkcija>();
	
	private static JLabel koordinate = new JLabel("X: , Y: ");
	private static JLabel dimenzije_figure = new JLabel("sirina: visina: ");
	
	public static void postaviDimenzije(int s, int v){
		dimenzije_figure.setText("sirina: " + s + ", visina: " + v);
	}
	
	public static void brisiDimenzije(){
		dimenzije_figure.setText("");
	}
	
	public static void postaviKoordinate(int x, int y){
		koordinate.setText("X: " + x + ", Y: " + y);
	}
	
	public static ArrayList<Figura> kolekcija = new ArrayList<Figura>();
	public static GlavnaPloca glavnaPloca = new GlavnaPloca(kolekcija);
	

		
	private DugmeAlatke dugmeLinija = new DugmeAlatke("slike/linija.png");
	private DugmeAlatke dugmePravougaonik =  new DugmeAlatke("slike/pravougaonik.png");
	private DugmeAlatke dugmeIzborFigure = new DugmeAlatke("slike/biranje.png");
	private DugmeAlatke dugmeBrisanje = new DugmeAlatke("slike/brisanje.png");
	private DugmeAlatke dugmeIzlomljenaLinija = new DugmeAlatke("slike/izlomljena.png");
	private DugmeAlatke dugmeIzlomljenaZatvorena = new DugmeAlatke("slike/zatvorenaLinija.png");
	private DugmeAlatke prethodnoPritisnuto;


	
	
	private JButton trenutnaBoja = new JButton();
	private JButton redoAkcija = new JButton("Redo");
	private JButton undoAkcija = new JButton("Undo");
	private int prethodnaDebljina = 3;
	
	private JMenuBar trakaMenija = new JMenuBar();
	private JMenu meniFajl = new JMenu("File");
	private JMenu meniAutor = new JMenu("Help");
	
	private JMenuItem novaSlika = new JMenuItem("Kreiranje nove slike");
	private JMenuItem snimanjePostojeceSlike = new JMenuItem("Snimanje postojece slike");
	private JMenuItem snimanjeNoveSlike = new JMenuItem("Snimanje nove slike");
	private JMenuItem zatvaranjeTrenutneSlike = new JMenuItem("Zatvaranje trenutno otvorene slike");
	private JMenuItem zatvaranjePrograma = new JMenuItem("Zatvaranje progmrama");
	private JMenuItem imeAutora = new JMenuItem("Ime Autora");
	private JMenuItem ucitavanjeSlike = new JMenuItem("Ucitavanje slike");
	
	public static String putanjaDoFajla = "";
	
	
	private JPanel jug = new JPanel();
	private JPanel sever = new JPanel();
	private JPanel severIstok = new JPanel();
	private JPanel severZapad = new JPanel();
	private JFrame frame = new JFrame("PAINT");
	
	
	private JButton[] dugmeBoje = new JButton[16];
	private Color[] boje = new Color[]
			{
				Color.BLACK, Color.GRAY, new Color(120, 20, 20), Color.RED, new Color(255, 127, 39), Color.YELLOW, new Color(0, 200, 0) , Color.BLUE,
				Color.WHITE, Color.LIGHT_GRAY, new Color(185, 122, 87),  Color.PINK, new Color(255, 201, 14), new Color(163, 73, 164), new Color(100, 255, 30), new Color(0, 200, 255)
			};
	
	private HashMap<DugmeAlatke, Alatka> mapaAlatke = new HashMap<DugmeAlatke, Alatka>();
	private HashMap<JButton, Color> mapaBoje = new HashMap<JButton, Color> ();
	
	
	
	public static JSlider slider = new JSlider(JSlider.VERTICAL, 1, 5, 1);

	
	public Program()
	{
		
		frame.setBounds(new Rectangle(0, 0, DUZINA, VISINA));
		
		postaviHashMapu();
		postaviProzor();
		dodajOsl();
		postaviMeni();
		postaviRedoUndo();
			
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void postaviHashMapu()
	{
		mapaAlatke.put(dugmeLinija,new AlatkaLinija(kolekcija, glavnaPloca) );
		mapaAlatke.put(dugmePravougaonik, new AlatkaPravougaonik(kolekcija, glavnaPloca));
		mapaAlatke.put(dugmeIzborFigure, new AlatkaIzborFigure(kolekcija, glavnaPloca));
		mapaAlatke.put(dugmeBrisanje, new AlatkaBrisanje(kolekcija, glavnaPloca));
		mapaAlatke.put(dugmeIzlomljenaLinija, new AlatkaIzlomljenaLinija(kolekcija, glavnaPloca));
		mapaAlatke.put(dugmeIzlomljenaZatvorena, new AlatkaIzlomljenaZatvorena(kolekcija, glavnaPloca));
		
		for(int i=0; i<16; i++){
			dugmeBoje[i] = new JButton();
			mapaBoje.put(dugmeBoje[i], boje[i]);
			dugmeBoje[i].setBackground(boje[i]);
		}
	}
	
	private void postaviMeni()
	{
		
		meniFajl.add(novaSlika);
		meniFajl.add(ucitavanjeSlike);
		meniFajl.add(snimanjePostojeceSlike);
		meniFajl.add(snimanjeNoveSlike);
		meniFajl.add(zatvaranjeTrenutneSlike);
		meniFajl.add(zatvaranjePrograma);		
		meniAutor.add(imeAutora);	
		
		trakaMenija.add(meniFajl);
		trakaMenija.add(meniAutor);
		
		imeAutora.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog(frame, "Ime Autora");
				dialog.setBounds(200, 200, 200, 100);
				dialog.getContentPane().setBackground(Color.WHITE);
				dialog.setVisible(true);
				dialog.add(new Label("Danilo Vucinic"), BorderLayout.CENTER);
			}
		});
		
		novaSlika.addActionListener(new NovaSlika());
		ucitavanjeSlike.addActionListener(new UcitavanjeSlike(frame));
		snimanjeNoveSlike.addActionListener(new SnimanjeNoveSlike(frame));
		snimanjePostojeceSlike.addActionListener(new SnimanjeSlike());
		zatvaranjeTrenutneSlike.addActionListener(new ZatvaranjeSlike());
		
		zatvaranjePrograma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SnimanjeSlike().actionPerformed(e);
				frame.dispose();
			}
		});
	
		frame.setJMenuBar(trakaMenija);
	}
	
	
	private void postaviProzor()
	{
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());	
		
		postaviAlate();
		postaviStatusnuLiniju();		
			
		content.add(jug, BorderLayout.SOUTH);
		content.add(sever, BorderLayout.NORTH);
		content.add(glavnaPloca, BorderLayout.CENTER);
		
	}
	
	
	private void postaviAlate()
	{
		severZapad.setLayout(new GridLayout(1, BROJ_DUGMADI));
		severZapad.setPreferredSize(new Dimension(100, 100));
		
		severZapad.add(dugmeLinija);
		severZapad.add(dugmeIzlomljenaLinija);
		severZapad.add(dugmeIzlomljenaZatvorena);
		severZapad.add(dugmePravougaonik);
		severZapad.add(dugmeIzborFigure);
		severZapad.add(dugmeBrisanje);
		
		sever.setLayout(new GridLayout(1, 3));
		
		
		JPanel panelBoje = new JPanel();
		panelBoje.setLayout(new GridLayout(2, 8));
		panelBoje.setPreferredSize(new Dimension(100, 100));
		for(int i=0; i<16; i++)
			panelBoje.add(dugmeBoje[i]);

		
		JLabel trenutnaDebljina = new JLabel("debljina: 1");
		slider.setPreferredSize(new Dimension(20, 60));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
					trenutnaDebljina.setText("debljina: " + slider.getValue());		
					glavnaPloca.postaviDebljinu(slider.getValue()*3);
					
					if(prethodnaDebljina != slider.getValue()*3)
					{
						undoList.add(new PromenaDebljineLinije(prethodnaDebljina));
						redoList.clear();
					}
					
					
					prethodnaDebljina = slider.getValue()*3;
			}	
		});
		
		
		JPanel panelDebljina = new JPanel();
		panelDebljina.setLayout(new BorderLayout());
		panelDebljina.add(slider, BorderLayout.CENTER);
		panelDebljina.add(trenutnaDebljina, BorderLayout.SOUTH);
		
		

		severIstok.setLayout(new BorderLayout());
		severIstok.add(panelDebljina, BorderLayout.WEST);
		severIstok.add(panelBoje, BorderLayout.CENTER);
		
		sever.add(severZapad);
		sever.add(severIstok);
	}
	
	
	private void postaviStatusnuLiniju()
	{
		
		
		
		JPanel panelStatus = new JPanel();
		panelStatus.setLayout(new GridLayout(1, 3));
		panelStatus.add(trenutnaBoja);
		panelStatus.add(koordinate);
		panelStatus.add(dimenzije_figure);

		JPanel redoUndo = new JPanel();
		redoUndo.setLayout(new GridLayout(1, 2));
		redoUndo.add(redoAkcija);
		redoUndo.add(undoAkcija);
		
		koordinate.setPreferredSize(new Dimension(40, 20));
		dimenzije_figure.setPreferredSize(new Dimension(150, 20));
		
		jug.setLayout(new BorderLayout());
		jug.setPreferredSize(new Dimension(100, 30));
		jug.add(panelStatus, BorderLayout.WEST);
		jug.add(redoUndo, BorderLayout.EAST);
		
		trenutnaBoja.setBackground(Color.BLACK);
		
		
	}
	
	
	private void dodajOsl()
	{

		//Color c = JColorChooser.showDialog(obojenoDugme, "Chose Color", Color.black);

		for(int i=0; i<16; i++)
			dugmeBoje[i].addActionListener(osluskivacBoje);
		
		
		dugmeLinija.addActionListener(osluskivacAlatke);
		dugmePravougaonik.addActionListener(osluskivacAlatke);
		dugmeIzlomljenaLinija.addActionListener(osluskivacAlatke);
		dugmeIzborFigure.addActionListener(osluskivacAlatke);
		dugmeBrisanje.addActionListener(osluskivacAlatke);
		dugmeIzlomljenaZatvorena.addActionListener(osluskivacAlatke);
		
	}
	
	private ActionListener osluskivacAlatke  = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			Alatka alatka = mapaAlatke.get(e.getSource());			
			glavnaPloca.postaviAlatku(alatka);
			
			if(prethodnoPritisnuto != null)
			{
				prethodnoPritisnuto.setSelected(false);
				prethodnoPritisnuto.setBackground(Color.BLACK);
			}
				
			prethodnoPritisnuto = (DugmeAlatke)e.getSource();
			prethodnoPritisnuto.setBackground(Color.GREEN);
			
			
		}
	};
	
	private ActionListener osluskivacBoje = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			undoList.add(new PromenaBoje(trenutnaBoja.getBackground()));
			redoList.clear();
			
			Color novaBoja= mapaBoje.get(e.getSource());
			glavnaPloca.postaviBoju(novaBoja);
			trenutnaBoja.setBackground(novaBoja);
		}
	};
	
	
	private void postaviRedoUndo()
	{
		
		undoAkcija.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(undoList.size() == 0) return;
				UndoRedoAkcija akcija = undoList.remove(undoList.size() - 1);
				akcija.radnjaUndo();
				
			}
		});
		
		redoAkcija.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(redoList.size() == 0) return;
				UndoRedoAkcija akcija = redoList.remove(redoList.size() - 1);
				akcija.radnjaRedo();
			}
		});
	}
	
	
	public static void main(String[] args) {
		new Program();
	}

}
