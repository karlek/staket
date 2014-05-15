import java.awt.*;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;


class Character {
	// x, y is the character's current coordinate on the screen.
	//
	// attack, block back and forward are the assigned keyboard buttons for that
	// character
	// Points is self explanatory.
	public int x, y, attack, block, back, forward, points;

	// images is a collection of the different animations the character can
	// take. Attack, block, move forward, move backword and idle.
	public HashMap<String, Image> images;

	// img is the current image the character is displaying on screen.
	public Image img;

	//
	Timer attackTimer;
	Timer blockTimer;
	Timer moveTimer;

	boolean isAttacking;

	int movementSize = 30;

	public Character(int x, int y, HashMap<String, Image> images, int attack, int back, int block, int forward) {
		this.x = x;
		this.y = y;
		this.attack = attack;
		this.back = back;
		this.block = block;
		this.forward = forward;
		this.images = images;
		this.img = this.images.get("idle");
	}

	public boolean isBlocking() {
		return blockTimer.isRunning();
	}

	public void attackAnim() {
		stopAnimations();

		isAttacking = true;
		img = images.get("thrust");
		attackTimer.restart();
	}

	public void blockAnim() {
		stopAnimations();

		img = images.get("block");
		blockTimer.restart();
	}

	public void moveRight(DisplayMode dm) {
		if (x+movementSize+img.getWidth(null) > dm.getWidth()) {
			x = dm.getWidth()-img.getWidth(null);
			return;
		}
		x += movementSize;
	}

	public void moveLeft(DisplayMode dm) {
		if (x-movementSize < 0) {
			x = 0;
			return;
		}
		x -= movementSize;
	}

	public void stopAnimations() {
		attackTimer.stop();
		blockTimer.stop();
		moveTimer.stop();
	}
}
