import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Implement movement block to not go past each other.

// Staket
public class Staket extends JPanel implements ActionListener {
	// bg = background.
	public Image bg, char1Img, char2Img;
	public int char1X = 1720, char1Y = 900, char2X = 100, char2Y = 900;

 	// ki handles key inputs.
	public KeyInputHandler ki;

	// char1 is the first character, char2 is the second
	// character.
	public Character char1, char2;

	// timer handles graphic redrawing.
	public Timer timer = new Timer(20, this);
	public void actionPerformed(ActionEvent e) {
	    this.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawImage(bg, 0, 0, null);
		g.drawImage(char1.img, char1.x, char1.y, null);
		g.drawImage(char2.img, char2.x, char2.y, null);
	}

	public void run(DisplayMode dm) {
		loadPics();

		char1 = new Character(
			char2X,
			char2Y,
			char2Img,
			KeyEvent.VK_W,
			KeyEvent.VK_A,
			KeyEvent.VK_S,
			KeyEvent.VK_D
		);
		char2 = new Character(
			char1X,
			char1Y,
			char1Img,
			KeyEvent.VK_UP,
			KeyEvent.VK_LEFT,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_RIGHT
		);

		this.ki = new KeyInputHandler(
			dm,
			char1,
			char2
		);

		JFrame f = new JFrame();
		f.addKeyListener(ki);
		f.add(this);

		timer.start();
		(new Screen()).setFullScreen(dm, f);
	}

	// loads pictures - Cpt. Obvious
	public void loadPics(){
		bg = new ImageIcon("/home/_/staket/bg.png").getImage();
		char1Img = new ImageIcon("/home/_/staket/char1.png").getImage();
		char2Img = new ImageIcon("/home/_/staket/char2.png").getImage();
	}

	// Main runs the game.
	public static void main(String [] args) {
		(new Staket()).run(new DisplayMode(1920, 1080, 32, DisplayMode.REFRESH_RATE_UNKNOWN));
	}
}
