package poop_2015_predrok;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;


public class DugmeAlatke extends JButton{
	

	public DugmeAlatke (String putanja)
	{
		this.setBackground(Color.BLACK);
		
		 try
		 {
		    Image img = ImageIO.read(getClass().getResource(putanja));
		    setIcon(new ImageIcon(img));
		  } 
		 catch (Exception ex) { }
	}
	

}
