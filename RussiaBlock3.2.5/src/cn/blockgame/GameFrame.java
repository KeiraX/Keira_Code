package cn.blockgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * ���ܣ�ʵ����Ϸ������ƣ���������ĸ�������
 * 
 * @author Keira
 *
 */
public class GameFrame extends JFrame {
	// ��������Ŀ���
	public static final int FRAME_WIDTH = GamePanel.GAME_WIDTH + 10;
	public static final int FRAME_HEIGHT = GamePanel.GAME_HEIGHT + 30;

	public GameFrame() {
		this.setLayout(new BorderLayout());
		// ��Ϸ�˵���
		GameMenus menus = new GameMenus();
		this.add(menus, BorderLayout.NORTH);

		// ��Ϸ�����
		GamePanel gamePanel = new GamePanel();
		this.add(gamePanel, BorderLayout.CENTER);

		setFrame("����˹����С��Ϸ");		
	}

	/**
	 * ���ܣ����ô�������
	 * 
	 * @param title
	 */
	public void setFrame(String title) {
		// ���ô��ڱ���
		this.setTitle(title);
		// ���ô��ڴ�С
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// ���ڹر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����ͼ��
		Image icon_jframe = new ImageIcon("resources\\image\\icon3.jpg").getImage();
		this.setIconImage(icon_jframe);
		// ���ô��ھ���
		this.setLocationRelativeTo(null);
		// ���ô��ڴ�С���ɱ�
		this.setResizable(false);
		// ���ô��ڿɼ�
		this.setVisible(true);
		
	}

}
