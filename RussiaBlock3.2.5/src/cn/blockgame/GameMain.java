package cn.blockgame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 功能：游戏的主入口
 * 
 * @author Keira
 *
 */
public class GameMain {

	public GameMain() {
	}

	public static void main(String[] args) {
		// 设置窗体皮肤;应置于启动窗体之前
		try {
			GameFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new GameFrame();

	}
}
