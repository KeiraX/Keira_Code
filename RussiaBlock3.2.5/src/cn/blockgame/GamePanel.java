package cn.blockgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 功能：窗体中的游戏大面板，包括：方块下落区面板、功能区面板
 * 
 * @author Keira
 *
 */
public class GamePanel extends JPanel {
	// 创建引用
	public static GameAreaPanel jp_game = null;
	public static FunctionPanel jp_fun = null;
	// 菜单栏
	public static final int Menus_HEIGHT = 20;
	// 整个游戏面板的宽、高
	public static final int GAME_WIDTH = GameAreaPanel.GAMEAREA_WIDTH + FunctionPanel.Function_WIDTH;
	public static final int GAME_HEIGHT = GameAreaPanel.GAMEAREA_HEIGHT + Menus_HEIGHT;
	
	public GamePanel() {
		this.setLayout(new BorderLayout());
		// 下落区
		GameAreaPanel jp_gameArea = new GameAreaPanel();
		// 让jp_gameArea指向jp_game,方便调用
		jp_game = jp_gameArea;
		// 设置下落区背景颜色
		//jp_gameArea.setBackground(Color.BLACK);
		//功能区
		FunctionPanel jp_function = new FunctionPanel();
		// 让jp_function指向jp_fun,方便调用
		jp_fun = jp_function;
		//添加
		this.add(jp_gameArea, BorderLayout.CENTER);
		this.add(jp_function, BorderLayout.EAST);
		// 重绘
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
