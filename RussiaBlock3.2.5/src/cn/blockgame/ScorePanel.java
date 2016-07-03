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
	// 分数
	JTextField jtf_score;
	// 等级
	JTextField jtf_level;
	// 行数
	JTextField jtf_lineNum;
	// 助攻游戏按钮,每按一次随机消除一行
	JButton jb_Assists;
	
	public ScorePanel() {
		super();
		this.setLayout(new GridLayout(7, 1));

		jtf_score = new JTextField("    得分：" + FunctionPanel.score, 10);	
		setJTextMM(jtf_score, Color.BLACK, 15);		
		jtf_level = new JTextField("    等级：" + FunctionPanel.level, 10);
		setJTextMM(jtf_level, Color.BLACK, 15);
		jtf_lineNum = new JTextField("    消行数：" + FunctionPanel.lineNum, 10);
		setJTextMM(jtf_lineNum, Color.BLACK, 15);
		
		jtf_score.setEditable(false); // 设置文本框不可编辑
		jtf_level.setEditable(false);
		jtf_lineNum.setEditable(false);
		
		JLabel jlab_null = new JLabel(); // 插空
		JLabel jlab_null2 = new JLabel(); // 插空
		
		jb_Assists = new JButton("咩~~~");
		
		this.add(jlab_null);
		this.add(jlab_null2);
		this.add(jtf_score);
		this.add(jtf_level);
		this.add(jtf_lineNum);
		this.add(jb_Assists);
		
		// 设置面板透明化
		this.setOpaque(false);
		// 设置面板边框
		//this.setBorder(new LineBorder(Color.MAGENTA, 2));
		
		// 为助攻按钮添加监听
		jb_Assists.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 产生0-15的随机数
				int rTemp = (int)(0+Math.random()*(15-0+1));
				// 产生16-30的随机数
				int rTemp2 = (int)(16+Math.random()*(30-16+1));
				System.out.println(rTemp + "------" +rTemp2);
					for (int r = rTemp; r < rTemp2; r++) {
						for (int c = 1; c < 21; c++) {
							if (GamePanel.jp_game.blockArr[r][c].getType() == 2) {
								// 拷贝数据：将r-i行赋值给r行；实现方块整体下移一位
								GamePanel.jp_game.blockArr[r][c].setType(GamePanel.jp_game.blockArr[rTemp - 1][c].getType());																
								// 消除的声音
								try {
									AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/audio/clear.WAV").getAbsoluteFile());
									Clip clip = AudioSystem.getClip();
									clip.open(audioInputStream);
									clip.start(); // 播放
								} catch (Exception ex) {
									System.out.println("Error with playing sound.");
									ex.printStackTrace();
								}
							}
						}
					}
					// 让下落区重新获取焦点
					GamePanel.jp_game.requestFocus();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		// 添加背景图片
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);
		
		jtf_score.setText("    得分：" + FunctionPanel.score);
		jtf_level.setText("    等级：" + FunctionPanel.level);
		jtf_lineNum.setText("    消行数：" + FunctionPanel.lineNum);		
	}
	
	/**
	 * 功能:设置文本框的字体、颜色、大小等属性
	 * 
	 */
	public void setJTextMM(JTextField jtextTemp, Color colorTemp, int fontTemp) {
		Font font = new Font("华文新魏", Font.BOLD, fontTemp);		
		jtextTemp.setFont(font);
		jtextTemp.setForeground(colorTemp);
	}
}
