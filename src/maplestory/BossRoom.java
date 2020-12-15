package maplestory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import maplestory.MapleApp.col;

public class BossRoom extends MapleApp implements Initable{

	@Override
	   public void init() {
	      c = getContentPane();
	      laBackground = new JLabel(new ImageIcon("image/����������.png"));
	      
	      icHp0 = new ImageIcon("image/hpp0.png");
	      icHp10 = new ImageIcon("image/hpp10.png");
	      icHp20 = new ImageIcon("image/hpp20.png");
	      icHp30 = new ImageIcon("image/hpp30.png");
	      icHp40 = new ImageIcon("image/hpp40.png");
	      icHp50 = new ImageIcon("image/hpp50.png");
	      icHp60 = new ImageIcon("image/hpp60.png");
	      icHp70 = new ImageIcon("image/hpp70.png");
	      icHp80 = new ImageIcon("image/hpp80.png");
	      icHp90 = new ImageIcon("image/hpp90.png");
	      icHp100 = new ImageIcon("image/hpp100.png");

	      icMp0 = new ImageIcon("image/mpp0.png");
	      icMp10 = new ImageIcon("image/mpp10.png");
	      icMp20 = new ImageIcon("image/mpp20.png");
	      icMp30 = new ImageIcon("image/mpp30.png");
	      icMp40 = new ImageIcon("image/mpp40.png");
	      icMp50 = new ImageIcon("image/mpp50.png");

	      player = new Player();
	      
	     
	      boss = new Boss("image/����.gif",400,200,400,"����");
	      bar = new PlayerHpBar();
	      bar2 = new PlayerMpBar();
	      gamePoint = new GamePoint();
	      
	      enemy.add(boss);


	      player.healing();
	      Thread bossCol = new Thread(new col(boss));
          bossCol.start();
	      
	      
	   }

	   @Override
	   public void setting() {
	      setTitle("������ ����");
	      setSize(1290, 759);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLayout(null);
	      setContentPane(laBackground);
	   }

	   @Override
	   public void batch() {
	      add(player);
	      add(boss);
	      
	      add(bar);
	      add(bar2);
	      add(gamePoint);

	   }

	   @Override
	   public void listener() {
	      addKeyListener(new KeyAdapter() {
	         @Override
	         public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	               player.moveRight1();
	            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	               player.moveLeft();
	            } else if (e.getKeyCode() == KeyEvent.VK_A) {
	               player.attack();
	            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
	               player.moveJump();
	            } else if (player.mp >= 10) {
	               if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
	                  skillShot = new Skill(player, enemy);
	                  add(skillShot);
	                  player.skilshot();
	                  System.out.println("MP : " + player.mp + " ���ҽ��ϴ�.");

	               } else if (e.getKeyCode() == KeyEvent.VK_UP) {
	                  player.isJump = false;
	               }

	            }
	         }

	         @Override
	         public void keyReleased(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	               player.isRight = false;
	               player.isMove = false;

	            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	               player.isLeft = false;
	               player.isMove = false;
	            } else if (e.getKeyCode() == KeyEvent.VK_A) {
	               player.isAttack = false;
	            }
	         }
	      });

	   }

	// �浹 Ŭ����
	   class col extends Thread {
	      Enemy enemy;

	      public col(Enemy enemy) {
	         this.enemy = enemy;
	      }

	      @Override
	      public void run() {
	         while (true) {
	            try {
	               Thread.sleep(1);

	               if (player.hp == 100) {
	                  bar.setIcon(icHp100);
	               } else if (player.hp < 100 && player.hp >= 90) {
	                  bar.setIcon(icHp90);
	               } else if (player.hp < 90 && player.hp >= 80) {
	                  bar.setIcon(icHp80);
	               } else if (player.hp < 80 && player.hp >= 70) {
	                  bar.setIcon(icHp70);
	               } else if (player.hp < 70 && player.hp >= 60) {
	                  bar.setIcon(icHp60);
	               } else if (player.hp < 60 && player.hp >= 50) {
	                  bar.setIcon(icHp50);
	               } else if (player.hp < 50 && player.hp >= 40) {
	                  bar.setIcon(icHp40);
	               } else if (player.hp < 40 && player.hp >= 30) {
	                  bar.setIcon(icHp30);
	               } else if (player.hp < 3 && player.hp >= 20) {
	                  bar.setIcon(icHp20);
	               } else if (player.hp < 20 && player.hp >= 10) {
	                  bar.setIcon(icHp10);
	               }

	               if (player.mp == 50) {
	                  bar2.setIcon(icMp50);
	               } else if (player.mp < 50 && player.mp >= 40) {
	                  bar2.setIcon(icMp40);
	               } else if (player.mp < 40 && player.mp >= 30) {
	                  bar2.setIcon(icMp30);
	               } else if (player.mp < 30 && player.mp >= 20) {
	                  bar2.setIcon(icMp20);
	               } else if (player.mp < 20 && player.mp >= 10) {
	                  bar2.setIcon(icMp10);
	               } else if (player.mp < 10 && player.mp >= 0) {
	                  bar2.setIcon(icMp0);
	               }
	               gamePoint.setText("Point : "+ MapleApp.deadEnemy[2]);
	               // �÷��̾�
	               if (crash(player.x, player.y, enemy.x, enemy.y, player.width, player.height, enemy.width,
	                     enemy.height)) {
	                  System.out.println("�浹 �߻�!");
	                  Thread.sleep(1500);
	                  player.hp = player.hp - 10;
	                  System.out.println("�÷��̾� hp : " + player.hp + " ���ҽ��ϴ�.");

	                  if (player.hp <= 0) {
	                     player.dieDown();
	                     int result = JOptionPane.showConfirmDialog(null, "�׾���...", "�ȳ��޼���", JOptionPane.OK_OPTION);
	                     if (result == JOptionPane.OK_OPTION) {
	                        System.exit(0);
	                     }
	                  }

	               }
	               // �⺻����
	               if (attackCrash(player.x, player.y, enemy.x, enemy.y, player.width, player.height, enemy.width,
	                     enemy.height)) {
	                  if (player.isAttack == true) {
	                     System.out.println("�⺻���� ����!");
	                     Thread.sleep(1000);
	                     enemy.hp = enemy.hp - 10;
	                     System.out.println(" hp : " + enemy.hp);

	                     if (enemy.hp <= 0) {
	                        System.out.println(enemy.name + " ����...");
	                        score(enemy.name);   //���� ���
	                        gamePoint.setText("Point : "+deadEnemy[2]);   //���� ǥ��
	                        if(deadEnemy[2] > 100) {
//	                        	c.remove(mushroom);
//	                        	c.remove(stone);
//	                        	c.remove(barlog);
//	                        	setContentPane(laBackground);
//	                        	laBackground = new JLabel(new ImageIcon("image/����������.png"));
//	                        	enemy.add(boss);
//	                            Thread bossCol = new Thread(new col(boss));
//	                            bossCol.start();
//	                            add(boss);
	                        	
	                            
	                        }
	                        enemy.x = 999999;
	                        Thread.sleep(3000);
	                        if (enemy == mushroom) {
	                           enemy.x = 550;
	                           enemy.hp = 20;
	                           
	                        }

	                        if (enemy == stone) {
	                           enemy.x = 100;
	                           enemy.hp = 20;
	                        }
	                        if (enemy == barlog) {
	                           enemy.x = 200;
	                           enemy.hp = 20;
	                        }
	                     }

	                  }

	               }

//	               

	            } catch (Exception e) {
	               e.getMessage();
	            }
	         }

	      }
	   }

	   // �浹 �Լ�
	   public boolean crash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
	         int enemyH) {
	      boolean check = false;
	      if (Math.abs((playerX + (playerW / 2)) - (enemyX + enemyW / 2 + 20)) < (enemyW / 2 + playerW / 2 - 60)
	            && Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
	         check = true;
	      } else {
	         check = false;
	      }
	      return check;
	   }
	   // end �浹

	   // ���� ���� �Լ�
	   public boolean attackCrash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
	         int enemyH) {
	      boolean check = false;
	      if (Math.abs((playerX + (playerW / 2)) - (enemyX + enemyW / 2)) < (enemyW / 2 + playerW / 2)
	            && Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
	         check = true;
	      } else {
	         check = false;
	      }
	      return check;
	   } // ���� ���� �Լ� END

	   public static int[] score(String enemy) {

	      if (enemy == "��Ȳ����") { // ���� 50��
	         scoreMushNum += 50; // ���� ���� ������ 50�� ����
	         deadEnemy[0]++; // ���� ���� �� ���
	         deadEnemy[2] += 50;
	         System.out.println("���� ����");
	         System.out.println(scoreMushNum);
	      } else if (enemy == "�����") { // �� 30��
	         scoreStoneNum += 30; // �� ���� ������ 30�� ����
	         deadEnemy[1]++; // �� ���� �� ���
	         deadEnemy[2] += 30;
	         System.out.println("�� ����");
	         System.out.println(scoreStoneNum);
	      } else if (enemy == "�ִϾ�߷�") { // �� 30��
	          scoreStoneNum += 40; // �� ���� ������ 30�� ����
	          deadEnemy[1]++; // �� ���� �� ���
	          deadEnemy[2] += 40;
	          System.out.println("�� ����");
	          System.out.println(scoreStoneNum);
	       }
	      return deadEnemy; // 0���� - ���� ���� ��
	   } // 1���� - �� ���� �� �� ���� ���� �� ��ȯ
}
