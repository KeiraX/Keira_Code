package cn.blockgame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * ���ܣ���Ϸ�������
 * 
 * @author Keira
 *
 */
public class GameMain {

	public GameMain() {
	}

	public static void main(String[] args) {
		// ���ô���Ƥ��;Ӧ������������֮ǰ
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
