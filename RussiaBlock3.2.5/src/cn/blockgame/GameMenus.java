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
 * ���ܣ�ʵ����Ϸ�Ĳ˵�����
 * 
 * @author Keira
 *
 */
public class GameMenus extends JMenuBar {
	public GameMenus() {
		super();
		// ����һ���˵�
		JMenu gameMenu = new JMenu("��Ϸ(G)");
		// �����ȼ�,��ALT+Q
		gameMenu.setMnemonic('G');
		JMenu helpMenu = new JMenu("����(H)");
		helpMenu.setMnemonic('H');
		// ���������˵�
		JMenu optionsItem = new JMenu("ѡ��(O)");
		optionsItem.setMnemonic('O');
		// �����˵���
		JMenuItem newGameItem = new JMenuItem("����(S)");
		newGameItem.setMnemonic('S');
		JMenuItem exitItem = new JMenuItem("�˳�(X)");
		exitItem.setMnemonic('X');
		JMenuItem aboutItem = new JMenuItem("����(A)");
		aboutItem.setMnemonic('A');

		// Ĭ��ѡ�г����Ѷ�
		JRadioButton primaryButton = new JRadioButton("����(P)", true);
		primaryButton.setMnemonic('P');
		JRadioButton intermediateButton = new JRadioButton("�м�(I)", false);
		intermediateButton.setMnemonic('I');
		JRadioButton advancedButton = new JRadioButton("�߼�(A)", false);
		advancedButton.setMnemonic('A');
		JMenuItem customItem = new JMenuItem("�Զ���(C)");
		customItem.setMnemonic('C');

		// ��3��RadioButtonͬһʱ��ֻ��һ��RadioButton��״̬����Ϊ"on";��ʵ�ֵ�ѡ��ť
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(primaryButton);
		bgroup.add(intermediateButton);
		bgroup.add(advancedButton);

		// �������ӵ�������
		gameMenu.add(newGameItem);
		gameMenu.addSeparator(); // �Ӻ���
		gameMenu.add(optionsItem);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);

		helpMenu.add(aboutItem);

		optionsItem.add(primaryButton);
		optionsItem.add(intermediateButton);
		optionsItem.add(advancedButton);
		optionsItem.addSeparator(); // �Ӻ���
		optionsItem.add(customItem);

		this.add(gameMenu);
		this.add(helpMenu);
		
		// ���"����"ʵ�ַ������������ַ��鿪ʼ��Ϸ
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʼ��Ϸ
				GamePanel.jp_game.startGame();
			}
		});

		// ���"�˳�"ʵ���˳�����
		exitItem.addActionListener(new ActionListener() {
			Icon icon = new ImageIcon("resources\\image\\icon2.jpg");

			public void actionPerformed(ActionEvent e) {
				// ѡ���Ƿ��˳���Ϸ�ĶԻ���
				int option = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳���Ϸ��", "��ѡ��", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, icon);
				// ѡ��"��",���˳���Ϸ
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		// ���"����"ʵ�ֵ���"����"�Ի���
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameDialog about = new GameDialog();
				about.aboutDialog();
			}
		});
		// ���"�Զ���(C)"ʵ�ֵ���"�Զ���"�Ի���
		customItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameDialog custom = new GameDialog();
				custom.CustomDialog();

			}
		});
		// �ȼ�ѡ�� -- ����
		primaryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---����---");
				FunctionPanel.level = "����";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(1000);
			}
		});
		// �ȼ�ѡ�� -- �м�
		intermediateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---�м�---");
				FunctionPanel.level = "�м�";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(500);
			}
		});
		// �ȼ�ѡ�� -- �߼�
		advancedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("---�߼�---");
				FunctionPanel.level = "�߼�";
				GamePanel.jp_fun.jp_score.repaint();
				GamePanel.jp_game.timer.setDelay(200);
			}
		});
	}

}
