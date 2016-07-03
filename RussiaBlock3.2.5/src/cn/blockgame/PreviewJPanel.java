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
 * ���ܣ���һ������Ԥ����
 * 
 * @author Keira
 *
 */
public class PreviewJPanel extends JPanel {
	// ��һ��������״
	private int[][] nextShape;
	// ��ǰ������״
	private int[][] curShape;
	// ���������״����
	public static int typeTemp = 0;
	// ������ɵ���ת״̬
	public static int stateTemp = 0;
	// ��ǰ������״
	public static int curType;
	// ��ǰ����״̬
	public static int curState;

	public PreviewJPanel() {
		super();
		this.setLayout(new GridLayout(4, 4));
//		JLabel jl_tip = new JLabel("��һ������Ԥ����", JLabel.CENTER);
		ImageIcon icon_view = new ImageIcon("resources\\image\\BlockPreviewArea3.gif");
		JLabel jl_tip = new JLabel(icon_view);
		
		this.add(jl_tip);
		
		// �������͸����
		this.setOpaque(false);
		// �������߿�
		//this.setBorder(new LineBorder(Color.RED, 2));
		// ���������һ������
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
	 * ���ܣ���д����,ʵ�����������һ������
	 * ˼·�� �ж���һ�������Ƿ�Ϊ�� 
	 * 		��Ϊ��,���ȡ��һ������;���ҽ���һ�����鸳ֵ����ǰ���� --
	 * 		�����һ���ǵ�һ������������� ����Ϊ��,��ֱ�ӽ���һ�����鸳ֵ����ǰ����;���ҽ��Ż�ȡ��һ������ķ�����״
	 * ע��:
	 * 		Ӧ�ñ��浱ǰ�ķ�����״������,֮���ٽ�����һ�ε����
	 */
	public int[][] ranNextShape() {
		if (nextShape == null) { // ��һ��
			nextShape = rangenblock();
			curShape = nextShape;
			curType = typeTemp;
			curState = stateTemp;
		} else { // ��һ����������
			curShape = nextShape;
			curType = typeTemp;
			curState = stateTemp;
			nextShape = rangenblock();
		}
		return curShape;
	}

	/**
	 * ���ܣ���дpaintComponent����
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ��ӱ���ͼƬ
//		Image gameBack = Toolkit.getDefaultToolkit().getImage("resources\\image\\star.jpg");
//		g.drawImage(gameBack,0,0,this);
		// ���ж���һ�������Ƿ�Ϊ��
		if (nextShape != null) {
			// System.out.println("---------------------");
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					// ����������������
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect(30 + c * 25, 60 + r * 25, 25, 25);
					// �ж���һ�������ǲ��Ƿ���(1)
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
	 * ���ܣ����ѡ�񷽿���״,ʹ��������������0-6��Χ�ڵ�һ������������ѡ���ж��������ʲô���͵ķ���
	 */
	public int[][] rangenblock() {
		// ��ʱ����
		int[][] ShapeTemp = null;
		Random ran = new Random();
		typeTemp = ran.nextInt(7);
		switch (typeTemp) {
		case 0:
			ShapeTemp = BlockModel.L_SHAPE[stateTemp]; // �������״
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
		// ��ӡ��������
		// System.out.println(typeTemp);
		return ShapeTemp;
	}

}
