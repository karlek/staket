// http://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyInputHandler extends KeyAdapter {
	DisplayMode dm;
	Character char1;
	Character char2;
	Staket s;
	int movementSize = 30;

	// KeyInputHandler initializes the KeyInputHandler.
	public KeyInputHandler(DisplayMode dm, Character char1, Character char2, Staket s) {
		this.dm = dm;
		this.char1 = char1;
		this.char2 = char2;
		this.s = s;

		char1.attackTimer = new Timer(200, new AttackListener(char1));
		char2.attackTimer = new Timer(200, new AttackListener(char2));

		char1.blockTimer = new Timer(500, new BlockListener(char1));
		char2.blockTimer = new Timer(500, new BlockListener(char2));

		char1.moveTimer = new Timer(50, new MoveListener(char1));
		char2.moveTimer = new Timer(50, new MoveListener(char2));
	}

	private class AttackListener implements ActionListener {
		Character c;

		AttackListener(Character c) {
			this.c = c;
		}
	    public void actionPerformed(ActionEvent e) {
	    	if (c.isAttacking) {
	    		c.img = c.images.get("attack");
	    		c.isAttacking = false;
	    		attackTest(c);
	    	} else {
		    	c.img = c.images.get("idle");
		    }
	    }
	}

	private class BlockListener implements ActionListener {
		Character c;
		BlockListener(Character c) {
			this.c = c;
		}
	    public void actionPerformed(ActionEvent e) {
	    	c.img = c.images.get("idle");
			c.blockTimer.stop();
	    }
	}

	private class MoveListener implements ActionListener {
		Character c;
		MoveListener(Character c) {
			this.c = c;
		}
		public void actionPerformed(ActionEvent e) {
			c.img = c.images.get("idle");
		}
	}

	// charActions checks if a character designated key was the key pressed.
	public void charActions(int keyCode, Character c) {
		if (keyCode == c.back) {
			moveBack(c);
		} else if (keyCode == c.forward) {
			moveForward(c);
		} else if (keyCode == c.attack) {
			attack(c);
		} else if (keyCode == c.block) {
			block(c);
		}
	}

	public void moveForward(Character c) {
		c.attackTimer.stop();
		c.blockTimer.stop();

		if (c == char1) {
			moveRight(c);
		} else if (c == char2) {
			moveLeft(c);
		}
		c.img = c.images.get("moveforward");
		c.moveTimer.restart();
	}

	public void moveBack(Character c) {
		c.attackTimer.stop();
		c.blockTimer.stop();

		if (c == char1) {
			moveLeft(c);
		} else if (c == char2) {
			moveRight(c);
		}
		c.img = c.images.get("moveback");
		c.moveTimer.restart();
	}

	public void moveLeft(Character c) {
		if (c.x-movementSize < 0) {
			c.x = 0;
			return;
		}
		c.x -= movementSize;
	}

	public void moveRight(Character c) {
		if (c.x+movementSize+c.img.getWidth(null) > dm.getWidth()) {
			c.x = dm.getWidth()-c.img.getWidth(null);
			return;
		}
		c.x += movementSize;
	}

	public void block(Character c) {
		c.attackTimer.stop();
		c.moveTimer.stop();

		c.img = c.images.get("block");
		c.blockTimer.restart();
	}

	public void attack(Character c) {
		c.moveTimer.stop();
		c.blockTimer.stop();

		c.isAttacking = true;
		c.img = c.images.get("thrust");
		c.attackTimer.restart();
	}

	public void attackTest(Character c) {
		int dist = Math.abs(char1.x - char2.x);
		// if distance is img width or less it's a hit.
		if (dist <= c.img.getWidth(null)/4) {
			// if no character is blocking, the attack was successful.
			if (!char1.blockTimer.isRunning() && !char2.blockTimer.isRunning()) {
			    c.points++;
				Graphics g = s.getGraphics();
				if (g != null) {
					s.paintFonts(g);
					s.paintComponent(g);
				}
				else s.repaint();

	    		try {
		    		Thread.sleep(500);
			    } catch (Exception a) {

			    }
			    char1.x = s.char1X;
			    char2.x = s.char2X;

			    char1.attackTimer.stop();
			    char1.blockTimer.stop();
			    char1.moveTimer.stop();
			    char2.attackTimer.stop();
			    char2.blockTimer.stop();
			    char2.moveTimer.stop();

			    char1.img = char1.images.get("idle");
			    char2.img = char2.images.get("idle");

			}
		}
	}

	// keyPressed is triggered when a key is pressed.
	public void keyPressed(KeyEvent e) {
		// if we hit escape, then quit the game
		if (e.getKeyCode() ==  KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		charActions(e.getKeyCode(), char1);
		charActions(e.getKeyCode(), char2);
	}
}
