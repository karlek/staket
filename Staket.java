import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

// Implement movement block to not go past each other.

// Staket
public class Staket extends JPanel {
	// bg = background.
	public Image bg;

	// HashMap of the animations for the characters.
	public HashMap<String, Image> char1Images, char2Images;

	public int char1StartX, char2StartX, charStartY, points1X, pointsY, points2X;

 	// ki handles key inputs.
	public KeyInputHandler ki;

	// char1 is the first character, char2 is the second character.
	public Character char1, char2;

	// timer handles graphic redrawing.
	public Timer timer = new Timer(20, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	        repaint();
	    }
	});

	public void redraw() {
		Graphics g = getGraphics();
		if (g != null) {
			paintComponent(g);
		} else {
			repaint();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		// Make text pretty.
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Draw background.
		g.drawImage(bg, 0, 0, null);

		// Draw characters.
		g.drawImage(char1.img, char1.x, char1.y, null);
		g.drawImage(char2.img, char2.x, char2.y, null);

		// Draw points.
		g.drawString(char1.points+"", points1X, pointsY);
		g.drawString(char2.points+"", points2X, pointsY);
	}

	public void positionChallengers() {
		char1.x = char1StartX;
		char2.x = char2StartX;

	    char1.img = char1.images.get("idle");
	    char2.img = char2.images.get("idle");
	}

	// run is the games main loop.
	public void run(DisplayMode dm) {
		loadPics();

		char1StartX = dm.getWidth()/4;
		char2StartX = dm.getWidth()-dm.getWidth()/2;
		charStartY = dm.getHeight()-dm.getHeight()/3;

		points1X = dm.getWidth()/10;
		points2X = dm.getWidth()-dm.getWidth()/5;
		pointsY = dm.getHeight()/6;

		char1 = new Character(
			char1StartX,
			charStartY,
			char1Images,
			KeyEvent.VK_W,
			KeyEvent.VK_A,
			KeyEvent.VK_S,
			KeyEvent.VK_D
		);

		char2 = new Character(
			char2StartX,
			charStartY,
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

		char1Images.put("idle", new ImageIcon("images/Idle.png").getImage());
		char2Images.put("idle", new ImageIcon("images/Idle2.png").getImage());

		char1Images.put("thrust", new ImageIcon("images/Thrust.png").getImage());
		char2Images.put("thrust", new ImageIcon("images/Thrust2.png").getImage());

		char1Images.put("attack", new ImageIcon("images/Attack.png").getImage());
		char2Images.put("attack", new ImageIcon("images/Attack2.png").getImage());

		char1Images.put("block", new ImageIcon("images/Block.png").getImage());
		char2Images.put("block", new ImageIcon("images/Block2.png").getImage());

		char1Images.put("moveback", new ImageIcon("images/BackMove.png").getImage());
		char2Images.put("moveback", new ImageIcon("images/BackMove2.png").getImage());

		char1Images.put("moveforward", new ImageIcon("images/FrontMove.png").getImage());
		char2Images.put("moveforward", new ImageIcon("images/FrontMove2.png").getImage());

		bg = new ImageIcon("images/bg.png").getImage();
	}

	// Main runs the game.
	public static void main(String [] args) {
		Dimension dim = (Toolkit.getDefaultToolkit()).getScreenSize();
		(new Staket()).run(new DisplayMode(dim.width, dim.height, 32, DisplayMode.REFRESH_RATE_UNKNOWN));
	}
}
