package cn.blockgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ScorePanel extends JPanel {
	// ����
	JTextField jtf_score;
	// �ȼ�
	JTextField jtf_level;
	// ����
	JTextField jtf_lineNum;
	// ������Ϸ��ť,ÿ��һ���������һ��
	JButton jb_Assists;
	
	public ScorePanel() {
		super();
		this.setLayout(new GridLayout(7, 1));

		jtf_score = new JTextField("    �÷֣�" + FunctionPanel.score, 10);	
		setJTextMM(jtf_score, Color.BLACK, 15);		
		jtf_level = new JTextField("    �ȼ���" + FunctionPanel.level, 10);
		setJTextMM(jtf_level, Color.BLACK, 15);
		jtf_lineNum = new JTextField("    ��������" + FunctionPanel.lineNum, 10);
		setJTextMM(jtf_lineNum, Color.BLACK, 15);
		
		jtf_score.setEditable(false); // �����ı��򲻿ɱ༭
		jtf_level.setEditable(false);
		jtf_lineNum.setEditable(false);
		
		JLabel jlab_null = new JLabel(); // ���
		JLabel jlab_null2 = new JLabel(); // ���
		
		jb_Assists = new JButton("��~~~");
		
		this.add(jlab_null);
		this.add(jlab_null2);
		this.add(jtf_score);
		this.add(jtf_level);
		this.add(jtf_lineNum);
		this.add(jb_Assists);
		
		// �������͸����
		this.setOpaque(false);
		// �������߿�
		//this.setBorder(new LineBorder(Color.MAGENTA, 2));
		
		// Ϊ������ť��Ӽ���
		jb_Assists.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����0-15�������
				int rTemp = (int)(0+Math.random()*(15-0+1));
				// ����16-30�������
				int rTemp2 = (int)(16+Math.random()*(30-16+1));
				System.out.println(rTemp + "------" +rTemp2);
					for (int r = rTemp; r < rTemp2; r++) {
						for (int c = 1; c < 21; c++) {
							if (GamePanel.jp_game.blockArr[r][c].getType() == 2) {
								// �������ݣ���r-i�и�ֵ��r�У�ʵ�ַ�����������һλ
								GamePanel.jp_game.blockArr[r][c].setType(GamePanel.jp_game.blockArr[rTemp - 1][c].getType());																
								// ����������
								try {
									AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/clear.WAV").getAbsoluteFile());
									Clip clip = AudioSystem.getClip();
									clip.open(audioInputStream);
									clip.start(); // ����
								} catch (Exception ex) {
									System.out.println("Error with playing sound.");
									ex.printStackTrace();
								}
							}
						}
					}
					// �����������»�ȡ����
					GamePanel.jp_game.requestFocus();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		// ��ӱ���ͼƬ
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);
		
		jtf_score.setText("    �÷֣�" + FunctionPanel.score);
		jtf_level.setText("    �ȼ���" + FunctionPanel.level);
		jtf_lineNum.setText("    ��������" + FunctionPanel.lineNum);		
	}
	
	/**
	 * ����:�����ı�������塢��ɫ����С������
	 * 
	 */
	public void setJTextMM(JTextField jtextTemp, Color colorTemp, int fontTemp) {
		Font font = new Font("������κ", Font.BOLD, fontTemp);		
		jtextTemp.setFont(font);
		jtextTemp.setForeground(colorTemp);
	}
}
