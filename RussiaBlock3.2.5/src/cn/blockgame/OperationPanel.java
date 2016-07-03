package cn.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class OperationPanel extends JPanel {

	public OperationPanel() {
		super();
		this.setLayout(new GridLayout(7, 1));
		
		JLabel jlab = new JLabel("功能键盘 ",JLabel.CENTER);
		setJLabelMM(jlab, Color.WHITE, 25);				
		JLabel jlab2 = new JLabel("快速向下：↓",JLabel.CENTER);
		setJLabelMM(jlab2, Color.WHITE, 20);
		JLabel jlab3 = new JLabel("旋转：↑",JLabel.CENTER);
		setJLabelMM(jlab3, Color.WHITE, 20);
		JLabel jlab4 = new JLabel("向左：←",JLabel.CENTER);
		setJLabelMM(jlab4, Color.WHITE, 20);
		JLabel jlab5 = new JLabel("向右：→",JLabel.CENTER);
		setJLabelMM(jlab5, Color.WHITE, 20);
		JLabel jlab6 = new JLabel("暂停：空格键",JLabel.CENTER);
		setJLabelMM(jlab6, Color.WHITE, 20);
		JLabel jlab7 = new JLabel("继续：回车键",JLabel.CENTER);
		setJLabelMM(jlab7, Color.WHITE, 20);
		
		this.add(jlab);
		this.add(jlab2);
		this.add(jlab3);
		this.add(jlab4);
		this.add(jlab5);
		this.add(jlab6);
		this.add(jlab7);
		
		// 设置面板透明化
		this.setOpaque(false);
		// 设置面板边框
		//this.setBorder(new LineBorder(Color.ORANGE, 2));
		// 在网格布局下设置面板大小
		this.setPreferredSize(new Dimension(GamePanel.jp_fun.Function_WIDTH, 0));
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		// 添加背景图片
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);		
//	}
	
	/**
	 * 功能:设置标签的字体、颜色、大小等属性
	 * 
	 */
	public void setJLabelMM(JLabel jlabelTemp, Color colorTemp, int fontTemp) {
		Font font = new Font("华文新魏", Font.BOLD, fontTemp);
		jlabelTemp.setFont(font);
		jlabelTemp.setForeground(colorTemp);
	}
}
