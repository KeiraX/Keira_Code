package cn.blockgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 功能：下一个方块预览区
 * 
 * @author Keira
 *
 */
public class PreviewJPanel extends JPanel {
	// 下一个方块形状
	private int[][] nextShape;
	// 当前方块形状
	private int[][] curShape;
	// 随机生成形状类型
	public static int typeTemp = 0;
	// 随机生成的旋转状态
	public static int stateTemp = 0;
	// 当前方块形状
	public static int curType;
	// 当前方块状态
	public static int curState;

	public PreviewJPanel() {
		super();
		this.setLayout(new GridLayout(4, 4));
//		JLabel jl_tip = new JLabel("下一个方块预览区", JLabel.CENTER);
		ImageIcon icon_view = new ImageIcon("resources\\image\\BlockPreviewArea3.gif");
		JLabel jl_tip = new JLabel(icon_view);
		
		this.add(jl_tip);
		
		// 设置面板透明化
		this.setOpaque(false);
		// 设置面板边框
		//this.setBorder(new LineBorder(Color.RED, 2));
		// 随机产生下一个方块
		ranNextShape();
	}

	public int[][] getNextShape() {
		return nextShape;
	}

	public void setNextShape(int[][] nextShape) {
		this.nextShape = nextShape;
	}

	public int[][] getCurShape() {
		return curShape;
	}

	public void setCurShape(int[][] curShape) {
		this.curShape = curShape;
	}

	/**
	 * 功能：编写方法,实现随机产生下一个方块
	 * 思路： 判断下一个方块是否为空 
	 * 		若为空,则获取下一个方块;并且将下一个方块赋值给当前方块 --
	 * 		该情况一般是第一次随机产生方块 若不为空,则直接将下一个方块赋值给当前方块;并且接着获取下一个随机的方块形状
	 * 注意:
	 * 		应该保存当前的方块形状和类型,之后再进行下一次的随机
	 */
	public int[][] ranNextShape() {
		if (nextShape == null) { // 第一次
			nextShape = rangenblock();
			curShape = nextShape;
			curType = typeTemp;
			curState = stateTemp;
		} else { // 第一次以外的情况
			curShape = nextShape;
			curType = typeTemp;
			curState = stateTemp;
			nextShape = rangenblock();
		}
		return curShape;
	}

	/**
	 * 功能：重写paintComponent方法
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 添加背景图片
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);
		// 先判断下一个方块是否为空
		if (nextShape != null) {
			// System.out.println("---------------------");
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					// 给画布绘制网格线
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect(30 + c * 25, 60 + r * 25, 25, 25);
					// 判断下一个方块是不是方块(1)
					if (nextShape[r][c] == 1) {
//						g.setColor(Color.ORANGE);
//						g.fill3DRect(30 + c * 25, 60 + r * 25, 25, 25, true);
						Image icon_block2 = new ImageIcon("resources\\image\\Block25.gif").getImage();
						g.drawImage(icon_block2, 30 + c * 25, 60 + r * 25, null);
					}
				}
			}
		}
	}

	/**
	 * 功能：随机选择方块形状,使用随机数随机产生0-6范围内的一个整数，用于选择判断输出的是什么类型的方块
	 */
	public int[][] rangenblock() {
		// 临时方块
		int[][] ShapeTemp = null;
		Random ran = new Random();
		typeTemp = ran.nextInt(7);
		switch (typeTemp) {
		case 0:
			ShapeTemp = BlockModel.L_SHAPE[stateTemp]; // 方块的形状
			break;
		case 1:
			ShapeTemp = BlockModel.J_SHAPE[stateTemp];
			break;
		case 2:
			ShapeTemp = BlockModel.X_SHAPE[stateTemp];
			break;
		case 3:
			ShapeTemp = BlockModel.I_SHAPE[stateTemp];
			break;
		case 4:
			ShapeTemp = BlockModel.T_SHAPE[stateTemp];
			break;
		case 5:
			ShapeTemp = BlockModel.S_SHAPE[stateTemp];
			break;
		case 6:
			ShapeTemp = BlockModel.Z_SHAPE[stateTemp];
			break;
		default:
			break;
		}
		// 打印测试数据
		// System.out.println(typeTemp);
		return ShapeTemp;
	}

}
