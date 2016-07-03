package cn.blockgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 功能：实现游戏窗体设计，包括窗体的各种属性
 * 
 * @author Keira
 *
 */
public class GameFrame extends JFrame {
	// 整个窗体的宽、高
	public static final int FRAME_WIDTH = GamePanel.GAME_WIDTH + 10;
	public static final int FRAME_HEIGHT = GamePanel.GAME_HEIGHT + 30;

	public GameFrame() {
		this.setLayout(new BorderLayout());
		// 游戏菜单栏
		GameMenus menus = new GameMenus();
		this.add(menus, BorderLayout.NORTH);

		// 游戏大面板
		GamePanel gamePanel = new GamePanel();
		this.add(gamePanel, BorderLayout.CENTER);

		setFrame("俄罗斯方块小游戏");		
	}

	/**
	 * 功能：设置窗口属性
	 * 
	 * @param title
	 */
	public void setFrame(String title) {
		// 设置窗口标题
		this.setTitle(title);
		// 设置窗口大小
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// 窗口关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置图标
		Image icon_jframe = new ImageIcon("resources\\image\\icon3.jpg").getImage();
		this.setIconImage(icon_jframe);
		// 设置窗口居中
		this.setLocationRelativeTo(null);
		// 设置窗口大小不可变
		this.setResizable(false);
		// 设置窗口可见
		this.setVisible(true);
		
	}

}
