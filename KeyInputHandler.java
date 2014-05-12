// http://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyInputHandler extends KeyAdapter {
	DisplayMode dm;
	Character char1;
	Character char2;
	int movmentSize = 30;

	// KeyInputHandler initializes the KeyInputHandler.
	public KeyInputHandler(DisplayMode dm, Character char1, Character char2) {
		this.dm = dm;
		this.char1 = char1;
		this.char2 = char2;
	}

	// charActions checks if a character designated key was the key pressed.
	public void charActions(int keyCode, Character c) {
		if (keyCode == c.left) {
			if (c.x-movmentSize < 0) {
				c.x = 0;
				return;
			}
			c.x -= movmentSize;
		}
		if (keyCode == c.right){
			if (c.x+movmentSize+c.img.getWidth(null) > dm.getWidth()) {
				c.x = dm.getWidth()-c.img.getWidth(null);
				return;
			}
			c.x += movmentSize;
		}
		if (c.up == keyCode) {
			attackUp(c);
			// check which direction to attack against.
		}
		/*
		if (c.up == keyCode) {
			if (c.y-movmentSize < 0) {
				c.y = 0;
				return;
			}
			c.y -= movmentSize;
		}
		if (keyCode == c.down) {
			if (c.y+movmentSize+c.img.getHeight(null) > dm.getHeight()) {
				c.y = dm.getHeight()-c.img.getHeight(null);
				return;
			}
			c.y += movmentSize;
		}
		*/
	}

	public void attackUp(Character c) {
		if (c == char1) {
			// attack from left to right
			attackUpLeft();
		}
		if (c == char2) {
			// attack from right to left
			attackUpRight();
		}
	}

	public void attackUpLeft() {
		// if distance is img width or less it's a hit.
		int dist = Math.abs(char1.x - char2.x);
		if (dist <= char1.img.getWidth(null)) {
			System.out.println("King Richard wins!");
			System.exit(0);
		}
	}

	public void attackUpRight() {
		// if distance is img width or less it's a hit.
		int dist = Math.abs(char2.x - char1.x);
		if (dist <= char2.img.getWidth(null)) {
			System.out.println("King Henry wins!");
			System.exit(0);
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
