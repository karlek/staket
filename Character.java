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
}
