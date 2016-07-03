package cn.blockgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * 功能：实现游戏的菜单功能
 * 
 * @author Keira
 *
 */
public class GameMenus extends JMenuBar {
	public GameMenus() {
		super();
		// 创建一级菜单
		JMenu gameMenu = new JMenu("游戏(G)");
		// 设置热键,按ALT+Q
		gameMenu.setMnemonic('G');
		JMenu helpMenu = new JMenu("帮助(H)");
		helpMenu.setMnemonic('H');
		// 创建二级菜单
		JMenu optionsItem = new JMenu("选项(O)");
		optionsItem.setMnemonic('O');
		// 创建菜单项
		JMenuItem newGameItem = new JMenuItem("开局(S)");
		newGameItem.setMnemonic('S');
		JMenuItem exitItem = new JMenuItem("退出(X)");
		exitItem.setMnemonic('X');
		JMenuItem aboutItem = new JMenuItem("关于(A)");
		aboutItem.setMnemonic('A');

		// 默认选中初级难度
		JRadioButton primaryButton = new JRadioButton("初级(P)", true);
		primaryButton.setMnemonic('P');
		JRadioButton intermediateButton = new JRadioButton("中级(I)", false);
		intermediateButton.setMnemonic('I');
		JRadioButton advancedButton = new JRadioButton("高级(A)", false);
		advancedButton.setMnemonic('A');
		JMenuItem customItem = new JMenuItem("自定义(C)");
		customItem.setMnemonic('C');

		// 此3个RadioButton同一时间只有一个RadioButton的状态可以为"on";即实现单选按钮
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(primaryButton);
		bgroup.add(intermediateButton);
		bgroup.add(advancedButton);

		// 把组件添加到窗体里
		gameMenu.add(newGameItem);
		gameMenu.addSeparator(); // 加横线
		gameMenu.add(optionsItem);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);

		helpMenu.add(aboutItem);

		optionsItem.add(primaryButton);
		optionsItem.add(intermediateButton);
		optionsItem.add(advancedButton);
		optionsItem.addSeparator(); // 加横线
		optionsItem.add(customItem);

		this.add(gameMenu);
		this.add(helpMenu);
		
		// 点击"开局"实现方块下落区出现方块开始游戏
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 开始游戏
				GamePanel.jp_game.startGame();
			}
		});

		// 点击"退出"实现退出程序
		exitItem.addActionListener(new ActionListener() {
			Icon icon = new ImageIcon("resources\\image\\icon2.jpg");

			public void actionPerformed(ActionEvent e) {
				// 选择是否退出游戏的对话框
				int option = JOptionPane.showConfirmDialog(null, "您确定要退出游戏？", "请选择", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, icon);
				// 选择"是",则退出游戏
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		// 点击"关于"实现弹出"关于"对话框
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameDialog about = new GameDialog();
				about.aboutDialog();
			}
		});
		// 点击"自定义(C)"实现弹出"自定义"对话框
		customItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameDialog custom = new GameDialog();
				custom.CustomDialog();

			}
		});
		// 等级选择 -- 初级
		primaryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---初级---");
				FunctionPanel.level = "初级";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(1000);
			}
		});
		// 等级选择 -- 中级
		intermediateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---中级---");
				FunctionPanel.level = "中级";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(500);
			}
		});
		// 等级选择 -- 高级
		advancedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---高级---");
				FunctionPanel.level = "高级";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(200);
			}
		});
	}

}
