import java.awt.*;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

class Character implements ActionListener {
	public int x, y, attack, back, block, forward, points=2147483647;
	public HashMap<String, Image> images;
	public Image img;
	Timer attackTimer;
	Timer blockTimer;
	Timer moveTimer = new Timer(50, this);
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

	public void actionPerformed(ActionEvent e) {
		this.img = images.get("idle");
	}
}
