import java.awt.*;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

// Implement movement block to not go past each other.

// Staket
public class Staket extends JPanel implements ActionListener {
	// bg = background.
	public Image bg;

	// HashMap of the animations for the characters.
	public HashMap<String, Image> char1Images, char2Images;

	// 1720
	public int char1X = 100, char1Y = 550, char2Y = 550, char2X = 600;

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
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		  RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
		  RenderingHints.VALUE_RENDER_QUALITY);
		super.paintComponent(g2);
		super.paintComponent(g);

		g.drawImage(bg, 0, 0, null);
		g.drawImage(char1.img, char1.x, char1.y, null);
		g.drawImage(char2.img, char2.x, char2.y, null);
		// g.drawString(char1.points+"", 200, 200);
		g.drawString(char2.points+"", 400, 200);
	}

	public void run(DisplayMode dm) {
		loadPics();

		char1 = new Character(
			char1X,
			char1Y,
			char1Images,
			KeyEvent.VK_W,
			KeyEvent.VK_A,
			KeyEvent.VK_S,
			KeyEvent.VK_D
		);

		char2 = new Character(
			char2X,
			char2Y,
			char2Images,
			KeyEvent.VK_UP,
			KeyEvent.VK_RIGHT,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_LEFT
		);

		this.ki = new KeyInputHandler(
			dm,
			char1,
			char2,
			this
		);

		setForeground(Color.BLACK);
		setFont(new Font("Serif", Font.PLAIN, 140));

		JFrame f = new JFrame();
		f.addKeyListener(ki);
		f.add(this);

		timer.start();
		(new Screen()).setFullScreen(dm, f);
	}

	// loads pictures - Cpt. Obvious
	public void loadPics() {
		char1Images = new HashMap<String, Image>();
		char2Images = new HashMap<String, Image>();

		char1Images.put("idle", new ImageIcon("/home/_/staket/Idle.png").getImage());
		char2Images.put("idle", new ImageIcon("/home/_/staket/Idle2.png").getImage());

		char1Images.put("thrust", new ImageIcon("/home/_/staket/Thrust.png").getImage());
		char2Images.put("thrust", new ImageIcon("/home/_/staket/Thrust2.png").getImage());

		char1Images.put("attack", new ImageIcon("/home/_/staket/Attack.png").getImage());
		char2Images.put("attack", new ImageIcon("/home/_/staket/Attack2.png").getImage());

		char1Images.put("block", new ImageIcon("/home/_/staket/Block.png").getImage());
		char2Images.put("block", new ImageIcon("/home/_/staket/Block2.png").getImage());

		char1Images.put("moveback", new ImageIcon("/home/_/staket/BackMove.png").getImage());
		char2Images.put("moveback", new ImageIcon("/home/_/staket/BackMove2.png").getImage());

		char1Images.put("moveforward", new ImageIcon("/home/_/staket/FrontMove.png").getImage());
		char2Images.put("moveforward", new ImageIcon("/home/_/staket/FrontMove2.png").getImage());

		bg = new ImageIcon("/home/_/staket/bg.png").getImage();
	}

	// Main runs the game.
	public static void main(String [] args) {
		(new Staket()).run(new DisplayMode(1600, 900, 32, DisplayMode.REFRESH_RATE_UNKNOWN));
	}
}
