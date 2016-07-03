package cn.blockgame;

import java.util.Random;

/**
 * ���ܣ�����һ��Block�࣬�����������ԣ���������x,��������y,��������type
 * 
 * @author Keira
 */
public class Block {
	// �����x����
	private int x;
	// �����y����
	private int y;
	// ��������
	private int type;

	
	public Block() {}
	
	public Block(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;	
	}
	
	
//	/**
//	 * ���ܣ��ƶ�����
//	 * 
//	 * @return
//	 */
//	public void move(int newRow, int newCol) {
//		// �ƶ�ǰ���жϷ����Ƿ�Խ��
//		if (!isMoveable(newRow, newCol)) {
//			// �����߽�,������
//			return;
//		} else {
//			this.clear();
//			this.x = newRow;
//			this.y = newCol;
////			this.showBlock();
//			new GameAreaPanel().repaint();
//		}
//	}
//	
//	/**
//	 * ���ܣ�ʵ�ַ��������;���в��䣬������
//	 * 
//	 * @return
//	 */
//	public void downMove() {
//		move(x + 1, y);
//	}
//
//	/**
//	 * ���ܣ�ʵ�ַ������ת
//	 * 
//	 * @return
//	 */
//	public void upMove() {
//		// TODO
//	}
//
//	/**
//	 * ���ܣ�ʵ�ַ��������;���в��䣬�м���
//	 * 
//	 * @return
//	 */
//	public void leftMove() {
//		move(x, y - 1);
//	}
//
//	/**
//	 * ���ܣ�ʵ�ַ��������;���в��䣬������
//	 * 
//	 * @return
//	 */
//	public void reghtMove() {
//		move(x, y - 1);
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
