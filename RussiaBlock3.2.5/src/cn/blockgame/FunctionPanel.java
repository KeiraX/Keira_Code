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
 * 功能：功能区面板，包括下一个方块预览区面板、操作说明面板、得分情况面板
 * 
 * @author Keira
 *
 */
public class FunctionPanel extends JPanel {
	// 功能区面板的宽、高
	public static int Function_WIDTH = 150;
	public static int Function_HEIGHT = GameAreaPanel.GAMEAREA_HEIGHT;
	// 等级
	static String level = "初级";
	// 分数
	static int score = 0;
	// 消行数
	static int lineNum = 0;
	// 预览区
	PreviewJPanel jp_preview;
	//操作说明区
	OperationPanel jp_operation;
	// 分数区
	ScorePanel jp_score;
	 
	public FunctionPanel() {
		this.setLayout(new GridLayout(3, 1));
		// 预览区
		jp_preview = new PreviewJPanel();
		//操作说明区
		jp_operation = new OperationPanel();		
		//得分情况区
		jp_score = new ScorePanel();
		
		//添加
		this.add(jp_preview);
		this.add(jp_operation);
		this.add(jp_score);
		
		// 设置面板透明化
		this.setOpaque(false);
		// 设置面板边框及颜色
		//this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
	}
}
