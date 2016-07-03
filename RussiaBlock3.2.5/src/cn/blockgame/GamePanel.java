package cn.blockgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * ���ܣ������е���Ϸ����壬������������������塢���������
 * 
 * @author Keira
 *
 */
public class GamePanel extends JPanel {
	// ��������
	public static GameAreaPanel jp_game = null;
	public static FunctionPanel jp_fun = null;
	// �˵���
	public static final int Menus_HEIGHT = 20;
	// ������Ϸ���Ŀ���
	public static final int GAME_WIDTH = GameAreaPanel.GAMEAREA_WIDTH + FunctionPanel.Function_WIDTH;
	public static final int GAME_HEIGHT = GameAreaPanel.GAMEAREA_HEIGHT + Menus_HEIGHT;
	
	public GamePanel() {
		this.setLayout(new BorderLayout());
		// ������
		GameAreaPanel jp_gameArea = new GameAreaPanel();
		// ��jp_gameAreaָ��jp_game,�������
		jp_game = jp_gameArea;
		// ����������������ɫ
		//jp_gameArea.setBackground(Color.BLACK);
		//������
		FunctionPanel jp_function = new FunctionPanel();
		// ��jp_functionָ��jp_fun,�������
		jp_fun = jp_function;
		//���
		this.add(jp_gameArea, BorderLayout.CENTER);
		this.add(jp_function, BorderLayout.EAST);
		// �ػ�
		GamePanel.this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\b1\\"+ GamePanel.jp_game.wallPaper + ".jpg");
		g.drawImage(gameBack,0,0,this);		
	}
}
