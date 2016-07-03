package cn.blockgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * ���ܣ���������壬������һ������Ԥ������塢����˵����塢�÷�������
 * 
 * @author Keira
 *
 */
public class FunctionPanel extends JPanel {
	// ���������Ŀ���
	public static int Function_WIDTH = 150;
	public static int Function_HEIGHT = GameAreaPanel.GAMEAREA_HEIGHT;
	// �ȼ�
	static String level = "����";
	// ����
	static int score = 0;
	// ������
	static int lineNum = 0;
	// Ԥ����
	PreviewJPanel jp_preview;
	//����˵����
	OperationPanel jp_operation;
	// ������
	ScorePanel jp_score;
	 
	public FunctionPanel() {
		this.setLayout(new GridLayout(3, 1));
		// Ԥ����
		jp_preview = new PreviewJPanel();
		//����˵����
		jp_operation = new OperationPanel();		
		//�÷������
		jp_score = new ScorePanel();
		
		//���
		this.add(jp_preview);
		this.add(jp_operation);
		this.add(jp_score);
		
		// �������͸����
		this.setOpaque(false);
		// �������߿���ɫ
		//this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	}
}
