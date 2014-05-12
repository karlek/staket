import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Character {
	public int x, y, up, left, down, right;
	public Image img;

	public Character(int x, int y, Image img, int up, int left, int down, int right) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.up = up;
		this.left = left;
		this.down = down;
		this.right = right;
	}
}