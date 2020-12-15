package maplestory;

import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends Enemy {
	
	
	public Boss() {
	}

	public Boss(String string, int x, int y, int hp, String name) {
		enemyMove = new ImageIcon(string);
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		
		setIcon(enemyMove);
		setSize(850, 700);
		setLocation(x, y);

	}

	

}
