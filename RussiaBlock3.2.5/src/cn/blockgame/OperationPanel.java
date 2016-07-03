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
		
		JLabel jlab = new JLabel("���ܼ��� ",JLabel.CENTER);
		setJLabelMM(jlab, Color.WHITE, 25);				
		JLabel jlab2 = new JLabel("�������£���",JLabel.CENTER);
		setJLabelMM(jlab2, Color.WHITE, 20);
		JLabel jlab3 = new JLabel("��ת����",JLabel.CENTER);
		setJLabelMM(jlab3, Color.WHITE, 20);
		JLabel jlab4 = new JLabel("���󣺡�",JLabel.CENTER);
		setJLabelMM(jlab4, Color.WHITE, 20);
		JLabel jlab5 = new JLabel("���ң���",JLabel.CENTER);
		setJLabelMM(jlab5, Color.WHITE, 20);
		JLabel jlab6 = new JLabel("��ͣ���ո��",JLabel.CENTER);
		setJLabelMM(jlab6, Color.WHITE, 20);
		JLabel jlab7 = new JLabel("�������س���",JLabel.CENTER);
		setJLabelMM(jlab7, Color.WHITE, 20);
		
		this.add(jlab);
		this.add(jlab2);
		this.add(jlab3);
		this.add(jlab4);
		this.add(jlab5);
		this.add(jlab6);
		this.add(jlab7);
		
		// �������͸����
		this.setOpaque(false);
		// �������߿�
		//this.setBorder(new LineBorder(Color.ORANGE, 2));
		// �����񲼾�����������С
		this.setPreferredSize(new Dimension(GamePanel.jp_fun.Function_WIDTH, 0));
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		// ��ӱ���ͼƬ
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);		
//	}
	
	/**
	 * ����:���ñ�ǩ�����塢��ɫ����С������
	 * 
	 */
	public void setJLabelMM(JLabel jlabelTemp, Color colorTemp, int fontTemp) {
		Font font = new Font("������κ", Font.BOLD, fontTemp);
		jlabelTemp.setFont(font);
		jlabelTemp.setForeground(colorTemp);
	}
}
