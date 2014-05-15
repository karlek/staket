import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyInputHandler extends KeyAdapter {
	DisplayMode dm;
	Character char1, char2;
	Staket s;

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
	    		attack();
	    	} else {
		    	c.img = c.images.get("idle");
		    }
	    }

    	public void attack() {
    		int dist = Math.abs(char1.x - char2.x);

    		// if distance is img width or less it's a hit.
    		// and if no character is blocking, the attack was successful.
    		if (dist <= c.img.getWidth(null)/4 && !char1.isBlocking() && !char2.isBlocking()) {
    		    // give the winning fencer a point.
    		    c.points++;

    		    // We need to force a repaint before the sleep. Otherwise the attack
    		    // animation won't be visible.
    		    s.redraw();

    			// Wait a half second to give the users a feedback on what happened.
        		try {
    	    		Thread.sleep(500);
    		    } catch (Exception a) {}

    		    stopTimers();
    		    s.positionChallengers();
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

	// keyPressed is triggered when a key is pressed.
	public void keyPressed(KeyEvent e) {
		// if we hit escape, then quit the game
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		charActions(e.getKeyCode(), char1);
		charActions(e.getKeyCode(), char2);
	}

	// charActions checks if a character designated key was the key pressed.
	public void charActions(int keyCode, Character c) {
		if (keyCode == c.back) {
			moveBack(c);
		} else if (keyCode == c.forward) {
			moveForward(c);
		} else if (keyCode == c.attack) {
			c.attackAnim();
		} else if (keyCode == c.block) {
			c.blockAnim();
		}
	}

	public void moveBack(Character c) {
		c.stopAnimations();

		if (c.equals(char1)) {
			c.moveLeft(dm);
		} else if (c.equals(char2)) {
			c.moveRight(dm);
		}
		c.img = c.images.get("moveback");
		c.moveTimer.restart();
	}

	public void moveForward(Character c) {
		c.stopAnimations();

		if (c.equals(char1)) {
			c.moveRight(dm);
		} else if (c.equals(char2)) {
			c.moveLeft(dm);
		}
		c.img = c.images.get("moveforward");
		c.moveTimer.restart();
	}

	// stopTimers stop both the characters animation timers.
	public void stopTimers() {
		char1.stopAnimations();
		char2.stopAnimations();
	}
}

